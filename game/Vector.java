import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.*;

class Vect
{
	double dir,mag;
	int x,y,baseX, baseY;

	Vect()
	{
		dir=mag=x=y=0;
	}

	void makeVect(int x1, int x2, int y1, int y2)
	{
		baseX=x1;
		baseY=y1;
		x=x2;
		y=y2;
		getMagnitude();
		getDirection();
	}

	double getMagnitude()
	{
		mag = Math.sqrt(Math.pow(y,2)+Math.pow(x,2));
		return mag;
	}

	double getDirection()
	{
		dir= Math.atan(y/x);
		if(x<0)
			dir+=180;
		return dir;
	}

	void setX(int a)
	{
		x=a;
	}
	void setY(int a)
	{
		y=a;
	}
	void setXY(int a, int b)
	{
		setX(a);
		setY(b);
	}

	public static Vect average(Thingy a[],int length)
	{
		Vect v = new Vect();
		int xA=0;
		int yA=0;
		for(int i =0;i<length; i++)
		{
			xA+=a[i].v.x-a[i].v.baseX;
			yA+=a[i].v.y-a[i].v.baseY;
		}
		v.setXY(xA,yA);
		return v;
	}
}