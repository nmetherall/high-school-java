import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.lang.*;
import java.awt.event.*;

class SnowV5 implements MouseListener
{
	double force[][];
	int weight[][];
	boolean pos[][];
	boolean prev[][];
	BufferedImage back;
	JFrame frame;
	int forced;
	int BLACK = Color.BLACK.getRGB();
	int WHITE = Color.WHITE.getRGB();
	int RED = Color.RED.getRGB();
	int BLUE = Color.BLUE.getRGB();
	int GRAY = Color.GRAY.getRGB();
	int GREEN = Color.GREEN.getRGB();
	int PINK = Color.PINK.getRGB();
	int CYAN = Color.CYAN.getRGB();
	int ORANGE = Color.ORANGE.getRGB();
	int YELLOW = Color.YELLOW.getRGB();
	int i,j,e;
	Graphics g;
	JComboBox col;
	JTextField w;
	double w2;
	JTextArea w3;
	JTextField f;
	double f2;
	JTextArea f3;
	JTextField sizeW;
	int sizeW2;
	JTextField sizeH;
	int sizeH2;
	JTextArea sizeH3;
//	double sizeW2;
	JTextArea sizeW3;
	JTextArea col2;
	String colo[];
	int x;
	int y;
	Rectangle bounds;
	int partCol[][];
	int bGColor;
	int c2;
	Checkbox erase;

	SnowV5()
	{
		//JFrame.setDefaultLookAndFeelDecorated(true);

		colo = new String[10];
		colo[0] = "BLACK";
		colo[1] = "GRAY";
		colo[2] = "BLUE";
		colo[3] = "RED";
		colo[4] = "GREEN";
		colo[5] = "CYAN";
		colo[6] = "ORANGE";
		colo[7] = "WHITE";
		colo[8] = "PINK";
		colo[9] = "YELLOW";

		frame = new JFrame();
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(0,0,700,520);
		frame.setBackground(Color.WHITE);
		frame.setVisible(true);
		back = new BufferedImage(500,500,BufferedImage.TYPE_INT_ARGB);
		force = new double[500][500];
		pos = new boolean[500][500];
		prev = new boolean[500][500];
		weight = new int[500][500];
		partCol = new int[500][500];

		w = new JTextField("250");
		w.setBounds(25,100,70,20);
		frame.add(w);
		w.setVisible(true);
		w3 = new JTextArea("Weight");
		w3.setBounds(20,80,150,20);
		frame.add(w3);
		w3.setVisible(true);

		f = new JTextField("0");
		f.setBounds(25,150,70,20);
		frame.add(f);
		f.setVisible(true);
		f3 = new JTextArea("Force");
		f3.setBounds(20,130,150,20);
		frame.add(f3);
		f3.setVisible(true);

		sizeH = new JTextField("20");
		sizeH.setBounds(25,200,70,20);
		frame.add(sizeH);
		sizeH.setVisible(true);
		sizeH3 = new JTextArea("# particale height");
		sizeH3.setBounds(20,180,150,20);
		frame.add(sizeH3);
		sizeH3.setVisible(true);

		sizeW = new JTextField("30");
		sizeW.setBounds(25,250,70,20);
		frame.add(sizeW);
		sizeW.setVisible(true);
		sizeW3 = new JTextArea("# particale width");
		sizeW3.setBounds(20,230,150,20);
		frame.add(sizeW3);
		sizeW3.setVisible(true);

		col = new JComboBox(colo);
		col.setBounds(25,300,70,20);
		frame.add(col);
		col.setVisible(true);
		col2 = new JTextArea("Color");
		col2.setBounds(20,280,150,20);
		frame.add(col2);
		col2.setVisible(true);

		erase = new Checkbox("Erase", false);
		erase.setBounds(20,420,150,20);
		frame.add(erase);


		//g.drawImage(back,200,0,null);
		doubleBuffer();

		frame.setVisible(true);
		frame.addMouseListener(this);
		frame.repaint();
		run();
	}//end of Snow()

	void run()
	{
		//enitialize();
		for(;;)
		{
			doubleBuffer();
		}

	}

	void doubleBuffer()
	{
		//for(int i=499;i>0;i--)
			//for(int j=499;j>0;j--)
		for(i=1;i<499;i++)
			for(j=499;j>0;j--)
			{
				if(pos[i][j]==false)
					back.setRGB(i,j,WHITE);
				if(pos[i][j] == true)
				{
					//pos[i][j] = false;
					move();
					forced = (int)force[i][j];
					back.setRGB(i,j,partCol[i][j]);
					if(j >= 499 || j<=1 || j+forced > 499 || j+forced<=1)
					{
						change(0,0);
					}
					else
					{
						//if(forced!=0)
						{
							if(pos[i][j+forced]==true)
							{
								double h = Math.random();
								if(h<0.5)
								{
									//System.out.println(h);
									if(pos[i+1][j]==true || i+1>=499)
									{
										if(pos[i-1][j]==true || i-1<=0 )
										{
											change(0,0);
										}
										else
										{
											change(-1,0);
										}
									}
									else
									{
										change(1,0);
									}
								}//if(math.random()>0.5)
								else
								{
									//System.out.println(h);
									if(pos[i-1][j]==true || i-1<=0 )
									{
										if(pos[i+1][j]==true || i+1>=499)
										{
											change(0,0);
										}
										else
										{
											change(1,0);
										}
									}//if(pos[i-1][j]==true || i-1==0 )
									else
									{
										change(-1,0);
									}
								}//else
							}//if(pos[i][j+1]==true)
							else
							{
								change(0,forced);
							}
						}//if(force[i][j]>0 && force[i][j]!=0)
						//else
						//{
						//	change(0,0);
						//}
					}//else
				}//if(pos[i][j] == true)
			}//end of for(i/j)
		g = frame.getGraphics();
		g.drawImage(back,200,20,null);
		//frame.setVisible(true);

	}//end of doubleBuffer()

	void enitialize()
	{
		for(i=100;i<400;i = i+5)
		{
			for(j=1;j<100;j = j+5)
			{
				pos[i][j] = true;
				prev[i][j] = false;
				weight[i][j] = 250;
				force[i][j] = 0.9;
				j++;
			}
		}
	}

	public void mouseReleased(MouseEvent evt)
	{
		bounds = frame.getBounds();

		//System.out.println("hi");
		//System.out.println(x+"\t"+y);
		String c = (String)(col.getSelectedItem());

		if(c == "BLACK")c2 = BLACK;
		if(c == "GRAY")c2 = GRAY;
		if(c == "BLUE")c2 = BLUE;
		if(c == "RED")c2 = RED;
		if(c == "GREEN")c2 = GREEN;
		if(c == "CYAN")c2 = CYAN;
		if(c == "ORANGE")c2 = ORANGE;
		if(c == "WHITE")c2 = WHITE;
		if(c == "PINK")c2 = PINK;
		if(c == "YELLOW")c2 = YELLOW;

		int a = Integer.parseInt(f.getText());
		int b = Integer.parseInt(w.getText());
		sizeW2 = Integer.parseInt(sizeW.getText());
		sizeH2 = Integer.parseInt(sizeH.getText());
		x = evt.getX()-200-(sizeW2/2);
		y = evt.getY()-(sizeH2/2);

		if(erase.getState() == true)
		{
			for(int p = x; p<x+sizeW2;p++)
				for(int q = y; q<y+sizeH2;q++)
				{
					if(p>499||p<1||q>499||q<1);
					else
					{
						pos[p][q] = false;
					}
				}
		}
		else
		{

			for(int p = x; p<x+sizeW2;p++)
				for(int q = y; q<y+sizeH2;q++)
				{
					//if(p>499||p<1||q>499||q<1);
					//System.out.println("hi2");
					//else
					{
						//System.out.println("hi3");
						pos[p][q] = true;
						prev[p][q] = false;
						force[p][q] = a;
						weight[p][q] = b;
						partCol[p][q] = c2;
					}
				}

		}
	}


	void move()
	{
		if(j == weight[i][j]) force[i][j] +=0;
		else
		{
			if(j<weight[i][j])
			{
				force[i][j]-=((j/weight[i][j])-0.3)+(force[i][j]/100);
				e = -1;
			}
			if(j>weight[i][j])
			{
				force[i][j]+=((weight[i][j]/j)-0.3)-(force[i][j]/100);
				e= 1;
			}

		}
	}//end of move()

	void change(int m, int n)
	{
		//for(int k = 0; Math.abs(k)<=Math.abs(m); k+=e)
		{
			//System.out.print("hello");
			//if(prev[i][j]=true){m=0;n=0;}
			//if(/*pos[i+n][j+m] != true &&*/ prev[i][j]!=true || Math.abs(m)+Math.abs(n)==0)
			{
				System.out.println(m+" "+n);
				pos[i][j]=false;
				pos[i+m][j+n] = true;
				prev[i][j] = false;
				if(m!=0&&n!=0)prev[i+m][j+n] = true;
				force[i+m][j+n] = force[i][j];
				weight[i+m][j+n] = weight[i][j];
				partCol[i+m][j+n] = partCol[i][j];
				//k= Math.absabs
			}
		}
	}

	public static void main(String args[])
	{
		SnowV5 s = new SnowV5();
	}//end of main

	 public void mouseExited( MouseEvent evt2 ) {}
	 public void mouseEntered( MouseEvent evt2 ) {}
	 public void mousePressed( MouseEvent evt2 ) {}
	 public void mouseClicked( MouseEvent evt2 ) {}
}//end SnowV2


