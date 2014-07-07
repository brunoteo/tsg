package tsg.generator;

import japa.parser.JavaParser;
import japa.parser.ParseException;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.stmt.BlockStmt;
import japa.parser.ast.stmt.ExpressionStmt;
import japa.parser.ast.stmt.Statement;
import japa.parser.ast.stmt.TryStmt;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import tsg.ast.MethodVisitor;
import tsg.ast.StatementVisitor;
import tsg.ast.TryCatchVisitor;
import tsg.execution.ExecutionManager;
import tsg.execution.ExecutionResult;
import tsg.logging.Logger;
import tsg.option.Options;
import tsg.randoop.Randoop;

public class TestCaseGenerator {
	
	private static final Logger logger = new Logger(TestCaseGenerator.class);
	
	private static TestCaseGenerator instance;
	
	private final List<List<Statement>> tests;
	private List<String> testsStr = null;

	public TestCaseGenerator() {
		this.tests = new ArrayList<List<Statement>>();
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
		String testPath = Options.I().getRandoopDir() + "/result/RandoopTest0.java";
		
		try {
			CompilationUnit cu = JavaParser.parse(new File(testPath));
			
			MethodVisitor visitorM = new MethodVisitor();
			visitorM.visit(cu, null);		
			for (MethodDeclaration method : visitorM.getTests()) {
				
				StatementVisitor visitorStm = new StatementVisitor();
				
				BlockStmt block = method.getBody();
				visitorStm.visit(block, null);
				
				if(visitorStm.getMethodFound()) {
					tests.add(visitorStm.getStatements());
				}
				
			}
			
			createTestScenarioFile();
			
			testsStr = convertToString();
			
			logger.debug("Prima " + testsStr.size());
			
			deleteEqual();
			
			logger.debug("Dopo " + testsStr.size());
			createTestScenarioFile2();
			
			logger.debug("Found: " + tests.size() + " methods");
			logger.info("Loaded methods - DONE");
		} catch (ParseException | IOException e) {
			logger.error(e.getMessage());
		}
	}
	
	//FIXME non servono (solo test)
	public void createTestScenarioFile() {
		String outputFile = "test_scenario.csv";
		logger.debug("Creating test_scenario.csv");
		FileWriter fw = null;
		
		try {
			fw = new FileWriter(outputFile);

			for(List<Statement> stms : tests) {
				for(Statement stm : stms) {
					fw.append(stm.toString());
					fw.append('\n');
				}
				fw.append('\n');
				fw.append('\n');
				fw.append('\n');
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fw != null) {
				try {
					fw.flush();
					fw.close();
					logger.info("Generated file test_scenario.csv - Done");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	//FIXME non servono (solo test)
	public void createTestScenarioFile2() {
		String outputFile = "test_scenario_reduced.csv";
		logger.debug("Creating test_scenario_reduced.csv");
		FileWriter fw = null;
		
		try {
			fw = new FileWriter(outputFile);

			for(String stms : testsStr) {
				fw.append(stms);
				fw.append('\n');
				fw.append('\n');
				fw.append('\n');
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fw != null) {
				try {
					fw.flush();
					fw.close();
					logger.info("Generated file test_scenario_reduced.csv - Done");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private List<String> convertToString() {
		List<String> result = new ArrayList<String>();
		
		for(List<Statement> stms : tests) {
			StringBuilder strBld = new StringBuilder();
			for(int i = 1; i < stms.size(); i++) {
				strBld.append(stms.get(i).toString());
				strBld.append('\n');
			}
			result.add(strBld.toString());
		}
		
		return result;
	}
	
	private void deleteEqual() {
		logger.info("Deleting the same methods");
		
		HashSet<String> hs = new HashSet<String>();
		hs.addAll(testsStr);
		testsStr.clear();
		testsStr.addAll(hs);
	}

}
