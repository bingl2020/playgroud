package Graph.MST;

import java.util.Arrays;

public class MinCostToConnectAllNodes_UnionFind {

    public static void main(String[] args) {

        int tc1 = new MinCostToConnectAllNodes_UnionFind().minCostToConnect(
                6, new int[][]{{1, 4}, {4, 5}, {2, 3}},
                new int[][]{{1, 2, 5}, {1, 3, 10}, {1, 6, 2}, {5, 6, 5}});
        if (tc1 == 7) {
            System.out.println("All Test Case Pases!");
        } else {
            System.out.println("There are test failures!");
        }
    }

    public int minCostToConnect(int n, int[][] edges, int[][] newEdges) {
        int[] parents = new int[n + 1];
        int connected = n, minCost = 0;
        for (int i = 0; i <= n; i++) {
            parents[i] = i;
        }

        // union and find out num of disjoint sets
        for (int[] edge : edges) {
            if (this.union(edge[0], edge[1], parents)) {
                connected--;
            }
        }

        Arrays.sort(newEdges, (a, b) -> a[1] - b[1]);

        // add new edge
        for (int[] newEdge : newEdges) {
            if (this.union(newEdge[0], newEdge[1], parents)) {
                minCost += newEdge[2];
                connected--;
            }
            if (connected == 1) {
                return minCost;
            }
        }
        return connected == 1 ? connected : -1;
    }

    private boolean union(int x, int y, int[] parents) {
        int setX = find(x, parents);
        int setY = find(y, parents);
        if (setX != setY) {
            parents[setY] = setX;
            return true;
        }
        return false;
    }

    private int find(int num, int[] parents) {
        if (parents[num] != num) {
            parents[num] = find(parents[num], parents);
        }
        return parents[num];
    }
}
