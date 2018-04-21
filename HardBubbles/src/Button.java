import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

public class Button {
	
	double x;
	double y;
	String name;
	int width;
	int height;
	boolean selected;
	BufferedImage bic, bis;
	
	public Button(String name, double x, double y) {
		width = 120;
		height = 60;
		selected = false;
		
		try {
			bic = ImageIO.read(new File("images/button/classic.png"));
			bis = ImageIO.read(new File("images/button/selected.png"));
		}catch(Exception e) {}
		
		this.x = x;
		this.y = y;
		this.name = name;
	}
	
	boolean update() {
		if(GamePanel.mouseX >= x-width/2 && GamePanel.mouseX <= x+width/2 && GamePanel.mouseY >= y-height/2 && GamePanel.mouseY <= y+height/2) {
			selected = true;
			if(GamePanel.click) {
				GamePanel.click = false;
				return true;
			}
		} else
		selected = false;
		return false;
	}
	
	void draw(Graphics2D g) {
		if(!selected)
			g.drawImage(bic, (int)x-width/2, (int)y-height/2, width, height, null);
		else
			g.drawImage(bis, (int)x-width/2, (int)y-height/2, width, height, null);
		
		g.setColor(new Color(255, 255, 255));
		g.setFont(new Font("Consolas", Font.BOLD, 20));
		int length = (int)g.getFontMetrics().getStringBounds(name, g).getWidth();
		g.drawString(name, (int)x-length/2, (int)y+5);
	}
}
