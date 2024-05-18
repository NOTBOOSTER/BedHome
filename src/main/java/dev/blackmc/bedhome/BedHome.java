package dev.blackmc.bedhome;

import org.bukkit.ChatColor;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import dev.blackmc.bedhome.commands.HomeCommand;
import dev.blackmc.bedhome.commands.ReloadCommand;
import dev.blackmc.bedhome.listeners.PlayerMoveListener;

public class BedHome extends JavaPlugin {

    private TeleportManager teleportManager;

    @Override
    public void onEnable() {
        try {
            saveDefaultConfig();
            teleportManager = new TeleportManager(this);

            if (getCommand("home") != null) {
                getCommand("home").setExecutor(new HomeCommand(this));
            } else {
                getLogger().severe("Failed to register command: home");
            }

            if (getCommand("bedhome") != null) {
                getCommand("bedhome").setExecutor(new ReloadCommand(this));
            } else {
                getLogger().severe("Failed to register command: bedhome");
            }

            if (getCommand("bh") != null) {
                getCommand("bh").setExecutor(new ReloadCommand(this));
            } else {
                getLogger().severe("Failed to register command: bh");
            }

            PluginManager pluginManager = getServer().getPluginManager();
            if (pluginManager != null) {
                pluginManager.registerEvents(new PlayerMoveListener(this), this);
            } else {
                getLogger().severe("Failed to get PluginManager");
            }

            // Send a message to the console on enable
            getLogger().info( "Thanks for using my plugin!");
            getLogger().info("You are using version " + getDescription().getVersion());
            getLogger().info("You can check for the latest version on GitHub:   https://github.com/NOTBOOSTER/BedHome/releases");
        } catch (Exception e) {
            getLogger().severe("An error occurred while enabling the plugin: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public void onDisable() {
        // Send a message to the console on disable
        getLogger().info("Disabling BedHome plugin.");
        getLogger().info("Thanks for using my plugin!");
        getLogger().info("You can check for the latest version on GitHub: https://github.com/NOTBOOSTER/BedHome/releases");
    }

    public TeleportManager getTeleportManager() {
        return teleportManager;
    }
}
