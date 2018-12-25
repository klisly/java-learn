package netty.hello;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;

/**
 * Created by wizardholy on 2017/8/1.
 */
public class Client {
    public static void main(String args[]) {
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        // Server服务启动器
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workerGroup)
                .channel(SocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() { // (4)
                    @Override
                    public void initChannel(SocketChannel ch) throws Exception {
                        ch.pipeline().addLast(new HelloClientHandler());
                    }
                })
                .option(ChannelOption.SO_BACKLOG, 128);        // (5)

        // Bind and start to accept incoming connections.
        ChannelFuture f = null; // (7)
        bootstrap.connect("localhost", 6666);
    }

    private static class HelloClientHandler extends
            ChannelInboundHandlerAdapter {
        /**
         * 当有客户端绑定到服务端的时候触发，打印"Hello world, I'm server."
         *
         * @alia OneCoder
         * @author lihzh
         */
        @Override
        public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
            super.channelRegistered(ctx);
            System.out.println("Hello world, I'm server.");
        }
    }
}
