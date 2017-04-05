单例设计模式的N中Java实现方法


目录(?)[+]
转载请注明出处：http://blog.csdn.net/ns_code/article/details/17359719

 

特点
    单例模式的特点：

    1、只能有一个实例；

    2、必须自己创建自己的一个实例；

    3、必须给所有其他对象提供这一实例。

 

饿汉式单例模式
    也称为预先加载法，实现方式如下：

[java] view plain copy 在CODE上查看代码片派生到我的代码片
class Single   
{  
    private Single()(  
        Syustem.out.println("ok");  
    )  
    private static Single instance = new Single();  
  
    public static Single getInstance(){  
        return instance;  
    }  
}  
   优点：线程安全，调用时反应速度快，在类加载的同时已经创建好了一个静态对象（创建的唯一对象）；

   缺点：资源利用效率不高，可能该实例并不需要，但也被系统加载了。另外，饿汉式在一些场景下是无法使用的，比如，如果Single实例的创建依赖参数或配置文件，则在getInstance()之前必须调用某个方法来设置这些参数，但在设置之前，可能已经new了Single实例，这种情况下，饿汉式的写法是无法使用的。

 

懒汉式单例模式
    也称为延迟加载法，实现方式如下：

[java] view plain copy 在CODE上查看代码片派生到我的代码片
public class LazySingleton {  
    private static LazySingleton instance;      
    private LazySingleton() {}  
      
    public static LazySingleton getInstance() {               
        if (instance == null) {                         
            instance = new LazySingleton();             
        }  
        return instance;                                        
    }  
}  
 

    延迟加载法在适用于单线程环境，它不是线程安全的，引入多线程时，就必须通过同步来保护getInstance（）方法，
    否则可能会返回LazySingleton的两个不同实例。比如，一个线程在判断instance为null后，
    还没来得及创建新的instance，另一个线程此时也判断到instance为null，
    这样两个线程便会创建两个LazySingleton实例。

    可以将getInstance（）方法改为同步方法，这样便可以避免上述问题，改进后的单例模式实现如下：

[java] view plain copy 在CODE上查看代码片派生到我的代码片
public class LazySingleton {  
    private static LazySingleton instance;      
    private LazySingleton() {}  
      
    public static synchronized LazySingleton getInstance() {               
        if (instance == null) {                         
            instance = new LazySingleton();             
        }  
        return instance;                                        
    }  
}  
    优点：资源利用率高,不执行getInstance就不会被实例。

    缺点：第一次加载时反应不快，多线程使用不必要的同步开销大

 

    这里的缺点主要是：每次调用getInstance（）方法时，都要同步，而且很多时候的同步是没必要的，这将会极大地拖垮性能（尤其在需要多次调用getInstance方法的地方，当第一次创建了LazySingleton实例后，instance便不再为null，这样后面每次调用getInstance进入方法体后，却便发现自己什么也不用做，而每次调用getInstnce都要同步，需要切换到内核，这样便很浪费资源，每次做很大开销进入方法体，却发现自己什么也不用做）。

 

DCL单例模式
    针对延迟加载法的同步实现所产生的性能低的问题，我们可以采用DCL，即双重检查加锁（Double Check Lock）的方法来避免每次调用getInstance（）方法时都同步。实现方式如下：

[java] view plain copy 在CODE上查看代码片派生到我的代码片
public class LazySingleton {  
    private static LazySingleton instance;      
    private LazySingleton() {}  
      
    public static LazySingleton getInstance() {               
        if (instance == null) {    
            synchronized(LazySingleton.class){  
                if(instance == null){  
                    instance = new LazySingleton();    
                }  
            }  
        }  
        return instance;                                        
    }  
}  
    优点：资源利用率高,不执行getInstance就不会被实例，多线程下效率高。

    缺点：第一次加载时反应不快，由于Java 内存模型一些原因偶尔会失败，在高并发环境下也有一定的缺陷，虽然发生概率很小。

    DCL对instance进行了两次null判断，第一层判断主要是为了避免不必要的同步，第二层的判断则是为了在null的情况下创建实例。


    对于DCL的不安全性，我们来看看如下场景：

    假设线程A执行到instance = new LazySingleton()这句，这里看起来是一句话，但实际上它并不是一个原子操作，我们只要看看这句话被编译后在JVM执行的对应汇编代码就发现，这句话被编译成8条汇编指令，大致做了3件事情：

 1.给LazySingleton的实例分配内存。

2.初始化LazySingleton()的构造器

3.将instance对象指向分配的内存空间（注意到这步instance就非null了）。

    但是，由于Java编译器允许处理器乱序执行，以及JDK1.5之前JMM（Java Memory Medel，即Java内存模型）中Cache、寄存器到主内存回写顺序的规定，上面的第二点和第三点的顺序是无法保证的，也就是说，执行顺序可能是1-2-3也可能是1-3-2，如果是后者，并且在3执行完毕、2未执行之前，被切换到线程B上，这时候instance因为已经在线程A内执行过了第三点，instance已经是非空了，所以线程B直接拿走instance，然后使用，然后顺理成章地报错，而且这种难以跟踪难以重现的错误很可能会隐藏很久。

    DCL的写法来实现单例是很多技术书、教科书（包括基于JDK1.4以前版本的书籍）上推荐的写法，实际上是不完全正确的。的确在一些语言（譬如C语言）上DCL是可行的，但这取决于是否能保证2、3步的顺序。在JDK1.5之后，官方已经注意到这种问题，调整了JMM、具体化了volatile关键字，因此如果JDK是1.5或之后的版本，只需要将instance的定义改成“private volatile static LazySingleton instance = null;”就可以保证每次都去instance都从主内存读取，就可以使用DCL的写法来完成单例模式。当然volatile或多或少也会影响到性能，最重要的是我们还要考虑JDK1.4以及之前的版本，所以本文中单例模式写法的改进还在继续。

 

static内部类单例模式
    该方法是为了解决DCL方法在并发环境中的缺陷而提出的，关于DCL在并发编程中存在的问题可以参考这篇文章：http://blog.csdn.net/ns_code/article/details/17348313的后半部分，其实现方式如下：

[java] view plain copy 在CODE上查看代码片派生到我的代码片
class Single   
{  
    private Single()(  
        Syustem.out.println("ok");  
    )  
      
    private static class InstanceHolder{  
        private static final Singlet instance = new Single();  
    }  
  
    public static Single getInstance(){  
        return InstanceHolder.instance;  
    }  
}  
    优点：线程安全，资源利用率高,不执行getInstance就不会被实例。

    缺点：第一次加载时反应不快。

 

    这里针对最后一种方法补充以下基本知识点：类级内部类（有static修饰的成员内部类）相当于其外部类的成员，只有在第一次使用时才会被装载，而不会在类加载器加载其外部类的时候被装载，而且只会被加载一次。因此，资源利用率高。

 

    总结：在Java中由于会涉及到并发编程，考虑到效率、安全性等问题，一般常用饿汉式单例模式或static内部类单例模式，而后者又是最优且最常用的单例设计模式的实现方法。

 

 

参考资料：http://blog.csdn.net/ns_code/article/details/17348313

                 http://www.myexception.cn/software-architecture-design/1235112.html

                 http://m.blog.csdn.net/blog/codezjx/8883599

                    http://blog.sina.com.cn/s/blog_6d2890600101gb8x.html 

