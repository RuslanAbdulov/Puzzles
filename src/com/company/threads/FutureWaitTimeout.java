package com.company.threads;

import java.util.concurrent.*;

public class FutureWaitTimeout {


    public static void main(String[] args) {

        Task task1 = new Task(1900);
        Task task2 = new Task(3000);
        ExecutorService service =  Executors.newFixedThreadPool(2);

        Future future1 =  service.submit(task1);
        Future future2 = service.submit(task2);

        try {
            future1.get(2000, TimeUnit.MILLISECONDS);
            future2.get(1000, TimeUnit.MILLISECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        service.shutdown();
        System.out.println("the end");
    }

    static class Task implements Runnable {

        long sleepTime;

        public Task(long sleepTime) {
            this.sleepTime = sleepTime;
        }

        @Override
        public void run() {
            System.out.println(String.format("%s started at %d",
                    Thread.currentThread().getName(),
                    System.currentTimeMillis()));
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(String.format("%s finished at %d",
                    Thread.currentThread().getName(),
                    System.currentTimeMillis()));
        }
    }
}
