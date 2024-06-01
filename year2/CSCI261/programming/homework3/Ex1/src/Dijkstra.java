/*
  @author: Alex Iacob ai9388@rit.edu
 * @filename:  Dijkstra.java
 * File runs an implementation of  Dijkstra's algorithm
 */

import java.util.*;
import java.io.*;

public class Dijkstra {
    public static Scanner input;
    public static int numOfNodes;
    public static Vertex startingNode;
    public static final String DIST_DISPLAY =  "dist:";
    public static final String PATH_DISPLAY = " path: ";


    /**
     * runs an implementation of dijkstra's algorithm
     * @param vertex: the given vertex
     */
    public static void dijkstra (Vertex vertex){
        Queue<Vertex> queue = new PriorityQueue<Vertex>();
        Queue<Vertex> result = new PriorityQueue<Vertex>();

        vertex.distance = 0;
        queue.add(vertex);

        while (! queue.isEmpty()){
            Vertex v = queue.remove();
            if(! result.contains(v)){
                result.add(v);

                for( Vertex w : v.adj.keySet()){
                    int cost = v.adj.get(w);
                   if(v.distance + cost < w.distance){
                        w.distance = v.distance + cost;
                        w.path = v;
                        queue.add(w);
                    }
                }
            }
        }

        for (Vertex a: result){
            if(a.path == null) {
                System.out.println(a.id + DIST_DISPLAY + a.distance + PATH_DISPLAY + null);
            } else{
                System.out.println(a.id + DIST_DISPLAY + a.distance + PATH_DISPLAY + a.path.id);
            }
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
            // getting the total number of nodes
            numOfNodes = input.nextInt();
            startingNode = new Vertex(1);
            input.nextLine();

            // if there is more than 1 input, there is the
            if (args.length > 1){
                if (!(Integer.parseInt(args[1]) > numOfNodes) && Integer.parseInt(args[1]) > 0)  {
                    startingNode = new Vertex(Integer.parseInt(args[1]));
                } else {
                    // if the starting node id is greater than the total number of nodes or negative
                    // then the system should exit
                    System.err.println("The given starting node is not valid");
                    System.exit(1);
                }
            }
        } catch (IOException ioException) {
            // if there is an error while attempting to open the file, then the program exits
            System.err.println("File cannot be opened.");
            System.exit(1);
        }

        // making a new list for the vertices
        List<Vertex> allVertexes = new ArrayList<>();

        // looping through the file to add all of the vertices into a list
        for (int i = 0; i < numOfNodes; i++){
            // add starting vertex into the list of vertices
            Vertex v = new Vertex(i + 1);
            allVertexes.add(v);
        }

        // reading the file for the content
        for (int i = 0; i < numOfNodes; i++){
            // parsing the line contents to get the necessary data to create the nodes
            String[] line = input.nextLine().trim().replaceAll("\\s{2,}", " ").split(" ");

            // iterating through each line individually
            for(int j = 1; j < line.length; j++){
                String[] splitByColon = line[j].split(":");
                Vertex adjacentVertex = allVertexes.get(Integer.parseInt(splitByColon[0])-1);
                int vertexWeight = Integer.parseInt(splitByColon[1]);
                allVertexes.get(i).addAdj(adjacentVertex, vertexWeight);
            }
        } // end of for

        dijkstra(allVertexes.get(startingNode.id - 1));

    } // end of main
} // end of file
