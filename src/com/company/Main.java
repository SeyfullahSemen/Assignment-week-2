package com.company;

import java.util.Arrays;
import java.util.Random;

public class Main {
    private static int[] randomArray;
    private static final int NUMBER_COUNT = 7;
    private static int threadAmount = 4;
    private static int lowestNumber = Integer.MAX_VALUE;
    private static int lowestIndex = Integer.MAX_VALUE;
    private static int lowestPosition = Integer.MAX_VALUE;

    public static void main(String[] args) {
        System.out.println("Running array with " + NUMBER_COUNT + " elements on " + threadAmount + " threads");

        // Generate numbers array and split them equally to subarrays
        int[] numbers = generateNumber(NUMBER_COUNT);
        System.out.println("Original array:\n" + Arrays.toString(numbers));
        int[][] splittedArray = splitArray(threadAmount, numbers);

        /*startThreads(threadAmount);*/

        // Split arrays and print them
        //System.out.println("\nSplit arrays:\n" + Arrays.deepToString(splittedArray));

        //System.out.println("\nSplit & sorted arrays:\n" + Arrays.deepToString(splittedArray));

        /*
            Find the lowest number in every sorted subarray
            The only thing we have to do for this is comparing [0]
         */
        for (int i = 0; i < splittedArray.length; i++) {
            for (int j = 0; j < splittedArray[i].length; j++) {
                if(splittedArray[i][j] < lowestNumber) {
                    lowestNumber = splittedArray[i][j];
                    lowestIndex = i;
                    lowestPosition = j;
                }
            }
        }

        splittedArray = swap(splittedArray, lowestIndex, lowestPosition);

        System.out.println("\nLowest number in split arrays: " + lowestNumber + " found on splitArray["
                + lowestIndex + "][" + lowestPosition + "]");
        numbers = regenerateNumbers(splittedArray);
        System.out.println(Arrays.toString(numbers));

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

    private static int[][] swap(int[][] splittedArray, int lowestIndex, int lowestPosition) {
        int tempNumber = splittedArray[lowestIndex][lowestPosition];
        splittedArray[lowestIndex][lowestPosition] = splittedArray[0][0];
        splittedArray[0][0] = tempNumber;

        return splittedArray;
    }

    private static int[] regenerateNumbers(int[][] splittedArray) {
        int[] numbers = new int[NUMBER_COUNT];
        int x = 0;

        for (int i = 0; i < splittedArray.length; i++) {
            for (int j = 0; j < splittedArray[i].length; j++) {
                numbers[x] = splittedArray[i][j];
                x++;
            }
        }
        return numbers;
    }

    private static int[][] splitArray(int arrayAmount, int[] numbers) {
        if (numbers.length == 0) {
            return new int[0][0];
        }

        int splitLength = (int) Math.ceil((double) numbers.length / (double) arrayAmount);
        int[][] splits = new int[arrayAmount][];

        int j = 0;
        int k = 0;
        for (int i = 0; i < numbers.length; i++) {
            if (k == splitLength) {
                k = 0;
                j++;
            }
            if (splits[j] == null) {
                int remainingNumbers = numbers.length - i;
                splits[j] = new int[Math.min(remainingNumbers, splitLength)];
            }
            splits[j][k++] = numbers[i];
        }
        return splits;

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
