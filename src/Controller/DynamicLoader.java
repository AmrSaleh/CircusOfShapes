package Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.nio.channels.FileChannel;

public class DynamicLoader {
	public static void addClass(File file, final String theClassName,boolean calledInternally) throws Exception {
		if (ListLogic.getAvailableClassesNames().contains("Model."+theClassName)) {
			return;
		}

		String fileDirectory = file.getAbsolutePath().replace(theClassName + ".class", "");

		prepareForLoadClassOfPackageModel(fileDirectory, new File(fileDirectory + "\\" + theClassName + ".class"));
		File directory = new File(fileDirectory);
		URL url;
		try {
			url = directory.toURI().toURL();
//			System.out.println("koko" + url.getPath());
			URL[] urls = new URL[] { url };
			ClassLoader cl = new URLClassLoader(urls);
			// Class cls =Class.forName("Model."+theClassName, true, cl);
			Class cls = cl.loadClass("Model." + theClassName);
			
			ListLogic.addClassToAvailClasses(cls);
			System.out.println("loaded extra class "+cls.getName());
			if(!calledInternally){
			ListLogic.addClasstoConfigFile("Model."+theClassName,file.getPath());
			}
			Object myObject = cls.newInstance();
			
//			Method myMethod = cls.getMethod("doSomething"); 
//			myMethod.invoke(myObject);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// DEAL WITH EXTREME CARE ...DELETES ACTUAL FILES ON COMPUTER (works
		// well now but I preferred not to use it for safety measures)
		// cleanAfterLoadingClassOfPackageModel(fileDirectory+"\\Model");
	}
	public static void prepareForLoadClassOfPackageModel(String pathOfContainingFolder, File classFile) throws Exception {
		makeFolder(pathOfContainingFolder + "\\Model");
		File dest = new File(pathOfContainingFolder + "\\Model\\" + classFile.getName());
		
			copyFile(classFile, dest);
		
	}
	public void cleanAfterLoadingClassOfPackageModel(String pathOfContainingFolder) {
		File directory = new File(pathOfContainingFolder);
		if (!directory.exists()) {

			System.out.println("Directory does not exist.");
			System.exit(0);

		} else {

			try {

				delete(directory);

			} catch (IOException e) {
				e.printStackTrace();
				System.exit(0);
			}
		}
	}

	private static void copyFile(File sourceFile, File destFile) throws Exception {
		if (!sourceFile.exists()) {
			throw new Exception("Class file not found");
		}
		if (!destFile.exists()) {
			destFile.createNewFile();
		}
		FileChannel source = null;
		FileChannel destination = null;
		source = new FileInputStream(sourceFile).getChannel();
		destination = new FileOutputStream(destFile).getChannel();
		if (destination != null && source != null) {
			destination.transferFrom(source, 0, source.size());
		}
		if (source != null) {
			source.close();
		}
		if (destination != null) {
			destination.close();
		}

	}

	public static void makeFolder(String folderPath) {
		// Create a directory; all non-existent ancestor directories are
		// automatically created
		Boolean success = (new File(folderPath)).mkdirs();
		if (!success) {
			// Directory creation failed
		}
	}

	public static void delete(File file) throws IOException {

		if (file.isDirectory()) {

			// directory is empty, then delete it
			if (file.list().length == 0) {

				file.delete();
				System.out.println("Directory is deleted : " + file.getAbsolutePath());

			} else {

				// list all the directory contents
				String files[] = file.list();

				for (String temp : files) {
					// construct the file structure
					File fileDelete = new File(file, temp);

					// recursive delete
					delete(fileDelete);
				}

				// check the directory again, if empty then delete it
				if (file.list().length == 0) {
					file.delete();
					System.out.println("Directory is deleted : " + file.getAbsolutePath());
				}
			}

		} else {
			// if file, then delete it
			file.delete();
			System.out.println("File is deleted : " + file.getAbsolutePath());
		}
	}

}
