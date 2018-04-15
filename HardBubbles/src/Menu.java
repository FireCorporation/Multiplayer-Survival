import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class Menu {
	
	enum Stat{
		MAIN,
		SHOP,
		BOSSES
	}
	
	static Stat s;
	
	Button swave;
	Button shop;
	Button back;
	Button bosses;
	
	static ShopButton charge;
	
	String ek, record, sscore;
	static int score;
	
	public Menu() {
		s = Stat.MAIN;
		
		swave = new Button("—“¿–“", GamePanel.WIDTH/2, GamePanel.HEIGHT/2);
		shop = new Button("Ã¿√¿«»Õ", GamePanel.WIDTH/2, GamePanel.HEIGHT/1.5);
		back = new Button("Õ¿«¿ƒ", GamePanel.WIDTH/2, GamePanel.HEIGHT/1.125);
		bosses = new Button("¡Œ——€", GamePanel.WIDTH/2, GamePanel.HEIGHT/3);
		
		charge = new ShopButton("«¿–ﬂƒ ¿", 40, GamePanel.WIDTH/4, GamePanel.HEIGHT/6);
		
		ek = "Ã¿——€ –¿—Ÿ»œÀ≈ÕŒ - ";
		record = "–≈ Œ–ƒ - ";
		sscore = "Œ◊ » - ";
		score = 0;
	}
	
	void update() {
		if(s.equals(Stat.MAIN)) {
			if(swave.update()) {
				GamePanel.ek = 0;
				GamePanel.b = new ArrayList<Bullet>();
				GamePanel.en = new ArrayList<Enemy>();
				GamePanel.p = new Player();
				Waves.wave = 0;
				GamePanel.s = GamePanel.Stat.GAME;
			}
			
			if(shop.update())
				s = Stat.SHOP;
			
			if(bosses.update())
				s = Stat.BOSSES;
				
		} else if(s.equals(Stat.SHOP)) {
			if(back.update())
				s = Stat.MAIN;
			
			charge.update();
		} else if(s.equals(Stat.BOSSES)) {
			if(back.update())
				s = Stat.MAIN;
		}
		
		GamePanel.click = false;
	}
	
	void draw(Graphics2D g) {
		if(s.equals(Stat.MAIN)) {
			swave.draw(g);
			shop.draw(g);
			bosses.draw(g);
	
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
		} else if(s.equals(Stat.BOSSES)) {
			back.draw(g);
		}
	}
}
