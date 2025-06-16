package com.pray.common;


import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * PrayThreadPoolExecutor
 *
 * @author Cotton Eye Joe
 * @since 2024/11/9 20:40
 */
public class PrayThreadPoolExecutor {
    private int corePoolSize = 2;
    private int maximumPoolSize = 4;
    private long keepAliveTime = 30L;
    private TimeUnit unit;
    private int queueSize;
    private ThreadPoolExecutor pool;

    /**
     * 限定好死的固定无参线程池,同时私有化构造方法，防止随意创建
     */
    private PrayThreadPoolExecutor() {
        this.unit = TimeUnit.MINUTES;
        this.queueSize = 2;
        this.pool = null;
    }

    private PrayThreadPoolExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, int queueSize) {
        this.corePoolSize = corePoolSize;
        this.maximumPoolSize = maximumPoolSize;
        this.keepAliveTime = keepAliveTime;
        this.unit = TimeUnit.MINUTES;
        this.queueSize = 2;
    }

    /**
     * 系统提供的默认参数线程池
     * @return PrayThreadPoolExecutor
     */
    public static PrayThreadPoolExecutor getPrayExecutor(){
        return new PrayThreadPoolExecutor();
    }

    /**
     * 用户自定义参数的线程池
     * @param corePoolSize
     * @param maximumPoolSize
     * @param keepAliveTime
     * @param unit
     * @param queueSize
     * @return PrayThreadPoolExecutor
     */
    public static PrayThreadPoolExecutor getPrayExecutor(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, int queueSize){
        return new PrayThreadPoolExecutor(corePoolSize,maximumPoolSize,keepAliveTime,unit,queueSize);
    }
    public void destory() {
        if (this.pool != null) {
            this.pool.shutdownNow();
        }
    }

    /**
     * 建造者模式
     * @param corePoolSize
     * @return
     */
    public PrayThreadPoolExecutor corePoolSize(int corePoolSize) {
        this.corePoolSize = corePoolSize;
        return this;
    }

    public PrayThreadPoolExecutor maximumPoolSize(int maximumPoolSize) {
        this.maximumPoolSize = maximumPoolSize;
        return this;
    }

    public PrayThreadPoolExecutor keepAliveTime(long keepAliveTime) {
        this.keepAliveTime = keepAliveTime;
        return this;
    }
    public PrayThreadPoolExecutor unit(TimeUnit unit) {
        this.unit = unit;
        return this;
    }

    public PrayThreadPoolExecutor queueSize(int queueSize) {
        this.queueSize = queueSize;
        return this;
    }
    public ExecutorService build() {
        this.pool = new ThreadPoolExecutor(this.corePoolSize, this.maximumPoolSize, this.keepAliveTime, this.unit, new ArrayBlockingQueue<>(this.queueSize), new PrayThreadFactory(), new PrayRejectedExecutionHandler());
        return this.pool;
    }
    private static class PrayRejectedExecutionHandler implements RejectedExecutionHandler{
        private PrayRejectedExecutionHandler() {
        }

        @Override
        public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
            try {
                executor.getQueue().put(r);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }
    private static class PrayThreadFactory implements ThreadFactory{
        private final AtomicInteger count = new AtomicInteger(0);

        private PrayThreadFactory() {
        }
        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r);
            String simpleName = PrayThreadPoolExecutor.class.getSimpleName();//反射中获取当前类名，不带包的路径
            String threadName = simpleName+this.count.addAndGet(1);
            thread.setName(threadName);
            return thread;
        }
    }
}
