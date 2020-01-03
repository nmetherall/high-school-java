import java.awt.*;
import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.image.*;
import javax.media.j3d.*;
import javax.vecmath.*;

public class MarbleDisplay extends TransformGroup
{
	Transform3D transform1 = new Transform3D();
	OrientedShape3D shape = null;

	public MarbleDisplay()
	{
		super();
		this.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

		Texture texture = new TextureLoader("Earth.jpg",null).getTexture();

		Appearance appear1 = new Appearance();
		appear1.setTexture(texture);
		Material mat = new Material();
		mat.setDiffuseColor(50,50,50);
		appear1.setMaterial(mat);

        TextureAttributes ta = new TextureAttributes();
        ta.setTextureMode(TextureAttributes.MODULATE);
        appear1.setTextureAttributes(ta);

		Sphere sp = new Sphere(1,Sphere.GENERATE_NORMALS|Sphere.GENERATE_TEXTURE_COORDS,500,appear1);
		this.addChild(sp);
	}

	public void setLocation(float x,float y,float z)
	{
		Matrix4f mat = new Matrix4f();
		mat.set(new AxisAngle4f(z,y,x,x+y+z));

		transform1.set(mat);
		transform1.setTranslation(new Vector3f(x,y,z));
		this.setTransform(transform1);
	}

}