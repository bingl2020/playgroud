package Graph;

import java.util.*;

/*
There are N network nodes, labelled 1 to N.

Given times, a list of travel times as directed edges times[i] = (u, v, w), where u is the source node, v is the target node, and w is the time it takes for a signal to travel from source to target.

Now, we send a signal from a certain node K. How long will it take for all nodes to receive the signal? If it is impossible, return -1.

Solution: shortest path
1. Dijkstra's T O(n^2)  -> O(NlogN + E)     Space O(N + E)      not support negative     single source shortest path
2. Bellman-Ford         T O(NE) -> o        Space O(N)          dense not good
3. Floyd-Warshall(All paris)   T O(N^3)     Space O(N^2)       任意连点  杀鸡用牛刀 slow  good for hundreds nodes.



 */
public class NetworkDelayTimes {

    // relax all edge a =gain, if we stil get lesser distance -> negative weight cycle
    public int networkDelayTime_Bellman_Ford(int[][] times, int N, int K) {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[K] = 0;
        for (int i = 1; i < N; i++) {
            for (int[] edge : times) {
                if (dist[edge[0]] != Integer.MAX_VALUE ) {
                    dist[edge[1]] = Math.min(dist[edge[1]], dist[edge[0]] + edge[2]);
                }
            }
        }

        int res = 0;
        for (int i = 1; i <= N; i++) {
            res = Math.max(res, dist[i]);
        }

        return res == Integer.MAX_VALUE ? -1 : res;
    }

    // negative weight cycle, check values on diagonal of distance matrix if negative
    // check for every pair of vertices, do we get a shorter distance by going thru k
    public int networkDelayTime_Floyd_Warshall(int[][] times, int N, int K) {
        int[][] dist = new int[N + 1][N + 1];
        // initialization
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                dist[i][j] = i == j ? 0 : Integer.MAX_VALUE;
            }
        }
        for (int[] edge : times) {
            dist[edge[0]][edge[1]] = edge[2];
        }
        // calculate all pairs shortest path
        for (int k = 1; k <= N; k++) {
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= N; j++) {
                    if (dist[i][k] != Integer.MAX_VALUE && dist[k][j] != Integer.MAX_VALUE) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
        }

        // calculate shortest path from K to all other nodes
        int maxTime = 0;
        for (int i = 1; i <= N; i++) {
            if (dist[K][i] == Integer.MAX_VALUE) {
                return -1; // i is not reachable from K
            }
            maxTime = Math.max(maxTime, dist[K][i]);
        }

        return maxTime;
    }

    public int networkDelayTime_dijkstra(int[][] times, int N, int K) {
        Map<Integer, List<int[]>> adj = buildAdjacencyList(times);

        int[] dist = new int[N + 1];
        boolean[] visited = new boolean[N + 1];

        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[K] = 0;
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]); // index
        minHeap.offer(new int[]{0, K});
        int max = 0;

        while (!minHeap.isEmpty()) {
            int[] curr = minHeap.poll();
            int currNode = curr[1];
            int currDis = curr[0];

            if (visited[currNode]) {
                continue;
            }
            visited[currNode] = true;
            max = currDis;
            N--;

            if (!adj.containsKey(currNode)) {
                continue;
            }

            for (int[] next : adj.get(currNode)) {
                if (!visited[next[1]] && currDis + next[2] < dist[next[1]]) {
                    minHeap.offer(new int[]{currDis + next[2], next[1]});
                }
            }
        }

        return N == 0 ? max : -1;
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

    public static void main(String[] args) {
//        int[][] times = {{2, 1, 1}, {2, 3, 1}, {3, 4, 1}};
        int[][] times = {
                {1, 3, 4},
                {1, 2, 2},
                {2, 4, 7},
                {2, 3, 1},
                {3, 5, 3},
                {4, 6, 1},
                {5, 4, 2},
                {5, 6, 5}};

        NetworkDelayTimes test = new NetworkDelayTimes();


        test.networkDelayTime_dijkstra(times, 6, 2);

    }
}
