import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Menu {
	
	enum Stat{
		MAIN,
		SHOP,
		PLAY,
		BOSSES,
		ONLINE,
		CONNECT,
		HOST
	}
	
	static Stat s;
	
	Button swave;
	Button shop;
	Button back;
	Button bosses;
	Button play;
	Button online;
	Button host;
	Button connect;
	Button hostok;
	Button connectok;
	
	TextField hostp;
	
	static ShopButton charge;
	
	String ek, record, sscore;
	static int score;
	
	public Menu() {
		s = Stat.MAIN;
		
		play = new Button("»√–¿“‹", GamePanel.WIDTH/2, GamePanel.HEIGHT/2);
		shop = new Button("Ã¿√¿«»Õ", GamePanel.WIDTH/2, GamePanel.HEIGHT/1.5);
		back = new Button("Õ¿«¿ƒ", GamePanel.WIDTH/2, GamePanel.HEIGHT/1.125);
		swave = new Button("¬ŒÀÕ€", GamePanel.WIDTH/2, GamePanel.HEIGHT/2);
		bosses = new Button("¡Œ——€", GamePanel.WIDTH/2, GamePanel.HEIGHT/3);
		online = new Button("ONLINE", GamePanel.WIDTH/2, GamePanel.HEIGHT/3);
		host = new Button("HOST", GamePanel.WIDTH/2, GamePanel.HEIGHT/3);
		connect = new Button("CONNECT", GamePanel.WIDTH/2, GamePanel.HEIGHT/2);
		hostok = new Button("Œ ", GamePanel.WIDTH/2, GamePanel.HEIGHT/1.5);
		connectok = new Button("Œ ", GamePanel.WIDTH/2, GamePanel.HEIGHT/1.5);
		
		hostp = new TextField("œŒ–“:", GamePanel.WIDTH/2, GamePanel.HEIGHT/2, 6, TextField.SYM.DIGITS);
		
		charge = new ShopButton("«¿–ﬂƒ ¿", 20, GamePanel.WIDTH/4, GamePanel.HEIGHT/6);
		
		ek = "Ã¿——€ –¿—Ÿ»œÀ≈ÕŒ - ";
		record = "–≈ Œ–ƒ - ";
		sscore = "Œ◊ » - ";
		score = 0;
	}
	
	void update() {
		if(s.equals(Stat.MAIN)) {
			if(play.update())
				s = Stat.PLAY;
			
			if(online.update())
				s = Stat.ONLINE;
			
			if(shop.update())
				s = Stat.SHOP;
		} else if(s.equals(Stat.SHOP)) {
			if(back.update())
				s = Stat.MAIN;
			
			charge.update();
		} else if(s.equals(Stat.PLAY)) {
			if(swave.update()) {
				GamePanel.ek = 0;
				GamePanel.b = new ArrayList<Bullet>();
				GamePanel.en = new ArrayList<Enemy>();
				GamePanel.p = new Player();
				Waves.wave = 0;
				GamePanel.s = GamePanel.Stat.GAME;
			}
			
			if(bosses.update())
				s = Stat.BOSSES;
			
			if(back.update())
				s = Stat.MAIN;
		} else if(s.equals(Stat.ONLINE)) {
			if(host.update())
				s = Stat.HOST;
			
			if(connect.update())
				s = Stat.CONNECT;
			
			if(back.update())
				s = Stat.MAIN;
		} else if(s.equals(Stat.HOST)) {
			hostp.update();
			
			if(hostok.update())
				Online.host(Integer.parseInt(hostp.text));
			
			if(back.update())
				s = Stat.ONLINE;
		} else if(s.equals(Stat.CONNECT)) {
			if(back.update())
				s = Stat.ONLINE;
		} else if(s.equals(Stat.BOSSES)) {
			if(back.update())
				s = Stat.PLAY;
		}
		
		TextField.nt = "";
		GamePanel.click = false;
	}
	
	void draw(Graphics2D g) {
		if(s.equals(Stat.MAIN)) {
			play.draw(g);
			online.draw(g);
			shop.draw(g);
	
			g.setFont(new Font("Consolas", Font.BOLD, 30));
			g.setColor(new Color(255, 255, 255));
			if(GamePanel.ek != 0) {
				int lengthek = (int)g.getFontMetrics().getStringBounds(ek+GamePanel.ek, g).getWidth();
				g.drawString(ek+GamePanel.ek, GamePanel.WIDTH/2 - lengthek/2, 40);
			}
			if(GamePanel.record != 0) {
				int lengthrecord = (int)g.getFontMetrics().getStringBounds(record+GamePanel.record, g).getWidth();
				g.drawString(record+GamePanel.record, GamePanel.WIDTH/2 - lengthrecord/2, GamePanel.HEIGHT-10);
			}
		} else if(s.equals(Stat.SHOP)) {
			back.draw(g);
			charge.draw(g);
			
			g.setFont(new Font("Consolas", Font.BOLD, 30));
			g.setColor(new Color(255, 255, 255));
			int lengthsscore = (int)g.getFontMetrics().getStringBounds(sscore+score, g).getWidth();
			g.drawString(sscore+score, GamePanel.WIDTH/2 - lengthsscore/2, 40);
		} else if(s.equals(Stat.PLAY)) {
			swave.draw(g);
			bosses.draw(g);
			back.draw(g);
		} else if(s.equals(Stat.ONLINE)) {
			host.draw(g);
			connect.draw(g);
			back.draw(g);
		} else if(s.equals(Stat.HOST)) {
			hostp.draw(g);
			back.draw(g);
		} else if(s.equals(Stat.CONNECT)) {
			back.draw(g);
		} else if(s.equals(Stat.BOSSES)) {
			back.draw(g);
		}
	}
}
