
public class TestScene3 extends GameScene {

	Ball ball;
	public static Ball ball2;
	Ball ball3;
	RadialField radialField;
	RepulsiveField repulsiveField;
	RepulsiveField repulsiveField2;
	
	public TestScene3() {
		ball = new Ball(10, new Position(0,0), new Vector(0,0), 1);
		ball3 = new Ball(10, new Position(120,80), new Vector(0,0), 1);
		repulsiveField = new RepulsiveField(ball.position, 5, 20, 2);
		repulsiveField2 = new RepulsiveField(ball3.position, 5, 20, 2);
		addChild(ball);
		radialField = new RadialField(new Position(85, 40), 20, 16, 2);
		ball2 = new Ball(1, new Position(85, 40), new Vector(0,0), 1);
		addChild(ball2);
		ball2.addChild(radialField);
		addChild(ball3);
		ball.addChild(repulsiveField);
		ball3.addChild(repulsiveField2);
	}
	
	@Override
	public void update(long timeInterval) {	
		
	}
	
}
