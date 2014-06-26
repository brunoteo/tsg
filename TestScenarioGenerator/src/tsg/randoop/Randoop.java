package tsg.randoop;

import java.util.ArrayList;
import java.util.List;

import tsg.option.Options;

public class Randoop {
	
	protected String command;
	
	public String[] getCommand() {
		List<String> randoop = new ArrayList<String>();
		randoop.add("java");
		randoop.add("-cp");
		randoop.add("randoop.jar:" + Options.I().getOutputDir());
		randoop.add("randoop.main.Main");
		randoop.add("gentests");
		randoop.add("--methodlist=methods.csv");
		randoop.add("--output-tests=pass");
		//FIXME cambiare cartella output
		randoop.add("--junit-output-dir=pippo");
		randoop.add("--pretty-print=true");
		randoop.add("--outputlimit=50");
		randoop.add("--always-use-ints-as-objects=true");
		randoop.add("--maxsize=15");
		randoop.add("--timelimit=60");
		this.command = randoop.toString();
		return randoop.toArray(new String[0]);
	}
	
	@Override
	public String toString() {
		return command.replaceAll("\\[", "").replaceAll("\\]", "").replaceAll(",", "");
	}
}
