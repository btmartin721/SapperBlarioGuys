import javax.swing.JFrame;
import java.awt.Toolkit;



@SuppressWarnings("serial")
public class Game extends JFrame
{
	// Contains main().
	
	View view;
	Model model;
	Controller controller;
	Mario mario;
		
	public Game()
	{
        // Constructor for Game class.
        model = new Model();
		controller = new Controller(model);
		view = new View(controller, model);
		view.screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		this.setTitle("Sapper Mario Guys");
		this.setSize(view.screenSize.getSize());
		this.setFocusable(true);
		this.getContentPane().add(view);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		view.addMouseListener(controller);
        this.addKeyListener(controller);
        view.addMouseMotionListener(controller);
	}

	public static void main(String[] args)
	{
		Game g = new Game();
		g.run();
	}
	
	public void run()
	{
        // Runs the program while JFrame is open.
		while(true)
		{
			controller.update();
			model.update();
			view.repaint(); // Indirectly calls View.paintComponent
			Toolkit.getDefaultToolkit().sync(); // Updates screen
	
			// Go to sleep for 40 miliseconds
			try
			{
				Thread.sleep(40);
			} catch(Exception e) {
				e.printStackTrace();
				System.exit(1);
			}
		}
	}
	
	
}
