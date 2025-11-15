package com.Flire2;

import com.Flire2.Commands.ServerManagerCommand;
import com.Flire2.GUI.ServerManagerGUI;
import org.bukkit.plugin.java.JavaPlugin;

public final class ServerManager extends JavaPlugin {

    private static ServerManager instance;

    public static ServerManager getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;

        getCommand("sm").setExecutor(new ServerManagerCommand());
        getCommand("sm").setTabCompleter(new ServerManagerCommand());

        getServer().getPluginManager().registerEvents(new ServerManagerGUI(), this);
    }

    @Override
    public void onDisable() { instance = null; }
}
