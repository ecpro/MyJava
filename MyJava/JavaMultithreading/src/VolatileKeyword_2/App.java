package VolatileKeyword_2;

/**
 * Volatile Keyword, <em>â€œâ€¦ the volatile modifier guarantees that any thread that
 * reads a field will see the most recently written value.â€�</em> - Josh Bloch
 * <br><br>
 * Codes with minor comments are from
 * <a href="http://www.caveofprogramming.com/youtube/">
 * <em>http://www.caveofprogramming.com/youtube/</em>
 * </a>
 * <br>
 * also freely available at
 * <a href="https://www.udemy.com/java-multithreading/?couponCode=FREE">
 *     <em>https://www.udemy.com/java-multithreading/?couponCode=FREE</em>
 * </a>
 *
 * @author Z.B. Celik <celik.berkay@gmail.com>
 */
import java.util.Scanner;

class Processor extends Thread {
	
	// volatile variable saves value in memory every time its value is changed
	// if a variable is not chosen volatile then its get saved in the cached into processor cache 
	// and other threads, which always values directly from memory may not get the updated value of that variable
	
    private volatile boolean running = true;

    public void run() {
        while (running) {
            System.out.println("Running");

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void shutdown() {
        running = false;
    }
}

public class App {

	
	
	
	
    public static void main(String[] args) throws InterruptedException {
        Processor pro = new Processor();
        pro.start();
        // Wait for the enter key
        System.out.println("Enter something to stop the thread,\nVolatile variable running will be forced to true :");
        new Scanner(System.in).nextLine();
        
        pro.shutdown(); // Here pro.shutdown() is called by the main thread and not the pro thread as it is inside the 
        				// main method and not under the run() method of pro.  
        
        pro.join();  // join holds the main thread there itself. Any statement after join() will only execute after the
        			// threads on which join method is called completes its execution
         			
        // Try putting pro.join() before pro.shutdown(). The pro thread will never complete its execution
        			// as pro.shutdown is required to change the variable running to false to stop pro and shutdown() 
         		 	// method cannot be executed before the completion of pro.join()
    }
}
