package BinarySearch;

public class SearchRangeFirstPositionAndLast {
    enum SearchType {
        FIRST,
        LAST;
    }
    public int[] searchRange(int[] nums, int target) {
        int first = binarySearch(nums, target, SearchType.FIRST);
        int last = binarySearch(nums, target, SearchType.LAST);
        return new int[]{first, last};
    }

    private int binarySearch(int[] arr, int target, SearchType searchType) {
        int left = 0, right = arr.length - 1;

        int res = -1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                res = mid;
                if (SearchType.FIRST == searchType) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid -1;
            }
        }

        return res;
    }
}
