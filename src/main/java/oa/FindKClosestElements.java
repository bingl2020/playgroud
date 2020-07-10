package oa;

import java.util.*;

public class FindKClosestElements {

    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        Set<String> set = new HashSet<>();

        Map<String, Integer> map = new HashMap<>();
        for (String word : "  ".toLowerCase().replaceAll("", " ").split("\\s+")) {

        }
        List<Integer> res = new ArrayList<>();
        if (k <= 0) {
            return res;
        }

        int closest = binarySearch(arr, k, x);
        int left = closest, right = closest;

        // res.add(arr[closest]);
        while (right - left + 1 < k && (left > 0 || right < arr.length - 1)) {
            if (left == 0) {
                right++;
                // res.add(arr[++right]);
            } else if (right == arr.length - 1) {
                // res.add(arr[--left]);
                left--;
            } else if (Math.abs(arr[left - 1] - x) <= Math.abs(arr[right + 1] - x)) {
                // res.add(arr[--left]);
                left--;
            } else {
                right++;
                // res.add(arr[++right]);
            }
        }

        while (left <= right) {
            res.add(arr[left++]);
        }

        return res;
    }

    public int binarySearch(int[] arr, int k, int x) {
        int left = 0, right = arr.length - 1, minDiff = Integer.MAX_VALUE, closest = -1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            // keep tracking
            if (Math.abs(arr[mid] - x) < minDiff) {
                minDiff = Math.abs(arr[mid] - x);
                closest = mid;
            }

            if (arr[mid] == x) {
                return mid;
            } else if (arr[mid] < x) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return closest;
    }

    public static void main(String[] args) {
        int[] test = {1, 2, 3, 4, 5};
        new FindKClosestElements().findClosestElements(test, 4, 3);
    }

}
