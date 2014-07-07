package tsg.ast;

import java.util.ArrayList;
import java.util.List;

import japa.parser.ast.stmt.ExpressionStmt;
import japa.parser.ast.stmt.Statement;
import japa.parser.ast.stmt.TryStmt;
import japa.parser.ast.visitor.VoidVisitorAdapter;


public class StatementVisitor extends VoidVisitorAdapter<Void> {
	
	private List<Statement> stms;
	private boolean tryFound = false;
	private boolean methodFound = false;
	
	public StatementVisitor() {
		stms = new ArrayList<Statement>();
	}

	@Override
	public void visit(final TryStmt n, final Void arg) {
		tryFound = true;
		super.visit(n, arg);
	}
	
	public void visit(final ExpressionStmt n, final Void arg) {
		if(!tryFound && !methodFound) {
			if(n.toString().contains("peek")) methodFound = true;
			stms.add(n);
		}
		super.visit(n, arg);
	}
	
	public List<Statement> getStatements() {
		return stms;
	}
	
	public boolean getMethodFound() {
		return methodFound;
	}
	
}
