package game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Handler implements ActionListener {
	
	Game2 game2;
	
	Handler(Game2 game2) {
		this.game2 = game2;
	}
	
	public void actionPerformed(ActionEvent e) {
		game2.render();
	}
}
