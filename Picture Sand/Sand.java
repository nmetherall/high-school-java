import java.util.*;

class Sand
{
	int x,y,velocity;
	int color;

	Sand(int a, int b)
	{
		x=a;
		y=b;
	}//end of Sand()

	void update()
	{

	}//end of update

	static ArrayList create(int x1, int y1, int x, int y, ArrayList l)
	{
		for(int i=0; i<=x; i++)
			for(int j=0; j<=y; j++)
				l.add(new Sand(i+x1,j+y1));
		return l;
	}//end of create
}//end dof Sand()