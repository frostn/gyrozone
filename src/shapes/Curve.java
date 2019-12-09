package shapes;

import java.awt.geom.Path2D.Double;

import java.awt.Color;
import java.awt.geom.PathIterator;

public class Curve extends Double {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Color c;

	public Curve(int x1, int y1, Color c) {
		moveTo(x1, y1);
		this.c = c;
	}
	
	public Curve(String line) {
		String data[] = line.split(",");
		int rgb = Integer.parseInt(data[1]);
		c = new Color(rgb);
		if (data.length == 8) {
			double x1 = Integer.parseInt(data[2]);
			double y1 = Integer.parseInt(data[3]);
			double x2 = Integer.parseInt(data[4]);
			double y2 = Integer.parseInt(data[5]);
			double x3 = Integer.parseInt(data[6]);
			double y3 = Integer.parseInt(data[7]);
			moveTo(x1, y1);
			quadTo(x2, y2, x3, y3);
		}
		else if (data.length == 10) {
			double x1 = Integer.parseInt(data[2]);
			double y1 = Integer.parseInt(data[3]);
			double x2 = Integer.parseInt(data[4]);
			double y2 = Integer.parseInt(data[5]);
			double x3 = Integer.parseInt(data[6]);
			double y3 = Integer.parseInt(data[7]);
			double x4 = Integer.parseInt(data[8]);
			double y4 = Integer.parseInt(data[9]);
			moveTo(x1, y1);
			curveTo(x2, y2, x3, y3, x4, y4);
		}
		else {
			double[] xVals = new double[(data.length - 2)/2];
			double[] yVals = new double[(data.length - 2)/2];
			int j = 0;
			for (int i = 2; i < data.length; i = i + 2) {
				xVals[j] = Integer.parseInt(data[i]);
				yVals[j] = Integer.parseInt(data[i+1]);
				j++;
			}
			moveTo(xVals[0], yVals[0]);
			for (int k = 1; k < xVals.length; k++) {
				lineTo(xVals[k], yVals[k]);
			}
		}
	}
	
	public Color getDrawColor() {
		return c;
	}
	
	public String toString() {
		String typeColor = "curve," + c.getRGB();
		String xy = "";
		PathIterator pathIterator = getPathIterator(null);
		double[] coordinates = new double[6];
		while (!pathIterator.isDone()) {
			int type = pathIterator.currentSegment(coordinates);
			if (type == PathIterator.SEG_LINETO || type == PathIterator.SEG_MOVETO) {
				int x = (int) coordinates[0];
				int y = (int) coordinates[1];
				xy += "," + x + "," + y;
			}
			pathIterator.next();
		}
		return String.format("%s%s%n", typeColor, xy);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Curve c = new Curve("curve,-255,250,300,300,250,350,300");
		System.out.println(c);
	}

}
