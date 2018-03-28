package cn.pattern.demo.proxy;

/**
 * @author yin.huang
 * @date 2017年7月17日 上午9:38:48
 */
public class UserServiceImpl implements UserService {

  @Override
  public String getName(int id) {
    System.out.println("--- get name ---");
    return "joky";
  }

  @Override
  public Integer getAge(int id) {
    System.out.println("--- get age ---");
    return 25;
  }

}
