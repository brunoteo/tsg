package esg.execution;

import esg.logging.Logger;
import esg.option.Options;
import esg.typeinference.TypeInference;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.Callable;

public class WorkerTypeInference implements Callable<ExecutionResult>{

	private static final Logger logger = new Logger(WorkerTypeInference.class);
	
	private final TypeInference typeInference;
	
	private int exitStatus;
	private ByteArrayOutputStream errStream = null;
	private ByteArrayOutputStream outStream = null;
	
	public WorkerTypeInference(TypeInference typeInference) {
		this.typeInference = typeInference;
	}
	
	@Override
	public ExecutionResult call() {
		ExecutionResult result = null;
		
		try {
			ProcessBuilder pb = new ProcessBuilder(typeInference.getCommand());
			pb = pb.directory(new File(Options.I().getTypeInferencePath()));

			logger.debug("Going to execute: " + typeInference.toString());
			Process process = pb.start();
			this.errStream = new ByteArrayOutputStream();
			this.outStream = new ByteArrayOutputStream();
			Thread errThread = new StreamRedirectThread(Thread.currentThread().getName() + "-error-reader", process.getErrorStream(), this.errStream);
			Thread outThread = new StreamRedirectThread(Thread.currentThread().getName() + "-output-reader", process.getInputStream(), this.outStream);
			errThread.start();
			outThread.start();

			this.exitStatus = process.waitFor();
			errThread.interrupt();
			outThread.interrupt();

			result = new ExecutionResult(Options.I().getTypeInferencePath());
			result.setCommand(typeInference.getCommand());
			result.setStdout(getOutStream());
			result.setStderr(getErrStream());
			result.setFilename("pure-methods.csv");		
			result.setExitStatus(this.exitStatus);
		}
		catch (IOException | InterruptedException e) {
			logger.error("Unable to execute command", e);
		}

		return result;
	}
	
	public int getExitStatus() {
		return this.exitStatus;
	}

	public String getErrStream() {
		return this.errStream.toString();
	}

	public String getOutStream() {
		return this.outStream.toString();
	}

	public TypeInference getCommand() {
		return this.typeInference;
	}
}
