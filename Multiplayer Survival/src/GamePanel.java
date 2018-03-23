import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class GamePanel extends JFrame implements Runnable{
	static int WIDTH = 800;
	static int HEIGHT = 500;
	
	static Thread t;
	
	BufferedImage bi = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	Graphics2D g = (Graphics2D)bi.getGraphics();
	Socket s;
	ObjectInputStream input;
	ObjectOutputStream output;
	
	GamePanel(String str) {
		super(str);
		
		t = new Thread(this);
	}
	
	public void run() {
		try {
			s = new Socket(InetAddress.getByName("127.0.0.1"), 15666);
			input = new ObjectInputStream(s.getInputStream());
			output = new ObjectOutputStream(s.getOutputStream());
		}catch(Exception e) {e.printStackTrace();}
		sendDetails();
		while(true) {
			draw();
			display();
			send();
		}
	}
	
	void sendDetails() {
		//try {output.writeChars("");} catch (Exception e) {}
	}
	
	void send() {
		
	}
	
	void draw() {
		try {
			while(true) {
				String ins = input.readUTF();
				if(!ins.equals(""))
					g.drawImage(ImageIO.read(new File("resources/images/"+ins)), input.readInt(), input.readInt(), null);
				else
					break;
			}
		}catch(Exception e) {e.printStackTrace();}
	}
	
	void display() {
		Graphics2D g2 = (Graphics2D)this.getGraphics();
		g2.drawImage(bi, 0, 0, WIDTH, HEIGHT, null);
		g2.dispose();
	}
}
