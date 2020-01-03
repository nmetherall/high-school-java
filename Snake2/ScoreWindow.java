import javax.swing.*;
import java.util.*;
import java.awt.Graphics;
import java.awt.*;

class ScoreWindow extends JPanel
{
	ArrayList<Snake> snakes;
	int whidth, height;
	Graphics g;

	ScoreWindow(ArrayList<Snake>  snakes, int whidth, int height)
	{
		setBounds(0,0,whidth, height);
		//setDoubleBuffered(true);
		this.snakes = snakes;
		this.whidth = whidth;
		this.height = height;
		//setIgnoreRepaint(true);
		setDoubleBuffered(false);
	}

	public void update(Graphics g)
	{
		int i = 0;

		g.setColor(Color.GRAY);
		g.fillRect(0, 25, whidth, height);

		g.setColor(Color.BLACK);
		g.drawString("Score:", 10, 50);

		for(Snake s : snakes)
		{
			g.setColor(s.c);
			g.drawString(""+s.score, 20, 80+(30*i));
			i++;
		}
	}

}