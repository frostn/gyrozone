package game;
//http://blog.slapware.eu/game-engine/preface/
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.image.BufferStrategy;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;

import javax.swing.Timer;

import game.Player2;


public class Game2 extends Canvas implements Runnable {

	private static final long serialVersionUID = 7580815534084638412L;
	static Player2 player;
	private static Dimension sSize = Toolkit.getDefaultToolkit().getScreenSize();
	Timer timer;
	boolean calcDone = false;
	boolean threadStop = false;
	static Shape[] buttons = new Shape[3];
	static boolean diffChosen = false;
	int difficulty = -1;
	static BufferStrategy bs;
	private Ball ball;
	
	public Game2() {
		super();
		setIgnoreRepaint(true);
		setPreferredSize(sSize);
		player = new Player2();
		ball = new Ball();
		//addMouseListener(new GameKeyListener(this));
		requestFocusInWindow();
		
		Handler handler = new Handler(this);
		timer = new Timer(15, handler);
		timer.start();
	}
	
	public synchronized void render() {
		if (!calcDone) {
			return;
		}
		
		bs = getBufferStrategy();
		Graphics g = bs.getDrawGraphics();
		Color bc = new Color(200,255,253);
		g.setColor(bc);
		g.fillRect(0, 0, sSize.width, sSize.height);
		g.setColor(Color.blue);
		Graphics2D g2 = (Graphics2D) g;
		if (diffChosen == true) {
//			buttons[0] = new Rectangle((int) (sSize.width*.1), (int) (sSize.height*.65), (int) (sSize.width*.2), (int) (sSize.height*.2), Color.magenta, true);
//			g2.setColor(Color.magenta);
//			g2.fill(buttons[0]);
//			buttons[1] = new Rectangle((int) (sSize.width*.4), (int) (sSize.height*.65), (int) (sSize.width*.2), (int) (sSize.height*.2), Color.magenta, true);
//			g2.setColor(Color.orange);
//			g2.fill(buttons[1]);
//			buttons[2] = new Rectangle((int) (sSize.width*.7), (int) (sSize.height*.65), (int) (sSize.width*.2), (int) (sSize.height*.2), Color.magenta, true);
//			g2.setColor(Color.red);
//			g2.fill(buttons[2]);
//			calcDone = true;
		} else {
			player.render(g2);
			ball.render();
			calcDone = false;
		}
		if (g != null) {
			g.dispose();
		}
		if (g2 != null) {
			g2.dispose();
		}
		bs.show();
		Toolkit.getDefaultToolkit().sync();
	}
	
	public void diffChosen() {
		diffChosen = true;
	}
	
	public void run() {
		for (;;) {
			
			if (threadStop) {
				timer.stop();
				return;
			}
			
			if (calcDone) {
				try {
					Thread.sleep(1L);
					continue;
				} catch (Exception e) {	
				}
			}
			
			AffineTransform at = new AffineTransform();
			
			//calculations
			if (Player2.rRight) {
				Shape shape = Player2.shapes[0];
				at.rotate((Math.PI / 90), Player2.midX, Player2.midY);
				Player2.shapes[0] = at.createTransformedShape(shape);
				Player2.areas[0] = new Area(Player2.shapes[0]);
				player.rotateRect(Player2.shapes[0]);
			}
			
			if (Player2.rLeft) {
				Shape shape = Player2.shapes[0];
				at.rotate(-(Math.PI / 90), Player2.midX, Player2.midY);
				Player2.shapes[0] = at.createTransformedShape(shape);
				Player2.areas[0] = new Area(Player2.shapes[0]);
				player.rotateRect(Player2.shapes[0]);
			}
			
			calcDone = true;
		}
	}
}
