package cn.pattern.demo.linked;

import java.util.LinkedList;

/**
 * @author yin.huang
 * @date 2018年1月5日 上午10:12:18
 */
public class LinkListTest {

  public Node first;

  public int  pos = 0;

  public LinkListTest() {
    this.first = null;
  }

  public void addFirstNode(int data) {
    Node node = new Node(data);
    node.next = first;
    first = node;
  }

  public void deleteFirstNode() {
    Node tempNode = first;
    first = tempNode.next;
  }

  public void add(int index, int data) {
    Node node = new Node(data);
    Node current = first;
    Node previous = first;
    while (pos != index) {
      previous = current;
      current = current.next;
      pos++;
    }
    node.next = current;
    previous.next = node;
    pos = 0;
  }
  
  public static void main(String[] args) {
    LinkedList<String> list = new LinkedList<String>();
    list.addFirst("1");
  }

}
