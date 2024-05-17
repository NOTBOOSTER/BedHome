package dev.blackmc.bedhome;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public class TeleportManager {

    private final BedHome plugin;
    private final HashMap<UUID, BukkitRunnable> cooldownTasks = new HashMap<>();

    public TeleportManager(BedHome plugin) {
        this.plugin = plugin;
    }

    public boolean isTeleporting(Player player) {
        return cooldownTasks.containsKey(player.getUniqueId());
    }

    public void teleportWithCooldown(Player player, Location location, int cooldown) {
        String prefix = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("prefix")));
        String teleportingMessage = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("messages.teleporting"))).replace("<cooldown>", String.valueOf(cooldown));
        String teleportedMessage = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("messages.teleported")));
        String teleportCancelledMessage = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("messages.teleport-cancelled")));

        if (isTeleporting(player)) {
            player.sendMessage(prefix + " " + ChatColor.RED + "You are already teleporting!");
            return;
        }

        player.sendMessage(prefix + " " + teleportingMessage);

        BukkitRunnable task = new BukkitRunnable() {
            @Override
            public void run() {
                player.teleport(location);
                player.sendMessage(prefix + " " + teleportedMessage);
                cooldownTasks.remove(player.getUniqueId());
            }
        };

        cooldownTasks.put(player.getUniqueId(), task);
        task.runTaskLater(plugin, cooldown * 20L);
    }

    public void cancelTeleport(Player player) {
        String prefix = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("prefix")));
        String teleportCancelledMessage = ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(plugin.getConfig().getString("messages.teleport-cancelled")));

        if (isTeleporting(player)) {
            cooldownTasks.get(player.getUniqueId()).cancel();
            cooldownTasks.remove(player.getUniqueId());
            player.sendMessage(prefix + " " + teleportCancelledMessage);
        }
    }
}
