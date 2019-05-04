package com.company;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Main {
    private static int[] randomArray;

    public static void main(String[] args) {
        // call the randomGenerator() function

        new SelectionSortThread("One");
        new SelectionSortThread("Two");
        new SelectionSortThread("Three");

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            System.out.println("Main thread Interrupted");
        }
        System.out.println("Main thread exiting.");
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
