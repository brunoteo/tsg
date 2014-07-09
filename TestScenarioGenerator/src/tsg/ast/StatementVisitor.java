package tsg.ast;

import java.util.ArrayList;
import java.util.List;

import tsg.option.Options;
import japa.parser.ast.stmt.ExpressionStmt;
import japa.parser.ast.stmt.Statement;
import japa.parser.ast.stmt.TryStmt;
import japa.parser.ast.visitor.VoidVisitorAdapter;


public class StatementVisitor extends VoidVisitorAdapter<Void> {
	
	private List<Statement> stms;
	private boolean consFound = false;
	private boolean tryFound = false;
	private boolean methodFound = false;
	private String targetClass;
	
	public StatementVisitor() {
		stms = new ArrayList<Statement>();
		String[] helpTargetClass = Options.I().getTargetClass().split("/");
		targetClass = helpTargetClass[helpTargetClass.length-1].split("\\.")[0];
	}

	@Override
	public void visit(final TryStmt n, final Void arg) {
		tryFound = true;
		super.visit(n, arg);
	}
	
	public void visit(final ExpressionStmt n, final Void arg) {
		if(!tryFound && !methodFound) {
			if(n.toString().split(" ")[0].equals(targetClass)) {
				if(!consFound) {
					consFound = true;
					stms.add(n);
				}
			}else{
				String[] ss = Options.I().getMethodUnderTest().split("\\(")[0].split("\\.");
				if(n.toString().contains(ss[ss.length-1])) methodFound = true;
				stms.add(n);
			}
			
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
