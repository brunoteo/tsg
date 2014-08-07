package ges.util;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class ClassUtil {

	public static String getMethodName(String m) {
		String[] tmpString = m.split("\\(")[0].split("\\.");
		return tmpString[tmpString.length-1];
	}
	
	public static String getMethodName(Method m) {
		String methodStr = m.toString();
		return methodStr.substring(methodStr.indexOf(m.getDeclaringClass().toString().split(" ")[1]));
	}
	
	public static int getNumParameters(String m) {
		int startParams = m.indexOf("(");
		String params = m.substring(startParams);
		params = params.replace("(", "").replace(")", "").replace(";", "");
		String[] tmpStr = params.split(",");
		if(tmpStr[0].equals("")) return 0;
		return tmpStr.length;
	}
	
	public static String getParameters(String e) {
		String params = e.substring(e.indexOf("("));
		params = params.substring(0, params.length()-1);
		return params;
	}
	
	public static String getClassName(String c) {
		String[] helpTargetClass = c.split("/");
		return helpTargetClass[helpTargetClass.length-1].split("\\.")[0];
	}
	
	public static String convertClass(String c) {
		return c.replaceAll(".java", "").replaceAll("/", ".");
	}
	
	public static String getConstructorName(Constructor c) {
		String constructorStr = c.toGenericString();
		return constructorStr.substring(constructorStr.indexOf(c.getName()));
	}
	
	public static String getMethodString(Method m) {
		String[] tmp = m.toGenericString().split(" ");
		return tmp[tmp.length-1];
	}
}
