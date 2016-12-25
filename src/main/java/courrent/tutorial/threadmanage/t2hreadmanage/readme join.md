Java 提供2种形式的 join() 方法:

join (long milliseconds)
join (long milliseconds, long nanos)
第一种join() 方法, 这方法让调用线程等待特定的毫秒数。例如，如果thread1对象使用代码thread2.join(1000), 那么线程 thread1暂停运行，直到以下其中一个条件发生：

thread2 结束运行
1000 毫秒过去了
当其中一个条件为真时，join() 方法返回。

第二个版本的 join() 方法和第一个很像，只不过它接收一个毫秒数和一个纳秒数作为参数。