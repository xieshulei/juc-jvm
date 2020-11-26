package juc;

interface Foo{
    public void sayHello();

    default int div(int x,int y){
        System.out.println("hello java");
        return x/y;
    }
}

// 何为lambda表达式
// 拷贝小括号 写死右箭头 落地大括号

public class LambdaExpressDemo {

    public static void main(String[] args) {

        // 匿名内部类
//        Foo foo = new Foo() {
//            @Override
//            public void sayHello() {
//                System.out.println("Hello java02");
//            }
//        };
//        foo.sayHello();
        Foo foo = () -> {

        };
        foo.sayHello();
    }
}
