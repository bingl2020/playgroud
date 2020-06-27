package test;

import java.util.ArrayDeque;
import java.util.Deque;

class BinaryTree {
    /* This is the core function to convert Tree to list. This function
       follows steps 1 and 2 of the above algorithm */
    Node root;

    Node convertBinaryTree(Node node) {
        if (node == null) {
            return null;
        }

        Deque<Node> stack = new ArrayDeque<>();
        Node dummy = new Node(0);
        Node curr = dummy;

        while (node != null || !stack.isEmpty()) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else {
                Node top = stack.pop();
                curr.right = top;
                top.left = curr;
                curr = curr.right;
                node = top.right;
            }
        }

        curr.right = null;
        dummy.right.left = null;

        return dummy.right;
    }


    Node convertBinaryTreeToDoubleLinkedList(Node node) {
        return helper(node).head;
    }

    private ResultType helper(Node node) {
        if (node == null) {
            return null;
        }

        ResultType left = helper(node.left);
        ResultType right = helper(node.right);

        ResultType res = new ResultType(null, null);

        if (left != null) {
            left.tail.right = node;
            node.left = left.tail;

            res.head = left.head;
        } else {
            node.left = null;
            res.head = node;
        }

        if (right != null) {
            right.head.left = node;
            node.right = right.head;

            res.tail = right.head;
        } else {
            node.right = null;
            res.tail = node;
        }


        return res;
    }

    Node bintree2listUtil(Node node) {
        // Base case 
        if (node == null)
            return node;

        // Convert the left subtree and link to root 
        if (node.left != null) {
            // Convert the left subtree 
            Node left = bintree2listUtil(node.left);

            // Find inorder predecessor. After this loop, left 
            // will point to the inorder predecessor 
            for (; left.right != null; left = left.right) ;

            // Make root as next of the predecessor 
            left.right = node;

            // Make predecssor as previous of root 
            node.left = left;
        }

        // Convert the right subtree and link to root 
        if (node.right != null) {
            // Convert the right subtree 
            Node right = bintree2listUtil(node.right);

            // Find inorder successor. After this loop, right 
            // will point to the inorder successor 
            for (; right.left != null; right = right.left) ;

            // Make root as previous of successor 
            right.left = node;

            // Make successor as next of root 
            node.right = right;
        }

        return node;
    }

    // The main function that first calls bintree2listUtil(), then follows 
    // step 3 of the above algorithm 

    Node bintree2list(Node node) {
        // Base case 
        if (node == null)
            return node;

        // Convert to DLL using bintree2listUtil() 
        node = bintree2listUtil(node);

        // bintree2listUtil() returns root node of the converted 
        // DLL.  We need pointer to the leftmost node which is 
        // head of the constructed DLL, so move to the leftmost node 
        while (node.left != null)
            node = node.left;

        return node;
    }

    /* Function to print nodes in a given doubly linked list */
    void printList(Node node) {
        while (node != null) {
            System.out.print(node.data + " ");
            node = node.right;
        }
    }

    /* Driver program to test above functions*/
    public static void main(String[] args) {
        BinaryTree tree = new BinaryTree();

        // Let us create the tree shown in above diagram 
        tree.root = new Node(10);
        tree.root.left = new Node(12);
        tree.root.right = new Node(15);
        tree.root.left.left = new Node(25);
        tree.root.left.right = new Node(30);
        tree.root.right.left = new Node(36);

        // Convert to DLL 
        Node head = tree.bintree2list(tree.root);

        Node head2 = tree.convertBinaryTree(tree.root);

        // Print the converted list 
        tree.printList(head);

        tree.printList(head2);

    }
}

class ResultType {
    Node head, tail;

    ResultType(Node head, Node tail) {
        this.head = head;
        this.tail = tail;
    }
}

class Node {
    int data;
    Node left, right;

    Node(int item) {
        data = item;
        left = right = null;
    }
}