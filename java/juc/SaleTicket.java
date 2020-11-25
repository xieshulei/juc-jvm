// 在高内聚  低耦合的前提下，线程操作资源类 ---- juc
// 在高内聚  低耦合的前提下，线程操作资源类 ---- juc
// 在高内聚  低耦合的前提下，线程操作资源类 ---- juc
// 在高内聚  低耦合的前提下，线程操作资源类 ---- juc
// 在高内聚  低耦合的前提下，线程操作资源类 ---- juc
// 在高内聚  低耦合的前提下，线程操作资源类 ---- juc
// 在高内聚  低耦合的前提下，线程操作资源类 ---- juc
// 在高内聚  低耦合的前提下，线程操作资源类 ---- juc
// 在高内聚  低耦合的前提下，线程操作资源类 ---- juc
// 在高内聚  低耦合的前提下，线程操作资源类 ---- juc
// 在高内聚  低耦合的前提下，线程操作资源类 ---- juc
// 在高内聚  低耦合的前提下，线程操作资源类 ---- juc

package juc;
public class SaleTicket {

    public static void main(String[] args) {
        Ticket ticket = new Ticket();

        // 接口也可以new
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 1;i<40;i++){
                    ticket.saleTicket();
                }

            }
        },"A").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0;i < 40;i++){
                    ticket.saleTicket();
                }
            }
        },"B");

        new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0;i<40;i++){
                            ticket.saleTicket();
                        }
                    }
                },"C").start();

    }
}



class Ticket {

    private int number = 90;
    // 操作
//    1. 修饰一个代码块，被修饰的代码块称为同步语句块，其作用的范围是大括号{}括起来的代码，作用的对象是调用这个代码块的对象；
//    2. 修饰一个方法，被修饰的方法称为同步方法，其作用的范围是整个方法，作用的对象是调用这个方法的对象；
//    3. 修饰一个静态的方法，其作用的范围是整个静态方法，作用的对象是这个类的所有对象；
//    4. 修饰一个类，其作用的范围是synchronized后面括号括起来的部分，作用主的对象是这个类的所有对象。
    public synchronized void saleTicket(){
        if (number > 0)
        {
            System.out.println(Thread.currentThread().getName() +
                    "\t卖出第："+(number -- )+"\t张票"+"还剩下"+number);
        }
    }
}

