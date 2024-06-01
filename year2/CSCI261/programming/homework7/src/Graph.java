/**
 * @author: Alex Iacob ai9388@rit.edu
 * @filename: Graph.java
 */

import java.util.*;

public class Graph {

    public Map<String, ArrayList<Edge>> map;   // keys are nodes
    // each node maps to an
    // list of Edges where the
    // node is the source

    public int flow;       // the flow from s -> t in this graph right now
    public ArrayList<Edge> completePath = new ArrayList<>();

    /**
     * Construct a Graph object
     *
     * @param
     */
    public Graph() {
        this.map = new HashMap<String, ArrayList<Edge>>();
    }

    /**
     * add a new node to this Graph
     *
     * @param node - is just a string label
     */
    public void addNode(String node) {
        this.map.put(node, new ArrayList<Edge>());
    }

    /**
     * add a new edge to this graph
     *
     * @param node - String - add this edge to nodes adjacency list
     * @param e    - Edge to add
     */
    public void addEdge(String node, Edge e) {
        ArrayList<Edge> list;
        list = map.get(node);
        list.add(e);
        map.put(node, list);
    }

    /**
     * Get set of nodes (all string labels) for this graph
     *
     * @return Set of Strings (nodes) in this graph
     */
    public Set<String> getNodes() {
        return map.keySet();
    }

    /**
     * Get set of nodes (all string labels) for this graph
     *
     * @return Set of Strings (nodes) in this graph
     */
    public ArrayList<Edge> getEdges(String n) {
        return map.get(n);
    }


    /**
     * @return String representation of this Graph, includes the current flow
     */
    public String toString() {
        String result = "G_" + this.flow + "\n";
        Set<String> nodes = this.map.keySet();
        for (String n : nodes) {
            result += n + "=" + this.map.get(n) + "\n";
        }
        return result;
    }

    /**
     * @return a residual graph corresponding to this Graph
     */
    public ResGraph getResidual() {

        ResGraph graphR = new ResGraph();
        Set<String> nodes = this.getNodes();
        for (String n : nodes)
            graphR.addNode(n);

        for (String n : nodes) {
            ArrayList<Edge> edgeList = this.getEdges(n);
            for (Edge e : edgeList) {
                if (e.capacity == e.flow)
                    graphR.addEdge(e.dest, new EdgeB(e.dest, e.source,
                            e.capacity, e.capacity));
                else if (e.flow == 0) {
                    graphR.addEdge(n, new EdgeF(e.source, e.dest,
                            e.capacity, e.capacity));
                } else {
                    graphR.addEdge(n,
                            new EdgeF(e.source, e.dest, e.capacity,
                                    e.capacity - e.flow));
                    graphR.addEdge(e.dest,
                            new EdgeB(e.dest, e.source, e.capacity, e.flow));
                }
            }
        }

        graphR.flow = this.flow;
        return graphR;
    }


    /**
     * @return augment(update) this graph with the new path
     */
    public void augment(ArrayList<Edge> path) {
        // if the path is empty, there is no update to this graph
        if (path.size() <= 0) return;

        int b = bottleNeck(path);
        // FINISH ME UP TO THE LAST STATEMENT BELOW
        // for each edge in the path
        // if that path is a forward edge
        // increase that path by the bottleneck value
        // otherwise the path is a backwards edge
        // decrease that path by the bottleneck value
        for (Edge e : path) {
            if (e instanceof EdgeF) {
                Objects.requireNonNull(pickOut(map.get(e.source), e.dest)).flow += b;
            } else if (e instanceof EdgeB) {
                Objects.requireNonNull(pickOut(map.get(e.dest), e.source)).flow -= b;
            }
        }

        // update the flow of this graph with the bottleneck in the path
        this.flow += b;
    }

    /**
     * @return pick out and return an edge from this adjacency
     * list given the destination node
     */
    private Edge pickOut(ArrayList<Edge> edgeList, String dest) {
        for (Edge e : edgeList)
            if (dest.equals(e.dest))
                return e;
        return null;

    }

    /**
     * @return given a path (a listed of Edges), return
     * the bottleneck
     */
    private int bottleNeck(ArrayList<Edge> path) {
        int n = path.size();
        int minFlow = path.get(0).flow;
        for (int i = 1; i < n; i++) {
            int flow = path.get(i).flow;
            if (flow < minFlow)
                minFlow = flow;
        }
        return minFlow;
    }
}

// There is a residual graph for every graph

class ResGraph extends Graph {
    HashSet<String> exploredEdges = new HashSet<>();

    /*
     *	@return String representation of this Graph, includes the current flow
     */
    public String toString() {
        String result = "G_" + this.flow + "^R\n";
        Set<String> nodes = this.map.keySet();
        for (String n : nodes) {
            result += n + "=" + this.map.get(n) + "\n";
        }
        return result;
    }

    // FINISH ONE OF THE FOLLOWING METHODS, either DFS or BFS

    /**
     * @param source node for search
     * @param target node for search
     * @return a path from the source node to the target node
     * which is a list of Edges
     * returns an empty path[] if there is no path
     * uses DFS
     */
    public ArrayList<Edge> DFS(String source, String target) {
        if (source.equals(target)) {
            return completePath;
        }

        exploredEdges.add(source);

        for (Edge e : map.get(source)) {
            if (!exploredEdges.contains(e.dest)) {
                completePath.add(e);
				ArrayList<Edge> returnedDFS = DFS(e.dest, target);

				//checking if wrong path
                if (returnedDFS.size() != 0) {
                    return returnedDFS;
                }

                completePath.remove(completePath.size() - 1);
            }
        }
        return new ArrayList<Edge>(); // dummy return value of
        // empty list which signals no path
    }


    /**
     * @param source node for search
     * @param target node for search
     * @return a path from the source node to the target node
     * which is a list of Edges
     * returns an empty path[] if there is no path
     * uses BFS
     */
    public ArrayList<Edge> BFS(String source, String target) {

        // or FINISH ME

        return new ArrayList<Edge>(); // dummy return value of
        // empty list which signals no path
    }


}
