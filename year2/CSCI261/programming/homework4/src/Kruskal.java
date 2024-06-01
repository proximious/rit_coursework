import java.io.*;
import java.util.*;

/**
 * @author: Alex Iacob ai9388@rit.edu
 * @filename: Kruskal.java
 */
public class Kruskal {
    public static Scanner input;
    public static List<Edge> allEdges;
    public static int numOfEdges;
    public static String outFilename;


    public static List<Edge> kruskal(List<Edge> edges, int numVertices) {
        DisjSets ds = new DisjSets(numVertices);
        Queue<Edge> queue = new PriorityQueue<>(edges);
        List<Edge> mst = new ArrayList<Edge>();

        while (mst.size() < numOfEdges - 1) {
            Edge e = queue.remove();
            int uSet = ds.find(e.getU() - 1);
            int vSet = ds.find(e.getV() - 1);
            if (uSet != vSet) {
                mst.add(e);
                ds.union(uSet, vSet);
            }
        }
        return mst;
    }


    public static void writeToOutput(List<Edge> mst ,String filename) throws IOException {
        FileWriter out = new FileWriter(filename);
        int totalWeight = 0;
        for (Edge e: mst) {
            out.write("(" + e.getU() + ", " + e.getV() + ")" + ":" + e.getWeight() + "\n");
            totalWeight += e.getWeight();
        }
        out.write("Total Cost: " + totalWeight);
        out.flush();
        out.close();
    }


    public static void main(String[] args) throws IOException {
        if (args.length == 0) {
            System.out.println("File could not be found.");
            System.exit(1);
        }
        try {
            //creating a new file to read
            File file = new File(args[0]);
            input = new Scanner(file);
            outFilename = args[1];
            // getting the total number of nodes
            numOfEdges = input.nextInt();
            input.nextLine();
        } catch (FileNotFoundException e) {
            // if there is an error while attempting to open the file, then the program exits
            System.err.println("File cannot be opened.");
            System.exit(1);
        } //catch (IOException ignored) {}

        allEdges = new ArrayList<>();
        for (int i = 0; i < numOfEdges; i++){
            // parsing the line contents to get the necessary data to create the nodes
            String[] line = input.nextLine().trim().replaceAll("\\s{2,}", " ").split(" ");
            int currentNode = i + 1;

            for (int j = 1; j < line.length; j++) {
                String[] splitByColon = line[j].split(":");
                int v = Integer.parseInt(splitByColon[0]);
                int edgeWeight = Integer.parseInt(splitByColon[1]);
                Edge edge = new Edge(currentNode, v, edgeWeight);
                allEdges.add(edge);
            }
        }

        writeToOutput(kruskal(allEdges, numOfEdges), outFilename);
    }
}
