package wtf.retarders.skywars.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.SneakyThrows;
import wtf.retarders.skywars.netty.impl.NettyGameInformationHandler;
import wtf.retarders.skywars.netty.impl.PlayerJoinHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NettyThread implements Runnable {

    private List<INettyTaskHandler> taskHandlers = new ArrayList<>();

    public NettyThread() {
        this.taskHandlers.addAll(Arrays.asList(
                new NettyGameInformationHandler(),
                new PlayerJoinHandler()
        ));
    }

    @SneakyThrows
    @Override
    public void run() {
        if (!taskHandlers.isEmpty()) {
            EventLoopGroup bossGroup = new NioEventLoopGroup();
            EventLoopGroup workerGroup = new NioEventLoopGroup();

            try {
                ServerBootstrap bootstrap = new ServerBootstrap();
                taskHandlers.forEach(handler -> bootstrap.group(bossGroup, workerGroup)
                        .channel(NioServerSocketChannel.class)
                        .childHandler(new ChannelInitializer<SocketChannel>() {
                            public void initChannel(SocketChannel ch) {
                                ch.pipeline().addLast(handler);
                            }
                        })
                        .childOption(ChannelOption.SO_KEEPALIVE, true));


                ChannelFuture f = bootstrap.bind(8080).sync();
                f.channel().closeFuture().sync();
            } finally {
                workerGroup.shutdownGracefully();
                bossGroup.shutdownGracefully();
            }
        }
    }
}