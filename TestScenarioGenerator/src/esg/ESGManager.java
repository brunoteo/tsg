package esg;

import esg.generator.MethodsFileGenerator;
import esg.generator.PureMethodsGenerator;
import esg.generator.TestCasesGenerator;
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
		PureMethodsGenerator pureMethodGenerator = PureMethodsGenerator.getInstance();
		pureMethodGenerator.generatePureMethods();
		pureMethodGenerator.readPureMethods();
		
		// ======================= CREATE RANDOOP METHODS FILE ========================
		MethodsFileGenerator methodFileGenerator = MethodsFileGenerator.getInstance();
		methodFileGenerator.generateMethods();
		methodFileGenerator.removePureMethods(pureMethodGenerator.getPureMethods());
		methodFileGenerator.createMethodsFile();
		
		// =============================== RUN RANDOOP ================================
		TestCasesGenerator testCaseGenerator = TestCasesGenerator.getInstance();
		testCaseGenerator.generateTestsCase();
		
		// ============================== AST TEST CASE ===============================
		testCaseGenerator.generateAST();
		
	}
}
