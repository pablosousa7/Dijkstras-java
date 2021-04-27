import java.util.ArrayList;

public class Path {
    /**
     * A collection of vertices which represent a path through a graph.
     */
    private ArrayList<Vertex> vertices;

    /**
     * The total distance of the path
     * */
    private int distance;

    /**
     * Constructs a Path object.
     * @param vertices - ArrayList of vertices in the path.
     */
    Path(ArrayList<Vertex> vertices){
        this.vertices = vertices;
        this.distance = calculateDistance();
    }

    /**
     * Calculates the total distance of a path based on its edge weights.
     * @return Total distance of the path.
     */
    private int calculateDistance(){
        int dist = 0;

        for (int i = 0; i < vertices.size(); i++){
            if(i < vertices.size() - 1){
                dist += vertices.get(i).getEdge(vertices.get(i+1).getValue()).getWeight();
            }
        }
        return dist;
    }

    /**
     * Sets a new path and recalculates the distance.
     * @param vertices - ArrayList of vertices.
     */
    public void setPath(ArrayList<Vertex> vertices){
        this.vertices = vertices;
        this.distance = calculateDistance();
    }

    /**
     * @return ArrayList of vertices in a path.
     */
    public ArrayList<Vertex> getVertices(){
        return this.vertices;
    }

    /**
     * @return Distance of a path.
     */
    public int getDistance(){
        return distance;
    }


    /**
     * @return A string representation of a path, without weights.
     */
    public String toString(){
        String output = "";

        for(int i = 0; i < vertices.size(); i++){
            output += vertices.get(i).getValue();

            if(i < vertices.size() -1){
                output += " -> ";
            }
        }

        return output;
    }
}
