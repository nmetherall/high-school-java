/*
Nicholas Metherall
10/1/07
10/1/07
2.29
*/
import java.util.*;
class P2_29
{
	P2_29()
	{
		Scanner s = new Scanner(System.in);
		while(true)
		{
			char c = s.nextLine().charAt(0);
			System.out.printf("The character %c has the value %d\n", c,((int )c));
		}
	}//end of 2_29()

	public static void main(String args[])
	{
		P2_29 p = new P2_29();
	}//end of main
}//end of 2_29