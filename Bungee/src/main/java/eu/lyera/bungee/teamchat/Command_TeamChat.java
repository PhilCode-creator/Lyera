package eu.lyera.bungee.teamchat;

import eu.lyera.bungee.BungeeSystem;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

@SuppressWarnings( "deprecation" )
public class Command_TeamChat extends Command {

    public Command_TeamChat(String name) {
        super(name);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(args.length < 1) {
            sender.sendMessage(BungeeSystem.getInstance().getPrefix() + "§cUse: /teamchat [message]");
            return;
        }

        String msg = "";
        for (int i = 0; i < args.length; i++) {
            msg = msg + " " + args[i];
        }

        for(ProxiedPlayer all : ProxyServer.getInstance().getPlayers()) {
            if(all.hasPermission("teamchat.use")) {
                all.sendMessage("§cTeamChat §e>> §7(" + sender.getName() + "): §b" + msg);
            }
        }
    }
}
