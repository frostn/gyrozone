package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.Toolkit;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.GeneralPath;

import java.lang.Math;

public class Player2 {
	
	static Dimension sSize = Toolkit.getDefaultToolkit().getScreenSize();
	static int midX = sSize.width/2;
	static int midY = sSize.height/2;
	static int r = (int) (midY*.7);
	private int halfCirc = (int) (r*Math.PI);
	private int rectW = (int) (halfCirc / 8);
	private int thick = 12;
	static Shape[] shapes = new Shape[8];
	static boolean rLeft = false;
	static boolean rRight = false;
	static boolean unmoved = true;
	private RenderingHints renderingHints;
	static GeneralPath[] paths = new GeneralPath[8];
	static Area[] areas = new Area[8];

	public Player2() {
		int[] r1x = {(int) (midX-(.5*rectW)), (int) (midX+(.5*rectW)), (int) (midX+(.5*rectW)), (int) (midX-(.5*rectW))};
		int[] r1y = {midY-r, midY-r, midY-r-thick, midY-r-thick};
		Polygon poly = new Polygon(r1x,r1y,4);
		shapes[0] = poly;
		areas[0] = new Area(shapes[0]);
		rotateRect(poly);
	}
	
	public void rotateRect(Shape s) {
		AffineTransform at = new AffineTransform();
		for (int i = 1; i < 8; i++) {	
			at.rotate(i*(Math.PI / 4), midX, midY);
			shapes[i] = at.createTransformedShape(s);
            areas[i] = new Area(shapes[i]);
		}
	}
	
	public void rLeft() {
		if (rLeft == false) {
			rLeft = true;
		} else {
			rLeft = false;
		}
	}
	
	public void rRight() {
		if (rRight == false) {
			rRight = true;
		} else {
			rRight = false;
		}
	}
	
	public void moved() {
		unmoved = false;
	}
	
	public void render(Graphics2D g2) {		
		this.renderingHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setRenderingHints(this.renderingHints);
		
		for(Shape p : shapes) {
			if (Frame.score < 20) {
				g2.setColor(Color.BLUE);
			} else if (Frame.score >= 20 && Frame.score < 40) {
				g2.setColor(Color.PINK);
			} else if (Frame.score >= 40 && Frame.score < 60) {
				g2.setColor(Color.RED);
			} else if (Frame.score >= 60 && Frame.score < 80) {
				g2.setColor(Color.MAGENTA);
			} else if (Frame.score >= 80 && Frame.score < 100) {
				g2.setColor(Color.GREEN);
			} else if (Frame.score >= 100) {
				g2.setColor(Color.GRAY);
			}
			g2.fill(p);
		}
	}
	
}
