import java.awt.Color;
import java.awt.Graphics2D;

public class Enemy {
	
	double x;
	double y;
	double mass;
	double speed;
	double rdx;
	double rdy;
	
	public Enemy(double mass) {
		this.mass = mass;
		speed = 5/mass;
		
		int side = (int)Math.round(Math.random()*4-0.5);
		int r = (int)Math.sqrt((mass*10/Math.PI)/(10/Math.PI))*5;
		switch(side) {
		case 0:
			x = (int)(Math.random()*GamePanel.WIDTH);
			y = 0 - r;
			break;
		case 1:
			y = (int)(Math.random()*GamePanel.HEIGHT);
			x = GamePanel.WIDTH + r;
			break;
		case 2:
			x = (int)(Math.random()*GamePanel.WIDTH);
			y = GamePanel.HEIGHT + r;
			break;
		case 3:
			y = (int)(Math.random()*GamePanel.HEIGHT);
			x = 0 - r;
			break;
		}
		
		cw();
	}
	
	void cw() {
		double distX = Player.x - x;
		double distY = Player.y - y;
		double dist = Math.sqrt(distX*distX + distY*distY);
		
		rdx = distX/dist*speed;
		rdy = distY/dist*speed;
	}
	
	boolean cww() {
		int r = (int)Math.sqrt((mass*10/Math.PI)/(10/Math.PI))*5;
		
		if(x-r <= 0)
			return true;
		if(x+r >= GamePanel.WIDTH)
			return true;
		if(y-r <= 0)
			return true;
		if(y+r*7 >= GamePanel.HEIGHT)
			return true;
		
		return false;
	}
	
	void move() {
		if(cww())
			cw();
		
		double dx = 0;
		double dy = 0;
		
		try{
			dx = rdx*((double)GamePanel.mustFPS/GamePanel.FPS);
			dy = rdy*((double)GamePanel.mustFPS/GamePanel.FPS);
		}catch(Exception e){}
		
		x += dx;
		y += dy;
	}
	
	boolean cwb(int index) {
		double distX = x - GamePanel.b.get(index).x;
		double distY = y - GamePanel.b.get(index).y;
		double dist = Math.sqrt(distX*distX + distY*distY);
		
		if(dist <= (int)Math.sqrt((mass*10/Math.PI)/(10/Math.PI))*5 + GamePanel.b.get(index).r)
			return true;
		
		return false;
	}
	
	void destroy(int index) {
		for(int i = 0; i < GamePanel.b.size(); i++)
			if(cwb(i)) {
				GamePanel.b.remove(i);
				GamePanel.en.remove(index);
			}
	}
	
	void update(int index) {
		move();
		destroy(index);
	}
	
	void draw(Graphics2D g) {
		g.setColor(new Color(0, 255, 0));
		int r = (int)Math.sqrt((mass*10/Math.PI)/(10/Math.PI))*5;
		g.fillOval((int)x-(int)r, (int)y-(int)r, (int)r*2, (int)r*2);
	}
}
