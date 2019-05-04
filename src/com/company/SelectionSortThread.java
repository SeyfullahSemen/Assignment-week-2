package com.company;

public class SelectionSortThread implements Runnable {
    String name;
    Thread t;
    // dit is de code je maakt een klasse aan met een constructor en dan geef je de naam van de thread hier
    SelectionSortThread(String thread) {
        name = thread;
        t = new Thread(this, name);
        System.out.println("New thread: " + t);
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
        } catch (InterruptedException e) {
            System.out.println(name + "Interrupted");
        }
        System.out.println(name + " exiting.");
    }
}

