/**
 *
 * @author Brad Kivell (20115449)
 */
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.Font;

public class ShipPanel extends JPanel implements KeyListener {

    // Local Variables
    public int shipCount;
    public boolean running;
    public int crashCount;

    // Objects
    public Port port;
    public Ship[] ships;

    // Images
    public Image shipIMG;
    public Image portWithBoatIMG;
    public Image portIMG;

    // CONSTRUCTOR
    public ShipPanel() {
        this.shipCount = 20; // Number of ships
        this.crashCount = 0;
        this.ships = new Ship[this.shipCount]; // Ship array of size shipCount
        this.port = new Port(900, 500); // Create port object

        for (int i = 0; i < this.shipCount; i++) {
            this.ships[i] = new Ship(20, i * 45, this.port); // Create ships
        }

        // Set images
        this.shipIMG = new ImageIcon("boat.png").getImage();
        this.portIMG = new ImageIcon("land.png").getImage();
        this.portWithBoatIMG = new ImageIcon("boat_land.png").getImage();

        // Setup panel
        this.addKeyListener(this);
        this.setFocusable(true);
        this.setBackground(Color.CYAN);
        this.running = false;
    }

    // PAINT COMPONENTS
    @Override
    public void paintComponent(final Graphics g) {
        // Setup
        super.paintComponent(g);
        g.setFont(new Font("Serif", 2, 18));

        // Display options at start
        if (!running) {
            g.drawString("Press Spacebar to start in synchronized mode", 250, 250);
            g.drawString("Press any other key to start in non synchronized mode", 250, 275);
        }
        else{
             g.drawString("Crashed Ship Count: " + this.crashCount, 750, 50);
        }

        // Update port image if ship is at port
        if (Ship.shipAtPort) {
            g.drawImage(this.portWithBoatIMG, this.port.xPos, this.port.yPos - 26, this);
        } else { // Port with no ship
            g.drawImage(this.portIMG, this.port.xPos, this.port.yPos - 26, this);
        }

        // Check for crash
        for (int i = 0; i < this.ships.length; i++) {
            if (this.ships[i].shipActive) {
                for (int j = 0; j < this.ships.length; j++) {
                    // Check if ship is active & if position equals another ship
                    if (this.ships[j].shipActive && j != i && this.ships[j].xPos == this.ships[i].xPos && this.ships[j].yPos == this.ships[i].yPos) {
                        if (!this.ships[i].crashed) { // Check if ship has crashed to prevent spam 
                            this.ships[i].crashed = true;
                            g.drawString("CRASH!!!", 250, 250);
                            this.crashCount++;
                        }
                    }
                }
            }
        }

        // Draw ships which haven't moved to port
        for (int i = 0; i < this.ships.length; i++) {
            if (!this.ships[i].shipArrived) {
                g.drawImage(this.shipIMG, this.ships[i].xPos, this.ships[i].yPos, this);
            }
        }

        // Repaint panel
        this.repaint();
    }

    // Key press to start
    @Override
    public void keyPressed(final KeyEvent key) {
        if (!this.running) {
            for (int i = 0; i < this.ships.length; i++) {
                if (key.getKeyCode() == KeyEvent.VK_SPACE) { // synchronizedStatus = true if space key pressed
                    this.ships[i].synchronizedStatus = true;
                    this.ships[i].start();
                } else {
                    this.ships[i].synchronizedStatus = false; // synchronizedStatus = false if other key pressed
                    this.ships[i].start();
                }
            }
            this.running = true;
        }
    }

    // Interface Methods
    @Override
    public void keyTyped(final KeyEvent key) {
    }

    @Override
    public void keyReleased(final KeyEvent key) {
    }
}
