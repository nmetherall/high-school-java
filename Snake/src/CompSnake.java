
import java.awt.Color;
import java.util.ArrayList;

class CompSnake extends Snake
{
    ArrayList<Fruit> fruits;
    int fruitNum=0;
    CompSnake(int length,int startX, int startY, int startDir, Color c, ArrayList<Fruit> fruits)
    {
        super( length, startX, startY, startDir, c);
        this.fruits = fruits;
        updateFruit(fruits);
    }

    void updateFruit(ArrayList<Fruit> f)
    {
		fruits = f;
		fruitNum=(int)(Math.random()*fruits.size()-1);
		System.out.println(fruits.size());
	}

    void update2()
    {
        if(fruits.size()!=0)
        {
			Fruit f = fruits.get(fruitNum);
            //if(Math.abs(f.get(0).p.x - position.get(0).x) < Math.abs(f.get(0).p.y - position.get(0).y))
            {
                if(f.p.x < position.get(position.size()-1).x)
                {
					if(dir==Snake.RIGHT)
                        setDirection(Snake.UP);
					else
	                    setDirection(Snake.LEFT);
                }
                else if(f.p.x > position.get(position.size()-1).x)
                {
					if(dir==Snake.LEFT)
						setDirection(Snake.DOWN);
					else
	                    setDirection(Snake.RIGHT);
                }
            }
            //else if(Math.abs(f.get(0).p.x - position.get(0).x) > Math.abs(f.get(0).p.y - position.get(0).y))
            {
                if(f.p.y < position.get(position.size()-1).y)
                {
					if(dir==Snake.DOWN)
						setDirection(Snake.RIGHT);
					else
                   		setDirection(Snake.UP);
                }
                else if(f.p.y > position.get(position.size()-1).y)
                {
					if(dir==Snake.DOWN)
						setDirection(Snake.LEFT);
					else
                    	setDirection(Snake.DOWN);
                }
            }
        }
        move();
    }


    void update()
	{
	    if(fruits.size()!=0)
	    {
			Fruit f = fruits.get(fruitNum);
	        //if(Math.abs(f.get(0).p.x - position.get(0).x) < Math.abs(f.get(0).p.y - position.get(0).y))
			if
			(
				Math.abs(f.p.x - position.get(position.size()-1).x) > Math.abs(f.p.y - position.get(position.size()-1).y)
				||
				Math.abs(position.get(position.size()-1).x-f.p.x) > Math.abs(position.get(position.size()-1).y-f.p.y )
			)
			{
	            if(f.p.x < position.get(position.size()-1).x)
	            {
					if(!checkCollideSelf(Snake.LEFT) && dir!=RIGHT)
						setDirection(Snake.LEFT);

	            }
	            else if(f.p.x > position.get(position.size()-1).x)
	            {
					if(!checkCollideSelf(Snake.RIGHT) && dir!=LEFT)
				    	setDirection(Snake.RIGHT);
	            }
			}
			else
			{
	            if(f.p.y < position.get(position.size()-1).y)
	            {
					if(!checkCollideSelf(Snake.UP) && dir!=DOWN)
	               		setDirection(Snake.UP);
	            }
	            else if(f.p.y > position.get(position.size()-1).y)
	            {
					if(!checkCollideSelf(Snake.DOWN)&& dir!=UP)
						setDirection(Snake.DOWN);
	            }
			}
	    }
	    move();
    }

    boolean checkCollideSelf(int d)
    {
		for(Position p: position)
		{
			if(d==Snake.UP)
				if(p.y-1==position.get(position.size()-1).y && p.x==position.get(position.size()-1).x)
					return true;
			if(d==Snake.DOWN)
				if(p.y+1==position.get(position.size()-1).y && p.x==position.get(position.size()-1).x)
					return true;
			if(d==Snake.LEFT)
				if(p.y==position.get(position.size()-1).y && p.x-1==position.get(position.size()-1).x)
					return true;
			if(d==Snake.UP)
				if(p.y==position.get(position.size()-1).y && p.x+1==position.get(position.size()-1).x)
					return true;
		}
		return false;
	}

}