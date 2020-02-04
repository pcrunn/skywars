package wtf.retarders.skywars.handler.impl;

import lombok.Data;
import wtf.retarders.skywars.SkywarsPlugin;
import wtf.retarders.skywars.handler.IHandler;

@Data
public class DataHandler implements IHandler {

    private int maxPlayers;
    private boolean teams;

    public DataHandler(int maxPlayers, boolean teams) {
        this.maxPlayers = maxPlayers;
        this.teams = teams;

        SkywarsPlugin.getPlugin().getHandlerManager().registerHandler(this);
    }

    public DataHandler() {
        this.maxPlayers = 16;
        this.teams = false;

        SkywarsPlugin.getPlugin().getHandlerManager().registerHandler(this);
    }
}
