package rip.skyland.schematics.util;

import org.bukkit.plugin.java.JavaPlugin;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import rip.skyland.schematics.SchematicLoaderAPI;
import rip.skyland.schematics.schematic.ISchematic;

import java.io.*;
import java.lang.reflect.InvocationTargetException;

public class JsonConfig {

    private File jsonFile;

    public JsonConfig(JavaPlugin plugin, String fileName) {
        this.jsonFile = new File(plugin.getDataFolder(), fileName + ".json");
    }

    public void storeSchematic(ISchematic schematic) {
        JSONObject document = new JSONObject();
        document.put("name", schematic.getName());

        JSONArray array = new JSONArray();
        schematic.getSchematicBlocks().forEach(schematicBlock -> array.add(SchematicLoaderAPI.INSTANCE.gson.toJson(schematicBlock)));
        document.put("blocks", array);

        try {
            FileWriter file = new FileWriter(jsonFile);
            document.writeJSONString(file);
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public ISchematic loadSchematicFile(Class<? extends ISchematic> schematicType) {
        ISchematic schematic = null;

        try {
            schematic = schematicType.getDeclaredConstructor(JsonConfig.class).newInstance(this);
            JSONParser parser = new JSONParser();

            JSONObject document = (JSONObject) parser.parse(new FileReader(jsonFile));

            schematic.setName((String) document.get("name"));
            JSONArray jsonArray = (JSONArray) document.get("blocks");
            ISchematic finalSchematic = schematic;
            jsonArray.forEach(string -> finalSchematic.getSchematicBlocks().add(SchematicLoaderAPI.INSTANCE.deserializeSchematicBlock((String) string)));
        } catch (IOException | ParseException | NoSuchMethodException | IllegalAccessException | InstantiationException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return schematic;
    }
}
