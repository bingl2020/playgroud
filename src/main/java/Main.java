import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

// Data structure to store a Binary Tree node
class Node
{
    int data;
    Node left, right;

    Node(int data) {
        this.data = data;
    }
}

class Main {
    // Function to print level order traversal of given binary tree
    public static void levelOrderTraversal(Node root) {
        if (root == null) {
            return;
        }

        // create an empty queue and enqueue root node
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(root);

        // pointer to store current node
        Node curr = null;

        // run till queue is not empty
        while (!queue.isEmpty()) {
            // process each node in queue and enqueue their
            // non-empty left and right child to queue
            curr = queue.poll();

            System.out.print(curr.data + " ");

            if (curr.left != null) {
                queue.add(curr.left);
            }

            if (curr.right != null) {
                queue.add(curr.right);
            }
        }
    }

    // Recursive function to store nodes of odd levels in a stack using
    // inorder traversal
    public static void pushOddLevelNodes(Node root, Stack<Integer> s, boolean level) {
        // Base case
        if (root == null) {
            return;
        }

        // store nodes in the left subtree
        pushOddLevelNodes(root.left, s, !level);

        // push current node's data into the stack only if level is odd
        if (level) {
            s.add(root.data);
        }

        // store nodes in the right subtree
        pushOddLevelNodes(root.right, s, !level);
    }

    // Recursive function to invert alternate levels of a perfect binary tree
    // using inorder traversal
    public static void invertBinaryTree(Node root, Stack<Integer> s, boolean level) {
        // Base case
        if (root == null) {
            return;
        }

        // invert nodes in the left subtree
        invertBinaryTree(root.left, s, !level);

        // if level is odd
        if (level) {
            // pop element from the stack and assign it to the current node
            root.data = s.pop();
        }

        // invert nodes in the right subtree
        invertBinaryTree(root.right, s, !level);
    }

    // Invert alternate levels of a perfect binary tree
    public static void invertBinaryTree(Node root) {
        // create a stack and push nodes of odd levels in it
        Stack<Integer> s = new Stack<>();
        pushOddLevelNodes(root, s, false);

        // put nodes of odd levels at their correct position using stack
        invertBinaryTree(root, s, false);
    }

}