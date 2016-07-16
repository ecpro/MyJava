
public class Subset {

	public static void main(String args[])
	{
		RandomizedQueue<String> rq= new RandomizedQueue<String>();
		int k=Integer.parseInt(args[0]);
		StdOut.print("Enter strings: ");
		while(!StdIn.isEmpty())
		{	
			rq.enqueue(StdIn.readString());
		}
			
		while(k>0)
		{
			StdOut.println(rq.dequeue());
			k--;
		}
	}
}
