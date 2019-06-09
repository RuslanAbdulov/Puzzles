package com.company.threadpool;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;

//Implement a service that accepts runnable with a delay (myThreadPool.submit(myRunnable, 10000))
// after which this runnable should be executed. You can just use a fixed thread count or support a configurable one.
public class ThreadPool {

    private final List<WorkerThread> pool;
    private BlockingQueue<Task> delayedQueue = new DelayQueue<>();

    public ThreadPool(int poolSize) {
        pool = new ArrayList<>(poolSize);
        for (int i = 0; i < poolSize; i++) {
            WorkerThread worker = new WorkerThread(delayedQueue);
            pool.add(worker);
            worker.start();
        }
    }

    public void submit(Runnable runnable, long delay) {
        delayedQueue.add(new Task(runnable, delay));
    }

    public void stop() {
        pool.forEach(WorkerThread::doStop);
    }


    public static void main(String[] args) {
        ThreadPool threadPool = new ThreadPool(3);
        threadPool.submit( () -> System.out.println("Task 1"),500);
        threadPool.submit( () -> System.out.println("Task 2"),500);
        threadPool.submit( () -> System.out.println("Task 3"),500);
        threadPool.submit( () -> System.out.println("Task 4"),500);
        threadPool.submit( () -> System.out.println("Task 5"),500);
        threadPool.submit( () -> System.out.println("Task 6"),100);
        threadPool.submit( () -> System.out.println("Task 7"),100);
        threadPool.submit( () -> System.out.println("Task 8"),99);
        threadPool.submit( () -> System.out.println("Task 9"),50);

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            threadPool.stop();
        }
    }
}
