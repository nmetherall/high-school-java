import java.awt.*;
import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.image.*;
import javax.media.j3d.*;
import javax.vecmath.*;

public class LightSystem extends TransformGroup
{
	public LightSystem()
	{
		Point3f center = new Point3f(0,0f,0);
		BoundingSphere bounds = new BoundingSphere(new Point3d(center),100f);

		PointLight l1 = new PointLight();
		l1.setInfluencingBounds(bounds);
		l1.setPosition(center);
		l1.setColor(new Color3f(Color.WHITE));
		this.addChild(l1);

		AmbientLight lightA = new AmbientLight();
		lightA.setInfluencingBounds(bounds);
		lightA.setColor(new Color3f(Color.WHITE));
		this.addChild(lightA);
	}
}