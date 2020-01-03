import java.awt.*;
import java.awt.image.*;
import javax.imageio.*;
import java.io.*;
import javax.swing.*;
import java.awt.event.*;
import java.awt.image.*;

class RunBlackHole extends JFrame implements MouseListener,MouseMotionListener
{
	static BufferedImage back;
	You y;
	It i[];

	RunBlackHole()
	{
		setBounds(0,0,550,550);
		Thingy.setCenter(275,275);

		y = new You();
		i = new It[5];
		for(int t=0; t<5;t++)
		{
			i[t]=new It(1);
			i[t].setX(100*t);
			i[t].setY(10*t);
		}

		back = new BufferedImage(500,500,BufferedImage.TYPE_INT_ARGB );

		addMouseListener(this);
		addMouseMotionListener(this);
		setVisible(true);

		createBufferStrategy(2);


		while(true)
		{

			paint();

			try
			{
				Thread.sleep(30);
			}catch(Exception e){}
		}
	}

	public static void main(String args[])
	{
		RunBlackHole r = new RunBlackHole();
	}

	public void paint()
	{
		Graphics2D g = null;

		if(getBufferStrategy() != null)
			g = (Graphics2D)getBufferStrategy().getDrawGraphics();

		super.paint(g);
		y.update(g);
		for(int t=0;t<5;t++)
		{
			i[t].checkCollide(i,5,y);
			i[t].update(g);
		}
		g.drawImage(back,0,0,this);
		getBufferStrategy().show();
	}
	public void mouseMoved(MouseEvent evt) {}
	public void mouseDragged(MouseEvent evt)
	{
		y.setEndX(evt.getX());
		y.setEndY(evt.getY());
	}
	public void mouseReleased(MouseEvent evt) {}
	public void mouseExited( MouseEvent evt ) {}
	public void mouseEntered( MouseEvent evt ) {}
	public void mousePressed( MouseEvent evt ) {}
	public void mouseClicked( MouseEvent evt ) {}
}