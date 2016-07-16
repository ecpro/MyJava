

public class Brute {

	private static Point max(Point p,Point q,Point r ,Point s)
	{
		Point max=null;
		
		if(p.compareTo(q)<0) max=q;
		else max=p;
		
		Point max2=null;
		
		if(r.compareTo(s)<0) max2=s;
		else max2=r;
		
		if(max.compareTo(max2)>0)return max;
		else return max2;
	}
	
	private static Point min(Point p,Point q,Point r ,Point s)
	{
		Point min=null;
		
		if(p.compareTo(q)<0) min=p;
		else min=q;
		
		Point min2=null;
		
		if(r.compareTo(s)<0) min2=r;
		else min2=s;
		
		if(min.compareTo(min2)>0)return min2;
		else return min;
	}
	
	public static void main(String[] args) 
	{
	
		In input=new In(args[0]);
		
		StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
		
		int pointCount=input.readInt();
		Point points []=new Point[pointCount];
		for(int i=0;i<pointCount;i++)
		{
			points[i]=new Point(input.readInt(),input.readInt());
			points[i].draw();
		}
		
		for(int i=0;i<points.length;i++)
		{
			for(int j=i+1;j<points.length;j++)
			{
				for(int k=j+1;k<points.length;k++)
				{
					
					if(points[i].slopeTo(points[j])==points[j].slopeTo(points[k]))
							{
								for(int l=k+1;l<points.length;l++)
								{
									if(points[j].slopeTo(points[k])==points[k].slopeTo(points[l]))
										{
										System.out.println(points[i]+" -> "+points[j]+" -> "+points[k]+" -> "+points[l]);
										Point max=max(points[i], points[j], points[k], points[l]);
										Point min=min(points[i], points[j], points[k], points[l]);
										max.drawTo(min);
										}
										
								}
							}
				}
			}
		}
		
	}

}
