/**
 * 
 */
package checkers.inference;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.SupportedOptions;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.TypeMirror;

import checkers.basetype.BaseTypeChecker;
import checkers.basetype.BaseTypeVisitor;
import checkers.types.AnnotatedTypeMirror;
import checkers.types.TypeHierarchy;
import checkers.util.AnnotationUtils;
import checkers.util.MultiGraphQualifierHierarchy;
import checkers.util.TreeUtils;

import com.sun.source.tree.ClassTree;
import com.sun.source.tree.CompilationUnitTree;
import com.sun.source.tree.MethodTree;
import com.sun.source.tree.StatementTree;
import com.sun.source.tree.Tree;
import com.sun.source.util.SourcePositions;
import com.sun.source.util.Trees;
import com.sun.tools.javac.code.Symbol;
import com.sun.tools.javac.code.Symbol.TypeSymbol;
import com.sun.tools.javac.code.Type;
import com.sun.tools.javac.code.Type.ClassType;
import com.sun.tools.javac.comp.AttrContext;
import com.sun.tools.javac.comp.Enter;
import com.sun.tools.javac.comp.Env;
import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeInfo;

/**
 * @author huangw5
 *
 */
@SupportedOptions( { "warn", "checking" } ) 
public abstract class InferenceChecker extends BaseTypeChecker {
	
	private boolean isChecking = false; 
	
	protected Enter enter;

	private SourcePositions positions;
	
	private Comparator<AnnotationMirror> comparator;
	
	private Set<String> libPureMethods = null;
	
	public static enum FailureStatus {
		IGNORE,
		WARN,
		ERROR
	}
	
	@Override
	public void initChecker(ProcessingEnvironment processingEnv) {
		super.initChecker(processingEnv);
		isChecking = processingEnv.getOptions().containsKey("checking");
		this.enter = Enter.instance(((JavacProcessingEnvironment) env).getContext());
		this.positions = Trees.instance(getProcessingEnvironment()).getSourcePositions();
		InferenceMain.getInstance().setInferenceChcker(this);
		
	}
	
	@Override
	protected BaseTypeVisitor<?> createSourceVisitor(CompilationUnitTree root) {
		if (!isChecking) {
			// create inference visitor
			return getInferenceVisitor(this, root);
		}
		return super.createSourceVisitor(root);
	}
	
	@Override
	protected TypeHierarchy createTypeHierarchy() {
    	return new InferenceTypeHierarchy(this, getQualifierHierarchy());
	}
	
	
    /** Factory method to easily change what Factory is used to
     * create a QualifierHierarchy.
     */
	@Override
    protected MultiGraphQualifierHierarchy.MultiGraphFactory createQualifierHierarchyFactory() {
        return new InferenceGraphQualifierHierarchy.InferenceGraphFactory(this);
    }

	public boolean isChecking() {
		return isChecking;
	}
	
	/**
	 * Get the root tree of element elt
	 * @param elt
	 * @return
	 */
	public CompilationUnitTree getRootByElement(Element elt) {
		Symbol symbol = (Symbol) elt;
		TypeSymbol enclosing = symbol.enclClass();
		Env<AttrContext> env = enter.getEnv(enclosing);
		if (env == null)
			return null;
		return env.toplevel;
	}
	
	public Tree getDeclaration(Element elt) {
        Tree fromElt;
        CompilationUnitTree newRoot = getRootByElement(elt);
        if (newRoot == null)
        	return null;
        switch (elt.getKind()) {
        case CLASS:
        case ENUM:
        case INTERFACE:
        case ANNOTATION_TYPE:
        case FIELD:
        case ENUM_CONSTANT:
        case METHOD:
        case CONSTRUCTOR:
            fromElt = trees.getTree(elt);
            break;
        default:
            fromElt = TreeInfo.declarationFor((Symbol)elt, (JCTree)newRoot);
            break;
        }
        return fromElt;
	}
	
	public boolean isExceptionClass(AnnotatedTypeMirror type) {
		boolean result = false;
		TypeMirror underlyingType = type.getUnderlyingType();
		if (underlyingType instanceof ClassType) {
			Type supertype_field = ((ClassType) underlyingType).supertype_field;
			while (supertype_field != null) {
				if (supertype_field.toString().equals("java.lang.Throwable")) {
					result = true;
					break;
				} else if (supertype_field instanceof ClassType) {
					supertype_field = ((ClassType) supertype_field).supertype_field;
				} else
					break;
			}
		}
		return result;
	}


	
	/**
	 * Decide if {@code methodElt} is a pure library method. It reads pure
	 * library methods from a text file, which includes all pure library 
	 * methods.
	 * @param methodElt
	 * @return
	 */
	public boolean isPureLibraryMethod(ExecutableElement methodElt) {
		if (libPureMethods == null) {
			libPureMethods = new HashSet<String>();
			String property = getProcessingEnvironment().getOptions().get(
					"libPureMethods");
			if (property == null) {
				// No library pure methods are given
				System.err.println("WARN: all library methods are assumed as impure.");
				System.err.println("\tPure libary methods can be given by "
						+ "-AlibPureMethods=file1:file2 separated by colons");
			} else {
				for (String fileName : property.split(":")) {
					fileName = fileName.trim();
			    	BufferedReader br = null;
			    	try {
			    		br = new BufferedReader(new FileReader(fileName));
			    		String line = null;
			    		while ((line = br.readLine()) != null) {
			    			line = line.trim();
			    			if (!line.startsWith("#"))
				    			libPureMethods.add(line);
			    		}
			    	} catch (FileNotFoundException e) {
						System.out.println("WARN: Cannot find pure library "
								+ "method file " + fileName);
			    	} catch (Exception e) {e.printStackTrace(); }
			    	finally {
						try {
							if (br != null)
								br.close();
						} catch (IOException e) {}
			    	}
				}
			}
		}
		String methodSignature = InferenceUtils.getMethodSignature(methodElt);
		return libPureMethods.contains(methodSignature);
	}
	
	
	/**
	 * Check if the {@code elt} is from library
	 * @param elt
	 * @return
	 */
	public boolean isFromLibrary(Element elt) {
		return this.getRootByElement(elt) == null;
	}
	
	
	public boolean isCompilerAddedConstructor(ExecutableElement methodElt) {
		MethodTree node = (MethodTree) getDeclaration(methodElt);
		Element enclosingElement = methodElt.getEnclosingElement();
		if (enclosingElement instanceof TypeElement && TreeUtils.isConstructor(node)) {
			// Check if it is a default constructor
			if (node.getParameters().isEmpty()) {
				List<? extends StatementTree> statements = node.getBody().getStatements();
				if (statements.size() == 1 && statements.get(0).toString().contains("super")) {
					CompilationUnitTree root = getRootByElement(methodElt);
					long constructorLineNum = positions.getStartPosition(root, node);
					constructorLineNum = root.getLineMap().getLineNumber(constructorLineNum);
					ClassTree enclosingClassDecl = (ClassTree) getDeclaration(enclosingElement);
					long classLineNum = positions.getStartPosition(root, enclosingClassDecl);
					classLineNum = root.getLineMap().getLineNumber(classLineNum);
					if (constructorLineNum == classLineNum) {
						// a default constructor, skip it
						return true;
					}
				}
			}
		}
        return false;
	}
	
	/**
	 * Adapt the declared type of a field from the point of view the receiver
	 * @param contextSet The set of annotations of the receiver type
	 * @param declSet The set of annotations of the declared type
	 * @return
	 */
	public Set<AnnotationMirror> adaptFieldSet(Set<AnnotationMirror> contextSet,
			Set<AnnotationMirror> declSet) {
		Set<AnnotationMirror> outSet = AnnotationUtils.createAnnotationSet();
		for (AnnotationMirror declAnno : declSet) {
			for (AnnotationMirror rcvAnno : contextSet) {
				AnnotationMirror anno = adaptField(rcvAnno, declAnno);
				if (anno != null)
					outSet.add(anno);
			}
		}
		return outSet;
	}
	
	/**
	 * Adapt the declared type of a parameter/return from the point of view the
	 * receiver
	 * 
	 * @param contextSet
	 *            The set of annotations of the receiver type
	 * @param declSet
	 *            The set of annotations of the declared type
	 * @return
	 */
	public Set<AnnotationMirror> adaptMethodSet(Set<AnnotationMirror> contextSet,
			Set<AnnotationMirror> declSet) {
		Set<AnnotationMirror> outSet = AnnotationUtils.createAnnotationSet();
		for (AnnotationMirror declAnno : declSet) {
			for (AnnotationMirror rcvAnno : contextSet) {
				AnnotationMirror anno = adaptMethod(rcvAnno, declAnno);
				if (anno != null)
					outSet.add(anno);
			}
		}
		return outSet;
	}
	
    /**
	 * Create the comparator
	 * @return
	 */
	public Comparator<AnnotationMirror> getComparator() {
		if (comparator == null) {
			comparator = new Comparator<AnnotationMirror>() {
				@Override
				public int compare(AnnotationMirror o1, AnnotationMirror o2) {
					int ow1 = getAnnotaionWeight(o1);
					int ow2 = getAnnotaionWeight(o2);
					return ow1 - ow2;
				}
			};
		}
		return comparator;
	}
	
	/**
	 * Indicate if it needs to force all elements in sup to be the super type
	 * of all elements in sub;
	 * @return
	 */
	public abstract boolean isStrictSubtyping();
				
	/**
	 * Indicate if the error on this constraint can be ignored
	 * @param c
	 * @return
	 */
	public abstract FailureStatus getFailureStatus(Constraint c);
	
	public abstract BaseTypeVisitor<?> getInferenceVisitor(InferenceChecker checker, 
			CompilationUnitTree root);
	
	public abstract Set<AnnotationMirror> getSourceLevelQualifiers();

	public abstract AnnotationMirror adaptField(AnnotationMirror contextAnno, 
			AnnotationMirror declAnno);
	
	public abstract AnnotationMirror adaptMethod(AnnotationMirror contextAnno, 
			AnnotationMirror declAnno);
	
	public abstract int getAnnotaionWeight(AnnotationMirror anno);
	
	public abstract void printResult(Map<String, Reference> solution, 
			PrintWriter out);
}
