

public class Brick
{
	// Class to make Bricks at X,Y coordinates of width w, height h.
	
	// Initialize Member variables.
    int x;
    int y;
    int w;
    int h;
    int top;
    int bottom;
    int left;
    int right;
    int mapPos;
    
    Brick(int d1, int d2, int d3, int d4)
    {
    	// Class constructor.
        x = d1;
        y = d2;
        w = d3;
        h = d4;
        top = 0;
        bottom = 0;
        left = 0;
        right = 0;
        mapPos = 0;
        
    }
    
    Json marshall() 
    {
    	// Store Brick objects as Json object.
    	Json ob = Json.newObject();
    	ob.add("x", x);
    	ob.add("y", y);
    	ob.add("w", w);;
    	ob.add("h", h);
    	return ob;
    }
    
    Brick(Json ob)
    {
    	// Unmarshall Json object to Brick object.
    	x = (int)ob.getLong("x");
    	y = (int)ob.getLong("y");
    	w = (int)ob.getLong("w");
    	h = (int)ob.getLong("h");
    }
    
    
    public void getBrickBounds(int _x, int _y, int _w, int _h)
    {
    	
    	top = _y;
    	bottom = _y + _h;
    	left = _x;
    	right = _x + _w;
    }
    
    public int setBrickMapPos(int pos)
    {
    	mapPos = x + pos;
    	return mapPos;
    }
    

}
