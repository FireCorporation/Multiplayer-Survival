import java.io.ObjectOutputStream;

public class Background {
	void draw(ObjectOutputStream output) throws Exception {
		output.writeUTF("background.png");
		output.writeInt(0);
		output.writeInt(0);
	}
}
