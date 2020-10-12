package com.junpenghe;


import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author Junpeng.He
 */
public class ThreadPool {

    private final int maxPoolSize;
    private ThreadPoolExecutor threadPoolExecutor;
    private final int queueSize;

    public ThreadPool(int maxPoolSize, int queueSize) {
        this.maxPoolSize = maxPoolSize;
        this.queueSize = queueSize;
        this.threadPoolExecutor = initThreadPool();
    }

    public void execute(Runnable workThread) {
        threadPoolExecutor.execute(workThread);
    }

    public void shutDownNow() {
        if (!threadPoolExecutor.isShutdown()) {
            threadPoolExecutor.shutdownNow();
        }
    }

    public void restart() {
        if (threadPoolExecutor.isShutdown()) {
            threadPoolExecutor = initThreadPool();
        }
    }

    public boolean isShutDown() {
        return threadPoolExecutor.isShutdown();
    }

    /**
     * check the threadPoolExecutor is able to execute a new task
     * need to use synchronized keyword here, otherwise it can return true to multiple threads at the same time
     */
    public synchronized boolean available() {
        return threadPoolExecutor.getActiveCount() < this.maxPoolSize || threadPoolExecutor.getQueue().size() < this.queueSize;
    }

    private ThreadPoolExecutor initThreadPool() {
        return new ThreadPoolExecutor(1, maxPoolSize, 5L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(queueSize));
    }
}
