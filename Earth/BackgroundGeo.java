import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.image.*;
import javax.media.j3d.*;
import javax.vecmath.*;
import com.sun.j3d.loaders.objectfile.*;
import com.sun.j3d.loaders.*;

public class BackgroundGeo extends BranchGroup
{
	Transform3D transform1 = new Transform3D();
	Sphere sp,sp2;
	float x,y,z;

	Matrix4f rotation = null;

	public BackgroundGeo()
	{
		super();
		/*Texture texture = new TextureLoader("Winter Leaves.jpg",null).getTexture();

		Appearance appear1 = new Appearance();
		appear1.setTexture(texture);
		Material mat = new Material();
		mat.setDiffuseColor(50,50,50);
		appear1.setMaterial(mat);

        TextureAttributes ta = new TextureAttributes();
        ta.setTextureMode(TextureAttributes.MODULATE);
        appear1.setTextureAttributes(ta);

        TransparencyAttributes tra = new TransparencyAttributes(TransparencyAttributes.NICEST,.7f);
		appear1.setTransparencyAttributes(tra);

		sp = new Sphere(1,Sphere.GENERATE_NORMALS|Sphere.GENERATE_TEXTURE_COORDS,100,appear1);
		this.addChild(sp);*/

		Texture texture2 = new TextureLoader("Garden.jpg",null).getTexture();

		Appearance appear2 = new Appearance();
		appear2.setTexture(texture2);
		Material mat2 = new Material();
		mat2.setDiffuseColor(50,50,50);
		appear2.setMaterial(mat2);

        TextureAttributes ta2 = new TextureAttributes();
        ta2.setTextureMode(TextureAttributes.MODULATE);
        appear2.setTextureAttributes(ta2);

		sp2 = new Sphere(10000000,Sphere.GENERATE_NORMALS_INWARD |Sphere.GENERATE_TEXTURE_COORDS,100,appear2);
		this.addChild(sp2);

		/*ObjectFile OBJECT1 = new ObjectFile(ObjectFile.RESIZE);
		Scene SCENE = null;
		try
		{
			SCENE = OBJECT1.load(ClassLoader.getSystemResource("3d.obj"));
		}
		catch(Exception e){e.printStackTrace();}

		this.addChild(SCENE.getSceneGroup());*/
	}
}