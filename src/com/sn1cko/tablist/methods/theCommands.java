package com.sn1cko.tablist.methods;

import com.sn1cko.tablist.commands.cmd_tablist;
import com.sn1cko.tablist.tablist;

public class theCommands {
    public tablist plugin;

    public theCommands(tablist plugin) {
        this.plugin = plugin;
    }

    public static void register(tablist plugin) {
        plugin.getCommand("tablist").setExecutor(new cmd_tablist(plugin));
    }
}
