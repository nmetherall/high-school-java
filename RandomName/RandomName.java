import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import java.awt.Image.*;
import java.awt.geom.*;
import java.awt.image.*;
import java.net.*;
import javax.imageio.*;
import javax.swing.ImageIcon.*;

public class RandomName extends JFrame implements ActionListener
{
	private final static long serialVersionUID = 43;
	// member variables
	BufferedWriter wt;
	int reduceSize = 4;
	JLabel pict;

	JButton quit, next, random, allNames, randomize, delayTime, dataFile;
	ImageIcon picture;
	boolean processTime = false;
	boolean processName = false;
	boolean dspAllNames = false;
	boolean clrDspAllNames = false;
	ArrayList <String>randChoice;
	ArrayList <NameRecord>names;
	NameRecord nr;
	String firstName="";
	String lastName="";
	String computer="";
	String pictureNumber="";
	int randSize;
	int numb;
	int indexName;
	String numbStr;
	String prevLastName, prevFirstName,prevComputer;
	int sleepSpeed;
	String folder;
	Image pic;
	JInternalFrame frame[] = new JInternalFrame[8];
	JButton pic4;
	JButton pic6;
	JButton pic8;
	int b;
	int j;
	/**
		constructor - initialize member variables ( instance variables)
	*/
	public RandomName(ArrayList <NameRecord>nme, String fld) throws IOException
	{
		folder = fld;
		sleepSpeed=100;
		names = nme;
		prevLastName="";
		prevFirstName="";
		prevComputer="";
		// setup random file
		randSize = names.size();
		numb=0;
		numbStr="";
		indexName=-1;
		setRandom();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(10,10);
		setTitle("Random Name Generator");
		setSize(1000,800); // width, height
		getContentPane().setBackground(Color.blue);
		getContentPane().setLayout(null);// manually set layout

		quit = new JButton("Quit");
		quit.setForeground(Color.red);
		quit.setBounds(5,10,60,20);
		getContentPane().add(quit);
		quit.addActionListener(this);

		next = new JButton("Next Name");
		next.setForeground(Color.blue);
		next.setBounds(85,10,100,20);
		getContentPane().add(next);
		next.addActionListener(this);

		random = new JButton("Random");
		random.setForeground(Color.blue);
		random.setBounds(205,10,100,20);
		getContentPane().add(random);
		random.addActionListener(this);

		allNames = new JButton("All Names");
		allNames.setForeground(Color.black);
		allNames.setBounds(325,10,100,20);
		getContentPane().add(allNames);
		allNames.addActionListener(this);

		randomize = new JButton("Randomize");
		randomize.setForeground(Color.blue);
		randomize.setBounds(450,10,100,20);
		getContentPane().add(randomize);
		randomize.addActionListener(this);

		dataFile = new JButton("Data File");
		dataFile.setForeground(Color.blue);
		dataFile.setBounds(700,10,100,20);
		getContentPane().add(dataFile);
		dataFile.addActionListener(this);

		delayTime = new JButton("Chg Speed");
		delayTime.setForeground(Color.blue);
		delayTime.setBounds(575,10,100,20);
		getContentPane().add(delayTime);
		delayTime.addActionListener(this);

			pic4 = new JButton("4 names");
			pic4.setForeground(Color.blue);
			pic4.setBounds(105,20,100,20);
			getContentPane().add(pic4);
			pic4.addActionListener(this);

			pic6 = new JButton("6 names");
			pic6.setForeground(Color.black);
			pic6.setBounds(230,20,100,20);
			getContentPane().add(pic6);
			pic6.addActionListener(this);

			pic8 = new JButton("8 names");
			pic8.setForeground(Color.green);
			pic8.setBounds(355,20,100,20);
			getContentPane().add(pic8);
			pic8.addActionListener(this);




		setVisible(true);

	}// end of constructor

	public void actionPerformed (ActionEvent evt)
	{
		if(evt.getActionCommand().equals("Quit"))
		{
			int choice = JOptionPane.showConfirmDialog( null,
				"You pressed "+evt.getActionCommand()+" are you sure?",
				"Close Frame",JOptionPane.YES_NO_OPTION);
			if ( choice == 0)
				this.dispose();
		}else
		if(evt.getSource()==randomize)
		{
			setRandom();
		}else
		if(evt.getSource()==delayTime)
		{
			Object[] options = {".10",".20",".30",".40",".50",".60",".70",".80",".90","1"};
			int speedSleep = JOptionPane.showOptionDialog(null,"Wait Time ",
						"Delay in Seconds",JOptionPane.DEFAULT_OPTION,
						JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
			sleepSpeed = (speedSleep+1)*100;
		}else
		if(evt.getSource()==next)
		{
			processName=true;
			processTime=true;
			clrDspAllNames=true;
			dspAllNames=false;
			processTime=true;
			setName();
		}else
		if(evt.getSource()==random)
		{
			processName=true;
			clrDspAllNames=true;
			dspAllNames=false;
			processTime=true;
			indexName = (int)(Math.random()*randSize);
			indexName--;
			setName();
		}else
		if(evt.getSource()==allNames)
		{
			dspAllNames=true;
			processTime=false;
			processName=false;
			clrDspAllNames = false;
			repaint();
		}
		if(evt.getSource()==dataFile)
		{
			try
			{
				wt = new BufferedWriter(new FileWriter("H:\\My Data Sources\\"+folder+".txt"));
				wt.write("fullName\tperiod\tComputer Number\n");
				wt.flush();
				for(int r=0; r<names.size(); r++)
				{
					NameRecord nr = new NameRecord();
					nr = (NameRecord)names.get(r);
					lastName =nr.lastName;
					firstName=nr.firstName;
					if(nr.computer.length()==0) nr.computer="null";
					wt.write(lastName+", "+firstName+"\t"+folder+"\t"+nr.computer+"\n");
					wt.flush();
				}
				wt.close();
			}catch(IOException ioe){}

			// sort datafile by computer
			Collections.sort(names,new ComputerComparator());
			try
			{
				wt = new BufferedWriter(new FileWriter("H:\\My Data Sources\\"+folder+"Computer.txt"));
				wt.write("fullName\tperiod\tComputer Number\n");
				wt.flush();
				for(int r=0; r<names.size(); r++)
				{
					NameRecord nr = new NameRecord();
					nr = (NameRecord)names.get(r);
					lastName =nr.lastName;
					firstName=nr.firstName;
					if(nr.computer.length()==0) nr.computer="null";
					wt.write(lastName+", "+firstName+"\t"+folder+"\t"+nr.computer+"\n");
					wt.flush();
				}
				wt.close();
			}catch(IOException ioe){}

			JOptionPane.showMessageDialog(null, "Write to H:\\My Data Sourcess\\"+folder+".txt");
		}


		if(evt.getSource()==pic4)
		{
			multiple(4);
		}
		if(evt.getSource()==pic6)
				{
					multiple(6);
		}
		if(evt.getSource()==pic8)
				{
					multiple(8);
		}


	}// end of actionperformed

	void setName()
	{
		indexName++;
		if(indexName==randSize)
		{
			setRandom();
			indexName=0;
		}
		prevLastName = lastName;
		prevFirstName = firstName;
		prevComputer = computer;
		NameRecord nr = new NameRecord();
		numb = Integer.parseInt((String)randChoice.get(indexName));
		nr = (NameRecord)names.get(numb);
		lastName =nr.lastName;
		firstName=nr.firstName;
		computer = nr.computer;
		pictureNumber = nr.picture;
		repaint();
	}// end of setName()

	void multiple(int r)
	{
		b=r;
		for(j=0; j<b; j++)
		{
			frame[j].setBounds((j*127),40,127,708);
			frame[j].getContentPane().setBackground(Color.BLUE);
			frame[j].getContentPane().setLayout(null);
			processName=true;
			clrDspAllNames=true;
			dspAllNames=false;
			processTime=true;
			indexName = (int)(Math.random()*randSize);
			indexName--;
			setName();
			getContentPane().add(frame[j]);
			frame[j].repaint();
		}//end of for loop
	}//end of multiple
/**
paint
*/
	public void paint( Graphics g)
	{
		super.paint(g);
		Font	f = new Font("Courier",Font.ITALIC + Font.BOLD,30);
    	if (((processTime) || (processName)) && (clrDspAllNames))
    	{
			f = new Font("Courier",Font.ITALIC + Font.BOLD,30);
	    	g.setFont(f);
			g.setColor(Color.blue);

			int row = 25;
			int col = 20;
			int rowConstant = 100;
			int index=0;
			for(int i=0; i<randChoice.size(); i++)
			{
				if(i%5==0)
						rowConstant+=10;
				index = Integer.parseInt((String)randChoice.get(i));
				NameRecord nr = new NameRecord();
				nr = (NameRecord)names.get(index);
				if(i>19)
					if(col != 500)
						{col=500; rowConstant = -390;}
				if(i<9)
					g.drawString(" "+(i+1)+"  "+nr.lastName+", "+nr.firstName,col,(row*i+rowConstant));
				else
					g.drawString(""+(i+1)+"  "+nr.lastName+", "+nr.firstName,col,(row*i+rowConstant));
			}
		}
		f = new Font("Courier",Font.ITALIC + Font.BOLD,400/b);
    	g.setFont(f);

		if(processTime)
		{
	    	for(int i=9; i>=0; i--)
	    	{
				g.setColor(new Color((int)(Math.random()*256),(int)(Math.random()*256),(int)(Math.random()*256)));
	    		g.drawString(""+i,600/b,450/b);
	    		try
	    		{
	    			Thread.sleep(sleepSpeed);
				}
				catch(InterruptedException ie){}
	    		g.setColor(Color.blue);
	    		g.drawString(""+i,600/b,450/b);
			}
		}// end of if(processTime)
		if(processName)
		{
			f = new Font("Courier",Font.ITALIC + Font.BOLD,100/b);
	    	g.setFont(f);

			g.setColor(Color.blue);
			g.drawString(prevFirstName,10/b,130/b);
			g.drawString(prevLastName, 50/b,230/b);

			g.setColor(new Color(175,15,60));
			//g.setColor(new Color((int)(Math.random()*256),
			//	(int)(Math.random()*256),(int)(Math.random()*256)));
			g.drawString(firstName,10/b,130/b);
			g.drawString(lastName, 50/b,230/b);

			f = new Font("Courier",Font.ITALIC + Font.BOLD,16/b);
	    	g.setFont(f);
	    	g.setColor(Color.blue);
			g.drawString(randSize+prevComputer, 850/b,50/b);
	    	g.setColor(Color.cyan);
			g.drawString(randSize+computer, 850/b,50/b);

			try
			{
				String pictureName="Pictures\\"+folder+"\\"+folder+"_10"+pictureNumber+".jpg";
				picture = new ImageIcon(getClass().getResource(pictureName));
				pic = picture.getImage();
				pic = pic.getScaledInstance(600/b,400/b,java.awt.Image.SCALE_FAST);

				picture = new ImageIcon(pic);
				picture.paintIcon(this,g,20/b,250/b);
				//picture = javax.swing.ImageIcon.ImageIcon(pic);
				//drawImage(pic,20,20,null);
			}
			catch (NullPointerException npe)
			{
				String pictureName="Pictures\\"+folder+"\\"+folder+"_10"+pictureNumber+".jpg";
				picture = new ImageIcon(getClass().getResource(pictureName));
				pic = picture.getImage();
				pic = pic.getScaledInstance(600/b,400/b,java.awt.Image.SCALE_FAST);

				picture = new ImageIcon(pic);
				picture.paintIcon(this,g,20/b,250/b);
				//picture = javax.swing.ImageIcon.ImageIcon(pic);
				//drawImage(pic,20,20,null);
			}

		}// end of if(processName)

		if(dspAllNames)
		{
			f = new Font("Courier",Font.ITALIC + Font.BOLD,100);
	    	g.setFont(f);
			g.setColor(Color.blue);
			g.drawString(prevFirstName, 10,250);
			g.drawString(prevLastName, 70,400);

			f = new Font("Courier",Font.ITALIC + Font.BOLD,28);
	    	g.setFont(f);
			g.setColor(Color.red);

			int row = 25;
			int col = 20;
			int rowConstant = 110;
			int index=0;
			for(int i=0; i<randChoice.size(); i++)
			{
				if(i%5==0)
						rowConstant+=10;
				index = Integer.parseInt((String)randChoice.get(i));
				NameRecord nr = new NameRecord();
				nr = (NameRecord)names.get(index);
				if(i>19)
					if(col != 500)
						{col=500; rowConstant = -390;}
				String fullName = nr.lastName+", "+nr.firstName;
				if(fullName.length() >18)
					fullName = fullName.substring(0,18);
				if(i<9)
					g.drawString(" "+(i+1)+"  "+padStringL(nr.computer,4)+" "+fullName,col,(row*i+rowConstant));
				else
					g.drawString(""+(i+1)+"  "+padStringL(nr.computer,4)+" "+fullName,col,(row*i+rowConstant));
			}
		}
	/*	if(multiple)
		{
			f = new Font("Courier",Font.ITALIC + Font.BOLD,100);
			g.setFont(f);

			g.setColor(Color.blue);
			g.drawString(prevFirstName,10,130);
			g.drawString(prevLastName, 50,230);

			g.setColor(new Color(175,15,60));
			//g.setColor(new Color((int)(Math.random()*256),
			//	(int)(Math.random()*256),(int)(Math.random()*256)));
			g.drawString(firstName,10,130);
			g.drawString(lastName, 50,230);

			f = new Font("Courier",Font.ITALIC + Font.BOLD,16);
		 	g.setFont(f);
			g.setColor(Color.blue);
			g.drawString(randSize+prevComputer, 850,50);
			g.setColor(Color.cyan);
			g.drawString(randSize+computer, 850,50);

			try
			{
				String pictureName="Pictures\\"+folder+"\\"+folder+"_10"+pictureNumber+".jpg";
				picture = new ImageIcon(getClass().getResource(pictureName));
				pic = picture.getImage();
				pic = pic.getScaledInstance(600,400,java.awt.Image.SCALE_FAST);

				picture = new ImageIcon(pic);
				picture.paintIcon(this,g,20,250);
				//picture = javax.swing.ImageIcon.ImageIcon(pic);
				//drawImage(pic,20,20,null);
			}
			catch (NullPointerException npe)
			{
				String pictureName="Pictures\\"+folder+"\\"+folder+"_10"+pictureNumber+".jpg";
				picture = new ImageIcon(getClass().getResource(pictureName));
				pic = picture.getImage();
				pic = pic.getScaledInstance(600,400,java.awt.Image.SCALE_FAST);

				picture = new ImageIcon(pic);
				picture.paintIcon(this,g,20,250);
				//picture = javax.swing.ImageIcon.ImageIcon(pic);
				//drawImage(pic,20,20,null);
			}*/
	}// end of paint()

	void setRandom()
	{
		boolean cont=true;
		randChoice = new ArrayList<String>();
		numb = (int)(Math.random()*randSize);
		numbStr = ""+numb;
		randChoice.add(numbStr);
		for (int i=0; i<randSize-1; i++)
		{
			do
			{
				cont = false;
				numb = (int)(Math.random()*randSize);
				numbStr = ""+numb;
				for(int c=0; c<randChoice.size(); c++)
				{
					if(numbStr.compareTo((String)randChoice.get(c))==0)
						cont=true;
					if(cont) break;
				}
			}while(cont);
			randChoice.add(numbStr);
		}
	}// end of setRandom()

	public String padStringL(String toPad, int pad)
	{
		char str[] = new char[pad], temp;
		if (toPad.length()>=pad) return toPad;
		for(int count = 0; count < toPad.length(); count++)
		{
			temp = toPad.charAt(count);
			try
			{
				str[count] = temp;
			}
			catch (ArrayIndexOutOfBoundsException exp) {}
		}
		return new String(str).replace('\0',' ');
	}// end of padStringL()

}// end of class RandomName



