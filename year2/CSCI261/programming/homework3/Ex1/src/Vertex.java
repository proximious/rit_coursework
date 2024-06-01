/*
@author: Alex Iacob
@filename: Vertex.java
Creates a vertex class
 */

import java.util.*;

public class Vertex implements Comparable<Vertex>{
    public int id;
    public int distance = Integer.MAX_VALUE;
    public HashMap<Vertex, Integer> adj; // holds the vertex and its distance
    public Vertex path;


    public Vertex(int id) {
        this.id = id;
        adj = new HashMap<Vertex, Integer>();
    }


    // add the given node to the adjacency list of this node
    // @ param node  the node to add
    public void addAdj(Vertex vertex, int distance ) {
        if ( this.id == vertex.id )
            throw new RuntimeException("loops not allowed");
        adj.put(vertex, distance);
    }


    @Override
    public String toString() {
        return "Vertex{" +
                "id=" + id +
                ", distance=" + distance +
                ", adj=" + adj +
                ", path=" + path +
                '}';
    }

    @Override
    public int compareTo(Vertex o) {
        return Integer.compare(this.distance, o.distance);
    }

}

