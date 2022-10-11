package lab08;
/**
 *
 * @author Brad Kivell
 */
public class StringBinaryTree {

    BTreeNode root; // Stores root of tree

    // Default constructor
    public StringBinaryTree() {    }

    // Takes an array of string as input and generates a tree
    public StringBinaryTree(String[] list) {
        for (int i = 0; i < list.length; i++) {
            add(list[i]);
        }
    }

    // Adds String parameter to the tree using encapsulated private method
    public void add(String data) {
        if (root == null) {
            root = new BTreeNode();
            root.data = data;
        } else {
            BTreeNode newNode = new BTreeNode();
            newNode.data = data;
            add(root, newNode);
        }
    }

    // Encapsulated add method adds node in order
    private void add(BTreeNode root, BTreeNode newNode) {
        if (root.compareTo(newNode) > 0) {
            if (root.left != null) {
                add(root.left, newNode);
            } else {
                root.left = newNode;
            }
        } else {
            if (root.right != null) {
                add(root.right, newNode);
            } else {
                root.right = newNode;
            }
        }
    }
    
    public void linkParent() {
        linkParent(root);
    }

    // Creates link from child nodes to parent
    private BTreeNode linkParent(BTreeNode root) {
        BTreeNode child;
        if (root.left != null) {
            child = root.left;
            child.parent = root;
            linkParent(child);
        }
        if (root.right != null) {
            child = root.right;
            child.parent = root;
            linkParent(child);
        }
        return root;
    }

    public void traverse() {
        traverse(root);
    }

    // Traverses tree printing out as it goes
    private void traverse(BTreeNode root) {
        if (root.left != null) {
            traverse(root.left);
        }
        System.out.println(root);
        if (root.right != null) {
            traverse(root.right);
        }
    }

    public static void main(String[] args) {
        String[] elements = "cow, fly, dog, bat, fox, cat, eel, ant".split(", ");
        StringBinaryTree btree = new StringBinaryTree(elements);
        btree.traverse();

        System.out.println();
        btree.linkParent();
        btree.traverse();
    }

}
