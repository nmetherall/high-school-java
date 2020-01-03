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

    BoardWindow(Board b, int sizeX, int sizeY)
    {
        this.b = b;
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        setBounds(10,20,sizeX,sizeY+25);
        addKeyListener(this);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        createBufferStrategy(2);
    }

    void update()
    {
        g = (Graphics2D)this.getBufferStrategy().getDrawGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 24, sizeX, sizeY);
		for(Snake s: b.snakes)
		{
			g.setColor(s.c);
			for(Position p: s.position)
				g.drawRect(p.x*4,p.y*4+24,4,4);
		}
        for(Fruit f: b.fruits)
        {
            g.setColor(Color.RED);
            g.fillRect(f.p.x*4,f.p.y*4+24,4,4);
		}
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
		}
	}
	public void keyReleased(KeyEvent evt){}
	public void keyTyped(KeyEvent evt){}
}
