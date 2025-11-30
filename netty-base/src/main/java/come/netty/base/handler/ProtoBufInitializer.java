package come.netty.base.handler;

import io.netty.channel.*;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;

public class ProtoBufInitializer extends ChannelInitializer<Channel> {

//    private final MessageLite lite;
//    public ProtoBufInitializer(MessageLite lite) {
//        this.lite = lite;
//    }

    @Override
    protected void initChannel(Channel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
        pipeline.addLast(new ProtobufVarint32FrameDecoder());
        pipeline.addLast(new ProtobufEncoder());
//        pipeline.addLast(new ProtobufDecoder(lite));
        pipeline.addLast(new MarshallingInitializer.ObjectHandler());

    }

    public static final class ObjectHandler extends SimpleChannelInboundHandler<Object> {

        @Override
        protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
            // Do something with the object
        }
    }

    //In this section we explored the different serialization options supported by Netty’s
    //specialized decoders and encoders: standard JDK serialization, JBoss Marshalling, and
    //Google’s Protocol Buffers.

}
