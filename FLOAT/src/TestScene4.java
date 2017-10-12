import java.io.File;

public class TestScene4 extends GameScene {

	Satellite satellite;
	UFO ufo;
	UFO ufo2;
	
	public TestScene4() {
		satellite = new Satellite(new File("Sprites/satellite.txt"), new Position(30, 50));
		ufo = new UFO(new Position(100, 0),satellite);
		ufo2 = new UFO(new Position(200, 0),satellite);
		addChild(satellite);
		addChild(ufo);
		addChild(ufo2);
		addChild(new UFO(new Position(600, 0),satellite));
		addChild(new UFO(new Position(400, 0),satellite));
		addChild(new UFO(new Position(300, 0),satellite));
		camera.position.x = satellite.position.x + 17;
		camera.position.y = satellite.position.y + 5;
	}
	
	@Override 
	public void update(long timeInterval) {
		for (Node node : children) {
			if (node instanceof UFO) {
				((UFO)node).update(timeInterval);
			}
		}
	}
	
}
