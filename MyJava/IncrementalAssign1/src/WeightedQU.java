

public class WeightedQU {
	int id[];
	int weight[];
	
	public WeightedQU(int N)
	{
		id=new int[N];
		weight=new int[N];
		
		for(int i=0;i<N;i++)
		{
			id[i]=i;
			weight[i]=1;
		}
	}
	
	private int root(int p)
	{
		while(p!=id[p]) p=id[p];
		return p;
	}
	public boolean isConnected(int p,int q)
	{	
		return root(p)==root(q);
	}
	
	public void union(int p,int q)
	{
		int rootp=root(p);
		int rootq=root(q);
		
		if(weight[rootp]<weight[rootq])
		{
			id[rootp]=rootq;
			weight[rootq]=weight[rootp]+weight[rootq];
		}
		else
		{
			id[rootq]=rootp;
			weight[rootp]=weight[rootp]+weight[rootq];
		}
	}
	
	public void printId()
	{
		System.out.println("\n\n");
		for(int i=0;i<id.length;i++)
		{
			System.out.print(i+"\t");
		}
	
		System.out.println("\n-------------------------------------------------------------------\n root: ");
		for(int i=0;i<id.length;i++)
		{
			System.out.print(id[i]+"\t");
		}
		
		System.out.println("\n-------------------------------------------------------------------\n weight: ");
		for(int i=0;i<id.length;i++)
		{
			System.out.print(weight[i]+"\t");
		}
	}
	
	public static void main(String [] args)
	{
		WeightedQU quuf=new WeightedQU(10);
		
		//System.out.println(quuf.isConnected(3, 1));
		
		quuf.union(0, 8);
		quuf.union(7, 5);
		quuf.union(0, 1);
		quuf.union(4, 6);
		quuf.union(1, 3);
		quuf.union(6, 7);
		quuf.union(9, 3);
		quuf.union(0, 2);
		quuf.union(5, 1);
		
		System.out.println(quuf.isConnected(0, 5) + " " + quuf.isConnected(0, 3) +" "+quuf.isConnected(4, 5)+" "+quuf.isConnected(6, 2));
		
		quuf.printId();
	}

}
