package Graph;

import java.util.ArrayList;
import java.util.List;

//323 Number of Connected Components in an Undirected Graph
//https://protegejj.gitbook.io/algorithm-practice/leetcode/union-find/323-number-of-connected-components-in-an-undirected-graph
/*
1. Question
Givennnodes labeled from0ton - 1and a list of undirected edges (each edge is a pair of nodes), write a function to find the number of connected components in an undirected graph.
 */
public class NumberOfConnectedComponents {

    public int countComponents_unionFind(int n, int[][] edges) {
        if (n <= 1) {
            return n;
        }

        int[] sets = new int[n];
        int[] rank = new int[n];

        for (int i = 0; i < n; i++) {
            sets[i] = i;
            rank[i] = 0;
        }

        for (int[] edge : edges) {

        }

        return 0;

    }

    private void union(int[] sets, int[] rank, int i, int j) {
        int p1 = find(i, sets);
        int p2 = find(j, sets);

        if (p1 == p2) {
            return;
        }

        if (rank[p1] < rank[p2]) {
            sets[p1] = sets[p2];
            rank[p2] += rank[p1];
        } else if (rank[p1] > rank[p2]){
            sets[p2] = sets[p1];
            rank[p1] += rank[p2];
        } else {
            sets[p1] = sets[p2];
            rank[p2]++;
        }
    }

    private int find(int node, int[] parent) {
        if (parent[node] == node) {
            return node;
        }
        return parent[node] = find(parent[node], parent);
    }

    public int countComponents_dfs(int n, int[][] edges) {
        if (n <= 1) {
            return n;
        }
        // build adjacency list
        List<Integer>[] adj = buildAjacencyList(n, edges);

        boolean[] visited = new boolean[n];
        int count = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(adj, visited, i);
                count++;
            }
        }
        return count;
    }

    private void dfs(List<Integer>[] adj, boolean[] visited, int node) {
        if (visited[node]) {
            return;
        }

        visited[node] = true;
        for (int neighbor : adj[node]) {
            if (!visited[neighbor]) {
                dfs(adj, visited, neighbor);
            }
        }
    }

    private List<Integer>[] buildAjacencyList(int n, int[][] edges) {
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
}