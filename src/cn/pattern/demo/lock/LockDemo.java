package cn.pattern.demo.lock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author yin.huang
 * @date 2017年3月15日 上午11:22:38
 */
public class LockDemo {

  private static List<ObjectDemo> objList = new ArrayList<ObjectDemo>();

  private static int              index   = 0;
  
  private Object object = null;

  public static void main(String[] args) {

    ExecutorService sendExecutor = Executors.newFixedThreadPool(5);
    for (int i = 0; i < 2; i++) {
      objList.add(new ObjectDemo("name：" + i));
    }

    sendExecutor.submit(new Runnable() {// 每隔一段时间就触发

      @Override
      public void run() {
        while (true) {

          ObjectDemo obj = objList.get(0);
          synchronized(obj){
          try {
            //obj.getLock().lock();
            System.out.println("线程1锁定：" + obj.hashCode());
            Thread.sleep(2000);
          } catch (Exception e) {
            e.printStackTrace();
          } finally {
            System.out.println("线程1解锁：" + obj.hashCode()+"-free");
            //obj.getLock().unlock();
          }
        }
        }
      }
    });

   sendExecutor.submit(new Runnable() {// 每隔一段时间就触发

      @Override
      public void run() {
        while (true) {

          ObjectDemo obj = objList.get(0);
          synchronized(obj){
          try {
            //obj.getLock().lock();
            System.out.println("线程2锁定：" + obj.hashCode());
            Thread.sleep(3000);
          } catch (Exception e) {
            e.printStackTrace();
          } finally {
            System.out.println("线程2解锁：" + obj.hashCode()+"-free");
            //obj.getLock().unlock();
          }
        }
        }
      }
    });

  }

  public static int getInt() {
    if(index == 0){
      index = 1;
    } else {
      index = 1;
    }
    return index;
  }

}
