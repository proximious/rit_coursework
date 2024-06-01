/*
@author: Alex Iacob ai9388
@filename: Stable,java
File runs the Gale Shapely algorithm.
 */
import java.io.*;
import java.util.*;

public class Stable {

    private static Scanner input;
    public static int menAndWomen;
    private static HashMap<Integer, Integer> couples;
    private static Set<Integer> set;


    public void makingMatches(int [][] men, int [][] women){
        couples = findCouples(men, women);
        set = couples.keySet();
        for (int key : set) {
            int manNumber = couples.get(key) + 1;
            int womanNumber = key + 1;
            System.out.println( "m" + manNumber + " w" + womanNumber);
        }
    }


    public static HashMap<Integer, Integer> findCouples(int[][] men, int[][] women){
        HashMap<Integer, Integer> couples = new HashMap<>();

        int k = 0;
        int j = 0;

        while (k < women.length){
            couples.put(k, null);
            k++;
        }

        Set<Integer> bachelors = new HashSet<>();
        while (j < men.length){
            bachelors.add(j);
            j++;
        }

        int bachelorCount = bachelors.size();

        while(bachelorCount > 0){

            int currentBachelor = bachelors.iterator().next();

            for (int i = 0; i < men[currentBachelor].length ; i++) {

                int wmen = men[currentBachelor][i] - 1;

                if(couples.get(wmen) == null){
                    couples.put(wmen, currentBachelor);
                    bachelors.remove(currentBachelor);
                    break;
                }else{
                    int alreadyAcceptedMan = couples.get(wmen);
                    if(willChangePartner(currentBachelor, alreadyAcceptedMan, wmen, women)){
                        couples.put(wmen, currentBachelor);
                        bachelors.add(alreadyAcceptedMan);
                        bachelors.remove(currentBachelor);
                        break;
                    }
                }
            }
            bachelorCount = bachelors.size();
        }
        return couples;
    }


    static boolean willChangePartner(int currentBachelor, int alreadyAcceptedMan, int currentWomen, int[][] women){

        int pref_currentBachelor = -1;
        int pref_alreadyAcceptedMan = -1;

        for (int i = 0; i <women[currentWomen].length ; i++) {

            if(women[currentWomen][i] - 1 == currentBachelor)
                pref_currentBachelor = i;

            if(women[currentWomen][i] - 1 == alreadyAcceptedMan)
                pref_alreadyAcceptedMan = i;
        }

        return pref_currentBachelor < pref_alreadyAcceptedMan;
    }


    public static void writeToOutput(int[][] men, int[][] women){
        String outFilename = menAndWomen + ".out";
        try {
            String filename = menAndWomen + ".in";
            File file = new File(filename);
            Scanner bufInput = new Scanner(file);

            BufferedWriter out = new BufferedWriter(new FileWriter(outFilename));
            //writing the first number
            out.write(menAndWomen + "\n");

            bufInput.nextLine();

            for (int i = 0; i < (menAndWomen * 2); i++) {
                String[] line = bufInput.nextLine().trim().replaceAll("\\s{2,}", " ").split(" ");
                for (String s : line) {
                    out.write(s + " ");
                }
                out.write("\n");
            }

            //writing out the final
            couples = findCouples(men, women);
            set = couples.keySet();
            for (int key : set) {
                int manNumber = couples.get(key) + 1;
                int womanNumber = key + 1;
                out.write( "m" + manNumber + " w" + womanNumber + "\n");
            }
            //closing the writer
            out.close();
        }
        catch (IOException ignored) {
        }
    }


    public static void main(String[] args) {
        // checking to see if there is no file in the command line
        if (args.length == 0){
            System.out.println("File could not be found.");
            System.exit(1);
        }

        try {
            //creating a new file to read
            File file = new File(args[0]);
            input = new Scanner(file);
        } catch (IOException ioException) {
            // if there is an error while attempting to open the file, then the program exits
            System.err.println("File cannot be opened.");
            System.exit(1);
        }

        // getting the size of the array
        menAndWomen = input.nextInt();
        input.nextLine();

        int [][] menArray = new int[menAndWomen][menAndWomen];
        int [][] womenArray = new int[menAndWomen][menAndWomen];

        // creating the male preference array
        for (int i = 0; i < menAndWomen; i++) {
            String[] line = input.nextLine().trim().replaceAll("\\s{2,}", " ").split(" ");
            for (int j = 1; j < line.length; j++) {
                menArray[i][j - 1] = Integer.parseInt(line[j]);
            }
        }

        // creating the women preference array
        for (int i = 0; i < menAndWomen; i++) {
            String[] line = input.nextLine().trim().replaceAll("\\s{2,}", " ").split(" ");
            for (int j = 1; j < line.length; j++) {
                womenArray[i][j - 1] = Integer.parseInt(line[j]);
            }
        }

        Stable stableMarriage = new Stable();
        stableMarriage.makingMatches(menArray, womenArray);
        writeToOutput(menArray, womenArray);

        // closing the file
        input.close();
    }
}
