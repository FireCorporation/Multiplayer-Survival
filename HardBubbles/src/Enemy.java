import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Enemy {
	
	double x;
	double y;
	int mass;
	double r;
	double speed;
	double rdx;
	double rdy;
	
	static BufferedImage bi;
	
	public Enemy(int mass) {
		this.mass = mass;
		r = (int)Math.sqrt(mass*100/Math.PI);
		speed = 20/r;
		
		int side = (int)Math.round(Math.random()*4-0.5);
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
	}
	
	public Enemy(int mass, double x, double y) {
		this.mass = mass;
		r = (int)Math.sqrt(mass*100/Math.PI);
		speed = 20/r;
		
		this.x = x;
		this.y = y;
		
		double angle = Math.toRadians(Math.random()*359);
		rdx = Math.cos(angle)*speed;
		rdy = Math.sin(angle)*speed;
	}
	
	void cw() {
		double distX = Player.x - x;
		double distY = Player.y - y;
		double dist = Math.sqrt(distX*distX + distY*distY);
		
		rdx = distX/dist*speed;
		rdy = distY/dist*speed;
	}
	
	boolean cww() {
		if(x-r <= 0 && rdx <= 0)
			return true;
		if(x+r >= GamePanel.WIDTH && rdx >= 0)
			return true;
		if(y-r <= 0 && rdy <= 0)
			return true;
		if(y+r >= GamePanel.HEIGHT && rdy >= 0)
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
		
		if(dist <= r + GamePanel.b.get(index).r)
			return true;
		
		return false;
	}
	
	void destroy(int index) {
		for(int i = 0; i < GamePanel.b.size(); i++)
			if(cwb(i)) {
				GamePanel.b.remove(i);
				try{
					GamePanel.en.remove(index);
					GamePanel.ek++;
					int MPW = mass-1;
					while(MPW > 0) {
						int ec = (int)Math.round((Math.random()*(MPW-1)+1));
						MPW -= ec;
						GamePanel.en.add(new Enemy(ec, x, y));
					}
				}catch(Exception e){}
			}
	}
	
	void update(int index) {
		move();
		destroy(index);
	}
	
	void draw(Graphics2D g) {
		g.drawImage(bi, (int)(x-r), (int)(y-r), (int)r*2, (int)r*2, null);
	}
}
