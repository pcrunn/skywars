package wtf.retarders.skywars.profile;

import lombok.Getter;
import wtf.retarders.skywars.handler.IHandler;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ProfileHandler implements IHandler {

    private List<Profile> profiles;

    @Override
    public void load() {
        profiles = new ArrayList<>();
    }

    @Override
    public void unload() {

    }
}
