package cn.pattern.demo.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @author yin.huang
 * @date 2017年7月17日 上午9:40:39
 */
public class MyInvocationHandler implements InvocationHandler {

  private Object target;

  MyInvocationHandler() {
    super();
  }

  MyInvocationHandler(Object target) {
    super();
    this.target = target;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    System.out.println("----before method：" + method);
    method.invoke(target, args);
    System.out.println("---- after method：" + method);
    return null;
  }

  public static void main(String[] args) {
    // 我们要代理的真实对象
    UserService userService = new UserServiceImpl();
    // 我们要代理哪个真实对象，就将该对象传进去，最后是通过该真实对象来调用其方法的
    MyInvocationHandler myInvocationHandler = new MyInvocationHandler(userService);

    /*
     * 通过Proxy的newProxyInstance方法来创建我们的代理对象，我们来看看其三个参数 第一个参数
     * handler.getClass().getClassLoader()
     * ，我们这里使用handler这个类的ClassLoader对象来加载我们的代理对象
     * 第二个参数realSubject.getClass().getInterfaces()，我们这里为代理对象提供的接口是真实对象所实行的接口，
     * 表示我要代理的是该真实对象，这样我就能调用这组接口中的方法了 第三个参数handler， 我们这里将这个代理对象关联到了上方的
     * InvocationHandler 这个对象上
     */
    UserService service = (cn.pattern.demo.proxy.UserService) Proxy.newProxyInstance(myInvocationHandler.getClass().getClassLoader(),
        userService.getClass().getInterfaces(), myInvocationHandler);

    System.out.println(service.getClass().getName());
    service.getName(0);
    service.getAge(0);

  }

}
