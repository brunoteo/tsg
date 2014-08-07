package esg.generator;

import esg.execution.ExecutionManager;
import esg.execution.ExecutionResult;
import esg.logging.Logger;
import esg.option.Options;
import esg.typeinference.TypeInference;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOError;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class PureMethodGenerator {
	
	private static final Logger logger = new Logger(PureMethodGenerator.class);
	
	private static PureMethodGenerator instance;
	
	private List<String> pureMethods;
	
	private PureMethodGenerator() {
		pureMethods = new ArrayList<String>();
	}
	
	public static PureMethodGenerator getInstance() {
		if (instance == null) {
			instance = new PureMethodGenerator();
		}
		return instance;
	}
	
	public void generatePureMethods() throws Exception {
		logger.info("Generating pure methods");
		ExecutionResult result = generate();
		
		logger.debug(result.getStdout());
		logger.debug(result.getStderr());
		
		logger.debug("Check whether the generation was successful");
		//TODO controllare che esista il file pure-methods.csv
		if (result.getExitStatus() != 0) {
			throw new Exception("Generation failed due " + result.getStdout() + System.lineSeparator() + result.getStderr());
		}
		
		logger.info("Genereted pure methods - Done");
	}
	
	public void readPureMethods() {
		logger.info("Reading pure methods");
		BufferedReader br = null;
		String line;
		try {
			br = new BufferedReader(new FileReader(Options.I().getTypeInferencePath()+"infer-output/pure-methods.csv"));
			while((line = br.readLine()) != null) {
				pureMethods.add(line);
			}
			logger.debug("Found: " + pureMethods.size() + " pure methods");
			logger.info("Read pure methods - Done");
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private ExecutionResult generate() throws Exception {
		ExecutionManager manager = new ExecutionManager();
		TypeInference typeInferenceCommand = new TypeInference();
		return manager.execute(typeInferenceCommand);
	}
	
	public List<String> getPureMethods() {
		return pureMethods;
	}

}
