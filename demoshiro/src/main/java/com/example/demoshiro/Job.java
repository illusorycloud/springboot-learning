package com.example.demoshiro;

public class Job implements Runnable {
    private Runnable runnable;

    public Job(Runnable runnable) {
        this.runnable = runnable;
    }

    @Override
    public void run() {
        runnable.run();
    }
}
