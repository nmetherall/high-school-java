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

		Texture texture = new TextureLoader("Level.jpg",null).getTexture();

		Appearance appear1 = new Appearance();
		appear1.setMaterial(new Material());
		appear1.setTexture(texture);

        TextureAttributes ta = new TextureAttributes();
        ta.setTextureMode(TextureAttributes.MODULATE);
        appear1.setTextureAttributes(ta);

		Box box = new Box(10,10,10,Box.GENERATE_NORMALS|Box.GENERATE_TEXTURE_COORDS,appear1);
		this.addChild(box);

		t3.setTranslation(new Vector3f(0.0f, -11f, 0f));
		this.setTransform(t3);

		BoundingSphere bounds = new BoundingSphere();

		DirectionalLight lightD = new DirectionalLight();
		lightD.setInfluencingBounds(bounds);
		lightD.setDirection(new Vector3f(0.0f,-1.0f, 0.0f));
		lightD.setColor(new Color3f(Color.WHITE));
		this.addChild(lightD);
	}
}