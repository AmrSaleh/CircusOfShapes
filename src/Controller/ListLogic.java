package Controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Properties;

import Model.*;

public class ListLogic {
	private static String path = "Shapes\\";
	// private static ArrayList<String> availableShape;
	private static ArrayList<Class<?>> availableClasses = new ArrayList<Class<?>>();
	private static ArrayList<String> availableClassesNames = new ArrayList<String>();

	public static void populateFreeShapeList() {
		// Class<?> noparams[] = new Class[0];
		for (int i = 0; i < ShapePool.getShapePoolInstance().getMaximumLength(); i++) {
			int random = 0 + (int) (Math.random() * ((availableClasses.size() - 1 - 0) + 1));
			// interpret.set("PlateShapeRed", availableShape.get(random));
			try {
				Class<?> cls = availableClasses.get(random);
				Constructor<?>[] cons = cls.getDeclaredConstructors();
				// Object obj = cons[1].newInstance();
				// Method method =
				// cls.getDeclaredMethod(availableShape.get(random).substring(0,
				// availableShape.get(random).length()-4), params);
				// RandShape rand = (RandShape) cons[0].newInstance();
				ShapePool.getShapePoolInstance().getFreeShapesList().add((RandShape) cons[0].newInstance());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				continue;
				// e.printStackTrace();
			}

			// ShapePool.getShapePoolInstance().getFreeShapesList().add((RandShape)Class.forName("").getMethod(availableShape.get(random).substring(0,
			// availableShape.get(random).length()-4)).invoke(availableShape.get(random),
			// 0));

		}
	}

	public static void loadAvailableShapes() {
		try {
			loadProperties();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("can't load properties");
			
			try {
				createProperties();
				loadProperties();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}

	private static void loadProperties()throws Exception {
		Properties prop = new Properties();
		
			// load a properties file

			prop.load(new FileInputStream("CircusOfShapes.properties"));

			// get the property value and print it out
			Class<?> cls = null;
			for (int i = 0; i < 8; i++) {
				try {
					// System.out.println("Default"+i+" "+prop.getProperty("Default"+i).replace(".class",
					// ""));
					cls = Class.forName(prop.getProperty("Default" + i));
					System.out.println("loaded " + cls.getName());
					if (cls != null)
						addClassToAvailClasses(cls);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					System.out.println("forname problem");
				
				}

			}
			int n=0;
			while(prop.containsKey("extra"+n)){
				
				try {
					
//					System.out.println("loading extra name at n"+n+" "+prop.getProperty("extra"+n));
//					System.out.println("loading extra path "+prop.getProperty("path"+n));
					DynamicLoader.addClass(new File(prop.getProperty("path"+n)), prop.getProperty("extra"+n).replace("Model.", ""),true);
				} catch (Exception e) {
					// TODO Auto-generated catch block
//					e.printStackTrace();
					System.out.println("extra class "+prop.getProperty("extra"+n) +" couldn't be loaded at start up");
					
				}
				
				
				n++;
			}

		
			

		
	}

	private static void createProperties() {
		Properties prop = new Properties();
		String[] defaultNames = { "Model.BallShapeBlue", "Model.BallShapeGreen", "Model.BallShapeRed", "Model.BallShapeYellow", "Model.PlateShapeBlue", "Model.PlateShapeGreen", "Model.PlateShapeRed", "Model.PlateShapeYellow" };
		try {
			// set the properties value
			for (int i = 0; i < 8; i++) {
				prop.setProperty("Default" + i, defaultNames[i]);
			}
			// prop.setProperty("koko",BallShapeBlue.class.getName());
			// save properties to project root folder
			prop.store(new FileOutputStream("CircusOfShapes.properties"), null);

		} catch (IOException ex2) {
			// System.out.println("can't save properties");

		}
	}

	public static void main(String[] args) {
		loadAvailableShapes();
		// populateFreeShapeList();
		// System.out.println(ShapePool.getShapePoolInstance().getFreeShapesList().get(0).getShapeName());
		// for(int i = 0; i <
		// ShapePool.getShapePoolInstance().getFreeShapesList().size(); i++)
		// {
		//
		// }
	}

	/**
	 * @return the path
	 */
	public static String getPath() {
		return path;
	}

	/**
	 * @param path
	 *            the path to set
	 */
	public static void setPath(String path) {
		ListLogic.path = path;
	}

	public static void addClassToAvailClasses(Class newClass) {
		// System.out.println("addClass "+newClass.getName());
		try {
			availableClasses.add(newClass);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("problem in addClasstoAvailClasses, cause : " + e.toString());
			e.printStackTrace();
		}
		availableClassesNames.add(newClass.getName());
	}

	public static void addClassToAvailClassesNames(String newClassName) {

		availableClassesNames.add(newClassName);
	}

	public static ArrayList<Class<?>> getAvailClasses() {
		return availableClasses;
	}

	public static ArrayList<String> getAvailableClassesNames() {
		return availableClassesNames;
	}

	public static void addClasstoConfigFile(String clsName,String path) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		Properties prop = new Properties();
		prop.load(new FileInputStream("CircusOfShapes.properties"));
		
		int n=0;
		
		while(prop.containsKey("extra"+n)){
			n++;
		}
		
		prop.put("extra"+n, clsName);
		prop.put("path"+n, path);
		
		prop.store(new FileOutputStream("CircusOfShapes.properties"), null);
	}

}
