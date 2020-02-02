package rip.skyland.schematics;

import com.google.common.base.Preconditions;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.Getter;
import org.bukkit.Location;
import org.bukkit.block.Block;
import rip.skyland.schematics.region.Region;
import rip.skyland.schematics.schematic.impl.Schematic;
import rip.skyland.schematics.schematic.block.SchematicBlock;
import rip.skyland.schematics.util.JsonConfig;
import wtf.retarders.skywars.SkywarsPlugin;

import java.util.ArrayList;
import java.util.List;

public enum SchematicLoaderAPI {

    INSTANCE;

    @Getter
    public List<Schematic> schematics = new ArrayList<>();
    public Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public SchematicBlock deserializeSchematicBlock(String string) {
        return gson.fromJson(string, SchematicBlock.class);
    }

    public Schematic createNewSchematic(Region region) {
        Preconditions.checkArgument(schematics.stream().noneMatch(schematic -> schematic.getName().equalsIgnoreCase(region.getName())), "Schematic with that name already exists");

        Schematic schematic = new Schematic(new JsonConfig(SkywarsPlugin.getPlugin(), region.getName()));
        schematic.setName(region.getName());

        Location point1 = region.getFirstLocation();
        Location point2 = region.getSecondLocation();

        System.out.println(point1.toString());
        System.out.println(point2.toString());


        int startX = Math.min(point1.getBlockX(), point2.getBlockX());
        double startY = Math.min(point1.getBlockY(), point2.getBlockY());
        double startZ = Math.min(point1.getBlockZ(), point2.getBlockZ());
        double dimX = Math.max(point1.getBlockX(), point2.getBlockX()) - startX;
        double dimY = Math.max(point1.getBlockY(), point2.getBlockY()) - startY;
        double dimZ = Math.max(point1.getBlockZ(), point2.getBlockZ()) - startZ;


        for (double j = 0; j <= dimY; ++j) {
            for (double i = 0; i <= dimX; ++i) {
                for (double k = 0; k <= dimZ; ++k) {
                    Block block = new Location(point1.getWorld(), startX + i, startY + j, startZ + k).getBlock();
                    schematic.getSchematicBlocks().add(new SchematicBlock(block.getType().name(), block.getData(), i, j, k));
                }
            }

        }
        return schematic;
    }
}
