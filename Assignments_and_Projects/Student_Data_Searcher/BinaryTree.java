/**
 *
 * @author Brad Kivell
 * @param <E>
 */
public class BinaryTree<E extends Comparable> {

    private Node root;
    private E foundNode;
    public int size;

    public void add(E data) {
        Node newNode = new Node(data);
        if (this.root == null) { // Binary tree is empty
            this.root = newNode;
        } else { // Root already contains node
            addNode(this.root, newNode);
        }
    }

    // Encapsulated add method adds node in order
    private void addNode(Node root, Node newNode) {
        int comparison = newNode.compareTo(root); // Compare newNode to root
        if (comparison < 0) { // If new node is smaller than root
            if (root.left != null) { // If root node has no left child
                addNode(root.left, newNode);
            } else {
                root.left = newNode;
            }
        } else if (comparison > 0) { // If new node is larger than root
            if (root.right != null) { // If root node has no right child
                addNode(root.right, newNode);
            } else {
                root.right = newNode;
            }
        } else { // New node is equal to root
            // Node already added in tree
        }
    }

    public E findNode(E data) {
        Node newNode = new Node(data);
        if (this.root == null) { // Binary tree is empty
            System.out.println("Tree contains no data");
            return null;
        } else { // Root already contains node
            findNode(this.root, newNode); // Recursively calls findnode methd
            if (data.compareTo(foundNode) == 0) { // Checks if  data is equal to node data
                return foundNode;
            } else {
                return null;
            }
        }
    }

    // Encapsulated find method finds node recursivley
    private void findNode(Node root, Node newNode) {
        int comparison = newNode.compareTo(root); // Compare newNode to root
        if (comparison < 0) { // If new node is smaller than root
            if (root.left != null) { // If root node has no left child
                findNode(root.left, newNode);
            }
        } else if (comparison > 0) { // If new node is larger than root
            if (root.right != null) { // If root node has no right child
                findNode(root.right, newNode);
            }
        } else if (comparison == 0) { // New node is equal to root
            foundNode = (E) root.data; // Sets found node
        }
    }

    public void reverseOrder(Node root) {
        if (this.root == null) { // Check if tree is empty
            return;
        }

        Node tempNode = root.left; // Switch left and right nodes
        root.left = root.right;
        root.right = tempNode;

        if (root.left != null) { // Recursivey call with left & right node as new root
            reverseOrder(root.left);
        }
        if (root.right != null) {
            reverseOrder(root.right);
        }

    }

    // Traverses and prints out nodes
    public void traversal(Node root) {
        if (root.left != null) {
            traversal(root.left);
        }
        System.out.println(root);
        if (root.right != null) {
            traversal(root.right);
        }
    }

    // Returns binary tree as single string
    public String convertToString() {
        System.out.println(convertToString(root));
        return convertToString(root);
    }

    private String convertToString(Node root) {
        String result = "";
        if (root == null) { // Checks tree isnt empty
            return "";
        }
        if (root.left != null) { // Checks left child isnt empty
            result += (convertToString(root.left)); // Adds left node string
        }
        result += (root.data.toString() + "\n");
        if (root.right != null) { // Checks right child isnt empty
            result += (convertToString(root.right)); // Adds right node string
        }

        return result;
    }

}
