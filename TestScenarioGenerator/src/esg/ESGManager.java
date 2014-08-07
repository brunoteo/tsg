package esg;

import esg.generator.MethodFileGenerator;
import esg.generator.PureMethodGenerator;
import esg.generator.TestCaseGenerator;
import esg.logging.Logger;

public class ESGManager {
	private static final Logger logger = new Logger(ESGManager.class);

	public ESGManager() {
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
