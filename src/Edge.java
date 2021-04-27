public class Edge {
    /**
     * The String value of the Vertex the edge points to.
     */
    private String endVertex;

    /*
    * The weight of the edge
    * */
    private int weight;

    /**
     * Constructs an Edge object.
     * @param endVertex - The String value of the Vertex the edge points to.
     * @param weight - The weight of the edge.
     */
    public Edge(String endVertex, int weight){
        this.endVertex = endVertex;
        this.weight = weight;
    }

    /**
     * @return String value of the Vertex the edge points to.
     */
    public String getEndVertex(){
        return endVertex;
    }

    /**
     * @return The weight of the edge.
     */
    public int getWeight(){
        return weight;
    }

    /**
     * Sets the weight of an edge.
     * @param w - The new weight to set.
     */
    public void setWeight(int w){
        this.weight = w;
    }
}
