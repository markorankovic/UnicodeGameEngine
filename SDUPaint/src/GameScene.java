
public class GameScene extends Node { 
	
	public static long currentTime = 0;
	public static boolean actionsFirstLoop = true;
	public CameraNode camera;
	
	public GameScene() {
		camera = new CameraNode();
		addChild(camera);
	}

	public void updateFrame(long timeInterval) {
		GameScene.currentTime += timeInterval;
		update(timeInterval); // Update frame.
	}
	
	public void update(long timeInterval) {
		
	}
		
}
