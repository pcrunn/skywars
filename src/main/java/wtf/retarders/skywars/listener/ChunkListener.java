package wtf.retarders.skywars.listener;

import org.bukkit.block.Chest;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.world.ChunkLoadEvent;
import wtf.retarders.skywars.SkywarsPlugin;
import wtf.retarders.skywars.chest.ChestHandler;
import wtf.retarders.skywars.chest.ChestType;

import java.util.Arrays;

public class ChunkListener implements Listener {

    @EventHandler
    public void onChunkLoad(ChunkLoadEvent event) {
        Arrays.stream(event.getChunk().getTileEntities()).forEach(blockState -> {
            ChestHandler chestHandler = SkywarsPlugin.getPlugin().getHandlerManager().findHandler(ChestHandler.class);
            if (blockState instanceof Chest && !chestHandler.getFilledChests().contains(blockState)) {
                chestHandler.fillChest(blockState, ChestType.NORMAL);
            }
        });
    }
}