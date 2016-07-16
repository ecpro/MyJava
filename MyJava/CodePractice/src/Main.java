import edu.duke.FileResource;

import java.io.*;
import java.util.TreeSet;

public class Main {

    public static void main(String[] args) throws IOException {
        //System.out.println("Hello World!");

        long startTime = System.nanoTime();

        TreeSet<String> uniqueCustomer = new TreeSet<>();
        //BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        FileResource fr = new FileResource("input2.txt");

        for ( String line : fr.lines()) {
            if(line.length() <=5) uniqueCustomer.add(line);
        }
        System.out.println(uniqueCustomer.size());
        //String line = br.readLine();
        //int N = Integer.parseInt(line);
//        for (int i = 0; i < N; i++) {
//            uniqueCustomer.add(br.readLine());
//        }
        for (String customer : uniqueCustomer) {
            System.out.println(customer);
        }

        long endTime = System.nanoTime();
        System.out.println("Took "+(float) (endTime - startTime)/1000000000 + "sec");
    }
}
