package tsg.generator;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import tsg.execution.InternalClassloader;
import tsg.logging.Logger;
import tsg.option.Options;

public class MethodFileGenerator {
	
	private static final Logger logger = new Logger(MethodFileGenerator.class);
	
	private final ClassLoader classLoader;
	private static MethodFileGenerator instance;
	
	private List<String> methodsList;
	private List<String> constructorList;
	private List<String> blackList;
	
	private String outputFile = Options.I().getRandoopDir() + "methods.csv";
	
	private MethodFileGenerator() throws Exception {
		InternalClassloader ic = new InternalClassloader(Options.I().getOutputDir());
		this.classLoader = ic.getClassLoader();
		methodsList = new ArrayList<String>();
		constructorList = new ArrayList<String>();
	}
	
	public static MethodFileGenerator getInstance() throws Exception {
		if (instance == null) {
			instance = new MethodFileGenerator();
		}
		return instance;
	}
	
	public void generateMethods() throws Exception {
		Class<?> c;
		String methodStr;
		String constructorStr;
		String targetClass = Options.I().getTargetClass();
		//TODO fare un utility
		targetClass = targetClass.replaceAll(".java", "").replaceAll("/", ".");
		logger.info("Generating methods.csv for Randoop");
		logger.debug("Class to be invetigated: " + targetClass);
		
		readBlackList();
		
		try {
			c = Class.forName(targetClass, false, classLoader);
		} catch (ClassNotFoundException e) {
			throw new Exception("Target class not found");
		}
		
		Constructor[] constructors = c.getDeclaredConstructors();
		for(Constructor constructor : constructors) {
			constructorStr = constructor.toGenericString();
			constructorList.add(constructorStr.substring(constructorStr.indexOf(constructor.getName())));
		}
		logger.debug("Found: " + constructorList.size() + " constructors");
		
		for(Method method : c.getMethods()) {
			if (!method.getDeclaringClass().equals(Class.class) &&
					!method.getDeclaringClass().equals(Object.class) &&
					!method.isBridge() &&
					!method.isSynthetic() &&
					!isBlacklist(method)) {
							
					//TODO fare un utility
					methodStr = method.toString();
					methodStr = methodStr.substring(methodStr.indexOf(method.getDeclaringClass().toString().split(" ")[1]));
					methodsList.add(methodStr);
				}
		}
		logger.debug("Found: " + methodsList.size() + " methods");
	}
	
	public void removePureMethods(List<String> pureMethods) {
		logger.debug("Remove pure methods");
		
		for(String pureMethod : pureMethods) {
			if (!pureMethod.equals(Options.I().getMethodUnderTest())) {
				methodsList.remove(pureMethod);
			}
			
		}
		logger.debug("Found: " + methodsList.size() + " non-pure methods");
		//TODO eliminare i metodi non puri che non ci piacciono
	}
	
	public void createMethodsFile() {
		logger.debug("Create methods.csv");
		FileWriter fw = null;
		
		try {
			fw = new FileWriter(outputFile);

			for(String constructor : constructorList) {
				fw.append("cons : " + constructor.substring(0, constructor.indexOf("(")) + ".<init>" + constructor.substring(constructor.indexOf("(")));
				fw.append('\n');
			}
			for(String method : methodsList) {
				fw.append("method : " + method);
				fw.append('\n');
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fw != null) {
				try {
					fw.flush();
					fw.close();
					logger.info("Generated file methods.csv - Done");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private boolean isBlacklist(Method method) {
		for(String black : blackList) {
			if (method.getName().contains(black)) {
				return true;
			}
		}
		return false;
	}
	
	private void readBlackList() {
		BufferedReader br = null;
		blackList = new ArrayList<String>();
		String line;
		try {
			br = new BufferedReader(new FileReader(Options.I().getBlackList()));
			while((line = br.readLine()) != null) {
				blackList.add(line);
			}
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
	

}
