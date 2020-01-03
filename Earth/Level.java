import java.awt.*;
import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.image.*;
import javax.media.j3d.*;
import javax.vecmath.*;

public class Level extends TransformGroup
{
	static Transform3D t3 = new Transform3D();

	public Level()
	{
		this.setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);

		Texture texture = new TextureLoader("ground.jpg",null).getTexture();

		Appearance appear1 = new Appearance();
		appear1.setMaterial(new Material());
		appear1.setTexture(texture);

        TextureAttributes ta = new TextureAttributes();
        ta.setTextureMode(TextureAttributes.MODULATE);
        appear1.setTextureAttributes(ta);

		Box box = new Box(10,1,10,Box.GENERATE_NORMALS|Box.GENERATE_TEXTURE_COORDS,appear1);
		this.addChild(box);

		/*Appearance sphereA1 = new Appearance();
		Material mat = new Material();
		mat.setDiffuseColor(50,50,50);
		sphereA1.setMaterial(mat);

		Sphere sp = new Sphere(4,0,100,sphereA1);
		this.addChild(sp);*/

		t3.setTranslation(new Vector3f(0.0f, -2f, 0f));
		this.setTransform(t3);
	}
}