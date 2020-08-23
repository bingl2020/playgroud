package BinarySearch;

public class ClassicBinarySearch {


    public int binarySearchIterative(int[] arr, int x) {
        int left = 0, right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == x) {
                return mid;
            } else if (arr[mid] > x) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return -1;
    }


    public int binarySearch(int[] arr, int x) {
        return binarySearchRec(arr, x, 0, arr.length - 1);
    }

    private int binarySearchRec(int[] arr, int x, int left, int right) {
        if (left > right) {
            return -1;
        }

        int mid = left + (right - left) / 2;

        if (arr[mid] == x) {
            return mid;
        }

        if (arr[mid] > x) {
            return binarySearchRec(arr, x, left, mid - 1);
        } else {
            return binarySearchRec(arr, x, mid + 1, right);
        }
    }

    public static void main(String args[]) {
        ClassicBinarySearch ob = new ClassicBinarySearch();
        int arr[] = {2, 3, 4, 10, 40};
        int n = arr.length;
        int x = 10;
        int result = ob.binarySearch(arr, x);
        if (result == -1)
            System.out.println("Element not present");
        else
            System.out.println("Element found at "
                    + "index " + result);

        System.out.println("Iterative :" + ob.binarySearchIterative(arr, x));

    }
}
