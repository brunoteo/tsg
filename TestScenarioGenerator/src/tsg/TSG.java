package tsg;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;

import tsg.logging.Logger;
import tsg.option.Options;


public class TSG {
	private static final Logger logger = new Logger(TSG.class);
	
	public static void main(String[] args) {
		logger.info("TSG started");
		final Options arguments = Options.I();
		final CmdLineParser parser = new CmdLineParser(arguments);
		
		try {
			parser.parseArgument(processArgs(args));
		} catch (CmdLineException e) {
			printUsage(parser);
			System.exit(-1);
		}
		
		try {
			TSGManager generator = new TSGManager();
			generator.generateTS();

			logger.info("TSG ended successfully");
		}
		//TODO sistemare l'eccezione Execption
		catch (Exception e) {
			logger.fatal("Execution aborted due: " + e.getMessage());
		}

	}
	
	private static void printUsage(final CmdLineParser parser) {
		System.err.println("java Main <options>");
		System.err.println("<options> are:");
		// print the list of available options
		parser.setUsageWidth(120);
		parser.printUsage(System.err);
	}
	
	private static String[] processArgs(final String[] args) {
		Pattern argPattern = Pattern.compile("(-[a-zA-Z_-]+)=(.*)");
		List<String> processedArgs = new ArrayList<String>();
		for (String arg : args) {
			Matcher matcher = argPattern.matcher(arg);
			if (matcher.matches()) {
				processedArgs.add(matcher.group(1));
				String value = matcher.group(2);
				processedArgs.add(value);
			} else {
				processedArgs.add(arg);
			}
		}

		return processedArgs.toArray(new String[0]);
	}

}
