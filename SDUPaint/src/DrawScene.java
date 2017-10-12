import java.io.File;
import java.io.IOException;

public class DrawScene extends GameScene {

	SpriteNode canvas;
	Ship ship = new Ship(new Position(30, 30));
	
	public DrawScene() {
		canvas = new SpriteNode(new Board(2000, 2000, ' '));
		//try {
		//	canvas = new SpriteNode(Board.convertFileToBoard(new File("C:/Users/marko/Desktop/savedBoard.txt")));
		//} catch (IOException e) {
		//}
		addChild(canvas);
		//addChild(ship);
	}
	
	@Override
	public void update(long timeInterval) {
		//camera.position = ship.position;
	}
	
}
