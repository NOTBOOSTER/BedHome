package dev.blackmc.bedhome;


import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import dev.blackmc.bedhome.commands.HomeCommand;
import dev.blackmc.bedhome.commands.ReloadCommand;
import dev.blackmc.bedhome.listeners.PlayerMoveListener;

import java.util.Objects;

public class BedHome extends JavaPlugin {

    private TeleportManager teleportManager;

    @Override
    public void onEnable() {
        saveDefaultConfig();
        teleportManager = new TeleportManager(this);
        Objects.requireNonNull(getCommand("home")).setExecutor(new HomeCommand(this));
        Objects.requireNonNull(getCommand("bedhome")).setExecutor(new ReloadCommand(this));
        Objects.requireNonNull(getCommand("bh")).setExecutor(new ReloadCommand(this));
        getServer().getPluginManager().registerEvents(new PlayerMoveListener(this), this);
        getLogger().info(ChatColor.GOLD + "Thanks for using my plugin!");
        getLogger().info(ChatColor.GREEN + "You are using version " + ChatColor.AQUA + getDescription().getVersion());
        getLogger().info(ChatColor.BLUE + "You can check for the latest version on GitHub: " + ChatColor.YELLOW + "https://github.com/NOTBOOSTER/BedHome/releases");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info(ChatColor.RED + "Disabling BedHome plugin.");
        getLogger().info(ChatColor.GOLD + "Thanks for using my plugin!");
        getLogger().info(ChatColor.BLUE + "You can check for the latest version on GitHub: " + ChatColor.YELLOW + "https://github.com/NOTBOOSTER/BedHome/releases");
    }

    public TeleportManager getTeleportManager() {
        return teleportManager;
    }
}
