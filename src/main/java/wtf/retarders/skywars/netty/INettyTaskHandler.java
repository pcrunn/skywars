package wtf.retarders.skywars.netty;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;

public interface INettyTaskHandler extends ChannelHandler {

    void channelRead(ChannelHandlerContext ctx, Object msg);

}
