import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.font.FontRenderContext;
import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JTextArea;

public class GameView extends JTextArea implements KeyListener { 

	private static final long serialVersionUID = 1L;
	public static GameScene scene;
	private Timer timer;
	public static Set<Integer> keysPressed = new HashSet<Integer>();
	boolean pauseOff = true;
	public static final long period = 15;
	private GameTask gameTask;
	boolean timerInstantiated = false;
	int cols;
	int rows;
	int fontSize;
	
	public GameView(int rows, int cols) {
		super();
		addKeyListener(this);
		setEditable(false);
		setHighlighter(null);
		fontSize = ((rows*cols)*8/(1280*720));
		Font font = new Font("Courier", Font.PLAIN, fontSize);
	    Rectangle rec = font.getStringBounds(" ",
	                        new FontRenderContext(
	                                font.getTransform(),
	                                false,
	                                false)).getBounds();
	    System.out.println((rows*cols)*8/(1920*1080));
	    int fontWidth = (int)(rec.getWidth());
	    int fontHeight = (int)(rec.getHeight());
		setFont(font);
		setBackground(Color.black);
		setForeground(Color.yellow);
		System.out.println("View" + "\n-------------------" + "\nRows: " + rows/fontHeight + "\nCols: " + cols/fontWidth + "\n-------------------");
		System.out.println();
		this.rows = (rows/fontHeight);
		this.cols = (cols/fontWidth);
		super.setColumns(this.cols);
		super.setRows(this.rows);
	}
	
	class GameTask extends TimerTask {
		@Override
		public void run() {
			gameLoop();
		}
	}
	
	public void presentScene(final GameScene scene) {
		if (timerInstantiated) {
			timer.cancel();
			timer.purge();
		}
		GameView.scene = scene;
		timer = new Timer();
		gameTask = new GameTask();
		timer.schedule(gameTask, 0, period);
		timerInstantiated = true;
	}
	
	private void gameLoop() {
		scene.updateFrame(period);
		Board frame = new Board(this.cols, this.rows, ' ');
		renderNode(scene, frame);
		setText(frame.toString());
	}
	
	public void renderNode(Node node, Board frame) {
		 if (node instanceof SpriteNode) {
			Board spriteBoard = ((SpriteNode)node).getCurrentBoard(); 
			if (spriteBoard != null) {
				frame.addBoard(spriteBoard, (int)Math.round(node.position.y-(scene.camera.position.y-rows/2)), (int)Math.round(node.position.x-(scene.camera.position.x-cols/2)));
			}
		 }
		 for (Node child : node.children) {
			 child.processKeys(keysPressed);
			 renderNode(child, frame);
		 }
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		scene.keyPressed(e);
		keysPressed.add(e.getKeyCode());
		if (e.getKeyCode() == KeyEvent.VK_Z) {
			fontSize++;
			super.setFont(new Font("Courier", Font.PLAIN, fontSize));
		}
		if (e.getKeyCode() == KeyEvent.VK_X) {
			fontSize--;
			super.setFont(new Font("Courier", Font.PLAIN, fontSize));
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		scene.keyReleased(e);
		if (keysPressed.contains(e.getKeyCode())) {
	        keysPressed.remove(e.getKeyCode());
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		scene.keyTyped(e);
	}

}