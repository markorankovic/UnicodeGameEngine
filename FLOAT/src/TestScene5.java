import java.io.File;
import java.io.IOException;

public class TestScene5 extends GameScene {

	public TestScene5() {
		try {
			SpriteNode lara = new SpriteNode(Board.convertFileToBoard(new File("Sprites/lara.txt")));
			lara.position = new Position(150, 20);
			SpriteNode alien = new SpriteNode(Board.convertFileToBoard(new File("Sprites/alien.txt")));
			alien.position = new Position(80, 20);
			SpriteNode story = new SpriteNode(Board.convertFileToBoard(new File("Sprites/story.txt")));
			story.position = new Position(66, 5);
			addChild(story);
			//addChild(alien);
			//addChild(lara);
		} catch (IOException e) {
		}
	}
	
}
