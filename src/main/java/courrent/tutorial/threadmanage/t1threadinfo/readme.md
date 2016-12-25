http://ifeve.com/thread-management-3/

获取和设置线程信息
Thread类的对象中保存了一些属性信息能够帮助我们来辨别每一个线程，
知道它的状态，调整控制其优先级。 这些属性是：

ID: 每个线程的独特标识。
Name: 线程的名称。
Priority: 线程对象的优先级。优先级别在1-10之间，1是最低级，10是最高级。
不建议改变它们的优先级，但是你想的话也是可以的。
Status: 线程的状态。在Java中，线程只能有这6种中的一种状态：
new, runnable, blocked, waiting, time waiting, 或 terminated.
