package Graph.MST;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
https://leetcode.com/problems/number-of-operations-to-make-network-connected/
https://leetcode.com/problems/number-of-operations-to-make-network-connected/discuss/477660/Java-Count-number-of-connected-components-Clean-code
 */
public class NumberOfOperationsToMakeNetworkConnected {

    public int makeConnected_union_find_path_compression(int n, int[][] connections) {

        if (connections.length < n - 1) {
            return -1;
        }

        int[] parent = new int[n];

        for(int i = 0; i < n; i++) {
            parent[i] = i;
        }

        int m = connections.length;
        int components = 0;
        int extraEdge = 0;
        for(int i = 0; i < m; i++) {
            int p1 = findParent(parent, connections[i][0]);
            int p2 = findParent(parent, connections[i][1]);
            if(p1 == p2) {
                extraEdge++;
            } else {
                parent[p1] = p2;
            }
        }
        for(int i = 0; i < n; i++) {
            if(parent[i] == i) components++;
        }
        return (extraEdge >= components - 1) ? components - 1 : -1;
    }

    public int makeConnected_unionFind_pathCompression_UnionByrank(int n, int[][] connections) {
        if (connections.length < n -1) {
            return -1;
        }

        int[] parent = new int[n];
        int[] rank = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }

        int components = n;
        for (int[] c : connections) {
            int u = findParent(parent, c[0]);
            int v = findParent(parent, c[1]);

            if (u != v) {
                if (rank[u] < rank[v]) {
                    parent[u] = v;
                } else {
                    parent[v] = u;
                    if (rank[u] == rank[v]) {
                        rank[v]++;
                    }
                }
                components--;
            }
        }

        return components - 1;
    }

    public static int findParent(int[] par, int i) {
        if(par[i] == i) return i;
        return par[i] = findParent(par, par[i]);
    }

    public int makeConnected_dfs(int n, int[][] connections) {
        if (connections.length < n - 1) {
            return -1; // To connect, need at least n - 1 edges.
        }
        List<Integer>[] adj = buildAdjacencyList(n, connections);

        int components = 0;
        boolean[] visited = new boolean[n];

        for (int u = 0; u < n; u++) {
            components += dfs(u, adj, visited);
        }

        return components - 1;
    }

    private int dfs (int u, List<Integer>[] adj, boolean[] visited) {
        if (visited[u]) {
            return 0;
        }
        visited[u] = true;
        for (int v : adj[u]) {
            dfs(v, adj, visited);
        }

        return 1;
    }

    private Map<Integer, List<int[]>> buildAdjacencyList(int[][] times) {
        Map<Integer, List<int[]>> adj = new HashMap<>();

        for (int[] edge : times) {
            adj.putIfAbsent(edge[0], new ArrayList<>());
//            adj.get(edge[0]).add(new int[] {edge[1], edge[2]});
            adj.get(edge[0]).add(edge);
        }
        return adj;
    }

    private List<Integer>[] buildAdjacencyList(int numCourses, int[][] prerequisites) {

        List<Integer>[] adj = new List[numCourses];

        for (int i = 0; i < numCourses; i++) {
            adj[i] = new ArrayList<>();
        }
        for (int[] pair : prerequisites) {
            adj[pair[1]].add(pair[0]);
        }

        return adj;
    }
}
