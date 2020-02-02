package wtf.retarders.skywars.handler;

import lombok.Getter;
import rip.skyland.schematics.SchematicHandler;
import wtf.retarders.skywars.map.MapHandler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
public class HandlerManager {

    private List<IHandler> handlers = new ArrayList<>();

    public HandlerManager() {
        this.handlers.addAll(Arrays.asList(
                new SchematicHandler(),
                new MapHandler()
        ));
    }

    @SuppressWarnings("unchecked")
    public <T> T findHandler(Class<T> clazz) {
        return (T) handlers.stream()
                .filter(handler -> handler.getClass().equals(clazz))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("theres no handler with that class available"));
    }

}
