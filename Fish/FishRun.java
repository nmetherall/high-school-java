import javax.swing.*;
import java.io.*;
class FishRun
{

	FishRun()
	{
		int t=0;
		int k = Integer.parseInt(JOptionPane.showInputDialog("How many fish would you like?"));
		Fish f[] = new Fish[200];

		for(int i=0;i<k;i++)f[i]=new Fish();

		while(true)
		{
			for(int i=0;i<k;i++)
				f[i].update();
			try
			{
				Thread.sleep(0);
				t++;
				if(t%500==0)
				{
					//for(int i=0;i<k;i++)f[i].setVisible(false);
					//Fish.backgroundUpdate();
					//for(int i=0;i<k;i++)f[i].setVisible(true);
				}
			}catch(Exception e){}
		}
	}

	public static void main(String args[])
	{
		FishRun fr = new FishRun();
	}
}