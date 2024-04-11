package com.pray.common;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * RestrictHandler
 * <p>
 *
 * @author 春江花朝秋月夜
 * @since 2023/8/25 13:13
 */
@Component
@Data
public class RestrictHandler {
    int limit;
    int time;

    private boolean state = false;
    private BlockingQueue<TimeDelay> delayQueue=new DelayQueue<>();
    private Map<String, Data> map = new HashMap<>();

    public void init(String key,Integer value,long time){
        map.put(key, new Data(value, time));
        delayQueue.add(new TimeDelay(key, time));
    }
    private void removeDelayKey(String key){
        delayQueue.remove(new TimeDelay(key));
    }
    public boolean check(String key,Integer limit){
        if (!map.containsKey(key)){
            return false;
        }
        Data data=map.get(key);
        boolean res=data.value>=limit;
        if (res){
            removeDelayKey(key);
            delayQueue.add(new TimeDelay(key,time));//添加到延时队列里面
        }
        return res;
    }
    @lombok.Data
    @NoArgsConstructor
    private class TimeDelay implements Delayed{
        String key;
        long expire;

        public TimeDelay(String key, long expire) {
            init();
            this.key = key;
            this.expire =new Date().getTime() + (expire * 1000);
        }

        public TimeDelay(String key) {
            this.key = key;
        }

        private void init(){
            if (!state){
                state=true;
                new Thread(()->{
                    while (true){
                        try {
                            TimeDelay task=delayQueue.take();
                            map.remove(task.key);
                        }catch (InterruptedException e){
                            e.printStackTrace();
                        }
                    }
                },"RestrictHandlerTask").start();
            }
        }

        @Override
        public int compareTo(Delayed o) {
            return (int) (this.getDelay(TimeUnit.MILLISECONDS)-o.getDelay(TimeUnit.MILLISECONDS));
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(this.expire-System.currentTimeMillis(),TimeUnit.MICROSECONDS);
        }
    }
    private static class Data{
        Integer value;
        Long time;

        public Data(Integer value, long time) {

            this.value = value;
            this.time = time;
        }
        public void incr(){
            this.value+=1;
        }
        public Data(){

        }
    }
}
