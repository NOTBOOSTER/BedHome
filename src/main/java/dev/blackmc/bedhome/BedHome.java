package dev.blackmc.bedhome;

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
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public TeleportManager getTeleportManager() {
        return teleportManager;
    }
}
