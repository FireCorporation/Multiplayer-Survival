import javax.swing.JFrame;

public class Main {
	static ServerLogic sl;
	public static void main(String[] args) {
		sl = new ServerLogic("Multiplayer Survival SERVER");
		sl.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		sl.setSize(200, 125);
		sl.setResizable(false);
		sl.setLayout(null);
		sl.setVisible(true);
		
		ServerLogic.t.start();
	}

}
