/**
 *
 * @author Brad Kivell
 */
public class Edge {

    // Holds reference to both verticies
    public Vertex vertex1;
    public Vertex vertex2;
    public boolean isPath;

    // Constructor, initializes variables
    public Edge(Vertex v1, Vertex v2) {
        vertex1 = v1;
        vertex2 = v2;
        isPath = false;
    }

    // Returns 2nd vertex in edge
    public Vertex followEdge() {
        return vertex2;
    }

}
