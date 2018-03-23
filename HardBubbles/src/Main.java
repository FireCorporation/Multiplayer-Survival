import javax.swing.JFrame;

public class Main {
	static JFrame f;
	static GamePanel gp;
	public static void main(String[] args) {
		gp = new GamePanel();
		f = new JFrame("HardBubbles");
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setLocationRelativeTo(null);
		f.setLocation(f.getLocation().x-gp.WIDTH/2, f.getLocation().y-gp.HEIGHT/2);
		f.setSize(gp.WIDTH, gp.HEIGHT);
		f.setResizable(false);
		f.setVisible(true);
		
		f.setContentPane(gp);
		
		gp.t.start();
		}
}
