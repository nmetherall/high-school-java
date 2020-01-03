import java.util.*;
import java.io.*;
import java.awt.*;

public class Reader
{
	ArrayList<String> combined = new ArrayList<String>();
	String dataA;
	String dataB;
	String dataC;

	public Reader()
	{
		compare(readA(),readB(),readC());

		Collections.sort(combined);

		System.out.println(combined);
	}

	public ArrayList<String> readA()
	{
		ArrayList<String> array = new ArrayList<String>();

		Scanner scanner = null;

		try
		{
			File f = new File("Department Weight.txt");
			scanner = new Scanner(f).useDelimiter("\t");
		}
		catch(Exception e){}

		scanner.nextLine();

		while(scanner.hasNextLine())
		{
			try
			{
				scanner.next();
				array.add(scanner.next());
				scanner.next();
				scanner.next();
			}
			catch(NoSuchElementException e){break;}
		}

		return array;
	}

	public ArrayList<String> readB()
	{
		ArrayList<String> array = new ArrayList<String>();

		Scanner scanner = null;

		try
		{
			File f = new File("DepartmentNumbers.txt");
			scanner = new Scanner(f).useDelimiter("\t");
		}
		catch(Exception e){}

		while(scanner.hasNextLine())
		{
			try
			{
				scanner.next();
				array.add(scanner.next());
			}
			catch(NoSuchElementException e){break;}
		}

		return array;
	}

	public ArrayList<String> readC()
	{
		ArrayList<String> array = new ArrayList<String>();

		Scanner scanner = null;

		try
		{
			File f = new File("SLCSD High School  Course Master File.txt");
			scanner = new Scanner(f).useDelimiter("\t");
		}
		catch(Exception e){}

		scanner.nextLine();

		while(scanner.hasNextLine())
		{
			try
			{
				array.add(scanner.next().trim());
				scanner.next();
				scanner.next();
			}
			catch(NoSuchElementException e){break;}
		}

		return array;
	}

	void find()
	{

	}

	void findA()
	{
		int a = Integer.parseInt()
	}
	void findB()
	{}
	void findC()
	{}

	void compare(ArrayList<String>... arraysOfArrays)
	{
		for(ArrayList<String> array : arraysOfArrays)
			for(String a: array)
				if(!combined.contains(a))
					combined.add(a);
	}

	public static void main(String args[])
	{
		Reader r = new Reader();
	}
}