package oa;

import java.util.*;

class CriticalPoint {

    public static int time = 0;

    public static void main(String[] args) {
        List<List<Integer>> A = new ArrayList<>();
        A.add(new ArrayList<>(Arrays.asList(0, 1)));
        A.add(new ArrayList<>(Arrays.asList(0, 2)));
        A.add(new ArrayList<>(Arrays.asList(1, 2)));
        A.add(new ArrayList<>(Arrays.asList(1, 3)));
        A.add(new ArrayList<>(Arrays.asList(1, 4)));
        A.add(new ArrayList<>(Arrays.asList(3, 5)));
        A.add(new ArrayList<>(Arrays.asList(4, 5)));

        System.out.println(criticalConnections(6, A));
    }

    public static List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int i = 0; i < n; i++)
            map.put(i, new HashSet<Integer>());
        for (int i = 0; i < connections.size(); i++) {
            List<Integer> curr = connections.get(i);
            map.get(curr.get(0)).add(curr.get(1));
            map.get(curr.get(1)).add(curr.get(0));
        }
        Set<List<Integer>> set = new HashSet<>();
        int[] low = new int[n];
        int[] ids = new int[n];
        int[] parent = new int[n];
        Arrays.fill(ids, -1);
        Arrays.fill(parent, -1);
        for (int i = 0; i < n; i++) {
            if (ids[i] == -1)
                dfs(map, low, ids, parent, i, set);
        }
        return new ArrayList<>(set);
    }

    public static void dfs(Map<Integer, Set<Integer>> map, int[] low, int[] ids, int[] parent, int curr,
                           Set<List<Integer>> set) {
        int children = 0;
        ids[curr] = low[curr] = ++time;
        for (int nei : map.get(curr)) {
            if (ids[nei] == -1) {
                children++;
                parent[nei] = curr;
                dfs(map, low, ids, parent, nei, set);
                low[curr] = Math.min(low[curr], low[nei]);
                if ((parent[curr] == -1 && children >= 1 && ids[curr] < low[nei])
                        || (parent[curr] != -1 && ids[curr] < low[nei])) {
                    List<Integer> temp = new ArrayList<>();
                    temp.add(curr);
                    temp.add(nei);
                    Collections.sort(temp);
                    set.add(temp);
                }
            } else if (nei != parent[curr]) {
                low[curr] = Math.min(low[curr], ids[nei]);
            }
        }
    }
}