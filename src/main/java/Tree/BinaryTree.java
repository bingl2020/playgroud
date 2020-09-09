package Tree;


import java.util.*;

public class BinaryTree {

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();

        if (root == null) return res;

        Stack<TreeNode> stack = new Stack<>();

        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            if (curr != null) {
                stack.push(curr);
                curr = curr.left;
            } else {
                curr = stack.pop();
                res.add(curr.val);
                curr = curr.right;
            }
        }

        return res;
    }

    public List<Integer> postorderTraversal(TreeNode root) {
        // write your code here
        List<Integer> res = new ArrayList<>();

        Deque<TreeNode> stack = new ArrayDeque<>();

        TreeNode curr = root;
        while (curr != null || !stack.isEmpty()) {
            // travese left substree
            if (curr != null) {
                stack.push(curr);
                curr = curr.left;
            } else {
                TreeNode temp = stack.peek().right;
                if (temp != null) {
                    curr = temp;
                } else {
                    do {
                        temp = stack.pop();
                        res.add(temp.val);
                    } while (!stack.isEmpty() && temp == stack.peek().right);
                }
            }
        }

        return res;
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();

        if (root == null) return res;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();

            res.add(node.val);
            if (node.right != null) {
                stack.push(node.right);
            }

            if (node.left != null) {
                stack.push(node.left);
            }

        }

        return res;
    }



    private Result helper(TreeNode root) {
        if (root == null) {
            return new Result(0, Integer.MAX_VALUE, null);
        }

        Result left = helper(root.left);
        Result right = helper(root.right);

        int sum = left.sum + right.sum + root.val;

        Result result = new Result(sum, sum, root);

        if (left.min <= result.min) {
            result.min = left.min;
            result.minNode = left.minNode;
        }

        if (right.min <= result.min) {
            result.min = right.min;
            result.minNode = right.minNode;
        }
        return result;
    }


    class Result {
        int sum;
        int min;
        TreeNode minNode;

        Result(int sum, int min, TreeNode minNode) {
            this.sum = sum;
            this.min = min;
            this.minNode = minNode;
        }
    }
}