import javax.swing.*;
import java.awt.*;
import javax.imageio.*;
import java.io.*;
import java.awt.image.*;
class Fish extends JWindow
{
	double endX, endY, speed,screenX, screenY;
	double posX;
	double posY;
	double q,w,e,r;
	Image fishy,back,deadFishy;
	int dead;
	static BufferedImage background;
	Fish()
	{
		dead= false;
		screenX = (double)(Toolkit.getDefaultToolkit().getScreenSize().width)-100;
		screenY = (double)(Toolkit.getDefaultToolkit().getScreenSize().height)-100;
		posX=360;
		posY=360;
		setBounds(0,0,100,100);
		setVisible(true);
		endX= (Math.random()*screenX);
		endY= (Math.random()*screenY);
		try
		{
			deadFishy = ImageIO.read(new File("fishdead.jpg"));
			deadFishy = deadFishy.getScaledInstance(100,100,Image.SCALE_SMOOTH);

			fishy = ImageIO.read(new File("fish.jpg"));
			fishy = fishy.getScaledInstance(100,100,Image.SCALE_SMOOTH);

			Robot r = new Robot();
			background = r.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
		}catch(Exception e){}
		fishy = makeWhiteTransparent(fishy,Color.WHITE);
		createBufferStrategy(2);

		repaint();
	}

	void update()
	{
		if(dead!=0 && (int)(Math.random()*10000)==5)
		{
			dead=1;
			fishy = deadFishy;
			fishy = makeWhiteTransparent(fishy,Color.WHITE);
		}
		if(dead==1)deadUpdate();
		else if((int)Math.sqrt((posX-endX)*(posX-endX) + (posY-endY)*(posY-endY))< 1)
		{
			endX = (int)(Math.random()*screenX);
			endY = (int)(Math.random()*screenY);

		}
		else
		{
			posX -= (posX-endX)/100d;
			posY -= (posY-endY)/100d;
			setLocation((int)(posX),(int)(posY));
			back=background.getSubimage((int)posX,(int)posY,100,100);
			repaint();
		}
	}


	public void paint(Graphics g1)
	{
		//GifImage flipHorizontalGifImage = GifTransformer.flipHorizontal(gifImage);(Math.toRadians(Math.atan((posY-endY)/(posX-endX)) ));
		if(getBufferStrategy()==null)
		return;
		Graphics2D g = (Graphics2D)getBufferStrategy().getDrawGraphics();
		g.drawImage(back, 0, 0, this);

		if((posX-endX)>0){g.scale(-1,1);g.translate(-100,0);g.rotate(Math.atan((posY-endY)/-(posX-endX)),50,50);}
		else if(dead==true) g.rotate(185,50,50);
		else g.rotate(Math.atan((posY-endY)/(posX-endX)),50,50);
		g.drawImage(fishy,0, 0, this);
		getBufferStrategy().show();
	}

	public Image makeWhiteTransparent(Image im,Color c1)
	{
		final int c = c1.getRGB();
		final int t = new Color(0,0,0,0).getRGB();

		ImageFilter filter = new RGBImageFilter()
		{
			public final int filterRGB(int x, int y, int rgb)
			{
				if(rgb==c)
					return t;
				else
					return rgb;
			}
		};

		ImageProducer source = new FilteredImageSource(im.getSource(), filter);
		return Toolkit.getDefaultToolkit().createImage(source);
	}

	static void backgroundUpdate()
	{
		try
		{
			Robot r = new Robot();
			background = r.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
		}catch(Exception e){}
	}

	void deadUpdate()
	{
		//endY=0;
		endX=posX;

		if(posY<=3)endY=20;
		if(endY==20 && posY <= 20)
		{
			posY += (.1);
			setLocation((int)(posX),(int)(posY));
			back=background.getSubimage((int)posX,(int)posY,100,100);
			repaint();
		}
		else
		{
			endY=0;
			/*if(posY-3>=endY)*/posY -= (.1);
			setLocation((int)(posX),(int)(posY));
			back=background.getSubimage((int)posX,(int)posY,100,100);
			repaint();
		}
	}

}//end of class Fish