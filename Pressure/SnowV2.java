import javax.swing.*;
import java.awt.*;
import java.awt.image.*;
import java.lang.*;

class SnowV2
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

	SnowV2()
	{
		frame = new JFrame();
		frame.setBounds(0,0,500,500);
		frame.setBackground(Color.WHITE);
		frame.setVisible(true);
		back = new BufferedImage(500,500,BufferedImage.TYPE_INT_ARGB);
		force = new double[500][500];
		pos = new boolean[500][500];
		prev = new boolean[500][500];
		weight = new int[500][500];
		enitialize();
		for(;;)
		{
			doubleBuffer();
		}

	}//end of Snow()

	void doubleBuffer()
	{
		//for(int i=499;i>0;i--)
			//for(int j=499;j>0;j--)
		for(i=1;i<500;i++)
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
					if(j >= 497 || j<=1 || j+forced >= 498 || j+forced<=1)
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
		Graphics g = frame.getGraphics();
		g.drawImage(back,0,0,null);

	}//end of doubleBuffer()

	void enitialize()
	{
		//pos[250][10] = true;
		//prev[250][10] = false;
		//weight[250][10] = 250;
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
		SnowV2 s = new SnowV2();
	}//end of main
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