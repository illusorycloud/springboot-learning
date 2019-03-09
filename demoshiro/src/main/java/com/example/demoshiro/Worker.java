package com.example.demoshiro;

import java.util.List;

public class Worker implements Runnable {
    private List<Job> jobs;
    private boolean running;

    public Worker(List<Job> jobs) {
        this.jobs = jobs;
    }

    @Override
    public void run() {
        while (running) {
            for (Job j : jobs
            ) {
                j.run();
            }
        }
    }

    public void shutdown() {
        running = false;
    }
}
