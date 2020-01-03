import javax.imageio.*;
import java.io.*;
import java.awt.image.*;
import javax.swing.*;
import java.awt.*;
import java.awt.Graphics2D.*;
import java.util.*;

public class TextProgram
{
	BufferedImage image;
	int w,h;
	int wBlockSize = 15;
	int hBlockSize = 30;
	BufferedImage im;
	BufferedImage the[];

	public TextProgram()
	{
		Scanner s = new Scanner(System.in);

		while(true)
		{
			String name = chooseImage();
			image = loadImage(name);
			if(name.endsWith("avi"))
			{

			}
			else{init();}

			System.out.println("\nPress Enter to continue.");
			try
			{
				s.nextLine();
			}catch(Exception e){}
		}
	}

	public void init()
	{
		w = image.getWidth();
		h = image.getHeight();
		Graphics2D d;
		wBlockSize = w/60;
		hBlockSize = h/50;
		im = new BufferedImage(60*6,50*10,BufferedImage.TYPE_USHORT_555_RGB);
		initIm();
		double t = Math.random()*200;

		String str="";

		for(int y = 0; y < h-hBlockSize; y += hBlockSize)
		{
			for(int x = 0; x < w-wBlockSize; x += wBlockSize)
			{
				try
				{
					d = im.createGraphics();
					BufferedImage section = image.getSubimage(x,y,wBlockSize,hBlockSize);
					System.out.print(getScaledChar(colorAve(section)));
					d.drawImage(the[colorAve(section)/60],x/wBlockSize*6,y/hBlockSize*10,null);
					str+=getScaledChar(colorAve(section));
				}catch(NullPointerException ex){}
			}
			str+="\n";
			System.out.println();
		}
		try
		{
			File f = new File("outputText\\Output"+t+".txt");
			FileWriter f2 = new FileWriter(f);
			f2.write(str);
			f2.flush();
			ImageIO.write(im,"jpg",new File("outputJPG\\Output"+t+".jpg"));
		}catch (IOException e) {}
		//JOptionPane.showMessageDialog(null, "Coversion Complete", "Done",0);
	}

	public String chooseImage()
	{
		FileDialog fd = new FileDialog((JFrame)null,"Choose an Image to Convert",FileDialog.LOAD);
		fd.setVisible(true);
		return fd.getDirectory()+fd.getFile();
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

	public static int colorAve(BufferedImage ra)
	{
		long k = 0;
		for(int i =0; i <= ra.getWidth()-1; i++)
			for(int j =0;j <= ra.getHeight()-1; j++)
			{
				Color p = new Color(ra.getRGB(i,j));
				k+=p.getRed()+p.getGreen()+p.getBlue();
			}
		k/=(ra.getWidth()*ra.getHeight());

		return (int)k;
	}

	public static char getScaledChar(int val)
	{
		switch(val/60)
		{
			case 0: return ' ';
			case 1: return '.';
			case 2: return '`';
			case 3: return '"';
			case 4: return '^';
			case 5: return '+';
			case 6: return '[';
			case 7: return '%';
			case 8: return '$';
			case 9: return '*';
			case 10: return '@';
			case 11: return '&';
			case 12: return '#';
		}
		return ' ';
	}

	public static String getScaledFile(int val)
	{
		switch(val)
		{
			case 0: return "chars\\space.jpg";
			case 1: return "chars\\period.jpg";
			case 2: return "chars\\singleQuote.jpg";
			case 3: return "chars\\quote.jpg";
			case 4: return "chars\\^.jpg";
			case 5: return "chars\\+.jpg";
			case 6: return "chars\\[.jpg";
			case 7: return "chars\\%.jpg";
			case 8: return "chars\\$.jpg";
			case 9: return "chars\\star.jpg";
			case 10: return "chars\\@.jpg";
			case 11: return "chars\\&.jpg";
			case 12: return "chars\\#.jpg";
		}
		return " ";
	}

	public static int getScaledNumber(int val)
		{
			switch(val/60)
			{
				case 0: return 0;
				case 1: return 1;
				case 2: return 2;
				case 3: return 3;
				case 4: return 4;
				case 5: return 5;
				case 6: return 6;
				case 7: return 7;
				case 8: return 8;
				case 9: return 9;
				case 10: return 10;
				case 11: return 11;
				case 12: return 12;
			}
			return 0;
	}

	public void initIm()
	{
		the = new BufferedImage[13];
		for(int i=0; i<13; i++)
		{
			the[i] = loadImage(getScaledFile(i));
		}
	}

	public static void main(String args[])
	{
		new TextProgram();
	}
}