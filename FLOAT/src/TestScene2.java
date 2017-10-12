
public class TestScene2 extends GameScene {

	Ball ball1;
	Ball ball2;
	RadialField radialField;
	
	public TestScene2() {
		//ball1 = new Ball(10, new Position(25, 50), new Vector(0,0), 1);
		ball2 = new Ball(7, new Position(0, 0), new Vector(0,0), 1);
		//radialField = new RadialField(new Position(50, 50), 1, 20, 2);
		//addChild(ball1);
		addChild(new CameraNode());
		addChild(ball2);
		addChild(radialField);
	}

	@Override
	public void update(long timeInterval) {
		//radialField.update(timeInterval);
		//ball1.repulsiveField.update(timeInterval);
		//ball2.repulsiveField.update(timeInterval);
	}
	
}
