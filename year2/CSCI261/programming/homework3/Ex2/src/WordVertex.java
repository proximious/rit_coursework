/*
@author: Alex Iacob
@filename: WordVertex.java
Creates a vertex class
 */

import java.util.HashMap;

public class WordVertex implements Comparable<WordVertex>{
    public int id;
    public int distance = Integer.MAX_VALUE;
    public HashMap<WordVertex, Integer> adj; // holds the vertex and its distance
    public WordVertex path;
    public String word;


    public WordVertex(int id) {
        this.id = id;
        adj = new HashMap<WordVertex, Integer>();
    }


    // add the given node to the adjacency list of this node
    // @ param node  the node to add
    public void addAdj(WordVertex vertex, int distance ) {
        if ( this.id == vertex.id )
            throw new RuntimeException("loops not allowed");
        adj.put(vertex, distance);
    }


    @Override
    public String toString() {
        return "WordVertex{" +
                "id=" + id +
                ", distance=" + distance +
                ", adj=" + adj +
                ", path=" + path +
                ", word='" + word + '\'' +
                '}';
    }

    @Override
    public int compareTo(WordVertex o) {
        return Integer.compare(this.distance, o.distance);
    }

}

