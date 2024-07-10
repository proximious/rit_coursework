import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;


/**
 * @author Alex Iacob ai9388@rit.edu
 * @filename Search.java
 *
 * This file runs BFS, DFS, and A* Search on a map to find the closest
 * city on a map of cities
 */
public class Search {

    // creating text strings to simplify later outputs
    static String BFS_TEXT = "\nBreadth-First Search Results: \n";
    static String DFS_TEXT = "\nDepth-First Search Results: \n";
    static String ASTAR_TEXT = "\nA* Search Results: \n";

    // Stores all of the cities with their nodes
    // i.e.) Sacramento, <"Sacramento", {adjacent cities}, location>
    private static HashMap<String, Node> map = new HashMap<>();


    /**
     * Loads all cities into the hashmap in order to create an adjacency matrix
     *
     * @param file city.dat
     */
    public static void loadAllCities(File file) {
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String[] line = sc.nextLine().split("\\s+");
                if (line.length != 4) {
                    continue;
                }
                String cityName = line[0];
                double lat = Double.parseDouble(line[2]);
                double lon = Double.parseDouble(line[3]);
                Location loc = new Location(lat, lon);

                Node city = new Node(cityName, loc, 0, Integer.MAX_VALUE);
                map.put(cityName, city);
            }
        } catch (IOException ioException) {
            System.err.println("File not found: city.dat");
            System.exit(0);
        }
    }


    /**
     * Loads all of the edges into the array list to create an adjacency matrix
     *
     * @param file edge.dat
     */
    public static void loadAllEdges(File file) {
        try {
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                String[] line = sc.nextLine().split("\\s+");
                if (line.length != 2) {
                    continue;
                }
                String city1 = line[0];
                String city2 = line[1];
                // find line[0] and line[1] in map then add each to their own adj list
                // adding each city to its adj list
                map.get(city1).addEdge(map.get(city2));
                map.get(city2).addEdge(map.get(city1));
            }
        } catch (IOException ioException) {
            System.err.println("File not found: city.dat");
            System.exit(0);
        }
    }


    /**
     * Turns latitude-longitude location to distance
     *
     * @param loc1 first location
     * @param loc2 second location
     * @return converted distance
     */
    public static double convertToDistance(Location loc1, Location loc2) {
        double distance;
        double lat1 = loc1.getLat();
        double lon1 = loc1.getLon();
        double lat2 = loc2.getLat();
        double lon2 = loc2.getLon();

        double val1 = (lat1 - lat2) * (lat1 - lat2);
        double val2 = (lon1 - lon2) * (lon1 - lon2);
        distance = Math.sqrt(val1 + val2) * 100;
        return distance;
    }


    /**
     * Sorts the BFS alphabetically
     *
     * @param adj: the set of adjacent nodes
     * @return a sorted list
     */
    public static List<Node> sortBFS(Set<Node> adj) {
        ArrayList<Node> orderedAdjacent = new ArrayList<>(adj);
        for (int i = 0; i < orderedAdjacent.size(); i++) {
            for (int j = i + 1; j < orderedAdjacent.size(); j++) {
                String s1 = orderedAdjacent.get(i).getCityName();
                String s2 = orderedAdjacent.get(j).getCityName();

                if (s1.compareTo(s2) > 0) {
                    // swapping
                    Node temp = orderedAdjacent.get(i);
                    Node move = orderedAdjacent.get(j);
                    orderedAdjacent.remove(i);
                    orderedAdjacent.add(i, move);
                    orderedAdjacent.remove(j);
                    orderedAdjacent.add(j, temp);
                }
            }
        }
        return orderedAdjacent;
    }


    /**
     * runs BFS on a given set of nodes
     *
     * @param nodes all of the cities that are connected
     * @param start starting city
     * @param dest  destination city
     * @return array of the cities forming a path from start to destination
     */
    public static ArrayList<String> breadthFirstSearch
    (HashMap<String, Node> nodes, String start, String dest) {

        ArrayList<String> tempPath = new ArrayList<>();
        ArrayList<Node> visitedNodes = new ArrayList<>();
        Queue<ArrayList<String>> queue = new LinkedList<>();

        tempPath.add(start);
        queue.add(tempPath);
        visitedNodes.add(nodes.get(start));

        while (!queue.isEmpty()) {
            tempPath = queue.remove();
            if (nodes.get(tempPath.get(tempPath.size() - 1)).name.equals(dest)) {
                return tempPath;
            }
            List<Node> adjList = sortBFS(nodes.get(tempPath.get(tempPath.size() - 1)).adj);
            for (Node n : adjList) {
                ArrayList<String> newPath = new ArrayList<>(tempPath);

                if (!visitedNodes.contains(n)) {
                    visitedNodes.add(n);
                    newPath.add(n.name);
                    queue.add(newPath);
                }

                if (n.name.equals(dest)) {
                    return newPath;
                }
            }
        }
        // this is never technically reached, unless you do not see 'dest'
        return tempPath;
    }


    /**
     * Sorts the DFS reverse alphabetically
     *
     * @param adj: the set of adjacent nodes
     * @return a sorted list
     */
    public static List<Node> sortDFS(Set<Node> adj) {
        ArrayList<Node> orderedAdjacent = new ArrayList<>(adj);
        for (int i = 0; i < orderedAdjacent.size(); i++) {
            for (int j = i + 1; j < orderedAdjacent.size(); j++) {
                String s1 = orderedAdjacent.get(i).getCityName();
                String s2 = orderedAdjacent.get(j).getCityName();

                if (s1.compareTo(s2) < 0) {
                    // swapping
                    Node temp = orderedAdjacent.get(i);
                    Node move = orderedAdjacent.get(j);
                    orderedAdjacent.remove(i);
                    orderedAdjacent.add(i, move);
                    orderedAdjacent.remove(j);
                    orderedAdjacent.add(j, temp);
                }
            }
        }
        return orderedAdjacent;
    }


    /**
     * runs DFS on a given set of nodes
     *
     * @param nodes all of the cities that are connected
     * @param start starting city
     * @param dest  destination city
     * @return array of the cities forming a path from start to destination
     */
    public static ArrayList<String> depthFirstSearch
    (HashMap<String, Node> nodes, String start, String dest) {

        ArrayList<String> tempPath = new ArrayList<>();
        ArrayList<Node> visitedNodes = new ArrayList<>();
        Stack<ArrayList<String>> stack = new Stack<>();

        tempPath.add(start);
        stack.push(tempPath);
        visitedNodes.add(nodes.get(start));

        while (!stack.empty()) {
            tempPath = stack.pop();
            if (nodes.get(tempPath.get(tempPath.size() - 1)).name.equals(dest)) {
                return tempPath;
            }

            List<Node> adjList = sortDFS(nodes.get(tempPath.get(tempPath.size() - 1)).adj);
            for (Node n : adjList) {
                ArrayList<String> newPath = new ArrayList<>(tempPath);

                if (!visitedNodes.contains(n)) {
                    visitedNodes.add(n);
                    newPath.add(n.name);
                    stack.push(newPath);
                }

                if (n.name.equals(dest)) {
                    return newPath;
                }
            }
        }
        // this is never technically reached, unless you do not see 'dest'
        return tempPath;
    }


    /**
     * Sorts the AStar adjacency matrix by heuristic
     *
     * @param adj the adjacency matrix
     * @return a sorted list
     */
    public static List<Node> sortAStar (Set<Node> adj) {
        ArrayList<Node> orderedAdjacent = new ArrayList<>(adj);
        for (int i = 0; i < orderedAdjacent.size(); i++) {
            for (int j = i + 1; j < orderedAdjacent.size(); j++) {
                long g1 = (long) orderedAdjacent.get(i).getFScore();
                long g2 = (long) orderedAdjacent.get(j).getFScore();
                long h1 = (long) orderedAdjacent.get(i).getHeuristic();
                long h2 = (long) orderedAdjacent.get(j).getHeuristic();
                long f1 = g1 + h1;
                long f2 = g2 + h2;

                if (compareLong(f1, f2) < 0) {
                    Node temp = orderedAdjacent.get(i);
                    Node move = orderedAdjacent.get(j);
                    orderedAdjacent.remove(i);
                    orderedAdjacent.add(i, move);
                    orderedAdjacent.remove(j);
                    orderedAdjacent.add(j, temp);
                }
            }
        }

        return orderedAdjacent;
    }


    /**
     * Reconstructs a path with a given node and its parent
     *
     * @param cameFrom parent node of current node
     * @param current current node
     * @return the path of strings from starting node to end node
     */
    public static ArrayList<String> reconstructPath (HashMap<Node, Node> cameFrom, Node current) {
        ArrayList<String> path = new ArrayList<>();
        path.add(current.name);

        while (cameFrom.containsKey(current)) {
            // setting current equal to its parent node
            current = cameFrom.get(current);
            path.add(current.name);
        }

        // reverse path because it is initially reversed
        ArrayList<String> reversed = new ArrayList<>();

        for (int i = path.size() - 1; i >= 0; i--) {
            reversed.add(path.get(i));
        }

        return reversed;
    }


    /**
     * runs A* on a given set of nodes
     *
     * @param nodes all of the cities that are connected
     * @param start starting city
     * @param dest  destination city
     * @return array of the cities forming a path from start to destination
     */
    public static ArrayList<String> aStar
    (HashMap<String, Node> nodes, String start, String dest) {

        Comparator<Node> comp = Comparator.comparing(Node::getFScore);
        PriorityQueue<Node> openSet = new PriorityQueue<>(comp);
        openSet.add(nodes.get(start));

        HashMap<Node, Node> cameFrom = new HashMap<>();
        Map<Node, Integer> gScore = new HashMap<>();

        // have to fill up each node with infinite g score
        for (Node n : map.values()) {
            gScore.put(n, Integer.MAX_VALUE);
            Node destNode = map.get(dest);
            double heuristicDistance = convertToDistance(n.loc, destNode.loc);

            n.setHeuristic(heuristicDistance);
        }
        gScore.put(nodes.get(start), 0);

        while (!openSet.isEmpty()) {
            Node current = openSet.peek();
            List<Node> adjList = sortAStar(current.adj);

            if (current.name.equals(dest)) {
                return reconstructPath(cameFrom, current);
            }

            openSet.remove(current);
            for (Node n : adjList) {
                double totalGScore = gScore.get(current) + convertToDistance(current.loc, n.loc);

                if (totalGScore < gScore.get(n)) {
                    cameFrom.put(n, current);
                    gScore.put(n, (int) totalGScore);
                    long cost = (long) (gScore.get(n) + n.h);
                    n.setDistanceTraveled(cost);
                    if (!openSet.contains(n)) {
                        openSet.add(n);
                    }
                }
            }
        }
        return new ArrayList<>();
    }


    /**
     * Formats the output to fit the correct format
     *
     * @param solution the correct path for the cities
     * @param nodes    all of the cities for proper distance calculation
     * @return formatted output
     */
    public static String prettyPrintOutput(ArrayList<String> solution,
                                           HashMap<String, Node> nodes) {
        double totalDistance = 0;
        String last = null;
        StringBuilder output = new StringBuilder();

        for (String city : solution) {
            output.append(city);
            output.append("\n");
            if (last != null) {
                Location loc1 = nodes.get(city).loc;
                Location loc2 = nodes.get(last).loc;

                totalDistance += convertToDistance(loc1, loc2);
            }
            last = city;
        }

        output.append(String.format("That took %d hops to find.\n", solution.size() - 1));

        double rounded = Math.round(totalDistance);
        int intRounded = (int) rounded;
        output.append(String.format("Total distance = %d miles.", intRounded));

        return output.toString();
    }


    /**
     * Main method
     *
     * Verifies the files to make sure they can be read, then prepares the
     * necessary map with all of the nodes from city.dat and edges.dat. Then
     * runs BFS, DFS, and A* searching algorithms on the nodes to get
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        // checking if one or both of the arguments are missing
        if (args.length != 2) {
            System.err.println("Usage: java Search inputFile outputFile");
            System.exit(0);
        }

        // checking to see if city.dat can be read
        File file1 = new File("city.dat");
        loadAllCities(file1);

        // checking to see if edge.dat can be read
        File file2 = new File("edge.dat");
        loadAllEdges(file2);

        // getting the parameters
        City params = City.getFromFile(args[0]);
        params.validate(map.keySet());
        String start = params.start;
        String dest = params.dest;

        // creating solution and output for file
        ArrayList<String> solution;
        StringBuilder output = new StringBuilder();

        // running BFS
        solution = breadthFirstSearch(map, start, dest);
        output.append(BFS_TEXT);
        output.append(prettyPrintOutput(solution, map));
        output.append("\n\n");

        // running DFS
        solution = depthFirstSearch(map, start, dest);
        output.append(DFS_TEXT);
        output.append(prettyPrintOutput(solution, map));
        output.append("\n\n");

        // running A*
        solution = aStar(map, start, dest);
        output.append(ASTAR_TEXT);
        output.append(prettyPrintOutput(solution, map));
        output.append("\n");

        // writing to file
        String filename = args[1];
        try {
            PrintWriter writer;
            if (filename.equals("-")) {
                writer = new PrintWriter(System.out);
            } else {
                writer = new PrintWriter(new File(filename));
            }
            writer.println(output.toString());
            writer.close();
        } catch (FileNotFoundException e) {
            System.err.println(String.format("Could not write to %s", filename));
            System.exit(0);
        }
    }


    /**
     * compares two long type variables
     *
     * @param o1 object 1
     * @param o2 object 2
     * @return 1, 0 , or -1
     */
    public static int compareLong(Object o1, Object o2) {
        long ob1 = (long) o1;
        long ob2 = (long) o2;

        long difference = ob1 - ob2;

        if (difference == 0) {
            // Both are equal
            return 0;
        }
        else if (difference < 0) {
            // obj1 < obj2
            return -1;
        }
        else {
            // obj1 > obj2
            return 1;
        }
    }
}