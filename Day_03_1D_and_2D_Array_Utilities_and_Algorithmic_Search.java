import java.util.Arrays;

public class Day_03_1D_and_2D_Array_Utilities_and_Algorithmic_Search {
    // TODO 3.1: Implement findMin(int[] arr) -> return minimum value
    // HINT: Loop from index 1 to arr.length - 1, updating min if arr[i] < min.

    public static int findMin(int[] arr) {
        if (arr == null || arr.length == 0){
            throw new IllegalArgumentException("Array cannot be empty or null");
                }
        int min = arr[0];
        for(int x : arr){
            if(x < min) min = x;
        }
        return min; // Replace
    }

    // TODO 3.2: Implement findMax(int[] arr) -> return maximum value
    public static int findMax(int[] arr) {
        if (arr == null || arr.length == 0){
            throw new IllegalArgumentException("Array cannot be empty or null");
        }
        int max = arr[0];
        for(int x : arr){
            if(x > max) max = x;
        }
        return max; // Replace
    }

    // TODO 3.3: Implement reverse(int[] arr) in-place using two-pointer swap
    // HINT: int left = 0, right = arr.length - 1; while(left < right) { swap; left++; right--; }
    public static void reverse(int[] arr) {
        if(arr == null) return;
        int left = 0;
        int right = arr.length - 1;

        while(left < right){
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;

            left++;
            right--;

        }


    }

    // TODO 3.4: Implement binarySearch(int[] arr, int target) on pre-sorted array
    // HINT: int mid = low + (high - low) / 2;
    public static int binarySearch(int[] arr, int target) {
        int low = 0, high = arr.length - 1;

        while(low <= high){
            int mid = low + (high - low)/2;
            if (arr[mid] == target) return mid;
            if (arr[mid] < target) low = mid +1;
            else high = mid -1;
        }

        return -1; // Replace
    }

    public static void main(String[] args) {
        int[] data = {45, 12, 89, 33, 67, 21};
        System.out.println("Original: " + Arrays.toString(data));
        System.out.println("Min: " + findMin(data) + " | Max: " + findMax(data));

        reverse(data);
        System.out.println("Reversed: " + Arrays.toString(data));

        Arrays.sort(data);
        System.out.println("Sorted: " + Arrays.toString(data));
        System.out.println("Binary Search (33): Index " + binarySearch(data, 33));
    }
}
