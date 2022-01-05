package ru.lymonmine.lsrvbungeevkpost;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class fakepostcmd extends Command {
    public fakepostcmd() {
        super("fakepost");
    }

    @Override
    public void execute(CommandSender commandSender, String[] args) {
        ProxiedPlayer player = (ProxiedPlayer) commandSender;
        if (!player.hasPermission("lsrvbungeevkpost.fake")) {
            player.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', main.instance.config.getString("lang.noperm"))));
        } else {
            for (String s : main.instance.config.getStringList("alert-newpost")) {
                s = ChatColor.translateAlternateColorCodes('&', s);
                ProxyServer.getInstance().broadcast(s);


            }
        }
    }
}
