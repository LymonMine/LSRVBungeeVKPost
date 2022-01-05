package ru.lymonmine.lsrvbungeevkpost;

import java.io.File;
import java.io.IOException;
import java.nio.file.CopyOption;
import java.nio.file.Files;

import com.vk.api.sdk.client.TransportClient;
import com.vk.api.sdk.client.VkApiClient;
import com.vk.api.sdk.client.actors.GroupActor;
import com.vk.api.sdk.exceptions.ApiException;
import com.vk.api.sdk.exceptions.ClientException;
import com.vk.api.sdk.httpclient.HttpTransportClient;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

public final class main extends Plugin {
    public Configuration config;
    public File f;
    public static main instance;


    @Override
    public void onEnable() {
        //конфиг
        this.getDataFolder().mkdirs();
        this.f = new File(this.getDataFolder(), "config.yml");
        if (!this.f.exists()) {
            try {
                Files.copy(this.getResourceAsStream("config.yml"), this.f.toPath(), new CopyOption[0]);
            } catch (Exception ex) {
            }
        }
        try {
            this.config = ConfigurationProvider.getProvider((Class) YamlConfiguration.class).load(this.f);
        } catch (IOException e) {
            e.printStackTrace();
        }
        //конфиг

        //команды
        getProxy().getPluginManager().registerCommand(this, new latpostcmd());
        getProxy().getPluginManager().registerCommand(this, new fakepostcmd());
        //команды
        //vkapi
        TransportClient transportClient = new HttpTransportClient();
        VkApiClient vk = new VkApiClient(transportClient);
        GroupActor actor = new GroupActor(config.getInt("vk-settings.groupid"), config.getString("vk-settings.groupToken"));
        vk.groups().setLongPollSettings(actor, config.getInt("vk-settings.groupid")).enabled(true).wallPostNew(true);
        longpollevent handler = new longpollevent(vk, actor);
        //vkapi

        instance = this;
        //vkapi
        getProxy().getScheduler().runAsync(main.instance, new Runnable() {
            @Override
            public void run() {
                try {
                    handler.run();
                } catch (ClientException e) {
                    e.printStackTrace();
                } catch (ApiException e) {
                    e.printStackTrace();
                }
                //vkapi

            }
        });
        {

        }
    }
}





