package cn.pattern.demo.threadlocal;

/**
 * @author yin.huang
 * @date 2017年3月23日 上午10:33:21 线程局部变量
 *  维持线程封闭(隔离)性 使用ThreadLocal维护变量时，为每个变量提供一份独立的变量副本
 *       不影响其他线程副本
 */
public class ThreadLocalTest {

  private static ThreadLocal<Integer> seqNum = new ThreadLocal<Integer>() {
    
    @Override
    public Integer initialValue() {
      return 0;
    }
    
  };

  public int getNextNum() {
    seqNum.set(seqNum.get() + 1);
    return seqNum.get();
  }

  public static void main(String[] args) {
    ThreadLocalTest sn = new ThreadLocalTest();
    TestClient t1 = new TestClient(sn);
    TestClient t2 = new TestClient(sn);
    TestClient t3 = new TestClient(sn);

    t1.start();
    t2.start();
    t3.start();
  }

  private static class TestClient extends Thread {

    private ThreadLocalTest sn;

    public TestClient(ThreadLocalTest sn) {
      this.sn = sn;
    }

    public void run() {
      for (int i = 0; i < 3; i++) {
        // ④每个线程打出3个序列值
        System.out.println("thread[" + Thread.currentThread().getName() + "] --> sn[" + sn.getNextNum() + "]");
      }
    }
  }
}