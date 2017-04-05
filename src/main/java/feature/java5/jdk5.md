JDK5新特性汇总
 http://blog.csdn.net/magister_feng/article/details/7242513

1 循环
5.0
1.4
for (type variable : array){
   body
}
for (int i = 0; i < array.length; i++){
   type variable = array[i];
   body
}
for (type variable : arrayList){
   body
}
for (int i = 0; i < arrayList.size(); i++){
   type variable = (type) arrayList.get(i);
   body
}
2 泛型
以ArrayList为例，包括创建一个容器对象和取得容器内对象操作：
5.0
1.4
ArrayList arrayList =
      new ArrayList()
ArrayList arrayList =
      new ArrayList();
arrayList.get(i)
(Type) arrayList.get(i)
3 自动装箱拆箱
在JDK5.0以前，在原始类型与相应的包装类之间的转化是不能自动完成的。要完成这种转化，需要手动调用包装类的构造函数：
5.0
1.4
Integer wrapper = n;
Integer wrapper = new Integer(n);
在JDK5.0环境中，可以自动转化，不再需要手工干预：
5.0
1.4
int n = wrapper;
int n = wrapper.intValue();
4 可变参数列表
5.0
1.4
method(other params, p1, p2, p3)
method(other params, new Type[] { p1, p2, p3 })
5 可变的返回类型
在JDK5.0以前，当覆盖父类方法时，返回类型是不能改变的。现在有新的规则用于覆盖方法。如下，一个典型的例子就是clone()方法：
5.0
1.4
public Employee clone() { ... }
...
Employee cloned = e.clone();
public Object clone() { ... }
...
Employee cloned = (Employee) e.clone();
6 静态导入
静态导入功能对于JDK 5.0以前的版本是不支持的。
5.0
1.4
import static java.lang.Math;
import static java.lang.System;
...
out.println(sqrt(PI));
System.out.println(Math.sqrt(Math.PI));
7 控制台输入
JDK 5.0先前的版本没有Scanner类，只能使用JOptionPane.showInputDialog类代替。
5.0
1.4
Scanner in = new Scanner(System.in);System.out.print(prompt);
int n = in.nextInt();
double x = in.nextDouble();
String s = in.nextLine();
String input = JOptionPane.showInputDialog(prompt);
int n = Integer.parseInt(input);
double x = Double.parseDouble(input);
s = input;
8 格式化输出
JDK5.0以前的版本没有print方法，只能使用NumberFormat.getNumberInstance来代替。
5.0
1.4
System.out.printf("%8.2f", x);
NumberFormat formatter
   = NumberFormat.getNumberInstance();
formatter.setMinimumFractionDigits(2);
formatter.setMaximumFractionDigits(2);
String formatted = formatter.format(x);
for (int i = formatted.length(); i < 8; i++)
   System.out.print(" "); System.out.print(formatted);
9 内容面板代理
在JDK5.0先前的版本中，JFrame,JDialog,JApplet等类没有代理add和setLayout方法。
5.0
1.4
add(component)
getContentPane().add(component)
setLayout(manager)
getContentPane().setLayout(manager)
10 StringBuilder类
JDK 5.0引入了StringBuilder类，这个类的方法不具有同步，这使得该类比StringBuffer类更高效。
5.0
1.4
StringBuilder
StringBuffer
