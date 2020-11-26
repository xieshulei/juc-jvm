package juc;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class Mycache {
    private volatile Map<String,Object> map = new HashMap<>();
    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    public void put(String key,Object value) throws InterruptedException {

        readWriteLock.writeLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t开始写入");
            TimeUnit.MILLISECONDS.sleep(300);
            map.put(key, value);

            System.out.println(Thread.currentThread().getName() + "\t写入完成");
        }finally {
            readWriteLock.writeLock().unlock();
        }


    }
    public void get(String key) throws InterruptedException {
        readWriteLock.readLock().lock();
        try {
            System.out.println(Thread.currentThread().getName() + "\t读取数据");
            TimeUnit.MILLISECONDS.sleep(300);
            Object result = map.get(key);
            System.out.println(Thread.currentThread().getName() + "\t读取完成");
        }finally {
            readWriteLock.readLock().unlock();
        }

    }
}
// 读写锁  读读可共享  写独占
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        Mycache mycache = new Mycache();
        for (int i = 0;i<5;i++){
            int finalI = i;
            new Thread(() -> {
                try {
                    mycache.put(finalI + "", finalI);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }

        for (int i = 0;i<5;i++){
            int finalI = i;
            new Thread(() -> {
                try {
                    mycache.get(finalI + "");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            },String.valueOf(i)).start();
        }

    }
}
