package Graph.MST;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/*
https://leetcode.com/discuss/interview-question/357310
 */

/*
Prim's algorithm is a greedy algorithm that finds a minimum spanning tree for a weighted undirected graph.
This mean it finds a subset of the edges that forms a tree that includes every vertex, where the total weight
of all the edges in the tree is minimized. Thre algorithm operates by building this tree one vertex at a time,
form an arbitrary starting vertex, at each step adding the cheapest possible connection from the tree to another vertex.

the idea to maintain two sets of vertices. Thre first set contains the vertices already included in the MST, the other set contains the vertices not yet included.
At every step, it considers all the edges tht connect the two sets, and pick the minimum weight edge from these edges.
After picking the edge, it moves the other endpoint of the edge to the set containing MST.

The idea behind Prim's algorithm is simple, a spanning tree means all vertices must ve connected. So the two disjoint subsets of vertices must be connected to make a
spanning tree.
 */
public class MinCostToRepairEdges {
    public static void main(String[] args) {

        int n1 = 5;
        int[][] edges1 = {{1, 2}, {2, 3}, {3, 4}, {4, 5}, {1, 5}}, edgesToRepair1 = {{1, 2, 12}, {3, 4, 30}, {1, 5, 8}};
        System.out.println("Min Cost 1a: " + minCost_prims(n1, edges1, edgesToRepair1));
        System.out.println("-------------------------------------------------");
        int n2 = 6;
        int[][] edges2 = {{1, 2}, {2, 3}, {4, 5}, {3, 5}, {1, 6}, {2, 4}}, edgesToRepair2 = {{1, 6, 410}, {2, 4, 800}};
        System.out.println("Min Cost 2a: " + minCost_prims(n2, edges2, edgesToRepair2));
        System.out.println("-------------------------------------------------");
        int n3 = 6;
        int[][] edges3 = {{1, 2}, {2, 3}, {4, 5}, {5, 6}, {1, 5}, {2, 4}, {3, 4}}, edgesToRepair3 = {{1, 5, 110}, {2, 4, 84}, {3, 4, 79}};
        System.out.println("Min Cost 3a: " + minCost_prims(n3, edges3, edgesToRepair3));

    }

    private static int minCost_prims(int n, int[][] edges, int[][] newEdges) {
        String to = "->";
        int res = 0;

        Map<String, Integer> graph = new HashMap<>();
        //MST
        //adjacency List
        for (int[] edge : edges) {
            graph.put(edge[0] + to + edge[1], 0);
            graph.put(edge[1] + to + edge[0], 0);
        }


        for (int[] edge : newEdges) {
            graph.put(edge[0] + to + edge[1], edge[2]);
            graph.put(edge[1] + to + edge[0], edge[2]);
        }

        int[] dist = new int[n + 1];
        boolean[] visited = new boolean[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[1] = 0;
        visited[1] = true;
        for (int i = 1; i < dist.length; i++) {
            if (graph.containsKey(1 + to + i)) {
                dist[i] = Math.min(dist[i], graph.get(1 + to + i));
            }
            if (graph.containsKey(i + to + 1)) {
                dist[i] = Math.min(dist[i], graph.get(i + to + 1));
            }
        }
        for (int cnt = 0; cnt < n - 1; cnt++) {
            int[] next = getMin(dist, visited);
            int p = next[0];
            int v = next[1];
            if (p != -1) {
                visited[p] = true;
                dist[p] = v;
                for (int i = 1; i < dist.length; i++) {
                    String key = p + to + i;
                    if (!visited[i] && graph.containsKey(key) && graph.get(key) < dist[i]) {
                        dist[i] = graph.get(key);
                    }
                }
            }
        }
        for (int i = 1; i < dist.length; i++) {
            if (dist[i] == Integer.MAX_VALUE)
                return -1;
            res += dist[i];
        }
        return res;
    }



    static int[] getMin(int[] dist, boolean[] visited) {
        int[] res = new int[2];
        res[0] = -1;
        res[1] = Integer.MAX_VALUE;
        for (int i = 1; i < dist.length; i++) {
            if (!visited[i]) {
                if (dist[i] < res[1]) {
                    res[0] = i;
                    res[1] = dist[i];
                }
            }
        }
        return res;
    }
}
