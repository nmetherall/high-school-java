import java.awt.*;
import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.image.*;
import javax.media.j3d.*;
import javax.vecmath.*;

public class MarbleDisplay extends TransformGroup
{
	Transform3D transform1 = new Transform3D();
	Transform3D trans = new Transform3D();
	Transform3D trans2 = new Transform3D();
	Transform3D transMars = new Transform3D();
	Transform3D transVenus = new Transform3D();
	Transform3D transJupitor = new Transform3D();

	TransformGroup jupitorGroup;
	TransformGroup venusGroup;
	TransformGroup moonGroup;
	TransformGroup earthGroup;
	TransformGroup marsGroup;
	OrientedShape3D shape = null;
	float g;
	float h;
	float mar;
	float ven;
	float jup;

	public MarbleDisplay()
	{
		super();
		this.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

		Texture texture = new TextureLoader("sun.bmp",null).getTexture();

		Appearance appear1 = new Appearance();
		appear1.setTexture(texture);
		Material mat = new Material();
		mat.setDiffuseColor(50,50,50);
		//appear1.setMaterial(mat);

        TextureAttributes ta = new TextureAttributes();
        ta.setTextureMode(TextureAttributes.MODULATE);
        appear1.setTextureAttributes(ta);

		Sphere sp = new Sphere(2,Sphere.GENERATE_NORMALS|Sphere.GENERATE_TEXTURE_COORDS,50,appear1);
		this.addChild(sp);



		earthGroup = new TransformGroup();
		earthGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

		Texture texture2 = new TextureLoader("Earth.jpg",null).getTexture();

		Appearance appear2 = new Appearance();
		appear2.setTexture(texture2);
		Material mat2 = new Material();
		mat2.setDiffuseColor(50,50,50);
		appear2.setMaterial(mat2);

		TextureAttributes ta2 = new TextureAttributes();
		ta2.setTextureMode(TextureAttributes.MODULATE);
		appear2.setTextureAttributes(ta2);

		Sphere sp2 = new Sphere(.1f,Sphere.GENERATE_NORMALS|Sphere.GENERATE_TEXTURE_COORDS,50,appear2);
		earthGroup.addChild(sp2);


		earthGroup.setTransform(trans);

		this.addChild(earthGroup);



		moonGroup = new TransformGroup();
		moonGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

		Texture texture3 = new TextureLoader("Moon3.bmp",null).getTexture();

		Appearance appear3 = new Appearance();
		appear3.setTexture(texture3);
		Material mat3 = new Material();
		mat3.setDiffuseColor(50,50,50);
		appear3.setMaterial(mat3);

		TextureAttributes ta3 = new TextureAttributes();
		ta3.setTextureMode(TextureAttributes.MODULATE);
		appear3.setTextureAttributes(ta3);

		Sphere sp3 = new Sphere(.03f,Sphere.GENERATE_NORMALS|Sphere.GENERATE_TEXTURE_COORDS,50,appear3);
		moonGroup.addChild(sp3);


		moonGroup.setTransform(trans2);

		this.addChild(moonGroup);



		marsGroup = new TransformGroup();
		marsGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

		Texture textureMars = new TextureLoader("mars.jpg",null).getTexture();

		Appearance appearMars = new Appearance();
		appearMars.setTexture(textureMars);
		Material matMars = new Material();
		matMars.setDiffuseColor(50,50,50);
		appearMars.setMaterial(matMars);

		TextureAttributes taMars = new TextureAttributes();
		taMars.setTextureMode(TextureAttributes.MODULATE);
		appearMars.setTextureAttributes(taMars);

		Sphere spMars = new Sphere(.1f,Sphere.GENERATE_NORMALS|Sphere.GENERATE_TEXTURE_COORDS,50,appearMars);
		marsGroup.addChild(spMars);


		marsGroup.setTransform(transMars);

		this.addChild(marsGroup);




		venusGroup = new TransformGroup();
		venusGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

		Texture textureVenus = new TextureLoader("venus.jpg",null).getTexture();

		Appearance appearVenus = new Appearance();
		appearVenus.setTexture(textureVenus);
		Material matVenus = new Material();
		matVenus.setDiffuseColor(50,50,50);
		appearVenus.setMaterial(matVenus);

		TextureAttributes taVenus = new TextureAttributes();
		taVenus.setTextureMode(TextureAttributes.MODULATE);
		appearVenus.setTextureAttributes(taVenus);

		Sphere spVenus = new Sphere(.1f,Sphere.GENERATE_NORMALS|Sphere.GENERATE_TEXTURE_COORDS,50,appearVenus);
		venusGroup.addChild(spVenus);


		venusGroup.setTransform(transVenus);

		this.addChild(venusGroup);




		jupitorGroup = new TransformGroup();
		jupitorGroup.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

		Texture textureJupitor = new TextureLoader("jupiter.jpg",null).getTexture();

		Appearance appearJupitor = new Appearance();
		appearJupitor.setTexture(textureJupitor);
		Material matJupitor = new Material();
		matJupitor.setDiffuseColor(50,50,50);
		appearJupitor.setMaterial(matJupitor);

		TextureAttributes taJupitor = new TextureAttributes();
		taJupitor.setTextureMode(TextureAttributes.MODULATE);
		appearJupitor.setTextureAttributes(taJupitor);

		Sphere spJupitor = new Sphere(.6f,Sphere.GENERATE_NORMALS|Sphere.GENERATE_TEXTURE_COORDS,50,appearJupitor);
		jupitorGroup.addChild(spJupitor);


		jupitorGroup.setTransform(transJupitor);

		this.addChild(jupitorGroup);

	}

	public void setLocation(float x,float y,float z)
	{
		//Matrix4f mat = new Matrix4f();
		//mat.set(new AxisAngle4f(z,y,x,x+y+z));

		//transform1.set(mat);
		ven +=.13;
		jup+=.10;
		mar +=.11;
		g += .15;
		h += 5;
		transJupitor.setTranslation(new Vector3f((float)Math.cos(Math.toRadians(jup))*20f,0,(float)Math.sin(Math.toRadians(jup))*20f));
		transVenus.setTranslation(new Vector3f((float)Math.cos(Math.toRadians(ven))*6f,0,(float)Math.sin(Math.toRadians(ven))*6f));
		transMars.setTranslation(new Vector3f((float)Math.cos(Math.toRadians(mar))*14f,0,(float)Math.sin(Math.toRadians(mar))*14f));
		trans.setTranslation(new Vector3f((float)Math.cos(Math.toRadians(g))*10f,0,(float)Math.sin(Math.toRadians(g))*10f));
		trans2.setTranslation(new Vector3f(((float)Math.cos(Math.toRadians(g))*10f)+((float)Math.cos(Math.toRadians(h))*.5f),0,((float)Math.sin(Math.toRadians(g))*10f)+((float)Math.sin(Math.toRadians(h))*.5f)));
		//trans.setTranslation(new Vector3f(2,2,2));
		earthGroup.setTransform(trans);
		moonGroup.setTransform(trans2);
		jupitorGroup.setTransform(transJupitor);
		marsGroup.setTransform(transMars);
		venusGroup.setTransform(transVenus);
		transform1.setTranslation(new Vector3f(x,y,z));
		//this.setTransform(transform1);
	}

}