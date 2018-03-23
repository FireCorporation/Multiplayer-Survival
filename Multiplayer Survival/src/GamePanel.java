import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

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
	
	Listener l;
	
	ArrayList<String> keys;
	int[] mouse;
	
	GamePanel(String str) {
		super(str);
		
		t = new Thread(this);
		keys = new ArrayList<String>();
		mouse = new int[2];
		l = new Listener();
		addKeyListener(l);
		addMouseListener(l);
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
		try{
			output.writeObject(keys);
			output.writeObject(mouse);
		}catch(Exception e){}
		keys.removeAll(keys);
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
	
	class Listener implements KeyListener, MouseListener{
		public void mouseClicked(MouseEvent e) {
			System.out.println(1);
		}
		public void mouseEntered(MouseEvent e) {
			System.out.println(2);
		}
		public void mouseExited(MouseEvent e) {
			System.out.println(3);
		}
		public void mousePressed(MouseEvent e) {
			System.out.println(4);
		}
		public void mouseReleased(MouseEvent e) {
			System.out.println(5);
		}
		public void keyPressed(KeyEvent e) {
			
		}
		public void keyReleased(KeyEvent e) {
			
		}
		public void keyTyped(KeyEvent e) {
			
		}
		
	}
}
