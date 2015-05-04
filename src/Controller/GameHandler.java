package Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

import Model.PlayerOne;
import Model.PlayerTwo;
import Model.RandShape;
import View.Panel;

public class GameHandler {
	public static boolean saveGame(String gameSaveName)
	{
		LinkedList<RandShape> used= ShapePool.getShapePoolInstance().getUsedShapesList();
		
		ArrayList<String> data=new ArrayList<String>();
		data.add("");data.add("");data.add("");
//		String pool="";
//		String player1="";
//		String player2="";
		
		for (int i = 0; i < used.size(); i++) {
			data.set(0,data.get(0) +used.get(i).serialize()) ;
			
			if(i!=used.size()-1){
				data.set(0,data.get(0) +"III") ;
			}
		}
		
		data.set(1, PlayerOne.getInstance().serialize());
		data.set(2, PlayerTwo.getInstance().serialize());
		
		try {
			FileHandler.saveGameToFile(gameSaveName, data);
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			System.out.println("saving failed");
		}
		
		
		return true;
	}
	public static boolean loadGame(String gameSaveName)
	{
		try {
			ArrayList<String> loadedData=FileHandler.load(gameSaveName);
			GameLoop theGameLoop = GameLoop.getInstance();
			theGameLoop.reset();
			RandShape temp;
			
			String[] shapesStrings = loadedData.get(0).split("III", -1);
			for (int i = 0; i < shapesStrings.length; i++) {
				if(!shapesStrings[i].equals("")){
				temp=RandShape.deserializeAndGetObject(shapesStrings[i]);
				theGameLoop.useShape(temp);
				}
			}
			
			PlayerOne.getInstance().deserializeAndSet(loadedData.get(1));
			PlayerTwo.getInstance().deserializeAndSet(loadedData.get(2));

			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			System.out.println("loading failed");
		} catch (IOException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			System.out.println("loading failed");
		}
		
		return true;
	}

}
