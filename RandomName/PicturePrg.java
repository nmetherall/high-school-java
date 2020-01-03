import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


	class PicturePrg
	{

		PicturePrg()
		{
		}

		ImageIcon picture;

		public void paint( Graphics g)
		{
			super.paint(g);
				try
				{
					picture = new ImageIcon(getClass().getResource("Pictures\\DSCF0007.jpg"));
					picture.paintIcon(this,g,20,250);
				}
				catch (NullPointerException npe)
				{
					//System.out.println(npe);
					picture = new ImageIcon(getClass().getResource("Pictures\\DSCF0007.jpg"));
					picture.paintIcon(this,g,20,250);
				}
		}// end of constructor

	}// end of PicturePrg class