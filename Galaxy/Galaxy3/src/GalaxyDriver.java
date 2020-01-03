import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

class GalaxyDriver extends JFrame implements MouseListener
{
	static boolean again = true;
	static Board b;
	boolean clearScreen = false;
	Graphics2D g;
	boolean pause=false;
	int xChange, yChange, xChangeV=0, yChangeV=0;
	double scaler = 1,scalerV =1;

	boolean newStar= false;
	int addX,addY;

	//*Initialises the Driver and creates the window.
	GalaxyDriver()
	{
		setSize((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(),(int)Toolkit.getDefaultToolkit().getScreenSize().getHeight());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		b = new Board(inputInt("How many stars would you like"));

		xChange=yChange=0;

		if(JOptionPane.showConfirmDialog(null,"Do you want to draw trails?","Trails",JOptionPane.YES_NO_OPTION)==1)
		{
			clearScreen = true;
		}

		addMouseListener(this);
		setVisible(true);
		createBufferStrategy(2);

		update();
	}//end of GalaxyDriver

	void update()
	{
		for(int iteration = 0;true;iteration++)
		{
			scaler *= scalerV;
			xChange+=((xChangeV)/scaler);
			yChange+=((yChangeV)/scaler);

			if(pause==false)
				b.update();
			g = (Graphics2D)this.getBufferStrategy().getDrawGraphics();

			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);

			if(clearScreen || iteration == 0)
			{
				g.setColor(Color.BLACK);
				g.fillRect(0,0,b.x,b.y);
			}

			for(Star s : b.stars)
			{
				int virtualX = (int)(((s.x-(Math.sqrt((Math.PI)*(s.size)))-b.aveX)*scaler)+(xChange*scaler)-((scaler-1)*(b.x/2)));
				int virtualY = (int)(((s.y-(Math.sqrt((Math.PI)*(s.size)))-b.aveY)*scaler)+(yChange*scaler)-((scaler-1)*(b.y/2)));
				int scaledSize = (int)((Math.sqrt((Math.PI)*(s.size))*scaler));
				g.setColor(s.c);
				g.fillOval(virtualX, virtualY, scaledSize, scaledSize);
				//g.fillOval((int)(((s.x-(Math.sqrt((Math.PI)*(s.size)))-b.aveX)*scaler)+(xChange*scaler)-((scaler-1)*(b.x/2))), (int)(((s.y-(Math.sqrt((Math.PI)*(s.size)))-b.aveY)*scaler)+(yChange*scaler)-((scaler-1)*(b.y/2))), (int)((Math.sqrt((Math.PI)*(s.size))*scaler)), (int)((Math.sqrt((Math.PI)*(s.size))*scaler)));
			}

			{
				int virtualX = (int)(((b.you.x-(Math.sqrt((Math.PI)*(b.you.size)))-b.aveX)*scaler)+(xChange*scaler)-((scaler-1)*(b.x/2)));
				int virtualY = (int)(((b.you.y-(Math.sqrt((Math.PI)*(b.you.size)))-b.aveY)*scaler)+(yChange*scaler)-((scaler-1)*(b.y/2)));
				int scaledSize = (int)((Math.sqrt((Math.PI)*(b.you.size))*scaler));
				g.setColor(b.you.c);
				g.fillOval(virtualX, virtualY, scaledSize, scaledSize);
			}

			getBufferStrategy().show();

			try
			{
				Thread.sleep(300/b.stars.size());
			}catch(Exception e){}
		}
	}

	public static int inputInt(String message)
	{
		String numStr;
		int num = 0;
		boolean good;
		do
		{
			good = true;
			numStr = JOptionPane.showInputDialog(message);
			if(numStr == null)
			{
				quit();
				good = false;
				continue;
			}
			try
			{
				num = Integer.parseInt(numStr);
			}catch(NumberFormatException e)
			{
				JOptionPane.showMessageDialog(null,"Please enter a number.","Pleasde enter a number",JOptionPane.ERROR_MESSAGE);
				good = false;
			}
		}while(!good);
		return num;
	}//end of inputInt()

	public static void quit()
	{
		if(JOptionPane.showConfirmDialog(null,"Are you sure you want to quit?","Quit",JOptionPane.ERROR_MESSAGE) == 0)
			System.exit(0);
	}//end of quit()

	public void keyPressed(KeyEvent e)
	{
		/*
		switch(e.getKeyCode())
		{
		}
		*/
	}

	public void keyReleased(KeyEvent e)
	{
		/*
		switch(e.getKeyCode())
		{
		}
		*/
	}

	public void keyTyped(KeyEvent e){}

	public void mouseReleased(MouseEvent evt)
	{
		b.you.mousePressed=false;
	}
	public void mouseExited( MouseEvent evt2 ){}
	public void mouseEntered( MouseEvent evt2 ){}
	public void mousePressed( MouseEvent evt2 )
	{
		b.you.setTarget(evt2.getX(), evt2.getY());
		System.out.println(evt2.getX()+" "+evt2.getY());
	}
	public void mouseClicked( MouseEvent evt2 ){}

	public static void main(String args[])
	{
		GalaxyDriver g = new GalaxyDriver();
	}//end of main(String args[])
}//end of class GalaxyDriver