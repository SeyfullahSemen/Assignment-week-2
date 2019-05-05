package com.company;

public class Splitter implements Runnable {
    private int randomArray[];
    private int low;
    private int high;
    public int pivotIndex;

    public Splitter(int[] randomArray) {
        this.randomArray = randomArray;
        this.low = 0;
        this.high = randomArray.length - 1;
    }

    public Splitter(int[] randomArray, int low, int high, int pivot) {
        this.randomArray = randomArray;
        this.low = low;
        this.high = high;
    }

    public String toString() {
        return "" + pivotIndex;
    }

    public void pivotSplit(int pivot) {
        System.out.println("Splitting on "+pivot);
        this.pivotIndex = pivotPartition(randomArray, low, high, pivot);
    }

    /**
     * Partition the array list[first..last]
     */
    private int partition(int[] list, int first, int last) {
        int pivot = list[first]; // Choose the first element as the pivot
        int low = first + 1; // Index for forward search
        int high = last; // Index for backward search

        while (high > low) {
            // Search forward from left
            while (low <= high && list[low] <= pivot)
                low++;

            // Search backward from right
            while (low <= high && list[high] > pivot)
                high--;

            // Swap two elements in the list
            if (high > low) {
                int temp = list[high];
                list[high] = list[low];
                list[low] = temp;
            }
        }

        while (high > first && list[high] >= pivot)
            high--;

        // Swap pivot with list[high]
        if (pivot > list[high]) {
            list[first] = list[high];
            list[high] = pivot;
            return high;
        } else {
            return first;
        }
    }

    public int pivotPartition(int[] list, int first, int last, int pivot) {

        int i = first; // Index for forward search
        int j = last; // Index for backward search

        while (i < j) {
            // Search forward from left
            while (i <= j && list[i] <= pivot)
                i++;

            // Search backward from right
            while (i <= j && list[j] > pivot)
                j--;

            // Swap two elements in the list
            if (i < j) {
                swap(list, i, j);
//                System.out.print("["+i + "," + j+"]");
//                Utils.printArray(list);
            }
        }

//        System.out.print("["+i + "," + j+"]");
        // list [low..j] contains elements <= pivot
        // list [j+1..high] contains element >= pivot
        return j;
    }

    private void swap(int[] ar, int i, int j) {
        int temp = ar[i];
        ar[i] = ar[j];
        ar[j] = temp;
    }

    @Override
    public void run() {

    }
}
