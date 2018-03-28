package cn.pattern.demo.test;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @author yin.huang
 * @date 2016年10月17日 下午2:27:50 垃圾回收 finalize（）
 */
public class Test3 {

  protected void finalize() throws Throwable {
    System.out.println("finalize()..");
    super.finalize();
  }

  public static void main(String[] args) {

    BlockingQueue<String> queue = new ArrayBlockingQueue<>(10);
    queue.add("");
    try {
      queue.put("");
    } catch (InterruptedException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }
}
