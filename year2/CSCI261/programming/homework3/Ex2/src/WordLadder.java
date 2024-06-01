/*
  @author: Alex Iacob ai9388@rit.edu
 * @filename:  WordLadder.java
 * File runs an implementation of  Dijkstra's algorithm
 */

import java.io.File;
import java.io.IOException;
import java.util.*;

public class WordLadder {
    public static Scanner input;
    public static int numOfNodes;
    public static WordVertex startingNode;


    /**
     * runs an implementation of dijkstra's algorithm
     * @param vertex: the given vertex
     */
    public static void dijkstra (WordVertex vertex){
        Queue<WordVertex> queue = new PriorityQueue<>();
        Queue<WordVertex> result = new PriorityQueue<>();

        vertex.distance = 0;
        queue.add(vertex);

        while (! queue.isEmpty()){
            WordVertex v = queue.remove();
            if(! result.contains(v)){
                result.add(v);

                for( WordVertex w : v.adj.keySet()){
                    int cost = v.adj.get(w);
                   if(v.distance + cost < w.distance){
                        w.distance = v.distance + cost;
                        w.path = v;
                        queue.add(w);
                    }
                }
            }
        }

        for (WordVertex w: result){
            while (!(w == null)){
                if(w.path == null){
                    System.out.print( w.word);
                }else {
                    System.out.print(w.word + ">");
                }
                w = w.path;
            }
            System.out.println();
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
            startingNode = new WordVertex(1);
            input.nextLine();

        } catch (IOException ioException) {
            // if there is an error while attempting to open the file, then the program exits
            System.err.println("File cannot be opened.");
            System.exit(1);
        }

        // making a new list for the vertices
        List<WordVertex> allVertexes = new ArrayList<>();

        // looping through the file to add all of the vertices into a list
        for (int i = 0; i < numOfNodes; i++){
            // add starting vertex into the list of vertices
            WordVertex v = new WordVertex(i + 1);
            allVertexes.add(v);
        }

        // reading the file for the content
        for (int i = 0; i < numOfNodes; i++){
            // parsing the line contents to get the necessary data to create the nodes
            String[] line = input.nextLine().trim().replaceAll("\\s{2,}", " ").split(" ");

            // getting the node number
            String trimmedLine = line[0].trim().replaceAll("\\D+", "");
            int vertexNum = Integer.parseInt(trimmedLine);

            allVertexes.get(vertexNum - 1).word = line[1];

            // iterating through each line individually
            for(int j = 2; j < line.length; j++){
                String[] getColon = line[j].split(":");
                WordVertex adjacentVertex = allVertexes.get(Integer.parseInt(getColon[0])-1);
                int vertexWeight = Integer.parseInt(getColon[1]);
                allVertexes.get(vertexNum - 1).addAdj(adjacentVertex, vertexWeight);
            }
        } // end of for
        
        dijkstra(allVertexes.get(startingNode.id - 1));

    } // end of main
} // end of file
