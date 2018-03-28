package cn.pattern.demo.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yin.huang
 * @date 2017年3月15日 上午11:22:52
 */
public class ObjectDemo {

  private Lock   lock = new ReentrantLock();

  private String name;

  public ObjectDemo(String name) {
    this.name = name;
  }

  public Lock getLock() {
    return lock;
  }

  public void setLock(Lock lock) {
    this.lock = lock;
  }

  public String getName() {

    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
