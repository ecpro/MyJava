package LockObjects_4;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Multiple locks to speed up complex multi-threaded code. Define shared
 * objects: list1 and list2 then synchronize these objects. Mainly discussing
 * making the method synchronized or making an object inside the method
 * synchronized, By defining two different locks we say that one thread may
 * execute the stageOne while other executes stageTwo.
 * 
 * @author Z.B. Celik <celik.berkay@gmail.com>
 * 
 * NOTES : How to use multiple lock for different sync block in the same object? 
 * 		   Make instances of the Object class and pass as argument to different synchronized block.
 * 	       This way synchronized block will have intrinsic locks of different objects.
 * 		   So if one thread is having lock on one object to access some sync blocks, other
 * 		   thread can have lock on second object to have access to some other sync block. 
 *         This method cannot be applied to synchronized methods.  
 *         Take a look at below code.
 */
public class Worker implements Runnable {

    private Random random = new Random();

    private final Object lock1 = new Object(); // used to lock sync block of stageOne() method 
    private final Object lock2 = new Object(); // 

    private List<Integer> list1 = new ArrayList<>();
    private List<Integer> list2 = new ArrayList<>();
    
    public int getList1() {
		return list1.size();
	}

	public int getList2() {
		return list2.size();
	}

	public void run() {
    	process();
    }

    public void stageOne() {
    	String thread = Thread.currentThread().getName();
		System.out.println(thread);
        synchronized (lock1) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                //do your work here
                e.printStackTrace();
            }
            list1.add(random.nextInt(100));
        }
    }

    public void stageTwo() {
    	String thread = Thread.currentThread().getName();
		System.out.println(thread);
        synchronized (lock2) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                //do your work here
                e.printStackTrace();
            }
            list2.add(random.nextInt(100));
        }

    }

    public void process() {
        for (int i = 0; i < 1000; i++) {
            stageOne();
            stageTwo();
        }
    }

    public static void main(String [] args) {
        System.out.println("Starting ...");
       
        long start = System.currentTimeMillis();
        
        Runnable task1 = new Worker();
        
        Thread t1 = new Thread(task1);
        Thread t2 = new Thread(task1);

        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();

        System.out.println("Time taken: " + (end - start));
        System.out.println("List1: " + ((Worker) task1).getList1()+ "; List2: " + ((Worker) task1).getList1());
    }
}
