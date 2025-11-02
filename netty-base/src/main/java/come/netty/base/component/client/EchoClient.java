package come.netty.base.component.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.net.InetSocketAddress;

public class EchoClient {
    private final String host;
    private final int port;
    public EchoClient(String host, int port) {
        this.host = host;
        this.port = port;
    }
    public void start() throws Exception {
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(group)
                    .channel(NioSocketChannel.class)
                    .remoteAddress(new InetSocketAddress(host, port))
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ch.pipeline().addLast(
                                    new EchoClientHandler());
                        }
                    });
            ChannelFuture f = b.connect().sync();  //Connects to the remote peer; waits until the connect completes
            f.channel().closeFuture().sync();      //Blocks until the Channel closes
        } finally {
            group.shutdownGracefully().sync();   //Shuts down the thread pools and the release of all resources
        }
    }


    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            System.err.println("Usage: " + EchoClient.class.getSimpleName() + " <host> <port>");
            return;
        }
        String host = args[0];
        int port = Integer.parseInt(args[1]);
        new EchoClient(host, port).start();
    }
}


//2.4.2 Bootstrapping the client
//As you’ll see in the next listing, bootstrapping a client is similar to bootstrapping a server, with the difference that instead of binding to a listening port the client uses host and port parameters to connect to a remote address, here that of the Echo server.
//
//As before, the NIO transport is used. Note that you could use different transports in the client and server; for example, NIO transport on the server side and OIO transport on the client side.
//In chapter 4 we’ll examine the factors and scenarios that would lead you to select a specific transport for a specific use case.
//Let’s review the important points introduced in this section:
//■ A Bootstrap instance is created to initialize the client.
//■ An NioEventLoopGroup instance is assigned to handle the event processing, which includes creating new connections and processing inbound and outbound data.
//■ An InetSocketAddress is created for the connection to the server.
//■ An EchoClientHandler will be installed in the pipeline when the connection is established.
//■ After everything is set up, Bootstrap.connect() is called to connect to the remote peer.
//Having finished the client, you can proceed to build the system and test it out.
