import java.awt.*;
import java.util.ArrayList;
class Snake
{
	int length, points, dir, startX, startY, startLength, startDir, score;
	ArrayList<Position> position;
	Color c;

	static final int UP = 545423;
	static final int DOWN = 875889;
	static final int LEFT = 788970;
	static final int RIGHT = 385689;

	Snake(int length,int startX, int startY, int startDir, Color c)
	{
		this.length = length;
        startLength = length;
		this.c = c;
        this.startX = startX;
        this.startY = startY;
		dir = startDir;
        this.startDir = startDir;

        score = 0;

		position = new ArrayList<Position>();

        position.add(new Position(startX, startY));

	}//end of Snake(int length,int startX, int startY, int startDir, Color c)

	void update(ArrayList<Snake> snakes)
	{
            move();
	}//end of void move()
    void move()
    {
        if(length == position.size())
            position.remove(position.get(0));

        switch(dir)
        {
            case Snake.UP:
                position.add(new Position(position.get(position.size()-1).x, position.get(position.size()-1).y-1));
                break;
            case Snake.DOWN:
                position.add(new Position(position.get(position.size()-1).x, position.get(position.size()-1).y+1));
                break;
            case Snake.LEFT:
                position.add(new Position(position.get(position.size()-1).x-1, position.get(position.size()-1).y));
                break;
            case Snake.RIGHT:
                position.add(new Position(position.get(position.size()-1).x+1, position.get(position.size()-1).y));
    			break;
        }//end of switch(startDir)
    }

    void collide(ArrayList<Snake> snakes, ArrayList<Fruit> fruits)
    {
        boolean col=false;
        boolean fcol=false;
        Fruit fruit = new Fruit(1,1);
        for(Snake s : snakes)
        {
            if(position.get(position.size()-1).x>(496/4) || position.get(position.size()-1).x<0 || position.get(position.size()-1).y>(496/4) || position.get(position.size()-1).y<0)
                col=true;

            else
                for(Position p: s.position)
                {
                    if(position.get(position.size()-1).equals(p) && p!=position.get(position.size()-1))
                    {
                        //System.out.println("a");
                        col=true;
                    }
                }
        }
        for(Fruit f: fruits)
        {
            if(position.get(position.size()-1).equals(f.p) && f.p!=position.get(position.size()-1))
            {
                fcol=true;
                fruit = f;
            }
        }
        if(col)
            reset();
        if(fcol)
            fruitCollide(fruit, fruits);
    }

    void reset()
    {
        position.clear();
        score-=10;
        position.add(new Position(startX,startY));
        length= startLength;
        dir = startDir;
    }

    void fruitCollide(Fruit f, ArrayList<Fruit> fruits)
    {
        length+=5;
        score+=100;
        //System.out.println(score);
        //if(score>=5000)
        fruits.remove(f);
    }

	void setDirection(int dir)
	{
		this.dir=dir;
	}//end of setDirection

	void updateFruit(ArrayList<Fruit> fruit)
	{
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

	boolean checkCollideAll(int d, ArrayList<Snake> snakes)
	{
		if(d==Snake.UP)
			for(Snake s : snakes)
				for(Position p : s.position)
					if(p.y-1==position.get(position.size()-1).y && p.x==position.get(position.size()-1).x && p != position.get(position.size()-1))
						return true;
		if(d==Snake.DOWN)
			for(Snake s : snakes)
				for(Position p : s.position)
					if(p.y+1==position.get(position.size()-1).y && p.x==position.get(position.size()-1).x && p != position.get(position.size()-1))
						return true;
		if(d==Snake.LEFT)
			for(Snake s : snakes)
				for(Position p : s.position)
					if(p.y==position.get(position.size()-1).y && p.x-1==position.get(position.size()-1).x && p != position.get(position.size()-1))
						return true;
		if(d==Snake.UP)
			for(Snake s : snakes)
				for(Position p : s.position)
					if(p.y==position.get(position.size()-1).y && p.x+1==position.get(position.size()-1).x && p != position.get(position.size()-1))
						return true;

		return false;
	}

}//end of class Snake