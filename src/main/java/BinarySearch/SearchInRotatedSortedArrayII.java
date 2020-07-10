package BinarySearch;

public class SearchInRotatedSortedArrayII {

    /*
    #33. Search in Rotated Sorted Array (no duplicates)
    #81 search in rotated sorted array (with duplicates)
    #153 find minimum in rotated sorted array (no duplicates)
    #154 find minimum in rotated sorted array (with duplicates)
     */

    // #33 search in rotated sorted array (no duplicates)
    public int search(int[] nums, int target) {
        int n = nums.length, lo = 0, hi = n - 1;

        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if (nums[mid] == target) {
                return mid;
            }
            // 3 4 5 6 7 8 1 2
            if (nums[mid] >= nums[lo]) {
                if (target > nums[mid] || target < nums[lo]) {
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            } else {
                if (target < nums[mid] || target > nums[hi]) {
                    hi = mid - 1;
                } else {
                    lo = mid + 1;
                }
            }
        }

        return -1;
    }

    //    #81 search in rotated sorted array (with duplicates)
    public static boolean searchWithDuplicates(int[] nums, int target) {
        int start = 0, end = nums.length - 1, mid = -1;
        while (start <= end) {
            mid = (start + end) / 2;
            if (nums[mid] == target) {
                return true;
            }
            //If we know for sure right side is sorted or left side is unsorted
            if (nums[mid] < nums[end] || nums[mid] < nums[start]) {
                if (target > nums[mid] && target <= nums[end]) {
                    start = mid + 1;
                } else {
                    end = mid - 1;
                }
                //If we know for sure left side is sorted or right side is unsorted
            } else if (nums[mid] > nums[start] || nums[mid] > nums[end]) {
                if (target < nums[mid] && target >= nums[start]) {
                    end = mid - 1;
                } else {
                    start = mid + 1;
                }
                //If we get here, that means nums[start] == nums[mid] == nums[end], then shifting out
                //any of the two sides won't change the result but can help remove duplicate from
                //consideration, here we just use end-- but left++ works too
            } else {
                end--;
            }
        }

        return false;
    }

    //  #153. Find Minimum in Rotated Sorted Array (no duplicates)
    public int findMin(int[] nums) {
        // write your code here
        int left = 0, right = nums.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            if (mid > 0 && nums[mid] < nums[mid - 1]) {
                return nums[mid];
            } else if (nums[mid] >= nums[left] && nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return nums[0];
    }

    //todo
//        #154 find minimum in rotated sorted array (with duplicates)

    public static void main(String[] args) {
        System.out.println(searchWithDuplicates(new int[]{3, 1}, 1));
    }
}
