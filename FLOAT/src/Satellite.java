import java.awt.event.KeyEvent;
import java.io.File;

public class Satellite extends SpriteNode {
	
	double speed = 5;	
	double dy = 0;
	double dx = 0;
	public Satellite(File file, Position position) {
		super(file, position);
		name = "Satellite";
	}	
	
	@Override
	public void processKeys(int keyCode) {
		if (keyCode == KeyEvent.VK_D) {
			dx = speed;
		} 
		if (keyCode == KeyEvent.VK_A) {
			dx = -speed;
		} 
		if (keyCode == KeyEvent.VK_W) {
			dy = -speed;
		} 
		if (keyCode == KeyEvent.VK_S) {
			dy = speed;
		} 
		runAction(Action.moveBy(new Vector(dx,dy),30.0));
	}
	
	@Override
	public void keyReleased() {
		dx = 0;
		dy = 0;
	}
	
}
