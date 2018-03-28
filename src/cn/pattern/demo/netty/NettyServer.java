package cn.pattern.demo.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * @author yin.huang
 * @date 2017年9月28日 下午2:11:51
 */
public class NettyServer {

  public static void main(String[] args) throws Exception {

    // NioEventLoopGroup 线程组
    // 一个用于服务器端接收客户端请求，一个用于进行socketChannel的网络读写
    EventLoopGroup bossGroup = new NioEventLoopGroup();
    EventLoopGroup workerGroup = new NioEventLoopGroup();

    try {
      ServerBootstrap b = new ServerBootstrap();

      b = b.group(bossGroup, workerGroup);
      /***
       * ServerSocketChannel以NIO的selector为基础进行实现的，用来接收新的连接 这里告诉Channel如何获取新的连接.
       */
      b = b.channel(NioServerSocketChannel.class);

      b = b.childHandler(new ChannelInitializer<SocketChannel>() {

        @Override
        public void initChannel(SocketChannel ch) throws Exception {
          ch.pipeline().addLast(new SimpleServerHandler());
        }
      });

      /***
       * 绑定端口并启动去接收进来的连接
       */
      ChannelFuture f = b.bind(9999).sync();
      /**
       * 这里会一直等待，直到socket被关闭
       */
      f.channel().closeFuture().sync();
    } finally {
      // 释放线程池资源
      bossGroup.shutdownGracefully();
      workerGroup.shutdownGracefully();
    }

  }

  private static class SimpleServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
      if (msg instanceof ByteBuf) {
        ByteBuf buf = (ByteBuf) msg;
        System.out.println(buf.toString());
      }
      ctx.channel().write("is ok");

    }
  }

}
