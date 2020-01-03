import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

public class BoardWindow extends JFrame implements KeyListener
{
    Board b;
    Graphics2D g;
    int sizeX, sizeY;
    int speed = 50;
    ScoreWindow sWindow;
    boolean paused = false;

    BoardWindow(Board b, int sizeX, int sizeY)
    {
        this.b = b;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        setBounds(10,20,sizeX+73,sizeY+25);
        sWindow = new ScoreWindow(b.snakes, 75, sizeY);
        addKeyListener(this);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(sWindow);
        setVisible(true);
        sWindow.setVisible(true);

		setIgnoreRepaint(true);

        createBufferStrategy(2);
    }

    void update()
    {
        g = (Graphics2D)this.getBufferStrategy().getDrawGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 24, sizeX+75, sizeY);
		for(Snake s: b.snakes)
		{
			g.setColor(s.c);
			for(Position p: s.position)
				g.drawRect(p.x*4+75,p.y*4+24,4,4);
		}
        for(Fruit f: b.fruits)
        {
            g.setColor(Color.RED);
            g.fillRect(f.p.x*4+75,f.p.y*4+24,4,4);
		}

    	sWindow.update(g);
    	getBufferStrategy().show();
   	}

    public void keyPressed(KeyEvent evt)
	{
		switch(evt.getKeyCode())
		{
			case KeyEvent.VK_UP:
            	if(b.snakes.get(0).dir!=Snake.DOWN)
                b.snakes.get(0).setDirection(Snake.UP);
                break;
			case KeyEvent.VK_DOWN:
                if(b.snakes.get(0).dir!=Snake.UP)
                b.snakes.get(0).setDirection(Snake.DOWN);
                break;
			case KeyEvent.VK_LEFT:
                if(b.snakes.get(0).dir!=Snake.RIGHT)
                b.snakes.get(0).setDirection(Snake.LEFT);
                break;
			case KeyEvent.VK_RIGHT:
                if(b.snakes.get(0).dir!=Snake.LEFT)
                b.snakes.get(0).setDirection(Snake.RIGHT);
                break;


            case KeyEvent.VK_W:
                if(b.snakes.get(1).dir!=Snake.DOWN)
                b.snakes.get(1).setDirection(Snake.UP);
                break;
			case KeyEvent.VK_S:
                if(b.snakes.get(1).dir!=Snake.UP)
                b.snakes.get(1).setDirection(Snake.DOWN);
                break;
			case KeyEvent.VK_A:
                if(b.snakes.get(1).dir!=Snake.RIGHT)
                b.snakes.get(1).setDirection(Snake.LEFT);
                break;
			case KeyEvent.VK_D:
                if(b.snakes.get(1).dir!=Snake.LEFT)
                b.snakes.get(1).setDirection(Snake.RIGHT);
                break;
        	case KeyEvent.VK_PERIOD:
        		speed-=2;
        		break;
        	case KeyEvent.VK_COMMA:
        		speed+=2;
        		break;

        	case KeyEvent.VK_SPACE:
        		//System.out.println("unpause");
        		if(!paused)
        		{
					g.setColor(Color.RED);
        			g.drawString("Paused" ,280,260);
        			getBufferStrategy().show();
        			sWindow.update(g);
					paused = true;
				}
				else
				{
					paused= false;
				}

				break;

		}
	}
	public void keyReleased(KeyEvent evt){}
	public void keyTyped(KeyEvent evt){}

	public boolean getPaused()
	{
		return paused;
	}
}
