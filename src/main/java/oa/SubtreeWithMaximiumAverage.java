package oa;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SubtreeWithMaximiumAverage {

    static class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    double max;
    Node maxNode;

    public Node getMaximumAverage(Node root) {
        maxNode = null;
        max = Double.MIN_VALUE;

        helper(root);
        return maxNode;
    }

    public double[] helper(Node root) {
        if (root == null) return new double[]{0, 0};
        double count = 1;
        double sum = root.val;

        if (root.children != null) {
            for (Node child : root.children) {
                double[] cur = helper(child);
                sum += cur[0];
                count += cur[1];
            }
        }
        double average = sum / count;
        if (count > 1 && average > max) {
            max = average;
            maxNode = root;
        }
        return new double[]{sum, count};
    }

    public static void main(String[] args) {
        // Input:
        //              20
        //            /   \
        //          12     18
        //       /  |  \   / \
        //     11   2   3 15  8
        Node left = new Node(12);
        left.children = Arrays.asList(new Node(11), new Node(2), new Node(3));

        Node right = new Node(18);
        right.children = Arrays.asList(new Node(15), new Node(8));

        Node root = new Node(20);
        root.children = Arrays.asList(left, right);

        test(root); // output: 18
    }

    private static void test(Node root) {

        System.out.println("A".compareToIgnoreCase("a"));
        Node maxNode = new SubtreeWithMaximiumAverage().getMaximumAverage(root);
        System.out.println("Max Average: " + maxNode.val);

        Node maxNode2 = new SubtreeWithMaximiumAverage().findSubtreeWithMaximiumAverage(root);
        System.out.println("Max Average: " + maxNode2.val);
    }


    public Node findSubtreeWithMaximiumAverage(Node root) {
        return helper2(root).max;
    }


    private ResultType helper2(Node root) {

        if (root == null) {
            return new ResultType(null, 0, 0, null, 0);
        }

        List<ResultType> childrenRes = new ArrayList<>();
        double sum = root.val;
        int count = 1;

        if (root.children != null) {
            for (Node child : root.children) {
                ResultType childRes = helper2(child);
                childrenRes.add(childRes);
                count += childRes.numOfNodes;
                sum += childRes.sum;
            }
        }

        ResultType max = new ResultType(root, count, sum, root, sum / count);
        for (ResultType childRes : childrenRes) {
            if (childRes.numOfNodes > 1 && childRes.maxAvg > max.maxAvg) {
                max = childRes;
            }
        }

        return max;
    }


    class ResultType {
        Node node;
        int numOfNodes;
        double sum;
        Node max;
        double maxAvg;


        ResultType(Node node, int numOfNodes, double sum, Node max, double maxAvg) {
            this.node = node;
            this.numOfNodes = numOfNodes;
            this.max = max;
            this.maxAvg = maxAvg;
        }
    }


    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();

        if (matrix == null || matrix.length == 0) {
            return res;
        }

        int colBegin = 0, colEnd = matrix.length - 1, rowBegin = 0, rowEnd = matrix[0].length - 1;

        while (rowBegin <= rowEnd && colBegin <= colEnd) {
            for (int i = colBegin; i <= colEnd; i++) {
                res.add(matrix[rowBegin][i]);
            }
            rowBegin++;
            for (int i = rowBegin; i <= rowEnd; i++) {
                res.add(matrix[i][colEnd]);
            }
            colEnd--;
            if (rowBegin <= rowEnd) {
                for (int i = colEnd; i >= colBegin; i--) {
                    res.add(matrix[rowEnd][i]);
                }
            }
            rowEnd--;

            if (colBegin <= colEnd) {
                for (int i = rowEnd; i >= rowBegin; i--) {
                    res.add(matrix[i][colBegin]);
                }
            }
            colBegin++;

        }

        return res;
    }

}
