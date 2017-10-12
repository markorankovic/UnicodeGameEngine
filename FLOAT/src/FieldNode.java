
public class FieldNode extends Node {
	
	double strength; // Field strength.
	double minRadius; // Any distance shorter than this value will be treated as equal.
	double falloff; // Exponent defining the rate of decay for the field strength.
	
	public FieldNode(Position position, double strength, double minRadius, double falloff) {
		this.position = position;
		this.strength = strength;
		this.minRadius = minRadius;
		this.falloff = falloff;
	}
	
	public void update(long timeInterval) {
		
	}

}
