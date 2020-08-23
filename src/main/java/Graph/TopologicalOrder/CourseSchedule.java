package Graph.TopologicalOrder;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class CourseSchedule {

    public boolean canFinish_kahns(int numCourses, int[][] prerequisites) {
        List<Integer>[] adj = buildAdjacencyList(numCourses, prerequisites);
        // calculate indegree
        int[] indegree = new int[numCourses];
        for (int[] pair : prerequisites) {
            indegree[pair[1]]++;
        }

        Deque<Integer> queue = new ArrayDeque<>();
        for (int course = 0; course < numCourses; course++) {
            if (indegree[course] == 0) {
                queue.offer(course);
            }
        }

        int count = 0;
        while (!queue.isEmpty()) {
            int course = queue.poll();

            for (int neighbor : adj[course]) {
                indegree[neighbor]--;
                if (indegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
            count++;
        }

        return count == numCourses;
    }

    public boolean canFinish_dfs(int numCourses, int[][] prerequisites) {

        List<Integer>[] adj = buildAdjacencyList(numCourses, prerequisites);

        int[] visisted = new int[numCourses];

        for (int course = 0; course < numCourses; course++) {
            if (hasCycle(adj, course, visisted)) {
                return false;
            }
        }

        return true;
    }

    private boolean hasCycle(List<Integer>[] adj, int course, int[] visited) {   // 0 unvisited, 1 visiting on rec stack, 2 visited
        if (visited[course] == 2) {
            return false;
        }

        if (visited[course] == 1) {
            return true;
        }

        visited[course] = 1;

        for (int neighbour : adj[course]) {
            if (hasCycle(adj, neighbour, visited)) {
                return true;
            }
        }
        visited[course] = 2;
        return false;
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

    public static void main(String[] args) {
        new CourseSchedule().canFinish_dfs(2, new int[][]{{1, 0}, {0, 1}});

    }
}
