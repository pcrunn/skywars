package wtf.retarders.skywars.netty.impl;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import org.bukkit.Bukkit;
import wtf.retarders.skywars.SkywarsConstants;
import wtf.retarders.skywars.netty.INettyTaskHandler;

import java.nio.charset.StandardCharsets;

public class PlayerJoinHandler extends INettyTaskHandler {

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        if (ctx.name().equalsIgnoreCase("player_join")) {
            ByteBuf byteBuf = (ByteBuf) msg;

            boolean hidden = byteBuf.getBoolean(0);
            String name = byteBuf.getCharSequence(1, 16, StandardCharsets.UTF_8).toString();

            if(!hidden) {
                Bukkit.broadcastMessage(SkywarsConstants.JOIN_MESSAGE.replace("%name%", name).replace("%amount%", Bukkit.getOnlinePlayers() + ""));
            }
        }
    }
}
