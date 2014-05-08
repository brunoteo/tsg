package tsg.typeinference;

import java.util.ArrayList;
import java.util.List;

import tsg.option.Options;

public class TypeInference {
	
	protected String command;
	
	public String[] getCommand() {
		List<String> typeInference = new ArrayList<String>();
		typeInference.add("./binary/javai-reim");
		typeInference.add("-d");
		typeInference.add(Options.I().getOutputDir());
		typeInference.add("-sourcepath");
		typeInference.add(Options.I().getSourcepath());
		typeInference.add(Options.I().getTargetClass());
		for(String classpath : Options.I().getClasses().split(";")) {
			typeInference.add(classpath);
		}
		this.command = typeInference.toString();
		return typeInference.toArray(new String[0]);
	}
	
	@Override
	public String toString() {
		return command.replaceAll("\\[", "").replaceAll("\\]", "").replaceAll(",", "");
	}
}
