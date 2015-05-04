package Model;


import java.awt.Image;
import java.awt.Shape;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class RandShape {
	
	
	private transient Image img;
	private transient Image OriginalImage;
	private String shapeName;
	private int xPos;
	private int yPos;
	private int color;
	private int type;
	private int state;
	private int direction;
	public RandShape() {
		this.setImg(null);
		this.setShapeName(null);
		this.xPos = 0;
		this.yPos = 0;
		
		this.color = 0;
		this.type = 0;
		this.state = 0;
		this.direction = 0;
	}

//	public RandShape(int xPos, int yPos, int width, Color color, int type) {
//		this.xPos = xPos;
//		this.yPos = yPos;
//		this.width = width;
//		this.height = width;
//		this.color = color;
//		this.type = type;
//		this.state = 0;
//	}

	public RandShape(String shapeName, int xPos, int yPos, int color, int type) {
		try {
			//MediaTracker tracker = new MediaTracker(null);
			//tracker.addImage(img, 1);
//			this.img =  ImageIO.read(new File(Controller.ListLogic.getPath()+shapeName+".png"));
			this.OriginalImage= ImageIO.read(new File(Controller.ListLogic.getPath()+shapeName+".png"));
			img=OriginalImage;
			//tracker.waitForAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			try {
//				this.img = ImageIO.read(new File(Controller.ListLogic.getPath()+"BallShapeBlue.png"+".png"));
				this.OriginalImage= ImageIO.read(new File(Controller.ListLogic.getPath()+shapeName+".png"));
				img=OriginalImage;
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//e.printStackTrace();
		};
		this.shapeName=shapeName;
		this.xPos = xPos;
		this.yPos = yPos;
		
		this.color = color;
		this.type = type;
		this.state = 0;
		this.direction = 0;
	}
//	public int numOfResizePoints()
//	{
//		return 0;
//	}
//	public Shape resizePoints(int n)
//	{
//		return null;
//	}

	public boolean contain(double x, double y) {
		//System.out.println("Default cotain");
		return false;
	}

	public Shape drawShape() {
		return null;
	}

	public int getColor() {
		return color;
	}

	public int getHeight() {
		return img.getHeight(null);
	}

	public int getWidth() {
		return img.getWidth(null);
	}

	public int getXPos() {
		return xPos;
	}

	public int getYPos() {
		return yPos;
	}

	public void setColor(int color) {
		this.color = color;
	}


	public void setXPos(int xPos) {
		this.xPos = xPos;
	}

	public void setYPos(int yPos) {
		this.yPos = yPos;
	}

	@Override
	public RandShape clone(){
		return null;
	}

	public void edit() {
		//System.out.println("I feel the fail");
		// TODO Auto-generated method stub
		
	}
	
//	@Override
//	public String toString()
//	{
//		String s = "";
//		s=(getImg().toString() + " " + getXPos() + " " + getYPos() + " " +getWidth() + " " +getHeight() + " " +getColor() + " " + getType());
//		return s;
//	}

	/**
	 * @return the type
	 */
	public int getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(int type) {
		this.type = type;
	}

	/**
	 * @return the state
	 */
	public int getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(int state) {
		this.state = state;
	}

	/**
	 * @return the img
	 */
	public Image getImg() {
		return img;
	}

	/**
	 * @param img the img to set
	 */
	public void setImg(Image img) {
		this.img = img;
	}

	/**
	 * @return the shapeName
	 */
	public String getShapeName() {
		return shapeName;
	}

	/**
	 * @param shapeName the shapeName to set
	 */
	public void setShapeName(String shapeName) {
		this.shapeName = shapeName;
	}

	/**
	 * @return the direction
	 */
	public int getDirection() {
		return direction;
	}

	/**
	 * @param direction the direction to set
	 */
	public void setDirection(int direction) {
		this.direction = direction;
	}

	public Image getOriginlImage(){
		return OriginalImage;
	}

	public void resetParameters() {
		// TODO Auto-generated method stub
		img=OriginalImage;
		this.xPos = 0;
		this.yPos = 0;
		this.state = 0;
		this.direction = 0;
		
		
	}
	
	public String serialize(){
		String s=this.getShapeName()+","+this.getXPos()+","+this.getYPos()+","+this.getWidth()+","+this.getHeight();
		return s;
	}
	
	public static RandShape deserializeAndGetObject(String s){
		String[] props = s.split(",", -1);
		
		RandShape newShape=null;
		
		if (props[0].equals("BallShapeBlue")) {
			newShape = new BallShapeBlue();
		}else if (props[0].equals("BallShapeGreen")) {
			newShape = new BallShapeGreen();
		}else if (props[0].equals("BallShapeRed")) {
			newShape = new BallShapeRed();
		}else if (props[0].equals("BallShapeYellow")) {
			newShape = new BallShapeYellow();
		}else if (props[0].equals("PlateShapeBlue")) {
			newShape = new PlateShapeBlue();
		}else if (props[0].equals("PlateShapeGreen")) {
			newShape = new PlateShapeGreen();
		}else if (props[0].equals("PlateShapeRed")) {
			newShape = new PlateShapeRed();
		}else if (props[0].equals("PlateShapeYellow")) {
			newShape = new PlateShapeYellow();
		}
		System.out.println(props[0]);
		try {
			System.out.println(Integer.parseInt(props[1]));
			newShape.setXPos(Integer.parseInt(props[1]));
			newShape.setYPos(Integer.parseInt(props[2]));
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
		}
		
		newShape.setImg(newShape.getOriginlImage().getScaledInstance(Integer.parseInt(props[3]), Integer.parseInt(props[4]), 0));
		
		
		return newShape;
	}
	
//	public String
	
}
