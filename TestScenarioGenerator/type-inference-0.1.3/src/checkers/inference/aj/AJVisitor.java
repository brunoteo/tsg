/**
 * 
 */
package checkers.inference.aj;

import com.sun.source.tree.CompilationUnitTree;

import checkers.basetype.BaseTypeVisitor;

/**
 * @author huangw5
 *
 */
public class AJVisitor extends BaseTypeVisitor<AJChecker> {

	public AJVisitor(AJChecker checker, CompilationUnitTree root) {
		super(checker, root);
		// TODO Auto-generated constructor stub
	}

}
