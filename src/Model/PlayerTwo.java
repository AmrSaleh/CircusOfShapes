package Model;

import java.awt.Image;
import java.io.File;
import java.util.LinkedList;

import javax.imageio.ImageIO;

public class PlayerTwo {
	private int xPos;
	private int yPos;
	private boolean leftListFull = false;
	private boolean rightListFull = false;
	private int maxYLeft = 2500;
	private int maxYRight = 2500;
	private int score;
	private LinkedList<RandShape> rightHandList;
	private LinkedList<RandShape> leftHandList;
	private Image img;
	private Image originalImg;
	private String nameOfImg = "Player\\p2.png";
	private int lengthOfBumper;
	private int rightOldClearance = 0;
	private int rightNewClearance = 10;
	private int leftOldClearance = 0;
	private int leftNewClearance = 10;
	private static PlayerTwo p2;
	private boolean gameOver = false;
	private PlayerTwo()
	{
		try {
			this.originalImg= ImageIO.read(new File(nameOfImg));
			img=originalImg;
		} catch (Exception e) {
			System.out.println("Can't load Player 2 img.");
		}
		rightHandList = new LinkedList<RandShape>();
		leftHandList = new LinkedList<RandShape>();
	}
	public static PlayerTwo getInstance()
	{
		if(p2 == null)
		{
			p2 = new PlayerTwo();
		}
		return p2;

	}
	public void reset()
	{
		rightHandList = new LinkedList<RandShape>();
		leftHandList = new LinkedList<RandShape>();
		img=originalImg;
		xPos=0;
		yPos=0;
		leftListFull = false;
		rightListFull = false;
		maxYLeft = 2500;
		maxYRight = 2500;
		score=0;
		rightOldClearance = 0;
		rightNewClearance = 10;
		leftOldClearance = 0;
		leftNewClearance = 10;
		gameOver=false;
	}
	/**
	 * @return the xPos
	 */
	public int getxPos() {
		return xPos;
	}
	/**
	 * @param xPos the xPos to set
	 */
	public void setxPos(int xPos) {
		this.xPos = xPos;
	}
	/**
	 * @return the yPos
	 */
	public int getyPos() {
		return yPos;
	}
	/**
	 * @param yPos the yPos to set
	 */
	public void setyPos(int yPos) {
		this.yPos = yPos;
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
	 * @return the originalImg
	 */
	public Image getOriginalImg() {
		return originalImg;
	}
	/**
	 * @param originalImg the originalImg to set
	 */
	public void setOriginalImg(Image originalImg) {
		this.originalImg = originalImg;
	}
	/**
	 * @return the nameOfImg
	 */
	public String getNameOfImg() {
		return nameOfImg;
	}
	/**
	 * @param nameOfImg the nameOfImg to set
	 */
	public void setNameOfImg(String nameOfImg) {
		this.nameOfImg = nameOfImg;
	}
	/**
	 * @return the lengthOfTable
	 */
	
	/**
	 * @return the p1
	 */
	public int getWidth()
	{
		return img.getWidth(null);
	}
	public int getHeight()
	{
		return img.getHeight(null);
	}
	/**
	 * @return the lengthOfBumper
	 */
	public int getLengthOfBumper() {
		return lengthOfBumper;
	}
	/**
	 * @param lengthOfBumper the lengthOfBumper to set
	 */
	public void setLengthOfBumper(int lengthOfBumper) {
		this.lengthOfBumper = lengthOfBumper;
	}
	/**
	 * @return the rightHandList
	 */
	public LinkedList<RandShape> getRightHandList() {
		return rightHandList;
	}
	/**
	 * @param rightHandList the rightHandList to set
	 */
	public void setRightHandList(LinkedList<RandShape> rightHandList) {
		this.rightHandList = rightHandList;
	}
	/**
	 * @return the leftHandList
	 */
	public LinkedList<RandShape> getLeftHandList() {
		return leftHandList;
	}
	/**
	 * @param leftHandList the leftHandList to set
	 */
	public void setLeftHandList(LinkedList<RandShape> leftHandList) {
		this.leftHandList = leftHandList;
	}
	public int[] getCenterOfWeight()
	{
		lengthOfBumper = img.getWidth(null)/3;
		int[] centers = {xPos+lengthOfBumper/2, xPos+getWidth()-lengthOfBumper/2};
		return centers;
	}
	public void moveShapes()
	{
		checkScore();
		int[] centers = this.getCenterOfWeight();
		for(int i = 0; i < leftHandList.size(); i++)
		{		
			leftHandList.get(i).setXPos(centers[0]-leftHandList.get(i).getWidth()/2);
		}
		for(int i = 0; i < rightHandList.size(); i++)
		{
			rightHandList.get(i).setXPos(centers[1]-rightHandList.get(i).getWidth()/2);
		}
	}
	/**
	 * @return the oldClearance
	 */
	/**
	 * @return the rightOldClearance
	 */
	public int getRightOldClearance() {
		return rightOldClearance;
	}
	/**
	 * @param rightOldClearance the rightOldClearance to set
	 */
	public void setRightOldClearance(int rightOldClearance) {
		this.rightOldClearance = rightOldClearance;
	}
	/**
	 * @return the rightNewClearance
	 */
	public int getRightNewClearance() {
		return rightNewClearance;
	}
	/**
	 * @param rightNewClearance the rightNewClearance to set
	 */
	public void setRightNewClearance(int rightNewClearance) {
		this.rightNewClearance = rightNewClearance;
	}
	/**
	 * @return the leftOldClearance
	 */
	public int getLeftOldClearance() {
		return leftOldClearance;
	}
	/**
	 * @param leftOldClearance the leftOldClearance to set
	 */
	public void setLeftOldClearance(int leftOldClearance) {
		this.leftOldClearance = leftOldClearance;
	}
	/**
	 * @return the leftNewClearance
	 */
	public int getLeftNewClearance() {
		return leftNewClearance;
	}
	/**
	 * @param leftNewClearance the leftNewClearance to set
	 */
	public void setLeftNewClearance(int leftNewClearance) {
		this.leftNewClearance = leftNewClearance;
	}
	/**
	 * @return the score
	 */
	public int getScore() {
		return score;
	}
	/**
	 * @param score the score to set
	 */
	public void setScore(int score) {
		this.score = score;
	}
	public void checkScore()
	{
		for(int i = 0; i < rightHandList.size()-2; i++)
		{
			
			if(rightHandList.get(i).getColor()==rightHandList.get(i+1).getColor()&&
					rightHandList.get(i+1).getColor()==rightHandList.get(i+2).getColor())
			{
				score++;
				for(int j = i+3; j <rightHandList.size(); j++)
				{
					rightHandList.get(j).setYPos(
							rightHandList.get(j).getYPos()
							-rightHandList.get(i).getHeight()
							-rightHandList.get(i+1).getHeight()
							-rightHandList.get(i+2).getHeight());
				}
				rightOldClearance=rightOldClearance
						-rightHandList.get(i).getHeight()
						-rightHandList.get(i+1).getHeight()
						-rightHandList.get(i+2).getHeight();
				rightNewClearance = rightOldClearance+10;
				rightHandList.remove(i);
				rightHandList.remove(i);
				rightHandList.remove(i);
				i=i-1;
						
			}
			if(i>=0&&i<rightHandList.size()&&rightHandList.get(i).getYPos()<maxYRight)
			{
				maxYRight = rightHandList.get(i).getYPos();
			}
		}
		for(int i = 0; i < leftHandList.size()-2; i++)
		{
			
			if(leftHandList.get(i).getColor()==leftHandList.get(i+1).getColor()&&
					leftHandList.get(i+1).getColor()==leftHandList.get(i+2).getColor())
			{
				score++;
				for(int j = i+3; j <leftHandList.size(); j++)
				{
					leftHandList.get(j).setYPos(
							leftHandList.get(j).getYPos()
							-leftHandList.get(i).getHeight()
							-leftHandList.get(i+1).getHeight()
							-leftHandList.get(i+2).getHeight());
				}
				leftOldClearance=leftOldClearance
						-leftHandList.get(i).getHeight()
						-leftHandList.get(i+1).getHeight()
						-leftHandList.get(i+2).getHeight();
				leftNewClearance = leftOldClearance+10;
				leftHandList.remove(i);
				leftHandList.remove(i);
				leftHandList.remove(i);
				i=i-1;
						
			}
			if(i>=0&&i<leftHandList.size()&&leftHandList.get(i).getYPos()<maxYLeft)
			{
				maxYLeft = leftHandList.get(i).getYPos();
			}
		}
		if(maxYLeft<100 && maxYRight <100)
		{
			gameOver=true;
		}
	}
	/**
	 * @return the gameOver
	 */
	public boolean isGameOver() {
		return gameOver;
	}
	/**
	 * @param gameOver the gameOver to set
	 */
	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}
	public void obligatoryMovement()
	{
		if(xPos<2000)
		{
			xPos = xPos + 20;
		}
		moveShapes();
	}
	/**
	 * @return the maxYLeft
	 */
	public int getMaxYLeft() {
		return maxYLeft;
	}
	/**
	 * @param maxYLeft the maxYLeft to set
	 */
	public void setMaxYLeft(int maxYLeft) {
		this.maxYLeft = maxYLeft;
	}
	/**
	 * @return the maxYRight
	 */
	public int getMaxYRight() {
		return maxYRight;
	}
	/**
	 * @param maxYRight the maxYRight to set
	 */
	public void setMaxYRight(int maxYRight) {
		this.maxYRight = maxYRight;
	}
	/**
	 * @return the leftListFull
	 */
	public boolean isLeftListFull() {
		return leftListFull;
	}
	/**
	 * @param leftListFull the leftListFull to set
	 */
	public void setLeftListFull(boolean leftListFull) {
		this.leftListFull = leftListFull;
	}
	/**
	 * @return the rightListFull
	 */
	public boolean isRightListFull() {
		return rightListFull;
	}
	/**
	 * @param rightListFull the rightListFull to set
	 */
	public void setRightListFull(boolean rightListFull) {
		this.rightListFull = rightListFull;
	}
	
	public String serialize() {
		String s = "";
		s = s + (xPos + "");
		s = s + ("," + yPos + "");
		s = s + "," + leftListFull;
		s = s + "," + rightListFull;
		s = s + ("," + maxYLeft + "");
		s = s + ("," + maxYRight + "");
		s = s + ("," + score + "");
		s = s + ("," + lengthOfBumper + "");
		s = s + ("," + rightOldClearance + "");
		s = s + ("," + rightNewClearance + "");
		s = s + ("," + leftOldClearance + "");
		s = s + ("," + leftNewClearance + "");
		s = s + "," + gameOver;

		s = s + "&";
		for (int i = 0; i < rightHandList.size(); i++) {
			s = s + rightHandList.get(i).serialize();

			if (i != rightHandList.size() - 1) {
				s = s + "III";
			}
		}
		s = s + "&";
		for (int i = 0; i < leftHandList.size(); i++) {
			s = s + leftHandList.get(i).serialize();

			if (i != leftHandList.size() - 1) {
				s = s + "III";
			}
		}
		return s;
	}

	public void deserializeAndSet(String s) {
		String[] sections = s.split("&", -1);

		String[] props = sections[0].split(",", -1);
		String[] rightShapesStrings = sections[1].split("III", -1);
		String[] leftShapesStrings = sections[2].split("III", -1);

		setxPos(Integer.parseInt(props[0]));
		setyPos(Integer.parseInt(props[1]));
		setLeftListFull(Boolean.parseBoolean(props[2]));
		setRightListFull(Boolean.parseBoolean(props[3]));
		setMaxYLeft(Integer.parseInt(props[4]));
		setMaxYRight(Integer.parseInt(props[5]));
		setScore(Integer.parseInt(props[6]));
		setLengthOfBumper(Integer.parseInt(props[7]));
		setRightOldClearance(Integer.parseInt(props[8]));
		setRightNewClearance(Integer.parseInt(props[9]));
		setLeftOldClearance(Integer.parseInt(props[10]));
		setLeftNewClearance(Integer.parseInt(props[11]));
		setGameOver(Boolean.parseBoolean(props[12]));

		LinkedList<RandShape> right = new LinkedList<RandShape>();

		for (int i = 0; i < rightShapesStrings.length; i++) {
			if(!rightShapesStrings[i].equals("")){
			right.add(i, RandShape.deserializeAndGetObject(rightShapesStrings[i]));
			}
		}

		setRightHandList(right);

		LinkedList<RandShape> left = new LinkedList<RandShape>();

		for (int i = 0; i < leftShapesStrings.length; i++) {
			if(!leftShapesStrings[i].equals("")){
			left.add(i, RandShape.deserializeAndGetObject(leftShapesStrings[i]));
			}
		}

		setLeftHandList(left);

	}
}
