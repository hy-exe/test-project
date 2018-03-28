package cn.pattern.demo.linked;
/**
 *  双向链表
 *  1. 双向链表只保存第一个节点和后一节点 以及大小(单向链表只保存第一个结点以及大小，通过链结点的next字段找其他结点)
 *  2. 通过第一节点可找到别的节点的数据
 *  
 *  栈：       后进先出，一种线性表，限定仅在表尾(栈顶)进行插入和删除,读取和操作只能在栈顶
 *  	   LinkedList实现栈，入栈（LinkedList.addFirst()）,出栈（LinkedList.removeFirst()）,查看（LinkedList.getFirst()）
 *  队列：   先进先出，限定插入只在表的一端（对尾）进行，删除在表的另一端（对头）进行
 *         LinkedList实现队列,入列（LinkedList.addLast()），出列（LinkedList.removeFirst）
 *  
 **/
class LinkList{
    Node head = new Node();  //头结点
    Node tail = new Node();  //尾结点
    int size = 0;            
    public LinkList(){
        head.next = tail;  //头的下一个为尾
        this.head.date = "head"; //头的数据
        tail.last = head;        //尾的上一个为头
        tail.next = null;        //尾的下一个为空
        this.tail.date = "tail"; //尾的数据  
    }
    

    //内部节点类k
    class Node{
        String date;   //数据域
        Node next;    //指向下一个节点
        Node last;      //指向上一个节点
        Node(){};
        Node(String date){
           this.date = date; 
           this.next = null;
           this.next = null;
        }
    }

    //向链表中添加一个数据 在指定的位置上插入
    public void Insert(String date){
        Node newNode = new Node(date); //新建结点
        newNode.next = this.head.next; //新结点下一个为头结点的下一个
        newNode.last = this.head;      
        this.head.next.last = newNode;
        this.head.next = newNode;
        System.out.println("插入数据"+date+"成功");
        this.size++;
    }

    //遍历链表输出
    public void PrintLink( LinkList linklist){
        Node preNode = new Node();
        preNode.next = this.head.next;
        System.out.print("遍历输出结果:");
            while(true){
                System.out.print(preNode.next.date+" -->");
                if(preNode.next.next.next == null)  break;
                preNode.next = preNode.next.next;
            }
            System.out.println("遍历输出完成～～～");
    }

    //删除链表中的某一个数
    public void DeleteLink(String date){
        Node preNode = new Node();
        preNode = this.head;
        Node node = null;
        while(preNode.next != this.tail){
            
            node = preNode;
            preNode = preNode.next;
            if(preNode.date == date){
                node.next = preNode.next;
                System.out.println("删除元素"+date+"成功");
                this.size--;
            }
        }
    }
}

public class DoublyLinkList{
    //主函数测试
    public static void main(String[] args){
      LinkList linklist = new LinkList();
        linklist.Insert("lv1");
        linklist.Insert("lv2");
        linklist.Insert("lv3");
        linklist.Insert("lv4");
        linklist.Insert("lv5");
        linklist.Insert("lv6");
        linklist.Insert("lv7");
        linklist.Insert("lv8");
        linklist.Insert("lv9");
        linklist.Insert("lv0");
        linklist.Insert("lv11");
        linklist.Insert("lv12");
        linklist.Insert("lv13");
        linklist.Insert("lv14");
        System.out.println("链表的长度为："+linklist.size);
        linklist.PrintLink(linklist);
        //删除lvbiao3
        linklist.DeleteLink("lv3");
        linklist.PrintLink(linklist);
        
        linklist.DeleteLink("lv14");
        
        linklist.DeleteLink("lv12");

        linklist.PrintLink(linklist);
        System.out.println("tail:"+linklist.tail.date);
    }
}