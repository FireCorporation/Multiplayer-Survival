import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Save {
	static void save() {
		try {
			ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream(new File("save.txt")));
			o.writeInt(Menu.score);
			o.writeInt(GamePanel.record);
			o.writeObject(Menu.charge);
			o.close();
		} catch (Exception e) {}
	}
	
	static void load() {
		try {
			ObjectInputStream i = new ObjectInputStream(new FileInputStream(new File("save.txt")));
			Menu.score = i.readInt();
			GamePanel.record = i.readInt();
			Menu.charge = (ShopButton)i.readObject();
			i.close();
		} catch (Exception e) {}
	}
}
