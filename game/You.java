import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
public class You extends Thingy
{
	You()
	{
		endX=endY=posX=posY=0;
		loadPic("you.jpg");
		power = 1;
	}

	void powerUp(PowerUp p)
	{
	}

	void update(Graphics2D g)
	{
		if(Math.abs(centX-posX)>4 || Math.abs(centY-posY)>4)
		{
			g2=g;
			posX -= (posX-endX)/10d;
			posY -= (posY-endY)/10d;
			g2.drawImage(pic,(int)posX,(int)posY,null);
		}
		else
			end();
	}
}