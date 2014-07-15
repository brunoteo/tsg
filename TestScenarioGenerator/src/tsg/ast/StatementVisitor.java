package tsg.ast;

import java.util.ArrayList;
import java.util.List;

import tsg.option.Options;
import tsg.util.ClassUtil;
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
		targetClass = ClassUtil.getClassName(Options.I().getTargetClass());
	}

	@Override
	public void visit(final TryStmt n, final Void arg) {
		tryFound = true;
		super.visit(n, arg);
	}
	
	public void visit(final ExpressionStmt n, final Void arg) {
		if(!tryFound && !methodFound) {
			if(n.toString().startsWith("assert")) {
				
			}
			else if(n.toString().split(" ")[0].equals(targetClass)) {
				if(!consFound) {
					consFound = true;
					stms.add(n);
				}
			}else{
				String methodNameMUT = ClassUtil.getMethodName(Options.I().getMethodUnderTest());
				String methodName = ClassUtil.getMethodName(n.toString());
				if(methodName.equals(methodNameMUT)) {
					int numParMUT = ClassUtil.getNumParameters(Options.I().getMethodUnderTest());
					int numPar = ClassUtil.getNumParameters(n.toString());
					if(numParMUT == numPar) methodFound = true;
				}
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
