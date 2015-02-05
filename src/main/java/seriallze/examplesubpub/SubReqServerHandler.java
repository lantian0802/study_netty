package seriallze.examplesubpub;

        import io.netty.channel.ChannelHandlerAdapter;
        import io.netty.channel.ChannelHandlerContext;

/**
 * Created by jianying.wcj on 2015/2/2 0002.
 */
public class SubReqServerHandler extends ChannelHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx,Object msg) throws Exception {
        SubscribeReq req = (SubscribeReq)msg;
        System.out.println("Service accept client subscribe req : [" + req.toString() + "]");
        ctx.writeAndFlush(resp(req.getSubReqID()));
        System.out.println("debug");
    }

    private SubscribeResp resp(int subReqID) {

        SubscribeResp resp = new SubscribeResp();
        resp.setSubReqID(1234);
        resp.setRespCode(0);
        resp.setDesc("Netty book order succed, 3 days later,sent to the designated address");
        return resp;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx,Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }
}
