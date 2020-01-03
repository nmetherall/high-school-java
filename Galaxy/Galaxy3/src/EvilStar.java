import java.util.ArrayList;

class EvilStar extends Star
{
	EvilStar(int x, int y,ArrayList<Star> list)
	{
		this.x=x;
		this.y=y;
		list.add(this);
	}

	public void update(ArrayList<Star> s, Ship you)
	{
		update(you);
	}

	public void update(Ship you)
	{
		int d = (int)Math.sqrt((((x+(size/2)) - (you.x+(you.size/2))) * ((x+(size/2)) - (you.x+(you.size/2)))) + (((y+(size/2)) - (you.y+(you.size/2))) * ((y+(size/2)) - (you.y+(you.size/2)))));//distance formula
		if(d<20)
		{
			double alpha = Math.atan((y - you.y)/(x - you.x));
			if(x-you.x<0)//if neccesary changes the angle
				alpha+=Math.PI;
			xa=2*Math.cos(alpha);
			ya=2*Math.sin(alpha);
		}
		else
		{
			xa/=2;
			ya/=2;
		}
		xv+=xa;
		yv+=ya;
	}

}