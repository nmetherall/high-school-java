import java.awt.*;
import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.image.*;
import javax.media.j3d.*;
import javax.vecmath.*;

public class Light extends TransformGroup
{
	RotationInterpolator ri;

	public Light()
	{
		super();
		this.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

		DirectionalLight light = new DirectionalLight();

		Alpha a = new Alpha(-1,30000);

		ri = new RotationInterpolator(a,this);
		this.addChild(ri);

		BoundingSphere bounds = new BoundingSphere();
		ri.setSchedulingBounds(bounds);

		light.setInfluencingBounds(bounds);
		light.setDirection(new Vector3f(0.0f, 0.0f, -1.0f));
		light.setColor(new Color3f(Color.WHITE));

		addChild(light);
	}
}