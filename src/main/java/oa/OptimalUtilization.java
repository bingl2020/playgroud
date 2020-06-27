package oa;

import java.util.*;

/**
 * https://leetcode.com/discuss/interview-question/373202
 * "Given 2 lists a and b. Each element is a pair of integers where the first integer represents the unique id
 * and the second integer represents a value. Your task is to find an element from a and an element form b
 * such that the sum of their values is less or equal to target and as close to target as possible.
 * Return a list of ids of selected elements. If no pair is possible, return an empty list."
 */
public class OptimalUtilization {
    public List<int[]> optimal(List<int[]> a, List<int[]> b, int target) {
        if (a == null || a.isEmpty() || b == null || b.isEmpty()) {
            return new ArrayList<int[]>();
        }

        Collections.sort(a, (a1, a2) -> Integer.compare(a1[1], a2[1]));
        Collections.sort(b, (b1, b2) -> Integer.compare(b1[1], b2[1]));
        int m = a.size();
        int n = b.size();
        int i = 0;
        int j = n - 1;
        List<int[]> result = new ArrayList<>();
        int max = Integer.MIN_VALUE;
        while (i < m && j >= 0) {
            int sum = a.get(i)[1] + b.get(j)[1];
            if (sum <= target) {
                // maybe duplicate ele
                if (sum > max) {
                    result.clear();
                    max = sum;
                    result.add(new int[]{a.get(i)[0], b.get(j)[0]});
                } else if (sum == max) {
                    result.add(new int[]{a.get(i)[0], b.get(j)[0]});
                }
                i++;
            } else {
                j--;
            }
        }
        return result;
    }

    public List<List<Integer>> aircraftUtilization(int maxTravelDist, int[][] forwardRouteList, int[][] returnRouteList) {
        List<List<Integer>> res = new ArrayList<>();
        int len1 = forwardRouteList.length, len2 = returnRouteList.length;
        if (len1 == 0 || len2 == 0) return res;
        Arrays.sort(forwardRouteList, (int[] a, int[] b) -> (a[1] - b[1]));
        Arrays.sort(returnRouteList, (int[] a, int[] b) -> (a[1] - b[1]));
        int left = 0, right = len2 - 1, maxVal = -1;
        HashMap<Integer, List<List<Integer>>> map = new HashMap<>();
        while (left < len1 && right >= 0) {
            int sum = forwardRouteList[left][1] + returnRouteList[right][1];
            if (sum > maxTravelDist) {
                --right;
                continue;
            }
            if (sum >= maxVal) {
                int r = right;
                map.putIfAbsent(sum, new ArrayList<>());
                // check the duplicates
                while (r >= 0 && returnRouteList[r][1] == returnRouteList[right][1]) {
                    List<Integer> list = new ArrayList<>();
                    list.add(forwardRouteList[left][0]);
                    list.add(returnRouteList[r][0]);
                    map.get(sum).add(list);
                    --r;
                }
                maxVal = sum;
            }
            ++left;
        }
        return map.get(maxVal);
    }

    public static void main(String[] args) {
        OptimalUtilization sol = new OptimalUtilization();
        List<int[]> aa = new ArrayList<>();
        aa.add(new int[]{1, 8});
        aa.add(new int[]{2, 15});
        aa.add(new int[]{3, 9});
        List<int[]> bb = new ArrayList<>();
        bb.add(new int[]{1, 8});
        bb.add(new int[]{2, 11});
        bb.add(new int[]{3, 12});
        List<int[]> res = sol.optimal(aa, bb, 20);
        for (int[] item : res) {
            System.out.println(Arrays.toString(item));
        }
    }
}