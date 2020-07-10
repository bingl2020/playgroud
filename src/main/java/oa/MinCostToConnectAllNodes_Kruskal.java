package oa;

import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/*
Prim's Algorithm
1. Randomly choose a vertex, 2. choose the shortest/cheapest edge from this vertex 3. choose the nearest/cheapest vertex not yet in the solution
r. 4 repeat until form a MST
Kruskal's
1. choose the shortest/cheapest 2. choose next shortest edge wgucg wouldn't create a cycle, repeat until form a MST

Kruskal time complexity worst case is O(E log E),this because we need to sort the edges.
Prim time complexity worst case is O(E log V) with priority queue or even better, O(E+V log V) with Fibonacci Heap.
We should use Kruskal when the graph is sparse, i.e.small number of edges,like E=O(V),when the edges are already sorted or if we can sort them in linear time.
We should use Prim when the graph is dense, i.e number of edges is high ,like E=O(V²).

 */
public class MinCostToConnectAllNodes_Kruskal {
    int[] parent;
    int component;
    public int getRepairCost(int n, int[][] edges, int[][] repairEdges) {
        component = n;
        parent = new int[n + 1];
        for(int i = 0; i < n + 1; i++) parent[i] = i;

        Set<Integer> set = new HashSet<>();
        for(int[] edge: repairEdges) {
            int[] e = new int[]{edge[0], edge[1]};
            int hash = Arrays.hashCode(e);
            set.add(hash);
        }

        for(int[] edge: edges) {
            int[] e = new int[]{edge[0], edge[1]};
            int hash = Arrays.hashCode(e);
            if(set.contains(hash)) {
                int i = 0;
                continue;
            }
            if(find(edge[0]) != find(edge[1])) {
                union(edge[0], edge[1]);
                component --;
            }
        }
        Arrays.sort(repairEdges, (e1, e2) -> e1[2] - e2[2]);
        int cost = 0;
        for(int[] edge: repairEdges) {
            if(find(edge[0]) != find(edge[1])) {
                union(edge[0], edge[1]);
                cost += edge[2];
                component --;
            }
        }
        return component == 1 ? cost : -1;
    }

    public int getCost(int n, int[][] edges, int[][] newEdges) {
        component = n;
        parent = new int[n + 1];
        for(int i = 0; i < n + 1; i++) parent[i] = i;
        for(int[] edge: edges) {
            if(find(edge[0]) != find(edge[1])) {
                union(edge[0], edge[1]);
                component --;
            }
        }
        Arrays.sort(newEdges, (e1, e2) -> e1[2] - e2[2]);
        int cost = 0;
        for(int[] edge: newEdges) {
            if(find(edge[0]) != find(edge[1])) {
                union(edge[0], edge[1]);
                cost += edge[2];
                component --;
            }
        }
        return component == 1 ? cost : -1;
    }

    private int find(int x) {
        if(parent[x] != x) parent[x] = find(parent[x]);
        return parent[x];
     }

    private void union(int x, int y) {
        parent[find(y)] = find(x);
    }

    public static void main(String[] args) {
        test(5, new int[][]{{1, 2}, {2, 3}, {3, 4}, {4, 5}, {1, 5}}, new int[][]{{1, 2, 12}, {3, 4, 30}, {1, 5 ,8}}, true); // Output: 20
        test(6, new int[][]{{1, 2}, {2, 3}, {4, 5}, {3, 5}, {1, 6}, {2, 4}}, new int[][]{{1, 6, 410}, {2, 4, 800}}, true); // Output: 410
        test(6, new int[][]{{1, 2}, {2, 3}, {4, 5}, {5, 6}, {1, 5}, {2, 4}, {3, 4}}, new int[][]{{1, 5, 110}, {2, 4, 84}, {3, 4, 79}}, true); // Output: 79
        test(6, new int[][]{{1, 4}, {4, 5}, {2, 3}}, new int[][]{{1, 2, 5}, {1, 3, 10}, {1, 6, 2}, {5, 6, 5}}, true); // Output: 7

        test(5, new int[][]{{1, 2}, {2, 3}, {3, 4}, {4, 5}, {1, 5}}, new int[][]{{1, 2, 12}, {3, 4, 30}, {1, 5 ,8}}, false); // Output: 20
        test(6, new int[][]{{1, 2}, {2, 3}, {4, 5}, {3, 5}, {1, 6}, {2, 4}}, new int[][]{{1, 6, 410}, {2, 4, 800}}, false); // Output: 410
        test(6, new int[][]{{1, 2}, {2, 3}, {4, 5}, {5, 6}, {1, 5}, {2, 4}, {3, 4}}, new int[][]{{1, 5, 110}, {2, 4, 84}, {3, 4, 79}}, false); // Output: 79
        test(6, new int[][]{{1, 4}, {4, 5}, {2, 3}}, new int[][]{{1, 2, 5}, {1, 3, 10}, {1, 6, 2}, {5, 6, 5}}, false); // Output: 7

    }

    private static void test(int n, int[][] edges, int[][] newEdges, boolean isRepair) {
        if(isRepair) {
            int cost = new MinCostToConnectAllNodes_Kruskal().getRepairCost(n, edges, newEdges);
            System.out.println("Min repair cost: " + cost);
        } else {
            int cost = new MinCostToConnectAllNodes_Kruskal().getCost(n, edges, newEdges);
            System.out.println("Min cost: " + cost);
        }
    }
}
