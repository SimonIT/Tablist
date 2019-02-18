package com.sn1cko.tablist.events;

import com.sn1cko.tablist.methods.theTablist;
import com.sn1cko.tablist.tablist;
import com.sn1cko.tablist.vars;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class playerLeave implements Listener {
    public tablist plugin;

    public playerLeave(tablist plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onLeave(PlayerQuitEvent ev) {
        if (vars.customPrefix) {
            theTablist.setCustomPrefixes();
        }

    }
}
