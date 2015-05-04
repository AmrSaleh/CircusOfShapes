package View;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.net.URLClassLoader;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.CloseAction;
import Controller.GameHandler;
import Controller.GameLoop;
import Controller.ListLogic;
import Controller.StandardResolution;

public class Frame {

	private JFrame frame;
	private static JPanel global;
	private JpanelImage mainMenu;
	private JpanelImage pauseMenu;
	private Panel gamePanel;
	private static CardLayout CL;
	private JButton resumeBtn, saveBtn, loadInResBtn, goToMainBtn;
	private JButton startBtn, loadInMainBtn, loadClassBtn, quitBtn;
	private GameLoop theGameLoop;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		ListLogic.loadAvailableShapes();
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Throwable e) {
			e.printStackTrace();
		}
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Frame window = new Frame();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Frame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		// frame.setBounds(0, 0, 1365, 730);
		frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		Dimension dim = new Dimension(450, 300);
		frame.setMinimumSize(dim);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		CL = new CardLayout();
		global = new JPanel();

		global.setLayout(CL);
		global.setVisible(true);
		mainMenu = new JpanelImage();
		mainMenu.setLayout(null);
		pauseMenu = new JpanelImage();
		pauseMenu.setLayout(null);
		pauseMenu.setBackground(Color.BLACK);
		gamePanel = Panel.getPanelInstance();

		pauseMenu.setBgImage("bck4.jpg");
		mainMenu.setBgImage("bck3.jpg");

		pauseMenu.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				adjustPauseButtons();

			}
		});

		mainMenu.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				adjustMainButtons();

			}
		});
		// final StandardResolution stdResolution=
		// StandardResolution.getStandardResolutionInstance();
		gamePanel.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				// StandardResolution.getStandardResolutionInstance().adjustPanelParameters(gamePanel.getWidth(),
				// gamePanel.getHeight());
				// theGameLoop = GameLoop.getInstance();

				try {
					theGameLoop.pause();
					StandardResolution.getStandardResolutionInstance().adjustImages();
					theGameLoop.adjustScreenDrag();
					System.out.println("drag");
					theGameLoop.setPanelIsResized(true);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					// e1.printStackTrace();
				}

			}
		});

		gamePanel.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
				// TODO Auto-generated method stub
				try {
					// new Thread(theGameLoop).start();
					theGameLoop.start();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}

			@Override
			public void mousePressed(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO Auto-generated method stub

			}
		});

		startBtn = new JButton("Start Game");
		startBtn.setMargin(new Insets(2, 2, 2, 2));
		startBtn.setSize(100, 20);
		startBtn.setBounds(frame.getWidth() / 2 - startBtn.getWidth() / 2, 50, startBtn.getWidth(), startBtn.getHeight());
		startBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				CL.show(global, "game");
				gamePanel.requestFocus();

				theGameLoop = GameLoop.getInstance();

				theGameLoop.reset();
				// new Thread(theGameLoop).start();
				theGameLoop.start();

			}
		});
		mainMenu.add(startBtn);

		loadInMainBtn = new JButton("Load Game");
		loadInMainBtn.setMargin(new Insets(2, 2, 2, 2));
		loadInMainBtn.setSize(100, 20);
		loadInMainBtn.setBounds(frame.getWidth() / 2 - loadInMainBtn.getWidth() / 2, startBtn.getY() + startBtn.getHeight() + 10, loadInMainBtn.getWidth(), loadInMainBtn.getHeight());
		loadInMainBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String userhome = System.getProperty("user.home");
				JFileChooser chooser = new JFileChooser(userhome + "\\Desktop");

				FileNameExtensionFilter filter = new FileNameExtensionFilter("txt files", "txt");
				chooser.setFileFilter(filter);

				int returnVal = chooser.showOpenDialog(frame.getContentPane());
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					System.out.println("You chose to open this file: " + chooser.getSelectedFile().getName());

					java.io.File file = chooser.getSelectedFile();
					String file_name = file.toString();

					GameHandler.loadGame(file_name);
					startGameRemotely();
					theGameLoop= GameLoop.getInstance();
					theGameLoop.start();
				}

				
			}
		});
		mainMenu.add(loadInMainBtn);

		loadClassBtn = new JButton("Load library");
		loadClassBtn.setMargin(new Insets(2, 2, 2, 2));
		loadClassBtn.setSize(100, 20);
		loadClassBtn.setBounds(frame.getWidth() / 2 - loadClassBtn.getWidth() / 2, loadInMainBtn.getY() + loadInMainBtn.getHeight() + 10, loadClassBtn.getWidth(), loadClassBtn.getHeight());
		loadClassBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String userhome = System.getProperty("user.home");
				JFileChooser chooser = new JFileChooser(userhome + "\\Desktop");

				FileNameExtensionFilter filter = new FileNameExtensionFilter("CLASS Files", "class");
				chooser.setFileFilter(filter);
				int returnVal = chooser.showOpenDialog(frame.getContentPane());
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					System.out.println("You chose to open this file: " + chooser.getSelectedFile().getName());

					File file = chooser.getSelectedFile();
					System.out.println("at path: " + chooser.getSelectedFile());

					// get file name
					final String theClassName = file.getName().replace(".class", "");

					try {
						Controller.DynamicLoader.addClass(file, theClassName, false);
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						// e1.printStackTrace();
						System.out.println("couldn't load class");
					}

				}
			}
		});
		mainMenu.add(loadClassBtn);

		quitBtn = new JButton("Quit Game");
		quitBtn.setMargin(new Insets(2, 2, 2, 2));
		quitBtn.setSize(100, 20);
		quitBtn.setBounds(frame.getWidth() / 2 - quitBtn.getWidth() / 2, loadClassBtn.getY() + loadClassBtn.getHeight() + 10, quitBtn.getWidth(), quitBtn.getHeight());
		quitBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub

				frame.dispose();

			}
		});
		mainMenu.add(quitBtn);

		resumeBtn = new JButton("Resume Game");
		resumeBtn.setMargin(new Insets(2, 2, 2, 2));
		resumeBtn.setSize(100, 20);
		resumeBtn.setBounds(frame.getWidth() / 2 - resumeBtn.getWidth() / 2, 50, resumeBtn.getWidth(), resumeBtn.getHeight());
		resumeBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				CL.show(global, "game");
				gamePanel.requestFocus();
				// new Thread(theGameLoop).start();
				theGameLoop.start();
			}
		});
		pauseMenu.add(resumeBtn);

		saveBtn = new JButton("Save Game");
		saveBtn.setMargin(new Insets(2, 2, 2, 2));
		saveBtn.setSize(100, 20);
		saveBtn.setBounds(frame.getWidth() / 2 - saveBtn.getWidth() / 2, resumeBtn.getY() + resumeBtn.getHeight() + 10, saveBtn.getWidth(), saveBtn.getHeight());
		saveBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String userhome = System.getProperty("user.home");
				JFileChooser chooser = new JFileChooser(userhome + "\\Desktop");

				FileNameExtensionFilter filter = new FileNameExtensionFilter("txt files", "txt");
				chooser.setFileFilter(filter);

				int returnVal = chooser.showSaveDialog(frame.getContentPane());
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					System.out.println("You chose to save this file: " + chooser.getSelectedFile().getName());

					java.io.File file = chooser.getSelectedFile();
					String file_name = file.toString();

					if(!file_name.endsWith(".txt")){
						file_name=file_name+".txt";
					}
					
					GameHandler.saveGame(file_name);

				}
			}
		});
		pauseMenu.add(saveBtn);

		loadInResBtn = new JButton("Load Game");
		loadInResBtn.setMargin(new Insets(2, 2, 2, 2));
		loadInResBtn.setSize(100, 20);
		loadInResBtn.setBounds(frame.getWidth() / 2 - loadInResBtn.getWidth() / 2, saveBtn.getY() + saveBtn.getHeight() + 10, loadInResBtn.getWidth(), loadInResBtn.getHeight());
		loadInResBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				String userhome = System.getProperty("user.home");
				JFileChooser chooser = new JFileChooser(userhome + "\\Desktop");

				FileNameExtensionFilter filter = new FileNameExtensionFilter("txt files", "txt");
				chooser.setFileFilter(filter);

				int returnVal = chooser.showOpenDialog(frame.getContentPane());
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					System.out.println("You chose to open this file: " + chooser.getSelectedFile().getName());

					java.io.File file = chooser.getSelectedFile();
					String file_name = file.toString();

					GameHandler.loadGame(file_name);
					startGameRemotely();
					theGameLoop= GameLoop.getInstance();
					theGameLoop.start();
				}

				
			}
		});
		pauseMenu.add(loadInResBtn);

		goToMainBtn = new JButton("Main Menu");
		goToMainBtn.setMargin(new Insets(2, 2, 2, 2));
		goToMainBtn.setSize(100, 20);
		goToMainBtn.setBounds(frame.getWidth() / 2 - goToMainBtn.getWidth() / 2, loadInResBtn.getY() + loadInResBtn.getHeight() + 10, goToMainBtn.getWidth(), goToMainBtn.getHeight());
		goToMainBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				CL.show(global, "main");
			}
		});
		pauseMenu.add(goToMainBtn);

		global.add(mainMenu, "main");
		global.add(gamePanel, "game");
		global.add(pauseMenu, "pause");

		CL.show(global, "main");

		frame.getContentPane().add(global);

	}

	public static void showPauseMenu() {
		CL.show(global, "pause");

	}

	private void adjustPauseButtons() {
		resumeBtn.setBounds(frame.getWidth() / 2 - resumeBtn.getWidth() / 2, 50, resumeBtn.getWidth(), resumeBtn.getHeight());
		saveBtn.setBounds(frame.getWidth() / 2 - saveBtn.getWidth() / 2, resumeBtn.getY() + resumeBtn.getHeight() + 10, saveBtn.getWidth(), saveBtn.getHeight());
		loadInResBtn.setBounds(frame.getWidth() / 2 - loadInResBtn.getWidth() / 2, saveBtn.getY() + saveBtn.getHeight() + 10, loadInResBtn.getWidth(), loadInResBtn.getHeight());
		goToMainBtn.setBounds(frame.getWidth() / 2 - goToMainBtn.getWidth() / 2, loadInResBtn.getY() + loadInResBtn.getHeight() + 10, goToMainBtn.getWidth(), goToMainBtn.getHeight());

	}

	private void adjustMainButtons() {

		startBtn.setBounds(frame.getWidth() / 2 - startBtn.getWidth() / 2, 50, startBtn.getWidth(), startBtn.getHeight());
		loadInMainBtn.setBounds(frame.getWidth() / 2 - loadInMainBtn.getWidth() / 2, startBtn.getY() + startBtn.getHeight() + 10, loadInMainBtn.getWidth(), loadInMainBtn.getHeight());
		loadClassBtn.setBounds(frame.getWidth() / 2 - loadClassBtn.getWidth() / 2, loadInMainBtn.getY() + loadInMainBtn.getHeight() + 10, loadClassBtn.getWidth(), loadClassBtn.getHeight());
		quitBtn.setBounds(frame.getWidth() / 2 - quitBtn.getWidth() / 2, loadClassBtn.getY() + loadClassBtn.getHeight() + 10, quitBtn.getWidth(), quitBtn.getHeight());

	}

	public class JpanelImage extends JPanel {

		private static final long serialVersionUID = 1L;
		private Image bgImage;

		public void setBgImage(String url) {
			try {
				bgImage = ImageIO.read(new File(url));
			} catch (IOException e) {

				e.printStackTrace();
			}
		}

		public void setBgImage(Image img) {
			bgImage = img;
		}

		public Image getBgImage() {
			return bgImage;
		}

		@Override
		protected void paintComponent(Graphics g) {

			super.paintComponent(g);
			g.drawImage(bgImage, 0, 0, getWidth(), getHeight(), null);
		}

	}

	public void startGameRemotely() {
		CL.show(global, "game");
		gamePanel.requestFocus();

	}

}
