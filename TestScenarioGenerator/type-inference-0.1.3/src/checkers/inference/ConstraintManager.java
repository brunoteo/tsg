/**
 * 
 */
package checkers.inference;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.lang.model.element.TypeElement;

import checkers.inference.Constraint.EqualityConstraint;
import checkers.inference.Constraint.SubtypeConstraint;
import checkers.inference.Reference.AdaptReference;
import checkers.inference.Reference.ArrayReference;
import checkers.inference.Reference.FieldAdaptReference;
import checkers.inference.Reference.NullReference;
import checkers.inference.Reference.PrimitiveReference;

/**
 * A singleton class for adding constraints
 * @author huangw5
 *
 */
public class ConstraintManager {
	
	private List<Constraint> constraints;
	
	private Set<TypeElement> visitedClasses = new HashSet<TypeElement>();
	
//	private static ConstraintManager manager; 
	/**
	 * It is not supposed to be used by multiple threads.
	 * Thus the synchronization is not necessary. 
	 * @return
	 */
//	public static ConstraintManager getInstance() {
//		if (manager == null)
//			manager = new ConstraintManager();
//		return manager;
//	}
	
	public ConstraintManager() {
		constraints = new LinkedList<Constraint>();
	}
	
	public void addSubtypeConstraint(Reference sub, Reference sup) {
		if (sub.equals(sup))
			return;
		if (sub instanceof PrimitiveReference || sup instanceof PrimitiveReference)
			return;
		if (sub instanceof NullReference || sup instanceof NullReference)
			return;
		
		// TODO: Handling the case that one of sub or sup is array. 
		// e.g. (x =f=> a)  <: b, where a and b are arrays 
		// then we add another constraint:  a.getComponenetRef == b.getComponentRef
		if (sub instanceof AdaptReference && sup instanceof ArrayReference) {
			// In case that the decl reference is an array
			Reference declRef = ((AdaptReference) sub).getDeclRef();
			if (declRef instanceof ArrayReference) {
				if (sub instanceof FieldAdaptReference)
					addEqualityConstraint(
							((ArrayReference) declRef).getComponentRef(),
							((ArrayReference) sup).getComponentRef());
//					constraints.add(new Constraint.EqualityConstraint(
//							((ArrayReference) declRef).getComponentRef(), 
//							((ArrayReference) sup).getComponentRef()));
				else
					addSubtypeConstraint(
							((ArrayReference) declRef).getComponentRef(),
							((ArrayReference) sup).getComponentRef());
//					constraints.add(new Constraint.SubtypeConstraint(
//							((ArrayReference) declRef).getComponentRef(), 
//							((ArrayReference) sup).getComponentRef()));
			}
		} else if (sub instanceof ArrayReference && sup instanceof AdaptReference) {
			Reference declRef = ((AdaptReference) sup).getDeclRef();
			if (declRef instanceof ArrayReference) {
				if (sup instanceof FieldAdaptReference)
					addEqualityConstraint(
							((ArrayReference) sub).getComponentRef(),
							((ArrayReference) declRef).getComponentRef());
//					constraints.add(new Constraint.EqualityConstraint(
//							((ArrayReference) sub).getComponentRef(),
//							((ArrayReference) declRef).getComponentRef()));
				else 
					addSubtypeConstraint(
							((ArrayReference) sub).getComponentRef(),
							((ArrayReference) declRef).getComponentRef());
//					constraints.add(new Constraint.SubtypeConstraint(
//							((ArrayReference) sub).getComponentRef(),
//							((ArrayReference) declRef).getComponentRef()));
			}
		} else if (sub instanceof ArrayReference && sup instanceof ArrayReference) {
			// Add component constraints
//			constraints.add(new SubtypeConstraint(((ArrayReference) sub).getComponentRef(),
//					((ArrayReference) sup).getComponentRef()));
			addSubtypeConstraint(((ArrayReference) sub).getComponentRef(),
					((ArrayReference) sup).getComponentRef());
		}
		constraints.add(new Constraint.SubtypeConstraint(sub, sup));
	}
	
	public void addEqualityConstraint(Reference left, Reference right) {
		if (left.equals(right))
			return;
		if (left instanceof PrimitiveReference || right instanceof PrimitiveReference)
			return;
		
		if (left instanceof ArrayReference && right instanceof ArrayReference) {
			addEqualityConstraint(((ArrayReference) left).getComponentRef(),
					((ArrayReference) right).getComponentRef());
		}
		constraints.add(new Constraint.EqualityConstraint(left, right));
	}
	
	public void addInequalityConstraint(Reference left, Reference right) {
		if (left.equals(right))
			return;
		if (left instanceof PrimitiveReference || right instanceof PrimitiveReference)
			return;
		constraints.add(new Constraint.InequalityConstraint(left, right));
	}
	
	/**
	 * For variables that are not involved in any constraints.
	 * @param ref
	 */
	public void addEmptyConstraint(Reference ref) {
		if (ref instanceof PrimitiveReference)
			return;
		constraints.add(new Constraint.EmptyConstraint(ref));
	}
	
	public List<Constraint> getConstraints() {
		return constraints;
	}
	
	public void addVisitedClass(TypeElement clazz) {
		visitedClasses.add(clazz);
	}

	public TypeElement[] getVisitedClasses() {
		return visitedClasses.toArray(new TypeElement[0]);
	}

}
