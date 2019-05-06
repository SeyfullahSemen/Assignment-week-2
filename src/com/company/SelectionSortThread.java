package com.company;

public class SelectionSortThread implements Runnable {
    String name;
    Thread t;

    private static ThreadsTracker tracker = new ThreadsTracker();
    public static int spawnSize = 100;

    // dit is de code je maakt een klasse aan met een constructor en dan geef je de naam van de thread hier
    SelectionSortThread(String thread) {
        name = thread;
        t = new Thread(this, name);
        System.out.println("New thread: " + t);
        System.out.println("The amount of processors you have " + getAmountOfProcessors());
        t.start();
    }

    // omdat je van de runnable klasse implement moet je de run functie van de runnable interface implementen
    // in deze functie gebeurd dan alles zeg maar
    @Override
    public void run() {
        try {
            for (int i = 5; i > 0; i--) {
                System.out.println(name + ": " + i);
                Thread.sleep(1000);
            }
            tracker.done();
        } catch (InterruptedException e) {
            System.out.println(name + "Interrupted");
        }
        System.out.println(name + " exiting.");
    }


    private void sort(int arr[]) {
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
    }

    // Prints the array
    private void printArray(int arr[]) {
        int n = arr.length;
        for (int i = 0; i < n; ++i)
            System.out.print(arr[i] + " ");
        System.out.println();
    }

    private int getAmountOfProcessors() {
        int processors = Runtime.getRuntime().availableProcessors();
        System.out.println("amount of processors " + processors);
        return processors;
    }
}

