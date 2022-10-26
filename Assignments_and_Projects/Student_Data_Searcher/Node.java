/**
 *
 * @author Brad Kivell
 * @param <E>
 */
public class Node<E extends Comparable> implements Comparable<Node> {

    public E data;
    public Node left; // Stores reference to left child
    public Node right; // Stores reference to right child

    // Initializes node data
    public Node(E data) {
        this.data = data;
    }

    @Override
    public int compareTo(Node node) {
        return this.data.compareTo(node.data); // Compares node data to node data
    }

    @Override
    public String toString() {
        return this.data.toString(); // Returns this data as string
    }

}
