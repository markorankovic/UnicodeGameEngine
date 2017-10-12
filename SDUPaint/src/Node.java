import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.Set;

public class Node { 
	
	double initialTime = 0;
	double initialSpeed = 0;
	double currentSpeed = 0;
	double previousFinalSpeed = 0;
	ArrayList<Node> children = new ArrayList<Node>();
	String name;
	Node parent;
	Position position = new Position(0, 0);
	int zPosition = 0;
	
	public Position getLocalPosition() {
		return new Position(position.x,position.y);
	}
	
	public Position getGlobalPosition() {
		if (parent == null) {
			return getLocalPosition();
		}
		Position parentPos = parent.getGlobalPosition();
		return new Position(position.x+parentPos.x,position.y+parentPos.y);
	}
	
	public void addChild(Node node) {
		if (node != null) {
			addInOrder(node);
			node.parent = this;
		} else {
			System.out.println("Error: Add child got nil node");
		}
	}
	
	public void addChild(Node node, Position position) {
		node.position = position;
		addChild(node);
	}
	
	public Node nodeWithName(String name) {
		for (Node child : this.children) {
			if (child.name == null) {
				continue;
			}
			if (child.name.equals(name)) {
				return child;
			}
			Node node = child.nodeWithName(name);
			if (node != null) {
				return node;
			}
		}
		return null;
	}
	
		private void addInOrder(Node node) {  // Insertion sort algorithm
			if (children.isEmpty()) {
				children.add(node);
			} else {
				for (int i = 0; i < children.size(); i++) {
					if (node.zPosition < children.get(i).zPosition) {
						children.add(i, node);
						break;
					} else {
						if (i == children.size() - 1) {
							children.add(i + 1, node);
							break;
						}
					}
				}
			}				
		}
	
	public void removeFromParent() {
		if (parent != null) {
			this.parent.children.remove(this);
			this.parent = null;
		}
	}
	
	public void keyTyped(KeyEvent e) {
		for (Node child : children) {
			child.keyTyped(e);
		}
	}
	public void keyPressed(KeyEvent e) {
		for (Node child : children) {
			child.keyPressed(e);
		}
	}
	public void keyReleased(KeyEvent e) {
		for (Node child : children) {
			child.keyReleased(e);
		}
		keyReleased();
	}
	
	public void keyReleased() {
		
	}
	
	public void processKeys(Set<Integer> keysPressed) {
		//System.out.println(keysPressed);
		try {
		for (Iterator<Integer> i = keysPressed.iterator(); i.hasNext();) {
			Integer keyCode = ((Integer)i.next());
			processKeys(keyCode);
		}
		} catch (ConcurrentModificationException e) {
			System.out.println("Key doesn't exist.");
		}
	}
	
	public void processKeys(int keyCode) {
		
	}
	
}
