package ru.lymonmine.lsrvbungeevkpost;


import com.vk.api.sdk.callback.longpoll.CallbackApiLongPoll;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.objects.wall.Wallpost;
import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.ProxyServer;


public class longpollevent extends CallbackApiLongPoll {
    public static String url;

    public longpollevent(VkApiClient client, GroupActor actor) {
        super(client, actor);
    }


    @Override
    public void wallPostNew(Integer groupId, Wallpost wallPost) {
        url = "https://vk.com/club" + main.instance.config.getInt("vk-settings.groupid") + "?w=wall-" + main.instance.config.getInt("vk-settings.groupid")
                + "_" + wallPost.getId();
        for (String s : main.instance.config.getStringList("alert-newpost")) {
            s = ChatColor.translateAlternateColorCodes('&', s);
            ProxyServer.getInstance().broadcast(s);
        }
    }
}



