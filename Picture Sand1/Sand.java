import java.awt.Color;
import java.util.Map;
import java.util.ArrayList;

class Sand
{
	static int width, height;
	static ArrayList<Position> position;

	Color color;
	Position p;

	Sand(Position p, int c, Map<Position, Sand> sand, ArrayList<Position> position)
	{
		this.position = position;
		this.p = p;
		color = new Color(c);
		if(color.equals(Color.BLACK))return;

		sand.put(this.p,this);
		position.add(p);
	}

	public void move(Map<Position, Sand> sand, ArrayList<Position>  pos)
	{
		if(checkEmptyDOWN(sand))
			moveDOWN(sand,pos);
		else
		{
			switch((int)(Math.random()*2))
			{
				case 1:
					if(checkEmptyLEFT(sand))
						moveLEFT(sand,pos);
					else if(checkEmptyRIGHT(sand))
						moveRIGHT(sand,pos);
						//System.out.println("left");
					break;
				case 0:
					if(checkEmptyRIGHT(sand))
						moveRIGHT(sand,pos);
					else if(checkEmptyLEFT(sand))
						moveLEFT(sand,pos);
					//System.out.println("right");
					break;
			}
		}
	}

	public void moveUP(Map<Position, Sand> sand, ArrayList<Position> pos)
	{
		sand.remove(p);
		p.moveUP();
		sand.put(p,this);
	}

	public void moveDOWN(Map<Position, Sand> sand, ArrayList<Position> pos)
	{
		sand.remove(p);
		p.moveDOWN();
		sand.put(p,this);
	}

	public void moveLEFT(Map<Position, Sand> sand, ArrayList<Position> pos)
	{
		sand.remove(p);
		p.moveLEFT();
		sand.put(p,this);
	}

	public void moveRIGHT(Map<Position, Sand> sand, ArrayList<Position> pos)
	{
		sand.remove(p);
		p.moveRIGHT();
		sand.put(p,this);
	}


	public boolean checkEmptyUP(Map<Position, Sand> sand)
	{
		return p.y > 0 && sand.get(p.getUP())==null;
	}

	public boolean checkEmptyDOWN(Map<Position, Sand> sand)
	{
		return p.y < height && sand.get(p.getDOWN())==null;
	}

	public boolean checkEmptyLEFT(Map<Position, Sand> sand)
	{
		return p.x > 0 && sand.get(p.getLEFT())==null;
	}

	public boolean checkEmptyRIGHT(Map<Position, Sand> sand)
	{
		return p.x < width && sand.get(p.getRIGHT())==null;
	}

	public static void setWidth(int width1)
	{
		width = width1;
	}

	public static void setHeight(int height1)
	{
		height = height1;
	}

	Position getPosition()
	{
		return p;
	}

}