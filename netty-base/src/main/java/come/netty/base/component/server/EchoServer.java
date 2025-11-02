package come.netty.base.component.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.net.InetSocketAddress;

public class EchoServer {

    private final int port;

    public EchoServer(int port) {
        this.port = port;
    }

    public static void main(String[] args) throws Exception {

        if (args.length != 1) {
            System.err.println("Usage: " + EchoServer.class.getSimpleName() + " <port>");
        }
        int port = Integer.parseInt(args[0]);
        new EchoServer(port).start();
    }


    public void start() throws Exception {
        final EchoServerHandler serverHandler = new EchoServerHandler();
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap();
            b.group(group)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(new InetSocketAddress(port))
                    .childHandler(new ChannelInitializer<SocketChannel>(){  //Adds an EchoServerHandler to the Channel’s ChannelPipeline
                        @Override
                        public void initChannel(SocketChannel ch)
                                throws Exception {
                            ch.pipeline().addLast(serverHandler);  //EchoServerHandler is @Sharable so we can always use the same one.
                        }
                    });
            ChannelFuture f = b.bind().sync(); //Binds the server asynchronously; sync() waits for the bind to complete.
            f.channel().closeFuture().sync();  //Gets the CloseFuture of the Channel and blocks the current thread until it’s complete
        } finally {
            group.shutdownGracefully().sync(); //Shuts down the EventLoopGroup, releasing all resources
        }
    }

//In (2) you create a ServerBootstrap instance. Because you’re using the NIO transport, you specify the NioEventLoopGroup b to accept and handle new connections and the NioServerSocketChannel d as the channel type.
//After this you set the local address to an InetSocketAddress with the selected port e. The server will bind to this address to listen for new connection requests.

//In 5 you make use of a special class, ChannelInitializer. This is key. When a new connection is accepted, a new child Channel will be created, and the ChannelInitializer will add an instance of your EchoServerHandler to the Channel’s ChannelPipeline.
//As we explained earlier, this handler will receive notifications about inbound messages.
//
//Although NIO is scalable, its proper configuration, especially as regards multithreading, is not trivial.
//Netty’s design encapsulates most of the complexity, and we’ll discuss the relevant abstractions (EventLoopGroup, SocketChannel, and ChannelInitializer) in more detail in chapter 3.
//
//Next you bind the server g and wait until the bind completes. (The call to sync() causes the current Thread to block until then.)
//At h, the application will wait until the server’s Channel closes (because you call sync() on the Channel’s CloseFuture).
//You can then shut down the EventLoopGroup and release all resources, including all created threads i.
//
//NIO is used in this example because it’s currently the most widely used transport,thanks to its scalability and thoroughgoing asynchrony.
//But a different transport implementation could be used as well.
//If you wished to use the OIO transport in yourserver, you’d specify OioServerSocketChannel and OioEventLoopGroup. We’ll explore transports in greater detail in chapter 4.



//In the meantime, let’s review the important steps in the server implementation you just completed. These are the primary code components of the server:
//■ The EchoServerHandler implements the business logic.
//■ The main() method bootstraps the server.
//The following steps are required in bootstrapping:
//■ Create a ServerBootstrap instance to bootstrap and bind the server.
//■ Create and assign an NioEventLoopGroup instance to handle event processing, such as accepting new connections and reading/writing data.
//■ Specify the local InetSocketAddress to which the server binds.
//■ Initialize each new Channel with an EchoServerHandler instance.
//■ Call ServerBootstrap.bind() to bind the server.
//At this point the server is initialized and ready to be used. In the next section we’ll examine the code for the client application.



}
