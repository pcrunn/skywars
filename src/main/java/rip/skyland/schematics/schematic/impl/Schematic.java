package rip.skyland.schematics.schematic.impl;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import rip.skyland.schematics.SchematicLoader;
import rip.skyland.schematics.SchematicLoaderAPI;
import rip.skyland.schematics.schematic.ISchematic;
import rip.skyland.schematics.schematic.block.SchematicBlock;
import rip.skyland.schematics.util.JsonConfig;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Schematic implements ISchematic {

    public List<SchematicBlock> schematicBlocks;
    public String name;
    public Location middle;
    public JsonConfig config;


    public Schematic(JsonConfig config) {
        schematicBlocks = new ArrayList<>();
        this.config = config;
        SchematicLoaderAPI.INSTANCE.getSchematics().add(this);
    }

    public void build(Location startLocation) {
        Bukkit.getScheduler().runTask(SchematicLoader.getInstance(), () -> schematicBlocks.forEach(schematicBlock -> {
            Location location = startLocation.add(schematicBlock.x, schematicBlock.y, schematicBlock.z);

            location.getBlock().setType(Material.valueOf(schematicBlock.blockMaterial));
            location.getBlock().setData(schematicBlock.blockData);
        }));
    }

    public int[] getWidthDepthHeight(Location location1, Location location2) {
        int[] maxAndMinX = getMaxAndMinimumX(location1, location2);
        int[] maxAndMinY = getMaxAndMinimumY(location1, location2);
        int[] maxAndMinZ = getMaxAndMinimumZ(location1, location2);

        return new int[] {
                maxAndMinX[1]-maxAndMinX[0],
                maxAndMinY[1]-maxAndMinY[0],
                maxAndMinZ[1]-maxAndMinZ[0]
        };
    }

    private int[] getMaxAndMinimumX(Location location1, Location location2) {
        return new int[] { Math.min(location1.getBlockX(), location2.getBlockX()), Math.max(location1.getBlockX(), location2.getBlockX())};
    }

    private int[] getMaxAndMinimumY(Location location1, Location location2) {
        return new int[] { Math.min(location1.getBlockY(), location2.getBlockY()), Math.max(location1.getBlockY(), location2.getBlockY())};
    }

    private int[] getMaxAndMinimumZ(Location location1, Location location2) {
        return new int[] { Math.min(location1.getBlockZ(), location2.getBlockZ()), Math.max(location1.getBlockZ(), location2.getBlockZ())};
    }

}
