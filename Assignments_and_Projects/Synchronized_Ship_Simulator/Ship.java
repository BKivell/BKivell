import java.util.Random;

/**
 *
 * @author Brad Kivell (20115449)
 */
public class Ship extends Thread {

    // Global variables
    public static boolean shipAtPort;
    public static final int MAX_DELAY = 1000;
    // Instance variables
    public int xPos;
    public int yPos;
    public int departDelay;
    public boolean crashed;
    public boolean shipArrived;
    public boolean shipActive;
    public boolean synchronizedStatus;
    public Port port;
    public Random rand;

    // Constructor
    public Ship(int x, int y, Port port) {
        this.xPos = x;
        this.yPos = y;
        this.port = port;
        this.crashed = false;
        this.shipActive = false;
        this.shipArrived = false;
        this.synchronizedStatus = false;
        // Set delay to random float
        rand = new Random();
        this.departDelay = rand.nextInt(MAX_DELAY);
    }

    // THREAD RUN
    @Override
    public void run() {
        // Decrease depart delay until 0
        while (this.departDelay > 0) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException ex) {
                System.out.println("InterruptedException");
            }
            this.departDelay--;
        }
        // Check for synchronizedStatus
        if (this.synchronizedStatus) {
            synchronized (this.port) {
                while (this.port.shipAtPort) { // Wait while ship is at port
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException ex) {
                        System.out.println("InterruptedException");
                    }
                }
                this.port.shipAtPort = true; // Set ship at port to true and start
            }
        } else { // Not synchronizeds
            while (this.port.shipAtPort) { // Wait while ship is at port
                try {
                    Thread.sleep(1);
                } catch (InterruptedException ex) {
                    System.out.println("InterruptedException");
                }
            }
            this.port.shipAtPort = true;
        }
        // Move ship
        this.shipActive = true;
        while (this.xPos != this.port.xPos || this.yPos != this.port.yPos) { // If x or y is not equal to port
            if (this.yPos < this.port.yPos) { // Increase yPos if below port
                this.yPos++;
            }
            if (this.yPos > this.port.yPos) { // Decrease yPos if above port
                this.yPos--;
            }
            if (this.xPos < this.port.xPos) { // Increase xPos if not at port
                this.xPos++;
            }
            try {
                Thread.sleep(1); // Sleep for 1ms to slow movement
            } catch (InterruptedException ex) {
                System.out.println("InterruptedException");
            }
        }
        // Shp arrived
        Ship.shipAtPort = true;
        this.shipActive = false;
        try {
            Thread.sleep(500); // Wait for 0.5 seconds at the port
        } catch (InterruptedException ex) {
            System.out.println("InterruptedException");
        }

        // Reset variables to allow for another ship
        this.port.shipAtPort = false;
        Ship.shipAtPort = false;
        this.shipArrived = true;
    }
}
