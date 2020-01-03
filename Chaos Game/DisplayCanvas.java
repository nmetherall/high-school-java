import javax.swing.*;
import java.awt.*;
import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.image.*;
import javax.media.j3d.*;
import javax.vecmath.*;

public class DisplayCanvas extends Canvas3D
{
	ChaosGame cg;
	public DisplayCanvas(GraphicsConfiguration a, ChaosGame cg)
	{
		super(a);
		this.cg=cg;
	}

	public void postRender()
	{
		//cg.make();
	}
}