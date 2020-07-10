package Graph.TopologicalOrder.DAG;

import java.util.*;

public class CourseScheduleII {

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // construct adjacency list
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int[] edge : prerequisites) {
            map.put(edge[1], new ArrayList<>());
        }
        for (int[] edge : prerequisites) {
            map.get(edge[1]).add(edge[0]);
        }

        // process all the nodes to find ordering
        Deque<Integer> stack = new ArrayDeque<>();
        int[] visited = new int[numCourses]; // 0 unvisited, 1 visiting on recursive stack  2 visited
        for (int course = 0; course < numCourses; course++) {
            if (visited[course] == 0) {
                if (dfs(map, course, visited, stack)) {
                    return new int[0];
                }
            }
        }

        // create the topological ordering.
        int[] res = new int[numCourses];
        int i = 0;
        while (!stack.isEmpty()) {
            res[i++] = stack.pop();
        }

        return res;
    }

    private boolean dfs(Map<Integer, List<Integer>> map, int course, int[] visited, Deque<Integer> stack) {// true = cycle
        if (visited[course] == 2) {
            return false;
        }

        if (visited[course] == 1) {
            return true; // detect cycle
        }

        visited[course] = 1;
        if (map.get(course) != null) {
            for (int neighbor : map.get(course)) {
                if (dfs(map, neighbor, visited, stack)) {
                    return true;
                }
            }
        }
        visited[course] = 2;
        stack.push(course);

        return false;
    }

    public static void main(String[] args) {
        CourseScheduleII test = new CourseScheduleII();
        test.findOrder(2, new int[][]{{1, 0}});
    }
}
