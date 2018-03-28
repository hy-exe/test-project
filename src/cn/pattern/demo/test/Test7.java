package cn.pattern.demo.test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yin.huang
 * @date 2017年3月29日 下午7:10:51
 */
public class Test7 {

  private static final ResponseFuture responseFuture = new Test7().new ResponseFuture();

  class ResponseFuture<V> extends FutureTask<V> {

    public ResponseFuture() {
      super(new Callable<V>() {

        public V call() throws Exception {
          return null;
        }
      });
    }

    public void set(V v) {
      super.set(v);
    }
  }

  private static Test7 save = null;

  private static Lock  lock = new ReentrantLock();

  public static void main(String[] args) throws Exception {

    new Thread(new Runnable() {

      @Override
      public void run() {
        // TODO Auto-generated method stub
        try {
          System.out.println(responseFuture.get(3, TimeUnit.SECONDS));
        } catch (InterruptedException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        } catch (ExecutionException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        } catch (TimeoutException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
      }
    }).start();

    new Thread(new Runnable() {

      @Override
      public void run() {
        try {
          Thread.sleep(2000);
        } catch (InterruptedException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
        //responseFuture.set("1111");
        //System.out.println("set value");
      }
    }).start();

    // save = new Test7();
    // save = null;
    // System.gc();
    // Thread.sleep(5000);
    // if (null != save) {
    // System.out.println("i am still alive");
    // } else {
    // System.out.println("i am dead");
    // }

//    Integer a = 188;
//    Integer b = 188;
//    System.out.println(a == b);

  }

  public static void readFile() throws Exception {
    LinkedList<String> list = new LinkedList<String>();
    String fileName = "E://testFile.txt";
    BufferedReader bf = new BufferedReader(new FileReader(new File(fileName).getAbsoluteFile()));
    String s = null;
    while ((s = bf.readLine()) != null) {
      list.add(s);
    }
    bf.close();
    for (int i = list.size() - 1; i >= 0; i--) {
      System.out.println(list.get(i));
    }
  }

  public static void writeFIle() throws IOException {
    String fileName = "E://testFile.txt";
    FileOutputStream fos = new FileOutputStream(fileName, true);
    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
    for (int i = 77; i < 100; i++) {
      bw.write(i + "\n");
    }
    bw.flush();
    fos.close();
    bw.close();
  }

  @Override
  protected void finalize() throws Throwable {
    super.finalize();
    System.out.println("execute method finalize()");
    save = this;
  }

  public static void getLock1() {
    try {
      lock.lock();
      System.out.println("getLock1");
      Thread.sleep(5000);
    } catch (Exception e) {
      // TODO: handle exception
    } finally {
      lock.unlock();
      System.out.println("unLock1");
    }
  }

  public static void getLock2() {
    try {
      lock.lock();
      System.out.println("getLock2");
      Thread.sleep(1000);
    } catch (Exception e) {
      // TODO: handle exception
    } finally {
      lock.unlock();
      System.out.println("unLock2");
    }
  }
}