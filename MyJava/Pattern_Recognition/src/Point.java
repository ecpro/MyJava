/*************************************************************************
 * Name: Piyush Ravi	
 * Email: mails4ipiyush@gmail.com
 *
 * Compilation:  javac Point.java
 * Execution:
 * Dependencies: StdDraw.java
 *
 * Description: An immutable data type for points in the plane.
 *
 *************************************************************************/

import java.util.Comparator;

public class Point implements Comparable<Point> {

    // compare points by slope
    
	public final Comparator<Point> SLOPE_ORDER;       // YOUR DEFINITION HERE

    private final int x;                              // x coordinate
    private final int y;                              // y coordinate

    // create the point (x, y)
    
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
        
        SLOPE_ORDER=new PointComparator();
    }
    
    private class PointComparator implements Comparator<Point>
    {

		@Override
		public int compare(Point arg0, Point arg1)
		{
			double slope0=slopeTo(arg0);
			double slope1=slopeTo(arg1);
			
			if(slope0==slope1) return 0;
			else if(slope0<slope1) return -1;
			else return 1;
		}
    	
    }

    // plot this point to standard drawing
    
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    // draw line between this point and that point to standard drawing
    
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    // slope between this point and that point
    
    /**
     * @param that
     * @return
     */
    public double slopeTo(Point that) 
    {
    	if(that==null) throw new NullPointerException();
    	if(that.x==this.x && this.y==that.y) return Double.NEGATIVE_INFINITY;
    	else if(that.x-this.x==0) return Double.POSITIVE_INFINITY;
    	else if(that.y-this.y==0) return 0;
    	else return (that.y-this.y)/(double)(that.x-this.x);
    }

    // is this point lexicographically smaller than that one?
    // comparing y-coordinates and breaking ties by x-coordinates
    
    public int compareTo(Point that)
    {
    	if(this.x==that.x && this.y==that.y) return 0;
		else if( this.y>that.y || (this.y==that.y && this.x>that.x) )return 1;
		else return -1;
    }

    // return string representation of this point
    
    public String toString() {
        /* DO NOT MODIFY */
    
    	return "(" + x + ", " + y + ")";
    }

    // unit test
    public static void main(String[] args) {
        /* YOUR CODE HERE */
    }
}