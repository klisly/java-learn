Java并发Api提供了一个更复杂、更强大的同步辅助类，即Phaser，
它允许执行并发多阶段任务。当我们有并发任务并且需要分解成几步执行时，
这种机制就非常适用。Phaser类机制是在每一步结束的位置对线程进行同步，
当所有的线程完成了这一步，才允许执行下一步。

跟其他同步工具一样，必须对Phaser类中参与同步操作的任务数进行初始化，
不同的是，我们可以动态地增加或者减少任务数。
