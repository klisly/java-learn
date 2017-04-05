package collection.concurrent.atmoic;

import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by wizardholy on 2017/4/1.
 */
public class AtomicReferenceTest {
    /**
     * 相关方法列表
     * @see AtomicReference#compareAndSet(Object, Object) 对比设置值，参数1：原始值，参数2：修改目标引用
     * @see AtomicReference#getAndSet(Object) 将引用的目标修改为设置的参数，直到修改成功为止，返回修改前的引用
     */
    public final static AtomicReference<String> ATOMIC_REFERENCE = new AtomicReference<String>("abc");

    public static void main(String []args) {
        for(int i = 0 ; i < 100 ; i++) {
            final int num = i;
            new Thread() {
                public void run() {
                    try {
                        Thread.sleep(Math.abs((int)(Math.random() * 100)));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if(ATOMIC_REFERENCE.compareAndSet("abc", new String("abc"))) {
                        System.out.println("我是线程：" + num + ",我获得了锁进行了对象修改！");
                    }
                }
            }.start();
        }
    }
}
