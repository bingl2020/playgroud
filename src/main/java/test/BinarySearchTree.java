package test;

public class BinarySearchTree {

    private Node root;

    public static void main(String[] args) {
        BinarySearchTree test = new BinarySearchTree();
        test.buildBST(new int[]{4, 6, 7, 3, 7, 9,12,45});
        System.out.println(" ");
        test.inorder();

    }

    private void buildBST(int[] values) {
        if (values == null || values.length == 0) {
            this.root = null;
            return;
        }

        for (int i : values) {
            this.root = insertRec(this.root, i);
        }
    }

    private Node insertRec(Node node, int val) {
        if (node == null) {
            return new Node(val);
        }

        if (val < node.val) {
            node.left = insertRec(node.left, val);
        } else {
            node.right = insertRec(node.right, val);
        }

        return node;
    }

    private void inorder() {
       inorderRec(this.root);
    }

    private void inorderRec(Node node) {
        if (node == null) {
            return;
        }

        inorderRec(node.left);
        System.out.print(" " + node.val);
        inorderRec(node.right);
    }


    class Node {
        int val;
        Node left, right;

        Node(int val) {
            this.val = val;
        }
    }
}
