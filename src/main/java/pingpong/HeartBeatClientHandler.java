package pingpong;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

import java.util.logging.Logger;

/**
 * Created by jianying.wcj on 2015/1/29 0029.
 */
public class HeartBeatClientHandler extends ChannelHandlerAdapter{

    private static final Logger logger = Logger.getLogger(HeartBeatClientHandler.class.getName());

    private int counter;

    private ByteBuf firstMessage;

    public HeartBeatClientHandler() {
        byte[] req = "ping".getBytes();
        firstMessage = Unpooled.buffer(req.length);
        firstMessage.writeBytes(req);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        ctx.writeAndFlush(firstMessage);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx,Object msg) throws Exception{
        System.out.println("client receive msg : " + msg);
        byte[] req1 = "ping".getBytes();
        ByteBuf firstMessage1 = Unpooled.copiedBuffer(req1);
        Thread.sleep(2000);
        ctx.writeAndFlush(firstMessage1);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        logger.warning("Unexcepted exception from downstream : "+ cause.getMessage());
        ctx.close();
    }
}
