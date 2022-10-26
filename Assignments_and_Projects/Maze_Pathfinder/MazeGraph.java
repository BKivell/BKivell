import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
/**
 *
 * @author Brad Kivell (20115449)
 * @param <E>
 */
public class MazeGraph<E extends Comparable> {

    public Set<Vertex<E>> vertices; // Stores all maze verticies
    public Set<Edge> edges; // Stores all maze edges
    public Map<Vertex<E>, Set<Edge>> adjacencyLists; // Maps vertex to its set of edges
    public ArrayList<String> pathList;

    // Constructor initializes data structures
    public MazeGraph() {
        this.vertices = new HashSet<>();
        this.edges = new HashSet<>();
        this.adjacencyLists = new HashMap<>();
        this.pathList = new ArrayList<>();
    }

    // Adds input vertex to set of verticies
    public void addVertex(Vertex newVertex) {
        vertices.add(newVertex);
    }

    // Creates edges based on vertex edge names
    public void linkEdges() {
        for (Vertex vertex1 : vertices) // Iterate through verticies
        {
            Set<Edge> vertexEdges = new HashSet<>(); // Stores each  vertex's edges
            for (int i = 0; i < vertex1.edgeNames.size(); i++) { // Iterate through edge names
                if (vertex1.edgeNames.get(i).equals("W")) { // Allow for case of W equals exit
                    vertex1.edgeNames.set(i, "EXIT"); // Set edge name to exit
                }
                Vertex vertex2 = findVertex((E) vertex1.edgeNames.get(i)); // Gets vertex with the edge name
                if (vertex2 != null) { // Check vertex was found
                    Edge newEdge = new Edge(vertex1, vertex2); // Creates edge between verticies
                    edges.add(newEdge); // Adds edge to set of edges
                    vertexEdges.add(newEdge); // Adds new edge to set
                    System.out.println("Vertex: " + vertex1.name + " shares edge with: " + vertex2.name);
                }
            }
            adjacencyLists.put(vertex1, vertexEdges); // Adds vertex and its set of edges to adjacencyList
            vertex1.setEdgeSet(vertexEdges); // Sets vertex edge set to its edge set
        }
    }

    // Returns vertex with same name
    public Vertex findVertex(E name) {
        for (Vertex v : vertices) { // Iterates through verticies
            if (v.name.compareTo(name) == 0) { // Checks if names are equal
                return v;
            }
        }
        return null;
    }

    // Find path and set status of edges using search depth first search
    public void findPath() {
        Set<Vertex> visitedVerticies = new HashSet<>(); // All of visited vertex's
        Stack<Vertex> vertexStack = new Stack<>(); // Vertex stack stores current path

        Vertex currentVertex = findVertex((E) "START"); // Starting vertex
        vertexStack.add(currentVertex); // Adds starting vertex to the stack
        visitedVerticies.add(currentVertex); // Marks starting vertex as visited

        // Loop until exit is found or all verticies have been visited
        while (visitedVerticies.size() != vertices.size()) {
            if (currentVertex.name.equals((E) "EXIT")) { // Check if reached exit vertex
                break;
            }
            Set<Edge> currentEdgeSet = currentVertex.edges; // Stores the current vertex edges
            Edge[] edgeArray = new Edge[currentEdgeSet.size()]; // Stores edges in array
            int i = 0;
            for (Edge edge : currentEdgeSet) { // Populates array with currentEdgeSet data
                edgeArray[i] = edge;
                i++;
            }

            //Selects first edge with an unvisited vertex 
            if (edgeArray.length >= 1 && edgeArray[0] != null && !visitedVerticies.contains(edgeArray[0].followEdge())) // If edge exisits and has not been visited
            {
                currentVertex = edgeArray[0].followEdge(); // Sets current vertex to other end of edge
                visitedVerticies.add(currentVertex); // Adds vertex to visited
                vertexStack.add(currentVertex); // Adds vertex to stack
            } else if (edgeArray.length >= 2 && edgeArray[1] != null && !visitedVerticies.contains(edgeArray[1].followEdge()))// If other edge exisits and has not been visited
            {
                currentVertex = edgeArray[1].followEdge(); // Sets current vertex to other end of edge
                visitedVerticies.add(currentVertex); // Adds vertex to visited
                vertexStack.add(currentVertex); // Adds vertex to stack
            } else { // No avalible path from vertex, pop back until unvisited path is avalible
                if (vertexStack.peek().hasUnvisitedEdge(visitedVerticies)) { // If the previous vertex has unvisited edges
                    currentVertex = vertexStack.peek(); // Only peek to keep current vertex in stack
                } else {
                    currentVertex = vertexStack.pop(); // No moves avalible at next vertex so return from stack
                }
            }
        }

        System.out.println("Tracing Path");
        currentVertex = vertexStack.pop(); // Pop exit vertex
        while (!vertexStack.isEmpty()) { // While verticies have not been traced back
            pathList.add(currentVertex.name.toString() + "   "); // Add each node to path string
            Vertex poppedVertex = vertexStack.pop(); // Pops previous vertex off top of stack
            Edge pathEdge = poppedVertex.returnEdge(currentVertex.name); // Gets edge from poppedVertex
            pathEdge.isPath = true; // Sets edge to path
            System.out.println(currentVertex.name + " : " + poppedVertex.name);
            currentVertex = poppedVertex; // Sets current vertex to poppedVertex to hold reference for finding edge
        }
        pathList.add(currentVertex.name.toString() + "   "); // Add start node
    }
}
