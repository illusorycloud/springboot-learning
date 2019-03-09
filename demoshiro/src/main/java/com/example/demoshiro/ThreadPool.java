package com.example.demoshiro;

public interface ThreadPool<Job extends Runnable> {
    void execute(com.example.demoshiro.Job job);

    void shutdown();

    void removeWorker(Worker worker);

    void addWorker(Worker worker);

    int getjobSize();

}
