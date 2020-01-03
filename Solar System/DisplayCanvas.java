import javax.swing.*;
import java.awt.*;
import com.sun.j3d.utils.universe.*;
import com.sun.j3d.utils.geometry.*;
import com.sun.j3d.utils.image.*;
import javax.media.j3d.*;
import javax.vecmath.*;

public class DisplayCanvas extends Canvas3D
{
	public DisplayCanvas(GraphicsConfiguration a)
	{
		super(a);
	}

	public void postRender()
	{
		Program.main.update();
	}
}