package eu.lyera.bungee.command;

import eu.lyera.bungee.BungeeSystem;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.chat.hover.content.Text;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

@SuppressWarnings( "deprecation" )
public class Command_Find extends Command {

    public Command_Find(String name) {
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
            player.sendMessage(BungeeSystem.getInstance().getPrefix() + "§cUse: /find <Player>");
            return;
        }

        ProxiedPlayer target = ProxyServer.getInstance().getPlayer(args[0]);
        if(target == null) {
           player.sendMessage(BungeeSystem.getInstance().getPrefix() + "§cThat Player is not Online!");
            return;
        }
        if(target.getUniqueId().equals(player.getUniqueId())) {
            player.sendMessage(BungeeSystem.getInstance().getPrefix() + "§cYou cant find yourself");
            return;
        }

        ServerInfo serverInfo = target.getServer().getInfo();

        player.sendMessage("§6" + target.getName() + " §7is on §a" + serverInfo.getName());
        TextComponent component = new TextComponent("§a§l[CLICK] §cTo Jump to " + target.getName());
        component.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/jumpto " + target.getName()));
        component.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new Text("§7Jump to §e" + target.getName())));
        player.sendMessage(component);
    }
}
