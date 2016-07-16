
public class OneDEquivalent {
	private int size;
	
	public OneDEquivalent(int n)
	{
		size=n;
	}
	public void oneDeqivalent(int i,int j)
	{
		int oneDEq=(i*size)-((size-j)+1); // converting 2 D matrix into 1 D matrix 
		System.out.println("("+i+" , "+j+")"+" = "+oneDEq);
	}
	
	public static void main(String args [])
	{
		OneDEquivalent ode=new OneDEquivalent(4);
		
		ode.oneDeqivalent(4, 3);
		ode.oneDeqivalent(2, 2);
		ode.oneDeqivalent(1, 1);
		
		
		
	}
}
