import java.awt.*;
import java.util.*;

class Board
{
	int width, height, numFruits;
	ArrayList<Snake> snakes;
	ArrayList<Fruit> fruits;
    //int pos[][];

	Board(int width, int height, int numFruits)
	{
		this.width = width;
		this.height = height;
		this.numFruits = numFruits;

		snakes = new ArrayList<Snake>();
        fruits = new ArrayList<Fruit>();

        for(int i = 0; i<numFruits; i++)
        {
			fruits.add(new Fruit((int)(Math.random()*125), (int)(Math.random()*125)));
		}

		snakes.add(new Snake(5,10,10,Snake.RIGHT,Color.BLUE));
        //snakes.add(new Snake(5,10,50,Snake.RIGHT, Color.GREEN));
        snakes.add(new CompSnake(5,10,100,Snake.RIGHT, Color.YELLOW, fruits));
        snakes.add(new CompSnake(5,100,10,Snake.LEFT, Color.PINK, fruits));
        snakes.add(new CompSnake(5,100,100,Snake.LEFT, Color.CYAN, fruits));
        snakes.add(new CompSnake(5,100,50,Snake.LEFT, Color.RED, fruits));
        //snakes.add(new CompSnake(5,50,100,Snake.LEFT, Color.WHITE, fruits));
	}

	void update()
	{
		for(Snake s: snakes)
		{
        	s.update();
        	s.collide(snakes,fruits);
		}
        if(fruits.size()<=numFruits)
        {
            fruits.add(new Fruit((int)(Math.random()*125), (int)(Math.random()*125)));
            for(Snake s: snakes)
            	s.updateFruit(fruits);
		}
	}

	void setNumFruits(int i)
	{
		numFruits = i;
	}


	void addSnake()
	{
	}

	void addCompSnake()
	{
	}
}