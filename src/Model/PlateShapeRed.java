package Model;

import java.awt.Shape;
import java.awt.geom.Rectangle2D;

public class PlateShapeRed extends RandShape {
	private final static int color = 1;
	private final static int type = 1;
	private final static String shapeName = "PlateShapeRed";
	public PlateShapeRed()
	{
		super(shapeName, 0, 0, color, type);
	}
	
	@Override
	public RandShape clone() {
		RandShape newSquare = new PlateShapeRed();
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
