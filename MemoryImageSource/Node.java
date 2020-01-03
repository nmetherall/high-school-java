import java.awt.*;
import java.awt.Color.*;

class Node
{

	static Node node[];
	static int color[];
	static int average[];
	static int width, height;
	static int mouseX, mouseY;
	static int max;

	int x, y;//position x, y
	int v;//velocity
	int d;//direction in radians
	int a;//acceleration
	int c;//color

	Node(int widthA, int heightA, int lengthA)
	{
		width = widthA;
		height = heightA;
		max=10;
		node = new Node[lengthA];
		average = new int[width*height];
		color = new int[width*height];

		java.util.Arrays.fill(color,Integer.MAX_VALUE);
	}

	Node(int x1, int y1, int v1, int a1, int c1,Node n, int pos)
	{
		java.util.Arrays.fill(color,-1);
		mouseX = (int)MouseInfo.getPointerInfo().getLocation().getX();
		mouseY = (int)MouseInfo.getPointerInfo().getLocation().getY();
		x=x1;
		y=y1;
		v=v1;
		a=a1;
		c=c1;
		d = (int)Math.atan2((mouseY-y)/2,(mouseX-x)/2);
		n.node[pos] = this;
	}

	static public Node mouseChange(Node n)
	{
		//java.util.Arrays.fill(n.color,-1);
		mouseX = (int)MouseInfo.getPointerInfo().getLocation().getX();
		mouseY = (int)MouseInfo.getPointerInfo().getLocation().getY();

		for(int i=0; i<n.node.length; i++)
		{

			int x0=n.node[i].x;
			int y0=n.node[i].y;

			n.node[i].d = (int)Math.atan2((mouseY-n.node[i].y)/2,(mouseX-n.node[i].x)/2);

			int vx=n.node[i].v*(int)Math.cos(n.node[i].d);
			int vy=n.node[i].v*(int)Math.sin(n.node[i].d);

			n.node[i].y += vy;
			n.node[i].x += vx;

			n.node[i].v = (int)(n.node[i].v+(Math.sqrt(2*n.node[i].a*(n.node[i].x-x0))));
			n.color[n.node[i].x+(n.node[i].y*n.width)] += n.node[i].c;
			if(i==5)
			System.out.println(""+n.node[i].x+"  "+n.node[i].y);
			//n.average[n.node[i].x*n.node[i].y] +=1;
			if(n.node[i].v>10)n.node[i].v=10;
		}

		//for(int i=0; i<n.width*n.height;i++)
		//{
		//	if(n.average[i]!=0)n.color[i]=n.color[i]/n.average[i];
		//}
		return n;
	}

	/*static public Node change(double zc, int yc, Node n)
	{
		return n;
	}*/
}