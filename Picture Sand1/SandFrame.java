import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import javax.imageio.*;
import java.util.ArrayList;

class SandFrame extends JPanel //implements Map
{
	int width, height;
	ArrayList<Position> position = new ArrayList<Position>();
	Map<Position, Sand> sand;
	int count = 0;

	SandFrame(int x, int y, int width, int height)
	{
		super();
		this.width = width;
		this.height = height;
		sand = new HashMap<Position, Sand>();
		setBounds(x, y, width, height);
		Sand.setWidth(width);
		Sand.setHeight(height);
		addSand(20,20,loadImage(chooseImage()));
	}

	public void addSand(int x, int y, BufferedImage im)
	{
		double size = (int)((im.getWidth()+im.getHeight()/2)/200);
		if(size==0)size=1;
		for(int i = 1; i<=im.getWidth()-1; i+=size)
			for(int j = 1; j<=im.getHeight()-1; j+=size)
			{
				Position p = new Position((int)((500 - im.getWidth()/size)/2+i/size), (int)((500-im.getHeight()/size)/2+j/size));
				new Sand(p,im.getRGB(i,j), sand, position);
			}

		Collections.shuffle(position);
	}

	public void update(Graphics g)
	{
		Iterator it = position.iterator();
		count++;
		if(count%3 == 0)
		{
			g.setColor(Color.BLACK);
			g.fillRect(0,0,width+5,height+5);
		}

		while(it.hasNext())
		{
			Sand s = sand.get(it.next());
			s.move(sand,position);
			if(count%3 == 0)
				paint(g, s);
		}
	}

	public void paint(Graphics g, Sand s)
	{
		g.setColor(s.color);
		g.fillRect(s.p.x, s.p.y, 1, 1);
	}

	public static BufferedImage loadImage(String a)
	{
		try
		{
			return ImageIO.read(new File(a));
		}
		catch(Exception e){e.printStackTrace();}

		return null;
	}

	public String chooseImage()
	{
			FileDialog fd = new FileDialog((JFrame)null,"Choose an Image to Convert",FileDialog.LOAD);
			fd.setVisible(true);
			return fd.getDirectory()+fd.getFile();
	}

}