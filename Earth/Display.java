import javax.swing.*;
import java.awt.*;
import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.image.*;
import javax.media.j3d.*;
import javax.vecmath.*;

public class Display extends JFrame
{
	static Display disp;
	BranchGroup scene;
	Canvas3D canvas3D;
	SimpleUniverse simpleU;
	Transform3D view;
	Transform3D view2 = new Transform3D();

	static float angleXZ = 0;
	float angleXZC = 0;

	float angleYZ = 0;
	float angleYZC = 0;

	static float x,y,z;//camera target
	static float cx,cy,cz;//camera location



	public Display(Program p)
	{
		disp = this;

		setTitle("3D");
		setLayout(new BorderLayout());
        GraphicsConfiguration config = SimpleUniverse.getPreferredConfiguration();

        DisplayCanvas canvas3D = new DisplayCanvas(config);
        canvas3D.setStereoEnable(false);
        add("Center", canvas3D);
		canvas3D.addKeyListener(p);

        scene = new BranchGroup();
		scene.addChild(new Background(new BackgroundGeo()));

        scene.addChild(new Level());
        scene.addChild(new LightSystem());

		view = new Transform3D();

        simpleU = new SimpleUniverse(canvas3D);
        simpleU.getViewingPlatform().getViewPlatformTransform().setTransform(view);

        setSize(300,300);
        setVisible(true);
	}

	public void start()
	{
		simpleU.addBranchGraph(scene);
	}

	public void setAngle()
	{
		float phi = (float)Math.toRadians(angleYZ);
		float theta = (float)Math.toRadians(angleXZ);
		float r = 12;

		cz = r * (float)Math.cos(phi) * (float)Math.cos(theta) + z;
		cx = r * (float)Math.cos(phi) * (float)Math.sin(theta) + x;
		cy = r * (float)Math.sin(phi) + y;

		view.lookAt(new Point3d(cx,cy,cz),new Point3d(x,y,z),new Vector3d(0,1,0));
		view.invert();

		simpleU.getViewingPlatform().getViewPlatformTransform().setTransform(view);
	}

	public static void setViewCenter(float a,float b,float c)
	{
		x = a;
		y = b;
		z = c;
	}

	public void setAngleXZChange(float c)
	{
		angleXZC = c;
	}

	public void setAngleYZChange(float c)
	{
		angleYZC = c;
	}

	public void update()
	{
		angleXZ += angleXZC;
		if(angleYZ+angleYZC > 0 && angleYZ+angleYZC < 90)angleYZ += angleYZC;

		setAngle();
	}

	public void add(TransformGroup t)
	{
		scene.addChild(t);
	}
}