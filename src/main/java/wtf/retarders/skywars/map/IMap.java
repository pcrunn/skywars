package wtf.retarders.skywars.map;

import org.bukkit.Location;
import rip.skyland.schematics.schematic.ISchematic;

import java.util.List;

public interface IMap extends ISchematic {

    Location getLobbyLocation();
    List<Location> getSpawnPoints();
    boolean isTeamMap();

}
