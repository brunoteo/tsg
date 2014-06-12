package tsg.execution;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.concurrent.Callable;

import tsg.logging.Logger;
import tsg.option.Options;
import tsg.randoop.Randoop;

public class WorkerRandoop implements Callable<ExecutionResult>{
private static final Logger logger = new Logger(WorkerTypeInference.class);
	
	private final Randoop randoop;
	
	private int exitStatus;
	private ByteArrayOutputStream errStream = null;
	private ByteArrayOutputStream outStream = null;
	
	public WorkerRandoop(Randoop randoop) {
		this.randoop = randoop;
	}
	
	@Override
	public ExecutionResult call() {
		ExecutionResult result = null;
		
		try {
			ProcessBuilder pb = new ProcessBuilder(randoop.getCommand());
			pb = pb.directory(new File(Options.I().getRandoopDir()));

			logger.debug("Going to execute: " + randoop.toString());
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

			result = new ExecutionResult(Options.I().getRandoopDir());
			result.setCommand(randoop.getCommand());
			result.setStdout(getOutStream());
			result.setStderr(getErrStream());
			result.setFilename("RandoopTest0.java");		
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

	public Randoop getCommand() {
		return this.randoop;
	}
}
