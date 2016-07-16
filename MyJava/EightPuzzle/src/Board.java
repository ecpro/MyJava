import java.util.ArrayList;
import java.util.Iterator;


public class Board {
	
	private  final int [][] blocks;
	
	private final ArrayList<Board> neighbours=new ArrayList<Board>(); 
	
	public Board(int [][]blocks) {
			this.blocks=blocks;
			findNeighbour();
	}
	
	public int dimension() {
		return blocks.length;
	}
	
	public int hamming() {
		int val=1,hDist=0;
		for(int i=0;i<dimension();i++)
		{
			for(int j=0;j<dimension();j++)
			{
				if(val<dimension()*dimension() && val!=blocks[i][j]) {
					hDist++;
				}
				val++;
			}
		}
		return hDist;
	}
	
	
	private class neighbours {
		 
		Board
		
		
	}
	
	public int manhattan() {
		int val=1,hDist=0;
		for(int i=0;i<dimension();i++)
		{
			for(int j=0;j<dimension();j++)
			{
				if(val<dimension()*dimension() && val!=blocks[i][j]) {
					hDist++;
				}
				val++;
			}
		}
		
		return hDist;
	}
	
	private void findNeighbour()
	{
		int x=0,y=0,flag=0;
		for(int i=0;i<dimension();i++) {
			for(int j=0;j<dimension();j++) {
				if(blocks[i][j]==0) {
					x=i ; y=j ; flag=1; break;
				}
			}
			break;
		}
		
		if(x+1<dimension()) {
			int  [][] newblock=new int [dimension()][dimension()];
			for(int i=0;i<newblock.length;i++)
			{
				for(int j=0;j<newblock.length;j++)
				{
					newblock[i][j]=blocks[i][j];
				}
			}
			
			swap(newblock,x,y,x+1,y);
			
			neighbours.add(new Board(newblock));
		}
		if(x-1>-1) {
			int  [][] newblock=new int [dimension()][dimension()];
			for(int i=0;i<newblock.length;i++)
			{
				for(int j=0;j<newblock.length;j++)
				{
					newblock[i][j]=blocks[i][j];
				}
			}
			
			swap(newblock,x,y,x-1,y);
			
			neighbours.add(new Board(newblock));
		}
		
		if(y-1>-1)
		{
			int  [][] newblock=new int [dimension()][dimension()];
			for(int i=0;i<newblock.length;i++)
			{
				for(int j=0;j<newblock.length;j++)
				{
					newblock[i][j]=blocks[i][j];
				}
			}
			
			swap(newblock,x,y,x,y-1);
			
			neighbours.add(new Board(newblock));
		}
		
		if(y+1<dimension())
		{
			int  [][] newblock=new int [dimension()][dimension()];
			for(int i=0;i<newblock.length;i++)
			{
				for(int j=0;j<newblock.length;j++)
				{
					newblock[i][j]=blocks[i][j];
				}
			}
			
			swap(newblock,x,y,x,y+1);
			
			neighbours.add(new Board(newblock));
		}
	}
	
	private void swap(int [][]block ,int x1,int y1,int x2,int y2){
		int temp=block[x1][y1];
		block[x1][y1]=block[x2][y2];
		block[x2][y2]=temp;
	}
	
	public boolean isGoal() {
		
		int val=1;
		for(int i=0;i<dimension();i++)
		{
			for(int j=0;j<dimension();j++)
			{
				//System.out.println("val :"+val+" block val :"+blocks[i][j]);
				if(blocks[i][j]!=val) break;
				val++;
			}
		}
		
		return val==dimension()*dimension();
	}
	
	public Board twin() {
		return null;
	}
	
	public boolean equals(Object y){
		if(y == null) return false;
		if(y==this) return true;
		if(this.getClass()!=y.getClass()) return false;
		Board that=(Board) y;
		for(int i=0;i<dimension();i++)
			{
				for(int j=0;j<dimension();j++)
				{
					if(that.blocks[i][j]!=this.blocks[i][j]) return false;
				}
			}
			
		return true;
	}
	
	public Iterable<Board> neighbors() {
		return new IterateNeigbours();
	}
	
	public String toString() {
		StringBuilder s = new StringBuilder();
        s.append(dimension() + "\n");
        for (int i = 0; i < dimension(); i++) {
            for (int j = 0; j < dimension(); j++) {
                s.append(String.format("%2d ", blocks[i][j]));
            }
            s.append("\n");
        }
        return s.toString();
	}
	
	private class IterateNeigbours implements Iterable<Board> {

		@Override
		public Iterator<Board> iterator() {
			return neighbours.iterator();
		}


	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int [][] matrix=new int[3][3];
		int val=1;
		for(int i=0;i<matrix.length;i++)
		{
			for(int j=0;j<matrix.length;j++)
			{
				if(val==matrix.length*matrix.length) matrix[i][j]=0;
				else matrix[i][j]=val;
				val++;
			}
		}
		
		matrix[0][1]=0;
		matrix[1][1]=2;
		matrix[1][2]=5;
		matrix[2][2]=6;
		
		
		
		Board br=new Board(matrix);
		System.out.println("dimension :"+br.dimension());
		System.out.println("Is goal :"+br.isGoal());
		System.out.println("hamming distance : "+br.hamming());
		System.out.println("is equal: "+br.equals(new Board(matrix)));
		
		System.out.println(br);
		
		Iterator itr=br.neighbors().iterator();
		
		for(int i=0;i<matrix.length;i++)
		{
			for(int j=0;j<matrix.length;j++)
			{
				System.out.print(matrix[i][j]+" ");
			}
			System.out.print("\n");
		}
	}

}
