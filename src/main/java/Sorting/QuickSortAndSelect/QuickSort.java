package Sorting.QuickSortAndSelect;

import java.util.Random;

//https://leetcode.com/problems/sort-an-array/
public class QuickSort {
    /*https://www.youtube.com/watch?v=Fiot5yuwPAg&t=448s
    average o(nlogn)  worset case o(n2)
    */

    public int[] sortArray(int[] nums) {
        quickSort(nums);
        return nums;
    }

    private void quickSort(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
    }

    private void quickSort(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }

        int pi = partition2(nums, left, right);
        quickSort(nums, left, pi - 1);
        quickSort(nums, pi + 1, right);
    }

    private int partition2(int[] nums, int left, int right) {
        int q = left + (int) (Math.random() * (right - left + 1));
        swap(nums, left, q);

        int index = left + 1;

        for (int i = left + 1; i <= right; i++) {
            if (nums[i] < nums[left]) {
                swap(nums, i, index++);
            }
        }

        swap(nums, left, --index);
        return index;
    }

    private int partition(int[] nums, int left, int right) {
        int q = left + (int) (Math.random() * (right - left + 1));
        swap(nums, left, q);

        int index = left + 1;

        for (int i = left + 2; i <= right; i++) {
            if (nums[i] < nums[left]) {
                swap(nums, i, index++);
            }
        }

        swap(nums, left, --index);
        return index;
    }

    private void swap(int[] nums, int i, int j) {
        if (i == j) {
            return;
        }

        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;

    }
}
