package wtf.retarders.skywars.chest;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public enum ChestType {

    POTPVP,
    NORMAL,
    OVERPOWERED;

    ItemStack getRandomItem() {
        return new ItemStack(Material.RED_ROSE);
    }

}