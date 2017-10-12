
public class Ball extends SpriteNode {
		
	//RepulsiveField repulsiveField;
	
	public Ball(int radius, Position position, Vector velocity, double mass) {
		this(new Board(radius, radius));
		physicsbody = new PhysicsBody(velocity, mass, 0.8);
		this.position = position;
		name = "Ball";
		//this.repulsiveField = new RepulsiveField(new Position(position.x,position.y), 0, 15, 2);
		//addChild(repulsiveField);
		renderCircle();
	}
	
	public Ball(Board board) {
		super(board);
	}

	private void renderCircle() {
		for (int row = 0; row < currentBoard.getHeight(); row++) {
			for (int col = 0; col < currentBoard.getWidth(); col++) {
				double distance = Math.hypot(Math.abs(col - (currentBoard.getWidth()-1)/2.0), Math.abs(row - (currentBoard.getHeight()-1)/2.0));
				if (Math.ceil(distance) <= currentBoard.getWidth()/2.0) {
					currentBoard.characters[row][col] = 'O';
				} else {
					currentBoard.characters[row][col] = ' ';
				}
			}
		}
	}
		
}
