import javax.swing.*;
import java.awt.Graphics2D;

class PictureSandDriver extends JFrame
{
	int width = 500;
	int height = 500;
	Graphics2D g;

	PictureSandDriver()
	{
		setBounds(200,200,width + 5, height + 5);
		SandFrame sf = new SandFrame(0,0,width,height);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		add(sf);
		setVisible(true);
		sf.setVisible(true);

		createBufferStrategy(2);

		for(;;)
		{
			g = (Graphics2D)this.getBufferStrategy().getDrawGraphics();
			sf.update(g);
			getBufferStrategy().show();
			try
			{
				//Thread.sleep(1);
			}catch(Exception e){}
		}
	}

	public static void main(String args[])
	{
		PictureSandDriver p = new PictureSandDriver();
	}
}