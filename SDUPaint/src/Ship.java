import java.awt.event.KeyEvent;
import java.io.File;

public class Ship extends SpriteNode {

	public Ship(Position position) {
		super(new File("Sprite/satellite.txt"), position);
	}

	@Override 
	public void processKeys(int keyCode) {
		if (keyCode == KeyEvent.VK_LEFT) {
			position.x -= 3;
		}
		if (keyCode == KeyEvent.VK_RIGHT) {
			position.x += 3;
		}
		if (keyCode == KeyEvent.VK_UP) {
			position.y -= 3;
		}
		if (keyCode == KeyEvent.VK_DOWN) {
			position.y += 3;
		}
	}

}
