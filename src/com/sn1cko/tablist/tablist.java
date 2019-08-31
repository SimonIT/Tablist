package com.sn1cko.tablist;

import com.sn1cko.tablist.methods.*;
import net.gravitydevelopment.updater.Updater;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Logger;

public class tablist extends JavaPlugin {
    public static final int id = 90510;
    public static Economy economy = null;
    public static Chat chat = null;
    private static tablist pl;
    public Logger logger = Logger.getLogger("Minecraft");

    public tablist() {
    }

    public static tablist getPlugin() {
        return pl;
    }

    @NotNull
    @Override
    public File getFile() {
        return super.getFile();
    }

    public void onEnable() {
        pl = this;
        if (!Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI")) {
            System.out.println(this.getDescription().getName() + "> " + vars.message_placeholderapierror);
        } else {
            vars.phapi_active = true;
            System.out.println(this.getDescription().getName() + "> " + vars.message_placeholderapisuccess);
        }

        this.startTPSTimer();
        theConfig.register(this);
        theEvents.register(this);
        theCommands.register(this);

        try {
            Class.forName("net.milkbowl.vault.economy.Economy");
            this.setupEconomy();
        } catch (ClassNotFoundException var3) {
            System.out.println(this.getDescription().getName() + "> " + vars.message_vaulterror + "Economy");
        }

        try {
            Class.forName("net.milkbowl.vault.chat.Chat");
            this.setupChat();
        } catch (ClassNotFoundException var2) {
            System.out.println(this.getDescription().getName() + "> " + vars.message_vaulterror + "Chat");
        }

        if (theConfig.getAnnouncerToggle(this)) {
            theSwitcher.loadMessages(this);
            theSwitcher.start(this);
        } else {
            theSwitcher.loadMessages(this);
        }

        if (vars.customPrefix) {
            theTablist.setCustomPrefixes();
        }

        PluginDescriptionFile pdf = this.getDescription();
        this.logger.info(pdf.getName() + "> " + pdf.getName() + " v" + pdf.getVersion() + " enabled!!");

        new Updater(this, tablist.id, getFile(), Updater.UpdateType.NO_DOWNLOAD, true);
    }

    public void onDisable() {
        PluginDescriptionFile pdf = this.getDescription();
        this.logger.info(pdf.getName() + "> " + pdf.getName() + " v" + pdf.getVersion() + " disabled!!");
    }

    public boolean setupEconomy() {
        RegisteredServiceProvider<Economy> economyProvider = this.getServer().getServicesManager().getRegistration(Economy.class);
        if (economyProvider != null) {
            economy = economyProvider.getProvider();
        }

        return economy != null;
    }

    public boolean setupChat() {
        RegisteredServiceProvider<Chat> chatProvider = this.getServer().getServicesManager().getRegistration(Chat.class);
        if (chatProvider != null) {
            chat = chatProvider.getProvider();
        }

        return chat != null;
    }

    public void startTPSTimer() {
        vars.tpsTask = Bukkit.getScheduler().runTaskTimer(this, () -> ++vars.tpsTicks, 1L, 1L);
        vars.tpsTimer = new Timer();
        vars.tpsTimer.schedule(new TimerTask() {
            public void run() {
                int size = 30;
                vars.tpsByteList.add(vars.tpsTicks);

                while (vars.tpsByteList.size() > size) {
                    vars.tpsByteList.remove(0);
                }

                vars.tpsTicks = 0;
            }
        }, 1000L, 1000L);
    }

    public void stopTPSTimer() {
        if (vars.tpsTask != null) {
            vars.tpsTask.cancel();
            vars.tpsTask = null;
        }

        if (vars.tpsTimer != null) {
            vars.tpsTimer.cancel();
            vars.tpsTimer = null;
        }

    }
}
