/**
 *
 * @author Brad Kivell (20115449)
 */
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.JFrame;

public class Main {
    
    // Main method
    public static void main(final String[] args) {
        JFrame frame = new JFrame("Port Simulator"); // Generate Frame
        ShipPanel panel = new ShipPanel(); // Generate panel
        frame.add(panel); // Add panel to frame
        frame.setSize(1000, 1000); // Set size of frame to 1000x1000
        frame.setVisible(true); // Sets frame to be visible
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE); // Set exit on close
    }
    
}