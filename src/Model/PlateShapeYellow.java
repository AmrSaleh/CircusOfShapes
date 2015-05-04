package Model;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;

public class PlateShapeYellow extends RandShape {
	private final static int color = 2;
	private final static int type = 1;
	private final static String shapeName = "PlateShapeYellow";
	public PlateShapeYellow()
	{
		super(shapeName, 0, 0, color, type);
	}
	
	@Override
	public RandShape clone() {
		RandShape newSquare = new PlateShapeYellow();
		return newSquare;
	}
	
	@Override
	public boolean contain(double x, double y)
	{
		Shape rectangle = new Rectangle2D.Double(getXPos(), getYPos(), getWidth(), getHeight());
		if (rectangle.contains(x, y)) {
			return true;
		}
		return false;
	}

}
