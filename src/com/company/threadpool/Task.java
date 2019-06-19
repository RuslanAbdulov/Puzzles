package com.company.threadpool;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

public class Task implements Delayed {

    private final Runnable runnable;
    private final long delay; //ms
    private final long executeAt; //ms


    public Task(Runnable runnable, long delay) {
        this.runnable = runnable;
        this.delay = delay;
        this.executeAt = System.currentTimeMillis() + delay;
    }

    public long getExecuteAt() {
        return executeAt;
    }

    public Runnable getRunnable() {
        return runnable;
    }

    public long getDelay() {
        return delay;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        long diff = executeAt - System.currentTimeMillis();
        return unit.convert(diff, TimeUnit.MILLISECONDS);
    }

    @Override
    public int compareTo(Delayed o) {
        return Long.compare(this.executeAt, ((Task)o).getExecuteAt());
    }
}