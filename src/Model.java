package sapper.mario.assignment3;
import java.util.ArrayList;


class Model
{
	// Class serving as the model for the game.
	
	// Member variables.
	boolean dragged;
	int cameraPos;
	int width;
	int height;
	int curX;
	int curY;
	Brick drag;
	ArrayList<Brick> bricks;
	Mario mario;
	int marioPosX;
	int marioPosY;
	int marioW;
	int marioH;
	int marioPos;
	int brickPos;
	int rightPanelX;
	int cameraWidth;
	int movePixels;
	int ground_vel;
	int brick_vel;
	int marioMapPos;
	int prevMarioPos;
	
	boolean leftBarrier;
	boolean rightBarrier;
	boolean topBarrier;
	boolean bottomBarrier;
	boolean collisionDetected;
	boolean moveOK;
	boolean inBrick;
	int[] collision;
	int groundY;
	
	View view;
	



	Model()
	{
		// Class constructor.
		
		mario = new Mario(0, 0, 60, 95);

		//this.updateMario(mario.x, mario.y, mario.w, mario.h); // Setter for Mario Coordinates in Model.java
		
		bricks = new ArrayList<Brick>();
        cameraPos = 0;
        dragged = false;
        marioPos = 0;
        rightPanelX = 0;
        cameraWidth = 0;
        ground_vel = movePixels;
        brick_vel = movePixels;

        collisionDetected = false;
        leftBarrier = false;
        rightBarrier = false;
        topBarrier = false;
        bottomBarrier = false;
        moveOK = true;
        marioMapPos = mario.x + marioPos;
        inBrick = false;
        prevMarioPos = 0;
        collision = new int[8];
        
        
        
	}

	public void update()
	{
		
		groundY = view.groundCoordY;
		mario.update(groundY, bricks);
		
		
		/*this.updateMario(mario.x, mario.y, mario.w, mario.h);
		
		this.marioMapPos = mario.x + marioPos;

		mario.getMarioBounds(this.marioMapPos, mario.y, mario.w, mario.h);
		
		int nextX = this.marioMapPos + (int)mario.ground_vel;
		int nextY = mario.y + (int)mario.vert_vel;

					
		for(int i = 0; i < bricks.size(); i++)
		{
			Brick b = bricks.get(i);
			//brickPos = b.setBrickMapPos(b.x);
			b.getBrickBounds(b.x, b.y, b.w, b.h);
			
			collision = checkCollisions(b, nextX, nextY);
			
			if(collision[0] != 0 || collision[1] != 0 || collision[2] != 0 || collision[3] != 0)
			{
				int offset = marioMapPos + nextX;
				mario.marioMapPos += offset;
				mario.moveFlag = false;
				moveOK = false;
				collisionDetected = true;
				
				int marioCenterX = (mario.x + mario.w) / 2;
				int brickCenterX = (b.x + b.w) / 2;
				int marioCenterY = (mario.y + mario.h) / 2;
				int brickCenterY = (b.y + b.h) / 2; 
												
				boolean marioLeft = marioCenterX - brickCenterX < 0;
				boolean marioRight = marioCenterX - brickCenterX > 0;
				boolean marioUp = marioCenterY - brickCenterY < 0;
				boolean marioDown = marioCenterY - brickCenterY > 0;
				
				if(marioLeft)
				{
					mario.breakCollision(3);
				}
				if(marioRight)
				{
					mario.breakCollision(-3);
				}*/
					
								
			//}
			//else
			//{
				
				//collisionDetected = false;
				//moveOK = true;
				//System.out.println("No collision");
			//}
		//}
		

	}
	
	public void makeBricks(int x, int y, int w, int h)
	{
		// Adds Brick to ArrayList<Brick>.
		Brick b = new Brick(x, y, w, h);
		bricks.add(b);
	}
	
	Json marshall()
	{
		// Stores class objects as Json object
		Json ob = Json.newObject(); // Create new Json object.
        ob.add("cameraPos", cameraPos);
        Json tmpList = Json.newList(); // For storing ArrayList.
        ob.add("bricks", tmpList);
        
        for(int i = 0; i < bricks.size(); i++)
            tmpList.add(bricks.get(i).marshall());
        
        return ob;
	}
	
	public void clearBricks()
	{
		// Clears ArrayList of bricks.
		bricks.clear();
	}
	
	public void unmarshall(Json ob)
	{
		// Extract Java objects from Json object.
		cameraPos = (int)ob.getLong("cameraPos"); // Casted as int.
		bricks = new ArrayList<Brick>();
		Json tmpList = ob.get("bricks");
		
		for(int i = 0; i < tmpList.size(); i++)
		{
			bricks.add(new Brick(tmpList.get(i)));
		}
		 
	}
	
	public void initializeBrick(int sX, int sY, int init1, int init2)
	{
		// Initialize Brick of 0,0 width,height
		curX = sX;
		curY = sY;
		width = init1;
		height = init2;
		drag = new Brick(curX, curY, width, height);
	}
	
	public void getDrag(int _x, int _y, int _w, int _h)
	{
		// Get X,Y,width,height of current brick size while dragging.
		curX = _x;
		curY = _y;
		width = _w;
		height = _h;
		drag = new Brick(curX, curY, width, height); // Make new Brick while dragging.
	}
	
	public boolean dragOn()
	{
		// Sets dragged to true if mouseDragged is triggered.
		dragged = true;
		return dragged;
		
	}
	
	public boolean dragOff()
	{
		// Sets dragged to false if mouseDragged is triggered.
		dragged = false;
		return dragged;
	}
	
	
	/*void updateMario(int x, int y, int w, int h)
	{
		marioPosX = x;
		marioPosY = y;
		marioW = w;
		marioH = h;
		
	}*/
	
	void checkCollisions(Brick brick)
	{
	/*	int[] collisionsArr = new int[8];
		if(mario.right + nextX < brick.left || mario.left + nextX > brick.right)
		{
			collisionsArr[0] = 0;
			collisionsArr[1] = 0;
			collisionsArr[4] = brick.left;
			collisionsArr[5] = brick.right;
			collisionsArr[6] = brick.top;
			collisionsArr[7] = brick.bottom;
		}
	
		if(mario.bottom + nextY < brick.top || mario.top + nextY > brick.bottom ) // assumes bigger is downward
		{
			collisionsArr[2] = 0;
			collisionsArr[3] = 0;
			collisionsArr[4] = brick.left;
			collisionsArr[5] = brick.right;
			collisionsArr[6] = brick.top;
			collisionsArr[7] = brick.bottom;
			
		}
	
		if(mario.right + nextX > brick.left && mario.right + nextX < brick.right)
		{
			collisionsArr[0] = mario.right + nextX;
			collisionsArr[1] = mario.left + nextX;
			collisionsArr[2] = mario.bottom + nextY;
			collisionsArr[3] = mario.top + nextY;
			collisionsArr[4] = brick.left;
			collisionsArr[5] = brick.right;
			collisionsArr[6] = brick.top;
			collisionsArr[7] = brick.bottom;
		}
		else if(mario.left + nextX < brick.right && mario.left + nextX > brick.right)
		{
			collisionsArr[0] = mario.right + nextX;
			collisionsArr[1] = mario.left + nextX;
			collisionsArr[2] = mario.bottom + nextY;
			collisionsArr[3] = mario.top + nextY;
			collisionsArr[4] = brick.left;
			collisionsArr[5] = brick.right;
			collisionsArr[6] = brick.top;
			collisionsArr[7] = brick.bottom;
		}
		
		return collisionsArr;*/
	}
	
	
	/*int marioPosition()
	{
		this.marioPos = mario.marioMapPos;
		
		if(this.marioPos < 0)
			this.marioPos = 0;
		
		return marioPos;
	}*/
	
	//void setMove(boolean value)
	//{
	//	mario.moveFlag = value;
	//}
	

	
	//void moveMario(int pixels)
	//{
	//	mario.ground_vel += pixels;
	//	this.ground_vel = mario.ground_vel;
	//}
	
	//void setVelX(double velX)
	//{
		//mario.velocityX = velX;
	//}
	
	void setView(View v)
	{
		view = v;
	}
	
	
	
}

