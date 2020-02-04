package wtf.retarders.skywars.handler;

import lombok.Getter;
import rip.skyland.schematics.SchematicHandler;
import wtf.retarders.skywars.map.MapHandler;
import wtf.retarders.skywars.profile.ProfileHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public class HandlerManager {

    private List<IHandler> handlers = new ArrayList<>();

    public HandlerManager() {
        Arrays.asList(
                new SchematicHandler(),
                new MapHandler(),
                new ProfileHandler()
        ).forEach(this::registerHandler);

        this.handlers.forEach(IHandler::load);
    }

    public void registerHandler(IHandler handler) {
        if(handlers.stream().anyMatch(targetHandler -> targetHandler.getClass().equals(handler.getClass()))) {
            throw new IllegalArgumentException("handler with same class already registered");
        }

        this.handlers.add(handler);
    }

    @SuppressWarnings("unchecked")
    public <T> T findHandler(Class<T> clazz) {
        return (T) handlers.stream()
                .filter(handler -> handler.getClass().equals(clazz))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("theres no handler with that class available"));
    }

}
