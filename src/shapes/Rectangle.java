package shapes;

import java.awt.Color;
import java.awt.geom.Rectangle2D.Double;
import java.util.Scanner;

public class Rectangle extends Double {

	private static final long serialVersionUID = 1L;
	public Color c;
	protected boolean fill;
	private float velX;
	private float velY;

	public Rectangle (int x1, int y1,int width, int height, Color c) {
		this.x = x1;
		this.y = y1;
		this.width = width;
		this.height = height;
		this.c = c;
	}

	public Rectangle (int x1, int y1, int width, int heigth, Color c, boolean fill) {
		this(x1, y1, width, heigth, c);
		this.fill = fill;
	}

	public Rectangle (String data) {
		Scanner sc = new Scanner(data);
		sc.useDelimiter(",\\s*");
		String type = sc.next();
		if (type.charAt(0) == 'f') fill = true;
		else fill = false;
		x = sc.nextInt();
		y = sc.nextInt();
		width = sc.nextInt();
		height = sc.nextInt();
		c = new Color(sc.nextInt());
		sc.close();
	}

	public boolean isFill() {
		return fill;
	}

	public Color getDrawColor() {
		return c;
	}

	public float getVelY() {
		return velY;
	}

	public void setVelY(float velY) {
		this.velY = velY;
	}

	public float getVelX() {
		return velX;
	}

	public void setVelX(float velX) {
		this.velX = velX;
	}

	public String toString() {
		return String.format("%s, %d, %d, %d, %d, %d%n", (fill ? "filledRectangle" : "rectangle"), (int) x, (int) y,
		  		(int) width, (int) height, c.getRGB());
	  }

}
