package dev.blackmc.bedhome;

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

    public void teleportWithCooldown(Player player, Location location, int cooldown) {
        if (cooldownTasks.containsKey(player.getUniqueId())) {
            player.sendMessage(plugin.getConfig().getString("prefix") + " " +
                    plugin.getConfig().getString("messages.cooldown"));
            return;
        }

        player.sendMessage(plugin.getConfig().getString("prefix") + " " +
                Objects.requireNonNull(plugin.getConfig().getString("messages.teleporting")).replace("<cooldown>", String.valueOf(cooldown)));

        BukkitRunnable task = new BukkitRunnable() {
            @Override
            public void run() {
                player.teleport(location);
                player.sendMessage(plugin.getConfig().getString("prefix") + " " +
                        plugin.getConfig().getString("messages.teleported"));
                cooldownTasks.remove(player.getUniqueId());
            }
        };

        cooldownTasks.put(player.getUniqueId(), task);
        task.runTaskLater(plugin, cooldown * 20L);
    }

    public void cancelTeleport(Player player) {
        if (cooldownTasks.containsKey(player.getUniqueId())) {
            cooldownTasks.get(player.getUniqueId()).cancel();
            cooldownTasks.remove(player.getUniqueId());
            player.sendMessage(plugin.getConfig().getString("prefix") + " " +
                    plugin.getConfig().getString("messages.teleport-cancelled"));
        }
    }
}
