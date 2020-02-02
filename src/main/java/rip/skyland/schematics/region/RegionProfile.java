package rip.skyland.schematics.region;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class RegionProfile {

    public static List<RegionProfile> regionProfiles = new ArrayList<>();

    public UUID player;
    public Region currentRegion;

    public RegionProfile(UUID player) {
        this.player = player;
        regionProfiles.add(this);
    }

    public void delete() {
        regionProfiles.remove(this);
        this.currentRegion = null;
    }

    public static RegionProfile getByUuid(UUID uuid) {
        return regionProfiles.stream().filter(regionProfile -> regionProfile.getPlayer().equals(uuid)).findFirst().orElse(null);
    }

}
