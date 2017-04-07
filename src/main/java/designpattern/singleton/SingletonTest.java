package designpattern.singleton;

/**
 * Created by wizardholy on 2017/3/29.
 */
public class SingletonTest {
    public static void main(String[] args){
        HungrySingle.getInstance();
        LazySingleton.getInstance();
        DCLSingleton.getInstance();
        StaticSingle.getInstance();
    }
}
// 恶汉模式
// 优点：线程安全，调用时反应速度快，在类加载的同时已经创建好了一个静态对象（创建的唯一对象）；
// 缺点：资源利用效率不高，可能该实例并不需要，但也被系统加载了。
class HungrySingle {
    private HungrySingle(){
        System.out.println("ok");
    }
    private static HungrySingle instance = new HungrySingle();

    public static HungrySingle getInstance() {
        return instance;
    }
}
// 懒汉模式
// 延迟加载法在适用于单线程环境，它不是线程安全的，引入多线程时，
// 就必须通过同步来保护getInstance（）方法，否则可能会返回LazySingleton的两个不同实例。
//优点：资源利用率高,不执行getInstance就不会被实例。
//缺点：第一次加载时反应不快，多线程使用不必要的同步开销大
class LazySingleton {
    private static LazySingleton instance;
    private LazySingleton() {}

    public static LazySingleton getInstance() {
        if (instance == null) {
            instance = new LazySingleton();
        }
        return instance;
    }
}

// DCL模式
// 针对延迟加载法的同步实现所产生的性能低的问题，我们可以采用DCL，
// 即双重检查加锁（Double Check Lock）的方法来避免每次调用getInstance（）方法时都同步
//  优点：资源利用率高,不执行getInstance就不会被实例，多线程下效率高。
//  缺点：第一次加载时反应不快，由于Java 内存模型一些原因偶尔会失败，
// 在高并发环境下也有一定的缺陷，虽然发生概率很小。
class DCLSingleton {
    private volatile static DCLSingleton instance;
    private DCLSingleton() {}

    public static DCLSingleton getInstance() {
        if (instance == null) {
            synchronized(DCLSingleton.class){
                if(instance == null){
                    instance = new DCLSingleton();
                }
            }
        }
        return instance;
    }
}

// static内部类单例模式
// 类级内部类（有static修饰的成员内部类）相当于其外部类的成员，
// 只有在第一次使用时才会被装载，而不会在类加载器加载其外部类的时候被装载，
// 而且只会被加载一次。因此，资源利用率高。
//  优点：线程安全，资源利用率高,不执行getInstance就不会被实例。
//  缺点：第一次加载时反应不快。
class StaticSingle
{
    private StaticSingle() {
        System.out.println("ok");
    }

    private static class InstanceHolder{
        private static final StaticSingle instance = new StaticSingle();
    }

    public static StaticSingle getInstance(){
        return InstanceHolder.instance;
    }
}