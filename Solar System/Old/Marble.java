public class Marble
{
	float x,y,z;
	float vx,vy,vz;
	MarbleDisplay display = new MarbleDisplay();

	public Marble()
	{
		x = 0;
		y = 0;
		z = 0;
	}

	public void update()
	{
		setLocation(x+vx,y+vy,z+vz);
	}

	public void setLocation(float x,float y,float z)
	{
		this.x = x;
		this.y = y;
		this.z = z;

		display.setRotation(x,y,z);
		display.setLocation(x,y,z);
		Display.setViewCenter(x,y,z);
	}

	public void startRotating()
	{

	}

	public void stopRotating()
	{

	}
}