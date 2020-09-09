package Tree;
/*
inorder predecessor: is node with maximum value in its left subtree,(right-most child)
    if its left subtree doesn't exist, predecessor is one of the ancestors,
        which is the node where we take last right turn.
inorder successor: is the node with minimum value in its right subtree (left-most child)
    if its right subtree doesn't exist, successor is one of the ancestors,
        which is the node where we take last left turn.



 */
public class BST_Predecessor_Successor {
    Node root;

    Node findInorderPredecessor(Node node, int key) {

        Node predesc = null;
        while (node != null) {
            if (key < node.key) {
                node = node.left;
            } else if (key > node.key) {
                predesc = node;
                node = node.right;
            } else { // found
                if (node.left != null) {
                    predesc = maxmiumNode(node.left);
                }
                break;
            }
        }

        return predesc;
    }

    Node findInorderSuccessor(Node node, int key) {
        Node successor = null;
        while (node != null) {
            if (key < node.key) {
                successor = node;
                node = node.left;
            } else if (key > node.key) {
                node = node.right;
            } else { // found
                if (node.right != null) {
                    successor = minimumNode(node.right);
                }
                break;
            }
        }

        return successor;
    }



    Node insert(Node node, int val) {
        if (node == null) {
            return new Node(val);
        } else {
            if (val < node.key) {
                node.left = insert(node.left, val);
            } else {
                node.right = insert(node.right, val);
            }
        }

        return node;
    }

    Node minimumNode(Node node) {
        if (node == null) {
            return null;
        }
        while (node.left != null) {
            node = node.left;
        }

        return node;
    }

    Node minimumNode(Node node, Node parent) {

        if (node == null) {
            return parent;
        }

        return minimumNode(node.left, node);
    }

    Node findPredecessorRec(Node node, int key) {
        if (node == null) {
            return null;
        }

        while(true) {
            if (key < node.key) {
                node = node.left;
            } else if (key > node.key) {
                node = node.right;
            }
        }
    }


    Node findInOrder(Node node, int key) {

        if (node == null) {
            return null;
        }
        if (key < node.key) {
            return findInOrder(node.left, key);
        } else {
            return findInOrder(node.right, key);
        }
    }

    private Node maxmiumNode(Node node) {
        if (node == null) {
            return null;
        }
        while (node.right != null) {
            node = node.right;
        }

        return node;
    }

    public static void main(String[] args) {
        BST_Predecessor_Successor tree = new BST_Predecessor_Successor();
        int[] keys = { 15, 10, 20, 8, 12, 16, 25 };

        for (int key : keys) {
            tree.root = tree.insert(tree.root, key);
        }

        System.out.println("findInorderPredecessor is " + tree.findInorderPredecessor(tree.root, 16).key);
        System.out.println("findInorderSuccessor is " + tree.findInorderSuccessor(tree.root, 16).key);


    }


}
