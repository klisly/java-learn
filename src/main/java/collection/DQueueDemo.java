package collection;

import java.util.Deque;
import java.util.LinkedList;

public class DQueueDemo {
    public static void main(String [] args) {
        Deque<String> queue = new LinkedList<String>();
        //追加元素
        queue.offer("one");
        queue.offer("two");
        queue.offer("three");
        queue.offer("four");
        System.out.println(queue);
        //从队首取出元素并删除
        String poll = queue.poll();
        System.out.println(poll);
        System.out.println(queue);
        //从队首取出元素但是不删除
        String peek = queue.peek();
        System.out.println(peek);
        System.out.println(queue);
        // 获取size
        System.out.println( queue.size());
        //遍历队列，这里要注意，每次取完元素后都会删除，整个
        //队列会变短，所以只需要判断队列的大小即可
        while(queue.size() > 0) {
            System.out.println(queue.poll());
        }

        queue.add("s");
        queue.add("d");
        // 获取size
        System.out.println( queue.size());
        queue.remove();
        // 获取size
        System.out.println( queue.size());

    }
}