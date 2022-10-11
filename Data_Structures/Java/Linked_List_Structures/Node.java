/**
 * @author Brad Kivell (20115449)
 * @param <E>
 */
public class Node<E extends Comparable<E>> {

    public E data;
    public Node next;

    public Node() {
        this.data = null;
        this.next = null;
    }

    // Returns true if this node and parameter are equal, false if not
    public boolean equals(Node n) {
        return (this.data == n.data);
    }

    // Compares nodes data
    public int compareTo(Node n) {
        return this.data.compareTo((E) n.data);
    }
}
