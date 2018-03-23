import javax.swing.JFrame;

public class Main {
	static GamePanel gp;
	public static void main(String[] args) {
		gp = new GamePanel("Multiplayer Survival");
		gp.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gp.setLocationRelativeTo(null);
		gp.setLocation(gp.getLocation().x-GamePanel.WIDTH/2, gp.getLocation().y-GamePanel.HEIGHT/2);
		gp.setSize(GamePanel.WIDTH, GamePanel.HEIGHT);
		gp.setResizable(false);
		gp.setLayout(null);
		gp.setVisible(true);
		
		GamePanel.t.start();
	}

}
