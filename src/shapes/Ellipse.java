/**
 * 
 */
package shapes;

import java.awt.Color;
import java.awt.geom.Ellipse2D.Double;
import java.util.Scanner;

/**
 * @author tompkinsj
 *
 */
public class Ellipse extends Double {

	private static final long serialVersionUID = -1348149774232535515L;
	private Color c;
	private boolean fill;
	private boolean speaking;
	private String words;

	public Ellipse(int x1, int y1, int w, int h, Color c) {
		super(x1, y1, w, h);
		this.c = c;
	}

	public Ellipse(int x1, int y1, int w, int h, Color c, boolean fill) {
		this(x1, y1, w, h, c);
		this.fill = fill;
	}

	public Ellipse(String str) {
		Scanner sc = new Scanner(str);
		sc.useDelimiter(",\\s*");
		String type = sc.next();
		int[] data = new int[5];
		int i = 0;
		while (sc.hasNextInt())
			data[i++] = sc.nextInt();
		sc.close();
		if (type.contentEquals("filledCircle"))
			fill = true;
		else
			fill = false;
		x = data[0];
		y = data[1];
		width = data[2];
		height = data[3];
		c = new Color(data[4]);

	}

	public Color getDrawColor() {
		return c;
	}

	public String toString() {
		return String.format("%s, %d, %d, %d, %d, %d%n", (fill ? "filledCircle" : "circle"), (int) x, (int) y,
				(int) width, (int) height, c.getRGB());
	}

	public boolean isFill() {
		return fill;
	}

	public boolean isSpeaking() {
		return speaking;
	}

	public String speak() {
		speaking = true;
		words = "What does the Buffalo say to his son in the morning?";
		return words;
	}

	public static void main(String[] args) {
		Ellipse e = new Ellipse("filledCircle, 475, 325, 48, 48, -16776961\n");
		Ellipse e1 = new Ellipse("circle, 375, 425, 47, 47, -16711936\n");
		System.out.print(e);
		System.out.print(e1);
	}

}
