package cn.pattern.demo.lock;

/**
 * @author yin.huang
 * @date 2017年3月17日 上午10:14:32
 */
public class SyncDemo {

  public void test1() {
    synchronized (SyncDemo.class) {
      int i = 5;
      while (i-- > 0) {
        System.out.println(Thread.currentThread().getName() + " : " + i);
        try {
          Thread.sleep(500);
        } catch (InterruptedException ie) {
        }
      }
    }
  }

  public static synchronized void test2() {
    int i = 5;
    while (i-- > 0) {
      System.out.println(Thread.currentThread().getName() + " : " + i);
      try {
        Thread.sleep(500);
      } catch (InterruptedException ie) {
      }
    }
  }

  public static void main(String[] args) {
    
    StringBuffer sbf = new StringBuffer();
    StringBuilder sbd = new StringBuilder();
    
    final SyncDemo myt2 = new SyncDemo();
    
    Thread test1 = new Thread(new Runnable() {
      @Override
      public void run() {
        myt2.test1();
      }
    }, "test1");
    
    Thread test2 = new Thread(new Runnable() {
      @Override
      public void run() {
        myt2.test2();
      }
    }, "test2");
    test1.start();
    test2.start();
  }
}
