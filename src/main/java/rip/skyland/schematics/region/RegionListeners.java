package rip.skyland.schematics.region;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import rip.skyland.schematics.SchematicLoaderAPI;
import wtf.retarders.skywars.util.CC;

public class RegionListeners implements Listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        if (event.getItem() != null && event.getItem().getType().equals(Material.GOLD_HOE)) {
            Player player = event.getPlayer();
            RegionProfile profile = RegionProfile.getByUuid(player.getUniqueId());

            if (profile != null) {
                event.setCancelled(true);

                if (player.isSneaking() && event.getAction().equals(Action.LEFT_CLICK_AIR)) {
                    if (profile.getCurrentRegion().getFirstLocation() == null || profile.getCurrentRegion().getSecondLocation() == null) {
                        player.sendMessage(CC.translate("&cYou must provide all corners before saving."));
                        return;
                    }

                    player.sendMessage(CC.translate("&a&lSuccess! &eBuilt schematic with name: \"" + SchematicLoaderAPI.INSTANCE.createNewSchematic(profile.getCurrentRegion()) +  "\""));
                    profile.delete();
                }

                if (event.getAction().equals(Action.LEFT_CLICK_BLOCK)) {
                    player.sendMessage(CC.translate("&a&lSuccess! &eSet corner 1 of the region."));
                    profile.getCurrentRegion().setFirstLocation(event.getClickedBlock().getLocation());
                    System.out.println(event.getClickedBlock().getLocation().toString());
                    return;
                }

                if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
                    player.sendMessage(CC.translate("&a&lSuccess! &eSet corner 2 of the region."));
                    profile.getCurrentRegion().setSecondLocation(event.getClickedBlock().getLocation());
                    System.out.println(event.getClickedBlock().getLocation().toString());
                }
            }
        }
    }

}
