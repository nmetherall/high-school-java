import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.image.*;
import javax.media.j3d.*;
import javax.vecmath.*;
import com.sun.j3d.loaders.objectfile.*;
import com.sun.j3d.loaders.*;

public class MarbleDisplay extends TransformGroup
{
	Transform3D transform1 = new Transform3D();
	Sphere sp,sp2;
	float x,y,z;

	Matrix4f rotation = null;

	static Group a = new Group(),b = new Group();

	public MarbleDisplay()
	{
		super();
		this.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);


		Texture texture2 = new TextureLoader("Earth.jpg",null).getTexture();

		Appearance appear2 = new Appearance();
		appear2.setTexture(texture2);
		Material mat2 = new Material();
		mat2.setDiffuseColor(255,255,255);
		//mat2.setLightingEnable(false);
		appear2.setMaterial(mat2);

        TextureAttributes ta2 = new TextureAttributes();
        ta2.setTextureMode(TextureAttributes.MODULATE);
        appear2.setTextureAttributes(ta2);

		sp2 = new Sphere(.75f,Sphere.GENERATE_NORMALS|Sphere.GENERATE_TEXTURE_COORDS,100,appear2);
		a.addChild(sp2);



		Texture texture1 = new TextureLoader("earth_lights.jpg",null).getTexture();

		Appearance appear = new Appearance();
		appear.setTexture(texture1);
		Material mat = new Material();
		//mat.setLightingEnable(false);
		//mat.setShininess(128f);
		mat.setSpecularColor(0,0,0);
		mat.setDiffuseColor(255,255,255);
		appear.setMaterial(mat);

		TransparencyAttributes tra = new TransparencyAttributes(TransparencyAttributes.NICEST,.5f);
		appear.setTransparencyAttributes(tra);

		TextureAttributes ta = new TextureAttributes();
		ta.setTextureMode(TextureAttributes.MODULATE);
		appear.setTextureAttributes(ta);

		sp = new Sphere(.75f,Sphere.GENERATE_NORMALS|Sphere.GENERATE_TEXTURE_COORDS,100,appear);
		b.addChild(sp);

		this.addChild(a);
		this.addChild(b);
	}

	public void setLocation(float x,float y,float z)
	{
		Matrix4f newRotation = new Matrix4f();
		float zc = -this.z+z;
		float xc = this.x-x;
		newRotation.set(new AxisAngle4f(new Vector3f(zc,0,xc),(float)Math.sqrt(zc*zc+xc*xc)));
		if(rotation != null)newRotation.mul(rotation);
		rotation = newRotation;

		transform1.set(rotation);
		transform1.setTranslation(new Vector3f(x,y,z));
		this.setTransform(transform1);

		this.x = x;
		this.y = y;
		this.z = z;
	}

}