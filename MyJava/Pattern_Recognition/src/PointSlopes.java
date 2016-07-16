import java.util.Arrays;


public class PointSlopes {
	public static void main(String[] args) {
		
		In input=new In(args[0]);
		int pc=input.readInt();
		
		Point [] points=new Point[pc];
		double [] slope =new double[pc];
		
		for(int i=0;i<pc;i++)
		{
			points[i]=new Point(input.readInt(),input.readInt());
		}
	
		Arrays.sort(points ,points[4].SLOPE_ORDER);
		for(int i=0;i<pc;i++)
		{
			slope[i]=points[0].slopeTo(points[i]);
		}
		
		for(int i=0;i<pc;i++)
		{
			System.out.print(points[i]);
			if(i==pc-1) System.out.println("\n");
		}
		
		for(int i=0;i<pc;i++)
		{
			System.out.print("  "+slope[i]+"  ");
		}
	
	}

}
