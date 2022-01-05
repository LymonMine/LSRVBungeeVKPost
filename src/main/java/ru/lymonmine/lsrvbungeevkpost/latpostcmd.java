package ru.lymonmine.lsrvbungeevkpost;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class latpostcmd extends Command {
    public latpostcmd() {
        super("latpost");
    }

    @Override
    public void execute(CommandSender commandSender, String[] args) {
        ProxiedPlayer player = (ProxiedPlayer) commandSender;
        if (!player.hasPermission("lsrvbungeevkpost.lat")) {
            player.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', main.instance.config.getString("lang.noperm"))));
        } else {
            if (longpollevent.url == null) {
                player.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', main.instance.config.getString("lang.last-post.nolast"))));
            } else {
                player.sendMessage(new TextComponent(ChatColor.translateAlternateColorCodes('&', main.instance.config.getString("lang.last-post.last"))));
                TextComponent message = new TextComponent(ChatColor.BLUE + "ПОСЛЕДНИЙ ПОСТ");
                message.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, longpollevent.url));
                message.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(ChatColor.translateAlternateColorCodes('&', main.instance.config.getString("lang.last-post.mouse"))).create()));
                player.sendMessage(message);


            }
        }
    }
}

