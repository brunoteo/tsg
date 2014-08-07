package ges;

import ges.generator.MethodFileGenerator;
import ges.generator.PureMethodGenerator;
import ges.generator.TestCaseGenerator;
import ges.logging.Logger;

public class GESManager {
	private static final Logger logger = new Logger(GESManager.class);

	public GESManager() {
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
