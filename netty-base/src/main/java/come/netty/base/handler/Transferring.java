package come.netty.base.handler;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.DefaultFileRegion;
import io.netty.channel.FileRegion;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Transferring {

    public static void main(String[] args) throws FileNotFoundException {
//        File file = new File("D:\\123.tet");
//        FileInputStream in = new FileInputStream(file);
//        FileRegion region = new DefaultFileRegion(in.getChannel(), 0, file.length());
//        channel.writeAndFlush(region).addListener(
//                new ChannelFutureListener() {
//                    @Override
//                    public void operationComplete(ChannelFuture future)
//                            throws Exception {
//                        if (!future.isSuccess()) {
//                            Throwable cause = future.cause();
//                        }
//                    }
//                });
    }
}
