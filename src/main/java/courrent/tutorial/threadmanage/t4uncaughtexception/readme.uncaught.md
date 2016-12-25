检查异常（Checked exceptions）: 这些异常必须强制捕获它们或在一个方法里的throws子句中。
例如， IOException 或者ClassNotFoundException。
未检查异常（Unchecked exceptions）: 这些异常不用强制捕获它们。
例如， NumberFormatException。
在一个线程 对象的 run() 方法里抛出一个检查异常，我们必须捕获并处理他们。
因为 run() 方法不接受 throws 子句。当一个非检查异常被抛出，
默认的行为是在控制台写下stack trace并退出程序。

幸运的是, Java 提供我们一种机制可以捕获和处理线程对象抛出的未检测异常来避免程序终结。