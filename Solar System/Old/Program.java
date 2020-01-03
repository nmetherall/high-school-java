import java.awt.event.*;

public class Program implements KeyListener
{
	Display disp = new Display(this);
	Marble marble = new Marble();

	public Program()
	{
		marble.setLocation(0,0,-3);
		disp.start();

		while(true)
		{
			marble.update();
			disp.update();

			pause(30);
		}
	}

	public void keyPressed(KeyEvent e)
	{
		String key = KeyEvent.getKeyText(e.getKeyCode());

		if(key.equals("A"))disp.setAngleXZChange(3);
		if(key.equals("D"))disp.setAngleXZChange(-3);
		if(key.equals("W"))disp.setAngleYZChange(3);
		if(key.equals("S"))disp.setAngleYZChange(-3);

		if(e.isShiftDown())
		{

		}
		else
		{
			if(key.equals("Left"))marble.vx = -.03f;
			if(key.equals("Right"))marble.vx = .03f;
			if(key.equals("Up"))marble.vz = -.03f;
			if(key.equals("Down"))marble.vz = .03f;
		}
	}

	public void keyReleased(KeyEvent e)
	{
		String key = KeyEvent.getKeyText(e.getKeyCode());

		if(key.equals("A"))disp.setAngleXZChange(0);
		if(key.equals("D"))disp.setAngleXZChange(0);
		if(key.equals("W"))disp.setAngleYZChange(0);
		if(key.equals("S"))disp.setAngleYZChange(0);


		if(key.equals("Left"))marble.vx = 0;
		if(key.equals("Right"))marble.vx = 0;
		if(key.equals("Up"))marble.vz = 0;
		if(key.equals("Down"))marble.vz = 0;
	}

	public void keyTyped(KeyEvent e){}

	public static void pause(int t)
	{
		try
		{
			Thread.sleep(t);
		}
		catch(Exception e){}
	}

	public static void main(String args[])
	{
		new Program();
	}
}