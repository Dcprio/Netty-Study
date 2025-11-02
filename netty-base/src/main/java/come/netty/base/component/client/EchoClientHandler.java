package come.netty.base.component.client;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;

@ChannelHandler.Sharable
public class EchoClientHandler extends
        SimpleChannelInboundHandler<ByteBuf> {
    @Override
    public void channelActive(ChannelHandlerContext ctx) {
        ctx.writeAndFlush(Unpooled.copiedBuffer("Netty rocks sdhflsdhflkjaksjjsljdfkljklasjkljdfkjaklsjkfjklasjdklfjklajkfljskljdfjlkajklsdjfkljaklsdjfkljaklsdjfkljaksldjfkljaklsjdksdhflkjaksjjsljdfkljklasjkljdfkjaklsjkfjklasjdklfjklajkfljskljdfjlkajklsdjfkljaklsdjfkljaklsdjfkljaksldjfkljaklsjdksdhflkjaksjjsljdfkljklasjkljdfkjaklsjkfjklasjdklfjklajkfljskljdfjlkajklsdjfkljaklsdjfkljaklsdjfkljaksldjfkljaklsjdksdhflkjaksjjsljdfkljklasjkljdfkjaklsjkfjklasjdklfjklajkfljskljdfjlkajklsdjfkljaklsdjfkljaklsdjfkljaksldjfkljaklsjdksdhflkjaksjjsljdfkljklasjkljdfkjaklsjkfjklasjdklfjklajkfljskljdfjlkajklsdjfkljaklsdjfkljaklsdjfkljaksldjfkljaklsjdksdhflkjaksjjsljdfkljklasjkljdfkjaklsjkfjklasjdklfjklajkfljskljdfjlkajklsdjfkljaklsdjfkljaklsdjfkljaksldjfkljaklsjdksdhflkjaksjjsljdfkljklasjkljdfkjaklsjkfjklasjdklfjklajkfljskljdfjlkajklsdjfkljaklsdjfkljaklsdjfkljaksldjfkljaklsjdksdhflkjaksjjsljdfsdhflkjaksjjsljdfkljklasjkljdfkjaklsjkfjklasjdklfjklajkfljskljdfjlkajklsdjfkljaklsdjfkljaklsdjfkljasdhflkjaksjjsljdfkljklasjkljdfkjaklsjkfjklasjdklfjklajkfljskljdfjlkajklsdjfkljaklsdjfkljaklsdjfkljaksldjfkljaklsjdksdhflkjaksjjsljdfkljklasjkljdfkjaklsjkfjklasjdklfjklajkfljskljdfjlkajklsdjfkljaklsdjfkljaklsdjfkljaksldjfkljaklsjdksdhflkjaksjjsljdfkljklasjkljdfkjaklsjkfjklasjdklfjklajkfljskljdfjlkajklsdjfkljaklsdjfkljaklsdjfkljaksldjfkljaklsjdksdhflkjaksjjsljdfkljklasjkljdfkjaklsjkfjklasjdklfjklajkfljskljdfjlkajklsdjfkljaklsdjfkljaklsdjfkljaksldjfkljaklsjdksdhflkjaksjjsljdfkljklasjkljdfkjaklsjkfjklasjdklfjklajkfljskljdfjlkajklsdjfkljaklsdjfkljaklsdjfkljaksldjfkljaklsjdklfjkljaskdljfkljaskldjfkljaskljfkldjklasjfkljsdklajfkljsdklajlkfjkljkalsjlfjkljaskdljfkljaskldjfkljaskljfkldjklasjfkljsdklajfkljsdklajlkfjkljkalsjlfjkljaskdljfkljaskldjfkljaskljfkldjklasjfkljsdklajfkljsdklajlkfjkljkalsjlfjkljaskdljfkljaskldjfkljaskljfkldjklasjfkljsdklajfkljsdklajlkfjkljkalsjlfjkljaskdljfkljaskldjfkljaskljfkldjklasjfkljsdklajfkljsdklajlkfjkljkalsjksldjfkljaklsjdklfjkljaskdljfkljaskldjfkljaskljfkldjklasjfkljsdklajfkljsdklajlkfjkljkalsjkljklasjkljdfkjaklsjkfjklasjdklfjklajkfljskljdfjlkajklsdjfkljaklsdjfkljaklsdjfkljaksldjfkljaklsjdklfjkljaskdljfkljaskldjfkljaskljfkldjklasjfkljsdklajfkljsdklajlkfjkljkalsjlfjkljaskdljfkljaskldjfkljaskljfkldjklasjfkljsdklajfkljsdklajlkfjkljkalsjlfjkljaskdljfkljaskldjfkljaskljfkldjklasjfkljsdklajfkljsdklajlkfjkljkalsjlfjkljaskdljfkljaskldjfkljaskljfkldjklasjfkljsdklajfkljsdklajlkfjkljkalsjlfjkljaskdljfkljaskldjfkljaskljfkldjklasjfkljsdklajfkljsdklajlkfjkljkalsjlfjkljaskdljfkljaskldjfkljaskljfkldjklasjfkljsdklajfkljsdklajlkfjkljkalsjlfjkljaskdljfkljaskldjfkljaskljfkldjklasjfkljsdklajfkljsdklajlkfjkljkalsjlfjkljaskdljfkljaskldjfkljaskljfkldjklasjfkljsdklajfkljsdklajlkfjkljkalsjkjaksjjsljdfkljklasjkljdfkjaklsjkfjklasjdklfjklajkfljskljdfjlkajklsdjfkljaklsdjfkljaklsdjfkljaksldjfkljaklsjdklfjkljaskdljfkljaskldjfkljaskljfkldjklasjfkljsdklajfkljsdklajlkfjkljkalsjfdk!",CharsetUtil.UTF_8));  //When notified that the channel is active, sends a message
    }
    @Override
    public void channelRead0(ChannelHandlerContext ctx, ByteBuf in) {
        System.out.println("Client received: " + in.toString(CharsetUtil.UTF_8));  //Logs a dump of the received message
    }
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {  //On exception, logs the error and closes channel
        ctx.close();
    }
}


//First you override channelActive(), invoked when a connection has been established.
//This ensures that something is written to the server as soon as possible, which in this case is a byte buffer that encodes the string "Netty rocks!".
//
//Next you override the method channelRead0(). This method is called whenever data is received. Note that the message sent by the server may be received in chunks.
//
//That is, if the server sends 5 bytes, there’s no guarantee that all 5 bytes will be received at once. Even for such a small amount of data, the channelRead0() method could be called twice, first with a ByteBuf (Netty’s byte container) holding 3 bytes, and second with a ByteBuf holding 2 bytes. \
//As a stream-oriented protocol, TCP guarantees that the bytes will be received in the order in which they were sent by the server.
//
//The third method you override is exceptionCaught(). Just as in EchoServerHandler (listing 2.2), Throwable is logged and the channel is closed, in this case terminating the connection to the server.


//SimpleChannelInboundHandler vs. ChannelInboundHandler
//You may be wondering why we used SimpleChannelInboundHandler in the client instead of the ChannelInboundHandlerAdapter used in the EchoServerHandler.
//This has to do with the interaction of two factors: how the business logic processes messages and how Netty manages resources.
//
//In the client, when channelRead0() completes, you have the incoming message and you’re done with it.
//When the method returns, SimpleChannelInboundHandler takes care of releasing the memory reference to the ByteBuf that holds the message.
//
//In EchoServerHandler you still have to echo the incoming message to the sender, and the write() operation, which is asynchronous, may not complete until after channelRead() returns (shown in listing 2.1).
//For this reason EchoServerHandler extends ChannelInboundHandlerAdapter, which doesn’t release the message at this point.
//
//The message is released in channelReadComplete() in the EchoServerHandler when writeAndFlush() is called (listing 2.1).
//Chapters 5 and 6 will cover message resource management in detail.