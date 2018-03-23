import java.io.ObjectOutputStream;

public class Player {
	double x;
	double y;
	int weight;
	int height;
	boolean onGround;
	
	Player(){
		x = 100;
		y = 100;
		weight = 80;
		height = 120;
	}
	
	void update() {
		double dx = 0;
		double dy = 0;
		
		if(!onGround)
			dy = -1;
		
		x += dx;
		y -= dy;
	}
	
	void draw(ObjectOutputStream output) throws Exception{
		output.writeUTF("player.png");
		output.writeInt((int)x);
		output.writeInt((int)y);
	}
}
