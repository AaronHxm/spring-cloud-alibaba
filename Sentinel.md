# 一：什么是sentinel服务容错【流量卫兵

sentinel是轻量级的流量控制、熔断降级Java库(容错的库)。

 

# 二：什么是雪崩效应

当一个服务调用另一个服务由于网络或自身原因，调用者就会等待被调用者的响应，当更多的服务请求到达这些资源时，将会导致更多的请求等待，从而导致出现连锁效应（即雪崩效应）

 
# 三：常见容错方案【避免雪崩效应】

超时模式--限流模式--仓壁模式--断路器模式--降级模式

## 1、超时【思想：只要释放够快服务就不容易那么死了】

为每次请求设置一个最大响应时间(超时时间，如1秒)，如果超过这个时间，不管这次请求是否成功，就断开这次请求，释放掉线程。只要线程释放速度够快，被请求的服务就不那么容易被拖死。

## 2、限流【思想：只有一碗的饭量，给再多也只是吃一碗】

高并发的系统存在大量的线程阻塞，若经过评估被请求B服务的实例，最大能够承载的QPS是1000，那么久可以为B服务设置一个适合的限流的值(如800QPS)，只要某一个实例达到设置的阈值，再有流量(请求)进来就会被直接拒绝。实现了自己的保护。

## 3、仓壁模式/隔离【思想：不把鸡蛋放一个篮子里面，各有各的线程池】

如将船分为若干个船舱，船舱与船舱之间用钢板焊死，使船舱达到隔离，即使某个船舱进水也不会影响其它的船舱。

常见的隔离方式有：线程池隔离和信号量隔离。

线程池隔离：如为A、B两个controller设置独立的线程池(coreSize=10)，他们之间就是用线程池这个钢板焊死了，而A controller对应的API调不通就像是船舱进水了，A的船舱进水与B的船舱没有任何关系。这就是仓壁模式。

# 4、断路器模式【监控+开关】

断路器用于处理服务调用出错时的雪崩效应，隔断服务调用错误的级联传递，防止服务调用错误的级联放大。

##（1）全开状态【服务调用失败达到一定次数】

一定时间内，服务调用失败达到一定次数，且多次检测无恢复迹象，断路器全开

##（2）半开状态【服务有恢复迹象】

短时间内，有恢复迹象，断路器会将部分请求发给该服务，断路器半开

##（3）关闭状态【服务正常状态】

当服务处于正常状态，能正常调用，断路器关闭

# 5、降级 

降级其实就是为服务提供一个托底方案，一旦服务无法正常调用，就使用托底方案。