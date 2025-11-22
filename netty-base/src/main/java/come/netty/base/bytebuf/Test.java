package come.netty.base.bytebuf;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.CompositeByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.buffer.UnpooledHeapByteBuf;

public class Test {

    public static void main(String[] args) {
//        ByteBuf heapBuf = new UnpooledHeapByteBuf();
//        if (heapBuf.hasArray()) {
//            byte[] array = heapBuf.array();
//            int offset = heapBuf.arrayOffset() + heapBuf.readerIndex();
//            int length = heapBuf.readableBytes();
//            handleArray(array, offset, length);
//        }
//
//
//        //direct
//        ByteBuf directBuf = ...;
//        if (!directBuf.hasArray()) {
//            int length = directBuf.readableBytes();
//            byte[] array = new byte[length];
//            directBuf.getBytes(directBuf.readerIndex(), array);
//            handleArray(array, 0, length);
//        }
//
//
//
//        //composite
//        CompositeByteBuf messageBuf = Unpooled.compositeBuffer();
//        ByteBuf headerBuf = ...; // can be backing or direct
//        ByteBuf bodyBuf = ...; // can be backing or direct
//        messageBuf.addComponents(headerBuf, bodyBuf);
//        messageBuf.hasArray()
//
//        messageBuf.removeComponent(0); // remove the header
//        for (ByteBuf buf : messageBuf) {
//            System.out.println(buf.toString());
//        }
    }
}
