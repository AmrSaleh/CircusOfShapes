package Controller;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class FileHandler {
	
		
	    
	    
	    public static void saveGameToFile(String FileLocation, ArrayList<String> data)throws Exception {
	    	FileOutputStream fos = new FileOutputStream(FileLocation);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			
			oos.writeObject(data);
			
			fos.close();
			
	    }
	    
	    
	    public static ArrayList<String> load(String FileLocation) throws IOException, ClassNotFoundException
		{
			FileInputStream fis = new FileInputStream(FileLocation);
			ObjectInputStream oin = new ObjectInputStream(fis);
			Object obj = oin.readObject();
			ArrayList<String> loaded = new ArrayList<String>();
			if (obj instanceof ArrayList) {
			loaded = (ArrayList<String>)obj;
			fis.close();
			}
	 
			return loaded;
		}

}
