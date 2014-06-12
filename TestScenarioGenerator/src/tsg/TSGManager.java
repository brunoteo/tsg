package tsg;

import tsg.generator.MethodFileGenerator;
import tsg.generator.PureMethodGenerator;
import tsg.generator.TestCaseGenerator;
import tsg.logging.Logger;

public class TSGManager {
	private static final Logger logger = new Logger(TSGManager.class);

	public TSGManager() {
		//TODO statistics
	}
	
	public void generateTS() throws Exception{
		
		// =================================== INIT ===================================
		//TODO controllare ciò che ho passato come args
		
		// ========================= CREATE PURE METHODS FILE =========================
		PureMethodGenerator pureMethodGenerator = PureMethodGenerator.getInstance();
		pureMethodGenerator.generatePureMethods();
		pureMethodGenerator.readPureMethods();
		
		// ======================= CREATE RANDOOP METHODS FILE ========================
		//TODO creare directory per il file
		MethodFileGenerator methodFileGenerator = MethodFileGenerator.getInstance();
		methodFileGenerator.generateMethods();
		methodFileGenerator.removePureMethods(pureMethodGenerator.getPureMethods());
		methodFileGenerator.createMethodsFile();
		
		// =============================== RUN RANDOOP ================================
		TestCaseGenerator testCaseGenerator = TestCaseGenerator.getInstance();
		testCaseGenerator.generateTestsCase();
		
		// ============================== AST TEST CASE ===============================
		testCaseGenerator.generateAST();
	}
}
