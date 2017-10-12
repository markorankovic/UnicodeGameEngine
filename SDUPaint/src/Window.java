
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;

public class Window extends JFrame implements ActionListener { 

	private static final long serialVersionUID = 1L;
	public static GameView view;
	
	public Window(int width, int height) {
		GameScene scene = new DrawScene();
		view = new GameView(height, width);
		view.presentScene(scene);
		setSize(width, height);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		add(view);
		setUndecorated(true);
		setVisible(true);
		setResizable(false);
		this.setLocationRelativeTo(null); 
		setTitle("FLOAT");
	}
	
	public static void main(String[] args) {
		new Window(1920, 1080);
	}
	
	public static void exit() {
		System.exit(DISPOSE_ON_CLOSE);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
	}
	
}
