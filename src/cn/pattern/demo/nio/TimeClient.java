package cn.pattern.demo.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author yin.huang
 * @date 2017年10月18日 上午11:12:53
 */
public class TimeClient implements Runnable {

  private String           host;

  private int              port;

  private Selector         selector;

  private SocketChannel    socketChannel;

  private volatile boolean stop;

  public TimeClient(String host, int port) {
    this.host = host == null ? "127.0.0.1" : host;
    this.port = port;
    try {
      selector = Selector.open();
      socketChannel = SocketChannel.open();
      socketChannel.configureBlocking(false);
    } catch (Exception e) {
      e.printStackTrace();
      System.exit(0);
    }
  }

  @Override
  public void run() {
    try {
      doConnect();
    } catch (Exception e) {
      e.printStackTrace();
      System.exit(0);
    }
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
      SocketChannel sc = (SocketChannel) key.channel();
      if (key.isConnectable()) {
        if (sc.finishConnect()) {
          sc.register(selector, SelectionKey.OP_READ);
          doWrite(sc);
        }
      } else {
        System.exit(0);
      }

      if (key.isReadable()) {
        ByteBuffer readBufer = ByteBuffer.allocate(1024);
        int readBytes = sc.read(readBufer);
        if (readBytes > 0) {
          readBufer.flip();
          byte[] bytes = new byte[readBufer.remaining()];
          readBufer.get(bytes);
          String body = new String(bytes, "UTF-8");
          System.out.println("NOW is : " + body);
          this.stop = true;
        } else if (readBytes < 0) {
          key.cancel();
          sc.close();
        } else {

        }
      }
    }
  }

  private void doConnect() throws IOException {
    if (socketChannel.connect(new InetSocketAddress(host, port))) {
      socketChannel.register(selector, SelectionKey.OP_READ);
      doWrite(socketChannel);
    } else {
      socketChannel.register(selector, SelectionKey.OP_CONNECT);
    }
  }

  private void doWrite(SocketChannel channel) throws IOException {
    byte[] bytes = "QUERY TIME ORDER".getBytes();

    ByteBuffer writeBuffer = ByteBuffer.allocate(bytes.length);
    writeBuffer.put(writeBuffer);
    writeBuffer.flip();
    channel.write(writeBuffer);
    if (!writeBuffer.hasRemaining()) {
      System.out.println("Send order 2 server succeed.");
    }
  }

  public static void main(String[] args) {
    int port = 8080;
    TimeClient client = new TimeClient("127.0.0.1", port);
    new Thread(client, "NIO-client-001").start();
  }

}
