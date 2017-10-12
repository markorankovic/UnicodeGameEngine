
import java.io.File;

public class UFO extends SpriteNode {
	
	Node target;
	
	public UFO(Position position, Node target) {
		super(new File("Sprites/ufo.txt"), position);
		name = "UFO";
		this.target = target;
		this.initialSpeed = 3;
		this.physicsbody = new PhysicsBody(new Vector(), 1, 0);
		addChild(new RepulsiveField(new Position(), 3, 10, 2));
		follow();
	}
	
	void follow() {
		runAction(Action.followNode(target));
	}
	
	public void update(long timeInterval) {
		//System.out.println(currentSpeed);
		double hypotenuse = Math.hypot(position.x-target.position.x, position.y-target.position.y);
		if (hypotenuse < 50) {
			currentSpeed = Action.accelerateTowards(0, 1000, this);
		} else if (hypotenuse > 50) {
			currentSpeed = Action.accelerateTowards(3, 1000, this);
		}
	}
								
}
 