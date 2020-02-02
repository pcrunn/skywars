package wtf.retarders.skywars.netty.impl;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import wtf.retarders.skywars.handler.impl.DataHandler;
import wtf.retarders.skywars.netty.INettyTaskHandler;

public class GameInformationNettyHandler implements INettyTaskHandler {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        ByteBuf byteBuf = (ByteBuf) msg;

        int maxPlayers = byteBuf.getInt(0);
        boolean teams = byteBuf.getBoolean(1);

        new DataHandler(maxPlayers, teams);
    }

    @Override public void handlerAdded(ChannelHandlerContext channelHandlerContext) { }
    @Override public void handlerRemoved(ChannelHandlerContext channelHandlerContext) { }
    @Override public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable throwable) { }
}
