package rip.skyland.schematics.region;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Location;

@AllArgsConstructor
@Getter
@Setter
public class Region {

    public String name;
    public Location firstLocation;
    public Location secondLocation;

}
