package collection;

import java.util.Deque;
import java.util.LinkedList;

public class DQueueDemo {
    public static void main(String [] args) {
        Deque<String> queue = new LinkedList<String>();
        //追加元素
        queue.offer("one");
        queue.offerFirst("two");
        queue.offer("three");
        queue.offerLast("four");
        queue.offer("five");
        queue.offerFirst("six");
        queue.offer("seven");
        queue.offerLast("eight");
        System.out.println(queue);
        //从队首取出元素并删除
        String poll = queue.poll();
        System.out.println("poll item:"+poll);
        System.out.println("after poll:"+queue);
        //从队首取出元素但是不删除
        String peek = queue.peek();
        System.out.println("peek item:"+peek);
        System.out.println("after peek:"+queue);
        //从队首取出元素但是不删除
        peek = queue.peekFirst();
        System.out.println("peekFirst item:"+peek);
        System.out.println("after peekFirst:"+queue);
        //从队尾取出元素但是不删除
        peek = queue.peekLast();
        System.out.println("peekLast item:"+peek);
        System.out.println("after peekLast:"+queue);
        //从队首取出元素并删除
        peek = queue.pollFirst();
        System.out.println("pollFirst item:"+peek);
        System.out.println("after pollFirst:"+queue);
        //从队尾取出元素且删除
        peek = queue.pollLast();
        System.out.println("pollLast item:"+peek);
        System.out.println("after pollLast:"+queue);
        // 获取size
        System.out.println( queue.size());
        //遍历队列，这里要注意，每次取完元素后都会删除，整个
//        //队列会变短，所以只需要判断队列的大小即可
//        while(queue.size() > 0) {
//            System.out.println(queue.poll());
//        }

        queue.add("s");
        queue.add("d");
        // 获取size
        System.out.println( queue.size());
        queue.remove();
        // 获取size
        System.out.println( queue.size());
        System.out.println("final:"+queue);

    }
}