import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.io.Serializable;

public class ShopButton implements Serializable	{
	
	double x;
	double y;
	String name;
	int width;
	int height;
	int g;
	int level;
	int cost;
	String scost;
	String slevel;
	
	public ShopButton(String name, int cost, double x, double y) {
		width = 120;
		height = 60;
		scost = "ÖÅÍÀ - ";
		slevel = " LV.";
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
		Color c;
		if(level == 10)
			c = new Color(255, 255, 0);
		else if(Menu.score < cost)
			c = new Color(127, 127, 127);
		else
			c =  new Color(255, 255, 255);
		
		g.setColor(c);
		g.setStroke(new BasicStroke(3));
		g.drawRect((int)x-width/2, (int)y-height/2, width, height);
		if(level != 10) {
			g.setFont(new Font("Consolas", Font.BOLD, 20));
			int lengthname = (int)g.getFontMetrics().getStringBounds(name, g).getWidth();
			g.setFont(new Font("Consolas", Font.BOLD, 10));
			int lengthlevel = (int)g.getFontMetrics().getStringBounds(slevel+level, g).getWidth();
			int length = lengthname+lengthlevel;
			g.setFont(new Font("Consolas", Font.BOLD, 20));
			g.drawString(name, (int)x-length/2, (int)y+2);
			g.setFont(new Font("Consolas", Font.BOLD, 10));
			g.drawString(slevel+level, (int)x-length/2+lengthname, (int)y+2);
			g.setFont(new Font("Consolas", Font.BOLD, 12));
			int lengthscost = (int)g.getFontMetrics().getStringBounds(scost+cost, g).getWidth();
			g.drawString(scost+cost, (int)x-lengthscost/2, (int)y+18);
		} else {
			g.setColor(c);
			g.setFont(new Font("Consolas", Font.BOLD, 25));
			int length = (int)g.getFontMetrics().getStringBounds(name, g).getWidth();
			g.drawString(name, (int)x-length/2, (int)y+10);
		}
		g.setColor(new Color(c.getRed(), c.getGreen(), c.getBlue(), this.g));
		g.fillRect((int)x-width/2, (int)y-height/2, width, height);
	}
}
