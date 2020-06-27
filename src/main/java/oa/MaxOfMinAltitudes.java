package oa;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MaxOfMinAltitudes {


    public int getMaxOfMinAltitude(int[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        List<int[]> path = new ArrayList<>();
        path.add(new int[]{0, 0});
        int[] res = new int[1];

        List<Integer> minList = new ArrayList<>();
        minList.add(Integer.MAX_VALUE);
        backtrack(grid, path, minList, res);

        return res[0];

    }

    private void backtrack(int[][] grid, List<int[]> path, List<Integer> minList, int[] res) {
        int[] point = path.get(path.size() - 1);
        int pathMin = minList.get(minList.size() - 1);

        if (point[0] == grid.length - 1 && point[1] == grid[0].length - 1) {
            res[0] = Math.max(pathMin, res[0]);
            minList.add(Integer.MAX_VALUE);
            return;
        }

        if (point[0] != 0 || point[1] != 0) {
            minList.add(Math.min(pathMin, grid[point[0]][point[1]]));
        }

        if (point[1] + 1 >= 0 && point[1] + 1 < grid[0].length ) {
            path.add(new int[]{point[0], point[1] + 1});
            backtrack(grid, path, minList, res);
            path.remove(path.size() -1);
            minList.remove(minList.size() - 1);
        }
        if (point[0] + 1 >= 0 && point[0] + 1 < grid.length ) {
            path.add(new int[]{point[0] + 1, point[1]});
            backtrack(grid, path, minList, res);
            path.remove(path.size() -1);
            minList.remove(minList.size() - 1);
        }
    }

    /* DRIVER CODE */
    public static void main(String[] args) {
        System.out.println("AMAZON OA2 - DRONE FLYING");
        int[][] grid = {{1,2,3},{4,5,1}};
        //int[][] grid = {{5,1},{4,5}};
        int result = getMaxOfMin(grid);
        System.out.println("paths(not including start & end):");
        for(ArrayList<Integer> list : Wrapper.paths){
            System.out.println(list.toString());
        }
        System.out.println("mins of every path: "+Wrapper.mins.toString());

        System.out.println("result: "+result);



        new MaxOfMinAltitudes().getMaxOfMinAltitude(grid);
    }
    /* SOLUTION DOWN HERE */
    public static class Wrapper{
        public static ArrayList<ArrayList<Integer>> paths;
        public static ArrayList<Integer> mins;
        public static int max;
    }
    public static int getMaxOfMin(int[][] grid){
        Wrapper.paths = new ArrayList<ArrayList<Integer>>();
        Wrapper.mins = new ArrayList<Integer>();
        Wrapper.max = 0;
        ArrayList<Integer> path = new ArrayList<>();
        getMins(grid, 0, 1, grid[0][1], path);
        path.clear();
        getMins(grid, 1, 0, grid[1][0], path);
        return Wrapper.max;
    }
    public static void getMins(int[][] grid, int r, int c, int min, ArrayList<Integer> path){
        //if end found, save values.
        if(r == grid.length-1 && c == grid[0].length-1) {
            Wrapper.paths.add(path);
            Wrapper.mins.add(min);
            if(min > Wrapper.max) Wrapper.max = min;
            return;
        }
        //check if we have a new min for current brach path
        int newMin = min;
        if(grid[r][c] < min){
            newMin = grid[r][c];
        }
        //Add current value to branch current path
        path.add(grid[r][c]);
        //GO RIGHT
        if(c+1 < grid[0].length) {
            ArrayList<Integer> newPath = new ArrayList<Integer>(path);
            getMins(grid, r, c+1, newMin, newPath);
        }
        //GO DOWN
        if(r+1 < grid.length) {
            ArrayList<Integer> newPath = new ArrayList<Integer>(path);
            getMins(grid, r+1, c, newMin, newPath);
        }
    }

}
