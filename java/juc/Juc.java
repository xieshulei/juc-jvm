package juc;

public class Juc {
    public static void main(String[] args) {
        // java运行原理

        // 创建线程
        Thread t = new ThreadTest();
        // 启动线程
        t.start(); // 执行瞬间结束，告诉java虚拟机分配一个新的栈给t
        // run方法不需要手动调用，系统启动后自动调用
        // 多线程之后main方法结束，只是主线程栈弹空了，但是其他线程中还没结束，
        // main方法结束程序还在运行

        for (int i = 0;i<10;i++){
            System.out.println("main--->" + i);
        }
    }

}

class ThreadTest extends Thread{
    public void run(){
        for (int i = 0;i<100;i++){
            System.out.println("run-->" + i);
        }
    }
}

// 实现线程的第二种方式
// 写一个Runable接口
// 实现run方法
class ThreadTest02{
    public static void main(String[] args) {
        // 创建线程
        Thread t1 = new Thread(new Processor());
        // 启动线程

        t1.start();
    }


}
// 实现接口之外还保留了类的继承
class Processor implements Runnable{

    @Override
    public void run() {
        for (int i = 0;i<100;i++){
            System.out.println("--->" + i);
        }
    }
}
