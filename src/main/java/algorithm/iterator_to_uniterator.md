递归算法向非递归算法转换
http://www.cnblogs.com/javaexam2/archive/2011/07/25/2632907.html
递归算法实际上是一种分而治之的方法，它把复杂问题分解为简单问题来求解。对于某些复杂问题(例如hanio塔问题)，递归算法是一种自然且合乎逻辑的解决问题的方式，但是递归算法的执行效率通常比较差。因此，在求解某些问题时，常采用递归算法来分析问题，用非递归算法来求解问题；另外，有些程序设计语言不支持递归，这就需要把递归算法转换为非递归算法。

    将递归算法转换为非递归算法有两种方法，一种是直接求值，不需要回溯；另一种是不能直接求值，需要回溯。前者使用一些变量保存中间结果，称为直接转换法；后者使用栈保存中间结果，称为间接转换法，下面分别讨论这两种方法。

1. 直接转换法

直接转换法通常用来消除尾递归和单向递归，将递归结构用循环结构来替代。

尾递归是指在递归算法中，递归调用语句只有一个，而且是处在算法的最后。例如求阶乘的递归算法：

long fact(int n)

{

　　if (n==0) return 1;

　　else return n*fact(n-1);

}

当递归调用返回时，是返回到上一层递归调用的下一条语句，而这个返回位置正好是算法的结束处，所以，不必利用栈来保存返回信息。对于尾递归形式的递归算法，可以利用循环结构来替代。例如求阶乘的递归算法可以写成如下循环结构的非递归算法：

long fact(int n)

{

　　int s=0;

　　for (int i=1; i<=n;i++)

　　s=s*i; //用s保存中间结果

　　return s;

}

单向递归是指递归算法中虽然有多处递归调用语句，但各递归调用语句的参数之间没有关系，并且这些递归调用语句都处在递归算法的最后。显然，尾递归是单向递归的特例。例如求斐波那契数列的递归算法如下：

int f(int n)

{

　　if (n= =1 | | n= =0) return 1;

　　else return f(n-1)+f(n-2);

}

对于单向递归，可以设置一些变量保存中间结构，将递归结构用循环结构来替代。例如求斐波那契数列的算法中用s1和s2保存中间的计算结果，非递归函数如下：

int f(int n)

{

　　int i, s;

　　int s1=1, s2=1;

　　for (i=3; i<=n; ++i)

        {

　　       s=s1+s2;

　　       s2=s1; // 保存f(n-2)的值

　　       s1=s; //保存f(n-1)的值

　　}

　　return s;

}

2. 间接转换法

该方法使用栈保存中间结果，一般需根据递归函数在执行过程中栈的变化得到。其一般过程如下：

将初始状态s0进栈

while (栈不为空)

{

　　退栈，将栈顶元素赋给s;

　　if (s是要找的结果) 返回;

　　else

        {

　　    寻找到s的相关状态s1;

　　    将s1进栈

　　}

}

间接转换法在数据结构中有较多实例，如二叉树遍历算法的非递归实现、图的深度优先遍历算法的非递归实现等等。

使用非递归方式实现递归问题的算法程序,不仅可以节省存储空间,而且可以极大地提高算法程序的执行效率。本文将递归问题分成简单递归问题和复杂递归问题;简单递归问题的非递归实现采用递推技术加以求解,复杂递归问题则根据问题求解的特点采用两类非递归实现算法,使用栈加以实现。