/**
 * 
 */
package checkers.inference.aj;

import java.io.PrintWriter;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.SupportedOptions;
import javax.lang.model.element.AnnotationMirror;

import checkers.basetype.BaseTypeVisitor;
import checkers.inference.Constraint;
import checkers.inference.InferenceChecker;
import checkers.inference.Reference;
import checkers.inference.aj.quals.AJBottom;
import checkers.inference.aj.quals.Aliased;
import checkers.inference.aj.quals.IntAliased;
import checkers.inference.aj.quals.IntSelf;
import checkers.inference.aj.quals.NonAliased;
import checkers.inference.aj.quals.Self;
import checkers.quals.TypeQualifiers;
import checkers.util.AnnotationUtils;

import com.sun.source.tree.CompilationUnitTree;

/**
 * @author huangw5
 *
 */
@SupportedOptions( { "warn", "checking"} ) 
@TypeQualifiers({ Aliased.class, NonAliased.class, IntAliased.class, Self.class, IntSelf.class })
public class AJChecker extends InferenceChecker {
	
    public AnnotationMirror ALIASED, NONALIASED, INTALIASED, SELF, INTSELF, BOTTOM;
    
	private Set<AnnotationMirror> sourceLevelQuals; 

	@Override
	public void initChecker(ProcessingEnvironment processingEnv) {
		super.initChecker(processingEnv);
		AnnotationUtils annoFactory = AnnotationUtils.getInstance(processingEnv);
		
        ALIASED = annoFactory.fromClass(Aliased.class);
        NONALIASED = annoFactory.fromClass(NonAliased.class);
        INTALIASED = annoFactory.fromClass(IntAliased.class);
        SELF = annoFactory.fromClass(Self.class);
        INTSELF = annoFactory.fromClass(IntSelf.class);
        BOTTOM = annoFactory.fromClass(AJBottom.class);
        
		sourceLevelQuals = AnnotationUtils.createAnnotationSet();
		sourceLevelQuals.add(ALIASED);
		sourceLevelQuals.add(NONALIASED);
		sourceLevelQuals.add(INTALIASED);

	}

	/* (non-Javadoc)
	 * @see checkers.inference.InferenceChecker#isStrictSubtyping()
	 */
	@Override
	public boolean isStrictSubtyping() {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see checkers.inference.InferenceChecker#getFailureStatus(checkers.inference.Constraint)
	 */
	@Override
	public FailureStatus getFailureStatus(Constraint c) {
		return FailureStatus.ERROR;
	}

	/* (non-Javadoc)
	 * @see checkers.inference.InferenceChecker#getInferenceVisitor(checkers.inference.InferenceChecker, com.sun.source.tree.CompilationUnitTree)
	 */
	@Override
	public BaseTypeVisitor<?> getInferenceVisitor(InferenceChecker checker,
			CompilationUnitTree root) {
		return new AJInferenceVisitor((AJChecker) checker, root);
	}

	/* (non-Javadoc)
	 * @see checkers.inference.InferenceChecker#getSourceLevelQualifiers()
	 */
	@Override
	public Set<AnnotationMirror> getSourceLevelQualifiers() {
		return sourceLevelQuals;
	}

	/* (non-Javadoc)
	 * @see checkers.inference.InferenceChecker#adaptField(javax.lang.model.element.AnnotationMirror, javax.lang.model.element.AnnotationMirror)
	 */
	@Override
	public AnnotationMirror adaptField(AnnotationMirror contextAnno,
			AnnotationMirror declAnno) {
		if (contextAnno.toString().equals(INTSELF.toString())) { 
			if (declAnno.toString().equals(SELF.toString()))
				return null;
			else
				return declAnno;
		}
		if (contextAnno.toString().equals(SELF.toString())) {
			if (declAnno.toString().equals(INTSELF.toString()))
				return null;
			else
				return declAnno;
		}
		if (contextAnno.toString().equals(INTALIASED.toString())) {
			if (declAnno.toString().equals(INTSELF.toString()))
				return INTALIASED;
			if (declAnno.toString().equals(SELF.toString()))
				return null;
			else
				return declAnno;
		}
		if (contextAnno.toString().equals(ALIASED.toString())) {
			if (declAnno.toString().equals(SELF.toString()))
				return ALIASED;
			if (declAnno.toString().equals(INTSELF.toString()))
				return null;
			else
				return declAnno;
		}
		// If control reaches here, then adapter is NONALIASED
		if (declAnno.toString().equals(NONALIASED.toString()) 
				|| declAnno.toString().equals(SELF.toString()))
			return NONALIASED;
		else 
			return null;

	}

	/* (non-Javadoc)
	 * @see checkers.inference.InferenceChecker#adaptMethod(javax.lang.model.element.AnnotationMirror, javax.lang.model.element.AnnotationMirror)
	 */
	@Override
	public AnnotationMirror adaptMethod(AnnotationMirror contextAnno,
			AnnotationMirror declAnno) {
		return adaptField(contextAnno, declAnno);
	}

	/* (non-Javadoc)
	 * @see checkers.inference.InferenceChecker#getAnnotaionWeight(javax.lang.model.element.AnnotationMirror)
	 */
	@Override
	public int getAnnotaionWeight(AnnotationMirror anno) {
		if (anno.toString().equals(INTALIASED.toString()))
			return 1;
		if (anno.toString().equals(NONALIASED.toString()))
			return 2;
		else if (anno.toString().equals(ALIASED.toString()))
			return 3;
		else if (anno.toString().equals(INTSELF.toString()))
			return 4;
		else if (anno.toString().equals(SELF.toString()))
			return 5;			
		else 
			return Integer.MAX_VALUE;
	}

	/* (non-Javadoc)
	 * @see checkers.inference.InferenceChecker#printResult(java.util.Map, java.io.PrintWriter)
	 */
	@Override
	public void printResult(Map<String, Reference> solution, PrintWriter out) {
		// TODO Auto-generated method stub

	}

}
