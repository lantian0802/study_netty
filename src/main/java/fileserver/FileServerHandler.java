package fileserver;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.DefaultFileRegion;
import io.netty.channel.FileRegion;
import io.netty.channel.SimpleChannelInboundHandler;

import java.io.File;
import java.io.RandomAccessFile;

/**
 * Created by jianying.wcj on 2015/8/27 0027.
 */
public class FileServerHandler extends SimpleChannelInboundHandler<String> {

    private static final String CR = System.getProperty("line.separator");

    @Override
    protected void messageReceived(ChannelHandlerContext ctx, String msg) throws Exception {
        File file = new File(msg);
        if(file.exists()) {
            if(!file.isFile()) {
                ctx.writeAndFlush("Not a file: "+file+CR);
                return;
            }
            ctx.write(file+" "+file.length()+CR);
            RandomAccessFile randomAccessFile = new RandomAccessFile(msg,"r");
            FileRegion region = new DefaultFileRegion(randomAccessFile.getChannel(),0,randomAccessFile.length());
            ctx.write(region);
            ctx.writeAndFlush(CR);
            randomAccessFile.close();
        } else {
            ctx.writeAndFlush("File not found:"+file+CR);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
    }
}
