
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import javax.swing.JFrame;

/**
 *
 * @author Brad Kivell (20115449)
 * @param <E>
 */
public class MazeGUIDrawer<E extends Comparable> {

    private JFrame frame; // Stores reference to canvas Jframe
    private MazeFileLoader mfl; // Stores reference to file loader to manage maze data
    private Map<Vertex<E>, Set<Edge>> adjacencyLists; // Maps vertex to edge

    public final int SCALE = 70; // Distance between vertex objects
    public final int CORNER_OFFSET = 75; // Distance from corner
    public final int SIZE = 40; // Size of vertex objects

    public MazeGUIDrawer(JFrame frame, MazeFileLoader mfl) {
        this.frame = frame;
        this.mfl = mfl;
    }

    public void drawMaze(Graphics g) {
        g.setColor(Color.white); // Clear any old graphics with new background
        g.fillRect(0, 0, frame.getWidth(), frame.getHeight()); //
        g.setColor(Color.black); //

        this.adjacencyLists = mfl.mazeGraph.adjacencyLists; // Update adjacency list to be drawn
        ArrayList<Edge> edgesToDraw = new ArrayList<>(); // Stores edges to be drawn after vertices

        // Iterate over maze adjacency list, (Each vertex mapped to its set of edges)
        for (Map.Entry<Vertex<E>, Set<Edge>> entry : adjacencyLists.entrySet()) {
            Vertex<E> vertex = entry.getKey(); // Pulls vertex
            Set<Edge> edgeSet = entry.getValue(); // Pulls edges as set

            int drawXPos = (vertex.xPosition * SCALE) + CORNER_OFFSET; // Calculates x position based on vertex
            int drawYPos = (vertex.yPosition * SCALE) + CORNER_OFFSET; // Calculates y position based on vertex

            // Draw Vertex + Name
            g.fillOval(drawXPos, drawYPos, SIZE, SIZE); // Draws circle representing vertex
            g.drawString(vertex.name.toString(), drawXPos, drawYPos); // Draws vertex name

            // Add edges to list to be drawn after all verticies
            for (Edge edge : edgeSet) {
                edgesToDraw.add(edge);
            }
        }
        for (Edge edge : edgesToDraw) // Paint edges after verticies to avoid overlap
        {
            drawEdge(edge, g, (edge.isPath) ? Color.green : Color.red); // Paints green if edge is part of the path, red if not a path
        }

    }

    // Draws an edge to canvas
    public void drawEdge(Edge edge, Graphics g, Color color) {
        g.setColor(color); // Sets color to param color
        int drawXPos = (edge.vertex1.xPosition * SCALE) + CORNER_OFFSET + (SIZE / 2); // Calculates xPosition based on vertex1 x
        int drawYPos = (edge.vertex1.yPosition * SCALE) + CORNER_OFFSET + (SIZE / 2);  // Calculates yPosition based on vertex1 y 
        int drawXPos2 = (edge.followEdge().xPosition * SCALE) + CORNER_OFFSET + (SIZE / 2); // Calculates xPosition based on vertex2 x 
        int drawYPos2 = (edge.followEdge().yPosition * SCALE) + CORNER_OFFSET + (SIZE / 2); // Calculates yPosition based on vertex2 y
        g.drawLine(drawXPos, drawYPos, drawXPos2, drawYPos2); // Draws edge
    }

}
