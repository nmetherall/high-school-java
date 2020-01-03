import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

class ImageSourceExample extends JFrame
{
	int WIDTH = 640;
	int HEIGHT = 480;

	Node q;

	MemoryImageSource source;
	Image image;

	//int t = 0;

	ImageSourceExample()
	{
		q = new Node(WIDTH,HEIGHT,500);

		setSize(WIDTH,HEIGHT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		source = new MemoryImageSource(WIDTH,HEIGHT,q.color,0,WIDTH);
		source.setFullBufferUpdates(true);
		source.setAnimated(true);
		image = createImage(source);

		for(int i = 0 ; i<500; i++)
		{
			new Node((int)(Math.random()*WIDTH),(int)(Math.random()*HEIGHT),1,(int)(Math.random()*3),(int)(Math.random()*Integer.MAX_VALUE),q,i);
		}


		setVisible(true);

		while(true)
		{
			//t++;
			q = Node.mouseChange(q);
			//for(int x = 0; x < WIDTH; x++)
			//	for(int y = 0; y < HEIGHT; y++)
					//q.color[x*y];

			repaint();

			try
			{
				Thread.sleep(50);
			}
			catch(Exception e){}
		}
	}

	public void paint(Graphics g)
	{
		source.newPixels();
		g.drawImage(image,0,0,null);
	}

	public static void main(String args[])
	{
		new ImageSourceExample();
	}
}