package tsg.option;

import org.kohsuke.args4j.Option;

public class Options {
	private static Options instance = null;

	private Options() { }

	public static Options I() {
		if (instance == null) {
			instance = new Options();
		}
		return instance;
	}

	@Option(name = "-sourcepath",
			usage = "Sourcepath to classes",
			required = true)
	private String soucepath;
	
	@Option(name = "-targetClass",
			usage = "Target class",
			required = true)
	private String targetClass;
	
	@Option(name = "-method_under_test",
			usage = "Method under test",
			required = true)
	private String methodUnderTest;
	
	@Option(name = "-classes",
			usage = "Depedences of target class",
			required = true)
	private String classes;
	
	@Option(name = "-outputDir",
			usage = "Output directory for binary",
			required = true)
	private String outputDir;
	
	@Option(name = "-type_inference",
			usage = "Path to type-inference")
	private String typeInferenceDir = "type-inference-0.1.3/";
	
	@Option(name = "-randoop",
			usage = "Path to randoop")
	private String randoopDir = "randoop/";

	public String getSourcepath() {
		return soucepath;
	}
	
	public String getTargetClass() {
		return targetClass;
	}
	
	public String getMethodUnderTest() {
		return methodUnderTest;
	}
	
	public String getClasses() {
		return classes;
	}
	
	public String getOutputDir() {
		return outputDir;
	}

	public String getTypeInferencePath() {
		return typeInferenceDir;
	}
	
	public String getRandoopDir() {
		return randoopDir;
	}	
	
	public void setSourcepath(String soucepath) {
		this.soucepath = soucepath;
	}
	
	public void setTargetClass(String targetClass) {
		this.targetClass = targetClass;
	}
	
	public void setMethodUnderTest(String methodUnderTest) {
		this.methodUnderTest = methodUnderTest;
	}
	
	public void setClasses(String classes) {
		this.classes = classes;
	}
	
	public void setOutputDir(String outputDir) {
		this.outputDir = outputDir;
	}
	
	public void setTypeInferencePath(String typeInferenceDir) {
		this.typeInferenceDir = typeInferenceDir;
	}
	
	public void setRandoopDir(String randoopDir) {
		this.randoopDir = randoopDir;
	}

}
