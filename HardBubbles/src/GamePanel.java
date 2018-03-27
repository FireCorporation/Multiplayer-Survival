import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{
	static int WIDTH = 700;
	static int HEIGHT = 500;
	
	BufferedImage bi;
	Graphics2D g;
	
	Listener l;
	
	static int FPS;
	static int mustFPS;
	
	static int mouseX;
	static int mouseY;
	
	static ArrayList<Bullet> b;
	static ArrayList<Enemy> en;
	
	Waves w;
	Background bg;
	Player p;
	
	Thread t;
	
	public GamePanel() {
		bi = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D)bi.getGraphics();
		
		l = new Listener();
		addKeyListener(l);
		addMouseMotionListener(l);
		addMouseListener(l);
		
		mustFPS = 60;
		FPS = 1;
		
		mouseX = 0;
		mouseY = 0;
		
		b = new ArrayList<Bullet>();
		en = new ArrayList<Enemy>();
		
		w = new Waves();
		bg = new Background();
		p = new Player();
		
		t = new Thread(this);
	}
	
	public void run() {
		int mustTimer = 1000/mustFPS;
		int st;
		while(true) {
			int timer = (int)System.currentTimeMillis();
			update();
			draw();
			display();
			if((int)System.currentTimeMillis()-timer<=mustTimer) {
				st = mustTimer-((int)System.currentTimeMillis()-timer);
				FPS = mustFPS;
			} else {
				st = 0;
				FPS = mustFPS/(((int)System.currentTimeMillis()-timer)/mustTimer);
			}
			try{Thread.sleep(st);}catch(Exception e){}
		}
	}
	
	void update() {
		w.update();
		p.update();

		for(int i = 0; i < b.size(); i++)
			b.get(i).update(i);
		
		for(int i = 0; i < en.size(); i++)
			en.get(i).update(i);
	}
	
	void draw() {
		bg.draw(g);
		p.draw(g);
		
		for(int i = 0; i < b.size(); i++)
			b.get(i).draw(g);

		for(int i = 0; i < en.size(); i++)
			en.get(i).draw(g);
		
		g.setFont(new Font("Cursive", Font.BOLD, 10));
		g.setColor(new Color(0, 0, 0));
		g.drawString("FPS: "+FPS, 5, GamePanel.HEIGHT-35);
	}
	
	void display() {
		Graphics2D g2 = (Graphics2D)this.getGraphics();
		g2.drawImage(bi, 0, 0, WIDTH, HEIGHT, null);
		g2.dispose();
	}
}
