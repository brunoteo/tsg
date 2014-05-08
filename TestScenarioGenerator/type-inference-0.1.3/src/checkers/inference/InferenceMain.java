/**
 * 
 */
package checkers.inference;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import checkers.util.CheckerMain;

import com.sun.tools.javac.main.Main;

/**
 * Those helper methods are from {@link CheckerMain}
 * @author huangw5
 *
 */
public class InferenceMain {

    private static final String VERSION = "1";
    
    private static InferenceMain inferenceMain = null;
    
    private InferenceChecker inferenceChcker; 
    
    private ConstraintSolver currentSolver;
    
    private ConstraintManager constraintManager;
    
    private InferenceMain() {
    	constraintManager = new ConstraintManager();
    	inferenceChcker = null;
    	currentSolver = null;
    }
    
    public static InferenceMain getInstance() {
    	synchronized (InferenceMain.class) {
    		if (inferenceMain == null)
    			inferenceMain = new InferenceMain();
		}
    	return inferenceMain;
    }
    
    public static void destroy() {
    	inferenceMain = null;
    }
    
	public ConstraintSolver getCurrentSolver() {
		return currentSolver;
	}

	public ConstraintManager getConstraintManager() {
		return constraintManager;
	}

	public InferenceChecker getInferenceChcker() {
		return inferenceChcker;
	}

	public void setInferenceChcker(InferenceChecker inferenceChcker) {
		this.inferenceChcker = inferenceChcker;
	}

	public List<Reference> infer(String[] args, String jdkBootPaths, PrintWriter out) {
		for (String path : jdkBootPaths.split(File.pathSeparator)) {
			if (!path.equals("") && !new File(path).exists()) 
				throw new RuntimeException("Cannot find the boot jar: " + path);
		}
		
		System.setProperty("sun.boot.class.path",
				jdkBootPaths + ":" + System.getProperty("sun.boot.class.path"));
		
		
//		System.out.println("sun.boot.class.path: " + System.getProperty("sun.boot.class.path"));
		
		List<String> argList = new ArrayList<String>(args.length + 10);
        // Add arguments
        argList.add("-Xbootclasspath/p:" + jdkBootPaths);
        argList.add("-proc:only");
        argList.add("-Awarns");
        
        for (String arg : args) 
        	argList.add(arg);
        
		com.sun.tools.javac.main.Main main = new com.sun.tools.javac.main.Main("javac", out);
        if (main.compile(argList.toArray(new String[0])) != Main.Result.OK)
        	return null;

        List<Constraint> constraints = constraintManager.getConstraints();
		System.out.println("Generated " + constraints.size() + " constraints in total");
		
		currentSolver = new SetbasedSolver(inferenceChcker, Reference.getExpReferences());
		currentSolver.solve(constraints);
		Reference.clearup();
		return currentSolver.getInferredReferences();
	}
	
	public boolean check(String[] args, String jdkBootPaths, PrintWriter out) {
		System.setProperty("sun.boot.class.path",
				jdkBootPaths + ":" + System.getProperty("sun.boot.class.path"));
		List<String> argList = new ArrayList<String>(args.length + 10);
		argList = new ArrayList<String>(args.length + 10);
        for (String arg : args) 
        	argList.add(arg);

        argList.add("-Xbootclasspath/p:" + jdkBootPaths);
        argList.add("-Achecking");
        argList.add("-Awarns");
        argList.add("-proc:only");
        com.sun.tools.javac.main.Main main = new com.sun.tools.javac.main.Main("javac", out);
        if (main.compile(argList.toArray(new String[0])) != Main.Result.OK) {
        	return false;
        }
        return true;
	}
	
    
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String jdkBootPaths = null;
		String bootStr = "-Xbootclasspath/p:";
		for (String arg : args) {
			if (arg.startsWith(bootStr)) {
				jdkBootPaths = arg.substring(bootStr.length());
//				System.out.println(jdkBootPaths);
				break;
			}
		}
		if (jdkBootPaths == null)
			jdkBootPaths = jdkJar();
		
		InferenceMain inferenceMain = InferenceMain.getInstance();
		long startTime = System.currentTimeMillis();
		if (inferenceMain.infer(args, jdkBootPaths, new PrintWriter(System.err, true)) == null) {
			return;
		}
		
		// print results
		PrintWriter pw = null;
		try {
			pw = new PrintWriter("new-result.csv");
			inferenceMain.getCurrentSolver().printAllVariables(pw);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		if (pw != null)
			pw.close();
		
		System.out.println("Inference finished");
		System.out.println("inferrence_time: "
				+ String.format("%6.1f ",
						(float) (System.currentTimeMillis() - startTime) / 1000));
		
		startTime = System.currentTimeMillis();
		inferenceMain.check(args, jdkBootPaths, new PrintWriter(System.err, true));
		System.out.println("Checking finished");
		System.out.println("checking_time: "
				+ String.format("%6.1f ",
					(float)(System.currentTimeMillis() - startTime) / 1000));
	}
	
	
    private static File tempJDKPath() {
        String userSupplied = System.getProperty("jsr308.jdk");
        if (userSupplied != null)
            return new File(userSupplied);

        String tmpFolder = System.getProperty("java.io.tmpdir");
        File jdkFile = new File(tmpFolder, "jdk-" + VERSION + ".jar");
        return jdkFile;
    }
	
	/** returns the path to annotated JDK */
    private static String jdkJar() {
        // case 1: running from binary
        String thisJar = findPathJar(CheckerMain.class);
        File potential = new File(new File(thisJar).getParentFile(), "jdk.jar");
        if (potential.exists()) {
            //System.out.println("from adjacent jdk.jar");
            return potential.getPath();
        }

        // case 2: there was a temporary copy
        File jdkFile = tempJDKPath();
        //System.out.println(jdkFile);
        if (jdkFile.exists()) {
            //System.out.println("From temporary");
            return jdkFile.getPath();
        }

        // case 3: extract zipped jdk.jar
        try {
            extractFile(thisJar, "jdk.jar", jdkFile);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        if (jdkFile.exists()) {
            //System.out.println("Extracted jar");
            return jdkFile.getPath();
        }

        throw new AssertionError("Couldn't find annotated JDK");
    }

    private static void extractFile(String jar, String fileName, File output) throws Exception {
        int BUFFER = 2048;

        File jarFile = new File(jar);
        ZipFile zip = new ZipFile(jarFile);

        ZipEntry entry = zip.getEntry(fileName);
        assert !entry.isDirectory();

        BufferedInputStream is = new BufferedInputStream(zip.getInputStream(entry));
        int currentByte;
        // establish buffer for writing file
        byte data[] = new byte[BUFFER];

        // write the current file to disk
        FileOutputStream fos = new FileOutputStream(output);
        BufferedOutputStream dest = new BufferedOutputStream(fos, BUFFER);

        // read and write until last byte is encountered
        while ((currentByte = is.read(data, 0, BUFFER)) != -1) {
            dest.write(data, 0, currentByte);
        }
        dest.flush();
        dest.close();
        is.close();
    }

    /**
     * Find the jar file containing the annotated JDK (i.e. jar containing
     * this file
     */
    public static String findPathJar(Class<?> context) throws IllegalStateException {
        if (context == null) context = CheckerMain.class;
        String rawName = context.getName();
        String classFileName;
        /* rawName is something like package.name.ContainingClass$ClassName. We need to turn this into ContainingClass$ClassName.class. */ {
            int idx = rawName.lastIndexOf('.');
            classFileName = (idx == -1 ? rawName : rawName.substring(idx+1)) + ".class";
        }

        String uri = context.getResource(classFileName).toString();
        if (uri.startsWith("file:")) throw new IllegalStateException("This class has been loaded from a directory and not from a jar file.");
        if (!uri.startsWith("jar:file:")) {
            int idx = uri.indexOf(':');
            String protocol = idx == -1 ? "(unknown)" : uri.substring(0, idx);
            throw new IllegalStateException("This class has been loaded remotely via the " + protocol +
                    " protocol. Only loading from a jar on the local file system is supported.");
        }

        int idx = uri.indexOf('!');
        //As far as I know, the if statement below can't ever trigger, so it's more of a sanity check thing.
        if (idx == -1) throw new IllegalStateException("You appear to have loaded this class from a local jar file, but I can't make sense of the URL!");

        try {
            String fileName = URLDecoder.decode(uri.substring("jar:file:".length(), idx), Charset.defaultCharset().name());
            return new File(fileName).getAbsolutePath();
        } catch (UnsupportedEncodingException e) {
            throw new InternalError("default charset doesn't exist. Your VM is borked.");
        }
    }
}
