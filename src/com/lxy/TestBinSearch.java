package com.lxy;

public class TestBinSearch {

    public static int binSearch(int[] arr, int start, int end, int target) {

        if (arr.length == 0) {
            return -1;
        }

        int mid = (end - start) / 2 + start;

        if (arr[mid] == target) {
            return mid;
        }

        if (start >= end) {
            return -1;
        } else if (target > arr[mid]) {
            return binSearch(arr, mid + 1, end, target);
        } else if (target < arr[mid]) {
            return binSearch(arr, start, mid - 1, target);
        }

        return -1;
    }

    public static void main(String[] args) {
        int srcArray[] = {3, 5, 11, 17, 21, 23, 28, 30, 32, 50, 64, 78, 81, 95, 101};
        System.out.println(binSearch(srcArray, 0, srcArray.length - 1, 81));
    }
}
