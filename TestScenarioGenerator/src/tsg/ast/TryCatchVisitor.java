package tsg.ast;

import japa.parser.ast.stmt.TryStmt;
import japa.parser.ast.visitor.VoidVisitorAdapter;


public class TryCatchVisitor extends VoidVisitorAdapter<Void> {
	
	private boolean found;
	
	public TryCatchVisitor() {
		this.found = false;
	}
	
	@Override
	public void visit(final TryStmt n, final Void arg) {
		this.found = true;
		super.visit(n, arg);
	}
	
	public boolean isFound() {
		return found;
	}
	
}
