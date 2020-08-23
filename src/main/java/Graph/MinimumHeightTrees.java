package Graph;

import java.util.*;

/*
https://leetcode.com/problems/minimum-height-trees/
For an undirected graph with tree characteristics, we can choose any node as the root. The result graph is then a rooted tree. Among all possible rooted trees, those with minimum height are called minimum height trees (MHTs). Given such a graph, write a function to find all the MHTs and return a list of their root labels.

Format
 */
public class MinimumHeightTrees {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n <= 0) return new ArrayList<>();

        List<Integer>[] adj = buildAjacencyList(n, edges);
        int[] pre = new int[n];

        int u = bfs(n, adj, pre, 0);
        int v = bfs(n, adj, pre, u);

        List<Integer> path = new ArrayList<>();

        while (v != -1) {
            path.add(v);
            v = pre[v];
        }

        if (path.size() % 2 == 0) {
            return Arrays.asList(path.get(path.size() / 2 - 1),
                    path.get(path.size() / 2));
        } else {
            return Arrays.asList(path.get(path.size() / 2));
        }

    }

    List<Integer>[] buildAjacencyList(int n, int[][] edges) {
        List<Integer>[] adj = new List[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }

        return adj;
    }


    private int bfs(int n, List<Integer>[] adj, int[] pre, int start) {
//        int[] dist = new int[n];
        boolean[] visited = new boolean[n];

        Deque<Integer> queue = new ArrayDeque<>();
        queue.add(start);
        visited[start] = true;
        pre[start] = -1;

        int res = start;
        while (!queue.isEmpty()) {
            res = queue.poll();
            for (int v : adj[res]) {
                if (!visited[v]) {
                    visited[v] = true;
                    queue.add(v);
//                    dist[v] = dist[res] + 1;
                    pre[v] = res;
                }
            }
        }

        return res;
    }

    public static void main(String[] args) {
//        new MinimumHeightTrees().findMinHeightTrees(4, new int[][]{{1, 0}, {1, 2}, {1, 3}});
        new MinimumHeightTrees().findMinHeightTrees(3, new int[][]{{0, 1}, {0, 2}});
    }
}
