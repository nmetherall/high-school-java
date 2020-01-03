class Position
{
	int xChange = 3;
	int yChange = 1;
	int x, y;
	Position(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	public void moveUP()
	{
		y-=yChange;
	}

	public void moveDOWN()
	{
		y+=yChange;
	}

	public void moveLEFT()
	{
		x-=xChange;
	}

	public void moveRIGHT()
	{
		x+=xChange;
	}

	public Position getUP()
	{
		return new Position(x, y-yChange);
	}

	public Position getDOWN()
	{
		return new Position(x, y+yChange);
	}

	public Position getLEFT()
	{
		return new Position(x-xChange, y);
	}

	public Position getRIGHT()
	{
		return new Position(x+xChange, y);
	}

	public boolean equals(Object o)
	{
		return o instanceof Position && ((Position)o).x == x && ((Position)o).y == y;
	}

	public int hashCode()
	{
		return x + (y * 500);
	}
}