package com.company;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

public class Main {
    private static int[] randomArray;

    public static void main(String[] args) {
        // call the randomGenerator() function
        int[] numbers = {16, 18, 3, 21, 14, 7, 9, 5};
        int threadAmount = 4;

        startThreads(threadAmount);

        splitArray(threadAmount, numbers);


        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            System.out.println("Main thread Interrupted");
        }
        System.out.println("Main thread exiting.");
    }

    private static void splitArray(int threadAmount, int[] numbers) {
        if (numbers.length != 0) {
            int n = numbers.length;
            for (int i = 0; i < threadAmount; i++) {
            }
            int[] a = Arrays.copyOfRange(numbers, 0, (n + 1) / 2);
            int[] b = Arrays.copyOfRange(numbers, (n + 1) / 2, n);

            System.out.println(Arrays.toString(a));
            System.out.println(Arrays.toString(b));
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
//        generatedList();

//        SelectionSort ob = new SelectionSort();
//        ob.sort(generatedList());
//        System.out.println("Sorted array");
//        ob.printArray(generatedList());
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

    public static int[] generatedList() {
        randomArray = new int[100000];
        for (int i = 0; i < randomArray.length; i++) {
            randomArray[i] = randomNumberGenerator();
        }
        for (int i : randomArray) {
            System.out.println("The array: " + i);
        }

        return randomArray;
    }

    public static boolean isSorted(int[] a) {
        for (int i = 0; i < a.length; i++) {
            return a[i] < a[i + 1];
        }
        return false;
    }

    private static long timeElapsed() {
        return System.currentTimeMillis();
    }

}
