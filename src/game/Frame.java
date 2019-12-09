package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
//import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Popup;
import javax.swing.PopupFactory;
import javax.swing.SwingUtilities;

public class Frame extends JFrame implements WindowListener {
	private static final long serialVersionUID = 2721843806653873180L;
	private static JPanel pNorth;
	private static JTextField scoreText;
	private static JTextField levelText;
	static int score = 0;
	private static Frame frame;
	private static File file;

	static Game2 canvas;
	
	public Frame() {
		super("GyroZone");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		setMaximumSize(screenSize);
		setMinimumSize(screenSize);
		setPreferredSize(screenSize);
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		Color bc = new Color(200,255,253);
		getContentPane().setBackground(bc);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		addWindowListener(this);
		pNorth = new JPanel();
		pNorth.setBackground(Color.BLUE);
		levelText = new JTextField("Level 1", 7);
		levelText.setHorizontalAlignment(JTextField.CENTER);
		levelText.setEditable(false);
		scoreText = new JTextField("Score: " + Integer.toString(score), 7);
		scoreText.setHorizontalAlignment(JTextField.CENTER);
		scoreText.setEditable(false);
		pNorth.add(levelText);
		pNorth.add(scoreText);
		add(pNorth, BorderLayout.NORTH);
		canvas = new Game2();
		addKeyListener(new GameKeyListener(Game2.player));
		add(canvas, BorderLayout.CENTER);
		setVisible(true);
		setFocusable(true);
		canvas.createBufferStrategy(2);
		Thread thread = new Thread(canvas);
		thread.start();
	}
	
	public static void scoreBackground(int score) {
		if (score >= 20 && score < 40) {
			pNorth.setBackground(Color.PINK);
		}
		if (score >= 40 && score < 60) {
			pNorth.setBackground(Color.RED);
		}
		if (score >= 60 && score < 80) {
			pNorth.setBackground(Color.MAGENTA);
		}
		if (score >= 80 && score < 100) {
			pNorth.setBackground(Color.GREEN);
		}
		if (score >= 100) {
			pNorth.setBackground(Color.GRAY);
		}
	}
	
	public static void setScoreText(int score) {
		scoreText.setText("Score: " + Integer.toString(score));
	}
	
	public static void setLevelText(int score) {
		if (score < 20) {
			levelText.setText("Level 1");
		}
		if (score >= 20 && score < 40) {
			levelText.setText("Level 2");
		}
		if (score >= 40 && score < 60) {
			levelText.setText("Level 3");
		}
		if (score >= 60 && score < 80) {
			levelText.setText("Level 4");
		}
		if (score >= 80 && score < 100) {
			levelText.setText("Level 5");
		}
		if (score >= 100) {
			levelText.setText("Level 6");
		}
	}
	
	public static void saveScore(int score) {
		String userName;
		String scoreString;
		userName = (JOptionPane.showInputDialog("Enter Username: "));
		scoreString = userName + "," + Integer.toString(score);
		file = new File("scoreHistory.txt");
		FileWriter fw = null;
		try {
			fw = new FileWriter(file, true);
			fw.write(scoreString + "\n");
		} catch (IOException ioe) {
			ioe.printStackTrace();
		} finally {
			try {
				fw.close();
			} catch (IOException ioe2) {
				ioe2.printStackTrace();
			}
		}
	}
	
	public static void displayLeaderboard() {
		Scanner sc = null;
		ArrayList<Integer> highScores = new ArrayList<Integer>();
		ArrayList<String> userNames = new ArrayList<String>();
		if (file != null) {
			try {
				sc = new Scanner(file);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				while (sc.hasNextLine()) {
					String line = sc.nextLine();
					String[] data = line.split(",");
					highScores.add(Integer.parseInt(data[1]));
					userNames.add(data[0]);
				}
				sc.close();
			}
			ArrayList<Integer> highScores2 = new ArrayList<Integer>();
			highScores2.addAll(highScores);
			Collections.sort(highScores2, Collections.reverseOrder());
			int[] index = new int[5];
			for (int i = 0; i < 5; i++) {
				int hS = highScores2.get(i);
				index[i] = highScores.indexOf(hS);
			}
			
			String myString = "Username | High Score\n" + userNames.get(index[0]) + " | " + 
											Integer.toString(highScores.get(index[0])) + "\n" + userNames.get(index[1]) + " | " + 
											Integer.toString(highScores.get(index[1])) + "\n" + userNames.get(index[2]) + " | " + 
											Integer.toString(highScores.get(index[2])) + "\n" + userNames.get(index[3]) + " | " + 
											Integer.toString(highScores.get(index[3])) + "\n" + userNames.get(index[4]) + " | " + 
											Integer.toString(highScores.get(index[4]));
			
			JLabel scoreInfo = new JLabel();
			scoreInfo.setText("<html><pre>" + myString.replaceAll("\n", "<br/>") + "</pre></html>");
			
			PopupFactory pf = new PopupFactory();
			JPanel p2 = new JPanel();
			p2.setBackground(Color.WHITE);
			p2.add(scoreInfo);
			Popup scoreTable;
			scoreTable = pf.getPopup(canvas, p2, Player2.sSize.width/2 - 70, Player2.sSize.height/2);
			scoreTable.show();
		}
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				frame = new Frame();
			}
		});
	}
	
	public void windowClosing(WindowEvent e) {
		canvas.threadStop = true;
		dispose();
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}
}
