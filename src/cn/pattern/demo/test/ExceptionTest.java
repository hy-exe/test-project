package cn.pattern.demo.test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;


/** 
* @author yin.huang 
* @date 2017年3月28日 上午9:50:44 
*/
public class ExceptionTest {
  
  private static int                      base                = 1000000;

  private static int                      max                 = 9999999;

  private static AtomicLong                      transactionSeq      = new AtomicLong(base);
  
  private static List<String>        crackServerPortList = new ArrayList<String>();
  
  public static void main(String[] args) {
    
    crackServerPortList.add("127.0.0.1:6660");
    
    Executors.newFixedThreadPool(1).submit(new Runnable() {

      @Override
      public void run() {

        while(true) {
          try {
            int index = (int) (getSeqid() % 1);
            String str = crackServerPortList.get(index);
            try {
              System.out.println(str + "内部 try 。。");   
              Thread.sleep(1000);
            } finally {
              System.out.println(str + "内部 finally 。。。");
            }

        } catch (Exception e) {
          e.printStackTrace();
          System.out.println("外部异常。。。");
        }
        }
        
      }    
    
  });
  }
  
  private static long getSeqid() {
    long value = transactionSeq.getAndIncrement();
    if (value == max) {
      transactionSeq.set(base);
    }
    return value;
  }
}
