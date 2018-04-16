import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class Player {
	
	static double x;
	static double y;
	int r;
	int rSpeed;
	int charge;
	int ttc;
	
	String scharge;
	
	static boolean fire;
	static boolean up;
	static boolean down;
	static boolean right;
	static boolean left;
	
	public Player() {
		r = 5;
		
		x = GamePanel.WIDTH/2-r;
		y = GamePanel.HEIGHT/2-r;
		
		rSpeed = 5;
		
		charge = 0;
		ttc = 0;
		
		scharge = "ÇÀÐßÄ - ";
		
		fire = false;
		up = false;
		down = false;
		right = false;
		left = false;
	}
	
	void move() {
		double speed = 0;
		try{speed = rSpeed*((double)GamePanel.mustFPS/GamePanel.FPS);}catch(Exception e){}
		
		double dx = 0;
		double dy = 0;
		
		if(up)
			dy = -speed;
		if(down)
			dy = speed;
		if(right)
			dx = speed;
		if(left)
			dx = -speed;
		
		if(left && x-r <= 0)
			dx = 0;
		if(right && x+r >= GamePanel.WIDTH)
			dx = 0;
		if(up && y-r <= 0)
			dy = 0;
		if(down && y+r >= GamePanel.HEIGHT)
			dy = 0;
		
		if((up && right) || (up && left) || (down && right) || (down && left)) {
			double angle = Math.toRadians(45);
			dx *= Math.sin(angle);
			dy *= Math.cos(angle);
		}
		
		x += dx;
		y += dy;
	}
	
	void fire() {
		if(fire && charge >= 1) {
			charge--;
			GamePanel.b.add(new Bullet());
		}
	}
	
	void destroy() {
		for(int i = 0; i < GamePanel.en.size(); i++) {
			double ex = GamePanel.en.get(i).x;
			double ey = GamePanel.en.get(i).y;
			double er = GamePanel.en.get(i).r;
			
			double distX = ex-x;
			double distY = ey-y;
			double dist = Math.sqrt(distX*distX + distY*distY);
			
			if(dist <= er+r) {
				if(GamePanel.ek > GamePanel.record)
					GamePanel.record = GamePanel.ek;
				Menu.score = GamePanel.ek;
				Save.save();
				GamePanel.s = GamePanel.Stat.MENU;
			}
		}
	}
	
	void charge() {
		int cs = 1000/Menu.charge.level;
		int tcs = cs/(1000/GamePanel.FPS);
		ttc++;
		if(ttc >= tcs) {
			ttc -= tcs;
			charge++;
		}
	}
	
	void update() {
		move();
		charge();
		fire();
		destroy();
	}
	
	void draw(Graphics2D g) {
		g.setColor(new Color(255, 0, 100));
		g.fillOval((int)x-r, (int)y-r, r*2, r*2);
		
		if(!GamePanel.s.equals(GamePanel.Stat.MENU)) {
			g.setFont(new Font("Consolas", Font.BOLD, 15));
			g.setColor(new Color(255, 255, 255));
			int length = (int)g.getFontMetrics().getStringBounds(scharge+charge, g).getWidth();
			g.drawString(scharge+charge, GamePanel.WIDTH-length-5, GamePanel.HEIGHT-5);
		}
	}
}
