import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;

class Thingy
{
	double posX, posY, power,endX,endY;
	static int centX,centY;
	BufferedImage pic;
	static BufferedImage back;
	static Graphics2D g2;
	int count = 0;
	int count2 = 0;
	Angle ang, ang2[];
	int length;
	boolean dead=false;
	Vect v;

	Thingy()
	{
		v = new Vect();
	}

	void loadPic(String s)
	{
		try
		{
			pic = ImageIO.read(new File(s));
		}catch(Exception e){}
	}//end of Ball()


	public int getX(){return (int)posX;}
	public int getY(){return (int)posY;}
	public void setEndX(int x){endX = x;}
	public void setEndY(int y){endY = y;}
	public void setX(int x){posX = x;}
	public void setY(int y){posY = y;}
	public static void setCenter(int x,int y){centX=x;centY=y;}
	public void getVector(){}

	void updateAngle(int a, int b)
	{
		ang = new Angle();
		ang.angle= Math.atan((b-posY)/(a-posX));
		if((a-posX)<0){ang.angle+= Math.PI;}
	}

	void update(Graphics2D g)
	{
		g2=g;
		updateAngle(centX,centY);

		if(Math.abs(centX-posX)>4 || Math.abs(centY-posY)>4)
		{
			if(centX-posX!=0)
			{
				posX += Math.cos(ang.angle)*power;
				posY += Math.sin(ang.angle)*power;
				g2.drawImage(pic,(int)posX,(int)posY,null);
			}
			else{posY += (centY-posY)/10d;g2.drawImage(pic,(int)posX,(int)posY,null);}
		}
		else
			end();
	}

	public void checkCollide(It a[], int l,You b)
	{
		length = l;
		ang2=new Angle[l+1];
		for(int i=0; i<length+1; i++)
			ang2[i]=new Angle();
		if(dead!=true)
		{
			for(int i=0; i<length; i++)
			{
				if(Math.sqrt((double)Math.pow((double)a[i].getX()-(double)posX,2)+Math.pow((double)a[i].getY()-(double)posY,2))<=50 && a[i].dead != true)
				{
					ang2[i].pri=Math.sqrt((double)Math.pow((double)a[i].getX()-(double)posX,2)+Math.pow((double)a[i].getY()-(double)posY,2));
					ang2[i].angle= Math.atan((a[i].getY()-posY)/(a[i].getX()-posX));
					if((a[i].getX()-posX)<0){ang2[i].angle+= Math.PI;}
				}
				else
					ang2[i].pri=0;
			}
			if(Math.sqrt((double)Math.pow((double)b.getX()-(double)posX,2)+Math.pow((double)b.getY()-(double)posY,2))<=70 && b.dead != true && dead!=true && b!= null)
			{
				ang2[length].pri=2*(Math.sqrt((double)Math.pow((double)b.getX()-(double)posX,2)+Math.pow((double)b.getY()-(double)posY,2)));
				ang2[length].angle= Math.atan((b.getY()-posY)/(b.getX()-posX));
				if((b.getX()-posX)<0){ang2[length].angle+= Math.PI;}
			}
			else
				ang2[length].pri=0;
			int av = Angle.average(ang2,length+1);
			if(av!=0)
			{
				posX -= Math.cos(av)*5;
				posY -= Math.sin(av)*5;
			}
		}
		else return;
	}

	/*public void checkCollide(It a[], int l,You b)
	{
		for(int i=0; i<length; i++)
		{
			a[i].v.makeVect((int)a[i].posX,(int)posX,(int)a[i].posY,(int)posY);
		}
		Vect.average(a,l);
		posX-=Math.cos(v.dir);//*(1/v.mag);
		posY-=Math.sin(v.dir);//*(1/v.mag);
	}*/

	void end()
	{
		dead=true;
		System.out.println("You Lose");
	}
}