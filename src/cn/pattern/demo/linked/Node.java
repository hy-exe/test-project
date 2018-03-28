package cn.pattern.demo.linked;

/**
 * @author yin.huang
 * @date 2018年1月5日 上午10:11:25
 */
public class Node {

  protected Node next;

  protected int  data;

  public Node(int data) {
    this.data = data;
  }

  public Node getNext() {
    return next;
  }

  public void setNext(Node next) {
    this.next = next;
  }

  public int getData() {
    return data;
  }

  public void setData(int data) {
    this.data = data;
  }

}
