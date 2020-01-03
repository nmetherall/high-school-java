public class Marble
{
	float x,y,z;
	float vx,vy,vz;
	float iv,ih;
	float frame = 0;
	MarbleDisplay display = new MarbleDisplay();

	boolean moving;
	int dir;

	final int F = 1;
	final int R = 2;
	final int B = 4;
	final int L = 8;

	public Marble()
	{
		x = 0;
		y = 0;
		z = 0;
	}

	public void update()
	{
		frame += .5;

		float h = iv==0?0:(-.01f)*(frame*frame)+(iv*frame)+ih;

		if(h < 0)
		{
			frame = 0;
			ih = 0;
			iv = (.01f*frame)+iv;//velocity of the object inverted
			iv *= .85;//causes it to slow down
			iv -= .075;//prevents it from bouncing only slightly
			if(iv < 0) iv = 0;//stops vertical velocity when too small
			y = 0;
		}

		float an = (float)Math.toRadians(Display.angleXZ);

		if(moving)
			switch(dir)
			{
				case 1:	vx = -(float)(Math.sin(an)/15);
						vz = -(float)(Math.cos(an)/15);break;
				case 2:	vx = -(float)(Math.sin(an-Math.PI/2)/15);
						vz = -(float)(Math.cos(an-Math.PI/2)/15);break;
				case 4:	vx = -(float)(Math.sin(an+Math.PI)/15);
						vz = -(float)(Math.cos(an+Math.PI)/15);break;
				case 8:	vx = -(float)(Math.sin(an+Math.PI/2)/15);
						vz = -(float)(Math.cos(an+Math.PI/2)/15);break;
				case 3:	vx = -(float)(Math.sin(an-Math.PI/3)/15);
						vz = -(float)(Math.cos(an-Math.PI/3)/15);break;
				case 6:	vx = -(float)(Math.sin(an-Math.PI*3/4)/15);
						vz = -(float)(Math.cos(an-Math.PI*3/4)/15);break;
				case 12:vx = -(float)(Math.sin(an-Math.PI*4/3)/15);
						vz = -(float)(Math.cos(an-Math.PI*4/3)/15);break;
				case 9:	vx = -(float)(Math.sin(an+Math.PI/3)/15);
						vz = -(float)(Math.cos(an+Math.PI/3)/15);break;
			}
		else
		{
			vx *= .97;
			vz *= .97;
		}

		setLocation(x+vx,h,z+vz);
	}

	public void setLocation(float x,float y,float z)
	{
		this.x = x;
		this.y = y;
		this.z = z;

		LightSystem.update(x,y,z);
		display.setLocation(x,y,z);
		Display.setViewCenter(x,y,z);
	}

	public void jump()
	{
		frame = 0;
		iv += .3f;
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

	public void invert(float x2,float z2)
	{
		System.out.println("A = "+(-.01f*frame)+iv);
		System.out.println("A2 = "+(.01f*frame)+iv);
		vx *= -1;
		vz *= -1;
		vx += x>0?Math.sin(x-x2)*y/20:-Math.sin(x-x2)*y/20;
		vz += z>0?Math.cos(z-z2)*y/20:-Math.cos(z-z2)*y/20;
		ih = y;
		iv = iv==0?0:(-.01f*frame)+iv;
		//iv *= .85;
		//iv -= .075;
		frame = 0;
		System.out.println("B = "+iv);
	}

	public void stop()
	{
		moving = false;
		dir = 0;
	}
}