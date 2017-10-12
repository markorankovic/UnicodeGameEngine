
public class PhysicsBody {

	Vector velocity;
	double mass;
	double linearDamping;
 	
	public PhysicsBody(Vector velocity, double mass, double linearDamping) {
		this.velocity = velocity;
		this.mass = mass;
		this.linearDamping = linearDamping;
	}
	
	public void update(long timeInterval, Node owner) {
		owner.position.x += velocity.dx * (1.0f/(1.0f + (GameScene.currentTime/1000) * linearDamping));
		owner.position.y += velocity.dy * (1.0f/(1.0f + (GameScene.currentTime/1000) * linearDamping));
	}
	
}
