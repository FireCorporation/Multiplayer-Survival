import java.awt.Color;
import java.awt.Graphics2D;

public class Background {
	
	public Background() {
		
	}
	
	public void draw(Graphics2D g) {
		g.setColor(new Color(0, 0, 255));
		g.fillRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
	}
}
