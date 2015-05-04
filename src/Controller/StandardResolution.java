package Controller;

import Model.PlayerOne;
import Model.PlayerTwo;
import View.Panel;

public class StandardResolution {
	private int panelWidth = 0;
	private int panelHeight = 0;
	private int shapeWidth = 0;
	private int shapeHeight = 0;
	private int longQueueLength = 0;
	private int shortQueueLength = 0;
	private int queueHeight = 0;
	private int playerWidth = 0;
	private int playerHeight = 0;
	private static StandardResolution screenRes;

	private StandardResolution()
	{
		panelWidth = Panel.getPanelInstance().getWidth();
		panelHeight = Panel.getPanelInstance().getHeight();
	}
	public static StandardResolution getStandardResolutionInstance()
	{
		if(screenRes == null)
		{
			screenRes = new StandardResolution();
		}
		return screenRes;
	}
	public void adjustPanelParameters(int width, int height)
	{
		panelWidth = width;
		panelHeight = height;
		adjustImages();
		updateImages();
	}
	public void adjustImages()
	{
		 shapeWidth = panelWidth/12;
		  while(shapeWidth%12!=0)
		  {
		    shapeWidth--;
		  }
		  longQueueLength = shapeWidth*5;
		  shortQueueLength = shapeWidth*2;
		  playerWidth = (int) 1.5*panelWidth/5;
		  playerHeight = (int) 2*panelHeight/5;
//		longQueueLength =(int) ((double)panelWidth/2.5);
//		while(longQueueLength%5!=0)
//		{
//			longQueueLength--;
//		}
//		shortQueueLength =(int) longQueueLength/2;
//		queueHeight = (int) panelHeight / 14;
//		shapeWidth = (int) longQueueLength/ 5;
//		//System.out.println(shapeWidth);
//		playerWidth = (int) panelWidth/5;
//		playerHeight = (int) panelHeight/5;
	}
	public void updateImages()
	 {
	  System.out.println("Updating");
	  //System.out.println(ShapePool.getShapePoolInstance().getUsedShapesList().size()+ShapePool.getShapePoolInstance().getFreeShapesList().size());
	  //System.out.println(ShapePool.getShapePoolInstance().getUsedShapesList().size());
	  for(int i = 0; i < ShapePool.getShapePoolInstance().getUsedShapesList().size(); i++)
	  {
	   //System.out.println(ShapePool.getShapePoolInstance().getUsedShapesList().get(i).getOriginlImage().getHeight(null));
	   //System.out.println(ShapePool.getShapePoolInstance().getUsedShapesList().get(i).getOriginlImage().getWidth(null));
	   double ratio = (double)ShapePool.getShapePoolInstance().getUsedShapesList().get(i).getOriginlImage().getHeight(null)/(double)ShapePool.getShapePoolInstance().getUsedShapesList().get(i).getOriginlImage().getWidth(null);
	   if(ratio > 0.5)
	   {
	    ShapePool.getShapePoolInstance().getUsedShapesList().get(i).setImg(ShapePool.getShapePoolInstance().getUsedShapesList().get(i).getOriginlImage().getScaledInstance(shapeWidth/3,  (int)((double)shapeWidth*ratio/3), 0));
	   }
	   else
	   {
	    ShapePool.getShapePoolInstance().getUsedShapesList().get(i).setImg(ShapePool.getShapePoolInstance().getUsedShapesList().get(i).getOriginlImage().getScaledInstance(shapeWidth,  (int)((double)shapeWidth*ratio), 0));
	   }
	   
	   GameLoop.getInstance().setPanelIsResized(false);
	   //System.out.println("The Ratio is "+ratio);
	   //ShapePool.getShapePoolInstance().getUsedShapesList().get(i).setImg(ShapePool.getShapePoolInstance().getUsedShapesList().get(i).getOriginlImage().getScaledInstance(shapeWidth,  (int)((double)shapeWidth*ratio), 0));
	   //ShapePool.getShapePoolInstance().getUsedShapesList().get(i).setHeight();
//	   if(ShapePool.getShapePoolInstance().getUsedShapesList().get(i).getImg().getHeight(null)<1)
//	   {
//	    ShapePool.getShapePoolInstance().getUsedShapesList().get(i).setImg(ShapePool.getShapePoolInstance().getUsedShapesList().get(i).getOriginlImage().getScaledInstance(shapeWidth,  1, 0));
//	   }
	  }
	  //System.out.println(playerWidth + " x " + playerHeight);
	  PlayerOne.getInstance().setImg(PlayerOne.getInstance().getOriginalImg().getScaledInstance(playerWidth, playerHeight, 0));
	  PlayerOne.getInstance().setyPos(panelHeight-playerHeight);
	  PlayerTwo.getInstance().setImg(PlayerTwo.getInstance().getOriginalImg().getScaledInstance(playerWidth, playerHeight, 0));
	  PlayerTwo.getInstance().setyPos(panelHeight-playerHeight);
	 }
	
	/**
	 * @return the panelWidth
	 */
	public int getPanelWidth() {
		return panelWidth;
	}
	/**
	 * @param panelWidth the panelWidth to set
	 */
	public void setPanelWidth(int panelWidth) {
		this.panelWidth = panelWidth;
	}
	/**
	 * @return the panelHeight
	 */
	public int getPanelHeight() {
		return panelHeight;
	}
	/**
	 * @param panelHeight the panelHeight to set
	 */
	public void setPanelHeight(int panelHeight) {
		this.panelHeight = panelHeight;
	}
	/**
	 * @return the shapeWidth
	 */
	public int getShapeWidth() {
		return shapeWidth;
	}
	/**
	 * @param shapeWidth the shapeWidth to set
	 */
	public void setShapeWidth(int shapeWidth) {
		this.shapeWidth = shapeWidth;
	}
	/**
	 * @return the shapeHeight
	 */
	public int getShapeHeight() {
		return shapeHeight;
	}
	/**
	 * @param shapeHeight the shapeHeight to set
	 */
	public void setShapeHeight(int shapeHeight) {
		this.shapeHeight = shapeHeight;
	}
	
	/**
	 * @return the queueHeight
	 */
	public int getQueueHeight() {
		return queueHeight;
	}
	/**
	 * @param queueHeight the queueHeight to set
	 */
	public void setQueueHeight(int queueHeight) {
		this.queueHeight = queueHeight;
	}
	/**
	 * @return the playerWidth
	 */
	public int getPlayerWidth() {
		return playerWidth;
	}
	/**
	 * @param playerWidth the playerWidth to set
	 */
	public void setPlayerWidth(int playerWidth) {
		this.playerWidth = playerWidth;
	}
	/**
	 * @return the playerHeight
	 */
	public int getPlayerHeight() {
		return playerHeight;
	}
	/**
	 * @param playerHeight the playerHeight to set
	 */
	public void setPlayerHeight(int playerHeight) {
		this.playerHeight = playerHeight;
	}
	/**
	 * @return the longQueueLength
	 */
	public int getLongQueueLength() {
		return longQueueLength;
	}
	/**
	 * @param longQueueLength the longQueueLength to set
	 */
	public void setLongQueueLength(int longQueueLength) {
		this.longQueueLength = longQueueLength;
	}
	/**
	 * @return the shortQueueLength
	 */
	public int getShortQueueLength() {
		return shortQueueLength;
	}
	/**
	 * @param shortQueueLength the shortQueueLength to set
	 */
	public void setShortQueueLength(int shortQueueLength) {
		this.shortQueueLength = shortQueueLength;
	}
}
