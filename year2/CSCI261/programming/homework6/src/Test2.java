import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Test2 {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner sc = new Scanner(new File(args[0]));
        int delta = Integer.parseInt(sc.nextLine());
        int alphaSemiMatch = Integer.parseInt(sc.nextLine());
        int alphaNoMatch = Integer.parseInt(sc.nextLine());
        String string1 = sc.nextLine();
        String string2 = sc.nextLine();
        System.out.println("Aligning " + string1 + " with " + string2);
        SeqAlign seq = new SeqAlign(string1, string2,
                delta, alphaSemiMatch, alphaNoMatch);
        long start = System.currentTimeMillis();
        int optVal = seq.OPT_D();
        long stop = System.currentTimeMillis();
        System.out.println("Optimal Value (Dynamic) = " + optVal);

        int m = string1.length();
        int n = string2.length();
        if (m < 10 && n < 10) {
            System.out.println("Matrix = ");
            seq.printMatrix();
        }

        if (m < 20 && n < 20) {
            System.out.println("Match Solution = ");
            seq.showMatch();
        }

        System.out.println("Time: " + (stop - start));
        start = System.currentTimeMillis();
        optVal = seq.OPT_R();
        stop = System.currentTimeMillis();
        System.out.println("Optimal Value (Recursive) = " + optVal);
        System.out.println("Time: " + (stop - start));
    }
}
