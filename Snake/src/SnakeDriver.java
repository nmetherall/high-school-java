import javax.swing.*;
class SnakeDriver
{
	SnakeDriver()
	{
		int sizeX = 500;
		int sizeY = 500;

		Board board = new Board(sizeX, sizeY, 2);
		BoardWindow bw= new BoardWindow(board, sizeX, sizeY);
		//inputSnakes();

        //bw.setVisible(true);

        for(;;)
        {
            board.update();
            bw.update();
            try
            {
                Thread.sleep(20);
            }catch(Exception e){}
        }

	}

	void inputSnakes()
	{
		JFrame f = new JFrame();
		f.setBounds(100,200,100,500);
		f.setVisible(true);
	}

	public static void main(String args[])
	{
		SnakeDriver sd = new SnakeDriver();
	}

}