import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.lang.*;
import java.awt.event.*;

class SnowV3 implements MouseListener
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
	int i,j;
	Graphics g;
	JComboBox col;
	JTextField w;
	double w2;
	JTextArea w3;
	JTextField f;
	double f2;
	JTextArea f3;
	JTextField sizeW;
	double sizeW2;
	JTextField sizeH;
	double sizeH2;
	JTextArea sizeH3;
//	double sizeW2;
	JTextArea sizeW3;
	JTextArea col2;
	String colo[];
	int x;
	int y;
	Rectangle bounds;

	SnowV3()
	{
		//JFrame.setDefaultLookAndFeelDecorated(true);

		colo = new String[10];
		colo[0] = "Black";
		colo[1] = "Grey";
		colo[2] = "Blue";
		colo[3] = "Red";
		colo[4] = "Green";
		colo[5] = "Purple";
		colo[6] = "Orange";
		colo[7] = "White";
		colo[8] = "Pink";
		colo[9] = "Yellow";

		frame = new JFrame();
		frame.setLayout(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(0,0,700,500);
		frame.setBackground(Color.WHITE);
		frame.setVisible(true);
		back = new BufferedImage(500,500,BufferedImage.TYPE_INT_ARGB);
		force = new double[500][500];
		pos = new boolean[500][500];
		prev = new boolean[500][500];
		weight = new int[500][500];

		w = new JTextField("250");
		w.setBounds(25,100,70,20);
		frame.add(w);
		w.setVisible(true);
		w3 = new JTextArea("Weight");
		w3.setBounds(20,80,150,20);
		frame.add(w3);
		w3.setVisible(true);

		f = new JTextField("1");
		f.setBounds(25,150,70,20);
		frame.add(f);
		f.setVisible(true);
		f3 = new JTextArea("Force");
		f3.setBounds(20,130,150,20);
		frame.add(f3);
		f3.setVisible(true);

		sizeH = new JTextField("50");
		sizeH.setBounds(25,200,70,20);
		frame.add(sizeH);
		sizeH.setVisible(true);
		sizeH3 = new JTextArea("# particale height");
		sizeH3.setBounds(20,180,150,20);
		frame.add(sizeH3);
		sizeH3.setVisible(true);

		sizeW = new JTextField("300");
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
					pos[i][j] = false;
					move();
					forced = (int)force[i][j];
					back.setRGB(i,j,BLACK);
					if(j >= 499 || j<=1 || j+forced > 499 || j+forced<=1)
					{
						pos[i][j]=true;
						prev[i][j] = false;
					}
					else
					{
						if(force[i][j]!=0)
						{
							if(pos[i][j+forced]==true)
							{
								if(Math.random()>0.5)
								{
									if(pos[i+1][j+forced]==true || i+1>=497)
									{
										if(pos[i-1][j+forced]==true || i-1==0 )
										{
											pos[i][j]=true;
											prev[i][j] = false;
										}
										else
										{
											if(prev[i][j] == true)
											{
												pos[i][j] = true;
												prev[i][j] = false;
											}
											else
											{
												pos[i-1][j+forced]=true;
												prev[i][j] = false;
												prev[i-1][j+forced] = true;
												force[i-1][j+forced] = force[i][j];
												weight[i-1][j+forced] = weight[i][j];
												//weight[i][j]=0;
												//force[i][j] = 0;
											}
										}
									}
									else
									{
										if(prev[i][j] == true)
										{
											pos[i][j] = true;
											prev[i][j] = false;
										}
										else
										{

											pos[i+1][j+forced]=true;
											prev[i][j] = false;
											prev[i+1][j+forced] = true;
											force[i+1][j+forced] = force[i][j];
											weight[i+1][j+forced] = weight[i][j];
											//weight[i][j]=0;
											//force[i][j] = 0;
										}
									}
								}//if(math.random()>0.5)
								else
								{
									if(pos[i-1][j+forced]==true || i-1==0 )
									{
										if(pos[i+1][j+forced]==true || i+1>=497)
										{
											pos[i][j]=true;
											prev[i][j] = false;
										}
										else
										{
											if(prev[i][j] == true)
											{
												pos[i][j] = true;
												prev[i][j] = false;
											}
											else
											{
												pos[i+1][j+forced] = true;
												prev[i][j] = false;
												prev[i+1][j+forced] = true;
												force[i+1][j+forced] = force[i][j];
												weight[i+1][j+forced] = weight[i][j];
												//weight[i][j]=0;
												//force[i][j]=0;
											}
										}
									}//if(pos[i-1][j]==true || i-1==0 )
									else
									{
										if(prev[i][j] == true)
										{
											pos[i][j] = true;
											prev[i][j] = false;
										}
										else
										{
											pos[i-1][j+forced]=true;
											prev[i][j] = false;
											prev[i-1][j+forced] = true;
											force[i-1][j+forced] = force[i][j];
											weight[i-1][j+forced] = weight[i][j];
											//weight[i][j]=0;
											//force[i][j] = 0;
										}
									}
								}//else
							}//if(pos[i][j+1]==true)
							else
							{
								if(forced<0 && prev[i][j] == true)
								{
									pos[i][j] = true;
									prev[i][j] = false;
								}
								else
								{
									pos[i][j+forced] = true;
									prev[i][j] = false;
									prev[i][j+forced] = true;
									force[i][j+forced] = force[i][j];
									weight[i][j+forced] = weight[i][j];
									//weight[i][j]=0;
									//force[i][j] = 0;
								}
							}
						}//if(force[i][j]>0 && force[i][j]!=0)
						else
						{
							pos[i][j]=true;
							prev[i][j] = false;
						}
					}//else
				}//if(pos[i][j] == true)
			}//end of for(i/j)
		g = frame.getGraphics();
		g.drawImage(back,200,0,null);
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
		x = evt.getX()-200;
		y = evt.getY();
		//System.out.println("hi");
		//System.out.println(x+"\t"+y);

		int a = Integer.parseInt(f.getText());
		int b = Integer.parseInt(w.getText());
		sizeW2 = Integer.parseInt(sizeW.getText());
		sizeH2 = Integer.parseInt(sizeH.getText());

		for(int p = x; p<x+sizeW2;p++)
			for(int q = y; q<y+sizeH2;q++)
			{
				if(p>499||p<1||q>499||q<1);
				//System.out.println("hi2");
				else
				{
					//System.out.println("hi3");
					pos[p][q] = true;
					force[p][q] = a;
					weight[p][q] = b;
				}
			}
		//run();
	}

	void move()
	{
		if(j == weight[i][j]) force[i][j] +=0;
		else
		{
			if(j<weight[i][j])force[i][j]-=((j/weight[i][j])-0.3)+(force[i][j]/100);
			if(j>weight[i][j])force[i][j]+=((weight[i][j]/j)-0.3)-(force[i][j]/100);

		}
	}//end of move()

	public static void main(String args[])
	{
		SnowV3 s = new SnowV3();
	}//end of main

	 public void mouseExited( MouseEvent evt2 ) {}
	 public void mouseEntered( MouseEvent evt2 ) {}
	 public void mousePressed( MouseEvent evt2 ) {}
	 public void mouseClicked( MouseEvent evt2 ) {}
}//end SnowV2



//math.random()



//setRGB(int x, int y, int rgb)

/*if(forced<0 && prev[i][j] == true)
{
	pos[i][j] = true;
	prev[i][j] = false;
}
else
{*/