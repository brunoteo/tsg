package tsg.generator;

import japa.parser.JavaParser;
import japa.parser.ParseException;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.body.MethodDeclaration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import tsg.ast.MethodVisitor;
import tsg.ast.TryCatchVisitor;
import tsg.execution.ExecutionManager;
import tsg.execution.ExecutionResult;
import tsg.logging.Logger;
import tsg.option.Options;
import tsg.randoop.Randoop;

public class TestCaseGenerator {
	
	private static final Logger logger = new Logger(TestCaseGenerator.class);
	
	private static TestCaseGenerator instance;
	
	private final List<MethodDeclaration> tests;

	public TestCaseGenerator() {
		this.tests = new ArrayList<MethodDeclaration>();
	}
	
	public static TestCaseGenerator getInstance() {
		if (instance == null) {
			instance = new TestCaseGenerator();
		}
		return instance;
	}
	
	public void generateTestsCase() throws Exception {
		logger.info("Generating tests case");
		ExecutionResult result = generate();
		
		logger.debug(result.getStdout());
		logger.debug(result.getStderr());
		
		logger.debug("Check whether the generation was successful");
		//TODO controllare che esista il file pure-methods.csv
		if (result.getExitStatus() != 0) {
			throw new Exception("Generation failed due " + result.getStdout() + System.lineSeparator() + result.getStderr());
		}
		
		logger.info("Genereted tests case - Done");
	}
	
	private ExecutionResult generate() throws Exception {
		ExecutionManager manager = new ExecutionManager();
		Randoop randoop = new Randoop();
		return manager.execute(randoop);
	}
	
	public void generateAST() {
		logger.debug("Loading generated test");
		String testPath = Options.I().getRandoopDir() + "/pippo/RandoopTest0.java";
		try {
			CompilationUnit cu = JavaParser.parse(new File(testPath));
			
			MethodVisitor visitorM = new MethodVisitor();
			visitorM.visit(cu, null);		
			for (MethodDeclaration method : visitorM.getTests()) {
				TryCatchVisitor visitorTC = new TryCatchVisitor();
				visitorTC.visit(method, null);
				if(!visitorTC.isFound()) {
					tests.add(method);
				}
			}
			
			logger.debug("Found: " + tests.size() + " methods");
			logger.info("Loaded methods - DONE");
		} catch (ParseException | IOException e) {
			logger.error(e.getMessage());
		}
	}
	
	
}
