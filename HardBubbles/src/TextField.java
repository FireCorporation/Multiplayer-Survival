import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class TextField {
	
	double x;
	double y;
	int width;
	int height;
	int c;
	String name;
	String text;
	static String nt;
	boolean focus;
	int length;
	int lengthname;
	int t;
	boolean line;
	
	enum SYM{
		DIGITS,
		ALL,
		LETTERS
	}
	
	SYM s;
	
	public TextField(String name, int x, int y, int c, SYM s) {
		this.x = x;
		this.y = y;
		this.c = c;
		this.name = name+" ";
		this.s = s;
		
		width = c*18;
		height = 30;
		text = "";
		focus = false;
		
		nt = "";
		length = 0;
		lengthname = 0;
		t = 0;
		line = false;
	}
	
	void update() {
		if(GamePanel.click) {
			if(GamePanel.mouseX >= x-length/2+lengthname && GamePanel.mouseX <= x-length/2+lengthname+width && GamePanel.mouseY >= y-height/2 && GamePanel.mouseY <= y+height/2) {
				if(!focus) {
					focus = true;
					line = true;
				}
				GamePanel.click = false;
			} else {
				focus = false;
				t = 0;
				line = false;
			}
		}
		if(focus) {
			if(nt.equals("-")) {
				try{text = text.substring(0, text.length()-1);}catch(Exception e) {}
			} else if(s.equals(SYM.DIGITS))
				try{
					if(Integer.parseInt(nt) >= 0) {
						text += nt;
						if(text.length() > c)
							text = text.substring(0, c);
					}
				}catch(Exception e) {}
			if(!nt.equals("") && !(nt.equals("-") && text.equals(""))) {
				t = 0;
				line = true;
			}
			nt = "";
		}
	}
	
	void draw(Graphics2D g) {
		g.setColor(new Color(255, 255, 255));
		g.setStroke(new BasicStroke(3));
		g.setFont(new Font("Consolas", Font.BOLD, 30));
		lengthname = (int)g.getFontMetrics().getStringBounds(name, g).getWidth();
		length = lengthname+width;
		g.drawString(name, (int)x-length/2, (int)y+10);
		g.drawRect((int)x-length/2+lengthname, (int)y-height/2, width, height);
		if(focus) {
			int nt = (int)(500/(1000/GamePanel.FPS));
			t++;
			if(t >= nt) {
				t -= nt;
				line = !line;
			}
		}
		if(line && text.length() < c) {
			g.drawLine((int)x-length/2+lengthname+text.length()*17+5, (int)(y-height/3), (int)x-length/2+lengthname+text.length()*17+5, (int)(y+height/3));
		}
		
		g.drawString(text, (int)x-length/2+lengthname+5, (int)y+height/3);
	}
}
