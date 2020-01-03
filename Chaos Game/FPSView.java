import java.awt.*;
import java.awt.event.*;
import com.sun.j3d.utils.universe.*;
import javax.media.j3d.*;
import javax.vecmath.*;

class FPSView extends Transform3D implements KeyListener
{
	static float angleXZ = 0;
	float angleXZC = 0;

	float angleYZ = 0;
	float angleYZC = 0;

	static float x,y,z;//camera target
	static float cx,cy,cz;//camera location

	SimpleUniverse simpleU;

	boolean moving;
	int dir;

	final int F = 1;
	final int R = 2;
	final int B = 4;
	final int L = 8;

	float vx,vy,vz;
	float iv,ih = 10;
	float frame = 0;

	FPSView(SimpleUniverse simpleU,Component c)
	{
		c.addKeyListener(this);
		this.simpleU = simpleU;

		setLocation(10,10,15);
	}

	public void updateView()
	{
		float phi = (float)Math.toRadians(angleYZ);
		float theta = (float)Math.toRadians(angleXZ);
		float r = 10;

		cz = r * (float)Math.cos(phi) * (float)Math.cos(theta) + z;
		cx = r * (float)Math.cos(phi) * (float)Math.sin(theta) + x;
		cy = r * (float)Math.sin(phi) + y;

		lookAt(new Point3d(x,y,z),new Point3d(cx,cy,cz),new Vector3d(0,1,0));
		invert();

		simpleU.getViewingPlatform().getViewPlatformTransform().setTransform(this);
	}

	public static void setViewCenter(float a,float b,float c)
	{
		x = a;
		y = b;
		z = c;
	}

	public void setAngleXZChange(float c)
	{
		angleXZC = c/3;
	}

	public void setAngleYZChange(float c)
	{
		angleYZC = c/3;
	}

	public void update()
	{
		frame += .3;

		float h = iv==0?0:(-.05f)*(frame*frame)+(iv*frame)+ih;

		if(h < 0)
		{
			frame = 0;
			ih = 0;
			/*iv = (.05f*frame)+iv;//velocity of the object inverted
			iv *= .85;//causes it to slow down
			iv -= .075;//prevents it from bouncing only slightly
			if(iv < 0) iv = 0;//stops vertical velocity when too small*/
			iv = 0;
			y = 0;
		}

		float an = (float)Math.toRadians(angleXZ);

		if(moving)
			switch(dir)
			{
				case 1:	vx = 25*(float)(Math.sin(an)/15);
						vz = 25*(float)(Math.cos(an)/15);break;
				case 2:	vx = 25*(float)(Math.sin(an-Math.PI/2)/15);
						vz = 25*(float)(Math.cos(an-Math.PI/2)/15);break;
				case 4:	vx = 25*(float)(Math.sin(an+Math.PI)/15);
						vz = 25*(float)(Math.cos(an+Math.PI)/15);break;
				case 8:	vx = 25*(float)(Math.sin(an+Math.PI/2)/15);
						vz = 25*(float)(Math.cos(an+Math.PI/2)/15);break;
				case 3:	vx = 25*(float)(Math.sin(an-Math.PI/3)/15);
						vz = 25*(float)(Math.cos(an-Math.PI/3)/15);break;
				case 6:	vx = 25*(float)(Math.sin(an-Math.PI*3/4)/15);
						vz = 25*(float)(Math.cos(an-Math.PI*3/4)/15);break;
				case 12:vx = 25*(float)(Math.sin(an-Math.PI*4/3)/15);
						vz = 25*(float)(Math.cos(an-Math.PI*4/3)/15);break;
				case 9:	vx = 25*(float)(Math.sin(an+Math.PI/3)/15);
						vz = 25*(float)(Math.cos(an+Math.PI/3)/15);break;
			}
		else
		{
			vx *= .90;
			vz *= .90;
		}

		setLocation(x+vx,h-37,z+vz);

		/////////////////////////

		angleXZ += angleXZC;
		if(angleYZ+angleYZC > -90 && angleYZ+angleYZC < 90)angleYZ += angleYZC;

		updateView();
	}

	public void setLocation(float x,float y,float z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public void jump()
	{
		frame = 0;
		iv += 1f;
		ih = y;
	}

	public void forward()
	{
		moving = true;
		if((dir&F)!=F)dir += F;
	}

	public void right()
	{
		moving = true;
		if((dir&R)!=R)dir += R;
	}

	public void backward()
	{
		moving = true;
		if((dir&B)!=B)dir += B;
	}

	public void left()
	{
		moving = true;
		if((dir&L)!=L)dir += L;
	}

	public void stop()
	{
		moving = false;
		dir = 0;
	}

	public void keyPressed(KeyEvent e)
	{
		String key = KeyEvent.getKeyText(e.getKeyCode());

		if(key.equals("A"))setAngleXZChange(1);
		if(key.equals("D"))setAngleXZChange(-1);
		if(key.equals("W"))setAngleYZChange(1);
		if(key.equals("S"))setAngleYZChange(-1);

		if(key.equals("Space"))jump();
		if(key.equals("Left"))left();
		if(key.equals("Right"))right();
		if(key.equals("Up"))forward();
		if(key.equals("Down"))backward();
	}

	public void keyReleased(KeyEvent e)
	{
		String key = KeyEvent.getKeyText(e.getKeyCode());

		if(key.equals("A"))setAngleXZChange(0);
		if(key.equals("D"))setAngleXZChange(0);
		if(key.equals("W"))setAngleYZChange(0);
		if(key.equals("S"))setAngleYZChange(0);


		if(key.equals("Left"))stop();
		if(key.equals("Right"))stop();
		if(key.equals("Up"))stop();
		if(key.equals("Down"))stop();
	}

	public void keyTyped(KeyEvent e){}
}