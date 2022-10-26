import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author Brad Kivell
 */
public class MazeFileLoader {

    public int linkers, columns, rows; // Stores data about size of maze
    public MazeGraph mazeGraph; // Stores mazeGraph object

    public MazeFileLoader() {
        mazeGraph = new MazeGraph(); // Initialises maze
    }

    // Reads file at input path
    public void readFile(String fileName) {
        int lineIndex = 1;
        File file = new File(fileName); // Creates reference to file
        try {
            Scanner fileScanner = new Scanner(file); // Creates scanner to read lines
            while (fileScanner.hasNextLine()) { // While not at the end of file
                String line = fileScanner.nextLine(); // Stores line
                String[] splitString = line.split(","); // Splits string from comma
                if (lineIndex == 1) // Reading maze header data
                {
                    linkers = Integer.parseInt(splitString[0]); // Stores number of edges
                    columns = Integer.parseInt(splitString[1]); // Stores number of columns
                    rows = Integer.parseInt(splitString[2]); // Stores number of rows
                    lineIndex++;
                }
                else if (lineIndex != 1) // Reading vertex data
                {
                    // Stores vertex as new vertex
                    Vertex newVertex = new Vertex(splitString[0], Integer.parseInt(splitString[1]), Integer.parseInt(splitString[2]), splitString[3], splitString[4]);
                    mazeGraph.addVertex(newVertex); // Adds vertex to maze
                    System.out.println("Added: " + newVertex.name);
                }
            }
            mazeGraph.linkEdges(); // Links maze edges once data is in
            mazeGraph.findPath(); // Calculates maze path
            fileScanner.close(); // Closes file scanner
        } catch (IOException e) {
            System.out.println("Failed to read file:  " + e.getMessage());
        }
    }
}
