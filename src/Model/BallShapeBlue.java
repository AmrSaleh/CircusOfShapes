package Model;

import java.awt.Shape;
import java.awt.geom.Ellipse2D;

public class BallShapeBlue extends RandShape {
	private final static int color = 4;
	private final static int type = 2;
	private final static String shapeName = "BallShapeBlue";
	public BallShapeBlue()
	{
		super(shapeName, 0, 0, color, type);
	}
	
	@Override
	public RandShape clone() {
		RandShape newSquare = new BallShapeBlue();
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
