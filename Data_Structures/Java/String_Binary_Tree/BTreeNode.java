package lab08;

/**
 *
 * @author Brad Kivell (20115449)
 */
class BTreeNode {

    String data;
    BTreeNode parent; // Stores reference to the parent node for traversal
    BTreeNode left; // Stores reference to the left node for traversal
    BTreeNode right; // Stores reference to the right node for traversal

    // Returns the standard comparator of String type (Alphabetical order)
    public int compareTo(BTreeNode node) {
        return data.compareTo(node.data);
    }

    // Returns a string containing the data stored in this node
    public String toString() {
        return "Node: " + data
                + " Parent: " + (parent == null ? "null" : parent.data)
                + " Left: " + (left == null ? "null" : left.data)
                + " Right: " + (right == null ? "null" : right.data);
    }

}
