import java.awt.Color;
import java.awt.Graphics2D;

public class Player {
	
	static double x;
	static double y;
	int r;
	int rSpeed;
	
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
		if(right && x+r*2 >= GamePanel.WIDTH)
			dx = 0;
		if(up && y-r <= 0)
			dy = 0;
		if(down && y+r*7 >= GamePanel.HEIGHT)
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
		if(fire)
			GamePanel.b.add(new Bullet());
	}
	
	void destroy() {
		
	}
	
	void update() {
		move();
		fire();
		destroy();
	}
	
	void draw(Graphics2D g) {
		g.setColor(new Color(255, 0, 100));
		g.fillOval((int)x-r, (int)y-r, r*2, r*2);
	}
}
