package eu.lyera.bungee.command;

import eu.lyera.bungee.BungeeSystem;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

@SuppressWarnings( "deprecation" )
public class Command_GlobalChatClear extends Command {


    public Command_GlobalChatClear(String name) {
        super(name);
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        if(!sender.hasPermission("bungee.gcc.use")) {
            sender.sendMessage(BungeeSystem.getInstance().getPrefix());
            return;
        }
        for(ProxiedPlayer all : ProxyServer.getInstance().getPlayers()) {
            if(!all.hasPermission("bungee.gcc.bypass")) {
                for (int i = 0; i < 100; i++) {
                    all.sendMessage("");
                }
                all.sendMessage("§4§lThe Chat got cleared.");
            } else {
                all.sendMessage("§4§lThe Chat got cleared but your immune to it!");
            }
        }
    }
}
