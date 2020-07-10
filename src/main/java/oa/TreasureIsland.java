package oa;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TreasureIsland {

    private final int[][] direction = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};

    public static int treasureIsland(char[][] island) {
        if (island == null || island.length == 0) return 0;

        int steps = 0;
        Queue<Coordinate> queue = new LinkedList<>();
        queue.add(new Coordinate(0, 0));
        boolean[][] visited = new boolean[island.length][island[0].length];
        visited[0][0] = true;
        int[][] dirs = new int[][]{{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

        // bfs
        while (!queue.isEmpty()) {

            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Coordinate coordinate = queue.poll();
                int x = coordinate.x;
                int y = coordinate.y;
                if (island[x][y] == 'X') return steps;

                for (int[] dir : dirs) {
                    int newX = x + dir[0];
                    int newY = y + dir[1];

                    if (newX >= 0 && newX < island.length && newY >= 0 && newY < island[0].length &&
                            island[newX][newY] != 'D' && !visited[newX][newY]) {
                        queue.add(new Coordinate(newX, newY));
                        visited[newX][newY] = true;
                    }
                }
            }
            steps++;
        }

        return 0;


    }

    public static void main(String[] args) {

        char[][] island = new char[][]{
                {'O', 'O', 'O', 'O'},
                {'D', 'O', 'D', 'O'},
                {'O', 'O', 'O', 'O'},
                {'X', 'D', 'D', 'O'}
        };
        int result = TreasureIsland.treasureIsland(island);
        System.out.println(String.format("%s (expect 5)", result));

        result = new TreasureIsland().findShortestRouteDFS(island);
        System.out.println(String.format("%s (expect 5)", result));
    }

    static class Coordinate {
        int x;
        int y;

        Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }


    public int findShortestRouteDFS(char[][] island) {
        if (island == null) {
            return -1;
        }
        int[] res = new int[]{Integer.MAX_VALUE};

        List<List<int[]>> allPaths = new ArrayList<>();
        List<int[]> shortestPath = new ArrayList<>();

        dfs(island, 0, 0, new boolean[island.length][island[0].length], 0, res,
                new ArrayList<>(), allPaths, shortestPath);

        return res[0];
    }

    public void dfs(char[][] grid, int i, int j, boolean[][] visited, int levelDistance, int[] shortestDistance,
                    List<int[]> path, List<List<int[]>> allPaths, List<int[]> shortestPath) {

        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] == 'D' || visited[i][j]) {
            return;
        }

        if (grid[i][j] == 'X') {

            if (shortestDistance[0] > levelDistance) {
                shortestDistance[0] = levelDistance;
                shortestPath.clear();
                shortestPath.addAll(path);
            }
            allPaths.add(new ArrayList<>(path));
            return;
        }

        visited[i][j] = true;

        for (int[] step : direction) {
            int nextX = i + step[0];
            int nextY = j + step[1];
            path.add(new int[]{nextX, nextY});
            dfs(grid, nextX, nextY, visited, levelDistance + 1, shortestDistance,
                    path, allPaths, shortestPath);
            path.remove(path.size() - 1);

        }

        visited[i][j] = false;
    }


}
