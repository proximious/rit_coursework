import java.util.*;

/**
 * @author Alex Iacob ai9388@rit.edu
 * @filename Node.java
 * 
 * Code originally from CSCI 261: Analysis of Algorithms, updated to fit project
 */
public class Node {

    public String name; // node number
    public Set<Node> adj; // adjacent nodes
    public Location loc; // lat and lon for each node
    public double h; // used for A* search
    public double f; // used for A* search

    public Node(String name, Location loc, double h, double f) {
        this.name = name;
        this.loc = loc;
        adj = new LinkedHashSet<Node>();
        this.h = h;
        this.f = 0;
    }


    public void setHeuristic (double val) {
        h += val;
    }


    public void setDistanceTraveled(double distanceTraveled) {
        this.f = distanceTraveled;
    }


    public String getCityName() {
        return name;
    }


    public double getHeuristic() {
        return h;
    }


    public double getFScore() {
        return f;
    }


    // add the given node to the adjacency list of this node
    // @ param node the node to add
    public void addAdj(Node node) {
        if (this.name.equals(node.name))
            throw new RuntimeException("loops not allowed");
        adj.add(node);
    }


    // add the given node to the adjacency set of this node
    // and add this node to the adjacency set of the given node
    // @param node the node to add
    public void addEdge(Node node) {
        addAdj(node); // add node to this adjacency set
        node.addAdj(this); // and this to node's adjacency set
    }


    public String toString() {
        String str = "Node[" + name + "]:";
        for (Node node : adj)
            str += " " + node.name;
        return str;
    }

}
