import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.*;

class Angle
{
	double angle;
	double pri;

	Angle()
	{
		angle=pri=0;
	}

	public static int average(Angle a[],int length)
	{
		int an=0;
		int pr=0;
		for(int i =0;i<length; i++)
		{
			an+=a[i].angle*a[i].pri;
			pr+=a[i].pri;
		}
		if(pr==0)return 0;
		return (int)(an/pr);
	}
}