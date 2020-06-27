package oa;

import java.util.LinkedList;
import java.util.Queue;

/*
You have a map that marks the locations of treasure islands. Some of the map area has jagged rocks and dangerous reefs. Other areas are safe to sail in. There are other explorers trying to find the treasure. So you must figure out a shortest route to one of the treasure islands.

Assume the map area is a two dimensional grid, represented by a matrix of characters. You must start from one of the starting point (marked as S) of the map and can move one block up, down, left or right at a time. The treasure island is marked as X. Any block with dangerous rocks or reefs will be marked as D. You must not enter dangerous blocks. You cannot leave the map area. Other areas O are safe to sail in. Output the minimum number of steps to get to any of the treasure islands.

 */
public class TreasureIslandII {
    final int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public static void main(String[] args) {
        TreasureIslandII main = new TreasureIslandII();
        char[][] grid1 = new char[][]{{'S', 'O', 'O', 'S', 'S'},
                {'D', 'O', 'D', 'O', 'D'},
                {'O', 'O', 'O', 'O', 'X'},
                {'X', 'D', 'D', 'O', 'O'},
                {'X', 'D', 'D', 'D', 'O'}};

        char[][] grid2 = new char[][]{{'S', 'O', 'O', 'S', 'S'},
                {'D', 'O', 'D', 'O', 'O'},
                {'O', 'O', 'O', 'O', 'X'},
                {'X', 'D', 'D', 'O', 'O'},
                {'X', 'D', 'D', 'D', 'O'}};
        int tc1 = main.shortestPathToTreasureIsland(grid1);
        int tc2 = main.shortestPathToTreasureIsland(grid2);
        if (tc1 == 3 && tc2 == 2) {
            System.out.println("All Test Case Passes!");
        } else {
            System.out.println("There are test failures!");
        }
    }

    private int shortestPathToTreasureIsland(char[][] grid) {
        if (grid == null || grid.length == 0) return 0;
        int row = grid.length, col = grid[0].length, minStep = Integer.MAX_VALUE;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (grid[i][j] == 'S') {
                    minStep = Math.min(minStep, this.bfs(grid, i, j));
                }
            }
        }
        return minStep;
    }

    private int bfs(char[][] grid, int row, int col) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{row, col});
        int steps = 0;
        boolean[][] visited = new boolean[grid.length][grid[0].length];

        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                int[] coor = queue.poll();
                int x = coor[0], y = coor[1];
                if (grid[x][y] == 'X') return steps;
                visited[x][y] = true;
                for (int i = 0; i < directions.length; i++) {
                    int dx = x + directions[i][0];
                    int dy = y + directions[i][1];
                    if (dx >= 0 && dx < grid.length && dy >= 0 && dy < grid[0].length && !visited[dx][dy] && grid[dx][dy] != 'D') {
                        queue.offer(new int[]{dx, dy});
                    }
                }
                size--;
            }
            steps++;
        }
        return -1;
    }

    public static int multiSourceShortest(char[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int MAX = m * n;
        int[][] rst = new int[n][m];
        int path = Integer.MAX_VALUE;

        // Sweep from upper left to bottom right
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (matrix[i][j] == 'D') {
                    rst[i][j] = MAX;
                } else if (matrix[i][j] == 'X') {
                    rst[i][j] = 0;
                } else {
                    int cellAbove = i > 0 ? rst[i - 1][j] : MAX;
                    int cellLeft = j > 0 ? rst[i][j - 1] : MAX;
                    rst[i][j] = Math.min(cellAbove, cellLeft) + 1;
                }
            }
        }

        // Sweep from bottom right to upper left
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                if (matrix[i][j] != 'D') {
                    int cellBelow = i < n - 1 ? rst[i + 1][j] : MAX;
                    int cellRight = j < m - 1 ? rst[i][j + 1] : MAX;
                    rst[i][j] = Math.min(rst[i][j], Math.min(cellBelow, cellRight) + 1);
                }

                // pick out the source nodes and get min path length
                if (matrix[i][j] == 'S') {
                    path = Math.min(path, rst[i][j]);
                }
            }
        }

        return path;
    }
}
