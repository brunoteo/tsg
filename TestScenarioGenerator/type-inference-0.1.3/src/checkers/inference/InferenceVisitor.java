/**
 * 
 */
package checkers.inference;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeKind;
import javax.lang.model.type.TypeMirror;

import checkers.basetype.BaseTypeVisitor;
import checkers.inference.Reference.ArrayReference;
import checkers.inference.Reference.DeclaredReference;
import checkers.inference.Reference.ExecutableReference;
import checkers.inference.Reference.PrimitiveReference;
import checkers.source.Result;
import checkers.types.AnnotatedTypeMirror;
import checkers.types.AnnotatedTypeMirror.AnnotatedDeclaredType;
import checkers.types.AnnotatedTypeMirror.AnnotatedExecutableType;
import checkers.types.AnnotatedTypeMirror.AnnotatedTypeVariable;
import checkers.util.ElementUtils;
import checkers.util.InternalUtils;
import checkers.util.Pair;
import checkers.util.TreeUtils;

import com.sun.source.tree.AnnotatedTypeTree;
import com.sun.source.tree.ArrayAccessTree;
import com.sun.source.tree.AssignmentTree;
import com.sun.source.tree.BinaryTree;
import com.sun.source.tree.ClassTree;
import com.sun.source.tree.CompilationUnitTree;
import com.sun.source.tree.CompoundAssignmentTree;
import com.sun.source.tree.ConditionalExpressionTree;
import com.sun.source.tree.EnhancedForLoopTree;
import com.sun.source.tree.ExpressionTree;
import com.sun.source.tree.IdentifierTree;
import com.sun.source.tree.MemberSelectTree;
import com.sun.source.tree.MethodInvocationTree;
import com.sun.source.tree.MethodTree;
import com.sun.source.tree.NewArrayTree;
import com.sun.source.tree.NewClassTree;
import com.sun.source.tree.ParameterizedTypeTree;
import com.sun.source.tree.ParenthesizedTree;
import com.sun.source.tree.PrimitiveTypeTree;
import com.sun.source.tree.ReturnTree;
import com.sun.source.tree.Tree;
import com.sun.source.tree.Tree.Kind;
import com.sun.source.tree.TypeCastTree;
import com.sun.source.tree.UnaryTree;
import com.sun.source.tree.VariableTree;
import com.sun.tools.javac.code.Type.ClassType;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.JCTree.JCBinary;
import com.sun.tools.javac.tree.JCTree.JCUnary;
import com.sun.tools.javac.tree.JCTree.Tag;

/**
 * It has two modes: inference mode and checking mode
 * @author huangw5
 *
 */
public abstract class InferenceVisitor extends BaseTypeVisitor<InferenceChecker> {
	
	protected final InferenceAnnotatedTypeFactory factory;
	
	/** For recording visited method invocation trees or allocation sites */
	private Set<Tree> visited = new HashSet<Tree>();

	public InferenceVisitor(InferenceChecker checker, CompilationUnitTree root) {
		super(checker, root);
		this.factory = (InferenceAnnotatedTypeFactory) atypeFactory;
	}
	
	public static enum AdaptContext {
		NONE,
		RECEIVER,
		ASSIGNTO
	}
	
	
    
	@Override
	protected void commonAssignmentCheck(Tree varTree, ExpressionTree valueExp,
			String errorKey) {
		// pass
	}

	@Override
	protected void commonAssignmentCheck(AnnotatedTypeMirror varType,
			ExpressionTree valueExp, String errorKey) {
		// pass
	}

	@Override
	protected void commonAssignmentCheck(AnnotatedTypeMirror varType,
			AnnotatedTypeMirror valueType, Tree valueTree, String errorKey) {
		// pass
	}

	@Override
	protected boolean checkMethodInvocability(AnnotatedExecutableType method,
			MethodInvocationTree node) {
		// pass
		return true;
	}

	@Override
	protected boolean checkConstructorInvocation(AnnotatedDeclaredType dt,
			AnnotatedExecutableType constructor, Tree src) {
		// pass	
		return true;
	}

	@Override
	protected void checkArguments(
			List<? extends AnnotatedTypeMirror> requiredArgs,
			List<? extends ExpressionTree> passedArgs) {
		// pass
	}

	@Override
	protected void checkAssignability(AnnotatedTypeMirror varType, Tree varTree) {
		// pass
	}

	@Override
	protected void checkTypecastRedundancy(TypeCastTree node, Void p) {
		// pass
	}

	@Override
	protected void checkTypecastSafety(TypeCastTree node, Void p) {
		// pass
	}

	@Override
	protected boolean checkOverride(MethodTree overriderTree,
			AnnotatedDeclaredType enclosingType,
			AnnotatedExecutableType overridden,
			AnnotatedDeclaredType overriddenType, Void p) {
		// pass
		return true;
	}

	@Override
	public Void visitAssignment(AssignmentTree node, Void p) {
		ExpressionTree variable = node.getVariable();
		ExpressionTree expression = node.getExpression();
		generateConstraint(variable, expression);
		return super.visitAssignment(node, p);
	}
	
	/**
     * A unary operation. ++, --, ...
     */
    @Override
    public Void visitUnary(UnaryTree node, Void p) {
		if (!visited.contains(node)) {
	        if (node instanceof JCUnary) {
	            Tag tag = ((JCUnary) node).getTag();
	            switch (tag) {
	            case PREINC:
	            case PREDEC:
	            case POSTINC:
	            case POSTDEC:
	                ExpressionTree expr = node.getExpression();
	                Reference ref = Reference.createReference(expr, factory);
	                generateConstraint(expr, ref);
	            }
	        }
		}
        return super.visitUnary(node, p);
    }

	
	@Override
    public Void visitBinary(BinaryTree node, Void p) {
		if (!visited.contains(node)) {
//	    	System.err.println("WARN: unhandled binary " + node);
			if (node instanceof JCBinary) {
	            Tag tag = ((JCBinary) node).getTag();
	            switch (tag) {
	            case BITOR_ASG: // |=
	            case BITXOR_ASG: // ^=
	            case BITAND_ASG: // &=
	            case SL_ASG: // <<=
	            case SR_ASG: // >>=
	            case USR_ASG: // >>>=
	            case PLUS_ASG: // +=
	            case MINUS_ASG: // -=
	            case MUL_ASG: // *=
	            case DIV_ASG: // /=
	            case MOD_ASG: // %=
	                ExpressionTree expr = node.getLeftOperand();
	                Reference ref = Reference.createReference(expr, factory);
	                generateConstraint(expr, ref);
	            }
	        }
		}
        return super.visitBinary(node, p);
    }

	
    /**
     * An assignment with "+=", "|=" ...
     */
	@Override
	public Void visitCompoundAssignment(CompoundAssignmentTree node, Void p) {
		if (!visited.contains(node)) {
//	    	System.err.println("WARN: unhandled compound " + node);
	        ExpressionTree var = node.getVariable();
	        ExpressionTree expr = node.getExpression();
	        generateConstraint(var, expr);
		}
		return super.visitCompoundAssignment(node, p);
	}
	
    @Override
    public Void visitEnhancedForLoop(EnhancedForLoopTree node, Void p) {
    	// In the enhanced for loop: for (X var : iterables)
    	// it is equivalent to X var; var = iterables.iteratedType;
    	// Generate constraints for var, iterated_type and iterables
    	
    	// Get the variable
        VariableTree varTree = node.getVariable();
		Reference varRef = Reference.createReference(
				TreeUtils.elementFromDeclaration(varTree), factory);
        // Get the expression
        ExpressionTree expr = node.getExpression();
//        if (expr.toString().equals("this"))
//        	System.out.println();
        AnnotatedTypeMirror exprType = factory.getAnnotatedType(expr);
		Reference exprRef = Reference.createReference(expr, factory);
    	// Recursively
    	generateConstraint(exprRef, expr);
        
        // In the case of arrays
        if (exprType.getKind() == TypeKind.ARRAY) {
        	ArrayReference arrayRef = (ArrayReference) exprRef;
        	Reference componentRef = arrayRef.getComponentRef();
        	InferenceMain.getInstance().getConstraintManager().addSubtypeConstraint(
        			getFieldAdaptReference(arrayRef, componentRef, varRef),
        			varRef);
        } else {
        	// It is an iterable type
        	DeclaredReference dRef = (DeclaredReference) exprRef;
//        	if (dRef.getTypeArguments().isEmpty() 
//        			&& expr.toString().contentEquals("this")) {
//        		// When expr is "this", the type doesn't contain type arguments
//        		ExecutableElement currentMethodElt = getCurrentMethodElt();
//        		if (currentMethodElt != null) {
//					dRef = (DeclaredReference) ((ExecutableReference) Reference.createReference(currentMethodElt,
//							factory)).getReceiverRef();
//        		}
//        	}
        	if (!dRef.getTypeArguments().isEmpty()) {
	        	// Get the first type reference
	        	Reference iteratedRef = dRef.getTypeArguments().get(0);
	        	InferenceMain.getInstance().getConstraintManager().addSubtypeConstraint(
	        			getFieldAdaptReference(dRef, iteratedRef, varRef),
	        			varRef);
        	}
        }
        return super.visitEnhancedForLoop(node, p);
    }
    
    
	@Override
	public Void visitMethod(MethodTree node, Void p) {
		ExecutableElement methodElt = TreeUtils.elementFromDeclaration(node);
//		if (methodElt.toString().equals("printStackTrace()"))
//			System.out.println();
		// First create method reference
		Reference methodRef = Reference.createReference(methodElt, factory);
		InferenceMain.getInstance().getConstraintManager().addEmptyConstraint(methodRef);
//		if (methodElt.getReturnType().getKind() != TypeKind.VOID) {
//			Reference returnRef = Reference.createReference(methodElt, 
//					methodType.getReturnType());
//			InferenceMain.getInstance().getConstraintManager().addEmptyConstraint(returnRef);
//		}
		
		// Add override constraints
        // Find which method this overrides!
        Map<AnnotatedDeclaredType, ExecutableElement> overriddenMethods = annoTypes
                .overriddenMethods(methodElt);
        for (Map.Entry<AnnotatedDeclaredType, ExecutableElement> pair 
        		: overriddenMethods.entrySet()) {
        	ExecutableElement overriddenElement = pair.getValue();
        	handleMethodOverride(methodElt, overriddenElement);
        }
		
		return super.visitMethod(node, p);
	}
    
    @Override
	public Void visitMethodInvocation(MethodInvocationTree node, Void p) {
    	// If this statement is enclosed in another expression, then it should
    	// have been visited and we skip it. 
    	// E.g. X x = y.m(z); this method invocation is visited in the assignment
    	// E.g. y.m(x.n(z));  the x.n(z) is visited as an argument   
    	ExecutableElement methodElt = TreeUtils.elementFromUse(node);
    	
    	if (node.toString().equals("super()") 
    			&& methodElt.toString().equals("Object()")) {
    		// skip
    		return super.visitMethodInvocation(node, p);
    	}
    	
//    	if (node.toString().contains("java.security.AccessController.checkPermission(perm)"))
//    		System.out.println();
    	
		if (!visited.contains(node)) {
    		// The enclosing is not an expression
    		// Generate constraints for receiver and parameters
    		Reference rcvRef;
			if (!ElementUtils.isStatic(methodElt)) {
				ExpressionTree rcvTree = InferenceUtils.getReceiverTree(node);
				if (rcvTree == null) {
					// This may be a a self invocation like x = m(z); 
					ExecutableElement currentMethodElt = getCurrentMethodElt();
					assert currentMethodElt != null;
					Reference currentMethodRef = Reference.createReference(
							currentMethodElt, factory);
					rcvRef = ((ExecutableReference) currentMethodRef).getReceiverRef();
				} else {
					rcvRef = Reference.createReference(rcvTree, factory);
					// generate constraints on the receiver recursively
					generateConstraint(rcvRef, rcvTree);
				}
			} else 
				rcvRef = null;
			Reference assignTo = null;
			if (methodElt.getReturnType().getKind() != TypeKind.VOID) {
				assignTo = Reference.createReference(node, factory);
			}
			handleMethodCall(methodElt, node.getArguments(), rcvRef, assignTo); // WEI: July 7
    	}
		return super.visitMethodInvocation(node, p);
	}
    
    
	@Override
	public Void visitNewArray(NewArrayTree node, Void p) {
    	// If this statement is enclosed in another expression, then it should
    	// have been visited and we skip it. 
    	// E.g. X[] x = new X[3]; this method invocation is visited in the assignment
		if (!visited.contains(node)) {
    		System.out.println("WARN: Unhandled statement " + node);
    	}
		return super.visitNewArray(node, p);
	}
	

	@Override
	public Void visitNewClass(NewClassTree node, Void p) {
    	// If this statement is enclosed in another expression, then it should
    	// have been visited and we skip it. 
    	// E.g. X x = new X(); this method invocation is visited in the assignment
		if (!visited.contains(node)) {
			List<? extends ExpressionTree> arguments = node.getArguments();
			Reference rcvRef = Reference.createReference(node, factory);
			// TODO: lhsRef passed to  generateMethodCallConstraints() is null
			handleMethodCall(TreeUtils.elementFromUse(node), arguments, rcvRef, null);
    	}
		return super.visitNewClass(node, p);
	}


	@Override
	public Void visitReturn(ReturnTree node, Void p) {
		ExpressionTree expr = node.getExpression();
		if (expr != null) {
			Reference exprRef = Reference.createReference(expr, factory);
			// Recursively
			generateConstraint(exprRef, expr);
			
			MethodTree methodTree = TreeUtils.enclosingMethod(getCurrentPath());
			ExecutableElement methodElt = (ExecutableElement) 
					TreeUtils.elementFromDeclaration(methodTree);
			// Get the reference of return 
			Reference methodRef = Reference.createReference(methodElt, factory);
			Reference returnRef = ((ExecutableReference) methodRef).getReturnRef();
			InferenceMain.getInstance().getConstraintManager().addSubtypeConstraint(exprRef, returnRef);
		}
		return super.visitReturn(node, p);
	}
	

	@Override
	public Void visitVariable(VariableTree node, Void p) {
		ExpressionTree initializer = node.getInitializer();
		Reference varRef = Reference.createReference(
				TreeUtils.elementFromDeclaration(node), factory);
		if (initializer != null) {
			// Add subtype constraint
			Reference initilizerRef = Reference.createReference(initializer, 
					factory);
			InferenceMain.getInstance().getConstraintManager().addSubtypeConstraint(initilizerRef, varRef);
			generateConstraint(initilizerRef, initializer);
		} else 
			InferenceMain.getInstance().getConstraintManager().addEmptyConstraint(varRef);
		return super.visitVariable(node, p);
	}
	
	
	@Override
	public Void visitClass(ClassTree node, Void p) {
		TypeElement classElt = TreeUtils.elementFromDeclaration(node);
		InferenceMain.getInstance().getConstraintManager().addVisitedClass(classElt);
		return super.visitClass(node, p);
	}

	/**
	 * Get current method element; 
	 * @return <code>null</code> if it is not in a method; otherwise return the
	 * current method element. 
	 */
    public ExecutableElement getCurrentMethodElt() {
        MethodTree enclosingMethod = TreeUtils.enclosingMethod(
        		this.getCurrentPath());
        if (enclosingMethod == null)
            return null;
        else
            return TreeUtils.elementFromDeclaration(enclosingMethod);
    }
    
	/**
	 * lhsTree = rhsTree;
	 * ===>
	 * lhsTree = lhsRef; lhsRef = rhsRef; rhsRef = rhsTree;
	 * @param lhsTree
	 * @param rhsTree
	 */
	protected void generateConstraint(ExpressionTree lhsTree, ExpressionTree rhsTree) {
		Reference lhsRef = Reference.createReference(lhsTree, factory);
		Reference rhsRef = Reference.createReference(rhsTree, factory);
		InferenceMain.getInstance().getConstraintManager().addSubtypeConstraint(rhsRef, lhsRef);
		generateConstraint(lhsTree, lhsRef);
		generateConstraint(rhsRef, rhsTree);
	}
	
	/**
	 * Generate constraints for lhsRef = rhsTree;
	 * @param lhsRef
	 * @param rhsTree
	 */
	protected void generateConstraint(Reference lhsRef, ExpressionTree rhsTree) {
		switch (rhsTree.getKind()) {
		case NEW_CLASS:
			NewClassTree ncTree = (NewClassTree) rhsTree;
			ExecutableElement methodElt = TreeUtils.elementFromUse(ncTree);
			Reference rcvRef = Reference.createReference(ncTree, factory);
			// Recursively
//			generateConstraint(rcvRef, ncTree);
			// TODO: lhsRef passed to  generateMethodCallConstraints() is null
			handleMethodCall(methodElt, ncTree.getArguments(), rcvRef, lhsRef);
			break;
		case METHOD_INVOCATION:
			MethodInvocationTree miTree = (MethodInvocationTree) rhsTree;
			ExecutableElement iMethodElt = TreeUtils.elementFromUse(miTree);
			ExpressionTree rcvTree = InferenceUtils.getReceiverTree(miTree);
			Reference iRcvRef = null;
			if (!ElementUtils.isStatic(iMethodElt)) {
				ExecutableElement currentMethodElt = getCurrentMethodElt();
				if (rcvTree == null) {
					// This may be a a self invocation like x = m(z); 
//					assert currentMethodElt != null;
					if (currentMethodElt == null)
						iRcvRef = null;
					else {
						Reference currentMethodRef = Reference.createReference(
								currentMethodElt, factory);
						iRcvRef = ((ExecutableReference) currentMethodRef)
								.getReceiverRef();
					}
				} else {
					iRcvRef = Reference.createReference(rcvTree, factory);
					// generate constraints on the receiver recursively
					generateConstraint(iRcvRef, rcvTree);
				}
			} else 
				iRcvRef = null;
			handleMethodCall(iMethodElt, miTree.getArguments(), iRcvRef, lhsRef);
			break;
		case MEMBER_SELECT:
			MemberSelectTree mTree = (MemberSelectTree) rhsTree;
            ExpressionTree expr = mTree.getExpression();
            Element fieldElt = TreeUtils.elementFromUse(mTree);
            if (isAccessOuterThis(mTree)) {
            	// If it is like Body.this
            	Element outerElt = getOuterThisElement(mTree);
            	if (outerElt != null 
            			&& outerElt.getKind() ==  ElementKind.METHOD) {
            		ExecutableElement outerExecutableElt = (ExecutableElement) outerElt;
					Reference outerMethodRef = Reference.createReference(
							outerExecutableElt, factory);
            		Reference outerThisRef = ((ExecutableReference) outerMethodRef).getReceiverRef();
            		InferenceMain.getInstance().getConstraintManager().addSubtypeConstraint(outerThisRef, lhsRef);
            	}
			} else if (!fieldElt.getSimpleName().contentEquals("super")
					&& isFieldElt(expr, fieldElt)) {
            	Reference fieldRef = Reference.createReference(fieldElt, factory);
                // TODO There may be type casts on "this", 
            	// e.g. ((@mutable X) this).field
				Reference exprRef = Reference.createReference(expr, factory);
            	// Recursively generate constraints
            	generateConstraint(exprRef, expr);
            	handleFieldRead(lhsRef, exprRef, fieldRef);
            } 
			break;
		case IDENTIFIER:
			ExecutableElement currentMethodElt = getCurrentMethodElt();
			IdentifierTree idTree = (IdentifierTree) rhsTree;
			Element idElt = TreeUtils.elementFromUse(idTree);
			// TODO: idElt should be the same as idTree. They should be equivalent. 
			// If idElt is "this", then we create the thisRef
			Reference idRef = null;
			if (idElt.getSimpleName().contentEquals("this")
					&& currentMethodElt != null) {
				Reference currentMethodRef = Reference.createReference(
						currentMethodElt, factory);
				idRef = ((ExecutableReference) currentMethodRef).getReceiverRef();
			} else
				idRef = Reference.createReference(idElt, factory);
			// Check if idElt is a field or not
			if (idRef == null) {
				// do nothing. This happens when currentMethodElt is null;
			} else if (!idElt.getSimpleName().contentEquals("this")
//					&& idElt.getKind() == ElementKind.FIELD
					&& isCurrentFieldElt(idElt)
					&& currentMethodElt != null) {
				Reference currentMethodRef = Reference.createReference(
						currentMethodElt, factory);
				Reference thisRef = ((ExecutableReference) currentMethodRef).getReceiverRef();
				handleFieldRead(lhsRef, thisRef, idRef);
			} else if (lhsRef.getTree() != null && lhsRef.getTree().equals(rhsTree)){
				// They are equivalent
				InferenceMain.getInstance().getConstraintManager().addEqualityConstraint(lhsRef, idRef);
			} else {
				InferenceMain.getInstance().getConstraintManager().addSubtypeConstraint(idRef, lhsRef);
			}
			break;
		case NEW_ARRAY:
			NewArrayTree nArrayTree = (NewArrayTree) rhsTree;
//			if (nArrayTree.toString().contains("user") && nArrayTree.toString().contains("pass"))
//				System.out.println();
			// Create the reference of the new array
			ArrayReference nArrayRef = (ArrayReference) Reference
					.createReference(nArrayTree, factory);
			// Generate constraints
			InferenceMain.getInstance().getConstraintManager().addSubtypeConstraint(nArrayRef, lhsRef);
			
			List<? extends ExpressionTree> aInitializers = nArrayTree.getInitializers();
			
			// Generate constraints for the initializers and the array 
			// if the initializers are not empty
			if (aInitializers != null && !aInitializers.isEmpty()) {
				// Get its component reference
				Reference componentRef = nArrayRef.getComponentRef();
				// TODO: What is the relation between the initializer
				// and the component? In the previous implementation, 
				// there is no adapt... 
				// I think it should do the adapt first, because it is
				// equivalent to:
				// X[] a = new X[2]; a[0] = x1; a[1] = x2;
				for (ExpressionTree initializer : aInitializers) {
					Reference initializerRef = Reference.createReference(
							initializer, factory);
					// Recursively 
					generateConstraint(initializerRef, initializer);
					// Now add the adapt constraint
					InferenceMain.getInstance().getConstraintManager().addSubtypeConstraint(initializerRef, 
							getFieldAdaptReference(nArrayRef, componentRef, null));
				}
			}
			break;
		case ARRAY_ACCESS:
			ArrayAccessTree aaTree = (ArrayAccessTree) rhsTree;
			ExpressionTree aaExpr = aaTree.getExpression();
			Reference exprRef = Reference.createReference(aaExpr, factory);
			
			// Recursively
			generateConstraint(exprRef, aaExpr);
			
			// Get the component reference of this array access
			Reference componentRef = ((ArrayReference) exprRef).getComponentRef();
			
			// Now add the adapt constraint
			handleArrayRead(lhsRef, exprRef, componentRef);
			
			break;
		case TYPE_CAST:
			// Get the tree being casted 
			ExpressionTree castedTree = ((TypeCastTree) rhsTree).getExpression();
			Reference castedRef = Reference
					.createReference(castedTree, factory);
			// Recursively 
			generateConstraint(castedRef, castedTree);
			
			AnnotatedTypeMirror rhsType = factory.getAnnotatedType(rhsTree);
			if (!rhsType.isAnnotated()) {
				// In the case no annotations appear in the cast 
				// connect the casted expr and the lhs 
				// TODO rhsType may have been annotated with all possible qualifiers
				InferenceMain.getInstance().getConstraintManager().addSubtypeConstraint(castedRef, 
						lhsRef);
			}
			break;
		case PARENTHESIZED:
//			if (rhsTree.toString().contains("null != mParser"))
//				System.out.println();
            ParenthesizedTree pTree = (ParenthesizedTree) rhsTree;
            ExpressionTree pExpr = pTree.getExpression();
			Reference pRef = Reference.createReference(pExpr, factory);
            // Recursively 
            generateConstraint(pRef, pExpr);
            
            InferenceMain.getInstance().getConstraintManager().addSubtypeConstraint(pRef, lhsRef);
			break;
		case ASSIGNMENT:
            AssignmentTree aTree = (AssignmentTree) rhsTree; 
            ExpressionTree aExpr = aTree.getVariable();
			Reference aRef = Reference.createReference(aExpr, factory);
            // Recursively
            generateConstraint(aRef, aExpr);
            
            InferenceMain.getInstance().getConstraintManager().addSubtypeConstraint(aRef, lhsRef);
			break;
		case CONDITIONAL_EXPRESSION:
            ConditionalExpressionTree cTree = (ConditionalExpressionTree) rhsTree;
            ExpressionTree cTrueExpr = cTree.getTrueExpression();
			Reference cTrueRef = Reference.createReference(cTrueExpr, factory);
            generateConstraint(cTrueRef, cTrueExpr);
            InferenceMain.getInstance().getConstraintManager().addSubtypeConstraint(cTrueRef, lhsRef);
            
            ExpressionTree cFalseExpr = cTree.getFalseExpression();
			Reference cFalseRef = Reference
					.createReference(cFalseExpr, factory);
            generateConstraint(cFalseRef, cFalseExpr);
            InferenceMain.getInstance().getConstraintManager().addSubtypeConstraint(cFalseRef, lhsRef);
			break;
		case INT_LITERAL:
		case LONG_LITERAL:
		case FLOAT_LITERAL:
		case DOUBLE_LITERAL:
		case BOOLEAN_LITERAL:
		case CHAR_LITERAL:
		case STRING_LITERAL:
		case NULL_LITERAL:
			break;
		default:
			// Check other cases
			if (rhsTree instanceof BinaryTree) {
				BinaryTree bTree = (BinaryTree) rhsTree;
				visitBinary(bTree, null);
			} else if (rhsTree instanceof UnaryTree) {
				UnaryTree uTree = (UnaryTree) rhsTree;
				visitUnary(uTree, null);
			} else
				System.out.println("WARN: Unhandled statment: " + rhsTree
						+ " type: " + rhsTree.getKind());
		}
		visited.add(rhsTree);
	}
	
	/**
	 * An assignment of "tree = ref". Generate constraints based on the 
	 * structure of the <code>lhsTree</code>
	 * @param lhsTree
	 * @param rhsRef
	 */
	protected void generateConstraint(ExpressionTree lhsTree, Reference rhsRef) {
		switch (lhsTree.getKind()) {
		case VARIABLE:
			// generate Reference of element
			Reference varRef = Reference.createReference(lhsTree, factory);
			InferenceMain.getInstance().getConstraintManager().addSubtypeConstraint(rhsRef, varRef);
			break;
		case IDENTIFIER:
			// lhsTree is an identifier: get its use element.
			Element idElt = TreeUtils.elementFromUse((IdentifierTree) lhsTree);
//			AnnotatedTypeMirror idType = factory.getAnnotatedType(lhsTree);
			ExecutableElement currentMethodElt = getCurrentMethodElt();
			// generate Reference of element
			Reference idRef = null;
			if (idElt.getSimpleName().contentEquals("this")) {
				Reference currentMethodRef = Reference.createReference(
						currentMethodElt, factory);
				idRef = ((ExecutableReference) currentMethodRef).getReceiverRef();
			} else
				idRef = Reference.createReference(idElt, factory);
			
			if (idRef == null) {
				// Do nothing
			} else if (!idElt.getSimpleName().contentEquals("this")
//					&& idElt.getKind() == ElementKind.FIELD
					&& isCurrentFieldElt(idElt)
					&& currentMethodElt != null) {
				// Now we need to check if idElt is a field. If so, then we need to 
				// generate adapt constraint
				// TODO Should it include "&& !ElementUtils.isStatic(idElt)"? 
				Reference currentMethodRef = Reference.createReference(
						currentMethodElt, factory);
				Reference thisRef = ((ExecutableReference) currentMethodRef).getReceiverRef();
				handleFieldWrite(thisRef, idRef, rhsRef);
			} else if (rhsRef.getTree() != null && rhsRef.getTree().equals(lhsTree)){
				// They are equivalent
				InferenceMain.getInstance().getConstraintManager().addEqualityConstraint(rhsRef, idRef);
			} else 
				InferenceMain.getInstance().getConstraintManager().addSubtypeConstraint(rhsRef, idRef);
			break;
		case MEMBER_SELECT:
			// Okay, it is an member selection. Need to construct the adapt
			// constraint. 
			MemberSelectTree mTree = (MemberSelectTree) lhsTree;
			// Get the expr
			ExpressionTree rcvExpr = mTree.getExpression();
			// Get the field
			Element fieldElt = TreeUtils.elementFromUse(mTree);
			if (!fieldElt.getSimpleName().contentEquals("super")
					&& isFieldElt(rcvExpr, fieldElt)) {
				Reference fieldRef = Reference.createReference(fieldElt, factory);
				Reference rcvRef = Reference.createReference(rcvExpr, factory);
				// Recursively, this is equal to:
				// exprRef = rcvExpr; exprRef.fieldRef = rhsRef;
				generateConstraint(rcvRef, rcvExpr);
				handleFieldWrite(rcvRef, fieldRef, rhsRef);
			} 
			break;
		case ARRAY_ACCESS:
			// It is an array access expression. Also need the viewpoint adaptation.
			ArrayAccessTree aTree = (ArrayAccessTree) lhsTree;
			ExpressionTree expr = aTree.getExpression();
			Reference exprRef = Reference.createReference(expr, factory);
			// Recursively 
			generateConstraint(exprRef, expr);
			
			// Get the component reference of this array access
			Reference componentRef = ((ArrayReference) exprRef).getComponentRef();
			handleArrayWrite(exprRef, componentRef, rhsRef);
			break;
		default:
			System.out.println("WARN: Unhandled statements: " + lhsTree
					+ " type: " + lhsTree.getKind());
		}
		visited.add(lhsTree);
	}
	
	/**
	 * Handle array read: a = b[i];
	 * @param lhsRef
	 * @param exprRef
	 * @param componentRef
	 */
	protected void handleArrayRead(Reference lhsRef, Reference exprRef, Reference componentRef) {
		InferenceMain.getInstance().getConstraintManager().addSubtypeConstraint( 
					getFieldAdaptReference(exprRef, componentRef, lhsRef), 
					lhsRef);
	}
	
	/**
	 * Handle field read: x = y.f;
	 * @param lhsRef
	 * @param rcvRef
	 * @param fieldRef
	 */
	protected void handleFieldRead(Reference lhsRef, Reference rcvRef, Reference fieldRef) {
		Element fieldElt = fieldRef.getElement();
    	if (!ElementUtils.isStatic(fieldElt)) {
	    	Reference msAdaptRef = getFieldAdaptReference(rcvRef, 
	    			fieldRef, lhsRef);
	    	InferenceMain.getInstance().getConstraintManager().addSubtypeConstraint(msAdaptRef, 
	    			lhsRef);
	    } else
	    	InferenceMain.getInstance().getConstraintManager().addSubtypeConstraint(fieldRef, 
	    			lhsRef);
	}
	
	/**
	 * Handle array write: a[i] = b;
	 * @param exprRef
	 * @param componentRef
	 * @param rhsRef
	 */
	protected void handleArrayWrite(Reference exprRef, Reference componentRef, 
			Reference rhsRef) {
		// Now add the adapt constraint
		InferenceMain.getInstance().getConstraintManager().addSubtypeConstraint(rhsRef, 
					getFieldAdaptReference(exprRef, componentRef, null));
	}
	
	/**
	 * Handle field write: x.f = y;
	 * @param rcvRef
	 * @param fieldRef
	 * @param rhsRef
	 */
	protected void handleFieldWrite(Reference rcvRef, Reference fieldRef, Reference rhsRef) {
		Element fieldElt = fieldRef.getElement();
		if (!ElementUtils.isStatic(fieldElt)) {
			// Get the adapt context's reference
			Reference adaptRef = getFieldAdaptReference(rcvRef, fieldRef, null);
				InferenceMain.getInstance().getConstraintManager().addSubtypeConstraint(rhsRef, adaptRef);
		} else 
			InferenceMain.getInstance().getConstraintManager().addSubtypeConstraint(rhsRef, fieldRef);
	}
	
	/**
	 * Handle method invocation
	 * @param methodElt The method element 
	 * @param arguments The actual arguments 
	 * @param rcvRef The reference of the receiver. It can be null if the method 
	 * is static
	 * @param lhsRef The reference of the return value is assigned to. It can be 
	 * null.
	 */
	protected void handleMethodCall(ExecutableElement methodElt, 
			List<? extends ExpressionTree> arguments, Reference rcvRef, Reference lhsRef) {
		Reference methodRef = Reference.createReference(methodElt, factory);
		if (!ElementUtils.isStatic(methodElt) && rcvRef != null) {
			// Generate constraints for the receiver, 
			// e.g. for x = y.m(z), we have t_y <: _ |> t_this_m
			// we skip this for static methods
			// Get the receiver reference of the call method
			Reference thisRef = ((ExecutableReference) methodRef).getReceiverRef();
			// Construct the adapt reference
			Reference adaptRef = getMethodAdaptReference(rcvRef, 
					thisRef, lhsRef);
			InferenceMain.getInstance().getConstraintManager().addSubtypeConstraint(rcvRef, adaptRef);
		}
			
		// Generate constraints for parameters: t_z <: _ |> t_p
		List<? extends VariableElement> parameters = methodElt.getParameters();
		int size = parameters.size() > arguments.size() ? 
				arguments.size() : parameters.size();
		for (int i = 0; i < size; i++) {
			VariableElement paramElt = parameters.get(i);
			Reference paramRef = Reference.createReference(paramElt, factory);
			ExpressionTree argTree = arguments.get(i);
			Reference argRef = Reference.createReference(argTree, factory);
			// Recursively generate constraints 
			generateConstraint(argRef, argTree);
			
			// rcvRef is null if the method is static 
			Reference adaptRef = getMethodAdaptReference(rcvRef, 
					paramRef, lhsRef);
			InferenceMain.getInstance().getConstraintManager().addSubtypeConstraint(argRef, adaptRef);
		}
		if (lhsRef != null) {
			if (methodElt.getReturnType().getKind() != TypeKind.VOID) { 
				// Generate constraints for returns: |> t_return <: t_lhs
				Reference returnRef = ((ExecutableReference) methodRef).getReturnRef();
				Reference adaptRef = getMethodAdaptReference(rcvRef, 
						returnRef, lhsRef);
				InferenceMain.getInstance().getConstraintManager().addSubtypeConstraint(adaptRef, 
						lhsRef);
			} else {
				// It is a constructor, we connect rcvRef and lhsRef
				InferenceMain.getInstance().getConstraintManager().addSubtypeConstraint(rcvRef, 
						lhsRef);
			}
		}
	}
	
	/**
	 * Generate constraints for method overriding
	 * @param overrider
	 * @param overridden
	 */
	protected void handleMethodOverride(ExecutableElement overrider, 
			ExecutableElement overridden) {
		
		ExecutableReference overriderRef = (ExecutableReference) Reference
				.createReference(overrider, factory);
		ExecutableReference overriddenRef = (ExecutableReference) Reference
				.createReference(overridden, factory);
		
		// Method Receiver: overridden <: overrider  
		Reference overriderRcvRef = overriderRef.getReceiverRef();
		Reference overriddenRcvRef = overriddenRef.getReceiverRef();
		InferenceMain.getInstance().getConstraintManager().addSubtypeConstraint(overriddenRcvRef, 
				overriderRcvRef);
		
		// Parameters: overridden <: overrider  
    	List<? extends VariableElement> overriderParams = overrider.getParameters();
    	List<? extends VariableElement> overriddenParams = overridden.getParameters();
    	int size = overriderParams.size() > overriddenParams.size() ? 
    			overriddenParams.size() : overriderParams.size();
    	for (int i = 0; i < size; i++) {
    		VariableElement overriderParam = overriderParams.get(i);
    		Reference overriderParamRef = Reference.createReference(overriderParam, 
    				factory);
    		VariableElement overriddenParam = overriddenParams.get(i);
    		Reference overriddenParamRef = Reference.createReference(overriddenParam, 
    				factory);
    		InferenceMain.getInstance().getConstraintManager().addSubtypeConstraint(overriddenParamRef, 
    				overriderParamRef);
    	}
    	
    	if (overrider.getReturnType().getKind() == TypeKind.VOID)
    		return;
    	// Returns: overrider <: overridden
    	Reference overriderReturnRef = overriderRef.getReturnRef();
    	Reference overriddenReturnRef = overriddenRef.getReturnRef();
    	InferenceMain.getInstance().getConstraintManager().addSubtypeConstraint(overriderReturnRef, 
    			overriddenReturnRef);
	}
	
	
	protected boolean isThisReference(Reference ref) {
		Element elt = ref.getElement();
		Tree tree = ref.getTree();
		
		// There may be type cast on "this", e.g.
		// ((/*@Mutable*/ tinySQLTableView) this).tsColumnCache
		if (tree != null) {
			if (TreeUtils.isExpressionTree(tree))
				tree = TreeUtils.skipParens((ExpressionTree) tree);
			while (tree.getKind() == Kind.TYPE_CAST) {
				tree = ((TypeCastTree) tree).getExpression();
				if (TreeUtils.isExpressionTree(tree))
					tree = TreeUtils.skipParens((ExpressionTree) tree);
			}
		}
				
		if (elt != null
				&& !(ref instanceof ExecutableReference) 
				&& ref.getReadableName().startsWith("THIS_") 
				|| tree.toString().equals("this"))
			return true;
		else
			return false;
	}
	
	 protected boolean isCurrentFieldElt(Element fieldElt) {
		if (fieldElt.getKind() != ElementKind.FIELD)
			return false;
		ClassTree enclosingOfClass = TreeUtils.enclosingOfClass(
				getCurrentPath(), ClassTree.class);
		AnnotatedDeclaredType classType = (AnnotatedDeclaredType) factory
				.getAnnotatedType(enclosingOfClass);
		TypeElement typeElt = (TypeElement)classType.getUnderlyingType().asElement();
		return isFieldElt(typeElt, fieldElt);
	}
	
	protected boolean isFieldElt(TypeElement typeElt, Element fieldElt) {
		if (fieldElt.getKind() != ElementKind.FIELD)
			return false;
		if (ElementUtils.isStatic(fieldElt) 
				&& fieldElt.getSimpleName().contentEquals("class"))
			return true;
		VariableElement findFieldInType = ElementUtils.findFieldInType(typeElt, fieldElt.toString());
		TypeMirror superclass = typeElt.getSuperclass();
		while (findFieldInType == null && superclass.getKind() != TypeKind.NONE) {
			typeElt = (TypeElement) ((ClassType) superclass).asElement();
			superclass = typeElt.getSuperclass();
			findFieldInType = ElementUtils.findFieldInType(typeElt, fieldElt.toString());
		}
		return findFieldInType != null;
	}
	
	protected boolean isFieldElt(ExpressionTree expr, Element fieldElt) {
		if (fieldElt.getKind() != ElementKind.FIELD)
			return false;
		AnnotatedTypeMirror exprType = factory.getAnnotatedType(expr);
		if (exprType.getKind() == TypeKind.DECLARED) {
			TypeElement t = (TypeElement) ((AnnotatedDeclaredType) exprType)
					.getUnderlyingType().asElement();
			return isFieldElt(t, fieldElt);
		}
		return false;
	}
	
    /**
     * Check if this mTree is accessing expr like "Body.this"
     * @param mTree
     * @return
     */
    protected boolean isAccessOuterThis(MemberSelectTree mTree) {
		if (!(mTree.getExpression() instanceof PrimitiveTypeTree)) {
			if (mTree.getIdentifier().contentEquals("this")) {
				return true;
			}
		}
		return false;
    }
    
    
    protected Element getOuterThisElement(MemberSelectTree mTree) {
    	if (!isAccessOuterThis(mTree))
    		return null;
        ExecutableElement currentMethodElt = getCurrentMethodElt();
        if (currentMethodElt == null) {
        	return null;
        }
    	Element enclosingElt = currentMethodElt.getEnclosingElement();
        TypeElement element = (TypeElement)InternalUtils.symbol(mTree.getExpression());
        while (enclosingElt != null) {
            if (enclosingElt instanceof ExecutableElement) {
                ExecutableElement method = (ExecutableElement) enclosingElt;
                if (method.asType() != null
                        && factory.isSubtype(
                    		(TypeElement) method.getEnclosingElement(), element))
                    if (ElementUtils.isStatic(method)) {
                        enclosingElt = null;
                        break;
                    }
                    else
                        break;
            }
            else if (enclosingElt instanceof TypeElement) {
                if (factory.isSubtype((TypeElement) enclosingElt, element))
                    break;
            }
            enclosingElt = enclosingElt.getEnclosingElement();
        }
        return enclosingElt;
    }
    
	
	
	protected Reference getFieldAdaptReference(Reference rcvRef, 
			Reference fieldRef, Reference assignToRef) {
		switch (getFieldAdaptContext(rcvRef, fieldRef, assignToRef)) {
		case NONE:
			return fieldRef;
		case RECEIVER:
			return Reference.createFieldAdaptReference(rcvRef, fieldRef);
		case ASSIGNTO:
			// If assignTo is primitive, then return directly
			if (assignToRef instanceof PrimitiveReference)
				return fieldRef;
			else
				return Reference.createFieldAdaptReference(assignToRef, fieldRef);
		}
		System.out.println("ERROR: No adapt context is found!");
		return null;
	}
	
	protected Reference getMethodAdaptReference(Reference rcvRef, 
			Reference declRef, Reference assignToRef) {
		switch (getMethodAdaptContext(rcvRef, declRef, assignToRef)) {
		case NONE:
			return declRef;
		case RECEIVER:
			return Reference.createMethodAdaptReference(rcvRef, declRef);
		case ASSIGNTO:
			// If assignTo is primitive, then return directly
			if (assignToRef instanceof PrimitiveReference)
				return declRef;
			else
				return Reference.createMethodAdaptReference(assignToRef, declRef);
		}
		System.out.println("ERROR: No adapt context is found!");
		return null;
	}
	
	/**
	 * Get the adapt context for field access
	 * @param rcvRef The reference of the receiver. It shouldn't be {@code null}
	 * @param fieldRef The reference of the field
	 * @param assignToRef The reference of this field access is assigned to. 
	 * If it is {@code null}, then it is a field write, e.g. x.f = y; 
	 * @return
	 */
	public abstract AdaptContext getFieldAdaptContext(Reference rcvRef, 
			Reference fieldRef, Reference assignToRef); 
	
	/**
	 * Get the adapt context for method call. 
	 * @param rcvRef The reference of the receiver. If it is {@code null}, then
	 * it is a static method. 
	 * @param declRef The reference of the parameter or the return
	 * @param assignToRef The reference of the return value is assigned to. 
	 * If the return value is void, it is {@code null}. 
	 * @return
	 */
	public abstract AdaptContext getMethodAdaptContext(Reference rcvRef, 
			Reference declRef, Reference assignToRef); 
	
	
	@Override
	protected InferenceTypeValidator createTypeValidator() {
        return new InferenceTypeValidator();
    }

	/**
	 * The following class is copied from {@link BaseTypeVisitor} with one line
	 * commented
	 * @author huangw5
	 *
	 */
    protected class InferenceTypeValidator extends BaseTypeVisitor<InferenceChecker>.TypeValidator {
        protected void reportError(AnnotatedTypeMirror type, Tree p) {
            checker.report(Result.failure("type.invalid",
                        type.getAnnotations(), type.toString()), p);
        }

        @Override
        public Void visitDeclared(AnnotatedDeclaredType type, Tree tree) {
            if (checker.shouldSkipUses(type.getElement()))
                return super.visitDeclared(type, tree);

            // Ensure that type use is a subtype of the element type
            AnnotatedDeclaredType useType = type.getErased();
            AnnotatedDeclaredType elemType = (AnnotatedDeclaredType)
                factory.getAnnotatedType(
                        useType.getUnderlyingType().asElement()).getErased();

            if (!isValidUse(elemType, useType)) {
                reportError(useType, tree);
            }

            /* Try to reconstruct the ParameterizedTypeTree from the given tree.
             * TODO: there has to be a nicer way to do this...
             */
            Pair<ParameterizedTypeTree, AnnotatedDeclaredType> p = extractParameterizedTypeTree(tree, type);
            ParameterizedTypeTree typeargtree = p.first;
            type = p.second;

            if (typeargtree!=null) {
                // We have a ParameterizedTypeTree -> visit it.

                visitParameterizedType(type, typeargtree);

                /* Instead of calling super with the unchanged "tree", adapt the second
                 * argument to be the corresponding type argument tree.
                 * This ensures that the first and second parameter to this method always correspond.
                 * visitDeclared is the only method that had this problem.
                 */
                List<? extends AnnotatedTypeMirror> tatypes = type.getTypeArguments();

                if (tatypes == null)
                    return null;

                assert tatypes.size() == typeargtree.getTypeArguments().size();

                for (int i=0; i < tatypes.size(); ++i) {
                    scan(tatypes.get(i), typeargtree.getTypeArguments().get(i));
                }

                return null;

                // Don't call the super version, because it creates a mismatch between
                // the first and second parameters.
                // return super.visitDeclared(type, tree);
            }

            return super.visitDeclared(type, tree);
        }

        private Pair<ParameterizedTypeTree, AnnotatedDeclaredType>
        extractParameterizedTypeTree(Tree tree, AnnotatedDeclaredType type) {
            ParameterizedTypeTree typeargtree = null;

            switch (tree.getKind()) {
            case VARIABLE:
                Tree lt = ((VariableTree)tree).getType();
                if (lt instanceof ParameterizedTypeTree) {
                    typeargtree = (ParameterizedTypeTree) lt;
                } else {
                  //   System.out.println("Found a: " + lt);
                }
                break;
            case PARAMETERIZED_TYPE:
                typeargtree = (ParameterizedTypeTree) tree;
                break;
            case NEW_CLASS:
                NewClassTree nct = (NewClassTree) tree;
                ExpressionTree nctid = nct.getIdentifier();
                if (nctid.getKind()==Tree.Kind.PARAMETERIZED_TYPE) {
                    typeargtree = (ParameterizedTypeTree) nctid;
                    /*
                     * This is quite tricky... for anonymous class instantiations,
                     * the type at this point has no type arguments.
                     * By doing the following, we get the type arguments again.
                     */
                    // FIXME: The following line is commented by huangw5
//                    type = (AnnotatedDeclaredType) factory.getAnnotatedType(typeargtree);
                }
                break;
            case ANNOTATED_TYPE:
                AnnotatedTypeTree tr = (AnnotatedTypeTree) tree;
                ExpressionTree undtr = tr.getUnderlyingType();
                if (undtr instanceof ParameterizedTypeTree) {
                    typeargtree = (ParameterizedTypeTree) undtr;
                } else if (undtr instanceof IdentifierTree) {
                    // @Something D -> Nothing to do
                } else {
                    // TODO: add more test cases to ensure that nested types are handled correctly,
                    // e.g. @Nullable() List<@Nullable Object>[][]
                    Pair<ParameterizedTypeTree, AnnotatedDeclaredType> p = extractParameterizedTypeTree(undtr, type);
                    typeargtree = p.first;
                    type = p.second;
                }
                break;
            case IDENTIFIER:
            case ARRAY_TYPE:
            case NEW_ARRAY:
            case MEMBER_SELECT:
            case UNBOUNDED_WILDCARD:
            case EXTENDS_WILDCARD:
            case SUPER_WILDCARD:
                // Nothing to do.
                // System.out.println("Found a: " + (tree instanceof ParameterizedTypeTree));
                break;
            default:
                System.err.printf("TypeValidator.visitDeclared unhandled tree: %s of kind %s\n", tree, tree.getKind());
            }

            return Pair.of(typeargtree, type);
        }


        /**
         * Checks that the annotations on the type arguments supplied to a type or a
         * method invocation are within the bounds of the type variables as
         * declared, and issues the "generic.argument.invalid" error if they are
         * not.
         *
         * This method used to be visitParameterizedType, which incorrectly handles the main
         * annotation on generic types.
         */
        protected Void visitParameterizedType(AnnotatedDeclaredType type, ParameterizedTypeTree tree) {
            // System.out.printf("TypeValidator.visitParameterizedType: type: %s, tree: %s\n", type, tree);

            if (TreeUtils.isDiamondTree(tree))
                return null;

            final TypeElement element = (TypeElement) type.getUnderlyingType().asElement();
            if (checker.shouldSkipUses(element))
                return null;

            List<AnnotatedTypeVariable> typevars = factory.typeVariablesFromUse(type, element);

            checkTypeArguments(tree, typevars, type.getTypeArguments(), tree.getTypeArguments());

            return null;
        }
    }
	
}

