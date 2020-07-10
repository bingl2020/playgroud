package BinarySearch;

import java.util.List;

public class SearchInABigSortedArray {

    public int searchInBigSortedArray(List<Integer> list, int x) {
        // find the range
        int index = 1;
        while (list.get(index) < x) {
            index *= 2;
        }

        int left = index / 2, right = index;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (list.get(mid) == x) {
                return mid;
            } else if (list.get(mid)  > x){
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return -1;
    }

}
