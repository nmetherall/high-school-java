import java.util.ArrayList;
import java.awt.*;

class Star
{
	double x,y;//position x and y
	double a,v,m;//acceleration, velocity, mass
	double size;//size of the star
	double xa,ya,za, xv,yv,zv;//x acceleration, y acceleration, z acceleration, x velocity, y velocity, z velocity
	double G = 6.67300E-11;//Universal gravitational constant
	Color c;//color of the star

	//* Initialises the Star and adds it to the ArrayList of Stars
	Star(){}

	Star(double x, double y, double v,double m, ArrayList<Star> list)
	{
		this.x = x;
		this.y = y;
		this.v = v;
		this.m = m;
		size = 1;

		switch((int)(Math.random()*4))//sets the color of the star
		{
			case 0: c= Color.CYAN;break;
			case 1: c= Color.RED;break;
			case 2: c= Color.YELLOW;break;
			case 3: c= Color.WHITE;break;
		}

		list.add(this);//adds the current star to boards array list
	}//end of Star()

	Star(double x, double y, double v,double m, int size, Color c,ArrayList<Star> list)
	{
		this.x = x;
		this.y = y;
		this.v = v;
		this.m = m;
		this.size = size;
		this.c = c;

		list.add(this);//adds the current star to boards array list
	}//end of Star()

	//*Updates the acceleration and velocity of the star.
	void update(ArrayList<Star> list, Ship you)
	{
		double f, d, xya, alpha;//force, distance, x and y accleration, angle
		xa = 0;//sets the x accelleration to 0
		ya = 0;//sets the y accelleration to 0
		Star temp=null;//a temporary star used for storing the star involved in a collision
		boolean comb=false;//creates a boolean that tells if a star combines with another or not

		for(Star s : list)//goes through the array list of stars and calculates the gravitational force beetween each one and this star
		{
			if(s == this)//if the current star is this star skip to the next one
				continue;

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
		xv += xa;//updates the x velocity
		yv += ya;//updates the y velocity
		if(comb)//if there was a collision
			combine(list,temp);
	}//end of update(ArrayList<Star> list)

	//* Changes the Star's x, y, and z values
	public void move()
	{
		x += xv;//adds the x velocity to the x position
		y += yv;//adds the y velocity to the y position
	}//end of move(ArrayList<Star> list)

	//*Combines the current star and the selected star
	void combine(ArrayList<Star> list, Star s)
	{
		//This should use m1v1+m2v2 = (m1+m2)vf;
		xv=((m*xv)+(s.m*s.xv))/(m+s.m);//adds both stars x velocity
		yv=((m*yv)+(s.m*s.yv))/(m+s.m);//adds both stars y velocity
		m+=s.m;//sets the mass equal to the two masses added together
		size+=(s.size);//adds the sizes of the two stars
		if(size<=s.size)//sets the color of the star to the color of the larger star
			c=s.c;
		list.remove(s);//removes the added star from the list
	}//end of combine(ArrayList<Star> list, Star s)
}//end of class Star