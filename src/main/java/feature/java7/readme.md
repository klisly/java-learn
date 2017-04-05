Jdk1.7的新特性： 
1，switch中可以使用字串 
Java代码：
String s = "test";   
switch (s) {   
  case "test" :   
     System.out.println("test");  
  case "test1" :   
    System.out.println("test1"); 
    break ;   
  default :   
    System.out.println("break"); 
    break ;   
 }  
 
 
2，"<>"这个玩意儿的运用List<String> tempList = new ArrayList<>(); 即泛型实例化类型自动推断。
 
 
3. 语法上支持集合，而不一定是数组 
Java代码：
final List<Integer> piDigits = [ 1,2,3,4,5,8 ]; 
 
 
4. 新增一些取环境信息的工具方法 
Java代码：
File System.getJavaIoTempDir() // IO临时文件夹 
File System.getJavaHomeDir() // JRE的安装目录 
File System.getUserHomeDir() // 当前用户目录 
File System.getUserDir() // 启动java进程时所在的目录 
....... 
 
 
5. Boolean类型反转，空指针安全,参与位运算 
Java代码：
Boolean Booleans.negate(Boolean booleanObj) 
True => False , False => True, Null => Null 
boolean Booleans.and(boolean[] array) 
boolean Booleans.or(boolean[] array) 
boolean Booleans.xor(boolean[] array) 
boolean Booleans.and(Boolean[] array) 
boolean Booleans.or(Boolean[] array) 
boolean Booleans.xor(Boolean[] array) 
 
 
6. 两个char间的equals 
Java代码：
boolean Character.equalsIgnoreCase(char ch1, char ch2) 
 
 
7，安全的加减乘除 
Java代码：
int Math.safeToInt(long value)
int Math.safeNegate(int value)
long Math.safeSubtract(long value1, int value2)
long Math.safeSubtract(long value1, long value2)
int Math.safeMultiply(int value1, int value2)
long Math.safeMultiply(long value1, int value2)
long Math.safeMultiply(long value1, long value2)
long Math.safeNegate(long value)
int Math.safeAdd(int value1, int value2)
long Math.safeAdd(long value1, int value2)
long Math.safeAdd(long value1, long value2)
int Math.safeSubtract(int value1, int value2)
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
首先是模块化特性：现在的 Java7也是采用了模块的划分方式来提速，一些不是必须的模块并没有下载和安装，因此在使用全新的Java7的虚拟机的时候会发现真的很快，当虚拟机需要用到某些功能的时候，再下载和启用相应的模块，这样使得最初需要下载的虚拟机大小得到了有效的控制。同时对启动速度也有了很大的改善。如果你对 OpenJDK的架构比较熟悉，你甚至可以定制JDK的模块。
 
其次是多语言支持：这里的多语言不是指中文英文之类的语言，而是说Java7的虚拟机对多种动态程序语言增加了支持，比如：Rubby、 Python等等。对这些动态语言的支持极大地扩展了Java虚拟机的能力。对于那些熟悉这些动态语言的程序员而言，在使用Java虚拟机的过程中同样可以使用它们熟悉的语言进行功能的编写，而这些语言是跑在功能强大的JVM之上的。
 
再有是开发者的开发效率得到了改善：Java7通过多种特性来增强开发效率。比如对语言本身做了一些细小的改变来简化程序的编写，在多线程并发与控制方面：轻量级的分离与合并框架，一个支持并发访问的HashMap等等。通过注解增强程序的静态检查。提供了一些新的API用于文件系统的访问、异步的输入输出操作、Socket通道的配置与绑定、多点数据包的传送等等。
 
最后是执行效率的提高，也是给人感觉最真切体验的特性：压缩了64位的对象指针，Java7通过对对象指针由64位压缩到与32位指针相匹配的技术使得内存和内存带块的消耗得到了很大的降低因而提高了执行效率。此外还提供了新的垃圾回收机制（G1）来降低垃圾回收的负载和增强垃圾回收的效果。G1垃圾回收机制拥有更低的暂停率和更好的可预测性。
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
jdk1.7新特性
1 对集合类的语言支持； 
2 自动资源管理； 
3 改进的通用实例创建类型推断； 
4 数字字面量下划线支持； 
5 switch中使用string； 
6 二进制字面量； 
7 简化可变参数方法调用；
8 新增一些取环境信息的工具方法；
9 Boolean类型反转，空指针安全,参与位运算；
10 两个char间的equals；
11 安全的加减乘除；
12 map集合支持并发请求 ，且可以写成 Map map = {name:"xxx",age:18};
   下面我们来仔细看一下这12个新功能： 
      1 对集合类的语言支持 
      Java将包含对创建集合类的第一类语言支持。这意味着集合类的创建可以像Ruby和Perl那样了。 
 
创建List / Set / Map 时写法更简单了。
  List< String> list = ["item"];  
 String item = list[0];  
 Set< String > set = {"item"};  
 Map< String,Integer > map = {"key" : 1};  
 int value = map["key"]; 
 
 
      原本需要这样： 
         List<String> list = new ArrayList<String>(); 
         list.add("item"); 
         String item = list.get(0); 
   
         Set<String> set = new HashSet<String>(); 
         set.add("item"); 
         Map<String, Integer> map = new HashMap<String, Integer>(); 
         map.put("key", 1); 
         int value = map.get("key"); 
 
      现在你可以这样： 
         List<String> list = ["item"]; 
         String item = list[0]; 
         
         Set<String> set = {"item"}; 
         
         Map<String, Integer> map = {"key" : 1}; 
         int value = map["key"]; 
 
      这些集合是不可变的。 
 
   
      2 自动资源管理 
      Java中某些资源是需要手动关闭的，如InputStream，Writes，Sockets，Sql classes等。这个新的语言特性允许try语句本身申请更多的资源， 
   这些资源作用于try代码块，并自动关闭。 
      这个： 
         BufferedReader br = new BufferedReader(new FileReader(path)); 
         try { 
         return br.readLine(); 
               } finally { 
                   br.close(); 
         } 
 
      变成了这个： 
          try (BufferedReader br = new BufferedReader(new FileReader(path)) { 
             return br.readLine(); 
          } 
    
      你可以定义关闭多个资源： 
         try ( 
             InputStream in = new FileInputStream(src); 
             OutputStream out = new FileOutputStream(dest)) 
         { 
         // code 
         } 
      为了支持这个行为，所有可关闭的类将被修改为可以实现一个Closable（可关闭的）接口。 
   
 
      3 增强的对通用实例创建（diamond）的类型推断 
      类型推断是一个特殊的烦恼，下面的代码： 
         Map<String, List<String>> anagrams = new HashMap<String, List<String>>(); 
 
      通过类型推断后变成： 
         Map<String, List<String>> anagrams = new HashMap<>(); 
      这个<>被叫做diamond（钻石）运算符，这个运算符从引用的声明中推断类型。 
 
   
      4 数字字面量下划线支持 
      很长的数字可读性不好，在Java 7中可以使用下划线分隔长int以及long了，如： 
         int one_million = 1_000_000; 
   运算时先去除下划线，如：1_1 * 10 = 110，120 – 1_0 = 110 
   
 
      5 switch中使用string 
      以前你在switch中只能使用number或enum。现在你可以使用string了： 
         String s = ... 
         switch(s) { 
         case "quux": 
              processQuux(s); 
     // fall-through 
         case "foo": 
   case "bar": 
              processFooOrBar(s); 
     break; 
         case "baz": 
        processBaz(s); 
              // fall-through 
   default: 
              processDefault(s); 
            break; 
  } 
 
  
      6 二进制字面量 
      由于继承C语言，Java代码在传统上迫使程序员只能使用十进制，八进制或十六进制来表示数(numbers)。 
      由于很少的域是以bit导向的，这种限制可能导致错误。你现在可以使用0b前缀创建二进制字面量： 
         int binary = 0b1001_1001; 
   现在，你可以使用二进制字面量这种表示方式，并且使用非常简短的代码，可将二进制字符转换为数据类型，如在byte或short。 
   byte aByte = (byte)0b001;    
   short aShort = (short)0b010;    
 
   
      7 简化的可变参数调用 
      当程序员试图使用一个不可具体化的可变参数并调用一个*varargs* （可变）方法时，编辑器会生成一个“非安全操作”的警告。 
   JDK 7将警告从call转移到了方法声明(methord declaration)的过程中。这样API设计者就可以使用vararg，因为警告的数量大大减少了。 
 
    8  新增一些取环境信息的工具方法
File System.getJavaIoTempDir()  // IO临时文件夹    
File System.getJavaHomeDir() // JRE的安装目录    
File System.getUserHomeDir() // 当前用户目录    
File System.getUserDir() // 启动java进程时所在的目录    
   9 Boolean类型反转，空指针安全,参与位运算
Boolean Booleans.negate(Boolean booleanObj)   
True => False , False => True, Null => Null   
boolean  Booleans.and( boolean [] array)   
boolean  Booleans.or( boolean [] array)   
boolean  Booleans.xor( boolean [] array)   
boolean  Booleans.and(Boolean[] array)   
boolean  Booleans.or(Boolean[] array)   
boolean  Booleans.xor(Boolean[] array) 
10 两个char间的equals
 
boolean  Character.equalsIgnoreCase( char  ch1,  char  ch2)   
 
11 安全的加减乘除
 
int  Math.safeToInt( long  value)   
int  Math.safeNegate( int  value)   
long  Math.safeSubtract( long  value1,  int  value2)   
long  Math.safeSubtract( long  value1,  long  value2)   
int  Math.safeMultiply( int  value1,  int  value2)   
long  Math.safeMultiply( long  value1,  int  value2)   
long  Math.safeMultiply( long  value1,  long  value2)   
long  Math.safeNegate( long  value)   
int  Math.safeAdd( int  value1,  int  value2)   
long  Math.safeAdd( long  value1,  int  value2)   
long  Math.safeAdd( long  value1,  long  value2)   
int  Math.safeSubtract( int  value1,  int  value2)
 
12 map集合支持并发请求 ，且可以写成 Map map = {name:"xxx",age:18};
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
特性1：二进制字面值（Binary Literals）
在java7里，整形(byte,short,int,long)类型的值可以用二进制类型来表示了，在使用二进制的值时，需要在前面加上ob或oB，看代码 
 
Java代码 
//b 大小写都可以 
int a = 0b01111_00000_11111_00000_10101_01010_10; 
short b = (short)0b01100_00000_11111_0; 
byte c = (byte)0B0000_0001; 
 
其次，二进制同十进制和十六进制相比，可以一目了然的看出数据间的关系。例如下面这个数组中展示了每次移动一位后数字的变化。 
 
Java代码 
public static final int[] phases = { 
0b00110001, 
0b01100010, 
0b11000100, 
0b10001001, 
0b00010011, 
0b00100110, 
0b01001100, 
0b10011000 
}
如果用十六进制来表示的，它们之间的关系就无法一眼看出来了。 
 
public static final int[] phases = { 
0x31, 0x62, 0xC4, 0x89, 0x13, 0x26, 0x4C, 0x98 
} 
特性2：数字变量对下划线_的支持
你可以在数值类型的变量里添加下滑线，除了以下的几个地方不能添加： 
数字的开头和结尾 
小数点前后 
F或者L前 
 
需要出现string类型值的地方(针对用0x或0b表示十六进制和二进制，参考第一点)，比如0x101，不能用0_x101 
 
int num = 1234_5678_9; 
float num2 = 222_33F; 
long num3 = 123_000_111L; 
 
//下面的不行 
//数字开头和结尾 
int nu = _123; 
int nu = 123_; 
 
//小数点前后 
float f = 123_.12; 
float f = 123._12; 
 
//F或者L前 
long l = 123_L; 
float f = 123_F; 
 
//需要出现String的地方 
int num = 0_b123; 
float f = 0_x123F; 
 
这个，我个人觉得没什么实际作用，只是可以提升代码的可读性。
 
特性3：switch 对String的支持
这个大家期待很久了，switch终于支持String了 
public static void first() { //项目状态 String status = "approval"; //我们之前经常根据项目状态不同来进行不同的操作 //目前已经换成enum类型 switch (status) { case "shouli": System.out.println("状态是受理"); break; case "approval": System.out.println("状态是审批"); break; case "finish": System.out.println("状态是结束"); break; default: System.out.println("状态未知"); } } 每个case是使用String的equals方法来进行比较的，对大小写敏感。 和一连串的if-else-then想比，使用switch来比较String，Java编译器会生成更加有效的字节码，写一个例子测试一下。 Java代码 public static void second() { String status = "approval"; if ("shouli".equals(status)) { System.out.println("状态是受理"); } else if ("approval".equals(status)) { System.out.println("状态是审批"); } else if ("finish".equals(status)) { System.out.println("状态是结束"); } else { System.out.println("状态未知"); } }
使用javap之后，生成字节码如下：（略）
网上说，用switch之后比用if-else生成的字节码更有效一些，不过目前至少从长度上来说，switch还是长一些 
 
特性4：try-with-resources 声明
try-with-resources 是一个定义了一个或多个资源的try 声明，这个资源是指程序处理完它之后需要关闭它的对象。try-with-resources 确保每一个资源在处理完成后都会被关闭。 
可以使用try-with-resources的资源有： 
任何实现了java.lang.AutoCloseable 接口和java.io.Closeable 接口的对象。
来看例子：
public static String readFirstLineFromFile(String path) throws IOException { try (BufferedReader br = new BufferedReader(new FileReader(path))) { return br.readLine(); } } 在java 7 以及以后的版本里，BufferedReader实现了java.lang.AutoCloseable接口。 由于BufferedReader定义在try-with-resources 声明里，无论try语句正常还是异常的结束， 它都会自动的关掉。而在java7以前，你需要使用finally块来关掉这个对象。 public static String readFirstLineFromFileWithFinallyBlock(String path) throws IOException { BufferedReader br = new BufferedReader(new FileReader(path)); try { return br.readLine(); } finally { if (br != null) br.close(); } } 然而，如果 readLine() 和 close() 这两个方法都抛出异常，那么readFirstLineFromFileWithFinallyBlock 方法只会抛出后面部分也就是finally块中的内容，try块中的异常就被抑制了，对于我们的程序来说，这显然不是一种好的方式。 
 
而在java 7中，无论是try块还是try-with-resource中抛出异常，都能捕捉到。 有 
 
另外，一个try-with-resourcse声明了可以包含多个对象的声明，用分号隔开，和声明一个对象相同，会在结束后自动调用close方法，调用顺序和生命顺序相反。 
 
try ( 
java.util.zip.ZipFile zf = new java.util.zip.ZipFile(zipFileName); 
java.io.BufferedWriter writer = java.nio.file.Files.newBufferedWriter(outputFilePath, charset) 
) { 
// do something 
} 
 
此外，try-with-resources 可以跟catch和finally，catch和finally的是在try-with-resources里声明的对象关闭之后才执行的。 
 
特性5：捕获多种异常并用改进后的类型检查来重新抛出异常 
 
1、捕获多种异常
在Java SE7里，一个catch可以捕获多个异常，这样可以减少重复代码。每个异常之间用 | 隔开。
public static void first(){ try { BufferedReader reader = new BufferedReader(new FileReader("")); Connection con = null; Statement stmt = con.createStatement(); } catch (IOException | SQLException e) { //捕获多个异常，e就是final类型的 e.printStackTrace(); } } 而在Java SE6以前，需要这样写 Java代码 public static void second() { try { BufferedReader reader = new BufferedReader(new FileReader("")); Connection con = null; Statement stmt = con.createStatement(); } catch (IOException e) { e.printStackTrace(); } catch (SQLException e) { e.printStackTrace(); } } 注意，如果一个catch处理了多个异常，那么这个catch的参数默认就是final的，你不能在catch块里修改它的值。 
另外，用一个catch处理多个异常，比用多个catch每个处理一个异常生成的字节码要更小更高效。 
 
2、用更包容性的类型检查来重新抛出异常
在方法的声明上，使用throws语句时，你可以指定更加详细的异常类型。
static class FirstException extends Exception { } static class SecondException extends Exception { } public void rethrowException(String exceptionName) throws Exception { try { if (exceptionName.equals("First")) { throw new FirstException(); } else { throw new SecondException(); } } catch (Exception e) { throw e; } }
这个例子，try块中只能抛出两种异常，但是因为catch里的类型是 Exception，在java SE7以前的版本中，在方法声明中throws 只能写Exception，但是在java SE7及以后的版本中，可以在throws后面写 FirstException和SecondException——编译器能判断出throw e语句抛出的异常一定来自try块，并且try块只能抛出FirstException和SecondException。
public static void reThrowException(String exceptionName) throws FirstException, SecondException{ try { if ("first".equals(exceptionName)) throw new FirstException(); else throw new SecondException(); } catch (Exception e) { throw e; } }
所以尽管catch里的异常类型是Exception，编译器仍然能够知道它是FirstException和 SecondException的实例。怎么样，编译器变得更智能了吧。
但是，如果在catch里对异常重新赋值了，在方法的throws后无法再象上面那样写成FirstException和SecondException了，而需要写成 Exception。
具体来说，在Java SE 7及以后版本中，当你在catch语句里声明了一个或多个异常类型，并且在catch块里重新抛出了这些异常，编译器根据下面几个条件来去核实异常的类型： 
 
- Try块里抛出它 
- 前面没有catch块处理它 
- 它是catch里一个异常类型的父类或子类。
特性6：创建泛型对象时类型推断
只要编译器可以从上下文中推断出类型参数，你就可以用一对空着的尖括号<>来代替泛型参数。这对括号私下被称为菱形(diamond)。 在Java SE 7之前，你声明泛型对象时要这样
List<String> list = new ArrayList<String>(); 
 
而在Java SE7以后，你可以这样 
 
List<String> list = new ArrayList<>(); 
因为编译器可以从前面(List)推断出推断出类型参数，所以后面的ArrayList之后可以不用写泛型参数了，只用一对空着的尖括号就行。当然，你必须带着”菱形”<>，否则会有警告的。 
 
Java SE7 只支持有限的类型推断：只有构造器的参数化类型在上下文中被显著的声明了，你才可以使用类型推断，否则不行。 
 
List<String> list = new ArrayList<>(); 
list.add("A"); 
 
//这个不行 
list.addAll(new ArrayList<>()); 
 
// 这个可以 
List<? extends String> list2 = new ArrayList<>(); 
list.addAll(list2);
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
想必做Java的技术人员们都非常关心Java 7在2010年会有哪些新的进展，这个问题起源于今年年底即将发布的Java 7，那么这些新的特性更新对我们有哪些好处、该如何使用、性能如何，都是大家密切关注的问题。
这里的内容主要包括其最 新更新、一些代码实例、跟以前版本的Java进行性能比较需要参考的一些基准、以及什么时候它才会发布等信息。
首先，让我们来看最重要 的事情。为了确定JDK 7中有哪些语言方面的微小变化，有关人员建立了一个名叫Project Coin的项目。描述了最终的五个变化(比五个多一点)。
Java 7更新内容如下所示：
◆允许在 switch中使用字符串
　　◆自动资源管理
　　◆通用实例创建来改进类型推断(diamond)
　　◆简化 的Varargs方法调用
　　◆更好的整型文字综合性建议
　　◆Collections集合的语言支持
　　◆JSR292的语言支持
在the OpenJDK 7特性页面中，你还可以看到其他的功能。
这些功能分为不同的种类：
◆虚拟机(VM)
　　◆压缩的64位对象指针
　　◆G1垃圾回收器GC(G1)
　　◆JSR 292：非Java语言的虚拟机支持(动态调用，InvokeDynamic)
语言方面(lang)
◆SR 294：模块化编程的语言以及虚拟机支持
　　◆JSR 308：Java类型注释
　　◆语言微小增强(我所谈论的 Project Coin)
　　◆JSR TBD: Project Lambda
内核(core)
◆模块化(Jigsaw项目)
　　◆对类加载器的结构进行升级
　　◆关闭URLClassLoader的方法
　　◆Unicode 5.1标准
　　◆并行以及集合的升级(jsr166y)
　　◆JSR 203：Java平台(NIO.2)的更多新型I/O API
　　◆SCTP (流控制传输协议，Stream Control Transmission Protocol)
　　◆SDP (套接字直接协议，Sockets Direct Protocol)
　　◆椭圆曲线加密技术(ECC)
客户端(client)
◆Java 2D的XRender管道
　　◆转发端口6u10部署特性
　　◆为6u10图形功能创建新的平台API
　　◆Swing的Nimbus外观和感觉
　　◆Swing的JLayer元件
网络(web)
◆ 更新XML的栈
就像你所看到的，这涉及了很多东西。几个月前，我亲自尝试了新的Garbage Collector (GC)，其性能表现给我留下了非常深刻的印象。不幸的是，JVM几个小时就会崩溃一次，所以这个产品不能使用JVM。虽然在Java 1.6中也可以使用这个GC，但也会出现同样的问题，经常会出现崩溃。
我想，这就是Java1.7增加新特性的原因。那么，现在我们最好去看一些代码实例。
Java 7新特性的代码实例
下面列出的大多数例子都是来源于Joe Wright博客中(Java7中的新语言特性)的高水平文章。
集合的语言支持
在这里我们主要讲的 是，当创建一个List、Set或者Map的时候，你怎样尽量少写代码。你不必先实例化Object，然后再给Collection添加元素。你现在只需 1行代码就可以完成。
List list = ["item"];
String item = list[0];
　　Set set = {"item"};
　　Map map = {"key" : 1};
　　int value = map["key"];
自动资源管理
由于try / catch语句的原因，冗长的代码令人非常头痛。你或许会喜欢这个全新的特性。
实际上，下面这些代码：
BufferedReader br = new BufferedReader(new FileReader(path));
　　try {
　　return br.readLine();
　　}
　　finally
　　{
　　br.close();
　　}
转变成了如下这种形式：
　　try (BufferedReader br = new BufferedReader(new FileReader(path)) {
　　return br.readLine();
　　}
通用实例创建来改进类型推断(diamond)
当你把对象的接口申明指定成范型后，你在对象实例化时不得不再指定一次。现 在，你不必如此了，因为你可以这样：
Map> map = new HashMap<>();
数值文字的加强
我不敢肯定这个对大多数人都有用。你可以这样做：
int billion = 1_000_000_000;
允许在 switch中使用字符串
这个无需解释，其意思很明确。
String availability = "available";
　　switch(availability)
　　{
　　case "available":
　　//code
　　break;
　　case "unavailable":
　　//code
　　break;
　　case "merged":
　　//code
　　default:
　　//code
　　break;
　　}
二进制文字
你可以使用前缀0b创建二进制文字
int binary = 0b1001_1001;
以上这些就 是Java1.7的代码实例。如果有人能给我指出还有哪些没有包含进去，那就更好了。我敢肯定，已经有其他的开发人员对此进行了关注。
Java 1.7的性能表现
Java 7的性能有多大的提升?这里我们来针对Java 7做一个测试，内容如下。在一台装有ArchLinux系统的Macbook Pro电脑上(因特尔Duo CPU T7700，主频2.40GHz，有两年的使用时间)运行了这些测试。内存是2Gb的，把Heap Size设置成了728m(-Xms728m -Xmx728m)。
◆测试1 为一个List添加100万个字符串值(String字符串是一个UUID，是用UUID.randomUUID()产生的)。
◆测试 2 带有100万键、值对的HashMap。每个键、值对通过并行线程进行计算。键是一个UUID，值int是用Math.random()产生的。
◆测试3 把100万个ArrayList条目打印到一定数量的文件(1000个)中。把条目写进恰巧并行的不同文件中。
我只比较了 Java1.6 (1.6.0_19) 和 Java 1.7 (b87)。后来根据评论的要求，我把Java1.5也添加了进来，但是并没有添加Java1.4，因为它的时间太久远了。
结果如下所示：
 
 
　　Java 7的发布日期
预计2009年11月Java 7将正式发布，原计划在2010年9月发布，到那时还将发布3个里程碑版本。其中，里程碑6在build 84版中已经完成，里程碑7的第一个测试版B85也计划在2010年3月4日完成，而本文使用的B87版本已在2010年3月25日发布。这样看起来，Java 7很有可能在2010年9月发布。让我们拭目以待吧！