
import java.util.ArrayList;
import java.awt.*;

class Ship extends Star
{
	int targetX, targetY;
        boolean mousePressed=false;

	Ship(int x, int y)
	{
		this.x=x;
		this.y=y;
		targetX = x;
		targetY = y;
		c = Color.GREEN;
		size=200;
		m=10e9;
		System.out.println(x+" "+y);
	}

	void update(ArrayList<Star> list,Ship q)
	{
		double f, d, xya, alpha;//force, distance, x and y accleration, angle
		xa = 0;//sets the x accelleration to 0
		ya = 0;//sets the y accelleration to 0
		Star temp=null;//a temporary star used for storing the star involved in a collision
		boolean comb=false;//creates a boolean that tells if a star combines with another or not

		for(Star s : list)//goes through the array list of stars and calculates the gravitational force beetween each one and this star
		{
			alpha = Math.atan((y - s.y)/(x - s.x));//finds the angle
			if(x-s.x<0)//if neccesary changes the angle
				alpha+=Math.PI;

			d = Math.sqrt((((x+(size/2)) - (s.x+(s.size/2))) * ((x+(size/2)) - (s.x+(s.size/2)))) + (((y+(size/2)) - (s.y+(s.size/2))) * ((y+(size/2)) - (s.y+(s.size/2)))));//distance forula
			a = ((G * s.m) / d);//total acceleration
			xa -= (a * Math.cos(alpha))*3;//updates the acceleration in the x direction
			ya -= (a * Math.sin(alpha))*3;//updates the acceleration in the y direction

			if(d<1)//tests for a collision
			{
				comb=true;
				temp = s;
			}
		}//end of for(Star s : list)
                if(x-targetX!=0 && y-targetY!=0 && mousePressed)
                {
                    alpha = Math.atan((y - targetY)/(x - targetX));//finds the angle
                    if(x-targetX<0)//if neccesary changes the angle
			alpha+=Math.PI;
       
                    xa-=4*Math.cos(alpha);
                    ya-=4*Math.sin(alpha);
                }

		xv += xa;//updates the x velocity
		yv += ya;//updates the y velocity
		if(comb)//if there was a collision
			combine(list,temp);
	}

	public void setTarget(int x , int y)
	{
            mousePressed=true;
            targetX=x;
            targetY=y;
	}

	void combine(ArrayList<Star> l, Star s)
	{
	}
}