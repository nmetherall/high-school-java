import java.awt.*;
import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.image.*;
import javax.media.j3d.*;
import javax.vecmath.*;

public class LightSystem extends TransformGroup
{
	static PointLight l2;

	public LightSystem()
	{
		Point3f center = new Point3f(0,10f,0);
		BoundingSphere bounds = new BoundingSphere(new Point3d(center),100f);

		PointLight l1 = new PointLight();
		l1.setInfluencingBounds(bounds);
		l1.setPosition(center);
		l1.removeAllScopes();
		l1.setColor(new Color3f(Color.WHITE));
		l1.addScope(MarbleDisplay.a);
		this.addChild(l1);

		l2 = new PointLight();
		l2.setCapability(PointLight.ALLOW_POSITION_WRITE);
		l2.setInfluencingBounds(bounds);
		l2.setPosition(new Point3f(0,-10f,0));
		l2.removeAllScopes();
		l2.setColor(new Color3f(Color.WHITE));
		l2.addScope(MarbleDisplay.b);
		this.addChild(l2);

		AmbientLight lightA = new AmbientLight();
		lightA.setInfluencingBounds(bounds);
		lightA.setColor(new Color3f(Color.WHITE));
		this.addChild(lightA);
	}
	public static void update(float x, float y, float z)
	{
		float xc = x;
		float yc = y-10;
		float zc = z;

		l2.setPosition(x+xc,y+yc,z+zc);
		//System.out.println("AAA");
	}
}