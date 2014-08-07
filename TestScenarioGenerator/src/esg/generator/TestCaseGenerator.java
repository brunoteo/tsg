package esg.generator;

import esg.ast.MethodVisitor;
import esg.ast.StatementVisitor;
import esg.execution.ExecutionManager;
import esg.execution.ExecutionResult;
import esg.logging.Logger;
import esg.option.Options;
import esg.randoop.Randoop;
import esg.util.ClassUtil;
import japa.parser.JavaParser;
import japa.parser.ParseException;
import japa.parser.ast.CompilationUnit;
import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.stmt.BlockStmt;
import japa.parser.ast.stmt.Statement;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

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
		//TODO controllare che esista il file RandoopTest0.csv
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
	
	//TODO recuperare gli import
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
			
//			createTestScenarioFile();
			
			testsStr = convertToString();
			
//			logger.debug("Prima " + testsStr.size());
			
			changeVarName();
			deleteEqual();
			
			
			logger.debug("Dopo " + testsStr.size());
			createTestScenarioFile2();
			
//			logger.debug("Found: " + tests.size() + " methods");
			logger.info("Loaded methods - DONE");
		} catch (ParseException | IOException e) {
			logger.error(e.getMessage());
		}
	}
	
	private void changeVarName() {
		String varName = testsStr.get(0).split(" ")[1];
		for(int i = 0; i < testsStr.size(); i++) {
			String tmpSts = testsStr.get(i).replaceAll(varName.substring(0, varName.length()-1) + ".", varName);
			testsStr.set(i, tmpSts);
		}
		
	}

	//TODO non servono (solo test)
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
	
	//TODO non servono (solo test)
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
		String targetClass = ClassUtil.getClassName(Options.I().getTargetClass());
		
		for(List<Statement> stms : tests) {
			StringBuilder strBld = new StringBuilder();
			for(int i = 1; i < stms.size(); i++) {
				strBld.append(stms.get(i).toString().replaceAll(targetClass, targetClass+"<Integer>").replaceAll("\\(Object\\)", "").replaceAll("Object", "Integer"));
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
