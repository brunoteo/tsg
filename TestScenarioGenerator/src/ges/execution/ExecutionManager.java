package ges.execution;

import ges.logging.Logger;
import ges.randoop.Randoop;
import ges.typeinference.TypeInference;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ExecutionManager {
	
	private static final Logger logger = new Logger(ExecutionManager.class);

	public ExecutionResult execute(TypeInference typeInference) throws Exception {
		ExecutorService executor = Executors.newFixedThreadPool(1);
		WorkerTypeInference worker = new WorkerTypeInference(typeInference);
		
		Future<ExecutionResult> firstResult = executor.submit(worker);
		executor.shutdown();

		boolean result = false;
		
		try {
			result = executor.awaitTermination(1000, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			logger.fatal("Timeout during pure methods generation");
			throw new Exception("Timeout during pure methods generation");
		}

		if (!result) {
			executor.shutdownNow();
			logger.fatal("Timeout during pure methods generation");
			throw new Exception("Timeout during pure methods generation");
		}

		ExecutionResult toReturn = null;
		try {
			toReturn = firstResult.get();
		} catch (InterruptedException | ExecutionException e) {
			throw new Exception("Error occurred during pure methods generation: " + e.getMessage(), e);
		}

		return toReturn;
	}
	
	public ExecutionResult execute(Randoop randoop) throws Exception {
		ExecutorService executor = Executors.newFixedThreadPool(1);
		WorkerRandoop worker = new WorkerRandoop(randoop);
		
		Future<ExecutionResult> firstResult = executor.submit(worker);
		executor.shutdown();

		boolean result = false;
		
		try {
			result = executor.awaitTermination(100, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			logger.fatal("Timeout during tests case generation");
			throw new Exception("Timeout during tests case generation");
		}

		if (!result) {
			executor.shutdownNow();
			logger.fatal("Timeout during tests case generation");
			throw new Exception("Timeout during tests case generation");
		}

		ExecutionResult toReturn = null;
		try {
			toReturn = firstResult.get();
		} catch (InterruptedException | ExecutionException e) {
			throw new Exception("Error occurred during tests case generation: " + e.getMessage(), e);
		}

		return toReturn;
	}
}
