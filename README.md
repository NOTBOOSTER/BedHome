# BedHome Plugin

BedHome is a simple yet powerful Minecraft plugin that allows players to teleport to their bed by using the `/home bed` command. The plugin also supports configurable messages, color codes, cooldowns, and integrates with the CombatLogX plugin to prevent teleportation while in combat.

## Features

- Teleport to your bed with `/home bed`.
- Configurable messages and prefix with color code support.
- Cooldown feature to prevent instant teleportation.
- Integration with CombatLogX to prevent teleportation during combat.
- Commands to reload the configuration.

## Commands and Permissions

### `/home bed`

- **Description**: Teleports the player to their bed.
- **Permission**: `bedhome.bed`
- **Default**: true

### `/bedhome reload` or `/bh reload`

- **Description**: Reloads the plugin configuration.
- **Permission**: `bedhome.admin`
- **Default**: op

## Configuration

The `config.yml` file allows you to customize the plugin messages, prefix, and cooldown.

```yaml
prefix: '&c&l BedHome &7> &f'
messages:
  no-bed: 'You don''t have a bed set!'
  teleporting: 'Teleporting in <cooldown> seconds...'
  teleported: 'You have been teleported to your bed.'
  teleport-cancelled: 'Teleportation cancelled due to movement.'
  cooldown: 'You must wait before using this command again!'
cooldown: 3 
```

- `prefix`: The prefix for all messages sent by the plugin.
- `messages.no-bed`: Message sent when the player does not have a bed set.
- `messages.teleporting`: Message sent when the teleportation is about to start. `<cooldown>` is replaced with the actual cooldown time.
- `messages.teleported`: Message sent when the player has been teleported.
- `messages.teleport-cancelled`: Message sent when the teleportation is cancelled due to player movement.
- `cooldown`: The cooldown time in seconds. Set to `0` for no cooldown.

## Installation

1. Download the plugin jar file.
2. Place the jar file in your server's `plugins` directory.
3. Restart the server to generate the default configuration files.
4. Customize the `config.yml` file to your liking.
5. Reload or restart the server to apply the changes.

## Integration with CombatLogX

BedHome integrates with the CombatLogX plugin to prevent players from teleporting while in combat. Ensure that CombatLogX is installed and enabled on your server for this feature to work.

## Development

### Build and Compile

To build and compile the plugin, you need to have [Maven](https://maven.apache.org/) installed.

1. Clone the repository.
2. Navigate to the project directory.
3. Run `mvn clean package` to build the plugin jar file.

### Contributing

Contributions are welcome! Please fork the repository and submit pull requests with your improvements.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.

## Acknowledgements

- [CombatLogX](https://www.spigotmc.org/resources/combatlogx.31689/) for combat logging integration.
- The Minecraft and Bukkit communities for their support and resources.

## Contact

For support, questions, or suggestions, please open an issue on the [GitHub repository](https://github.com/NOTBOOSTER/BedHome/issues).
