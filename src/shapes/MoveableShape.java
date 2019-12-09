package shapes;

import java.awt.Graphics2D;

interface MoveableShape {
	
	public abstract void tick();
	public abstract void render(Graphics2D g2);

}
