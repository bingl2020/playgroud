package Tree;

import java.util.Arrays;

/*
https://www.techiedelight.com/fix-binary-tree-one-swap-bst/
 */
public class BSTOneSwap {

    public Node insert(Node root, int key) {
        if (root == null) {
            return new Node(key);
        }

        if (key < root.key) {
            root.left = insert(root.left, key);
        } else {
            root.right = insert(root.right, key);
        }

        return root;
    }

    public void inorder(Node root) {
        if (root == null) {
            return;
        }

        inorder(root.left);
        System.out.print(root.key + " -> ");
        inorder(root.right);
    }

    public void swapData(Node first, Node second)
    {
        int data = first.key;
        first.key = second.key;
        second.key = data;
    }
    public void correctBST(Node root, NodeWrapper x, NodeWrapper y,
                                  NodeWrapper prev)
    {
        // base case
        if (root == null) {
            return;
        }

        // recur for left subtree
        correctBST(root.left, x, y, prev);

        // if current node is less than the previous node
        if (root.key < prev.node.key)
        {
            // if this is first occurrence, update x and y to previous node
            // and current node respectively
            if (x.node == null) {
                x.node = prev.node;
            } else {
                y.node = root;
            }

            // if this is second occurrence, update y to current node

        }

        // update previous node and recur for right subtree
        prev.node = root;
        correctBST(root.right, x, y, prev);
    }

    // Fix given binary tree that is only one swap away from becoming a BST
    public void correctBST(Node root)
    {
        // Wrap x, y, prev nodes so their reference can be changed
        // inside the correctBST() method

        // x and y stores node to be swapped
        NodeWrapper x = new NodeWrapper();
        NodeWrapper y = new NodeWrapper();

        // stores previous processed node in in-order traversal
        // initialize it by minus infinity
        NodeWrapper prev = new NodeWrapper(Integer.MIN_VALUE);

        // fix the binary tree
        correctBST(root, x, y, prev);

        // swap the nodes
        if (x.node != null && y.node != null ) {
            swapData(x.node, y.node);
        }
    }

    public void test(Integer x) {
        x = 10;
    }

    public static void main(String[] args) {
        Node root = null;
        int[] keys = {15, 10, 20, 8, 12, 16, 25};
        BSTOneSwap bstOneSwap = new BSTOneSwap();

        for (int key : keys) {
            root = bstOneSwap.insert(root, key);
        }

        bstOneSwap.swapData(root.left.left, root.right.right);
        bstOneSwap.inorder(root);
        System.out.println("");
        bstOneSwap.correct(root);

        bstOneSwap.correctBST(root);

        bstOneSwap.inorder(root);
    }


    public void correct(Node root) {

    }
}



class NodeWrapper {
    Node node;

    NodeWrapper() {
    }

    NodeWrapper(Node node) {
        this.node = node;
    }

    NodeWrapper(int key) {
        this.node = new Node(key);
    }
}