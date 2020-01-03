import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

import java.awt.event.*;

import java.awt.*;



import java.io.OutputStreamWriter.*;

class Calan implements ActionListener
{
	JComboBox month;
	JTextField year;
	JLabel su,mond,tu,we,th,f,sa;
	int date,y;
	int day,m;
	int dateV,yV;
	int dayV,mV;
	JButton cal[][], enter;
	JFrame frame;
	BufferedWriter bw;
	GregorianCalendar c,v;
	int k2,f2;

	public Calan()
	{
		String[] names = new String[]{"January","Febuary","March","April","May","June","July","August","September","October","November","December"};

		frame = new JFrame();
		frame.setLayout(null);
		frame.setBounds(0,0,360,400);

		enter = new JButton("enter");
		enter.setBounds(190,10,70,20);
		enter.addActionListener(this);
		frame.add(enter);

		c = new GregorianCalendar();
		date = c.get(c.DATE);
		m = c.get(c.MONTH)+1;
		y = c.get(c.YEAR);
		day = c.get(c.DAY_OF_WEEK);
		//System.out.println(y+" month"+m+" "+date+" "+day);          7
		cal = new JButton[7][6];
		//System.out.print(day);

		year = new JTextField(""+y);
		year.setBounds(10,10,50,20);
		frame.add(year);

		month = new JComboBox(names);
		month.setBounds(80,10,100,20);
		month.setSelectedIndex(m-1);
		frame.add(month);

		su = new JLabel("Su");
		su.setBounds(20,50,20,20);
		frame.add(su);

		mond = new JLabel("M");
		mond.setBounds(70,50,20,20);
		frame.add(mond);

		tu = new JLabel("Tu");
		tu.setBounds(120,50,20,20);
		frame.add(tu);

		we = new JLabel("W");
		we.setBounds(170,50,20,20);
		frame.add(we);

		th = new JLabel("Th");
		th.setBounds(220,50,20,20);
		frame.add(th);

		f = new JLabel("F");
		f.setBounds(270,50,20,20);
		frame.add(f);

		sa = new JLabel("Sa");
		sa.setBounds(320,50,20,20);
		frame.add(sa);


		int f = day-(date/7)-1;
		int k = 1;
		for(int j = 0; j<6; j++)
			for(int i = 0;i<=6;i++)

			{
					cal[i][j] = new JButton("");
					cal[i][j].addActionListener(this);
			}
		//notMonth();
		enter.doClick();
		frame.setVisible(true);
	}

	public void notMonth()
	{

		v = new GregorianCalendar(Integer.parseInt(year.getText()),month.getSelectedIndex(),1);
		dateV = v.get(v.DATE);
		m = v.get(v.MONTH)+1;
		y = v.get(v.YEAR);
		day = v.get(v.DAY_OF_WEEK);

		f2 = day-(dateV/7)-1;
		//System.out.print(f2);
		k2 = 1;
		for(int j = 0; j<6; j++)
			for(int i = 0;i<=6;i++)
			{
				frame.remove(cal[i][j]);
				if(k2-f2>=1&&k2-f2<=v.getActualMaximum(v.DAY_OF_MONTH))
					cal[i][j].setText(""+(k2-f2));
				else
					cal[i][j].setText("");
				if(k2-f2 == date && (c.get(c.MONTH))==(v.get(v.MONTH)) && (c.get(c.YEAR))==(v.get(v.YEAR)))
					cal[i][j].setForeground(Color.RED);
				else
					cal[i][j].setForeground(Color.BLACK);
				cal[i][j].setBounds(50*i,50*j+70,50,50);
				frame.add(cal[i][j]);
				k2++;
			}
	}



	public void actionPerformed(ActionEvent evt)
	{
		if(evt.getSource() == enter)
		{
		//	frame.removeAll();
			frame.setVisible(false);

			notMonth();

			frame.setVisible(true);
		}
		else
		{
			try
			{
				/*String dayOfWeek = "";
				//bw = new BufferedWriter(new FileWriter(""+((JButton)evt.getSource()).getText()+m+y+".txt"));
				switch(day-(Integer.parseInt(((JButton)evt.getSource()).getText())/7))
				{
					case 1: dayOfWeek = "Sunday";break;
					case 2: dayOfWeek = "Monday";break;
					case 3: dayOfWeek = "Tuesday";break;
					case 4: dayOfWeek = "Wednesday";break;
					case 5: dayOfWeek = "Thursday";break;
					case 6: dayOfWeek = "Friday";break;
					case 7: dayOfWeek = "Saturday";break;
				}*/
				System.out.println("You Selected "+m+"/"+((JButton)evt.getSource()).getText()+"/"+y);//+" Which is a "+dayOfWeek);
				//bw.close();
			}catch(Exception ex){}
		}
	}
//Integer.parseInt(((JButton)evt.getSource()).getText())
	public static void main(String args[])
	{
		Calan a = new Calan();
	}
}