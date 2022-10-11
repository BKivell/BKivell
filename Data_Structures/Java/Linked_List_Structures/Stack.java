/**
 * @author Brad Kivell (20115449)
 *
 * Stack implementation using Linked List
 */
public class Stack<E> extends LinkedList<E> {

    private LinkedList<E> stack = new LinkedList();

    public void Stack() {
    }

    // Adds element to stack
    public void push(E data) {
        this.stack.add(data);
    }

    // Returns the element on the top of the stack
    public E pop() {
        E temp = null;
        if (this.stack.getNode(this.stack.size - 1) != null) {
            temp = (E) stack.getNode(this.stack.size - 1).data;
            this.stack.removeFromTail();
            return temp;
        } else {
            return temp;
        }
    }

    // Returns number of elements as integer
    public int getSize() {
        return this.stack.size;
    }

    // Prints elements to console
    public void printStack() {
        this.stack.printLinkedList();
    }
}
