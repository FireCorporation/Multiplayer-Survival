import java.awt.Color;
import java.awt.Graphics2D;

public class Player {
	
	double x;
	double y;
	int speed;
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
	
	void update() {
		try{speed = rSpeed*(GamePanel.mustFPS/GamePanel.FPS);}catch(Exception e){}
		
		int dx = 0;
		int dy = 0;
		
		if(up)
			dy = -speed;
		if(down)
			dy = speed;
		if(right)
			dx = speed;
		if(left)
			dx = -speed;
		
		if(left && x <= 0)
			dx = 0;
		if(right && x >= GamePanel.WIDTH-r*3)
			dx = 0;
		if(up && y <= 0)
			dy = 0;
		if(down && y >= GamePanel.HEIGHT-r*8)
			dy = 0;
		
		if((up && right) || (up && left) || (down && right) || (down && left)) {
			double angle = Math.toRadians(45);
			dx *= Math.sin(angle);
			dy *= Math.cos(angle);
		}
		
		x += dx;
		y += dy;
	}
	
	void draw(Graphics2D g) {
		g.setColor(new Color(255, 0, 100));
		g.fillOval((int)x, (int)y, r*2, r*2);
	}
}
