package udp;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;
import io.netty.util.CharsetUtil;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by jianying.wcj on 2015/8/27 0027.
 */
public class ChineseProverbServerHandler extends SimpleChannelInboundHandler<DatagramPacket> {

    private static final String[] DICTIONARY = {"只要功夫深，铁杵磨成针","一寸光阴一寸金，寸金难买寸光阴"};

    private String nextQuote() {
        int quoted = ThreadLocalRandom.current().nextInt(DICTIONARY.length);
        return DICTIONARY[quoted];
    }
    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, DatagramPacket msg) throws Exception {
        ByteBuf buf = msg.content();
        byte[] req = new byte[buf.readableBytes()];
        buf.readBytes(req);
        String body = new String(req,"UTF-8");
        System.out.println(body);
        ByteBuf res = Unpooled.copiedBuffer("result:" + nextQuote(), CharsetUtil.UTF_8);
        ctx.writeAndFlush(new DatagramPacket(res,msg.sender()));
    }
}
