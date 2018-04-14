import java.awt.Dimension;

import javax.swing.JFrame;

public class Main {
	static JFrame f;
	static GamePanel gp;
	public static void main(String[] args) {
		gp = new GamePanel();
		f = new JFrame("HardBubbles");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);
		f.setLocation(f.getLocation().x-GamePanel.WIDTH/2, f.getLocation().y-GamePanel.HEIGHT/2);
		f.setResizable(false);
		f.setVisible(true);
		
		gp.setPreferredSize(new Dimension(GamePanel.WIDTH, GamePanel.HEIGHT));
		
		f.setContentPane(gp);
		f.pack();
		
		gp.setFocusable(true);
		gp.requestFocus();
		gp.t.start();
		}
}
