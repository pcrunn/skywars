package rip.skyland.schematics.schematic.block;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class SchematicBlock {

    public String blockMaterial;
    public byte blockData;
    public double x, y, z;
}