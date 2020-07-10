package BFS.QuickSortAndSelect;

import java.util.Random;

public class QuickSort {
    /*https://www.youtube.com/watch?v=Fiot5yuwPAg&t=448s
    average o(nlogn)  worset case o(n2)
    */

    public void quickSort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private void quickSort(int[] arr, int low, int high) {
        if (low < high + 1) {
            int p = partition(arr, low, high);
            quickSort(arr, low, p - 1);
            quickSort(arr, p, high);
        }
    }

    private int getPivot(int low, int high) {
        Random rand = new Random();
        return rand.nextInt(high - low + 1) + low;
    }

    private int partition(int[] arr, int low, int high) {
        swap(arr, low, getPivot(low, high));
        int boarder = low + 1;
        for (int i = boarder; i <=high; i++) {
            if (arr[i] < arr[low]) {
                swap(arr, i, boarder++);
            }
        }

        swap(arr, low, boarder - 1);
        return boarder - 1;
    }

    private void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
