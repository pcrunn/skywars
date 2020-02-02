package rip.skyland.schematics.schematic;

import rip.skyland.schematics.schematic.block.SchematicBlock;
import rip.skyland.schematics.util.JsonConfig;

import java.util.List;


public interface ISchematic {


    String getName();
    List<SchematicBlock> getSchematicBlocks();
    JsonConfig getConfig();

    void setName(String name);
    List<SchematicBlock> setSchematicBlocks(List<SchematicBlock> schematicBlocks);

}
