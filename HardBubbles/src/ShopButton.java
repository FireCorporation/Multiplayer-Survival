import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.Serializable;

import javax.imageio.ImageIO;

public class ShopButton implements Serializable	{
	
	double x;
	double y;
	String name;
	int width;
	int height;
	int level;
	int cost;
	String scost;
	String slevel;
	BufferedImage bic, bis, bil, big;
	boolean selected;
	
	public ShopButton(String name, int cost, double x, double y) {
		width = 120;
		height = 60;
		scost = "ÖÅÍÀ - ";
		slevel = " LV.";
		level = 1;
		selected = false;
		
		try {
			bic = ImageIO.read(new File("images/button/classic.png"));
			bis = ImageIO.read(new File("images/button/selected.png"));
			bil = ImageIO.read(new File("images/button/locked.png"));
			big = ImageIO.read(new File("images/button/gold.png"));
		}catch(Exception e) {}
		
		this.x = x;
		this.y = y;
		this.name = name;
		this.cost = cost;
	}
	
	void update() {
		if(GamePanel.mouseX >= x-width/2 && GamePanel.mouseX <= x+width/2 && GamePanel.mouseY >= y-height/2 && GamePanel.mouseY <= y+height/2) {
			selected = true;
			if(GamePanel.click) {
				GamePanel.click = false;
				if(level < 10 && Menu.score >= cost) {
					Menu.score -= cost;
					cost *= 1.5;
					level++;
				}
			}
		} else
			selected = false;
	}
	
	void draw(Graphics2D g) {
		if(level == 10)
			g.drawImage(big, (int)x-width/2, (int)y-height/2, width, height, null);
		else if(Menu.score < cost)
			g.drawImage(bil, (int)x-width/2, (int)y-height/2, width, height, null);
		else if(!selected)
			g.drawImage(bic, (int)x-width/2, (int)y-height/2, width, height, null);
		else
			g.drawImage(bis, (int)x-width/2, (int)y-height/2, width, height, null);

		if(level != 10) {
			g.setColor(new Color(255, 255, 255));
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
			g.setColor(new Color(255, 255, 0));
			g.setFont(new Font("Consolas", Font.BOLD, 25));
			int length = (int)g.getFontMetrics().getStringBounds(name, g).getWidth();
			g.drawString(name, (int)x-length/2, (int)y+10);
		}
	}
}
