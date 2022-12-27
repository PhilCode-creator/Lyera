package eu.lyera.bungee;

import eu.lyera.bungee.command.Command_Find;
import eu.lyera.bungee.command.Command_JumpTo;
import eu.lyera.bungee.report.Report;
import eu.lyera.bungee.teamchat.Command_TeamChat;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BungeeSystem extends Plugin {

    private String prefix = "§8[§eLyera§8] §r";
    private String noPerms = prefix + "§cNot enough Permissions";
    private String noPlayer = prefix + "§cThere for you need to be a Player!";

    private List<Report> reports;

    private static BungeeSystem instance;

    public String getPrefix() {
        return prefix;
    }

    public static BungeeSystem getInstance() {
        return instance;
    }

    public String getNoPerms() {
        return noPerms;
    }

    public String getNoPlayer() {
        return noPlayer;
    }

    @Override
    public void onEnable() {
        instance = this;
        reports = new ArrayList<>();
        PluginManager pluginManager = ProxyServer.getInstance().getPluginManager();
        pluginManager.registerCommand(this, new Command_Find("find"));
        pluginManager.registerCommand(this, new Command_JumpTo("jumpto"));
        pluginManager.registerCommand(this, new Command_TeamChat("teamchat"));
    }

    @Override
    public void onDisable() {

    }
}
