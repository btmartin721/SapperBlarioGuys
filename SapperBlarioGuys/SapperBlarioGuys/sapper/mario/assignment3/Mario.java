package sapper.mario.assignment3;




public class Mario 
{

	int x;
	int y;
	int w;
	int h;
	double vert_vel;
	Model model;
	View view;
	int marioMapPos;
	int top;
	int bottom;
	int left;
	int right;
	double ground_vel;
	int movePixels;
	boolean moveFlag;
	double velocityX;
	
	


	Mario(int _x, int _y, int _w, int _h)
	{
		vert_vel = 0.0;
		x = _x;
		y = _y;
		w = _w;
		h = _h;
		marioMapPos = 0;
		top = 0;
		bottom = 0;
		left = 0;
		right = 0;
		movePixels = 10;
		ground_vel = 0.0;
		moveFlag = false;
		velocityX = 0.0;
	}
	
	void update()
	{
		
			
		//vert_vel += 1.2;
		vert_vel += 1.2;
		y += vert_vel;
					
		if(y > view.groundCoordY)
		{
			vert_vel = 0.0;
			y = view.groundCoordY; // snap back to the ground
		}
		
		if(x < 0)
			x = 0;
		
		if(moveFlag)
		{
			ground_vel += velocityX;
			x += ground_vel;
		}
		else
			ground_vel = velocityX;
		
	}
	
	void setView(View v)
	{
		view = v;
	}
	
	
	public void getMarioBounds(int _x, int _y, int _w, int _h)
	{
		top = _y;
		bottom = _y + _h;
		left = _x;
		right = _x + _w;
	}
	

	int getMarioPosition()
	{
		return marioMapPos;
	}
	
	void breakCollision(int val)
	{			
		x -= val;
	}
	

		
}
