package dev.blackmc.bedhome.listeners;

import dev.blackmc.bedhome.BedHome;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class PlayerMoveListener implements Listener {

    private final BedHome plugin;

    public PlayerMoveListener(BedHome plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        plugin.getTeleportManager().cancelTeleport(event.getPlayer());
    }
}
