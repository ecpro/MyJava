
public class Percolation {
	private int gridSize;  // to specify the edge length of grid
	private int [] state; // whether empty or filled
	private WeightedQuickUnionUF cell; 	//  to connect the cells 
	//QuickFindUF cell;
	public Percolation(int n)
	{
		if (n<=0) throw new IllegalArgumentException("the grid edge cannot be less than 1");
		gridSize=n;
		cell=new WeightedQuickUnionUF(n*n+2);
		//cell=new QuickFindUF(n*n+2);
		state =new int [n*n+2];
		
		for(int i=0;i<n*n;i++)
		{
			state[i]=0;
		}
		state[n*n]=1;
		state[n*n+1]=1;
	}
	
	// Check if cell location with index (i,j) is open or closed
	
	public boolean isOpen(int i,int j)
	{
		if(checkLimits(i,j))
		{
			if(state[location(i,j)]==1) return true;
		}
		else throw new IndexOutOfBoundsException("row or column index out of bound");
		return false;
	}
	
	// formula for 2D array location to 1D array location conversion
	
	private int location(int i,int j)
	{
		return (i*gridSize)-((gridSize-j)+1);  
	}

	// check for valid row and column input
	
	private boolean checkLimits(int i,int j)
	{
		if(i<1|| i>gridSize || j<1 || j>gridSize) return false;
		else return true;
	}
	
	// connect the open cells to its neighbors
	
	private void connect2Neighbour(int i,int j)
	{
		int middle=location(i,j);
		int bottom=location(i+1,j);
		int left=location(i,j-1);
		int right=location(i,j+1);
		int top=location(i-1,j);
		
		if(i==1)  // if in first row
		{
			if(isOpen(i,j)) connect(middle,gridSize*gridSize); // if cell(i,j) open then connect to dummy cell at the top
			if(isOpen(i+1,j)) connect(middle,bottom); // connect to the bottom cells
		}
		else if(i==gridSize) // if in last row
		{
			if(isOpen(i,j)) connect(middle,gridSize*gridSize+1); // if cell(i,j) open then connect to dummy at the bottom
			if(isOpen(i-1,j)) connect(middle,top); // connect to top cells
		}
		else  
		{
			if(isOpen(i+1,j)) connect(middle,bottom);
			if(isOpen(i-1,j)) connect(middle,top);
			
			if(j==1)   // if at the left edge or first column 
			{
				if(isOpen(j,j+1)) connect(middle,right);
			}
			else if(j==gridSize)// if at the right edge or last column 
			{
				if(isOpen(i,j-1)) connect(middle,left);
			}
			else // when the cell is not at any corners
			{
				if(isOpen(i,j+1)) connect(middle,right);
				if(isOpen(i,j-1)) connect(middle,left);
			}
		}
		
	}
// actual call to union-find's connected() and union() methods

private void connect(int i,int j)
{
	if(cell.connected(i, j)) return;
	else
		cell.union(i, j);
}

// open up the cell with row i and column j and call the connect2neighbour() method to connect the newly opened cells to its neighbors
public void open(int i,int j)
{
	if (i<1 || i>gridSize || j<1 || j>gridSize) throw new IndexOutOfBoundsException("row and column should be within 0 and "+gridSize);

	if(isOpen(i, j)) return ;
	else
	{
		state[location(i,j)]=1;
		connect2Neighbour(i, j);
	}
}

// check whether a particular cells is connected to the top or not

public boolean isFull(int i,int j)
{
	if (i<1 || i>gridSize || j>1 || j>gridSize) throw new IndexOutOfBoundsException("row and column should be within 0 and "+gridSize);
	return isOpen(i,j) && cell.connected(location(i,j), gridSize*gridSize);
}

// check if the system percolates

public boolean percolates()
{
	return cell.connected(gridSize*gridSize, gridSize*gridSize+1);
}

// client method 

//public static void main(String args[])
//{
//	Percolation perc=new Percolation(5);
//	int count=0;
//	while(!perc.percolates())
//	{
//		int i=StdRandom.uniform(5)+1;
//		
//		int j=StdRandom.uniform(5)+1;
//
//			if(!perc.isOpen(i, j))
//			{
//				perc.open(i, j);
//				count++;
//			}
//	}
//	
//	System.out.println(perc.percolates());
//	System.out.println("count : "+count+ "\n probabilty : "+(float)count/(5*5));
//
//}
//

}