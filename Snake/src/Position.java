class Position
{
	final int x, y;
	Position(int x, int y)
	{
		this.x=x;
		this.y=y;
	}

    boolean equals(Position p)
    {
        if(x==p.x && y==p.y)
            return true;
        else
            return false;
    }
}