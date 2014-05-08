package tsg.generator;

import tsg.execution.ExecutionManager;
import tsg.execution.ExecutionResult;
import tsg.logging.Logger;
import tsg.randoop.Randoop;
import tsg.typeinference.TypeInference;

public class TestCaseGenerator {
	
	private static final Logger logger = new Logger(TestCaseGenerator.class);
	
	private static TestCaseGenerator instance;

	public TestCaseGenerator() {
		
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
	
	
}
