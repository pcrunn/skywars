package wtf.retarders.skywars;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import wtf.retarders.skywars.handler.HandlerManager;
import wtf.retarders.skywars.netty.NettyThread;

@Getter
public class SkywarsPlugin extends JavaPlugin {

    private HandlerManager handlerManager;

    public void onEnable() {
        this.handlerManager = new HandlerManager();
        Bukkit.getScheduler().runTaskTimerAsynchronously(this, new NettyThread(), 60L, 0L);
    }

    public void onDisable() {

    }

    public static SkywarsPlugin getPlugin() {
        return SkywarsPlugin.getPlugin(SkywarsPlugin.class);
    }

}
