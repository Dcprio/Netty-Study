package come.netty.base.component;


import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;

@ChannelHandler.Sharable   //Indicates that a ChannelHandler can be safely shared by multiple channels
public class EchoServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        //Writes the received message to the sender without flushing the outbound messages
        ByteBuf in = (ByteBuf) msg;
        System.out.println("Server received: " + in.toString(CharsetUtil.UTF_8));
        ctx.write(in);
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        //Flushes pending messages to the remote peer and closes the channel
        ctx.writeAndFlush(Unpooled.EMPTY_BUFFER)
                    .addListener(ChannelFutureListener.CLOSE);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        cause.printStackTrace();
        ctx.close();
    }

    //ChannelInboundHandlerAdapter has a straightforward API, and each of its methods can be overridden to hook into the event lifecycle at the appropriate point.
    //Because you need to handle all received data, you override channelRead().
    //In this server you simply echo the data to the remote peer.


    //Overriding exceptionCaught() allows you to react to any Throwable subtypes— here you log the exception and close the connection.
    //A more elaborate application might try to recover from the exception, but in this case simply closing the connection signals to the remote peer that an error has occurred.

//    In addition to ChannelInboundHandlerAdapter, there are many ChannelHandler subtypes and implementations to learn about, and we’ll cover these in detail in chapters 6 and 7. For now, please keep these key points in mind:
//            ■ ChannelHandlers are invoked for different types of events.
//            ■ Applications implement or extend ChannelHandlers to hook into the event lifecycle and provide custom application logic.
//            ■ Architecturally, ChannelHandlers help to keep your business logic decoupled from networking code. This simplifies development as the code evolves in response to changing requirements.

}
