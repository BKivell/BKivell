/**
 * @author Brad Kivell (20115449)
 * @param <E>
 *
 * Linked list data structure
 */
public class LinkedList<E> {

    public int size;
    Node head;

    public LinkedList() {
        this.size = 0;
        this.head = null;
    }

    // Adds element to end of linked list
    public void add(E data) {
        Node current;
        Node n = new Node();
        n.data = (Comparable) data;
        if (head == null) {
            head = n;
        } else {
            current = this.head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = n;
        }
        size++;
    }

    //  Adds in ascending numerical order if the generic object is a number. It follows 
    //  alphabetical order if the generic object is a char or String.
    public void addInOrder(E data) {
        Node current;
        Node insertNode = new Node();
        boolean added = false;
        insertNode.data = (Comparable) data; 
        if (head == null) {
            head = insertNode;
        } else {
            if (head.compareTo(insertNode) > 0)
            {
                insertNode.next = this.head;
                this.head = insertNode;
            } else { // Larger than head
                current = this.head;
                while (current.next != null && added == false) {
                    if (current.next.compareTo(insertNode) > 0)
                    {
                        insertNode.next = current.next;
                        current.next = insertNode;
                        added = true;
                    }
                    current = current.next;
                }
                if (added == false) {
                    current.next = insertNode;
                }
            }
        }
        size++;
    }

    // Returns true if list contains paramater node
    public boolean contains(Node node) {
        if (head != null) {
            Node current = this.head;
            while (current != null) {
                if (current.data.equals(node.data)) {
                    return true;
                }
                current = current.next;
            }
        }
        return false;
    }

    // Prints linked list to console
    public void printLinkedList() {
        if (this.size > 0) {
            Node current = this.head;
            while (current.next != null) {
                System.out.print(current.data + ", ");
                current = current.next;
            }
        }
        System.out.println("Finished Printing");
    }

    // Removes node equal to parameter node
    public void remove(Node node) {
        if (this.head.data.equals(node.data)) {
            this.head = this.head.next;
            size--;
        } else {
            Node current = this.head;
            while (current.next != null) {
                if (current.next.data.equals(node.data)) {
                    current.next = current.next.next;
                    size--;
                } else {
                    current = current.next;
                }
            }
        }
    }

    // Removes node from head of linked list
    public void removeFromHead() {
        if (this.head.next != null) {
            this.head = this.head.next;
            this.size--;
        } else if (this.head.next == null) {
            this.head = null;
            size--;
        }
    }

    // Removes node from tail of linked list
    public void removeFromTail() {
        if (this.size > 0) {
            if (this.head.next == null) {
                removeFromHead();
            } else if (this.head.next.next == null) {
                this.head.next = null;
                this.size--;
            } else {
                Node current = this.head;
                while (current.next != null) {
                    current = current.next;
                }
                current.next = null;
                this.size--;
            }
        }
    }

    // Returns ith node
    public Node getNode(int i) {
        Node current = null;
        if (i < this.size && i >= 0) {
            current = this.head;
            for (int j = 0; j < this.size; j++) {
                if (i == j) {
                    return current;
                }
                current = current.next;
            }
        }
        return current;
    }
}
