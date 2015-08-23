package pingpong;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

/**
 * Created by jianying.wcj on 2015/8/18 0018.
 */
public class MsgDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        ByteBuf inClone = Unpooled.copiedBuffer(in);
        System.out.println("decode:" + inClone.readChar());
        out.add(in.readSlice(4));
    }
}
