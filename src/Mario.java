
import java.util.ArrayList;



public class Mario 
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
	double velX;
	double velY;
	double vert_vel;


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
		velY = 30; // For jumping.
		vert_vel = 0.0; // For gravity.
		
	}
	
	void update(int groundY, ArrayList<Brick> bricks)
	{
		
			
		vert_vel += 1.2; // Gravity.
		y += vert_vel;
					
		if(y > groundY) // If on the ground, snap back to ground.
		{
			vert_vel = 0.0;
			y = groundY; // snap back to the ground
		}
		
		if(x < 0) // If off the map to the left.
			x = 0; // Snap back to map.
		
		
		this.getMarioBounds(x, y, w, h); // Gets top, bottom, left right of Mario.
	
		
		for(int i = 0; i < bricks.size(); i++)
		{
			Brick b = bricks.get(i); // For each brick in ArrayList<Brick>.
			//brickPos = b.setBrickMapPos(b.x);
			b.getBrickBounds(b.x, b.y, b.w, b.h); // Get top, bottom, left, right of current brick.
			
			if(checksCollision(b)) // If collision.
			{
				int[] side = getPreviousPosition(b); // Get side of collision.
				
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
		if(this.bottom <= _b.top) // assumes bigger is downward
			return false;
		if(this.top >= _b.bottom) // assumes bigger is downward
			return false;
		return true;
	}
	
	
	int[] getPreviousPosition(Brick _b)
	{
		// Gets side of collision.
		int x_offset = 0;
		int y_offset = 0;
		int[] sides = new int[2];
		
		
		if(this.right > _b.left && this.left < _b.left && x > prevX)
		{
			// Mario to left of brick.
			x_offset = this.right - _b.left;
			x = x - x_offset;
			sides[0] = 1;
		}
		else if(this.left < _b.right && this.right > _b.right && x < prevX)
		{
			// Mario to right of brick.
			x_offset = _b.right - this.left;
			x = x + x_offset;
			sides[0] = -1;
		}
		else
		{
			// No left or right collision.
			sides[0] = 0;
		}
		if(this.top > _b.bottom && this.bottom < _b.bottom && y < prevY) // Assumes downward is bigger.
		{
			// Mario below brick.
			y_offset = _b.bottom - this.top;
			sides[1] = 1;
		}
		else if (this.bottom > _b.top && this.top < _b.top && y > prevY) // Assumes downward is bigger.
		{
			// Mario on top of brick.
			y_offset = this.bottom - _b.top;
			y = y - y_offset;
			sides[1] = -1;
		}
		else
		{
			// No top or bottom collision.
			sides[1] = 0;
		}
		
		if(sides[0] == 1 && sides[1] == 1 )
		{
			// Bottom left corner.
			if(x_offset < y_offset)
				x -= x_offset; // X is closer.
			else if(x_offset > y_offset)
				y += y_offset; // Y is closer.
		}
		else if (sides[0] == 1 && sides[1] == -1)
		{
			// Top left of brick.
			if(x_offset < y_offset)
				x -= x_offset;
			else if(x_offset > y_offset)
				y -= y_offset;
		}
		else if (sides[0] == -1 && sides[1] == -1)
		{
			// Top right of brick.
			if(x_offset < y_offset)
				x += x_offset;
			else if(x_offset > y_offset)
				y -= y_offset;
		}
		else if (sides[0] == -1 && sides[1] == 1)
		{
			// Bottom right of brick.
			if(x_offset < y_offset)
				x += x_offset;
			else if(x_offset > y_offset)
				y += y_offset;
		}
		else if(sides[0] == 0 && sides[1] == 1)
			y += y_offset;
		else if(sides[0] == 0 && sides[1] == -1)
			y -= y_offset;
		else if(sides[0] == 1 && sides[1] == 0)
			x -= x_offset;
		else if(sides[0] == -1 && sides[1] == 0)
			x += x_offset;
		
		
		return sides;
		/*if(x_offset < y_offset && x > prevX)
			x = x - x_offset;
		else if(x_offset < y_offset && x < prevX)
			x = x + x_offset;
		else if(x_offset > y_offset && y > prevY)
		else if (x_offset > y_offset && y < prevY)*/
		
										
	
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
