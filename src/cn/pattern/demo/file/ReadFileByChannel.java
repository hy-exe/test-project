package cn.pattern.demo.file;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author yin.huang
 * @date 2017年9月28日 上午10:50:44
 */
public class ReadFileByChannel {

  public static void main(String[] args) {

    RandomAccessFile aFile = null;
    try {
      aFile = new RandomAccessFile("E:/1.txt", "rw");
      FileChannel fileChannel = aFile.getChannel();
      ByteBuffer buf = ByteBuffer.allocate(1024); //分配空间

      int bytesRead = fileChannel.read(buf); //写入数据到Buffer
      System.out.println(bytesRead);

      while (bytesRead != -1) {
        buf.flip();
        while (buf.hasRemaining()) {
          System.out.print((char) buf.get());
        }

        buf.compact();
        bytesRead = fileChannel.read(buf);
      }
    } catch (IOException e) {
      e.printStackTrace();
    } finally {
      try {
        if (aFile != null) {
          aFile.close();
        }
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }
}
