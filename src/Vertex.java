import java.util.ArrayList;

class Vertex{
    /**
     * value of vertex
     * */
    private String value;

    /*
    * A collection of outgoing edges from this vertex
    * */
    private ArrayList<Edge> edges;

    /*
    * Constructs a Vertex object
    * @param value - Vertex value to set.
    * */
    public Vertex(String value){
        this.value = value;
        this.edges = new ArrayList<Edge>();
    }

    /*
    * @return The value of this Vertex
    * */
    public String getValue(){
        return value;
    }

    /**
     * Returns the Edge object that goes to the
     * given vertex value, if it exists.
     * @param endVertex - the String value of the ending vertex.
     * @return Possibly-null Edge object.
     */
    public Edge getEdge(String endVertex){
        for(Edge e : edges){
            if (e.getEndVertex().equals(endVertex)){
                return e;
            }
        }

        return null;
    }

    /*
    * Returns a collection of outgoing edges.
    * @return ArrayList of all outgoing edges.
    * */
    public ArrayList<Edge> getEdges(){
        return this.edges;
    }

    /**
     * Adds a new outgoing edge to this Vertex.
     * @param e - edge to add
     */
    public void addEdge(Edge e){
        edges.add(e);
    }

    /**
     * Adds a new outgoing Edge to this Vertex.
     * @param endVertex - The ending Vertex.
     * @param weight - The weight of this edge.
     */
    public void addEdge(String endVertex, int weight){
        edges.add(new Edge(endVertex, weight));
    }

    /**
     * Returns a collection of this Vertex's neighbors.
     * @return ArrayList of neighboring Vertexes.
     */
    public ArrayList<String> getNeighbors(){
        ArrayList<String> neighbors = new ArrayList<>();

        for(Edge e : edges){
            neighbors.add(e.getEndVertex());
        }

        return neighbors;
    }

    /**
     * @return a string representation of a Vertex.
     */
    public String toString(){
        return "V(" + getValue() + ")";
    }
}