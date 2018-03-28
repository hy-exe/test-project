package cn.pattern.demo.server;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
/**
 *  1. 创建一个ServerSocket对象；
 *	2. 调用ServerSocket对象的accept方法，等待连接，连接成功会返回一个Socket对象，否则一直阻塞等待；
 *	3. 从Socket对象中获取InputStream和OutputStream字节流，这两个流分别对应request请求和response响应；
 *	4. 处理请求：读取InputStream字节流信息，转成字符串形式，并解析，这里的解析比较简单，仅仅获取uri(统一资源标识符)信息;
 *	5. 处理响应：根据解析出来的uri信息，从WEB_ROOT目录中寻找请求的资源资源文件, 读取资源文件，并将其写入到OutputStream字节流中；
 *	6. 关闭Socket对象；
 *	7. 转到步骤2，继续等待连接请求；
 */
public class HttpServer {
	/**
     * WEB_ROOT是HTML和其它文件存放的目录. 这里的WEB_ROOT为工作目录下的webroot目录
     */
    public static final String WEB_ROOT = System.getProperty("user.dir") + File.separator + "webroot";
    
    // 关闭服务命令
    private static final String SHUTDOWN_COMMAND = "/SHUTDOWN";
    
    public static void main(String[] args) {
        HttpServer server = new HttpServer();
        //等待连接请求
        server.await();
    }

	private void await() {
		ServerSocket serverSocket = null;
        int port = 8080;
        try {
            //服务器套接字对象
            serverSocket = new ServerSocket(port, 1, InetAddress.getByName("127.0.0.1"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
        
        // 循环等待一个请求
        while (true) {
           Socket socket = null;
           InputStream input = null;
           OutputStream output = null;
           try {
        	   System.out.println("-------服务器运行中-------");
               //等待连接，连接成功后，返回一个Socket对象
               socket = serverSocket.accept();
               input = socket.getInputStream();  //获取网络连接输入
               output = socket.getOutputStream(); //输出，连接的另一端得到输入

               // 创建Request对象并解析
               Request request = new Request(input);
               request.parse();
               // 检查是否是关闭服务命令
               if (request.getUri().equals(SHUTDOWN_COMMAND)) {
                   break;
               }

               // 创建 Response 对象
               Response response = new Response(output);
               response.setRequest(request);
               response.sendStaticResource();

               // 关闭 socket 对象
               socket.close();
               
           } catch (Exception e) {
               e.printStackTrace();
               continue;
           }
        }
		
	}	
}