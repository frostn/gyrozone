package game;


import java.awt.*;
import java.awt.geom.Area;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import shapes.Ellipse;

public class Ball {
	private double ballX, ballY;
	private double deltaX, deltaY;
	private boolean repaintInProgress = false;
	Boolean col = false;
	private Ellipse ball = new Ellipse((int) ballX, (int) ballY, 20, 20, Color.black);
	private double ballSpeed = 1.5;
	private RenderingHints renderingHints;
	private boolean done = false;
	
	public Ball() {
		ballX = Player2.midX;
		ballY = Player2.midY;
		deltaX = getStart();
		deltaY = getStart();
	}
	
	public int getStart() {
	    Random random = new Random();
	    boolean sign = random.nextBoolean();
	    if (sign) return intRange(1, 2);
	    else return intRange(-2, -1);
	}
	
	public int getSign() {
	    Random random = new Random();
	    boolean sign = random.nextBoolean();
	    if (sign) return 1;
	    else return -1;
	}
	
	private static int intRange(int min, int max) {
		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}
		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}

	public void render() {
		Graphics graphics = Game2.bs.getDrawGraphics();
		Graphics2D g2 = (Graphics2D) graphics;
		this.renderingHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHints(this.renderingHints);
		if(repaintInProgress)
			return;
		repaintInProgress = true;
		collision();
		ballX += deltaX;
		ballY += deltaY;
		ball = new Ellipse((int) ballX, (int) ballY, 20, 20, Color.black);
		g2.fill(ball);
		if(graphics != null)
			graphics.dispose();
		repaintInProgress = false;
	}
	
	public void collision() {
		if ((ballX <= 0 || ballY <= 0 || ballX >= Player2.sSize.width || ballY >= Player2.sSize.height) && !done) {
			Frame.saveScore(Frame.score);
			Frame.displayLeaderboard();
			done = true;
		}
		for (Area a : Player2.areas) {
			if ((a.contains((double) (ballX), (double) (ballY+12.5)) || //left
			a.contains((double) (ballX+12.5), (double) (ballY)) || //top
			a.contains((double) (ballX+25), (double) (ballY+12.5)) || //right
			a.contains((double) (ballX+12.5), (double) (ballY+25))) &&
			col == false) { 
				col = true;
				int multiplier1;
				int multiplier2;
				double fraction1;
				double fraction2;
				if ((ballX - Player2.midX) > (.9 * Player2.r)) {
					multiplier1 = intRange(40,100);
					multiplier2 = 100 - multiplier1;
					fraction1 = multiplier1 * .01;
					fraction2 = multiplier2 * .01;
					deltaX = (double) (-1 * (fraction1 * ballSpeed));
					deltaY = (double) (getSign() * (fraction2 * ballSpeed));
				} else if ((Player2.midX - ballX) > (.9 * Player2.r)) {
					multiplier1 = intRange(40,100);
					multiplier2 = 100 - multiplier1;
					fraction1 = multiplier1 * .01;
					fraction2 = multiplier2 * .01;
					deltaX = (double) (fraction1 * ballSpeed);
					deltaY = (double) (getSign() * (fraction2 * ballSpeed));
				} else if ((ballY - Player2.midY) > (.9 * Player2.r)) {
					multiplier2 = intRange(40,100);
					multiplier1 = 100 - multiplier2;
					fraction1 = multiplier1 * .01;
					fraction2 = multiplier2 * .01;
					deltaX = (double) (getSign() * (fraction1 * ballSpeed));
					deltaY = (double) (-1 * (fraction2 * ballSpeed));
				} else if ((Player2.midY - ballY) > (.9 * Player2.r)) {
					multiplier2 = intRange(40,100);
					multiplier1 = 100 - multiplier2;
					fraction1 = multiplier1 * .01;
					fraction2 = multiplier2 * .01;
					deltaX = (double) (getSign() * (fraction1 * ballSpeed));
					deltaY = (double) (fraction2 * ballSpeed);
				} else if (deltaX > 0 && deltaY > 0) {
					multiplier1 = intRange(40,60);
					multiplier2 = 100 - multiplier1;
					fraction1 = multiplier1 * .01;
					fraction2 = multiplier2 * .01;
					deltaX = (double) (-1 * (fraction1 * ballSpeed));
					deltaY = (double) (-1 * (fraction2 * ballSpeed));
				} else if (deltaX > 0 && deltaY < 0) {
					multiplier1 = intRange(40,60);
					multiplier2 = 100 - multiplier1;
					fraction1 = multiplier1 * .01;
					fraction2 = multiplier2 * .01;
					deltaX = (double) (-1 * (fraction1 * ballSpeed));
					deltaY = (double) (fraction2 * ballSpeed);
				} else if (deltaX < 0 && deltaY > 0) {
					multiplier1 = intRange(40,60);
					multiplier2 = 100 - multiplier1;
					fraction1 = multiplier1 * .01;
					fraction2 = multiplier2 * .01;
					deltaX = (double) (fraction1 * ballSpeed);
					deltaY = (double) (-1 * (fraction2 * ballSpeed));
				} else if (deltaX < 0 && deltaY < 0) {
					multiplier1 = intRange(40,60);
					multiplier2 = 100 - multiplier1;
					fraction1 = multiplier1 * .01;
					fraction2 = multiplier2 * .01;
					deltaX = (double) (fraction1 * ballSpeed);
					deltaY = (double) (fraction2 * ballSpeed);
				}
				Frame.score++;
				Frame.scoreBackground(Frame.score);
				ballSpeed += .1;
				delayCol(1);
			}
			Frame.setLevelText(Frame.score);
			Frame.setScoreText(Frame.score);
		}
	}
	
	private void delayCol(int halfSeconds) {
		Timer timer2 = new Timer();
		timer2.schedule(new delayTask(), halfSeconds * 500);
	}
	
	class delayTask extends TimerTask {
        public void run() {
            col = false;
        }
    }

}

	
