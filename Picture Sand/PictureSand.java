import javax.swing.*;
import java.awt.event.*;
import java.awt.image.*;
import java.util.*;

class PictureSand implements MouseListener
{
	JFrame frame;
	BufferedImage back;

	static ArrayList<Sand> list = new ArrayList<Sand>();

	PictureSand()
	{
		frame = new JFrame();
		frame.setBounds(200,200,500,500);
		frame.setVisible(true);
		back = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB);
		//for(;;)
		{
			try
			{
				//Thread.sleep(1000);
			}catch(Exception e){}
		}//end of for(;;)
	}//end of PictureSand()

	public static void main(String args[])
	{
		PictureSand p = new PictureSand();

	}//end of main()

	public void mousePressed(MouseEvent evt2)
	{
		list = Sand.create(evt2.getX(), evt2.getY(), 50, 50,list);
	}
	public void mouseEntered(MouseEvent evt2){}
	public void mouseExited(MouseEvent evt2){}
	public void mouseClicked(MouseEvent evt2){}
	public void mouseReleased(MouseEvent evt2){}
}//end of PictureSand