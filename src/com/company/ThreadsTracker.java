package com.company;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

public class ThreadsTracker {

    private static ExecutorService executor;
    private static AtomicInteger threadsInProgress;

    public ThreadsTracker() {
        executor = Executors.newCachedThreadPool();
    }

    protected void start() {
        threadsInProgress = new AtomicInteger(0);
    }

    protected int spawn(Runnable r) {
        executor.submit(r);
        int running = threadsInProgress.incrementAndGet();
        System.out.println("Spawned, now running " + running);
        return running;
    }

    protected int done() {
        int running = threadsInProgress.decrementAndGet();
        System.out.println("Done, now running " + running);
        return running;
    }

    protected boolean running() {
        int running = threadsInProgress.get();
        return running > 0;
    }

    protected void await() {
        while (running()) {
            // stay idle while waiting for threads to finish
        }
    }
}
