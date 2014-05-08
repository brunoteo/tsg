/**
 * 
 */
package checkers.inference.aj;

import com.sun.source.tree.CompilationUnitTree;

import checkers.inference.InferenceChecker;
import checkers.inference.InferenceVisitor;
import checkers.inference.Reference;

/**
 * @author huangw5
 *
 */
public class AJInferenceVisitor extends InferenceVisitor {
	
	private AJChecker checker;

	public AJInferenceVisitor(AJChecker checker, CompilationUnitTree root) {
		super(checker, root);
		this.checker = checker;
	}

	/* (non-Javadoc)
	 * @see checkers.inference.InferenceVisitor#getFieldAdaptContext(checkers.inference.Reference, checkers.inference.Reference, checkers.inference.Reference)
	 */
	@Override
	public AdaptContext getFieldAdaptContext(Reference rcvRef,
			Reference fieldRef, Reference assignToRef) {
		return AdaptContext.RECEIVER;
	}

	/* (non-Javadoc)
	 * @see checkers.inference.InferenceVisitor#getMethodAdaptContext(checkers.inference.Reference, checkers.inference.Reference, checkers.inference.Reference)
	 */
	@Override
	public AdaptContext getMethodAdaptContext(Reference rcvRef,
			Reference declRef, Reference assignToRef) {
		return AdaptContext.RECEIVER;
	}

}
