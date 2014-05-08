/**
 * 
 */
package checkers.inference;

/**
 * There are three types of constraints currently
 * @author huangw5
 *
 */
public abstract class Constraint {
	
	Reference left; 
	Reference right;
	Reference ref;
	
	int id; 
	private static int counter = 0;
	
	public Constraint() {
		id = counter++;
	}

	public Reference getLeft() {
		return left;
	}

	public Reference getRight() {
		return right;
	}

	public Reference getRef() {
		return ref;
	}

	public static class SubtypeConstraint extends Constraint {
		public SubtypeConstraint(Reference sub, Reference sup) {
			super();
			this.left = sub;
			this.right = sup;
		}
		
		@Override
		public String toString() {
			return "SUB-" + id + ": " + left.toString() + "  <:  " + right.toString();
		}
	}
	
	public static class EqualityConstraint extends Constraint {
		public EqualityConstraint(Reference left, Reference right) {
			super();
			this.left = left;
			this.right = right;
		}
		
		@Override
		public String toString() {
			return "EQU-" + id + ": " + left.toString() + "  ==  " + right.toString();
		}
		
	}
	
	public static class InequalityConstraint extends Constraint {
		public InequalityConstraint(Reference left, Reference right) {
			super();
			this.left = left;
			this.right = right;
		}
		
		@Override
		public String toString() {
			return "INE-" + id + ": " + left.toString() + "  !=  " + right.toString();
		}
	}
	
	/**
	 * It doesn't enforce any constraints. It is used for variables that are free
	 * of constraints, e.g. a variable defined but never used. 
	 * @author huangw5
	 *
	 */
	public static class EmptyConstraint extends Constraint {
		public EmptyConstraint(Reference ref) {
			super();
			this.ref = ref;
		}
		
		@Override
		public String toString() {
			return "EMP-" + id + ": " + ref.toString();
		}
	}
}

