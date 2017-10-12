
public class Action { // Due for review

	boolean initialized = false;
	double duration;
	double previousFinalSpeed = 0.0;
	Vector delta;
	Vector initialDelta;
	Node target;
	String name;
	Position initialPos = null;	
	
	public Action(Vector delta, Double duration) {
		this.delta = delta;
		this.duration = duration;
		name = "moveBy";
	}
	
	public static Action moveBy(Vector delta, Double duration) {
		return new Action(delta, duration);
	}
	
	public void moveByCall(Node driver, double duration) {
		double t = (GameScene.currentTime - driver.initialTime)/duration;
		driver.position.x = initialPos.x + t*delta.dx;
		driver.position.y = initialPos.y + t*delta.dy;
		if (t >= 1.0) {
			t = 1.0;
			driver.stopAction(this);
		} 
	}
	
	public Action(Node node) {
		this.target = node;
		name = "followNode";
	}
	
	public static Action followNode(Node node) {
		return new Action(node);
	}
	
	public void followNodeCall(Node stalker) {
		double hypotenuse = Math.hypot(stalker.position.x-target.position.x, stalker.position.y-target.position.y);
		double sinTheta = (stalker.position.y-target.position.y)/hypotenuse;
		double cosTheta = (stalker.position.x-target.position.x)/hypotenuse;
		stalker.position.x -= stalker.currentSpeed * cosTheta;
		stalker.position.y -= stalker.currentSpeed * sinTheta;
		//System.out.println(stalker.currentSpeed);
	} 
		
	public static double accelerateTowards(double finalSpeed, double duration, Node node) {
		double deltaT = (GameScene.currentTime - node.initialTime);
		if (deltaT > duration) {
			deltaT = duration;
		}
		if (node.previousFinalSpeed == finalSpeed || GameScene.actionsFirstLoop ) {
			node.previousFinalSpeed = finalSpeed;
			return node.initialSpeed + (((deltaT)*(finalSpeed-node.initialSpeed))/(duration));
		} else {
			node.initialTime = GameScene.currentTime;
			node.initialSpeed = node.currentSpeed;
			node.previousFinalSpeed = finalSpeed;
		}
		return node.currentSpeed;
	}
					
	public void run(Node node) {
		if (!initialized) {
			node.initialTime = GameScene.currentTime;
			initialPos = new Position(node.position.x, node.position.y);
			if (target != null) {
				initialDelta = new Vector(node.position.x-target.position.x, node.position.y-target.position.y);
				delta = initialDelta;
			}
			initialized = true;
		}
		try {
			switch (name) {
			case "moveBy": moveByCall(node, duration);
			case "followNode": followNodeCall(node);
			}
		} catch (Exception e) {
			
		}
	}
				
}
