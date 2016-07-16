
public class PercolationStats {

	private Percolation material;
	private double [] trials;
	public PercolationStats(int N,int T)
	{
		if(N<=0 || T<=0) throw new IllegalArgumentException("N and T should not be less than 1");
		trials=new double[T];
		
		for(int k=0;k<trials.length;k++)
		{
			//Stopwatch watch=new Stopwatch();
			material=new Percolation(N);
			int count=0;
			while(!material.percolates())
			{
				int i=StdRandom.uniform(N)+1;
				int j=StdRandom.uniform(N)+1;
				
				if(!material.isOpen(i, j))
				{
					material.open(i, j);
					count++;
				}
			}
			//System.out.println("time taken for one trial "+ watch.elapsedTime());
			//System.out.println("count = "+count);
			trials[k]=(double)count/Math.pow(N,2);
			//System.out.println("fraction of cells required = "+trials[k]);
			//N=N*2;
		}
		
	}
	
	public double mean()
	{
	
		return StdStats.mean(trials);
	}
	public double stddev()
	{	
		return StdStats.stddev(trials);
	}
	public double confidenceLo()
	{
		double mean=mean();
		double stddev=stddev();
		
		return mean-(1.96*stddev/(Math.sqrt(trials.length)));
		
	}
	public double confidenceHi()
	{
		double mean=mean();
		double stddev=stddev();
		
		return mean+(1.96*stddev/(Math.sqrt(trials.length)));

	}
	public static void main(String[] args) 
	{
		PercolationStats percStats=new PercolationStats(Integer.parseInt(args[0]),Integer.parseInt(args[1]));
		
		System.out.println("mean = " +percStats.mean());
		System.out.println("stddev = "+percStats.stddev());
		System.out.println("95 % confidence interval = "+percStats.confidenceLo()+","+percStats.confidenceHi());
		
		
	}

}
