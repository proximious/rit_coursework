/*
@author: Alex Iacob
@filename: Cycle.java
File creates a graph configuration and creates it nodes
 */


import java.io.*;
import java.util.*;

public class Cycle {
    public static Scanner input;
    public static int numOfNodes;
    public static Node[] allNodes;


    /*
    params: nodes: all of the nodes
            node:  the starting node to start the BFS
     */
    public static void BFS(Node[] nodes, Node node){
        // when visited is true, that means that that node was visited
        boolean[] visited = new boolean[nodes.length + 1];
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);
        visited[node.id] = true;

        node.layer = 0;
        System.out.print(node.id + "(" + node.layer + ") ");

        while(queue.size() > 0){
            Node removedNode = queue.remove();
            //visit the removed node
            visited[removedNode.id] = true;

            for(Node u : removedNode.adj){
                if(!visited[u.id]){
                    visited[u.id] = true;
                    queue.add(u);
                    u.layer = removedNode.layer + 1;
                    System.out.print(u.id + "(" + u.layer + ") ");
                } else if(u.layer == removedNode.layer){
                    System.out.println("not bipartite");
                    System.out.println("cycle exists");
                } else if(u.layer == removedNode.layer + 1){
                    System.out.println("cycle exists");
                 }
            }
        }
        System.out.println("\nbipartite");
        System.out.println("acyclic");
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
        numOfNodes = input.nextInt();
        // creating the array of nodes
        allNodes = new Node[numOfNodes];
        input.nextLine();

        //filling up the array of Nodes
        for (int i = 0; i < numOfNodes; i++){
            Node node = new Node(i + 1);
            allNodes[i] = node;
        }

        for (int i = 0; i < numOfNodes; i++){
            String[] line = input.nextLine().trim().replaceAll("\\s{2,}", " ").split(" ");

            for (int j = 1; j < line.length; j++){
                allNodes[i].addEdge(allNodes[Integer.parseInt(line[j]) - 1]);
            }
        }

        System.out.println(numOfNodes);
        for (Node allNode : allNodes) {
            System.out.println(allNode.toString());
        }
        System.out.println("");
        int connectedNumber = 1;
        //loop though all of the nodes
        // check each node's layer
        // if the layer is -1, then apply the BFS
        // otherwise keep looping until another -1 layer is found
        // print out a newline plus connected component
        for(int i = 0; i < numOfNodes; i++){
            if (allNodes[i].layer == -1){
                System.out.println("connected component: " + connectedNumber);
                BFS(allNodes, allNodes[i]);
                connectedNumber++;
            }
        }
    }
}

