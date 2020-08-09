package Graph.ShortestPath;

import java.util.*;

/*
https://www.techiedelight.com/cost-of-shortest-path-in-dag-using-one-pass-of-bellman-ford/
Find Cost of Shortest Path in DAG using one pass of Bellman-Ford

topological sort of a DAG is a linear ordering of its vertices, for every edge uv, u comes before v.

We can use topological sort to solve this problem. When we consider a vertex u in topological order,
it is guaranteed that we have considered every incoming edge to it. for each vertex in the topological order,
we relex cost of its outgoing edges. (update shortest path information). In
 */
public class DAG_SSSP {

    private void dijkstra(Graph graph, int source, int N) {
        int[] dist = new int[N];
        boolean[] visited = new boolean[N];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;

        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]); // 0 -> wight 1 -> v
        minHeap.offer(new int[] {0, source});

        while (!minHeap.isEmpty()) {
            int[] curr = minHeap.poll();
            int u = curr[1];
            int currDist = curr[0];

            if (visited[u]) {
                continue;
            }

            visited[u] = true;
            for (Edge edge : graph.adjList.get(u)) {
                if (!visited[edge.dest] && dist[u] + edge.weight < dist[edge.dest]) {
                    dist[edge.dest] = dist[u] + edge.weight;
                    minHeap.offer(new int[]{dist[edge.dest], edge.dest});
                }
            }
        }

        for (int i = 0; i < N - 1; i++) {
            System.out.printf("dist(%d, %d) = %2d\n", source, i, dist[i]);
        }

    }

    private void dfs2(Graph graph, int source, boolean[] visited, List<Integer> order) {
        visited[source] = true;

        for (Edge edge : graph.adjList.get(source)) {
            int v = edge.dest;
            if (!visited[v]) {
                dfs2(graph, v, visited, order);
            }
        }

        order.add(source);
    }

    public void findShortestDistance2(Graph graph, int source, int N) {
        List<Integer> order = new ArrayList<>();
        boolean[] visited = new boolean[N];

        for (int i = 0; i < N; i++) {
            if (!visited[i]) {
                dfs2(graph, i, visited, order);
            }
        }

        Collections.reverse(order);
        int[] dist = new int[N];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;


        for (int u : order) {
            for (Edge e : graph.adjList.get(u)) {
                int v = e.dest;
                int w = e.weight;

                if (dist[u] != Integer.MAX_VALUE && dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                }
            }
        }

        for (int i = 0; i < N - 1; i++) {
            System.out.printf("dist(%d, %d) = %2d\n", source, i, dist[i]);
        }

    }


    public static void main(String[] args) {
        // List of graph edges as per above diagram
        List<Edge> edges = Arrays.asList(
                new Edge(0, 6, 2), new Edge(1, 2, -4),
                new Edge(1, 4, 1), new Edge(1, 6, 8),
                new Edge(3, 0, 3), new Edge(3, 4, 5),
                new Edge(5, 1, 2), new Edge(7, 0, 6),
                new Edge(7, 1, -1), new Edge(7, 3, 4),
                new Edge(7, 5, -4)
        );

        // Set number of vertices in the graph
        final int N = 8;

        // create a graph from given edges
        Graph graph = new Graph(edges, N);

        // source vertex
        int source = 3;

        // find Shortest distance of all vertices from given source
        new DAG_SSSP().findShortestDistance2(graph, source, N);
        System.out.println();
        new DAG_SSSP().findShortestDistance(graph, source, N);
        System.out.println();
        new DAG_SSSP().dijkstra(graph, source, N);

    }

    private static int DFS(Graph graph, int v, boolean[] discovered,
                           int[] departure, int time) {
        // mark current node as discovered
        discovered[v] = true;

        // set arrival time - not needed
        // time++;

        // do for every edge (v -> u)
        for (Edge edge : graph.adjList.get(v)) {
            int u = edge.dest;

            // u is not discovered
            if (!discovered[u]) {
                time = DFS(graph, u, discovered, departure, time);
            }
        }

        // ready to backtrack
        // set departure time of vertex v
        departure[time] = v;
        time++;

        return time;
    }

    // The function performs topological sort on a given DAG and then finds out
    // the longest distance of all vertices from given source by running one pass
    // of Bellman-Ford algorithm on edges of vertices in topological order
    public static void findShortestDistance(Graph graph, int source, int N) {
        // departure[] stores the vertex number using departure time as index
        int[] departure = new int[N];
        Arrays.fill(departure, -1);

        // stores vertex is discovered or not
        boolean[] discovered = new boolean[N];
        int time = 0;

        // perform DFS on all undiscovered vertices
        for (int i = 0; i < N; i++) {
            if (!discovered[i]) {
                time = DFS(graph, i, discovered, departure, time);
            }
        }

        int[] cost = new int[N];
        Arrays.fill(cost, Integer.MAX_VALUE);

        cost[source] = 0;

        // Process the vertices in topological order i.e. in order
        // of their decreasing departure time in DFS
        for (int i = N - 1; i >= 0; i--) {
            // for each vertex in topological order,
            // relax cost of its adjacent vertices
            int v = departure[i];
            for (Edge e : graph.adjList.get(v)) {
                // edge e from v to u having weight w
                int u = e.dest;
                int w = e.weight;

                // if the distance to the destination u can be shortened by
                // taking the edge vu, update cost to the new lower value
                if (cost[v] != Integer.MAX_VALUE && cost[v] + w < cost[u]) {
                    cost[u] = cost[v] + w;
                }
            }
        }

        // print shortest paths
        for (int i = 0; i < N - 1; i++) {
            System.out.printf("dist(%d, %d) = %2d\n", source, i, cost[i]);
        }
    }

}

class Edge {
    int source, dest, weight;

    public Edge(int source, int dest, int weight) {
        this.source = source;
        this.dest = dest;
        this.weight = weight;
    }
}

class Graph {
    // A List of Lists to represent an adjacency list
    List<List<Edge>> adjList = null;

    // Constructor
    Graph(List<Edge> edges, int N) {
        adjList = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            adjList.add(new ArrayList<>());
        }

        // add edges to the undirected graph
        for (Edge edge : edges) {
            adjList.get(edge.source).add(edge);
        }
    }
}
