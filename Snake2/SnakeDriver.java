import javax.swing.*;
class SnakeDriver
{

	SnakeDriver()
	{
		int sizeX = 500  ;
		int sizeY = 500;

		Board board = new Board(sizeX, sizeY, 3);
		BoardWindow bw= new BoardWindow(board, sizeX, sizeY);
		//inputSnakes();

        //bw.setVisible(true);

        for(;;)
        {
			if(!bw.getPaused())
			{
            	board.update();
            	bw.update();
			}
            try
            {
                Thread.sleep(bw.speed);
            }catch(Exception e){}
        }

	}

//	void inputSnakes()
//	{
//		JFrame f = new JFrame();
//		f.setBounds(100,200,100,500);
//		f.setVisible(true);
//	}

	public static void main(String args[])
	{
		SnakeDriver sd = new SnakeDriver();
	}

}