package Controller;

import Model.PlayerOne;
import Model.PlayerTwo;
import Model.RandShape;
import View.Panel;

public class GameLoop {

	private static GameLoop myGameLoop;
	private Panel myPanel;
	private PlayerOne p1;
	private PlayerTwo p2;
	private ShapePool pool;
	private boolean isRunning;
	private int ShelfEdge1 = 400;
	private int ShelfEdge2 = 200;
	private Boolean panelIsResized = false;
	private int step = 4;
	private int oldPanelWidth, oldPlateWidth;
	private boolean p1Left = false;
	private boolean p1Right = false;
	private boolean p2Left = false;
	private boolean p2Right = false;
	private MyThreadClass theThreadInstance;
	private Thread thread;

	private GameLoop() {
		// TODO Auto-generated constructor stub
		
		initialize(true);
	}

	public static GameLoop getInstance() {
		if (myGameLoop == null) {
			myGameLoop = new GameLoop();
		}

		return myGameLoop;
	}
	
	public void reset (){
		initialize(false);
	}

	
	public void initialize(boolean isAtStart) {
		isRunning = false;
		myPanel = Panel.getPanelInstance();
		myPanel.reset();
		pool = ShapePool.getShapePoolInstance();
		pool.initialize();
		p1 = PlayerOne.getInstance();
		p2 = PlayerTwo.getInstance();
		p1.reset();p2.reset();
		oldPanelWidth = myPanel.getWidth();
		ListLogic.populateFreeShapeList();
		StandardResolution.getStandardResolutionInstance().adjustPanelParameters(myPanel.getWidth(), myPanel.getHeight());
		oldPlateWidth = StandardResolution.getStandardResolutionInstance().getShapeWidth();
		theThreadInstance = new MyThreadClass();
		thread = new Thread(theThreadInstance);
		p1.setxPos(0);
		p1.setyPos(myPanel.getHeight() - p1.getHeight());
		p2.setxPos(myPanel.getWidth() - p2.getWidth());
		p2.setyPos(myPanel.getHeight() - p2.getHeight());
		
		StandardResolution.getStandardResolutionInstance().adjustPanelParameters(myPanel.getWidth(), myPanel.getHeight());
		
		if (isAtStart) {
			RandShape sample;
			sample = pool.getFreeShapesList().get(0);
			prepareNextShape(sample);
			pool.useShape(0, 5, 1);

			sample = pool.getFreeShapesList().get(0);
			prepareNextShape(sample);
			pool.useShape(0, 50, 2);

			sample = pool.getFreeShapesList().get(0);
			prepareNextShape(sample);
			pool.useShape(myPanel.getWidth() - sample.getWidth(), 5, 3);

			sample = pool.getFreeShapesList().get(0);
			prepareNextShape(sample);
			pool.useShape(myPanel.getWidth() - sample.getWidth(), 50, 4);
		}
		
	}

	public void start() {

		if (!thread.isAlive()) {
			thread = new Thread(theThreadInstance);
		}

		thread.start();

	}

	public void useShape(RandShape shape){
		pool.useShape(shape);
	}
	// public void resume() {
	//
	//
	// thread.resume();
	//
	// }
	public void pause() {

		isRunning = false;
		

	}

	// private void run() {
	// // TODO Auto-generated method stub
	// isRunning = true;
	// while (isRunning) {
	//
	// addNewShapes();
	// update();
	//
	// if (panelIsResized) {
	// StandardResolution.getStandardResolutionInstance().adjustPanelParameters(myPanel.getWidth(),
	// myPanel.getHeight());
	// }
	//
	// myPanel.repaint();
	// // for(int i =0; i <
	// // ShapePool.getShapePoolInstance().getUsedShapesList().size(); i++)
	// // {
	// //
	// System.out.println(ShapePool.getShapePoolInstance().getUsedShapesList().get(i).getXPos());
	// //
	// System.out.println(ShapePool.getShapePoolInstance().getUsedShapesList().get(i).getYPos());
	// // }
	// // System.out.println("Updating");
	// try {
	// Thread.sleep(60);
	// } catch (InterruptedException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	//
	// }
	//
	// }

	private void prepareNextShape(RandShape myShape) {
		StandardResolution.getStandardResolutionInstance().adjustImages();
		// System.out.println(myPanel.getWidth() + "   " + myPanel.getHeight());
		// System.out.println(StandardResolution.getStandardResolutionInstance().getShapeWidth());
		double ratio = (double) myShape.getOriginlImage().getHeight(null) / (double) myShape.getOriginlImage().getWidth(null);
		if (ratio > 0.5) {
			// System.out.println("ratio * width / 3 = " +
			// StandardResolution.getStandardResolutionInstance().getShapeWidth()
			// * ratio / 3);
			myShape.setImg(myShape.getOriginlImage().getScaledInstance(StandardResolution.getStandardResolutionInstance().getShapeWidth() / 3, (int) ((double) StandardResolution.getStandardResolutionInstance().getShapeWidth() * ratio / 3), 0));
		} else {
			// System.out.println("ratio * width / 3 = " +
			// StandardResolution.getStandardResolutionInstance().getShapeWidth()
			// * ratio / 3);
			myShape.setImg(myShape.getOriginlImage().getScaledInstance(StandardResolution.getStandardResolutionInstance().getShapeWidth(), (int) ((double) StandardResolution.getStandardResolutionInstance().getShapeWidth() * ratio), 0));
		}
	}

	private void addNewShapes() {
		// TODO Auto-generated method stub
		RandShape currentShape, sample;
		int iterations = pool.getUsedShapesList().size();
		for (int i = 0; i < iterations; i++) {
			currentShape = pool.getUsedShapesList().get(i);

			if (currentShape.getDirection() == 1) { // upper left

				if (currentShape.getXPos() == step) {
					// System.out.println(i);
					sample = pool.getFreeShapesList().get(0);
					prepareNextShape(sample);
					pool.useShape(-sample.getWidth(), 5, 1);
				}

			} else if (currentShape.getDirection() == 2) {// lower left
				if (currentShape.getXPos() == step) {
					sample = pool.getFreeShapesList().get(0);
					prepareNextShape(sample);
					pool.useShape(-sample.getWidth(), 50, 2);
				}
			} else if (currentShape.getDirection() == 3) {// upper right
				if (currentShape.getXPos() + currentShape.getWidth() == myPanel.getWidth() - step) {
					sample = pool.getFreeShapesList().get(0);
					prepareNextShape(sample);
					pool.useShape(myPanel.getWidth(), 5, 3);
				}
			} else if (currentShape.getDirection() == 4) {// lower right
				if (currentShape.getXPos() + currentShape.getWidth() == myPanel.getWidth() - step) {
					sample = pool.getFreeShapesList().get(0);
					prepareNextShape(sample);
					pool.useShape(myPanel.getWidth(), 50, 4);
				}
			}
			// System.out.println(ShapePool.getShapePoolInstance().getUsedShapesList().size());
			// System.out.println(currentShape.getXPos());
			// System.out.println(currentShape.getYPos());

		}

	}

	private void update() {
		// TODO Auto-generated method stub
		int type1 = 0, type2 = 0, type3 = 0, type4 = 0;
		myPanel.clearDrawList();
		RandShape currentShape, sample;
		// int difference = myPanel.getWidth() - oldPanelWidth;
		for (int i = 0; i < pool.getUsedShapesList().size(); i++) {
			currentShape = pool.getUsedShapesList().get(i);

			if (currentShape.getDirection() == 1) { // upper left
				type1++;
				if (currentShape.getXPos() > ShelfEdge1) {
					currentShape.setYPos(currentShape.getYPos() + 10);
				}
				currentShape.setXPos(currentShape.getXPos() + step);
			} else if (currentShape.getDirection() == 2) {// lower left
				type2++;
				if (currentShape.getXPos() > ShelfEdge2) {
					currentShape.setYPos(currentShape.getYPos() + 10);
				}
				currentShape.setXPos(currentShape.getXPos() + step);
			} else if (currentShape.getDirection() == 3) {// upper right
				type3++;

				// currentShape.setXPos(currentShape.getXPos() + difference);

				if (currentShape.getXPos() + currentShape.getWidth() < myPanel.getWidth() - ShelfEdge1) {
					currentShape.setYPos(currentShape.getYPos() + 10);
				}
				currentShape.setXPos(currentShape.getXPos() - step);
			} else if (currentShape.getDirection() == 4) {// lower right
				type4++;

				// currentShape.setXPos(currentShape.getXPos() + difference);

				if (currentShape.getXPos() + currentShape.getWidth() < myPanel.getWidth() - ShelfEdge2) {
					currentShape.setYPos(currentShape.getYPos() + 10);
				}
				currentShape.setXPos(currentShape.getXPos() - step);
			}

			// oldPanelWidth = myPanel.getWidth();
			// oldPlateWidth=
			// StandardResolution.getStandardResolutionInstance().getShapeWidth();

			// System.out.println(ShapePool.getShapePoolInstance().getUsedShapesList().size());
			// System.out.println(currentShape.getXPos());
			// System.out.println(currentShape.getYPos());
			if (currentShape.getYPos() > myPanel.getHeight()) {
				// pool.resetShape(i);
				pool.releaseShape(i);
				i--;
			} else {
				myPanel.addImageToDrawList(currentShape.getImg(), currentShape.getXPos(), currentShape.getYPos());
			}
		}

		if (type1 == 0) {
			sample = pool.getFreeShapesList().get(0);
			prepareNextShape(sample);
			pool.useShape(0, 5, 1);
		}
		if (type2 == 0) {
			sample = pool.getFreeShapesList().get(0);
			prepareNextShape(sample);
			pool.useShape(0, 50, 2);
		}
		if (type3 == 0) {
			sample = pool.getFreeShapesList().get(0);
			prepareNextShape(sample);
			pool.useShape(myPanel.getWidth() - sample.getWidth(), 5, 3);
		}
		if (type4 == 0) {
			sample = pool.getFreeShapesList().get(0);
			prepareNextShape(sample);
			pool.useShape(myPanel.getWidth() - sample.getWidth(), 50, 4);
		}

	}

	public void checkP1Intersection() {
		p1.getCenterOfWeight();

		for (int i = 0; i < pool.getUsedShapesList().size(); i++) {
			if (p1.getMaxYLeft() > 100) {
				if (pool.getUsedShapesList().get(i).getYPos() + pool.getUsedShapesList().get(i).getHeight() >= (p1.getyPos() - p1.getLeftNewClearance()) && pool.getUsedShapesList().get(i).getYPos() + pool.getUsedShapesList().get(i).getHeight() < (p1.getyPos() - p1.getLeftOldClearance())) {
					if ((pool.getUsedShapesList().get(i).getXPos() + pool.getUsedShapesList().get(i).getWidth() / 2) >= p1.getxPos() && (pool.getUsedShapesList().get(i).getXPos() + pool.getUsedShapesList().get(i).getWidth() / 2) < p1.getxPos() + p1.getLengthOfBumper()) {
						RandShape temp = pool.getUsedShapesList().get(i).clone();
						prepareNextShape(temp);
						temp.setYPos(p1.getyPos() - p1.getLeftOldClearance() - temp.getHeight());
						p1.setLeftOldClearance(p1.getLeftOldClearance() + temp.getHeight());
						p1.setLeftNewClearance(10 + p1.getLeftOldClearance());
						p1.getLeftHandList().add(temp);
						pool.releaseShape(i);
						i--;
						p1.moveShapes();
						continue;
					}
				}
			}
			if (p1.getMaxYRight() > 100) {
				if (pool.getUsedShapesList().get(i).getYPos() + pool.getUsedShapesList().get(i).getHeight() >= (p1.getyPos() - p1.getRightNewClearance()) && pool.getUsedShapesList().get(i).getYPos() + pool.getUsedShapesList().get(i).getHeight() < (p1.getyPos() - p1.getRightOldClearance())) {
					if ((pool.getUsedShapesList().get(i).getXPos() + pool.getUsedShapesList().get(i).getWidth() / 2) >= (p1.getxPos() + p1.getWidth() - p1.getLengthOfBumper()) && (pool.getUsedShapesList().get(i).getXPos() + pool.getUsedShapesList().get(i).getWidth() / 2) < p1.getxPos() + p1.getWidth()) {
						RandShape temp = pool.getUsedShapesList().get(i).clone();
						prepareNextShape(temp);
						temp.setYPos(p1.getyPos() - p1.getRightOldClearance() - temp.getHeight());
						p1.setRightOldClearance(p1.getRightOldClearance() + temp.getHeight());
						p1.setRightNewClearance(10 + p1.getRightOldClearance());
						p1.getRightHandList().add(temp);
						pool.releaseShape(i);
						i--;
						p1.moveShapes();
					}
				}
			}
		}
	}

	public void checkP2Intersection() {
		p2.getCenterOfWeight();
		for (int i = 0; i < pool.getUsedShapesList().size(); i++) {
			if (p2.getMaxYLeft() > 100) {
				if (pool.getUsedShapesList().get(i).getYPos() + pool.getUsedShapesList().get(i).getHeight() >= (p2.getyPos() - p2.getLeftNewClearance()) && pool.getUsedShapesList().get(i).getYPos() + pool.getUsedShapesList().get(i).getHeight() < (p2.getyPos() - p2.getLeftOldClearance())) {
					if ((pool.getUsedShapesList().get(i).getXPos() + pool.getUsedShapesList().get(i).getWidth() / 2) >= p2.getxPos() && (pool.getUsedShapesList().get(i).getXPos() + pool.getUsedShapesList().get(i).getWidth() / 2) < p2.getxPos() + p2.getLengthOfBumper()) {
						RandShape temp = pool.getUsedShapesList().get(i).clone();
						prepareNextShape(temp);
						temp.setYPos(p2.getyPos() - p2.getLeftOldClearance() - temp.getHeight());
						p2.setLeftOldClearance(p2.getLeftOldClearance() + temp.getHeight());
						p2.setLeftNewClearance(10 + p2.getLeftOldClearance());
						p2.getLeftHandList().add(temp);
						pool.releaseShape(i);
						i--;
						p2.moveShapes();
						continue;
					}
				}
			}
			if (p2.getMaxYRight() > 100) {
				if (pool.getUsedShapesList().get(i).getYPos() + pool.getUsedShapesList().get(i).getHeight() >= (p2.getyPos() - p2.getRightNewClearance()) && pool.getUsedShapesList().get(i).getYPos() + pool.getUsedShapesList().get(i).getHeight() < (p2.getyPos() - p2.getRightOldClearance())) {
					if ((pool.getUsedShapesList().get(i).getXPos() + pool.getUsedShapesList().get(i).getWidth() / 2) >= (p2.getxPos() + p2.getWidth() - p2.getLengthOfBumper()) && (pool.getUsedShapesList().get(i).getXPos() + pool.getUsedShapesList().get(i).getWidth() / 2) < p2.getxPos() + p2.getWidth()) {
						RandShape temp = pool.getUsedShapesList().get(i).clone();
						prepareNextShape(temp);
						temp.setYPos(p2.getyPos() - p2.getRightOldClearance() - temp.getHeight());
						p2.setRightOldClearance(p2.getRightOldClearance() + temp.getHeight());
						p2.setRightNewClearance(10 + p2.getRightOldClearance());
						p2.getRightHandList().add(temp);
						pool.releaseShape(i);
						i--;
						p2.moveShapes();
					}
				}
			}
		}
	}

	public void setPanelIsResized(boolean b) {
		// TODO Auto-generated method stub
		panelIsResized = b;
	}

	public void adjustScreenDrag() {
		StandardResolution.getStandardResolutionInstance().adjustPanelParameters(myPanel.getWidth(), myPanel.getHeight());
		ShelfEdge1 = StandardResolution.getStandardResolutionInstance().getLongQueueLength();
		ShelfEdge2 = StandardResolution.getStandardResolutionInstance().getShortQueueLength();
		myPanel.clearDrawList();
		RandShape currentShape;
		int difference = myPanel.getWidth() - oldPanelWidth;
		int plateSizeDiff = StandardResolution.getStandardResolutionInstance().getShapeWidth() - oldPlateWidth;
		System.out.println(difference);
		for (int i = 0; i < pool.getUsedShapesList().size(); i++) {
			currentShape = pool.getUsedShapesList().get(i);

			if (currentShape.getDirection() == 3) {// upper right

				currentShape.setXPos(currentShape.getXPos() + difference - plateSizeDiff);

			} else if (currentShape.getDirection() == 4) {// lower right

				currentShape.setXPos(currentShape.getXPos() + difference - plateSizeDiff);

			}

			myPanel.addImageToDrawList(currentShape.getImg(), currentShape.getXPos(), currentShape.getYPos());

		}
		oldPanelWidth = myPanel.getWidth();
		oldPlateWidth = StandardResolution.getStandardResolutionInstance().getShapeWidth();
		myPanel.repaint();

	}

	/**
	 * @return the p1Left
	 */
	public boolean isP1Left() {
		return p1Left;
	}

	/**
	 * @param p1Left
	 *            the p1Left to set
	 */
	public void setP1Left(boolean p1Left) {
		this.p1Left = p1Left;
	}

	/**
	 * @return the p1Right
	 */
	public boolean isP1Right() {
		return p1Right;
	}

	/**
	 * @param p1Right
	 *            the p1Right to set
	 */
	public void setP1Right(boolean p1Right) {
		this.p1Right = p1Right;
	}

	/**
	 * @return the p2Left
	 */
	public boolean isP2Left() {
		return p2Left;
	}

	/**
	 * @param p2Left
	 *            the p2Left to set
	 */
	public void setP2Left(boolean p2Left) {
		this.p2Left = p2Left;
	}

	/**
	 * @return the p2Right
	 */
	public boolean isP2Right() {
		return p2Right;
	}

	/**
	 * @param p2Right
	 *            the p2Right to set
	 */
	public void setP2Right(boolean p2Right) {
		this.p2Right = p2Right;
	}

	private void addPlayer() {
		// TODO Auto-generated method stub
		myPanel.addImageToDrawList(p1.getImg(), p1.getxPos(), p1.getyPos());
		myPanel.addImageToDrawList(p2.getImg(), p2.getxPos(), p2.getyPos());
		for (int i = 0; i < p1.getLeftHandList().size(); i++) {
			myPanel.addImageToDrawList(p1.getLeftHandList().get(i).getImg(), p1.getLeftHandList().get(i).getXPos(), p1.getLeftHandList().get(i).getYPos());
		}
		for (int i = 0; i < p1.getRightHandList().size(); i++) {
			myPanel.addImageToDrawList(p1.getRightHandList().get(i).getImg(), p1.getRightHandList().get(i).getXPos(), p1.getRightHandList().get(i).getYPos());
		}
		for (int i = 0; i < p2.getLeftHandList().size(); i++) {
			myPanel.addImageToDrawList(p2.getLeftHandList().get(i).getImg(), p2.getLeftHandList().get(i).getXPos(), p2.getLeftHandList().get(i).getYPos());
		}
		for (int i = 0; i < p2.getRightHandList().size(); i++) {
			myPanel.addImageToDrawList(p2.getRightHandList().get(i).getImg(), p2.getRightHandList().get(i).getXPos(), p2.getRightHandList().get(i).getYPos());
		}

	}
	public class MyThreadClass implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			// TODO Auto-generated method stub
			isRunning = true;
			while (isRunning) {
				if (p1.isGameOver() && p2.isGameOver()) {
					p1.obligatoryMovement();
					p2.obligatoryMovement();
				} else {
					if (p1.isGameOver()) {
						p1.obligatoryMovement();
					}
					if (p2.isGameOver()) {
						p2.obligatoryMovement();
					}
				}
				if (p1Left && !p1.isGameOver()) {
					p1.setxPos(p1.getxPos() - 10);
					if (p1.getxPos() < 0) {
						p1.setxPos(0);
					}
					p1.moveShapes();
				}
				if (p1Right && !p1.isGameOver()) {
					p1.setxPos(p1.getxPos() + 10);
					if (p1.getxPos() > myPanel.getWidth() - p1.getWidth()) {
						p1.setxPos(myPanel.getWidth() - p1.getWidth());
					}
					p1.moveShapes();
				}
				if (p2Left && !p2.isGameOver()) {
					p2.setxPos(p2.getxPos() - 10);
					if (p2.getxPos() < 0) {
						p2.setxPos(0);
					}
					p2.moveShapes();
				}
				if (p2Right && !p2.isGameOver()) {
					p2.setxPos(p2.getxPos() + 10);
					if (p2.getxPos() > myPanel.getWidth() - p2.getWidth()) {
						p2.setxPos(myPanel.getWidth() - p2.getWidth());
					}
					p2.moveShapes();
				}
				ShelfEdge1 = StandardResolution.getStandardResolutionInstance().getLongQueueLength();
				ShelfEdge2 = StandardResolution.getStandardResolutionInstance().getShortQueueLength();
				addNewShapes();
				checkP1Intersection();
				checkP2Intersection();
				update();

				if (panelIsResized) {
					StandardResolution.getStandardResolutionInstance().adjustPanelParameters(myPanel.getWidth(), myPanel.getHeight());
				}
				addPlayer();
				myPanel.repaint();
				// for(int i =0; i <
				// ShapePool.getShapePoolInstance().getUsedShapesList().size();
				// i++)
				// {
				// System.out.println(ShapePool.getShapePoolInstance().getUsedShapesList().get(i).getXPos());
				// System.out.println(ShapePool.getShapePoolInstance().getUsedShapesList().get(i).getYPos());
				// }
				// System.out.println("Updating");
				try {
					Thread.sleep(60);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}

		

	}
}
