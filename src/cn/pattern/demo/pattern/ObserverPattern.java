package cn.pattern.demo.pattern;

import java.util.ArrayList;
import java.util.List;

/**
 * 观察者模式
 * 
 * 意图：定义对象间的一种一对多的依赖关系，当一个对象的状态发生改变时，所有依赖于它的对象都得到通知并被自动更新。
 * 何时使用：一个对象（目标对象）的状态发生改变，所有的依赖对象（观察者对象）都将得到通知，进行广播通知。
 * 关键代码：在抽象类里有一个 ArrayList 存放观察者们。	
 * 注意事项： 1、JAVA 中已经有了对观察者模式的支持类。 
 * 			 2、避免循环引用。 
 * 			 3、如果顺序执行，某一观察者错误会导致系统卡壳，一般采用异步方式。
 *  
 *  1.创建一个需要被观察的类，在类中创建一个存放观察者的 list，创建状态改变时通知所有观察者的方法，创建增加和删除观察者的方法
 *  2.创建观察者实体类，创建时在构造方法中绑定到被观察的对象的list中，创建更新自身状态的方法。
 *  3.在使用的时候，先新建一个被观察者对象Subject，然后创建观察者对象Observer，创建时将Subject作为参数传入Observer，
 *  	在Observer构造方法中完成观察者绑定。当观察者状态改变，也会在改变方法中执行通知被观察者改变状态的方法。
 *  
 */
public class ObserverPattern {

}
//有绑定观察者的方法,需要被观察的类
class Subject {
	
   private List<Observer> observers 
      = new ArrayList<Observer>();
   private int state;

   public int getState() {
      return state;
   }
   //改变的时候通知观察者
   public void setState(int state) {
      this.state = state;
      notifyAllObservers();
   }
   //添加观察者
   public void attach(Observer observer){
      observers.add(observer);		
   }
   //通知观察者
   public void notifyAllObservers(){
      for (Observer observer : observers) {
         observer.update();
      }
   } 	
}
//抽象观察者类
abstract class Observer {
   protected Subject subject;
   public abstract void update();
}
//观察者实体类
class BinaryObserver extends Observer{

   public BinaryObserver(Subject subject){
      this.subject = subject;
      this.subject.attach(this);
   }

   @Override
   public void update() {
      System.out.println( "Binary String: " 
      + Integer.toBinaryString( subject.getState() ) ); 
   }
}
//观察者实体类
class OctalObserver extends Observer{

   public OctalObserver(Subject subject){
      this.subject = subject;
      this.subject.attach(this);
   }

   @Override
   public void update() {
     System.out.println( "Octal String: " 
     + Integer.toOctalString( subject.getState() ) ); 
   }
}
//使用
class ObserverPatternDemo {
   public static void main(String[] args) {
      Subject subject = new Subject();

      new OctalObserver(subject);
      new BinaryObserver(subject);

      System.out.println("First state change: 15");	
      subject.setState(15);
      System.out.println("Second state change: 10");	
      subject.setState(10);
   }
}