public class Dijkstras {
    public static void main(String[] args){
        Graph g = new Graph(false, true);

        g.setVertices(new String[] {"A", "B", "C", "D", "E"});

        g.setEdge("A", "B", 1);
        g.setEdge("B", "C", 3);
        g.setEdge("C", "D", 1);
        g.setEdge("D", "E", 2);
        g.setEdge("B", "E", 8);

        Path p = g.getShortestPathBetween("B", "E");
        System.out.println(p);
    }
}
