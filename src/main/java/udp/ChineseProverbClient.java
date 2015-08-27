package udp;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;
import io.netty.util.CharsetUtil;

import java.net.InetSocketAddress;

/**
 * Created by jianying.wcj on 2015/8/27 0027.
 */
public class ChineseProverbClient {

    public void run(int port) throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        Bootstrap b = new Bootstrap();
        try {
            b.group(group).channel(NioDatagramChannel.class)
                    .option(ChannelOption.SO_BROADCAST, true)
                    .handler(new ChineseProverbClientHandler());
            //选择0这个端口，表示让系统随机选择一个本地端口。
            Channel ch = b.bind(0).sync().channel();
            ByteBuf content = Unpooled.copiedBuffer("q".getBytes());
            ch.writeAndFlush(
                    new DatagramPacket(content,new InetSocketAddress("255.255.255.255",port))).sync();
            if(!ch.closeFuture().await(15000)) {
                System.out.println("查询超时");
            }
        } finally {
            group.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {
        int port = 8080;
        if(args.length > 0) {
            port = Integer.parseInt(args[0]);
        }
        new ChineseProverbClient().run(port);
    }
}
