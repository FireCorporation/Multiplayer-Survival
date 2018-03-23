import java.io.ObjectOutputStream;

public class Rock {
	double x;
	double y;
	int weight;
	int height;
	
	Rock(){
		x = 100;
		y = 300;
		weight = 80;
		height = 80;
	}
	
	void update() {
		
	}
	
	void draw(ObjectOutputStream output) throws Exception{
		output.writeUTF("rock.png");
		output.writeInt((int)x);
		output.writeInt((int)y);
	}
}
