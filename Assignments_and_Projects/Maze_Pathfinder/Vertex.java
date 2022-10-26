import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Bradk
 * @param <E>
 */
public final class Vertex<E extends Comparable> {

    public E name; // Stores node identifier
    public int xPosition; // Stores vertex x location on frame
    public int yPosition; // Stores vertex y location on frame
    public ArrayList<E> edgeNames = new ArrayList<>(); // Stores vertex names to be linked with edge
    public Set<Edge> edges = new HashSet<>(); // Stores reference to edges

    public Vertex(E name, int xPos, int yPos, String name1, String name2) {
        this.name = name;
        this.xPosition = xPos;
        this.yPosition = yPos;
        this.addEdgeName((E) name1);
        this.addEdgeName((E) name2);
    }

    // Adds vertex name to edgeNames
    public void addEdgeName(E connectedVertexName) {
        edgeNames.add((E) connectedVertexName);
    }

    // Sets the vertex' edge set to input set
    public void setEdgeSet(Set<Edge> newSet) {
        this.edges = newSet;
    }

    // Returns edge if edge points to input name
    public Edge returnEdge(E name) {
        for (Edge edge : edges) { // Loops through edges in edge set
            if (edge.vertex2.name.equals(name)) { // Check if name matches
                return edge;
            }
        }
        return null; // Returns null if nothing found
    }

    // Returns true if either of the connected edges vertices have a name matching the input set
    public boolean hasUnvisitedEdge(Set<Vertex> visitedVerticies) {
        boolean flag = false;
        for(Edge edge : edges) // Iterates through edges
        {
            if(!visitedVerticies.contains(edge.vertex2)) // Check if the set contains the connected vertex
            {
                flag = true;
            }
        }
        return flag;
    }

}
