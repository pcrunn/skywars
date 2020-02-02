package rip.skyland.schematics;

import org.bukkit.Bukkit;
import rip.skyland.schematics.region.RegionListeners;
import wtf.retarders.skywars.SkywarsPlugin;
import wtf.retarders.skywars.handler.IHandler;

public class SchematicHandler implements IHandler {

    public void load() {
        Bukkit.getPluginManager().registerEvents(new RegionListeners(), SkywarsPlugin.getPlugin());
    }

    public void unload() {
        SchematicLoaderAPI.INSTANCE.getSchematics().forEach(schematic -> schematic.getConfig().storeSchematic(schematic));
    }
}
