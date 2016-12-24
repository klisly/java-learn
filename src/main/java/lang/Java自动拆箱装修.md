在本文中，笔者向大家介绍下Java中一个非常重要也非常有趣的特性，就是自动装箱与拆箱，并从源码中解读自动装箱与拆箱的原理，同时这种特性也留有一个陷阱。开发者如果不注意，就会很容易跌入这个陷阱。
自动装箱(Autoboxing)
定义
大家在平时编写Java程序时，都常常以以下方式来定义一个Integer对象:
Integer i=100;
从上面的代码中，大家可以得知，i为一个Integer类型的引用，100为Java中的基础数据类型(primitive data type)。而这种直接将一个基础数据类型传给其相应的封装类(wrapper class)的做法，便是自动装箱(Autoboxing)。
在jdk 1.5中，自动装箱首次被引入。而在jdk 1.5之前，如果你想要定义一个value为100的Integer对象，则需要这样做：
Integer i=new Integer (100);
原理
我们在以上代码“Integer i=100;”处打一个断点，跟踪一下。

接下来，我们可以看到，程序跳转到了Integer类的valueOf(int i)方法中
/**
   * Returns a <tt>Integer</tt> instance representing the specified
   * <tt>int</tt> value.
   * If a new <tt>Integer</tt> instance is not required, this method
   * should generally be used in preference to the constructor
   * {@link #Integer(int)}, as this method is likely to yield
   * significantly better space and time performance by caching
   * frequently requested values.
   *
   * @param i an <code>int</code> value.
   * @return a <tt>Integer</tt> instance representing <tt>i</tt>.
   * @since 1.5
   */
  public static Integer valueOf(int i) {
    if(i >= -128 && i <= IntegerCache.high)
      return IntegerCache.cache[i + 128];
    else
      return new Integer(i);
  }
换句话说，装箱就是jdk自己帮你完成了调用Integer.valueOf(100)。
拆箱(Unboxing)
定义
?
1
2
Integer integer100=100;
int int100=integer100;
从上面的代码中，大家可看出integer100为一个Integer类型的引用，int100为一个int类型的原始数据类型。但是，我们可以将一个Integer类型的对象赋值给其相应原始数据类型的变量。这便是拆箱。
拆箱与装箱是相反的操作。装箱是将一个原始数据类型赋值给相应封装类的变量。而拆箱则是将一个封装类的变量赋值给相应原始数据类型的变量。装箱、拆箱的名字也取得相当贴切。
原理
笔者相信大家也都猜到了，拆箱过程中jdk为我们做了什么。我们还是通过实验来证明我们的猜想吧。
在以上代码的第二行代码打上断点，即在“int int100=integer100;”上打上断点，跟踪一下。
我们可以看到，程序跳转到了Integer的intValue()方法。

/**
   * Returns the value of this <code>Integer</code> as an
   * <code>int</code>.
   */
  public int intValue() {
    return value;
  }
也就是，jdk帮我们完成了对intValue()方法的调用。对于以上的实验而言，便是调用integer100的intValue()方法，将其返回值赋给了int100。
扩展
实验1
Integer integer400=400;
int int400=400;
System.out.println(integer400==int400);
在以上代码的第三行中，integer400与int400执行了==运行。而这两个是不同类型的变量，到底是integer400拆箱了，还是int400装箱了呢？运行结果是什么呢？
 ==运算是判断两个对象的地址是否相等或者判断两个基础数据类型的值是否相等。所以，大家很容易推测到，如果integer400拆箱了，则说明对比的是两个基础类型的值，那此时必然相等，运行结果为true；如果int400装箱了，则说明对比的是两个对象的地址是否相等，那此时地址必然不相等，运行结果为false。（至于为什么笔者对它们赋值为400，就是后面将要讲到的陷阱有关）。
我们实际的运行结果为true。所以是integer400拆箱了。对代码跟踪的结果也证明这一点。
实验2

Integer integer100=100;
int int100=100;
System.out.println(integer100.equals(int100));
在以上代码的第三行中，integer100的方法equals的参数为int100。我们知道equals方法的参数为Object，而不是基础数据类型，因而在这里必然是int100装箱了。对代码跟踪的结果也证明了这一点。
其实，如果一个方法中参数类型为原始数据类型，所传入的参数类型为其封装类，则会自动对其进行拆箱；相应地，如果一个方法中参数类型为封装类型，所传入的参数类型为其原始数据类型，则会自动对其进行装箱。
实验3
Integer integer100 = 100;
int int100 = 100;
Long long200 = 200l;
System.out.println(integer100 + int100);
System.out.println(long200 == (integer100 + int100));
System.out.println(long200.equals(integer100 + int100));
在第一个实验中，我们已经得知，当一个基础数据类型与封装类进行==运算时，会将封装类进行拆箱。那如果+、-、*、/呢？我们在这个实验中，就可知道。
如果+运算，会将基础数据类型装箱，那么：
•第4行中，integer100+int100就会得到一个类型为Integer且value为200的对象o，并执行这个对象的toString()方法，并输出”200”；
•第5行中，integer100+int100就会得到一个类型为Integer且value为200的对象o，==运算将这个对象与long200对象进行对比，显然，将会输出false；
•第6行中，integer100+int100就会得到一个类型为Integer且value为200的对象o，Long的equals方法将long200与o对比，因为两都是不同类型的封装类，因而输出false；
如果+运算，会将封装类进行拆箱，那么:
•第4行中，integer100+int100就会得到一个类型为int且value为200的基础数据类型b，再将b进行装箱得到o，执行这个对象的toString()方法，并输出”200”；
•第5行中，integer100+int100就会得到一个类型为int且value为200的基础数据类型b1，==运算将long200进行拆箱得到b2，显然b1==b2，输出true；
•第6行中，integer100+int100就会得到一个类型为int且value为200的基础数据类型b，Long的equals方法将b进行装箱，但装箱所得到的是类型为Integer的对象o，因为o与long200为不同的类型的对象，所以输出false；
程序运行的结果为:
200
true
false
因而，第二种推测是正确，即在+运算时，会将封装类进行拆箱。
陷阱
陷阱1
Integer integer100=null;
int int100=integer100;
这两行代码是完全合法的，完全能够通过编译的，但是在运行时，就会抛出空指针异常。其中，integer100为Integer类型的对象，它当然可以指向null。但在第二行时，就会对integer100进行拆箱，也就是对一个null对象执行intValue()方法，当然会抛出空指针异常。所以，有拆箱操作时一定要特别注意封装类对象是否为null。
陷阱2
Integer i1=100;
Integer i2=100;
Integer i3=300;
Integer i4=300;
System.out.println(i1==i2);
System.out.println(i3==i4);
因为i1、i2、i3、i4都是Integer类型的，所以我们想，运行结果应该都是false。但是，真实的运行结果为“System.out.println(i1==i2);”为 true，但是“System.out.println(i3==i4);”为false。也就意味着,i1与i2这两个Integer类型的引用指向了同一个对象，而i3与i4指向了不同的对象。为什么呢？不都是调用Integer.valueOf(int i)方法吗？
让我们再看看Integer.valueOf(int i)方法。

/**
   * Returns a <tt>Integer</tt> instance representing the specified
   * <tt>int</tt> value.
   * If a new <tt>Integer</tt> instance is not required, this method
   * should generally be used in preference to the constructor
   * {@link #Integer(int)}, as this method is likely to yield
   * significantly better space and time performance by caching
   * frequently requested values.
   *
   * @param i an <code>int</code> value.
   * @return a <tt>Integer</tt> instance representing <tt>i</tt>.
   * @since 1.5
   */
  public static Integer valueOf(int i) {
    if(i >= -128 && i <= IntegerCache.high)
      return IntegerCache.cache[i + 128];
    else
      return new Integer(i);
  }
我们可以看到当i>=-128且i<=IntegerCache.high时，直接返回IntegerCache.cache[i + 128]。其中，IntegerCache为Integer的内部静态类，其原码如下：

private static class IntegerCache {
    static final int high;
    static final Integer cache[];

    static {
      final int low = -128;

      // high value may be configured by property
      int h = 127;
      if (integerCacheHighPropValue != null) {
        // Use Long.decode here to avoid invoking methods that
        // require Integer's autoboxing cache to be initialized
        int i = Long.decode(integerCacheHighPropValue).intValue();
        i = Math.max(i, 127);
        // Maximum array size is Integer.MAX_VALUE
        h = Math.min(i, Integer.MAX_VALUE - -low);
      }
      high = h;

      cache = new Integer[(high - low) + 1];
      int j = low;
      for(int k = 0; k < cache.length; k++)
        cache[k] = new Integer(j++);
    }

    private IntegerCache() {}
  }
我们可以清楚地看到，IntegerCache有静态成员变量cache，为一个拥有256个元素的数组。在IntegerCache中也对cache进行了初始化，即第i个元素是值为i-128的Integer对象。而-128至127是最常用的Integer对象，这样的做法也在很大程度上提高了性能。也正因为如此，“Integeri1=100;Integer i2=100;”，i1与i2得到是相同的对象。
对比扩展中的第二个实验，我们得知，当封装类与基础类型进行==运行时，封装类会进行拆箱，拆箱结果与基础类型对比值；而两个封装类进行==运行时，与其它的对象进行==运行一样，对比两个对象的地址，也即判断是否两个引用是否指向同一个对象。
以上这篇浅谈Java自动装箱与拆箱及其陷阱就是小编分享给大家的全部内容了，希望能给大家一个参考，也希望大家多多支持脚本之家。