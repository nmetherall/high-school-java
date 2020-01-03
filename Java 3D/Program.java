import java.awt.event.*;

public class Program implements KeyListener
{
	Display disp;
	Marble marble;
	static Program main;

	public Program()
	{
		main = this;

		marble = new Marble();
		disp = new Display(this);
		disp.add(marble.display);

		marble.setLocation(0,0,0);
		disp.start();
	}

	public void update()
	{
		marble.update();
		disp.update();
	}

	public void keyPressed(KeyEvent e)
	{
		String key = KeyEvent.getKeyText(e.getKeyCode());

		if(key.equals("A"))disp.setAngleXZChange(3);
		if(key.equals("D"))disp.setAngleXZChange(-3);
		if(key.equals("W"))disp.setAngleYZChange(3);
		if(key.equals("S"))disp.setAngleYZChange(-3);

		if(key.equals("Space"))marble.jump();
		if(key.equals("Left"))marble.left();
		if(key.equals("Right"))marble.right();
		if(key.equals("Up"))marble.forward();
		if(key.equals("Down"))marble.backward();
	}

	public void keyReleased(KeyEvent e)
	{
		String key = KeyEvent.getKeyText(e.getKeyCode());

		if(key.equals("A"))disp.setAngleXZChange(0);
		if(key.equals("D"))disp.setAngleXZChange(0);
		if(key.equals("W"))disp.setAngleYZChange(0);
		if(key.equals("S"))disp.setAngleYZChange(0);


		if(key.equals("Left"))marble.stop();
		if(key.equals("Right"))marble.stop();
		if(key.equals("Up"))marble.stop();
		if(key.equals("Down"))marble.stop();
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