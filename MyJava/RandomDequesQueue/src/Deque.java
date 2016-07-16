import java.util.Iterator;
import java.util.NoSuchElementException;


public class Deque<Item> implements Iterable<Item>{
	private Node head;
	private Node tail;
	private int length;
	
	private class Node 
	{
		Item key;
		Node next;
		Node prev;
	}
	
	
	private class ListIterator implements Iterator<Item>
	{
		Node current=head;

		@Override
		public boolean hasNext() 
		{
			return current!=null;
		}

		@Override
		public Item next() 
		{
			if(current.key==null) throw new NoSuchElementException();
			Item value=current.key;
			current=current.next;
			
			return value;
		}
		
		@Override
		public void remove()
		{
			throw new UnsupportedOperationException();
		}
		
	}
	
	public Deque()
	{
		head=tail=null;
		length=0;
	}
	public void addFirst(Item key)
	{
		if(key==null) throw new NullPointerException();
		if(head==null)
		{
			head=new Node();
			head.key=key;
			head.next=null;
			head.prev=null;
			tail=head;
		}
		else
		{
			Node temp=new Node();
			temp.key=key;
			temp.next=head;
			temp.prev=null;
			head.prev=temp;
			head=temp;
		}
		length++;
	}
	
	public void addLast(Item item)
	{
		if(item==null) throw new NullPointerException();
		if(head==null)
		{
			head=new Node();
			head.key=item;
			head.next=null;
			head.prev=null;
			tail=head;
		}
		else
		{
			Node temp=new Node();
			temp.key=item;
			temp.next=null;
			temp.prev=tail;
			tail.next=temp;
			tail=temp;
		}
		length++;
	}

	public Item removeFirst()
	{
		if(head==null) throw new NoSuchElementException();
		Item value=head.key;
		if(head.next!=null)
		{
			head=head.next;
			head.prev=null;
		}
		else
			head=tail=null;
		length--;
		return value;
	}
	
	public Item removeLast()
	{
		if(head==null) throw new NoSuchElementException();
		Item value=tail.key;
		if(tail.prev==null)
		{
			tail=head=null;
		}
		else
		{
			tail=tail.prev;
			tail.next=null;
		}
		length--;
		return value;
	}
	
	public int size()
	{
		return length;
	}
	
	public boolean isEmpty()
	{
		return head==null;
	}
	@Override
	public Iterator<Item> iterator() {
		return new ListIterator();
	}
	
	public static void main(String args[])
	{
		Deque<Integer> dq=new Deque<Integer>();
		
		for(int i=0;i<10;i++)
		{
			if((10+i)%2==0)
				dq.addFirst(10+i);
			else
				dq.addLast(10+i);
		}
		
		
		Iterator<Integer> itr=dq.iterator();
		
		while(itr.hasNext())
			System.out.println(itr.next());
		
		for(int i=0;i<5;i++)
		{
			if((10+i)%2==0)
				dq.removeFirst();
			else
				dq.removeLast();
		}
		
		Iterator<Integer> itr2=dq.iterator();
		System.out.println("\n\n\n");
		while(itr2.hasNext())
			System.out.println(itr2.next());
	}

}
