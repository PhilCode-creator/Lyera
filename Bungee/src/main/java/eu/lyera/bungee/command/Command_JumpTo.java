package eu.lyera.bungee.command;

import eu.lyera.bungee.BungeeSystem;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class Command_JumpTo extends Command {
    public Command_JumpTo(String name) {
        super(name);
    }

    public void execute(CommandSender sender, String[] args) {
        if(!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage(BungeeSystem.getInstance().getNoPlayer());
            return;
        }
        ProxiedPlayer player = (ProxiedPlayer) sender;
        if(!player.hasPermission("bungee.find")) {
            player.sendMessage(BungeeSystem.getInstance().getNoPerms());
            return;
        }

        if(args.length != 1) {
            player.sendMessage(BungeeSystem.getInstance().getPrefix() + "§cUse: /jumpto <Player>");
            return;
        }

        ProxiedPlayer target = ProxyServer.getInstance().getPlayer(args[0]);
        if(target == null) {
            player.sendMessage(BungeeSystem.getInstance().getPrefix() + "§cThat Player is not Online!");
            return;
        }
        if(target.getUniqueId().equals(player.getUniqueId())) {
            player.sendMessage(BungeeSystem.getInstance().getPrefix() + "§cYou cant jumpto yourself");
            return;
        }

        player.connect(target.getServer().getInfo());
        player.sendMessage(BungeeSystem.getInstance().getPrefix() + "§aJumping to " + target.getName());
    }
}