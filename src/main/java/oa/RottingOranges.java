package oa;

import java.util.ArrayDeque;
import java.util.Deque;

public class RottingOranges {
    private static final int[][] directions = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    public int orangesRotting(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }

        int freshCount = 0;
        Deque<int[]> queue = new ArrayDeque<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    freshCount++;
                } else if (grid[i][j] == 2) {
                    queue.offer(new int[]{i, j});
                }
            }
        }

        if (freshCount == 0) {
            return 0;
        }

        int count = 0;
        int hours = 0;
        while (!queue.isEmpty()) {

            int size = queue.size();

            while (size-- > 0) {
                int[] point = queue.poll();
                for (int[] direction : directions) {
                    int newX = point[0] + direction[0];
                    int newY = point[1] + direction[1];
                    if (newX >= 0 && newX < grid.length
                            && newY >= 0 && newY < grid[newX].length
                            && grid[newX][newY] == 1) {

                        grid[newX][newY] = 2;
                        queue.offer(new int[] {newX, newY});
                        count++;
                    }
                }
            }
            hours++;
        }

        return (count == freshCount) ? hours - 1 : -1;
    }

    public static void main(String[] args) {
        new RottingOranges().orangesRotting(new int[][]{{1,2}});
    }
}
