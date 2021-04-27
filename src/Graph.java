import java.util.*;

/**
 * This class represents the graph structure of Dijkstra Algorithm
 * author: Pablo Sousa
 * */
class Graph{
    /*
    * Boolean value of whether or not this graph is directed.
    * */
    private boolean directed;

    /**
     * Boolean value of whether or not this graph is weighted.
     * */
    private boolean weighted;

    /*
    * A collection of vertices in a {@link Graph}
    * */
    private ArrayList<Vertex> vertices = new ArrayList<>();

    /**
     * Constructs a Graph object.
     */
    public Graph(boolean directed, boolean weighted){
        this.directed = directed;
        this.weighted = weighted;
    }

    /**
     * @return Boolean value of whether or not this graph is directed.
     * */
    public boolean isDirected(){
        return directed;
    }

    /**
     * @return Boolean value of whether or not this is weighted
     * */
    public boolean isWeighted(){
        return weighted;
    }

    /**
     * Adds a vertex to a Graph
     * */
    public void addVertex(String v){
        if(!hasVertex(v)){
            vertices.add(new Vertex(v));
        }
    }

    /**
     * @return A collection of vertices in a Graph.
     * */
    public ArrayList<Vertex> getVertices(){
        return vertices;
    }

    /**
     * Creates a set of vertices based on a array of String value
     * @param vertices - String[] representing vertices
     * */
    public void setVertices(String[] vertices){
        this.vertices = new ArrayList<Vertex>();
        for(String v : vertices){
            addVertex(v);
        }
    }

    /*
    * Finds the given vertex.
    * @param value - value of the requested Vertex
    * @return Vertex with the given value
    * */
    public Vertex getVertex(String value){
        for(Vertex v : vertices){
            if(v.getValue().equals(value)){
                return v;
            }
        }
        return null;
    }

    /**
     * @param value - String value of requested Vertex
     * @return Whether or not the given vertex exists within a Graph.
     */
    public boolean hasVertex(String value){
        Vertex v = getVertex(value);

        if(v != null){
            return true;
        }

        return false;
    }

    /**
     * @param v1 - Starting vertex value
     * @param v2 - Ending vertex value
     * @return Whether or not an edge exists between the given vertices.
     */
    public boolean areNeighbors(String v1, String v2){
        Vertex vert1 = getVertex(v1);
        Vertex vert2 = getVertex(v2);

        for(Edge e: vert1.getEdges()){
            if(e.getEndVertex().equals(vert2.getValue())){
                return true;
            }
        }

        return false;
    }

    /**
     * Creates an edge between two vertices.
     * @param v1 - value of first vertex.
     * @param v2 - value of second vertex.
     * @param weight - weight of the new edge.
     */
    public void setEdge(String v1, String v2, int weight){
        if(!weighted){
            weight = 1;
        }

        // if the two vertices are already connected, only change the edge weight
        if(areNeighbors(v1, v2)){
            getVertex(v1).getEdge(v2).setWeight(weight);

            if(!directed){
                getVertex(v2).getEdge(v2).setWeight(weight);
            }
        }else{
            getVertex(v1).addEdge(new Edge(v2, weight));

            if(!directed){
                getVertex(v2).addEdge(new Edge(v1, weight));
            }
        }
    }

    /**
     * Creates an un-weighted edge between the two vertices.
     * @param v1 - String value of first vertex
     * @param v2 - String value of second vertex
     */
    public void setEdge(String v1, String v2){
        setEdge(v1, v2, 1);
    }

    /**
     * Finds the weight of the edge between two vertices.
     * @param v1 - String value of first vertex
     * @param v2 - String value of second vertex
     * @return Weight of the edge between the two vertices if it exists. If there is no edge, returns 0.
     */
    public int getWeightOfEdge(String v1, String v2){
        if(areNeighbors(v1, v2)){
            return getVertex(v1).getEdge(v2).getWeight();
        }

        return 0;
    }

    /**
     * Implements Dijkstra's algorithm to find the shortest path between the given source and all other vertices.
     * @param v1 - String value of starting vertex
     * @return DijkstraTable object containing resulting data
     * @see DijkstraTable
     */
    public DijkstraTable dijkstras(String v1){
        // Table of each vertex's distance form v1
        HashMap<String, Integer> distTable = new HashMap<>();

        // Table of the previous vertex on the shortest path of each vertex
        HashMap<String, String> prevTable = new HashMap<>();

        // Queue of vertices to visit
        LinkedList<String> toVisit = new LinkedList<>();

        for(Vertex v : vertices){
            distTable.put(v.getValue(), Integer.MAX_VALUE);
            prevTable.put(v.getValue(), null);
            toVisit.add(v.getValue());
        }

        distTable.put(v1, 0);
        prevTable.put(v1, v1);

        while(!toVisit.isEmpty()){
            String u1 = null;
            int minDist = Integer.MAX_VALUE;
            for(String v : distTable.keySet()){
                if(toVisit.contains(v)){
                    int value = distTable.get(v);
                    if(value < minDist){
                        minDist = value;
                        u1 = v;
                    }
                }
            }

            Vertex u = getVertex(u1);

            toVisit.remove(u1);

            for(String v : u.getNeighbors()){
                int dist = distTable.get(u.getValue()) + getWeightOfEdge(u.getValue(), v);

                if (dist < distTable.get(v)){
                    distTable.put(v, dist);
                    prevTable.put(v, u.getValue());
                }
            }
        }

        return new DijkstraTable(distTable, prevTable);
    }

    /**
     * Implements Dijkstra's algorithm to find the shortest path between two vertices.
     * @param v1 - String value of starting vertex
     * @param v2 - String value of ending vertex
     * @return Possibly-null {@link Path}  object containing the shortest path between the given vertices. If no path exists, returns null.
     */
    public Path getShortestPathBetween(String v1, String v2){
        DijkstraTable dt = dijkstras(v1);

        ArrayList<Vertex> path = new ArrayList<>();

        String current = v2;
        while(!current.equals(v1)){
            path.add(getVertex(current));
            current = dt.getPrevTable(current);
        }

        path.add(getVertex(v1));

        Collections.reverse(path);

        return new Path(path);
    }
}