package com.sn1cko.tablist.methods;

import com.sn1cko.tablist.events.playerJoin;
import com.sn1cko.tablist.events.playerLeave;
import com.sn1cko.tablist.tablist;
import org.bukkit.Bukkit;

public class theEvents {
    public static void register(tablist plugin) {
        Bukkit.getPluginManager().registerEvents(new playerJoin(plugin), plugin);
        Bukkit.getPluginManager().registerEvents(new playerLeave(plugin), plugin);
    }
}
