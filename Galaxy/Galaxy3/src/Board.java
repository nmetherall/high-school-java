import java.util.ArrayList;
import java.awt.*;
class Board
{
	ArrayList<Star> stars = new ArrayList<Star>();
	int x;
	int y;
	int aveX, aveY;
	Ship you;

	//*Enitialises board with the number of stars
	Board(int numStar)
	{
		x=(int)Toolkit.getDefaultToolkit().getScreenSize().getWidth();//gets the screen whidth and stores it
		y= (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight();//gets the screen height and stores it
		for(int i=0; i<numStar; i++)//enitialises thje stars and adds them to the array
		{
			int startx;
			int starty;
			do//creates the stars in a circle
			{
				startx= (int)(Math.random()*x);
				starty= (int)(Math.random()*y);
			}while(Math.sqrt(((startx-(x/5))*(startx-(x/5)))+((starty-(y/2))*(starty-(y/2))))>(y/6)  &&   Math.sqrt(((startx-(4*x/5))*(startx-(4*x/5)))+((starty-(y/2))*(starty-(y/2))))>(y/6)  &&  Math.sqrt(((startx-(x/2))*(startx-(x/2)))+((starty-(y/5))*(starty-(y/5))))>(y/6)   &&  Math.sqrt(((startx-(x/2))*(startx-(x/2)))+((starty-(4*y/5))*(starty-(4*y/5))))>(y/6));
			//}while(Math.sqrt(((startx-(x/2))*(startx-(x/2)))+((starty-(y/2))*(starty-(y/2))))>(y/2.3));
			new Star(startx,starty, 0, Math.random()*10e4, stars);//x,y,z,v,m,ArrayList
		}//end of for(int i=0; i<numStar; i++)
		you = new Ship(1,1);
	}//end of Board()

	public void update()
	{
		aveX=aveY=0;
		for(int j=0;j<5;j++)
			for(int i=0; i<stars.size();i++)
			{
				stars.get(i).update(stars,you);
				aveX+=stars.get(i).x;
				aveY+=stars.get(i).y;
			}
		aveX=(aveX/(stars.size()*5))-(x/2);
		aveY=(aveY/(stars.size()*5))-(y/2);

		you.update(stars, you);

		for(Star s : stars)
			s.move();
		you.move();
	}//end of update()
}//end of class Board