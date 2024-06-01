import java.util.*;
import java.io.*;

public class TestAndTime {

    public static void main (String [] args) throws FileNotFoundException{

	Scanner sc = new Scanner(new File(args[0]));
        // number of points
        int n =Integer.parseInt(sc.nextLine());
        Point [] P = new Point[n];
        for (int i = 0; i < P.length; i++) {
	    String [] tokens = sc.nextLine().split(" ");
	    int x = Integer.parseInt(tokens[0]);
	    int y = Integer.parseInt(tokens[1]);
	    P[i] = new Point(x, y);
	}

	// use a copy to guard against pointer messes you  may make
	Point[] copyOfP = Arrays.copyOf(P,P.length);


	long start, stop;
	
        // make a brute force algorithm
	ClosestPairAlg a = new BruteForce();
	// time
	start = System.currentTimeMillis();
	Triple result = a.closestPair(copyOfP);
	stop = System.currentTimeMillis();	
	// report results
	System.out.println("Closest Pair:");
	System.out.println(result);
        System.out.println("Time: " + (stop-start));	

	// use another copy to guard against pointer messes you  may make
        copyOfP = Arrays.copyOf(P,P.length);

	// make a divide and conquer algorithm
	a = new DivideAndConquer();
	// time
	start = System.currentTimeMillis();
	result = a.closestPair(copyOfP);
        stop = System.currentTimeMillis();

	// report results
	System.out.println("Closest Pair:");
	System.out.println(result);
        System.out.println("Time: " + (stop-start));		
    }
}

