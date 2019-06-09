package com.company.threadpool;

import java.util.concurrent.BlockingQueue;

public class WorkerThread extends Thread{

    private final BlockingQueue<Task> delayedQueue;
    private boolean isStopped = false;

    public WorkerThread(BlockingQueue<Task> delayQueue) {
        this.delayedQueue = delayQueue;
    }

    public void run(){
        while(!isStopped()){
            try{
                Task task = delayedQueue.take();
                task.getRunnable().run();
            } catch(Exception e){
                //log or otherwise report exception,
                //but keep pool thread alive.
            }
        }
    }

    public synchronized void doStop(){
        isStopped = true;
        this.interrupt();
    }

    public synchronized boolean isStopped(){
        return isStopped;
    }
}
