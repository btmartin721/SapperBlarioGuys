package sapper.mario.assignment3;

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
		vert_vel = 0.0;
		x = _x;
		y = _y;
		w = _w;
		h = _h;
		top = 0;
		bottom = 0;
		left = 0;
		right = 0;
		velX = 25.1;
		velY = 30;
		
	}
	
	void update(int groundY, ArrayList<Brick> bricks)
	{
		
			
		vert_vel += 1.2;
		y += vert_vel;
					
		if(y > groundY)
		{
			vert_vel = 0.0;
			y = groundY; // snap back to the ground
		}
		
		if(x < 0)
			x = 0;
		
		
		this.getMarioBounds(x, y, w, h);
	
		
		for(int i = 0; i < bricks.size(); i++)
		{
			Brick b = bricks.get(i);
			//brickPos = b.setBrickMapPos(b.x);
			b.getBrickBounds(b.x, b.y, b.w, b.h);
			
			if(checksCollision(b))
			{
				int[] side = getPreviousPosition(b);
				
			}
		
		}
		
		prevX = x;
		prevY = y;
		
	}
	
	boolean checksCollision(Brick _b)
	{
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
		int x_offset = 0;
		int y_offset = 0;
		int[] sides = new int[2];
		
		
		if(this.right > _b.left && this.left < _b.left && x > prevX)
		{
			x_offset = right - _b.left;
			x = x - x_offset;
			sides[0] = 1;

		}
		else if(this.left < _b.right && this.right > _b.right && x < prevX)
		{
			x_offset = _b.right - left;
			x = x + x_offset;
			sides[0] = -1;

		}
		if(this.top > _b.bottom && this.bottom < _b.bottom && y < prevY)
		{
			y_offset = _b.bottom - top;
			y = y + y_offset;
			sides[1] = 1;

		}
		else if (this.bottom > _b.top && this.top < _b.top && y > prevY)
		{
			y_offset = bottom - _b.top;
			y = y - y_offset;
			sides[1] = -1;
		}
		else
			sides[0] = 0;
			sides[1] = 0;
		
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
		top = _y;
		bottom = _y + _h;
		left = _x;
		right = _x + _w;
	}
	
	
	void breakCollision(int val)
	{			
		x -= val;
	}
	

		
}
