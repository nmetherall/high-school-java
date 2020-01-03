import java.awt.event.*;

public class Program implements KeyListener
{
	Display disp;
	static Marble marble1,marble2;
	static Program main;

	public Program()
	{
		main = this;

		marble1 = new Marble();

		disp = new Display(this);


		disp.add(marble1.display);
		marble1.setLocation(3,3,0);

		disp.start();
	}

	public void update()
	{
		if(marble1 == null)return;

		marble1.update();
		disp.update();
	}

	public void keyPressed(KeyEvent e)
	{
		String key = KeyEvent.getKeyText(e.getKeyCode());

		if(key.equals("A"))disp.setAngleXZChange(3);
		if(key.equals("D"))disp.setAngleXZChange(-3);
		if(key.equals("W"))disp.setAngleYZChange(3);
		if(key.equals("S"))disp.setAngleYZChange(-3);

		if(key.equals("Space"))marble1.jump();
		if(key.equals("Left"))marble1.left();
		if(key.equals("Right"))marble1.right();
		if(key.equals("Up"))marble1.forward();
		if(key.equals("Down"))marble1.backward();
		//if(key.equals("T"))marble1.invert(mar);
	}

	public void keyReleased(KeyEvent e)
	{
		String key = KeyEvent.getKeyText(e.getKeyCode());

		if(key.equals("A"))disp.setAngleXZChange(0);
		if(key.equals("D"))disp.setAngleXZChange(0);
		if(key.equals("W"))disp.setAngleYZChange(0);
		if(key.equals("S"))disp.setAngleYZChange(0);


		if(key.equals("Left"))marble1.stop();
		if(key.equals("Right"))marble1.stop();
		if(key.equals("Up"))marble1.stop();
		if(key.equals("Down"))marble1.stop();
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

	public float distance(float x1,float y1,float z1,float x2,float y2,float z2)
	{
		return (float)Math.sqrt((x1-x2)*(x1-x2)+(y1-y2)*(y1-y2)+(z1-z2)*(z1-z2));
	}

	public static void main(String args[])
	{
		new Program();
	}
}