
public class TestScene extends GameScene {
	
	RadialField radialField = new RadialField(new Position(60, 60), 1, 30, 2);
	Ball ball1 = new Ball(10, new Position(40, 50), new Vector(0,0), 1);
	Ball ball2 = new Ball(10, new Position(60, 70), new Vector(0,0), 1);
	Ball ball3 = new Ball(10, new Position(100, 20), new Vector(0,0), 1);

	public TestScene() {
		addChild(ball1);
		addChild(ball2);
		addChild(ball3);
		addChild(radialField);
	}
	
	@Override 
	public void update(long timeInterval) {
		radialField.update(timeInterval);
		//ball1.repulsiveField.position = ball1.position;
		//ball2.repulsiveField.position = ball2.position;
		//ball3.repulsiveField.position = ball3.position;
		
		//ball1.repulsiveField.update(timeInterval);
		//ball2.repulsiveField.update(timeInterval);
		//ball3.repulsiveField.update(timeInterval);
	}
	
}
