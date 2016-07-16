////import java.util.Iterator;
////
////
////public class ListIterator<Item> implements Iterator<Item> {
////
////	private Deque dq;
////	public static void main(String[] args) {
////		// TODO Auto-generated method stub
////
////	}
////
////	@Override
////	public Iterator iterator() {
////		// TODO Auto-generated method stub
////		return null;
////	}
////
////}
//
//public class DequeTest
//{
//	public static void main(String args[])
//	{
//		System.out.println(1<1);
//	
//	}
//}
//
//
//import java.util.Iterator;
//import java.util.NoSuchElementException;
//
//
//public class RandomizedQueue<Item> implements Iterable<Item> {
//
//	private Node head;
//	private Node tail;
//	private int length;
//	private class Node
//	{
//		Item key;
//		Node next;
//	}
//	
//	public RandomizedQueue()
//	{
//		head=null;
//		length=0;
//	}
//	
//	public void enqueue(Item item)
//	{
//		if(item==null) throw new NullPointerException();
//		if(head==null)
//		{
//			head=new Node();
//			head.key=item;
//			head.next=null;
//			tail=head;
//		}
//		else
//		{
//			Node temp=new Node();
//			temp.key=item;
//			temp.next=null;
//			tail.next=temp;
//			tail=temp;
//		}
//		length++;
//	}
//	public Item dequeue()
//	{
//		if(head==null ||length==0) throw new NoSuchElementException();
//		Item value=removeAtloc(StdRandom.uniform(length)+1);
//		return value;
//	}
//	
//	private Item removeAtloc(int x)
//	{
//		Item value=null;
//		if(x==1)
//		{
//			if(length==1)
//			{
//				value=head.key;
//				head=tail=null;
//			}
//			else
//			{
//				value=head.key;
//				head=head.next;
//			}
//		}
//		else
//		{
//			Node current=head;
//			for(int i=1;i<x-1;i++)
//			{
//				current=current.next;
//			}
//			value=current.next.key;
//			if(current.next==tail)
//			{
//				tail=current;
//				tail.next=null;
//			}
//			else
//			{
//				current.next=current.next.next;
//			}
//		}
//		length--;
//		return value;
//	}
//	public Item sample()
//	{
//		if(head==null || length==0) throw new NoSuchElementException();
//		Item value=findItem(StdRandom.uniform(length)+1);
//		
//		return value;
//	}
//	
//	private Item findItem(int x)
//	{
//		Node current=head;
//		for(int i=1;i<x;i++)
//		{
//			current=current.next;
//		}
//		return current.key;
//	}
//	public boolean isEmpty()
//	{
//		return head==null;
//	}
//	public int size()
//	{
//		return length;
//	}
//	@Override
//	public Iterator<Item> iterator() 
//	{
//		return new QueueIterator();
//	}
//	
//	private class QueueIterator implements Iterator<Item>
//	{
//		Node currentNode;
//		
//		public QueueIterator()
//		{
//			currentNode=findNode(StdRandom.uniform(length)+1);
//		}
//		
//		private Node findNode(int x)
//		{
//			Node temp=head;
//			for(int i=1;i<x;i++)
//			{
//				temp=temp.next;
//			}
//			return temp;
//		}
//		
//		@Override
//		public boolean hasNext() 
//		{
//			return currentNode!=null;
//		}
//
//		@Override
//		public Item next() 
//		{
//			if(currentNode.key==null) throw new NoSuchElementException();
//			Item value=currentNode.key;
//			currentNode=currentNode.next;
//			
//			return value;
//		}
//		
//	}
//	
//	public static void main(String args[])
//	{
//		RandomizedQueue<Integer> rq=new RandomizedQueue<Integer>();
//		
//		for(int i=0;i<2;i++)
//		{
//			rq.enqueue(i+10);
//		}
//		
//		Iterator<Integer> itr =rq.iterator();
//		
//		while(itr.hasNext())
//			System.out.println(itr.next());
//		
//		System.out.println("\n\n"+rq.sample());
//		
//		System.out.println("\n\n"+rq.dequeue()+" "+rq.isEmpty() +" "+rq.size());
//		
//		
//		
//	}
//	
//}
