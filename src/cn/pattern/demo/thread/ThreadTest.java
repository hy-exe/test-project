package cn.pattern.demo.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author yin.huang
 * @date 2018年3月28日 上午9:53:01
 */
public class ThreadTest {

  public static void main(String[] args) {

    ExecutorService cxecutorService = new ThreadPoolExecutor(5, 10, 0L, TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(20));
    
  }

}
