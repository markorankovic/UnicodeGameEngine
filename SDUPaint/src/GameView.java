import java.awt.Color;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.font.FontRenderContext;
import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JTextArea;

public class GameView extends JTextArea implements KeyListener, MouseListener, MouseMotionListener { 

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
	int fontWidth;
	int fontHeight;
	private Font font;
	char paint = '\u23F9';
	private boolean saveBoard = false;
	
	public GameView(int rows, int cols) {
		super();
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
		setEditable(false);
		setHighlighter(null);
		fontSize = 20;
		System.out.println(fontSize);
		Font font = new Font("Courier", Font.PLAIN, fontSize);
	    Rectangle rec = font.getStringBounds(" ",
	                        new FontRenderContext(
	                                font.getTransform(),
	                                false,
	                                false)).getBounds();
	    //System.out.println((rows*cols)*8/(1920*1080));
	    this.fontWidth = (int)(rec.getWidth());
	    this.fontHeight = (int)(rec.getHeight());
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
				if (saveBoard)  {
					spriteBoard.boardToFile();
					saveBoard = false;
				}
				
			}
		 }
		 for (Node child : node.children) {
			 child.processKeys(keysPressed);
			 renderNode(child, frame);
		 }
	}
	
	public void painting(MouseEvent arg0) {
		try {
		int xPos = (arg0.getX()/fontWidth) - (cols/2)+(int)scene.camera.position.x;
		int yPos = (arg0.getY()/fontHeight) - (rows/2)+(int)scene.camera.position.y;
		System.out.println(xPos);
		System.out.println(yPos);
		((DrawScene)scene).canvas.currentBoard.setCharacter(xPos, yPos, paint);
		} catch (ArrayIndexOutOfBoundsException e) {
			
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		scene.keyPressed(e);
		keysPressed.add(e.getKeyCode());
		if (e.getKeyCode() == KeyEvent.VK_1) {
			fontSize++;
			font = new Font("Courier", Font.PLAIN, fontSize);
			super.setFont(font);
		    Rectangle rec = font.getStringBounds(" ",
                    new FontRenderContext(
                            font.getTransform(),
                            false,
                            false)).getBounds();
		    this.fontWidth = (int)(rec.getWidth());
		    this.fontHeight = (int)(rec.getHeight());

			this.rows = (720/this.fontHeight);
			this.cols = (1280/this.fontWidth);
			super.setColumns(this.cols);
			super.setRows(this.rows);
		}
		if (e.getKeyCode() == KeyEvent.VK_2) {
			fontSize--;
			font = new Font("Courier", Font.PLAIN, fontSize);
			super.setFont(font);
		    Rectangle rec = font.getStringBounds(" ",
                    new FontRenderContext(
                            font.getTransform(),
                            false,
                            false)).getBounds();
		    this.fontWidth = (int)(rec.getWidth());
		    this.fontHeight = (int)(rec.getHeight());

			this.rows = (720/this.fontHeight);
			this.cols = (1280/this.fontWidth);
			super.setColumns(this.cols);
			super.setRows(this.rows);
		}
		if (e.getKeyCode() < 37 || e.getKeyCode() > 40) {
			paint = e.getKeyChar();
		}
		if (e.getKeyCode() == KeyEvent.VK_3) {
			paint = ' ';
		}
		if (e.getKeyCode() == KeyEvent.VK_4) {
			saveBoard = true;
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

	@Override
	public void mouseClicked(MouseEvent arg0) {
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		painting(arg0);
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		
	}

}