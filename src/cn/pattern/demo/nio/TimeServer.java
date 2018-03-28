package cn.pattern.demo.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author yin.huang
 * @date 2017年10月18日 上午10:28:02
 */
public class TimeServer implements Runnable {

  private Selector            selector;

  private ServerSocketChannel serverSocketChannel;

  private volatile boolean    stop;

  public TimeServer(int port) {
    try {
      selector = Selector.open();
      serverSocketChannel = ServerSocketChannel.open();
      serverSocketChannel.configureBlocking(false);
      serverSocketChannel.socket().bind(new InetSocketAddress(port), 1024);
      serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
      System.err.println("The time server is start in port: " + port);
    } catch (Exception e) {
      e.printStackTrace();
      System.exit(0);
    }
  }

  public void stop() {
    this.stop = true;
  }

  @Override
  public void run() {
    while (!stop) {
      try {
        selector.select(1000);
        Set<SelectionKey> selectedKeys = selector.selectedKeys();
        Iterator<SelectionKey> it = selectedKeys.iterator();
        SelectionKey key = null;
        while (it.hasNext()) {
          key = it.next();
          it.remove();
          try {
            handleInput(key);
          } catch (Exception e) {
            if (key != null) {
              key.cancel();
              if (key.channel() != null) {
                key.channel().close();
              }
            }
          }
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    if (selector != null) {
      try {
        selector.close();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  private void handleInput(SelectionKey key) throws IOException {
    if (key.isValid()) {
      if (key.isAcceptable()) {
        ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
        SocketChannel sc = ssc.accept();
        sc.configureBlocking(false);
        sc.register(selector, SelectionKey.OP_READ);
      }
      if (key.isReadable()) {
        SocketChannel sc = (SocketChannel) key.channel();
        ByteBuffer readBufer = ByteBuffer.allocate(1024);
        int readBytes = sc.read(readBufer);
        if (readBytes > 0) {
          readBufer.flip();
          byte[] bytes = new byte[readBufer.remaining()];
          readBufer.get(bytes);
          String body = new String(bytes, "UTF-8");
          System.out.println("The time server receive order : " + body);
          String currentTime = "QUERY TIME ORDER".equals(body) ? new java.util.Date(System.currentTimeMillis()).toString() : "BAD ORDER";
          doWrite(sc, currentTime);
        } else if (readBytes < 0) {
          key.cancel();
          sc.close();
        } else {

        }
      }
    }
  }

  private void doWrite(SocketChannel channel, String reponse) throws IOException {
    if (reponse != null && reponse.trim().length() > 0) {
      byte[] bytes = reponse.getBytes();
      ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
      writeBuffer.put(writeBuffer);
      writeBuffer.flip();
      channel.write(writeBuffer);
    }
  }

  public static void main(String[] args) {
//    int port = 8080;
//    TimeServer server = new TimeServer(port);
//    new Thread(server, "NIO-TimeServer-001").start();
    String msg = "TCP hzhzz";
    byte[] payload = msg.getBytes();
//    int MAX_LENGTH = 32;
//    ByteBuffer byteBuffer = ByteBuffer.allocate(MAX_LENGTH);
//    byteBuffer.put(msg.getBytes());
//    byte[] fillData = new byte[MAX_LENGTH - msg.length()];
//    byteBuffer.put(fillData);
//    byte[] data = byteBuffer.array();
    ByteBuffer byteBuffer = ByteBuffer.allocate(payload.length + 4);
    byteBuffer.putInt(payload.length);
    byteBuffer.put(payload);
    
    int position = byteBuffer.position();
    int limit = byteBuffer.limit();
    byteBuffer.flip();
    // 判断数据长度是否够首部长度
    if (byteBuffer.remaining() < 4) {
        byteBuffer.position(position);
        byteBuffer.limit(limit);
    }
    // 判断bytebuffer中剩余数据是否足够一个包
    int length = byteBuffer.getInt();
    if (byteBuffer.remaining() < length) {
        byteBuffer.position(position);
        byteBuffer.limit(limit);
    }
    // 拿到实际数据包
    byte[] data = new byte[length];

    byteBuffer.get(data, 0, length);
    System.out.println(new String(data) + " <---> ");
    byteBuffer.compact();
   // System.out.println(new String(byteBuffer.array()));
  }

}
