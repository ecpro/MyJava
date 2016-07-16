import java.util.Iterator;
import java.util.NoSuchElementException;


public class RandomizedQueue<Item> implements Iterable<Item> {

	private Item [] queue;
	private int size;
	
	@SuppressWarnings("unchecked")
	public RandomizedQueue()
	{
		queue=(Item []) new Object[1];
		size=0;
	}
	
	public boolean isEmpty()
	{
		return size==0;
	}
	
	public int size()
	{
		return size;
	}
	
	@SuppressWarnings("unchecked")
	public void enqueue(Item item)
	{
		if(item==null) throw new NullPointerException();
		if(queue.length<=size) resize(queue.length*2);
		queue[size]=(Item) new Object();
		queue[size++]=item;
		
	}
	
	public Item dequeue()
	{
		if(isEmpty()) throw new NoSuchElementException();
		int randomIndex=StdRandom.uniform(size);
		Item value=queue[randomIndex];
		if(value==queue[size-1])
		{
			queue[size-1]=null;
		}
		else
		{
			queue[randomIndex]=queue[size-1];
			queue[size-1]=null;
		}
		size--;
		
		if(size==queue.length/4) resize(queue.length/2);
		
		return value;
		
	}
	
	public Item sample()
	{
		if(isEmpty()) throw new NoSuchElementException();
		int randomIndex=StdRandom.uniform(size);
		
		return queue[randomIndex];
	}
	
	@SuppressWarnings("unchecked")
	private void resize(int x)
	{
		Item [] copyOfQueue=(Item []) new Object[x];
		for(int i=0;i<size;i++)
		{
			copyOfQueue[i]=(Item) new Object();
			copyOfQueue[i]=queue[i];
		}
		queue=copyOfQueue;
	}
	
	private class QueueIterator implements Iterator<Item>{
		Item [] copyQueue;
		int current;
		
		@SuppressWarnings("unchecked")
		QueueIterator()
		{
			copyQueue=(Item []) new Object[size];
			current=0;
			for(int i=0;i<size;i++)
			{
				copyQueue[i]=(Item) new Object();
				copyQueue[i]=queue[i];
			}
		
			shuffle(copyQueue);
			
		}
		
		private void shuffle(Item [] array)
		{
			for(int i=0;i<size;i++)
			{
				int j=StdRandom.uniform(i+1);
				exchange(copyQueue,i,j);
			}
		}
		
		private void exchange(Item [] array,int i,int j)
		{
			Item temp=array[i];
			array[i]=array[j];
			array[j]=temp;
		}
		@Override
		public boolean hasNext() {
			return current<size;
		}
		@Override
		public Item next() {
			
			if(current>=size) throw new NoSuchElementException();
			return copyQueue[current++];
		}
		@Override
		public void remove()
		{
			throw new UnsupportedOperationException();
		}
	}
	
	@Override
	public Iterator<Item> iterator() {
		return new QueueIterator();
	}

	public static void main(String args[])
	{
		RandomizedQueue<Integer> rq=new RandomizedQueue<Integer>();
		
		for(int i=0;i<12;i++)
			rq.enqueue(i);
	
		//System.out.println("\n\n\n");
		
		Iterator<Integer> itr=rq.iterator();
		
		while(itr.hasNext())
		{
			System.out.println(itr.next());
		}
		
		System.out.println("\n\n\n");
		
		for(int i=0;i<5;i++)
		{
			System.out.println(rq.dequeue());
		}
		
		System.out.println("\n\n\n");
		
		Iterator<Integer> it2=rq.iterator();
		
		while(it2.hasNext())
			System.out.println(it2.next());
		
		
	}
	
}
