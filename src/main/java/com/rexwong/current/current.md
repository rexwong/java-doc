

# 核心类

![](img/Executor.png)

| 类名                       | 说明                                       |
| ------------------------ | ---------------------------------------- |
| Executor                 | 具体Runnable任务的执行者                         |
| ExecutorService          | 一个线程池管理者，其实现类有多种，我会介绍一部分。我们能把Runnable,Callable提交到池中让其调度 |
| ScheduledExecutorService | 一个 ExecutorService，可安排在给定的延迟后运行或定期执行的命令  |
| Future                   | 是与Runnable,Callable进行交互的接口，比如一个线程执行结束后取返回的结果等等，还提供了cancel终止线程 |
| BlockingQueue            | 阻塞队列                                     |
| ReentrantLock            | 一个可重入的互斥锁定 Lock，功能类似synchronized，但要强大的多  |
| CompletionService        | ExecutorService的扩展，可以获得线程执行结果的           |
|                          |                                          |
|                          |                                          |

