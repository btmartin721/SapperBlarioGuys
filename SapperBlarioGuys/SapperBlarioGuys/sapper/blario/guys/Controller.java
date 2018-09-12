package sapper.mario.assignment3;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.lang.Math;
import java.awt.event.MouseMotionListener; // For mouseDragged and mouseMoved.


class Controller implements MouseListener, KeyListener, MouseMotionListener
{
	// Class to implement mouse clicks/drags and key presses.
	
	// Initialize member variables.
    View view;
    Model model;
    boolean keyLeft;
	boolean keyRight;
	boolean keyUp;
	boolean keyDown;
	boolean keyS;
	boolean keyL;
	boolean inDrag;
	boolean noCollision;
	boolean lastKeyRight;
	int mouseClickX;
	int mouseClickY;
	boolean leftBarrier;
	boolean topBarrier;
	boolean rightBarrier;
	boolean bottomBarrier;
	int movePixels;
	int marioPos;
	int brickPosition;
	double xVelocity;

	Controller(Model m)
	{
		// Class constructor.
		model = m;
		inDrag = false;
		keyLeft = false;
		keyRight = false;
		keyUp = false;
		keyDown = false;
		keyS = false;
		keyL = false;
		lastKeyRight = true;
		noCollision = true;
		leftBarrier = true;
		topBarrier = true;
		rightBarrier = true;
		bottomBarrier = true;
		movePixels = 0;
		marioPos = 0;
		xVelocity = 1.0;
	}
	
	void setView(View v)
	{
        // Set reference to View class
        view = v;
	}
	
	@Override
	public void mousePressed(MouseEvent e)
	{
        // Gets location of mouse click for class Model
		mouseClickX = e.getX(); // Original X value when clicked.
		mouseClickY = e.getY(); // Original Y value when clicked.
		inDrag = true;
		model.dragOn();
		model.initializeBrick(mouseClickX, mouseClickY, 0, 0);
				
	}
    
	@Override
	public void mouseReleased(MouseEvent e) 
	{    
		// Overrides mouseReleased operator.
        int x1 = mouseClickX;
		int x2 = e.getX();
		int y1 = mouseClickY;
		int y2 = e.getY();
		int left = Math.min(x1,  x2);
		int right = Math.max(x1,  x2);
		int top = Math.min(y1,  y2);
		int bottom = Math.max(y1,  y2);
		model.makeBricks(left, top, right - left, bottom - top);
        inDrag = false;
        model.dragOff();
	}
	
	@Override
	public void mouseDragged(MouseEvent e) 
	{
		// Overrides mouseDragged operator.
		
		if(inDrag)
		{	
			model.initializeBrick(e.getX(), e.getY(), 0, 0);
			int x1 = mouseClickX;
			int x2 = e.getX();
			int y1 = mouseClickY;
			int y2 = e.getY();
			int left = Math.min(x1,  x2);
			int right = Math.max(x1,  x2);
			int top = Math.min(y1,  y2);
			int bottom = Math.max(y1,  y2);
			model.getDrag(left, top, right - left, bottom - top);
			view.repaint();
		}
	}
	
	public void mouseEntered(MouseEvent e) {    }
	public void mouseExited(MouseEvent e) {    }
	public void mouseClicked(MouseEvent e) 
	{  
		if(e.getY() < 100)
		{
			System.out.println("break here");
		}
	}
	public void mouseMoved(MouseEvent e) {    } // Necessary to implement mouseMotionListener.

	@Override
	public void keyPressed(KeyEvent e)
	{
        // True if keys pressed. 
        
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_RIGHT: keyRight = true; lastKeyRight = true; break; // Arrow right key.
			case KeyEvent.VK_LEFT: keyLeft = true; lastKeyRight = false; break; // Arrow left key.
			case KeyEvent.VK_UP: keyUp = true; break; // Arrow up key.
			case KeyEvent.VK_DOWN: keyDown = true; break; // Arrow down key.
			case KeyEvent.VK_S: keyS = true; break; // S key.
			case KeyEvent.VK_L: keyL = true; break; // L key.
		}
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
        // False if keys released.
        
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_RIGHT: keyRight = false; break; // Arrow right key.
			case KeyEvent.VK_LEFT: keyLeft = false; break; // Arrow left key.
			case KeyEvent.VK_UP: keyUp = false; break; // Arrow up key.
			case KeyEvent.VK_DOWN: keyDown = false; break; // Arrow down key.
			case KeyEvent.VK_S: keyS = false; break; // S key.
			case KeyEvent.VK_L: keyL = false; break; // L key.
		}
	}

	public void keyTyped(KeyEvent e)
	{
	}

	void update()
	{
				
		
		
		movePixels = model.movePixels;
        // What to do when specific keys are pressed.

			if(keyRight) // Scrolls screen right.
			{
				if(model.collisionDetected == false)
				{
					this.marioPos = model.marioPosition();
				
					model.setMove(true);
					model.setVelX(xVelocity);
					
					int frame = 0;
					frame = view.cycleMario(view.mario_images_right);
					view.current_mario = view.mario_images_right[frame];
				}
				else if(model.collisionDetected)
				{
					//model.setMove(false);
					this.marioPos = model.marioPosition();
					model.setVelX(0.0);

					int frame = 0;
					view.current_mario = view.mario_images_right[frame];
					
				}	
			} 
			else if (keyRight == false && lastKeyRight == true)
			{
				model.setMove(false);
				model.setVelX(0.0);
				view.current_mario = view.mario_images_right[0];
			}

			if(keyLeft) // Scrolls screen left.
			{
				
				if(model.collisionDetected == false)
				{
					
					if(model.cameraPos < 0) 
						model.cameraPos = 0; // Doesn't let camera move < 0.

					this.marioPos = model.marioPosition();
					
					if(this.marioPos < 0)
					{
						model.setMove(false);
						model.setVelX(0.0);
					}
					else
					{
						model.setMove(true);
						model.setVelX(-xVelocity);
					}
					
					int frame = 0;
					frame = view.cycleMario(view.mario_images_left);
					view.current_mario = view.mario_images_left[frame];
				}
				else if(model.collisionDetected)
				{
					//model.setMove(false);
					this.marioPos = model.marioPosition();
					model.setVelX(0.0);

						
					int frame = 0;
					view.current_mario = view.mario_images_left[frame];
				}
		
			}
			else if (keyLeft == false && lastKeyRight == false)
			{
				model.setMove(false);
				model.setVelX(0.0);
				view.current_mario = view.mario_images_left[0];
			}
		
		model.rightPanelX = model.cameraPos + view.getWidth();
		
		
		if(keyS)
		{
			// Saves Model objects to Json object.
			Json brickList = model.marshall();
			brickList.save("map.Json"); // Save to file "map.Json".
		}
		
		if(keyL)
		{
			// Loads Model objects from Json object.
			model.clearBricks();
			Json loaded = Json.load("map.Json");
			model.unmarshall(loaded); // Load from file "map.Json".
		}
	}
	
}


