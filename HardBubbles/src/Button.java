import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class Button {
	
	double x;
	double y;
	String name;
	int width;
	int height;
	int g;
	
	public Button(String name, double x, double y) {
		width = 120;
		height = 60;
		
		this.x = x;
		this.y = y;
		this.name = name;
		
		g = 0;
	}
	
	boolean update() {
		if(GamePanel.mouseX >= x-width/2 && GamePanel.mouseX <= x+width/2 && GamePanel.mouseY >= y-height/2 && GamePanel.mouseY <= y+height/2) {
			g = 80;
			if(GamePanel.click) {
				GamePanel.click = false;
				return true;
			}
		} else
			g = 0;
		return false;
	}
	
	void draw(Graphics2D g) {
		g.setColor(new Color(255, 255, 255));
		g.setStroke(new BasicStroke(3));
		g.drawRect((int)x-width/2, (int)y-height/2, width, height);
		g.setFont(new Font("Consolas", Font.BOLD, 40));
		int length = (int)g.getFontMetrics().getStringBounds(name, g).getWidth();
		g.drawString(name, (int)x-length/2, (int)y+10);
		g.setColor(new Color(255, 255, 255, this.g));
		g.fillRect((int)x-width/2, (int)y-height/2, width, height);
	}
}
