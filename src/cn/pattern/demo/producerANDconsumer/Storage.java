package cn.pattern.demo.producerANDconsumer;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
/**
 * 仓库
 * 
 *  1. 使用线程安全的阻塞队列   LinkedBlockingQueue ，先进先出   插入是插入尾部
 *  2. 使用 put 和  take 方法 产生阻塞效果
 *  3. 使用poll()方法会产生非阻塞效果
 */
public class Storage {
	
	BlockingQueue<Product> queues = new LinkedBlockingQueue<Product>(10); 

    /**
     * 生产
     * 
     * @param p
     *            产品
     * @throws InterruptedException
     */
    public void push(Product p) throws InterruptedException {
        queues.put(p);  //put方法在队列满的时候会阻塞直到有队列成员被消费
    }

    /**
     * 消费
     * 
     * @return 产品
     * @throws InterruptedException
     */
    public Product pop() throws InterruptedException {
        return queues.take(); //take方法在队列空的时候会阻塞
    }
}

class Product {
    private int id;

    public Product(int id) {
        this.id = id;
    }

    public String toString() {// 重写toString方法
        return "产品：" + this.id;
    }
}

