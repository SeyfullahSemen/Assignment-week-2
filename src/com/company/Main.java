package com.company;

import java.util.Arrays;
import java.util.Random;

public class Main {
    private static int[] randomArray;
    private static final int NUMBER_COUNT = 16;
    private static int threadAmount = Runtime.getRuntime().availableProcessors();
    private static int lowestNumber = Integer.MAX_VALUE;

    public static void main(String[] args) {
        System.out.println("Running array with " + NUMBER_COUNT + " elements on " + threadAmount + " threads");

        // Generate numbers array and split them equally to subarrays
        int[] numbers = generateNumber(NUMBER_COUNT);
        System.out.println("Original array:\n" + Arrays.toString(numbers));
        int[][] splittedArray = new int[threadAmount][(numbers.length/threadAmount)];

        /*startThreads(threadAmount);*/

        // Split arrays and print them
        splitArray(threadAmount, numbers, splittedArray);
        System.out.println("\nSplit arrays:\n" + Arrays.deepToString(splittedArray));

        // Sort subarrays and printing them
        for (int i = 0; i < threadAmount; i++) {
            splittedArray[i] = sort(splittedArray[i]);
        }

        System.out.println("\nSplit & sorted arrays:\n" + Arrays.deepToString(splittedArray));

        /*
            Find the lowest number in every sorted subarray
            The only thing we have to do for this is comparing [0]
         */
        for (int i = 0; i < splittedArray.length; i++) {
            if(splittedArray[i][0] < lowestNumber)
                lowestNumber = splittedArray[i][0];
        }
        System.out.println("\nLowest number in split arrays: " + lowestNumber);

        /*
            Add comment
         */
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            System.out.println("Main thread Interrupted");
        }
        //System.out.println("Main thread exiting.");
    }

    private static void splitArray(int threadAmount, int[] numbers, int[][] splittedArray) {
        int elPerSubArr = numbers.length / threadAmount;

        int x = 0;
        int i = x;

        if (numbers.length != 0) {
            while (i < numbers.length) {
                for (int j = 0; j < elPerSubArr; j++) {
                    splittedArray[x][j] = numbers[i];
                    i++;
                }
                x++;
            }
        } else {
            System.out.println("There are no elements inside this array!");
        }
    }

    private static void startThreads(int threadAmount) {
        for (int i = 0; i < threadAmount; i++) {
            new SelectionSortThread("Thread " + i);
        }
    }
//        long before = System.currentTimeMillis();
//        randomNumberGenerator();
//        generateNumber();

//        SelectionSort ob = new SelectionSort();
//        ob.sort(generateNumber());
//        System.out.println("Sorted array");
//        ob.printArray(generateNumber());
//
//        System.out.println(System.currentTimeMillis() - before);

    /**
     * @return randomly generated integers
     */
    public static int randomNumberGenerator() {
        Random rand = new Random();
        int randomNum = rand.nextInt(100);
        return randomNum;
    }

    public static int[] generateNumber(int amount) {
        randomArray = new int[amount];
        for (int i = 0; i < randomArray.length; i++) {
            randomArray[i] = randomNumberGenerator();
        }

        return randomArray;
    }

    // Can be used to check if array is sorted
    public static boolean isSorted(int[] a) {
        for (int i = 0; i < a.length; i++) {
            return a[i] < a[i + 1];
        }
        return false;
    }

    private static int[] sort(int arr[]) {
        int n = arr.length;

        // One by one move boundary of unsorted subarray
        for (int i = 0; i < n - 1; i++) {
            // Find the minimum element in unsorted array
            int min_idx = i;
            for (int j = i + 1; j < n; j++)
                if (arr[j] < arr[min_idx])
                    min_idx = j;

            // Swap the found minimum element with the first
            // element
            int temp = arr[min_idx];
            arr[min_idx] = arr[i];
            arr[i] = temp;
        }
        return arr;
    }
}
