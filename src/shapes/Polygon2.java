package shapes;

import java.awt.Color;
import java.awt.Polygon;
//import java.util.Scanner;
import java.lang.String;

public class Polygon2 extends Polygon {

	private static final long serialVersionUID = 1L;
	private boolean fill;
	private Color c;

	public Polygon2(int[] xpts, int[] ypts, int npts) {
		super(xpts, ypts, npts);
		this.xpoints = xpts;
		this.ypoints = ypts;
	}
	
	public Polygon2(int[] xpts, int[] ypts, int npts, Color c) {
		super(xpts,ypts,npts);
		this.c = c;
		
	}
	
	public Polygon2(int[] xpts, int[] ypts, int npts, Color c, boolean fill) {
		this(xpts,ypts,npts,c);
		this.fill = fill;
	}
	
	public Polygon2(String line) {
		String data[] = line.split(",");
		for (String d : data) {
			d.replaceAll(",", "");
			d.replaceAll(" ", "");
		}
		npoints = Integer.parseInt(data[1]);
		int rgb = Integer.parseInt(data[2]);
		c = new Color(rgb);
		if (data[0].charAt(0) == 'f') fill = true;
		xpoints = new int[npoints];
		ypoints = new int[npoints];
		int j = 0;
		for (int i = 0; i < 2*npoints; i+=2) {
			xpoints[j] = Integer.parseInt(data[i+3]);
			ypoints[j] = Integer.parseInt(data[i+4]);
			j++;
		}
	}
	
	public Color getDrawColor() {
		return c;
	}
	
	public boolean isVisible() {
		return fill;
	}
	
	public String toString() {
		String typeColor = (fill ? "filledPolygon," : "polygon,") + npoints + "," + c.getRGB();
		String xy = "";
		for(int i = 0; i < npoints; i++) {
			xy += "," + xpoints[i] + "," + ypoints[i];
		}
		return String.format("%s%s%n", typeColor, xy);
	}
		
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Polygon2 e = new Polygon2("filledPolygon,3,-256,100,140,180,250,290,250");
	    Polygon2 e1 = new Polygon2("polygon,3,-256,400,420,440,400,380,400");
	    System.out.print(e);
	    System.out.print(e1);
	}
}
