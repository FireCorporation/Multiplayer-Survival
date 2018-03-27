
public class Waves {
	
	int wave;
	
	public Waves() {
		wave = 0;
	}
	
	void update() {
		if(GamePanel.en.size() != 0)
			return;
		wave++;
		int MPW = wave*wave;
		int ec = (int)Math.round((Math.random()*(MPW-1)+1));
		int mfe = MPW/ec;
		
		for(int i = 0; i < ec; i++)
			GamePanel.en.add(new Enemy(mfe));
	}
}
