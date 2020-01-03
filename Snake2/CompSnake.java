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
		//System.out.println(fruits.size());
	}

    void update(ArrayList<Snake> snakes)
	{
	    if(fruits.size()!=0)
	    {
			Fruit f = fruits.get(fruitNum);
	        //if(Math.abs(f.get(0).p.x - position.get(0).x) < Math.abs(f.get(0).p.y - position.get(0).y))
			if
			(
				Math.abs(f.p.x - position.get(position.size()-1).x) > Math.abs(f.p.y - position.get(position.size()-1).y)
				||
				Math.abs(position.get(position.size()-1).x-f.p.x) > Math.abs(position.get(position.size()-1).y-f.p.y ))
			{
	            if(f.p.x < position.get(position.size()-1).x)
	            {
					if(!checkCollideAll(Snake.LEFT, snakes) && dir!=RIGHT)
						setDirection(Snake.LEFT);
				    else if(dir==RIGHT&&!checkCollideAll(Snake.DOWN, snakes))
				    {
						boolean isGoingUp=false;
						if(!checkCollideAll(Snake.UP, snakes))
						{
							setDirection(UP);
						}
						else
						{
							isGoingUp=true;
							setDirection(Snake.DOWN);
						}
						move();
						if(!checkCollideAll(Snake.LEFT, snakes))
						{
							setDirection(Snake.LEFT);
						}
						else if(isGoingUp&&!checkCollideAll(Snake.UP, snakes))
						{
							setDirection(Snake.UP);
						}
						else if(!checkCollideAll(Snake.DOWN, snakes))
						{
							setDirection(Snake.DOWN);
						}
						move();
					}

	            }
	            else if(f.p.x > position.get(position.size()-1).x)
	            {

					if(!checkCollideAll(Snake.RIGHT, snakes) && dir != Snake.LEFT)
				    	setDirection(Snake.RIGHT);
				    else if(dir==LEFT)
				    {
						boolean isGoingUp=false;
						if(!checkCollideAll(Snake.UP, snakes))
						{
							setDirection(UP);
						}
						else
						{
							isGoingUp=true;
							setDirection(DOWN);
						}
						move();
						if(!checkCollideAll(Snake.RIGHT, snakes))
						{
							setDirection(Snake.RIGHT);
						}
						else if(isGoingUp&&!checkCollideAll(Snake.UP, snakes))
						{
							setDirection(Snake.UP);
						}
						else if(!checkCollideAll(Snake.DOWN, snakes))
						{
							setDirection(Snake.DOWN);
						}
					}
	            }
			}
			else
			{
	            if(f.p.y < position.get(position.size()-1).y)
	            {
					if(!checkCollideAll(Snake.UP, snakes) && dir!=DOWN)
	               		setDirection(Snake.UP);
					else if(dir==DOWN)
					{
						boolean isGoingLeft=false;
						if(!checkCollideAll(Snake.RIGHT, snakes))
						{
							setDirection(Snake.RIGHT);
						}
						else
						{
							isGoingLeft=true;
							setDirection(Snake.LEFT);
						}
						move();
						if(!checkCollideAll(Snake.UP, snakes))
						{
							setDirection(Snake.UP);
						}
						else if(isGoingLeft&&!checkCollideAll(Snake.RIGHT, snakes))
						{
							setDirection(Snake.RIGHT);
						}
						else if(!checkCollideAll(Snake.LEFT, snakes))
						{
							setDirection(Snake.LEFT);
						}
					}
	            }
	            else if(f.p.y > position.get(position.size()-1).y)
	            {
					if(!checkCollideAll(Snake.DOWN, snakes)&& dir!=UP)
						setDirection(Snake.DOWN);
					else if(dir==UP)
					{
						boolean isGoingLeft=false;
						if(!checkCollideAll(Snake.RIGHT, snakes))
						{
							setDirection(RIGHT);
						}
						else
						{
							isGoingLeft=true;
							setDirection(LEFT);
						}
						move();
						if(!checkCollideAll(Snake.DOWN, snakes))
						{
							setDirection(Snake.DOWN);
						}
						else if(isGoingLeft&&!checkCollideAll(Snake.RIGHT, snakes))
						{
							setDirection(Snake.RIGHT);
						}
						else if(!checkCollideAll(Snake.LEFT, snakes))
						{
							setDirection(Snake.LEFT);
						}
					}

	            }
			}

	    }
	    move();
    }



}