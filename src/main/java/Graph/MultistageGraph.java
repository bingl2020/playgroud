package Graph;

//https://www.geeksforgeeks.org/multistage-graph-shortest-path/
public class MultistageGraph {
    static int INF = Integer.MAX_VALUE;
    static int N = 8;
    // Returns shortest distance from 0 to N-1.
    public static int shortestDist(int[][] graph) {

        int n = N;
        int[] dist = new int[n];
        dist[n - 1] = 0;
        int[] d = new int[n];
        // calculate shorest path for the rest of nodes
        for (int i = n - 2; i >= 0; i-- ) {
            dist[i] = Integer.MAX_VALUE;

            // check all the nodes in next stage, and calculate min distance for i
            for (int j = i; j < n; j++) {
                if (graph[i][j] != Integer.MAX_VALUE) {
                    dist[i] = Math.min(dist[i], graph[i][j] + dist[j]);
                }
            }
        }

        return dist[0];
    }



    public static void main(String[] args)
    {
        // Graph stored in the form of an
        // adjacency Matrix
        int[][] graph = new int[][]{
                {INF, 1, 2, 5, INF, INF, INF, INF},
                {INF, INF, INF, INF, 4, 11, INF, INF},
                {INF, INF, INF, INF, 9, 5, 16, INF},
                {INF, INF, INF, INF, INF, INF, 2, INF},
                {INF, INF, INF, INF, INF, INF, INF, 18},
                {INF, INF, INF, INF, INF, INF, INF, 13},
                {INF, INF, INF, INF, INF, INF, INF, 2}};

        System.out.println(shortestDist(graph));
    }


}
