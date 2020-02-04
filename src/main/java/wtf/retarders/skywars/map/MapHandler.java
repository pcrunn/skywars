package wtf.retarders.skywars.map;

import wtf.retarders.skywars.handler.IHandler;
import wtf.retarders.skywars.handler.impl.DataHandler;

import java.util.ArrayList;
import java.util.List;

public class MapHandler implements IHandler {

    private List<IMap> maps = new ArrayList<>();

    @Override
    public void load() {

    }

    public IMap findMap(DataHandler dataHandler) {
        return maps.stream()
                .filter(map -> map.isTeamMap() == dataHandler.isTeams() && map.getSpawnPoints().size() == dataHandler.getMaxPlayers())
                .findFirst().orElse(null);
    }
}
