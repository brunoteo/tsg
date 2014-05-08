/**
 * 
 */
package checkers.inference;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.TypeKind;

import checkers.inference.Constraint.EqualityConstraint;
import checkers.inference.Constraint.InequalityConstraint;
import checkers.inference.Constraint.SubtypeConstraint;
import checkers.inference.InferenceChecker.FailureStatus;
import checkers.inference.Reference.AdaptReference;
import checkers.inference.Reference.ArrayReference;
import checkers.inference.Reference.DeclaredReference;
import checkers.inference.Reference.ExecutableReference;
import checkers.inference.Reference.FieldAdaptReference;
import checkers.types.AnnotatedTypeMirror;
import checkers.types.AnnotatedTypeMirror.AnnotatedExecutableType;
import checkers.util.AnnotationUtils;


/**
 * @author huangw5
 *
 */
public class SetbasedSolver implements ConstraintSolver {
	
	class SetbasedSolverException extends Exception {

		public SetbasedSolverException(String string) {
			super(string);
		}
		
	}
	
	private InferenceChecker inferenceChecker;
	
	private List<Reference> exprRefs;
	
	private Map<String, Reference> maximalSolution = null;
	
	public SetbasedSolver(InferenceChecker inferenceChcker, 
			List<Reference> exprRefs) {
		this.inferenceChecker = inferenceChcker;
		this.exprRefs = exprRefs;
		initialize();
	}
	
	protected void initialize() {
		for (Reference ref : exprRefs) {
			if (ref.getAnnotations().isEmpty()) {
				ref.setAnnotations(
						inferenceChecker.getSourceLevelQualifiers());
			}
		}
	}
	
	/**
	 * Return true if there are updates
	 * @param c
	 * @return
	 * @throws SetbasedSolverException
	 */
	protected boolean handleSubtypeConstraint(SubtypeConstraint c) throws SetbasedSolverException {
		// Get the references
		Reference subRef = c.getLeft();
		Reference supRef = c.getRight();
		
		boolean hasUpdate = false;		
		// Get the annotations
		Set<AnnotationMirror> subAnnos = getAnnotations(subRef);
		Set<AnnotationMirror> supAnnos = getAnnotations(supRef);
		
//		if (c.id == 1601 && subAnnos.size() < 2)
//			System.out.println();
		
		// First update the left: If a left annotation is not 
		// subtype of any right annotation, then remove it. 
		for (Iterator<AnnotationMirror> it = subAnnos.iterator(); 
				it.hasNext();) {
			AnnotationMirror subAnno = it.next();
			boolean isFeasible = false;
			for (AnnotationMirror supAnno : supAnnos) {
				if (inferenceChecker.getQualifierHierarchy().isSubtype(
						subAnno, supAnno)) {
					isFeasible = true;
					break;
				}
			}
			if (!isFeasible)
				it.remove();
		}
		
		// Now update the right: If a right annotation is not super type 
		// of any left annotation, remove it
		// We only do this if it is strict subtyping
		if (inferenceChecker.isStrictSubtyping()) {
			for (Iterator<AnnotationMirror> it = supAnnos.iterator(); 
					it.hasNext();) {
				AnnotationMirror supAnno = it.next();
				boolean isFeasible = false;
				for (AnnotationMirror subAnno : subAnnos) {
					if (inferenceChecker.getQualifierHierarchy().isSubtype(
							subAnno, supAnno)) {
						isFeasible = true;
						break;
					}
				}
				if (!isFeasible)
					it.remove();
			}
		}
		
		if (subAnnos.isEmpty() || supAnnos.isEmpty())
			throw new SetbasedSolverException("ERROR: solve " + c 
					+ " failed becaue of an empty set.");
		
		hasUpdate = setAnnotations(subRef, subAnnos)
				|| setAnnotations(supRef, supAnnos) || hasUpdate;
		
		return hasUpdate;
	}
	
	protected boolean handleEqualityConstraint(EqualityConstraint c) throws SetbasedSolverException {
		// Get the references
		Reference left = c.getLeft();
		Reference right = c.getRight();
		
		boolean hasUpdate = false;
		
		// Get the annotations
		Set<AnnotationMirror> leftAnnos = getAnnotations(left);
		Set<AnnotationMirror> rightAnnos = getAnnotations(right);
	
		// The default intersection of Set doesn't work well
		Set<AnnotationMirror> interAnnos = InferenceUtils.intersectAnnotations(
				leftAnnos, rightAnnos);
		
		if (interAnnos.isEmpty()) {
			throw new SetbasedSolverException("ERROR: solve " + c 
					+ " failed becaue of an empty set.");
		}
		// update both
		return setAnnotations(left, interAnnos)
				|| setAnnotations(right, interAnnos)
				|| hasUpdate;
	}
	
	protected boolean handleInequalityConstraint(InequalityConstraint c) throws SetbasedSolverException {
		// Get the references
		Reference left = c.getLeft();
		Reference right = c.getRight();
		
		// Get the annotations
		Set<AnnotationMirror> leftAnnos = getAnnotations(left);
		Set<AnnotationMirror> rightAnnos = getAnnotations(right);
	
		// The default intersection of Set doesn't work well
		Set<AnnotationMirror> differAnnos = InferenceUtils.differAnnotations(
				leftAnnos, rightAnnos);
		
		if (differAnnos.isEmpty()) {
			throw new SetbasedSolverException("ERROR: solve " + c 
					+ " failed becaue of an empty set.");
		}
		// Update the left
		return setAnnotations(left, differAnnos);
	}
	
	protected Set<AnnotationMirror> getAnnotations(Reference ref) {
		if (ref instanceof AdaptReference) {
			AdaptReference aRef = (AdaptReference) ref;
			Reference contextRef = aRef.getContextRef();
			Reference declRef = aRef.getDeclRef();
			
			if (aRef instanceof FieldAdaptReference)
				return inferenceChecker.adaptFieldSet(contextRef.getAnnotations(), 
						declRef.getAnnotations());
			else
				return inferenceChecker.adaptMethodSet(contextRef.getAnnotations(), 
						declRef.getAnnotations());
		} else
			return ref.getAnnotations();
	}
	
	/**
	 * Return true if there are updates
	 * @param ref
	 * @param annos
	 * @return
	 * @throws SetbasedSolverException
	 */
	protected boolean setAnnotations(Reference ref, Set<AnnotationMirror> annos)
			throws SetbasedSolverException {
		if (ref instanceof AdaptReference)
			return setAnnotations((AdaptReference) ref, annos);
		if (ref.getAnnotations().equals(annos))
			return false;
//		if (ref.id == 2926 && annos.size() == 1)
//			System.out.println();
		ref.setAnnotations(annos);
		return true;
	}
	
	protected boolean setAnnotations(AdaptReference ref, Set<AnnotationMirror> annos) 
			throws SetbasedSolverException {
		AdaptReference aRef = (AdaptReference) ref;
		Reference contextRef = aRef.getContextRef();
		Reference declRef = aRef.getDeclRef();
		
		Set<AnnotationMirror> contextAnnos = contextRef.getAnnotations();
		Set<AnnotationMirror> declAnnos = declRef.getAnnotations();
		
		// First iterate through contextAnnos and remove infeasible annotations
		for (Iterator<AnnotationMirror> it = contextAnnos.iterator(); it.hasNext();) {
			AnnotationMirror contextAnno = it.next();
			boolean isFeasible = false;
			for (AnnotationMirror declAnno : declAnnos) {
				AnnotationMirror outAnno = null;
				if (aRef instanceof FieldAdaptReference)
					outAnno = inferenceChecker.adaptField(contextAnno, declAnno);
				else
					outAnno = inferenceChecker.adaptMethod(contextAnno, declAnno);
				if (outAnno != null && annos.contains(outAnno)) {
					isFeasible = true;
					break;
				}
			}
			if (!isFeasible)
				it.remove();
		}
		
		if (contextAnnos.isEmpty())
			throw new SetbasedSolverException("ERROR: Empty set for contextRef in AdaptConstraint");
		
		// Now iterate through declAnnos and remove infeasible annotations
		for (Iterator<AnnotationMirror> it = declAnnos.iterator(); it.hasNext();) {
			AnnotationMirror declAnno = it.next();
			boolean isFeasible = false;
			for (AnnotationMirror contextAnno : contextAnnos) {
				AnnotationMirror outAnno = null;
				if (aRef instanceof FieldAdaptReference)
					outAnno = inferenceChecker.adaptField(contextAnno, declAnno);
				else
					outAnno = inferenceChecker.adaptMethod(contextAnno, declAnno);
				if (outAnno != null && annos.contains(outAnno)) {
					isFeasible = true;
					break;
				}
			}
			if (!isFeasible)
				it.remove();
		}
		
		if (declAnnos.isEmpty())
			throw new SetbasedSolverException("ERROR: Empty set for declRef in AdaptConstraint");
		
		return setAnnotations(contextRef, contextAnnos)
				|| setAnnotations(declRef, declAnnos);
	}
	
	/**
	 * Build the maximal solution
	 */
	protected void buildMaximalSolution() {
		if (maximalSolution != null)
			return;
		maximalSolution = new HashMap<String, Reference>();
		try{
		PrintWriter pw = new PrintWriter("maximalsolution.csv");
		for (Reference ref : exprRefs) {
			String identifier = ref.getIdentifier();
			if (identifier != null) {
				Reference maxRef = getMaximal(ref);
				maximalSolution.put(identifier, maxRef);
				pw.println(identifier + ": " + maxRef.toString());
			}
		}
		pw.close();
		} catch (Exception e) {}
	}
	
	/**
	 * Get a copy of {@code ref} with only the maximal annotation left
	 * @param ref
	 * @return
	 */
	protected Reference getMaximal(Reference ref) {
		Reference copy = ref;
		AnnotationMirror[] annos = copy.getAnnotations().toArray(
				new AnnotationMirror[0]);
		if (annos.length == 0)
			return copy;
		// sort
		Arrays.sort(annos, inferenceChecker.getComparator());
		// get the maximal annotation
		Set<AnnotationMirror> maxAnnos = AnnotationUtils.createAnnotationSet();
		maxAnnos.add(annos[0]);
		copy.setAnnotations(maxAnnos);
		
		// Check the type of ref
		if (copy instanceof DeclaredReference) {
			List<? extends Reference> typeArgs = 
					((DeclaredReference) copy).getTypeArguments();
			if (typeArgs != null) {
				List<Reference> ts = new ArrayList<Reference>(typeArgs.size());
				for (Reference typeArgRef : typeArgs)
					ts.add(getMaximal(typeArgRef));
				((DeclaredReference) copy).setTypeArguments(ts);
			}
		} else if (copy instanceof ArrayReference) {
			Reference componentRef = ((ArrayReference) copy).getComponentRef();
			((ArrayReference) copy).setComponentRef(getMaximal(componentRef));
		} else if (copy instanceof ExecutableReference) {
			ExecutableReference executableRef = (ExecutableReference) copy;
			executableRef.setReceiverRef(getMaximal(executableRef.getReceiverRef()));
			executableRef.setReturnRef(getMaximal(executableRef.getReturnRef()));
		} 
		return copy;
	}
	
	@Override
	public void annotateInferredType(Element elt, AnnotatedTypeMirror type) {
		if (elt.getKind() == ElementKind.METHOD 
				|| elt.getKind() == ElementKind.CONSTRUCTOR) {
			if (type.getKind() != TypeKind.EXECUTABLE)
				throw new RuntimeException("Incompatible method type!");
			ExecutableElement methodElt = (ExecutableElement) elt;
			AnnotatedExecutableType methodType = (AnnotatedExecutableType) type;
			assert methodElt.getParameters().size() == methodType.getParameterTypes().size();
			// Parameters
			for (Iterator<? extends VariableElement> itParamElt = methodElt
					.getParameters().iterator(); itParamElt.hasNext();) {
				for (Iterator<AnnotatedTypeMirror> itParamType = methodType
						.getParameterTypes().iterator(); itParamType.hasNext();) {
					annotateInferredType(itParamElt.next(), itParamType.next());
				}
			}
			String methodSig = InferenceUtils.getElementSignature(elt);
			ExecutableReference methodRef = (ExecutableReference) maximalSolution.get(methodSig);
			if (methodRef != null) {
				// Return
				InferenceUtils.annotateReferenceType(methodType.getReturnType(), 
						methodRef.getReturnRef());
				// Receiver
				InferenceUtils.annotateReferenceType(methodType.getReceiverType(), 
						methodRef.getReceiverRef());
			}
		} else
			annotateInferredType(InferenceUtils.getElementSignature(elt), type);
	}

	@Override
	public void annotateInferredType(String identifier, AnnotatedTypeMirror type) {
		if (maximalSolution == null)
			throw new RuntimeException("Should have called buildMaximalSolution()"
					+ " before retrieving the inferrred type!");
		Reference ref = maximalSolution.get(identifier);
		if (ref != null) {
			InferenceUtils.annotateReferenceType(type, ref);
		}
	}
	
	@Override
	public Reference getInferredReference(String identifier) {
		if (maximalSolution == null)
			throw new RuntimeException("Should have called buildMaximalSolution()"
					+ " before retrieving the inferrred type!");
		Reference ref = maximalSolution.get(identifier);
		return ref;
	}

	@Override
	public List<Reference> getInferredReferences() {
		if (maximalSolution == null)
			throw new RuntimeException("Should have called buildMaximalSolution()"
					+ " before retrieving the inferrred type!");
		return new ArrayList<Reference>(maximalSolution.values());
	}

	
	@Override
	public boolean solve(List<Constraint> constraints) {
		// FIXME: output constraints
		try {
			PrintWriter pw = new PrintWriter("new-constraints.csv");
			for (Constraint c : constraints) {
				pw.println(c);
			}
			pw.close();
		} catch (Exception e) {}
		
		Set<Constraint> warnConstraints = new HashSet<Constraint>();
		boolean hasUpdate = false;
		boolean hasError = false;
		do {
			hasUpdate = false;
			for (Constraint c : constraints) {
				try {
					if (c instanceof SubtypeConstraint) {
						hasUpdate = handleSubtypeConstraint((SubtypeConstraint) c) 
								|| hasUpdate;
					} else if (c instanceof EqualityConstraint) {
						hasUpdate = handleEqualityConstraint((EqualityConstraint) c) 
								|| hasUpdate;
					} else if (c instanceof InequalityConstraint) {
						hasUpdate = handleInequalityConstraint((InequalityConstraint) c) 
								|| hasUpdate;
					}
				} catch (SetbasedSolverException e) {
					FailureStatus fs = inferenceChecker.getFailureStatus(c);
					if (fs == FailureStatus.ERROR) {
						System.err.println("ERROR: while handling constraint " + c);
						System.err.println("left: " + c.getLeft().getIdentifier() + " " + c.getLeft().fileName);
						System.err.println("right: " + c.getRight().getIdentifier() + " " + c.getRight().fileName);
						e.printStackTrace();
						hasUpdate = false;
						hasError = true;
						break;
					} else if (fs == FailureStatus.WARN) {
						if (!warnConstraints.contains(c)) {
							System.err.println("WARN: handling constraint " + c + " failed.");
							warnConstraints.add(c);
						}
					}
				}
			}
		} while (hasUpdate && !hasError);
		
		if (hasError) {
//			try{
//			PrintWriter pw = new PrintWriter("maximalsolution.csv");
//			for (Reference ref : exprRefs) {
//				String identifier = ref.getIdentifier();
//				if (identifier != null) {
//					pw.println(identifier + ": " + ref.toString() + ":" + ref.getType());
//				}
//			}
//			pw.close();
//			} catch (Exception e) {}
			System.exit(-1);
		}
		
		buildMaximalSolution();
		return true;
	}
	
	
	@Override
	public void printAllVariables(PrintWriter out) {
		inferenceChecker.printResult(maximalSolution, out);
	}

}
