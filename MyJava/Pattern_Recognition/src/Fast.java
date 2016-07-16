import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;


public class Fast {

	public static void main(String[] args) {
		In input=new In(args[0]);
		
		StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
		
		int pCount=input.readInt();
		
		Point [] points=new Point[pCount];
		Point [] copy=new Point[pCount];
		
		for(int i=0;i<pCount;i++)
		{
			int x=input.readInt();
			int y=input.readInt();
			points[i]=new Point(x,y);
			copy[i]=new Point(x,y);
			points[i].draw();
		}
		
		
		
		for(int i=0;i<pCount;i++)
		{
			Arrays.sort(copy, points[i].SLOPE_ORDER);
			
			for(int j=2;j<pCount;j++)
			{
				ArrayList<Point> cp =new ArrayList<Point>();
				cp.add(points[i]);
				cp.add(copy[j-1]);
				Double slope=points[i].slopeTo(copy[j-1]);
				
				while(slope==points[i].slopeTo(copy[j]))
				{
					cp.add(copy[j]);
					j++;
				}
				if(cp.size()>3)
				{
					for(int k=0;k<cp.size();k++)
					{
						System.out.print(cp.get(k));
						Collections.max(cp).drawTo(Collections.min(cp));
						if(k!=cp.size()-1)System.out.print(" -> ");
					}
				}
			}
		}
		
//		for(int i=0;i<points.length;i++)
//		{
//			Arrays.sort(points, points[i].SLOPE_ORDER);
//			
//			for(int j=0;j<points.length;j++)
//			{
//				System.out.print(points[j]);
//			}
//			System.out.println("\n");
//			
//		}
	}

}
