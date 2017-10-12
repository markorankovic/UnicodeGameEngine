import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class SpriteNode extends Node { 
	
	HashMap<String, Board[]> animations = new HashMap<String, Board[]>();
	Board currentBoard;
	boolean canRun = false;
	
	public Board getCurrentBoard() {
		return currentBoard;
	}
	
	public SpriteNode(Board board) {
		currentBoard = board;
		loadAnimations();
	}
	
	public SpriteNode(File file) {
		try {
			currentBoard = Board.convertFileToBoard(file);
			loadAnimations();
			System.out.println("File found!");
		} catch (IOException e) {
			System.out.println("File not found.");
		}
	}
	
	public SpriteNode(File file, Position position) {
		this(file);
		this.position = position;
	}
	
	public boolean collidesWith(SpriteNode node) {
		for (int r = 0; r < getCurrentBoard().getHeight(); r++) {
			for (int c = 0; c < getCurrentBoard().getWidth(); c++) {
				for (int r2 = 0; r2 < node.getCurrentBoard().getHeight(); r2++) {
					for (int c2 = 0; c2 < node.getCurrentBoard().getWidth(); c2++) {
						if (position.y + r == node.position.y + r2 && position.x + c == node.position.x + c2) {
							if (getCurrentBoard().characters[r][c] != ' ' && node.getCurrentBoard().characters[r2][c2] != ' ') {
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}
	
	void loadAnimations() {
		// Add arrays of boards to the animations HashMap.
	}
		
	void runAnim(String name, int i) { // To move to the Action class
		currentBoard = i < animations.get(name).length ? animations.get(name)[i] : animations.get("main")[0];
	}
	
	@Override
	public void processKeys(int KeyCode) {
		
	}
	
}
