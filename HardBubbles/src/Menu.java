import java.awt.Graphics2D;

public class Menu {
	
	Button swave;
	
	public Menu() {
		swave = new Button("Старт", GamePanel.WIDTH/2, GamePanel.HEIGHT/2);
	}
	
	void update() {
		if(swave.update())
			GamePanel.s = GamePanel.Stat.GAME;
		
		GamePanel.click = false;
	}
	
	void draw(Graphics2D g) {
		swave.draw(g);
	}
}
