package ges.generator;

import ges.execution.InternalClassloader;
import ges.logging.Logger;
import ges.option.Options;
import ges.util.ClassUtil;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

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
		String targetClass = Options.I().getTargetClass();
		
		targetClass = ClassUtil.convertClass(targetClass);
		logger.info("Generating methods.csv for Randoop");
		logger.debug("Class to be invetigated: " + targetClass);
		
		try {
			c = Class.forName(targetClass, false, classLoader);
		} catch (ClassNotFoundException e) {
			throw new Exception("Target class not found");
		}
		
		Constructor[] constructors = c.getDeclaredConstructors();
		for(Constructor constructor : constructors) {
			
			constructorList.add(ClassUtil.getConstructorName(constructor));
		}
		logger.debug("Found: " + constructorList.size() + " constructors");
		
		readBlackList();
		
		for(Method method : c.getMethods()) {
			if (!method.getDeclaringClass().equals(Class.class) &&
					!method.getDeclaringClass().equals(Object.class) &&
					!method.isBridge() &&
					!method.isSynthetic() &&
					!isBlacklist(method)) {
							
					//FIXME Sistemare Object[] con L ecc..
					methodsList.add(ClassUtil.getMethodName(method));
				}
		}
		logger.debug("Found: " + methodsList.size() + " methods");
	}
	
	public void removePureMethods(List<String> pureMethods) {
		logger.debug("Remove pure methods");
		
		for(String pureMethod : pureMethods) {
			if(!pureMethod.equals(Options.I().getMethodUnderTest())) {
				methodsList.remove(pureMethod);
			}
			
		}
		logger.debug("Found: " + methodsList.size() + " non-pure methods");
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
		String methodName = ClassUtil.getMethodString(method);
		if(methodName.equals(Options.I().getMethodUnderTest())){
			return false;
		}
		
		for(String black : blackList) {
//			if (method.getName().contains(black)) {
			if (method.toString().contains(black)) {
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
