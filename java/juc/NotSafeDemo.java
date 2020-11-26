package juc;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

//  线程安全不安全
// Arraylist线程不安全 需要用vector
// CopyOnWriteArrayList   --- juc
// 写时复制  -- 读写分离
public class NotSafeDemo {
    public static void main(String[] args) {
        // 安全
        List<String> list = Collections.synchronizedList(new ArrayList<>());
        // 安全
        List<String> list1 = new Vector<String>();

        List<String> list2 = new CopyOnWriteArrayList<String>();


        Set<String> set = new HashSet<>(); // 不安全set

        Set<String> set1 = Collections.synchronizedSet(new HashSet<>()); // 安全的set

        Set<String> set2 = new CopyOnWriteArraySet<>();

        Map map = new ConcurrentHashMap();
//        list.add("a");
//        list.add("b");
//        list.add("c");
//
//        for (String name : list){
//            System.out.println(name);
//        }

        for (int i = 0;i<3;i++){
            new Thread(() -> {
                set2.add(UUID.randomUUID().toString().substring(0,8));
                System.out.println(set2);
            },String.valueOf(i)).start();
        }
    }
}
