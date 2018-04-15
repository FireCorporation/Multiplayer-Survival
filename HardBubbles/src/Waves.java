
public class Waves {
	
	static int wave;
	
	public Waves() {
		wave = 0;
	}
	
	void update() {
		if(GamePanel.en.size() != 0)
			return;
		
		wave++;
		int MPW = wave*wave;
		
		while(MPW > 0) {
			int ec = (int)Math.round((Math.random()*(MPW-1)+1));
			MPW -= ec;
			GamePanel.en.add(new Enemy(ec));
		}
	}
}
