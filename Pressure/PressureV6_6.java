import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.lang.*;
import java.awt.event.*;

class PressureV6_6 implements MouseListener,ActionListener
{
	double force[][];
	int weight[][];
	boolean pos[][],pauseC;
	boolean prev[][];
	JButton pause, unPause;
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
	JLabel w3;
	JTextField f;
	double f2;
	JLabel f3;
	JTextField sizeW;
	int sizeW2;
	JTextField sizeH;
	int sizeH2;
	JLabel sizeH3;
//	double sizeW2;
	JLabel sizeW3;
	JLabel col2;
	String colo[];
	int x;
	int y;
	Rectangle bounds;
	int partCol[][];
	int bGColor;
	int c2;
	JRadioButton erase, ran, ranBlock,selectCol;
	boolean erase2,ran2,ranBlock2;
	Thread threadMain;
	ButtonGroup aGroup;

	PressureV6_6()
	{
		aGroup = new ButtonGroup();
		//JFrame.setDefaultLookAndFeelDecorated(true);
		ran2=ranBlock2=erase2=false;
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

		frame = new JFrame("Pressure V6_6");
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
		w.setBounds(25,40,70,20);
		frame.add(w);
		w.setVisible(true);
		w3 = new JLabel("Weight");
		w3.setBounds(10,15,150,20);
		frame.add(w3);
		w3.setVisible(true);

		f = new JTextField("0");
		f.setBounds(25,90,70,20);
		frame.add(f);
		f.setVisible(true);
		f3 = new JLabel("Force");
		f3.setBounds(10,65,150,20);
		frame.add(f3);
		f3.setVisible(true);

		sizeH = new JTextField("20");
		sizeH.setBounds(25,140,70,20);
		frame.add(sizeH);
		sizeH.setVisible(true);
		sizeH3 = new JLabel("# particle height");
		sizeH3.setBounds(10,115,150,20);
		frame.add(sizeH3);
		sizeH3.setVisible(true);

		sizeW = new JTextField("30");
		sizeW.setBounds(25,190,70,20);
		frame.add(sizeW);
		sizeW.setVisible(true);
		sizeW3 = new JLabel("# particle width");
		sizeW3.setBounds(10,165,150,20);
		frame.add(sizeW3);
		sizeW3.setVisible(true);

		col = new JComboBox(colo);
		col.setBounds(25,290,70,20);
		frame.add(col);
		col.setVisible(true);
		col2 = new JLabel("Color");
		col2.setBounds(10,210,150,20);
		frame.add(col2);
		col2.setVisible(true);

		erase = new JRadioButton("Erase", false);
		erase.setBounds(20,455,150,20);
		aGroup.add(erase);
		erase.addActionListener(this);
		frame.add(erase);

		threadMain = Thread.currentThread();

		ran = new JRadioButton("Random Color", false);
		ran.setBounds(20,230,150,20);
		ran.addActionListener(this);
		aGroup.add(ran);
		frame.add(ran);

		unPause = new JButton("unpause");
		unPause.setBounds(20,435,90,20);
		unPause.addActionListener(this);
		frame.add(unPause);
		unPause.setVisible(false);

		pause = new JButton("pause");
		pause.setBounds(20,435,90,20);
		pause.addActionListener(this);
		frame.add(pause);

		pauseC=false;

		ranBlock = new JRadioButton("Random Color Block", false);
		ranBlock.setBounds(20,250,150,20);
		ranBlock.addActionListener(this);
		aGroup.add(ranBlock);
		frame.add(ranBlock);

		selectCol = new JRadioButton("Select Color", true);
		selectCol.setBounds(20,270,150,20);
		selectCol.addActionListener(this);
		aGroup.add(selectCol);
		frame.add(selectCol);

		//g.drawImage(back,200,0,null);
		doubleBuffer();

		frame.setVisible(true);
		frame.addMouseListener(this);
		frame.repaint();
		run();


	}//end of Pressure()

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
		try
		{
			if(pauseC == true)threadMain.sleep(1000000);
			else{}
		}
		catch(InterruptedException e){}
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
									if(pos[i+e][j+1]==true || i+1>=499)
									{
										if(pos[i+e][j-1]==true || i-1<=0 )
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
									if(pos[i+e][j-1]==true || i-1<=0 )
									{
										if(pos[i+e][j+1]==true || i+1>=499)
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

		if(ranBlock2 == true)
		{
			int rand = (int)(Math.random()*9);
			switch(rand)
			{
				case 0: c2 = BLACK;break;
				case 1: c2 = GRAY;break;
				case 2: c2 = BLUE;break;
				case 3: c2 = RED;break;
				case 4: c2 = GREEN;break;
				case 5: c2 = CYAN;break;
				case 6: c2 = ORANGE;break;
				case 7: c2 = WHITE;break;
				case 8: c2 = PINK;break;
				case 9: c2 = YELLOW;break;
			}
		}

		if(erase2 == true)
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
						if(p>499||p<1||q>499||q<1);
						else
						{
							pos[p][q] = true;
							prev[p][q] = false;
							force[p][q] = a;
							weight[p][q] = b;
							if(ran2 == true)
							{
								int rand = (int)(Math.random()*9);
								switch(rand)
								{
									case 0: c2 = BLACK;break;
									case 1: c2 = GRAY;break;
									case 2: c2 = BLUE;break;
									case 3: c2 = RED;break;
									case 4: c2 = GREEN;break;
									case 5: c2 = CYAN;break;
									case 6: c2 = ORANGE;break;
									case 7: c2 = WHITE;break;
									case 8: c2 = PINK;break;
									case 9: c2 = YELLOW;break;
								}
							}
							partCol[p][q] = c2;
						}
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
			}
			if(j>weight[i][j])
			{
				force[i][j]+=((weight[i][j]/j)-0.3)-(force[i][j]/100);
			}
			if((int)force[i][j]!=0)e = (int)(Math.abs(force[i][j])/force[i][j]);
		}
	}//end of move()

	void change(int m, int n)
	{

		//for(int k = 0; Math.abs(k)<=Math.abs(m); k+=e)
		{
			//System.out.print("hello");
			if(prev[i][j]==true){prev[i][j]=false;return;}
			//if(/*pos[i+n][j+m] != true &&*/ prev[i][j]!=true || Math.abs(m)+Math.abs(n)==0)
			else if(n!=0 && m==0  && pos[i][j+n]!=true && j+n>1 && j+n<497 && i+1<497 && i+1>1)
			{
				pos[i][j]=false;
				pos[i][j+n] = true;
				//prev[i][j] = false;
				prev[i][j+n] = true;
				force[i][j+n] = force[i][j];
				weight[i][j+n] = weight[i][j];
				partCol[i][j+n] = partCol[i][j];
				return;
			}
			else if(m!=0 && n==0)
			{
				for(int k = 0; Math.abs(k)<=Math.abs(m) && Math.abs(k)<=10 && j+n>1 && j+n<497 && i+k<497 && i+k>1; k+=e)
				{
					if(pos[i+k+e][j] != true);
					else if(pos[i+k+e][j] == true && j+n>1 && j+n<497 && i+k<497 && i+k>1 && k!=forced)
					{
						//System.out.println(m+" "+n);
						pos[i][j]=false;
						pos[i+k][j] = true;
						//prev[i][j] = false;
						prev[i+k][j] = true;
						force[i+k][j] = 0;
						weight[i+k][j] = weight[i][j];
						partCol[i+k][j] = partCol[i][j];
						force[i+k+e][j] = (1/3)*force[i][j];
						//k= Math.absabs
						return;
					}
					else if(k==forced)
					{
						pos[i][j]=false;
						pos[i+k][j] = true;
						//prev[i][j] = false;
						prev[i+k][j] = true;
						force[i+k][j] = force[i][j];
						weight[i+k][j] = weight[i][j];
						partCol[i+k][j] = partCol[i][j];
						//k= Math.absabs
						return;
					}
				}
			}
		}
	}

	public void actionPerformed(ActionEvent evnt)
	{
		try
		{
			if(evnt.getSource() == pause)
			{
				pauseC =true;
				pause.setVisible(false);
				unPause.setVisible(true);
				return;
			}
			if(evnt.getSource() == unPause)
			{
				threadMain.interrupt();
				pause.setVisible(true);
				unPause.setVisible(false);
				pauseC = false;
				return;
				//System.out.println("h");
			}
			if(evnt.getSource()==erase)
			{
				erase2=true;
				ran2=false;
				ranBlock2=false;
			}
			if(evnt.getSource()== ran)
			{
				ran2=true;
				erase2=false;
				ranBlock2=false;
			}
			if(evnt.getSource()== ranBlock)
			{
				ranBlock2=true;
				erase2=false;
				ran2=false;
			}
			if(evnt.getSource()== selectCol)
			{
				ranBlock2=false;
				erase2=false;
				ran2=false;
			}
		}catch(Exception at){at.printStackTrace();}
	}

	public static void main(String args[])
	{
		PressureV6_6 s = new PressureV6_6();
	}//end of main

	 public void mousePressed( MouseEvent evt2 ) {}
	 public void mouseEntered( MouseEvent evt2 ) {}
	 public void mouseExited( MouseEvent evt2 ) {}
	 public void mouseClicked( MouseEvent evt2 ) {}
}//end PressureV2


