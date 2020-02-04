package wtf.retarders.skywars.profile;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class Profile {

    private UUID uuid;

    public Profile(UUID uuid) {
        this.uuid = uuid;
    }

}
