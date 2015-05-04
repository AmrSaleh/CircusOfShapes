package Model;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;

public class BallShapeRed extends RandShape {
	private final static int color = 1;
	private final static int type = 2;
	private final static String shapeName = "BallShapeRed";
	public BallShapeRed()
	{
		super(shapeName, 0, 0, color, type);
	}
	
	@Override
	public RandShape clone() {
		RandShape newSquare = new BallShapeRed();
		return newSquare;
	}
	
	@Override
	public boolean contain(double x, double y)
	{
		Shape circle = new Ellipse2D.Double(getXPos(), getYPos(), getWidth(), getHeight());
		if (circle.contains(x, y)) {
			return true;
		}
		return false;
	}

}
