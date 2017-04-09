package collection.queue;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * Created by wizardholy on 2017/4/8.
 PriorityQueue是个基于优先级堆的极大优先级队列。
 此队列按照在构造时所指定的顺序对元素排序，既可以根据元素的自然顺序来指定排序（参阅 Comparable），
 也可以根据 Comparator 来指定，这取决于使用哪种构造方法。优先级队列不允许 null 元素。
 依靠自然排序的优先级队列还不允许插入不可比较的对象（这样做可能导致 ClassCastException）


 */
public class PriorityBlockingQueueDemo {
    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        PriorityBlockingQueue<Integer> qi = new PriorityBlockingQueue<>();

        qi.add(5);
        qi.add(2);
        qi.add(1);
        qi.add(10);
        qi.add(3);
        while (!qi.isEmpty()){
            System.out.print(qi.poll() + ",");
        }
        System.out.println();
        System.out.println("-----------------------------");

        Comparator<Integer> cmp;
        cmp = new Comparator<Integer>() {
            public int compare(Integer e1, Integer e2) {
                return e2 - e1;
            }
        };
        Queue<Integer> q2 = new PriorityQueue<Integer>(5,cmp);
        q2.add(2);
        q2.add(8);
        q2.add(9);
        q2.add(1);
        while (!q2.isEmpty()){
            System.out.print(q2.poll() + ",");
        }
    }

}
