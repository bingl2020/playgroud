package Tree;
/*
            1
         2      3
     4      5      6
                        7


 */

import java.util.ArrayList;
import java.util.List;

public class LowestCommonAncestorOfDeepestLeaves {

    public TreeNode lcaDeepestLeaves(TreeNode root) {
        return helper(root, 0).node;
    }

    public Result helper(TreeNode root, int depth) {
        if (root == null) {
            return new Result(null, -1);
        }

        Result left = helper(root.left, depth + 1);
        Result right = helper(root.right, depth + 1);

        if (left.depth > right.depth) {
            return new Result(left.node, left.depth);
        } else if (left.depth < right.depth) {
            return new Result(right.node, right.depth);
        }

        return new Result(root, depth);
    }

    public static void main(String[] args) {

        List<Integer> test = new ArrayList<>();
        test.add(100);
        test.add(101);
        test.add(102);
        test.add(103);

        test.remove(1);



        TreeNode root = new TreeNode(1);

        root.left = new TreeNode(2);
        root.right = new TreeNode(3);

        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
       new LowestCommonAncestorOfDeepestLeaves().lcaDeepestLeaves(root);
    }

}

class Result {
    TreeNode node;
    int depth;

    Result(TreeNode node, int depth) {
        this.node = node;
        this.depth = depth;
    }

}