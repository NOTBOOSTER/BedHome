package dev.blackmc.bedhome.commands;

import dev.blackmc.bedhome.BedHome;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.github.sirblobman.combatlogx.api.ICombatLogX;
import com.github.sirblobman.combatlogx.api.manager.ICombatManager;

public class HomeCommand implements CommandExecutor {

    private final BedHome plugin;

    public HomeCommand(BedHome plugin) {
        this.plugin = plugin;
    }

    private boolean isInCombat(Player player) {
        ICombatLogX combatLogX = (ICombatLogX) plugin.getServer().getPluginManager().getPlugin("CombatLogX");
        if (combatLogX == null) {
            return false;
        }
        ICombatManager combatManager = combatLogX.getCombatManager();
        return combatManager.isInCombat(player);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command.");
            return true;
        }

        Player player = (Player) sender;
        String prefix = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("prefix"));
        String noBedMessage = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("messages.no-bed"));
        int cooldown = plugin.getConfig().getInt("cooldown");

        if (args.length != 1 || !args[0].equalsIgnoreCase("bed")) {
            return false;
        }

        Location bedLocation = player.getBedSpawnLocation();
        if (bedLocation == null) {
            player.sendMessage(prefix + " " + noBedMessage);
            return true;
        }

        if (isInCombat(player)) {
            player.sendMessage(prefix + " " + ChatColor.RED + "You are in combat!");
            return true;
        }

        plugin.getTeleportManager().teleportWithCooldown(player, bedLocation, cooldown);
        return true;
    }
}
