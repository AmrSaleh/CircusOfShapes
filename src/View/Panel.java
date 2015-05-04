package View;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Controller.GameLoop;
import Model.PlayerOne;
import Model.PlayerTwo;

public class Panel extends JPanel implements ChangeListener, KeyListener {

	private static final long serialVersionUID = 1L;

	private static Panel myPanel;
	private ArrayList<Image> images;
	private ArrayList<Integer> xPositions;
	private ArrayList<Integer> yPositions;
	private Font font = new Font("Arial", Font.BOLD, 15);
	private Image img = null;
	private Image p1win = null;
	private Image p2win = null;
	private Image tieImg = null;
	private int xWin=4,yWin=4;
	private int maxXWin=500,maxYWin=500;
	Graphics2D g2d;

	private Panel() {
		images = new ArrayList<Image>();
		xPositions = new ArrayList<Integer>();
		yPositions = new ArrayList<Integer>();
		setLayout(null);
		addKeyListener(this);
		try {
			img = ImageIO.read(new File("bck1.jpg"));
			p1win = ImageIO.read(new File("Player\\clownWin.png"));
			p2win = ImageIO.read(new File("Player\\robotWin.png"));
			tieImg = ImageIO.read(new File("Player\\draw.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static Panel getPanelInstance() {
		if (myPanel == null) {
			myPanel = new Panel();

		}

		return myPanel;
	}

	public void addNotify() {
		super.addNotify();
		requestFocus();
	}

	public void paint(Graphics g) {
		super.paint(g);
		g2d = (Graphics2D) g;

		g2d.setColor(Color.white);

		// img.getScaledInstance(width, height, null);

		g2d.drawImage(img, 0, 0, getWidth(), getHeight(), null);

		synchronized (images) {
			for (int i = 0; i < images.size(); i++) {
				try {
					g2d.drawImage(images.get(i), xPositions.get(i), yPositions.get(i), null);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					// e.printStackTrace();
				}
			}
		}

		g2d.setFont(font);
		g2d.drawString("Score1: " + PlayerOne.getInstance().getScore(), 5, getHeight() - 10);
		g2d.drawString("Score2: " + PlayerTwo.getInstance().getScore(), getWidth() - 80, getHeight() - 10);

		if (PlayerOne.getInstance().isGameOver() && PlayerTwo.getInstance().isGameOver()) {
			if (PlayerOne.getInstance().getScore() > PlayerTwo.getInstance().getScore()) {
				g2d.drawImage(p1win, getWidth() / 2 - xWin / 2, getHeight() / 2 - yWin / 2,xWin,yWin, null);
			} else if (PlayerOne.getInstance().getScore() < PlayerTwo.getInstance().getScore()) {
				g2d.drawImage(p2win, getWidth() / 2 - xWin / 2, getHeight() / 2 - yWin / 2,xWin,yWin, null);
			} else {
				g2d.drawImage(tieImg, getWidth() / 2 - xWin / 2, getHeight() / 2 - yWin / 2,xWin,yWin, null);
			}
			
			if(xWin<maxXWin){
				xWin+=10;
			}
			if(yWin<maxYWin){
				yWin+=10;
			}
		}

	}

	public void addImageToDrawList(Image myImage, int x, int y) {
		images.add(myImage);
		xPositions.add(x);
		yPositions.add(y);
	}

	public void clearDrawList() {

		xPositions.clear();
		yPositions.clear();
		images.clear();
	}

	@Override
	public void stateChanged(ChangeEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("sss");

		try {
			GameLoop.getInstance().start();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			// e1.printStackTrace();
		}
		// TODO Auto-generated method stub
		if (e.getKeyCode() == 39) {
			// Right arrow key code
			GameLoop.getInstance().setP2Right(true);
		}

		else if (e.getKeyCode() == 37) {
			// Left arrow key code
			GameLoop.getInstance().setP2Left(true);
		}

		if (e.getKeyCode() == 65) {
			// a key code
			GameLoop.getInstance().setP1Left(true);
		}

		else if (e.getKeyCode() == 68) {
			// d key code
			GameLoop.getInstance().setP1Right(true);
		}

		if (e.getKeyCode() == 8 || e.getKeyCode() == 27) { // backspace or
															// escape
			// pause game here ///////////

			System.out.println("pressed pause");
			GameLoop.getInstance().pause();
			// setVisible(false);
			Frame.showPauseMenu();

		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == 39) {
			// Right arrow key code
			GameLoop.getInstance().setP2Right(false);
		}

		else if (e.getKeyCode() == 37) {
			// Left arrow key code
			GameLoop.getInstance().setP2Left(false);
		}

		if (e.getKeyCode() == 65) {
			// a key code
			GameLoop.getInstance().setP1Left(false);
		}

		else if (e.getKeyCode() == 68) {
			// d key code
			GameLoop.getInstance().setP1Right(false);
		}

	}

	public void reset(){
		xWin=4;yWin=4;

		xPositions.clear();
		yPositions.clear();
		images.clear();
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

}
