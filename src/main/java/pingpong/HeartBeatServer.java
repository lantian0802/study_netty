package pingpong;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * Created by jianying.wcj on 2015/1/26 0026.
 */
public class HeartBeatServer {

    public void bind(int port) throws Exception {
        /**
         * 配置服务端的NIO线程组
         */
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try{
            ServerBootstrap b = new ServerBootstrap();
            b.group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG,1024)
                    .childHandler(new ChildChannelHandler());
            /**
             * 绑定端口，同步等待成功
             */
            ChannelFuture f = b.bind(port).sync();
            /**
             * 等待服务端监听端口关闭
             */
            f.channel().closeFuture().sync();
        } finally {
            /**
             * 优雅退出，释放线程池资源
             */
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }
    }

    private class ChildChannelHandler extends ChannelInitializer<SocketChannel> {

        protected void initChannel(SocketChannel ch) throws Exception {
            ChannelPipeline pipeline = ch.pipeline();
            //pipeline.addLast(new MsgEncoder());
            pipeline.addLast(new MsgDecoder());
            pipeline.addLast(new HeartBeatServerHandler());
        }
    }

    public static void main(String[] args) throws Exception {
        int port = 8080;
        if(args != null && args.length > 0) {
            try {
                port = Integer.valueOf(args[0]);
            } catch(NumberFormatException e) {
                //ignore
            }
        }
        new HeartBeatServer().bind(port);
    }
}
