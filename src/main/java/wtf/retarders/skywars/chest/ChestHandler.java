package wtf.retarders.skywars.chest;

import lombok.Getter;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import wtf.retarders.skywars.handler.IHandler;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ChestHandler implements IHandler {

    private List<BlockState> filledChests = new ArrayList<>();

    public void fillChest(BlockState block, ChestType chestType) {
        if(!(block instanceof Chest)) {
            throw new IllegalArgumentException("block is not instance of a chest");
        }

        Chest chest = (Chest) block;
        chest.getInventory().addItem(chestType.getRandomItem());
        this.filledChests.add(block);
    }
}