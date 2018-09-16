
import java.util.ArrayList;



class Mario 
{

	int x;
	int y;
	int w;
	int h;
	int top;
	int bottom;
	int left;
	int right;
	int prevX;
	int prevY;
	int solidGround;
	double velX;
	double yVel;
	double vert_vel;
	double gravity;
	boolean isCollision;
	boolean breakCollision;
	boolean jumping;
	boolean topCollision;
	int jumpHeight;
	double jumpStrength;
	int brickIDX;


	Mario(int _x, int _y, int _w, int _h)
	{
		x = _x; // Mario's X coordinate...
		y = _y;
		w = _w;
		h = _h;
		top = 0; // Mario's top side.
		bottom = 0; // Mario's bottom side.
		left = 0; // Mario's left side.
		right = 0; // Mario's right side.
		velX = 25.1; // For running.
		yVel = 30.1; // For jumping.
		vert_vel = 0.0; // For gravity.
		gravity = 1.2;
		isCollision = false;
		breakCollision = false;
		jumping = false;
		topCollision = false;
		solidGround = 0;
		jumpHeight = 30;
		jumpStrength = 30.1;
		brickIDX = 0;
		
	}
	
	void update(int groundY, ArrayList<Brick> bricks)
	{
		
		solidGround++;
		
		
  		if(jumping)
		{
			vert_vel = jumpStrength;
			y -= vert_vel;
			
			jumpStrength -= 1.2;
		}
		else if(!jumping && !topCollision)
		{
			jumpStrength = yVel;
			vert_vel += gravity;
			y += vert_vel;
		}
			
		
		
		if(y > groundY) // If on the ground, snap back to ground.
		{
			vert_vel = 0.0;
			y = groundY; // snap back to the ground
			solidGround = 0;
		}
		
		if(x < 0) // If off the map to the left.
			x = 0; // Snap back to map.
		
		
		this.getMarioBounds(x, y, w, h); // Gets top, bottom, left right of Mario.
	
		
		for(int i = 0; i < bricks.size(); i++)
		{
			Brick b = bricks.get(i); // For each brick in ArrayList<Brick>.
			//brickPos = b.setBrickMapPos(b.x);
			b.getBrickBounds(b.x, b.y, b.w, b.h); // Get top, bottom, left, right of current brick.
			
			isCollision = checksCollision(b);
			
			topCollision = false;
			if(isCollision)// If collision.
			{
				brickIDX = i;
				
				topCollision = breakCollision(b); // Get side of collision.
				if(topCollision)
				{
					solidGround = 0;
				}
						
			}
			else if(!isCollision)
			{
				
				if(topCollision)
				{
					topCollision = breakCollision(b);
					if(topCollision)
					{
						vert_vel = 0.0;
					}
				}
				
 				System.out.println("break here");
			}
		}
		
		prevX = x; // Gets Mario's previous x coordinate.
		prevY = y; // Gets Mario's previous y coordinate.
		
	}
	
	boolean checksCollision(Brick _b)
	{
		// Returns true if collision occurs.
		if(this.right <= _b.left)
			return false;
		if(this.left >= _b.right)
			return false;
		if(this.bottom <=_b.top) // assumes bigger is downward
			return false;
		if(this.top >= _b.bottom) // assumes bigger is downward
			return false;
		return true;
	}
	
	
	boolean breakCollision(Brick _b)
	{
		// Gets side of collision.
		int x_offset = 0;
		int y_offset = 0;
		
		int prevLeft = prevX;
		int prevRight = prevX + w;
		int prevTop = prevY;
		int prevBottom = prevY + h;
		
		boolean topColl = false;
		
		if(this.right >= _b.left && prevRight < _b.left) // Left side collision.
		{
			// Mario to left of brick.
			x_offset = this.right - _b.left;
			x = x - x_offset - 1;
			topColl = false;
		}
		else if(this.left <= _b.right && prevLeft > _b.right) // Right side collision.
		{
			// Mario to right of brick.
			x_offset = this.left - _b.right;
			x = x - x_offset + 1; // 1 pixel to right of brick.
			topColl = false;
		}
		
		if(this.top <= _b.bottom && prevTop > _b.bottom) // Assumes downward is bigger.
		{
			// Mario below brick.
			y_offset = this.top - _b.bottom;
			y = y - y_offset + 1; // 1 pixel below brick.
			topColl = false;
		}
		else if (this.bottom >=_b.top && prevBottom <= _b.top) // Assumes downward is bigger.
		{
			// Mario on top of brick.
			y_offset = this.bottom - _b.top;
			//y = y - y_offset - 1; // 1 pixel above brick.
			y = _b.top - this.h - 1;
			topColl = true;
		}
					
		return topColl;
	}
	
	
	public void getMarioBounds(int _x, int _y, int _w, int _h)
	{
		// Gets Mario's top, bottom, left, right coordinates.
		top = _y;
		bottom = _y + _h;
		left = _x;
		right = _x + _w;
	}
			
}
