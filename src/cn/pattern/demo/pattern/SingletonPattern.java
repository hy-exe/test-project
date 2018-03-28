package cn.pattern.demo.pattern;

/**
 * 单例模式
 * 
 * 使用场景： 1、要求生产唯一序列号。 2、WEB 中的计数器，不用每次刷新都在数据库里加一次，用单例先缓存起来。
 * 3、创建的一个对象需要消耗的资源过多，比如 I/O 与数据库的连接等。 注意事项：getInstance() 方法中需要使用同步锁
 * synchronized (Singleton.class) 防止多线程同时进入造成 instance 被多次实例化。
 * 
 * 1. 懒汉式： 线程不安全 最简单 基本 
 * 2. 懒汉式： 线程安全 synchronized getInstance（） 
 * 3. 饿汉式(建议使用)：
 * private static Singleton instance = new Singleton(); 类加载时就初始化 。 基于 classloder
 * 机制避免了多线程的同步问题。 注意：可以通过反射机制攻击；线程安全[多个类加载器除外] 不能延时加载 
 * 4.双检锁/双重校验锁（DCL，即
 * double-checked locking） private volatile static Singleton singleton; private
 * Singleton (){} public static Singleton getSingleton() { if (singleton ==
 * null) { synchronized (Singleton.class) { if (singleton == null) { singleton =
 * new Singleton(); } } } return singleton; } 
 * 5.登记式/静态内部类 private static class
 * SingletonHolder { private static final Singleton INSTANCE = new Singleton();
 * } private Singleton (){} public static final Singleton getInstance() { return
 * SingletonHolder.INSTANCE; } 
 * 6.枚举 public enum Singleton { INSTANCE; //枚举元素
 * ，代表本身的一个实例 public void whateverMethod() { } }
 */
public class SingletonPattern {

  // 内部类实现
  SingletonPattern() {

  }
  
  public static SingletonPattern getInstance(){
    return Nested.singletonInstance;
  }
  
  static class Nested{
    static final SingletonPattern singletonInstance = new SingletonPattern();
  }

}
