package cn.pattern.demo.producerANDconsumer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * ExecutorService线程池 的submit（）和execute（）方法区别
 * 1. 接受参数有一些区别
 * 2. submit有返回值  execute 没有返回值
 * 3. submit 方便exception 处理
 * 
 * 线程池的创建
 * newFixedThreadPool()：创建一个固定线程数的线程池，可控制线程最大并发数，适用需要限制线程池数量的应用场景。
 * newSingleThreadExecutor()：创建一个单线程的线程池，它只会用唯一的工作线程来执行任务，
 * 								保证所有任务按照指定顺序(FIFO)执行适用于那种需要按照线程数量执行的场景。
 * newCachedThreadPool()：创建一个可以根据需要创建新线程的线程池，它是没有线程数量限制的，
 * 								适用于短期异步任务的操作，或者是负载比较轻的服务器。 
 * newScheduledThreadPool()：创建一个固定线程数的线程池，支持定时及周期性执行后台任务。
 * 
 */

public class Test {
	public static void main(String args[]){

        Storage s = new Storage();

        ExecutorService service = Executors.newCachedThreadPool(); //线程池
        Producer p = new Producer("张三", s);
        Producer p2 = new Producer("李四", s);
        Consumer c = new Consumer("王五", s);
        Consumer c2 = new Consumer("老刘", s);
        Consumer c3 = new Consumer("老林", s);
        service.submit(p);
        service.submit(p2);
        service.submit(c);
        service.submit(c2);       
        service.submit(c3);
        service.execute(p);
        service.execute(p2);
        service.execute(c);
        service.execute(c2);
        service.execute(c3);

	}
}
