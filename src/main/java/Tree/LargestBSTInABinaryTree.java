package Tree;

public class LargestBSTInABinaryTree {

    class ResultType {
        Integer max, min, size;
        boolean isBST;

        ResultType(boolean isBST, Integer min, Integer max, Integer size) {
            this.isBST = isBST;
            this.min = min;
            this.max = max;
            this.size = size;
        }
    }

    public int findTheSizeOfLargetBST_bottomUp(Node root) {
        return findLargetBST(root).size;
    }

    private ResultType findLargetBST(Node root) {
        if (root == null) {
            return new ResultType(true, Integer.MAX_VALUE, Integer.MIN_VALUE, 0);
        }

        ResultType left = findLargetBST(root.left);
        ResultType right = findLargetBST(root.right);

        if (left.isBST && right.isBST && root.key > left.max && root.key < right.min) {
            return new ResultType(true, Math.min(left.min, root.key), Math.max(root.key, right.max), left.size + right.size + 1);
        } else {
            return new ResultType(false, 0, 0, Math.max(left.size, right.size));
        }

    }

    // o{n2}
    public int findTheSizeOfLargetBST(Node root) {
        /*
         */
        if (isBST(root, Integer.MIN_VALUE, Integer.MAX_VALUE)) {
            return height(root);
        }

        return Math.max(findTheSizeOfLargetBST(root.left), findTheSizeOfLargetBST(root.right));
    }

    private boolean isBST(Node node, int min, int max) {
        if (node == null) {
            return true;
        }
        if (node.key < min || node.key > max) {
            return false;
        }

        return isBST(node.left, min, node.key) && isBST(node.right, node.key, max);
    }

    private int height(Node root) {
        if (root == null) {
            return 0;
        }

        return Math.max(height(root.left), height(root.right)) + 1;
    }


    public static void main(String[] args) {
   /* Construct below binary tree
                      10
                    /    \
                   /      \
                  15       8
                 / \      / \
                /   \    /   \
               12   20  5     9
              / \      / \     \
             /   \    /   \     \
            2    14  4    7     10
        */

        Node root = new Node(10);

        root.left = new Node(15);
        root.right = new Node(8);

        root.left.left = new Node(12);
        root.left.right = new Node(20);
        root.right.left = new Node(5);
        root.right.right = new Node(9);

        root.left.left.left = new Node(2);
        root.left.left.right = new Node(14);
        root.right.left.left = new Node(4);
        root.right.left.right = new Node(7);

        root.right.right.right = new Node(10);
        LargestBSTInABinaryTree test = new LargestBSTInABinaryTree();

        System.out.println("The size of the largest BST is " + test.findTheSizeOfLargetBST_bottomUp(root));
    }
}
