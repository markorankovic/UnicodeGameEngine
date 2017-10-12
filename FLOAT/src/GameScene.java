
public class GameScene extends Node { 
	
	public static long currentTime = 0;
	public static boolean actionsFirstLoop = true;
	public CameraNode camera;
	
	public GameScene() {
		camera = new CameraNode();
		addChild(camera);
	}

	public void updateFrame(long timeInterval) {
		GameScene.currentTime += timeInterval;
		doAllActionsInChildrenOf(this); // Do all actions.
		simulatePhysicsInChildrenOf(this, timeInterval); // Simulate physics.
		update(timeInterval); // Update frame.
	}
	
	private void simulatePhysicsInChildrenOf(Node node, long timeInterval) {
		for (Node n: node.children) {
			if (n instanceof FieldNode) {
				((FieldNode)n).update(timeInterval);
			}
			if (n.physicsbody != null) {
				n.physicsbody.update(timeInterval, n);
			}
			simulatePhysicsInChildrenOf(n, timeInterval);
		}
	}

	public void update(long timeInterval) {
		
	}
	
	private void doAllActionsInChildrenOf(Node node) {
		for (Node n : node.children) {
			if (!n.actionsToAdd.isEmpty()) {
				n.onGoingActions.addAll(n.actionsToAdd);
				n.actionsToAdd.removeAll(n.actionsToAdd);
			}
			if (!n.actionsToRemove.isEmpty()) {
				n.onGoingActions.removeAll(n.actionsToRemove); 
				n.actionsToRemove.removeAll(n.actionsToRemove);
			}
			for (Action action : n.onGoingActions) {
				action.run(n);
			}
			doAllActionsInChildrenOf(n);
		}
		actionsFirstLoop = false;
		return;
	}
	
}
