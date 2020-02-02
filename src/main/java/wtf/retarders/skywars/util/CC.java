package wtf.retarders.skywars.util;

import org.bukkit.ChatColor;

public enum CC {

    INSTANCE;

    public static String translate(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }

}
