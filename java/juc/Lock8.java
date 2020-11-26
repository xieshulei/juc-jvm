package juc;
// 高聚合低耦合的前提下，线程操作资源类
// 判断  干活  通知
// 多线程交互中，防止虚假唤醒 只能用while
// 标志位
// 两个静态同步方法

import java.util.concurrent.TimeUnit;

class Phone{
    public synchronized void sendEmail() throws InterruptedException {
        TimeUnit.SECONDS.sleep(4);
        System.out.println("--------sendEmail");
    }

    public synchronized void sendSMS(){
        System.out.println("---------sendSMS");
    }

}
public class Lock8 {
    public static void main(String[] args) throws InterruptedException {
        Phone phone = new Phone();
        new Thread(() -> {
            try {
                phone.sendEmail();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"线程名字").start();

        Thread.sleep(200);

        new Thread(() -> {
            phone.sendSMS();
        },"线程名字").start();
    }
}
