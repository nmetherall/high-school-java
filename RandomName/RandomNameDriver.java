import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;
import jxl.*;

public class RandomNameDriver extends JFrame implements ActionListener
{
	//private final static long serialVersionUID = 12345678;
	// member variables
	JButton quit, file, next, delete;
	BufferedReader rdMaster;
	String sheetName;
	boolean draw = false;
	ArrayList <NameRecord>names; JCheckBox boxes[];
	boolean selected[];
	int boxsize;
	NameRecord nr;
	StringTokenizer tokens;
	boolean process;
		/**
		constructor - initialize member variables ( instance variables)
	*/
	public RandomNameDriver()
	{
		process=false;
		ItemHandler ih = new ItemHandler();
		names = new ArrayList<NameRecord>();
		sheetName="";
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(10,10);
		setTitle("Random Name Generator");
		setSize(450,700); // width, height
//		getContentPane().setBackground(Color.blue);
		getContentPane().setLayout(null);// manually set layout

		quit = new JButton("Quit");
		quit.setForeground(Color.red);
		quit.setBounds(5,10,60,20);
		getContentPane().add(quit);
		quit.addActionListener(this);

		file = new JButton("New File");
		file.setForeground(Color.black);
		file.setBounds(75,10,100,20);
		getContentPane().add(file);
		file.addActionListener(this);

		delete = new JButton("Delete Checked");
		delete.setForeground(Color.red);
		delete.setBounds(185,10,130,20);
		getContentPane().add(delete);
		delete.addActionListener(ih);

		setVisible(true);

	}// end of constructor

	public void actionPerformed (ActionEvent evt)
	{
		if(evt.getActionCommand().equals("Quit"))
		{
			System.out.println("Event caught \"Quit\"");
			int choice = JOptionPane.showConfirmDialog( null,
				"You pressed "+evt.getActionCommand()+" are you sure?",
				"Close Frame",JOptionPane.YES_NO_OPTION);
			if ( choice == 0)
				this.dispose();
		}
		if(evt.getSource()==file)
		{
			    names = new ArrayList<NameRecord>();
				Object[] options = {"Red1","Red2","Red3","Red4","Blk1","Blk2","Blk3","Blk4","Cancel"};
				int fileChoice = JOptionPane.showOptionDialog(null,"Select file ",
						"Random Name Generator",JOptionPane.DEFAULT_OPTION,
						JOptionPane.QUESTION_MESSAGE,null,options,options[0]);
				//setup to open and read each file
				switch(fileChoice)
				{
					case 0: sheetName="D1P1"; break;
					case 1: sheetName="D1P2"; break;
					case 2: sheetName="D1P3"; break;
					case 3: sheetName="D1P4"; break;
					case 4: sheetName="D2P1"; break;
					case 5: sheetName="D2P2"; break;
					case 6: sheetName="D2P3"; break;
					case 7: sheetName="D2P4"; break;
				}
				String fullName="null";
				String computer="null";
				String test="null";
				String pictureNumber="null";
				try
				{
					Workbook workbook = Workbook.getWorkbook(new File("Czapla_Robert_R.xls"));
					Sheet sheet = workbook.getSheet(sheetName);

					Cell read = sheet.getCell(0,0);
					int size = Integer.parseInt(read.getContents());
					size+=5;
					for(int row=5; row<size; row++)
					{
						read = sheet.getCell(4,row);
						fullName = read.getContents();
						if(fullName.length()==0) break;
						read = sheet.getCell(8,row);
						computer = read.getContents();

						read = sheet.getCell(11,row);
						pictureNumber = read.getContents();
						process=true;

						read = sheet.getCell(5,row);
						test = read.getContents();
						if(test.length()!=0)
						{
							if(test.length() != 3)
								process = true;
							else
							if(test.toLowerCase().compareTo("xxx")==0)
								process=false;
						}

						read = sheet.getCell(9,row);
						test = read.getContents();
						if(test.length()!=0) process=false;

						if(process)
						{
							nr = new NameRecord();
							nr.lastName = fullName.substring(0,fullName.indexOf(","));
							nr.firstName = fullName.substring(fullName.indexOf(",")+2,fullName.length());
							if(computer.length()==0)
								computer="00";
							nr.computer = computer;
							if(pictureNumber.length()==0)
								pictureNumber="null";
							nr.picture=pictureNumber;
							names.add(nr);
						}
					}


				}
				catch(Exception ioe){}
				draw = true;
				boxsize = names.size();
				boxes = new JCheckBox[boxsize];
				selected= new boolean[boxsize];
				repaint();
		}
	}// end of actionperformed
/**
paint
*/
	public void paint( Graphics g)
	{
		super.paint(g);

		Font f = new Font("Courier",Font.ITALIC + Font.BOLD,200);
    	g.setFont(f);
		if(draw)
		{
			int row = 40;
			int col = 10;
			boolean chg=false;
			for(int i = 0; i < names.size(); i++)
			{
				NameRecord nr = new NameRecord();
				nr = (NameRecord)names.get(i);
				String tempName = nr.lastName+", "+nr.firstName;
				boxes[i] = new JCheckBox(tempName);
				boxes[i].addActionListener(this);
				boxes[i].setBounds(col, row, 200, 20);
				super.add(boxes[i]);
				row += 20;
				int subt = names.size()/2 ;
				if((i>subt -(subt%2)-1) && (!chg))
				{
					row=40;
					col= 210;
					chg = true;
				}
			}
			super.paint(g);
			draw = false;
		}//end of if(draw)
	}// end of paint()

	/**
	main method begins execution of Java Application
	*/
	public static void main(String args[])
	{
//		int choice=1;
//		do
//		{
//			choice = JOptionPane.showConfirmDialog( null,"Randomize a class period?",
//				"Random Generator",JOptionPane.YES_NO_OPTION);
//			if (choice == 0)
//			{
				RandomNameDriver  in = new RandomNameDriver();
//			}
//		}while(choice ==0);

	}// end of main()


	private class ItemHandler implements ActionListener
	{
		public void actionPerformed ( ActionEvent event)
		{
			int tempSize=boxsize;
			for ( int count = 0; count <boxsize; count++)
			{
				if(boxes[count].isSelected())
				{
					selected[count]=true;
					tempSize--;
				}
			}
			ArrayList <NameRecord>tempNames = new ArrayList<NameRecord>();
			JCheckBox[] tempBoxes = new JCheckBox[tempSize];
			int i=0;
			for( int count =0; count<boxsize; count++)
			{
				if(selected[count]==false)
				{
					tempBoxes[i]=boxes[count];
					tempNames.add(names.get(count));
					i++;
				}
			}
			names=tempNames;
			boxes = tempBoxes;
			boxsize=tempSize;
			dispose();
			try
			{
				RandomName rn = new RandomName(names,sheetName);
			}
			catch(IOException ioe){}
		}
	}
}// end of class RandomNameDriver



