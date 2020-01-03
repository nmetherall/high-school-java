import java.awt.*;
import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.image.*;
import javax.media.j3d.*;
import javax.vecmath.*;

public class MarbleDisplay extends TransformGroup
{
	static Transform3D t3 = new Transform3D();
	RotationInterpolator ri;

	public MarbleDisplay()
	{
		super(t3);
		this.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

		TransformGroup objs = new TransformGroup();
		objs.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
		this.addChild(objs);

		t3.setTranslation(new Vector3f(0.0f, 0.0f, -3.0f));
		setTransform(t3);

		Texture texture = new TextureLoader("Earth.jpg",null).getTexture();

		Appearance appear1 = new Appearance();
		appear1.setTexture(texture);
		Material mat = new Material();
		mat.setDiffuseColor(50,50,50);
		appear1.setMaterial(mat);

        TextureAttributes ta = new TextureAttributes();
        ta.setTextureMode(TextureAttributes.MODULATE);
        appear1.setTextureAttributes(ta);

		Sphere sp = new Sphere(1,Sphere.GENERATE_NORMALS|Sphere.GENERATE_TEXTURE_COORDS,100,appear1);
		objs.addChild(sp);

		Alpha a = new Alpha(-1,10000);

		//ri = new RotationInterpolator(a,objs);
		//objs.addChild(ri);

		BoundingSphere bounds = new BoundingSphere();
		//ri.setSchedulingBounds(bounds);

		DirectionalLight lightD = new DirectionalLight();
		lightD.setInfluencingBounds(bounds);
		lightD.setDirection(new Vector3f(0.0f, 0.0f, -1.0f));
		lightD.setColor(new Color3f(Color.WHITE));
		objs.addChild(lightD);

		AmbientLight lightA = new AmbientLight();
		lightA.setInfluencingBounds(bounds);
		lightA.setColor(new Color3f(Color.WHITE));
		objs.addChild(lightA);

		Display.disp.add(this);
	}

	public void setRotation(float a,float b,float c)
	{
		t3.rotX(a);
		m.rotY(a);
		t3.rotZ(a);
	}

	public void setLocation(float x,float y,float z)
	{
		t3.setTranslation(new Vector3f(x,y,z));
		setTransform(t3);
	}

}