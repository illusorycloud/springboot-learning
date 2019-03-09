package com.example.demoshiro;

import java.util.ArrayList;
import java.util.List;

public class DefaultThreadPool implements ThreadPool {
    private List<Worker> workers = new ArrayList<>();
    List<Job> jobs = new ArrayList<>();

    @Override
    public void execute(Job job) {
        jobs.add(job);
        job.notify();
    }

    @Override
    public void shutdown() {
        for (Worker w : workers
        ) {
            w.shutdown();
        }
    }

    @Override
    public void removeWorker(Worker worker) {
        workers.remove(worker);
    }

    @Override
    public void addWorker(Worker worker) {
        workers.add(worker);
    }

    @Override
    public int getjobSize() {
        return 0;
    }
}
