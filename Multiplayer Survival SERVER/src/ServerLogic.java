import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JButton;
import javax.swing.JFrame;

public class ServerLogic extends JFrame implements Runnable{
	JButton b;
	
	static Thread t;
	
	Socket s;
	ServerSocket ss;
	ObjectInputStream input;
	ObjectOutputStream output;
	
	int mustFPS = 60;
	int mustTimer = 1000/mustFPS;
	int st;
	int timer;
	
	Background bg;
	Player p;
	Rock r;
	
	static int WIDTH = 800;
	static int HEIGHT = 500;
	
	ServerLogic(String str){
		super(str);
		
		t = new Thread(this);
		
		bg = new Background();
		p = new Player();
		r = new Rock();
		
		b = new JButton("Сохранить");
		b.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		b.setBounds(0, 0, 194, 96);
		add(b);
	}
	
	public void run() {
		try {
			ss = new ServerSocket(15666);
			s = ss.accept();
			output = new ObjectOutputStream(s.getOutputStream());
			input = new ObjectInputStream(s.getInputStream());
		} catch (Exception e) {e.printStackTrace();}
		while(true) {
			timer = (int)System.nanoTime()/1000000;
			collect();
			update();
			send();
			try {
				if((int)System.nanoTime()-timer<mustTimer) {
					st = (int)System.nanoTime()-timer-mustTimer;
				} else {
					st = 0;
				}
				Thread.sleep(st);
			} catch (Exception e) {}
		}
	}
	
	void collect() {
		
	}
	
	void update() {
		p.update();
		r.update();
		
		if(p.x+p.weight > r.x && p.x < r.x+r.weight && p.y+p.height == r.y)
			p.onGround = true;
	}
	
	void send() {
		try {
			bg.draw(output);
			p.draw(output);
			r.draw(output);
			output.writeUTF("");
		} catch (Exception e) {}
	}
}
