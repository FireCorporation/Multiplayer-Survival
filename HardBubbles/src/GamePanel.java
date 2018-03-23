import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{
	int WIDTH = 700;
	int HEIGHT = 500;
	
	BufferedImage bi;
	Graphics2D g;
	
	Thread t;
	
	public GamePanel() {
		bi = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D)bi.getGraphics();
		
		t = new Thread(this);
	}
	
	public void run() {
		while(true) {
			update();
			draw();
			display();
		}
	}
	
	void update() {
		
	}
	
	void draw() {
		
	}
	
	void display() {
		Graphics2D g2 = (Graphics2D)this.getGraphics();
		g2.drawImage(bi, 0, 0, WIDTH, HEIGHT, null);
		g2.dispose();
	}
}
