package Controller;

import java.util.LinkedList;

import Model.RandShape;

public class ShapePool {
	private LinkedList<RandShape> freeShapesList;
	private LinkedList<RandShape> usedShapesList;
	private int maximumLength;
	private static ShapePool objectPool;
	private ShapePool()
	{
		freeShapesList = new LinkedList<RandShape>();
		usedShapesList = new LinkedList<RandShape>();
		maximumLength = 100;
	}
	public static ShapePool getShapePoolInstance()
	{
		if(objectPool == null)
		{
			objectPool = new ShapePool();
		}
		return objectPool;
	}
	/**
	 * @return the freeShapesList
	 */
	public LinkedList<RandShape> getFreeShapesList() {
		return freeShapesList;
	}
	/**
	 * @param freeShapesList the freeShapesList to set
	 */
	public void setFreeShapesList(LinkedList<RandShape> freeShapesList) {
		this.freeShapesList = freeShapesList;
	}
	/**
	 * @return the usedShapesList
	 */
	public LinkedList<RandShape> getUsedShapesList() {
		return usedShapesList;
	}
	/**
	 * @param usedShapesList the usedShapesList to set
	 */
	public void setUsedShapesList(LinkedList<RandShape> usedShapesList) {
		this.usedShapesList = usedShapesList;
	}
	/**
	 * @return the maximumLength
	 */
	public int getMaximumLength() {
		return maximumLength;
	}
	/**
	 * @param maximumLength the maximumLength to set
	 */
	public void setMaximumLength(int maximumLength) {
		this.maximumLength = maximumLength;
	}
	public void useShape (int x, int y, int direction)
	{
		
		if(!freeShapesList.isEmpty())
		{
			
			freeShapesList.get(0).setXPos(x);
			freeShapesList.get(0).setYPos(y);
			freeShapesList.get(0).setDirection(direction);
//			double ratio = (double)freeShapesList.get(0).getOriginlImage().getHeight(null)/
//					(double)freeShapesList.get(0).getOriginlImage().getWidth(null);
//			//System.out.println(ratio);
//			System.out.println(freeShapesList.get(0).getWidth());
//			freeShapesList.get(0).setImg(freeShapesList.get(0).getOriginlImage().getScaledInstance
//					(StandardResolution.getStandardResolutionInstance().getShapeWidth(),
//							(int)((double)StandardResolution.getStandardResolutionInstance().getShapeWidth()*ratio), 0));
//			System.out.println(freeShapesList.get(0).getWidth());
//			System.out.println(freeShapesList.get(0).getHeight());
			//System.out.println(freeShapesList.get(0).getYPos());
			usedShapesList.add(freeShapesList.remove());
			
		}
		else
		{
			System.out.println("Free shapes list is empty!");
		}
	}
	public void releaseShape (int index)
	{
		if(!usedShapesList.isEmpty())
		{
			usedShapesList.get(index).setXPos(0);
			usedShapesList.get(index).setYPos(0);
			usedShapesList.get(index).setDirection(0);
			usedShapesList.get(index).setImg(usedShapesList.get(index).getOriginlImage());
			freeShapesList.add(usedShapesList.remove(index));
		}
		else
		{
			System.out.println("Used shapes list is empty!");
		}
	}
	public void resetShape(int i) {
		// TODO Auto-generated method stub
		usedShapesList.get(i).resetParameters();
		
	}
	public void initialize() {
		// TODO Auto-generated method stub
		while(!usedShapesList.isEmpty()){
			releaseShape(0);
		}
	}
	
	public void useShape (RandShape theNewShape)
	{
		
		if(!freeShapesList.isEmpty())
		{
			
			
//			freeShapesList.remove();
//			usedShapesList.add(theNewShape);
//			System.out.println("the new shape : "+theNewShape);
			
		}
		else
		{
			System.out.println("Free shapes list is empty!");
		}
	}

}
