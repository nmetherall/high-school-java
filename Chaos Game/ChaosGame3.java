import javax.swing.*;
import java.awt.*;
import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.image.*;
import javax.media.j3d.*;
import javax.vecmath.*;
import java.awt.event.*;

class ChaosGame3 implements KeyListener
{
	int count,count2,rand;
	double x1,y1,z1;

	Canvas3D canvas;
	BranchGroup scene;
	SimpleUniverse simple;
	JFrame frame;
	PointArray point;
	Point3d[] points = new Point3d[100];
	Transform3D view;

	double cx = 15,cy = 15,cz = 15;

	ChaosGame3()
	{
		//Point(int vertexCount, int vertexFormat)
		frame = new JFrame("3D Chaos Game");
		frame.setBounds(100,100,500,500);

		count =count2= 0;
		canvas = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
		simple = new SimpleUniverse(canvas);

		frame.add(canvas);
		frame.setVisible(true);
		//frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
		canvas.setFocusable(true);
		canvas.addKeyListener(this);



		view = new Transform3D();

		view.lookAt(new Point3d(15,15,15),new Point3d(0,0,0),new Vector3d(0,1,0));
		view.invert();
		simple.getViewingPlatform().getViewPlatformTransform().setTransform(view);

		change(0,0,0);
		for(int i = 0;i<2000;i++)
		{
			count=count2=0;
			change(x1,y1,z1);
			try
			{
				Thread.sleep(70);
			}catch(Exception e){}
		}
	}//end of ChaosGame3()

	public double update(double a,double b,double c)
	{
		return ((a*x1)+(b*y1)+c);
	}

	public double updateZ(double a,double b,double c)
	{
	return ((a*z1)+(b*y1)+c);
	}

	public void change(double x,double y, double z)
	{
		x1=x;
		y1=y;
		z1=z;
		count+=1;
		count2+=1;
		Double r = Math.random();
		if(r<=.01)
		{
			x = update(0,0,.16);
			//y = update(0,0,0);

		}

		else if(.005< r && r <= .43)
		{
			x = update(.85,.04,0);
			//y = update(-.04,.85,1.6);
		}

		else if(.43< r && r <= .465)
		{
			x = update(.2,-.26,0);
			y = update(.23,.22,1.6);
		}

		else if(.465< r && r <= .5)
		{
			x = update(-.15,.28,0);
			y = update(.26,.24,.44);
		}

		if(.5<r &&r<=.51)
		{
			z = updateZ(0,0,.16);
			//y = update(0,0,0);

		}

		else if(.51< r && r <= .86)
		{
			z = updateZ(.85,.04,0);
			//y = update(-.04,.85,1.6);
		}

		else if(.86<r && r <= .93)
		{
			z = updateZ(.2,-.26,0);
			y = update(.23,.22,1.6);
		}

		else if(.93< r && r <= 1)
		{
			z = updateZ(-.15,.28,0);
			//y = update(.26,.24,.44);
		}

		/*rand = (int)(Math.random()*100);//generates a random number to decide which case is called

		switch(rand)
		{

			case 0:
			{
				x+=2;//changes A change of fractal for x
				y+=3;//changes A change of fractal for y
				z+=1;//changes A change of fractal for z
				break;
			}//end case 0

			case 1:
			{
				x+=(int)(-.70*x);//changes B change of fractal for x
				y+=(int)(-.80*y);//changes B change of fractal for y
				z+=(int)(-.20*z);//changes B change of fractal for z
				break;
			}//end case 1

			case 2:
			{
				x+=(int)(1);//changes C change of fractal for x
				y+=(int)(-1);//changes C change of fractal for y
				z+=(int)(1);//changes C change of fractal for z
				break;
			}//end case 2
		}//end of switch*/

		points[count-1] = new Point3d(x,y,z);
		if(count == 100)
		{
			count = 0;
			PointArray arr = new PointArray(100,PointArray.COORDINATES);
			arr.setCoordinates(0,points);
			BranchGroup b = new BranchGroup();

			b.addChild(new Shape3D(arr));
			simple.addBranchGraph(b);//adds Points to Scene
		}

		if(count2==200)
		{
			x1=x;
			y1=y;
			z1=z;
			return;
		}
		change(x,y,z);//calls make with new values for x,y,and z
	}//end of make()

	public void keyPressed(KeyEvent e)
	{
		switch(e.getKeyCode())
		{
			case KeyEvent.VK_LEFT:
			{

				//System.out.println("1");
				view.lookAt(new Point3d(cx++,cy,cz),new Point3d(0,0,0),new Vector3d(0,1,0));
				view.invert();
				simple.getViewingPlatform().getViewPlatformTransform().setTransform(view);
				break;
			}//end case '1'

			case KeyEvent.VK_RIGHT:
			{
				System.out.println("2");
				view.lookAt(new Point3d(cx--,cy,cz),new Point3d(0,0,0),new Vector3d(0,1,0));
				view.invert();
				simple.getViewingPlatform().getViewPlatformTransform().setTransform(view);
				break;
			}//end case '2'

			case KeyEvent.VK_UP:
			{
				System.out.println("3");
				view.lookAt(new Point3d(cx,cy++,cz),new Point3d(0,0,0),new Vector3d(0,1,0));
				view.invert();
				simple.getViewingPlatform().getViewPlatformTransform().setTransform(view);
				break;
			}//end case '3'

			case KeyEvent.VK_DOWN:
			{
				System.out.println("3");
				view.lookAt(new Point3d(cx,cy--,cz),new Point3d(0,0,0),new Vector3d(0,1,0));
				view.invert();
				simple.getViewingPlatform().getViewPlatformTransform().setTransform(view);
				break;
			}//end case '3'

			case KeyEvent.VK_COMMA:
			{
				System.out.println("3");
				view.lookAt(new Point3d(cx,cy,cz++),new Point3d(0,0,0),new Vector3d(0,1,0));
				view.invert();
				simple.getViewingPlatform().getViewPlatformTransform().setTransform(view);
				break;
			}//end case '3'

			case KeyEvent.VK_PERIOD:
			{
				System.out.println("3");
				view.lookAt(new Point3d(cx,cy,cz--),new Point3d(0,0,0),new Vector3d(0,1,0));
				view.invert();
				simple.getViewingPlatform().getViewPlatformTransform().setTransform(view);
				break;
			}//end case '3'



			case KeyEvent.VK_1:
			{
				cx=10;
				cy=10;
				cz=15;
				System.out.println("3");
				view.lookAt(new Point3d(cx,cy,cz--),new Point3d(0,0,0),new Vector3d(0,1,0));
				view.invert();
				simple.getViewingPlatform().getViewPlatformTransform().setTransform(view);
				break;
			}//end case '3'
			case KeyEvent.VK_2:
			{
				cx=10;
				cy=15;
				cz=10;
				System.out.println("3");
				view.lookAt(new Point3d(cx,cy,cz--),new Point3d(0,0,0),new Vector3d(0,1,0));
				view.invert();
				simple.getViewingPlatform().getViewPlatformTransform().setTransform(view);
				break;
			}//end case '3'
			case KeyEvent.VK_3:
			{
				cx=15;
				cy=10;
				cz=10;
				System.out.println("3");
				view.lookAt(new Point3d(cx,cy,cz--),new Point3d(0,0,0),new Vector3d(0,1,0));
				view.invert();
				simple.getViewingPlatform().getViewPlatformTransform().setTransform(view);
				break;
			}//end case '3'
		}//end of switch
	}//end of keyPressed()

	public void keyReleased(KeyEvent e){}
	public void keyTyped(KeyEvent e){}

	public static void main(String args[])
	{
		ChaosGame3 g = new ChaosGame3();
	}//end of main()
}//end of class ChaosGame3