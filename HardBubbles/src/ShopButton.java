import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class ShopButton {
	
	double x;
	double y;
	String name;
	int width;
	int height;
	int g;
	int level;
	int cost;
	String scost;
	
	public ShopButton(String name, int cost, double x, double y) {
		width = 120;
		height = 60;
		scost = "ÖÅÍÀ - ";
		level = 1;
		
		this.x = x;
		this.y = y;
		this.name = name;
		this.cost = cost;
		
		g = 0;
	}
	
	void update() {
		if(GamePanel.mouseX >= x-width/2 && GamePanel.mouseX <= x+width/2 && GamePanel.mouseY >= y-height/2 && GamePanel.mouseY <= y+height/2) {
			g = 70;
			if(GamePanel.click) {
				GamePanel.click = false;
				if(level < 10 && Menu.score >= cost) {
					Menu.score -= cost;
					cost *= 1.5;
					level++;
				}
			}
		} else
			g = 0;
	}
	
	void draw(Graphics2D g) {
		g.setColor(new Color(255, 255, 255));
		g.setStroke(new BasicStroke(3));
		g.drawRect((int)x-width/2, (int)y-height/2, width, height);
		g.setFont(new Font("Consolas", Font.BOLD, 20));
		int length = (int)g.getFontMetrics().getStringBounds(name, g).getWidth();
		g.drawString(name, (int)x-length/2, (int)y+12);
		int lengthscost = (int)g.getFontMetrics().getStringBounds(scost+cost, g).getWidth();
		g.drawString(scost+cost, (int)x-lengthscost/2, (int)y-7);
		g.setColor(new Color(255, 255, 255, this.g));
		g.fillRect((int)x-width/2, (int)y-height/2, width, height);
	}
}
