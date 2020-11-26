package juc;

// 高聚合低耦合的前提下，线程操作资源类
// 判断  干活  通知
// 多线程交互中，防止虚假唤醒 只能用while
// 标志位


import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class ShareResource{
     private int number = 1;// 1:A 2:B 3:C
    private Lock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();


    public void print5(){
        lock.lock();
        try {
            // 判断
            while (number != 1){
                condition1.await();
            }
            // 干活
            for (int i = 0;i<5;i++){
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            // 通知
            number = 2;
            condition2.signal();
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
            lock.unlock();

                }
    }
    public void print10(){
        lock.lock();
        try {
            // 判断
            while (number != 2){
                condition2.await();
            }
            // 干活
            for (int i = 0;i<10;i++){
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            // 通知
            number = 3;
            condition3.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();

        }
    }
    public void print15(){
        lock.lock();
        try {
            // 判断
            while (number != 3){
                condition3.await();
            }
            // 干活
            for (int i = 0;i<15;i++){
                System.out.println(Thread.currentThread().getName()+"\t"+i);
            }
            // 通知
            number = 1;
            condition1.signal();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            lock.unlock();

        }
    }

}

public class ThreadOrderAccess {

    public static void main(String[] args) {
        ShareResource shareResource = new ShareResource();

        new Thread(() ->{
            for (int i = 0;i<10;i++){
                shareResource.print5();
            }
        },"A").start();

        new Thread(() -> {
            for (int i = 0;i<10;i++){
                shareResource.print10();
            }
        },"B").start();

        new Thread(() -> {
            for (int i = 0;i<10;i++){
                shareResource.print15();
            }
        },"C").start();

    }
}
