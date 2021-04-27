import java.util.HashMap;

public class DijkstraTable {
    /**
     * Distance of node
     * */
    private HashMap<String, Integer> distTable;

    /**
     * Previous node
     * */
    private HashMap<String, String> prevTable;

    /*
    * Constructor
    * */
    public DijkstraTable(HashMap<String, Integer> distTable, HashMap<String, String> prevTable){
        this.distTable = distTable;
        this.prevTable = prevTable;
    }

    /*
    * Get Source of path
    * */
    public String getSource(){
        return distTable.keySet().iterator().next();
    }

    /**
     * Get distance to finish
     * */
    public int getDistTable(String v) {
        return distTable.get(v);
    }

    /*
    * Get previous vertex
    * */
    public String getPrevTable(String v){
        return prevTable.get(v);
    }
}
