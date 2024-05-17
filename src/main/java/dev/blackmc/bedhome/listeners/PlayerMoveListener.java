package dev.blackmc.bedhome.listeners;

import dev.blackmc.bedhome.BedHome;
import org.bukkit.entity.Player;
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
        Player player = event.getPlayer();
        if (plugin.getTeleportManager().isTeleporting(player)) {
            plugin.getTeleportManager().cancelTeleport(player);
        }
    }
}
