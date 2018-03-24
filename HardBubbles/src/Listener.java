import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Listener implements KeyListener, MouseMotionListener, MouseListener {

	public void mouseDragged(MouseEvent e) {
		
	}

	public void mouseMoved(MouseEvent e) {
		GamePanel.mouseX = e.getX();
		GamePanel.mouseY = e.getY();
	}

	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_W)
			Player.up = true;
		
		if(e.getKeyCode() == KeyEvent.VK_S)
			Player.down = true;
		
		if(e.getKeyCode() == KeyEvent.VK_D)
			Player.right = true;
		
		if(e.getKeyCode() == KeyEvent.VK_A)
			Player.left = true;
	}

	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_W)
			Player.up = false;
		
		if(e.getKeyCode() == KeyEvent.VK_S)
			Player.down = false;
		
		if(e.getKeyCode() == KeyEvent.VK_D)
			Player.right = false;
		
		if(e.getKeyCode() == KeyEvent.VK_A)
			Player.left = false;
	}

	public void keyTyped(KeyEvent e) {

	}

	public void mouseClicked(MouseEvent e) {
		
	}

	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
		
	}

	public void mousePressed(MouseEvent e) {
		if(e.getButton() == 1)
			Player.fire = true;
	}

	public void mouseReleased(MouseEvent e) {
		if(e.getButton() == 1)
			Player.fire = false;
	}

}
