package game;

//import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import game.Game2;

public class GameKeyListener implements KeyListener, MouseListener {
	private Player2 player;
//	private Game2 game2;
	
	public GameKeyListener(Player2 player2) {
		this.player = player2;
	}
	
//	public GameKeyListener(Game2 canvas) {
//		this.game2 = canvas;
//	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		if (Game2.diffChosen == false) {
			switch (key) {
			case KeyEvent.VK_LEFT:
				player.moved();
				player.rLeft();
				break;
			case KeyEvent.VK_RIGHT:
				player.moved();
				player.rRight();
				break;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		switch (key) {
		case KeyEvent.VK_LEFT:
			if (Player2.rLeft) player.rLeft();
			break;
		case KeyEvent.VK_RIGHT:
			if (Player2.rRight) player.rRight();
			break;
		}

	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
//		Point p = e.getPoint();
//		if (Game2.buttons[0].contains(p)) {
//			game2.diffChosen();
//			game2.difficulty = 0;
//		}
//		if (Game2.buttons[1].contains(p)) {
//			game2.diffChosen();
//			game2.difficulty = 1;
//		}
//		if (Game2.buttons[2].contains(p)) {
//			game2.diffChosen();
//			game2.difficulty = 2;
//		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
