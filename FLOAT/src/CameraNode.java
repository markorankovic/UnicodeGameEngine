import java.awt.event.KeyEvent;

public class CameraNode extends Node {
	
	public CameraNode() {
	}
	
	@Override 
	public void processKeys(int keyCode) {
		System.out.println("Camera" + "\n-------------------");
		System.out.println("Position: (" + position.x + "," + " " + position.y + ")");
		System.out.println("-------------------");
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
			position.y += 1;
		}
	}
	
}
