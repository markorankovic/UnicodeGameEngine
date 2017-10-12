
public class RadialField extends FieldNode {

	double sign;
	
	public RadialField(Position position, double strength, double minRadius, double falloff, double sign) {
		super(position, strength, minRadius, falloff);
		this.sign = sign;
	}
	
	public RadialField(Position position, double strength, double minimumDistance, double falloff) {
		this(position, minimumDistance, strength, falloff, 1.0);
	}
	
	@Override
	public void update(long timeInterval) {
		//System.out.println("RadialField");
		//System.out.println("----------------------------------------------");
		Node root = GameView.scene;
		for (Node child : root.children) {
			if (child.equals(parent)) {
				position = child.position;
			}
			if (child instanceof SpriteNode && !child.equals(parent) && !child.equals(TestScene3.ball2)) {
				//System.out.println(sign);
				if (child.physicsbody != null) {
					double distance = Math.hypot((position.y-child.position.y),(position.x-child.position.x));
					distance = max(distance, minRadius);
					//System.out.println("minRadius: " + minRadius);
					//System.out.println("distance: " + distance);
					double force = (strength*child.physicsbody.mass)-(Math.hypot(child.physicsbody.velocity.dx, child.physicsbody.velocity.dy));
					double acceleration = force*sign/(Math.pow(distance, falloff));
					
					double angle = sign * Math.atan2((position.y-child.position.y),(position.x-child.position.x));
					double dx = child.physicsbody.velocity.dx + acceleration * Math.cos(angle);
					double dy = child.physicsbody.velocity.dy + acceleration * Math.sin(angle);
					child.physicsbody.velocity = new Vector(dx, dy);  
					
					//System.out.println("angle: " + angle * 180/Math.PI + ", force: " + acceleration + ", dx: " + dx + ", dy: " + dy);
					//System.out.println("xPos: " + child.position.x + ", yPos: " + child.position.y);
					//System.out.println("Distance: " + distance);
					//System.out.println(child.name);
					//System.out.println("----------------------------------------------");
					//System.out.println();
				}
			}
		}
	}
	
	double max(double a, double b) {
		if (a > b) {
			return a;
		}
		return b;
	}
	
}
