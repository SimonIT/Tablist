package com.sn1cko.tablist.commands;

import com.sn1cko.tablist.methods.theSwitcher;
import com.sn1cko.tablist.tablist;
import com.sn1cko.tablist.vars;
import net.gravitydevelopment.updater.Updater;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class cmd_tablist implements CommandExecutor {
    public tablist plugin;

    public cmd_tablist(tablist plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        String cmdName = "tablist";
        String prefix = vars.message_prefix;
        String errormsg = prefix + vars.message_error;
        String permmsg = prefix + vars.message_nopermissions;
        String helpmsg = vars.message_help;
        if (cmd.getName().equalsIgnoreCase(cmdName)) {
            if (args.length == 0) {
                sender.sendMessage(helpmsg);
            } else if (args.length == 1) {
                if (args[0].equalsIgnoreCase("reload")) {
                    if (sender.hasPermission(vars.permission_reload)) {
                        this.plugin.reloadConfig();
                        theSwitcher.stop();
                        theSwitcher.start(this.plugin);
                        sender.sendMessage(prefix + vars.message_reload);
                        return true;
                    } else {
                        sender.sendMessage(permmsg);
                    }
                } else if (args[0].equalsIgnoreCase("help")) {
                    sender.sendMessage(helpmsg);
                    return true;
                } else if (args[0].equalsIgnoreCase("update")) {
                    new Updater(plugin, tablist.id, plugin.getFile(), Updater.UpdateType.DEFAULT, true);
                    return true;
                } else {
                    sender.sendMessage(errormsg);
                }
            } else {
                sender.sendMessage(errormsg);
            }
        }

        return false;
    }
}
