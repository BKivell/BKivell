/**
 * @author Brad Kivell (20115449)
 * @param <E> Queue implementation using linked list
 */
public class Queue<E> extends LinkedList<E> {

    private LinkedList<E> queue = new LinkedList();

    public void Queue() {
    }

    // Adds element to queue
    public void enqueue(E data) {
        this.queue.add(data);
    }

    // Returns the first item in queue
    public E dequeue() {
        E temp = null;
        if (queue.head.data != null) {
            temp = (E) queue.head.data;
            this.queue.removeFromHead();
            return temp;
        } else {
            return temp;
        }
    }

    // Returns number of elements as integer
    public int getSize() {
        return this.queue.size;
    }

    // Prints elements to console
    public void printQueue() {
        queue.printLinkedList();
    }

}
