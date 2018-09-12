package sapper.mario.assignment3;
import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JPanel;
import javax.xml.ws.Service.Mode;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.awt.Image;
import java.io.IOException;
import java.io.File;
import java.awt.Dimension;
import java.lang.Math;



@SuppressWarnings("serial")
class View extends JPanel
{
	// Class to handle view on screen.
	
	// Initialize Member variables.
	Model model;
	Dimension screenSize;
	BufferedImage brick_image;
	BufferedImage ground_block;
	Image[] mario_images_right;
	Image[] mario_images_left;
	Image current_mario;
	int currentFrame;
	int mapSize;
	int groundCoordY;
	int getMiddle;
	int marioCameraPos;
	int marioMapPos;
	
	
	View(Controller c, Model m)
	{
        // View class constructor.
        c.setView(this);
        m.setView(this);
        
        model = m;
               
    	// Loads Brick image from file.
		this.brick_image = loadImage("brick.png");
		this.ground_block = loadImage("ground_block.png");
		
		// Loads Mario facing right images into Image[]
        mario_images_right = new Image[6];
        mario_images_right[0] = loadImage("mario_standing_right.png");
		mario_images_right[1] = loadImage("mario1_right.png");
		mario_images_right[2] = loadImage("mario2_right.png");
		mario_images_right[3] = loadImage("mario3_right.png");
		mario_images_right[4] = loadImage("mario4_right.png");
		mario_images_right[5] = loadImage("mario5_right.png");
		
		
		// Loads Mario facing left images into Image[]
        mario_images_left = new Image[6];
        mario_images_left[0] = loadImage("mario_standing_left.png");
    	mario_images_left[1] = loadImage("mario1_left.png");
		mario_images_left[2] = loadImage("mario2_left.png");
		mario_images_left[3] = loadImage("mario3_left.png");
		mario_images_left[4] = loadImage("mario4_left.png");
		mario_images_left[5] = loadImage("mario5_left.png");
        
        current_mario = mario_images_right[0];
               
	}
	
	public void paintComponent(Graphics g)
	{
        // Paints objects to screen.
		groundCoordY = this.getHeight() - model.mario.h - 50;
		mapSize = this.getWidth() * 2;
		//marioCameraPos = this.getWidth() / 2;
		//model.marioPosX = marioCameraPos; // This puts Mario in the center of the panel.
		model.cameraWidth = this.getWidth();
		
        g.setColor(new Color(128, 255, 255)); // Set color of Jpanel.
		g.fillRect(0, 0, this.getWidth(), this.getHeight()); // Fills Jpanel rectangle.
		
		Brick d = model.drag; // Brick object while dragging.

		g.drawImage(current_mario, model.mario.x, model.mario.y, model.mario.w, model.mario.h, null);

		
		if(model.dragged)
		{
			// Draws brick while dragging. Shuts off when mouseReleased triggered.
			g.drawImage(this.brick_image, d.x, d.y, d.w, d.h, null);
		}
		
		
		
		for(int i = 0; i < model.bricks.size(); i++)
		{
			// Draws each Brick in ArrayList<Brick>.
			Brick b = model.bricks.get(i);

			g.drawImage(this.brick_image, b.x, b.y, b.w, b.h, null);
		}
        
		for(int i = 0; i <= mapSize; i+=50)
		{
			g.drawImage(ground_block, i, this.getHeight() - 50, 50, 50, null);
		}
				
		
		
		
		
	}
	
	private BufferedImage loadImage(String file)
	{
		BufferedImage img = null;
		try
		{
        	// Loads image from file.
			img =
				ImageIO.read(new File(file));
		} catch(Exception e) {
			e.printStackTrace(System.err);
			System.exit(1); // Die if image file not found.
		}
		
		return img;

	}
	
	public int cycleMario(Image[] img_array)
	{
		this.currentFrame++;
		if (this.currentFrame >= img_array.length)
		{
			currentFrame = 0;
		}
		
		return currentFrame;
	}
	
	
	
	
}
