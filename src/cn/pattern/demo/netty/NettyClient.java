package cn.pattern.demo.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author yin.huang
 * @date 2017年9月28日 下午2:16:00
 */
public class NettyClient {

  public static void main(String[] args) throws Exception {

    EventLoopGroup group = new NioEventLoopGroup();
    try {
      Bootstrap b = new Bootstrap();
      b = b.group(group).channel(NioSocketChannel.class);
      b.handler(new ChannelInitializer<SocketChannel>() {

        @Override
        protected void initChannel(SocketChannel ch) throws Exception {
          // TODO Auto-generated method stub
          ch.pipeline().addLast(new SimpleClientHandler());
        }

      });
      ChannelFuture f = b.connect("127.0.0.1", 9999).sync();
      f.channel().closeFuture().sync();
    } finally {
      // TODO: handle exception
      group.shutdownGracefully();
    }

  }

  public static class SimpleClientHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {

    }

  }
}
