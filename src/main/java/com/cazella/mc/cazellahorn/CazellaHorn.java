package com.cazella.mc.cazellahorn;

import com.cazella.mc.cazellahorn.germgui.GermListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class CazellaHorn extends JavaPlugin {
    public static CazellaHorn getIns() {
        return ins;
    }

    private static CazellaHorn ins;

    @Override
    public void onEnable() {
        ins = this;
        if (Bukkit.getPluginManager().getPlugin("GermPlugin") == null){
            getLogger().info("未检测到萌芽引擎，本插件已关闭!");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }
        saveDefaultConfig();
        Util.states = false;
        if (!Util.getFile("CheckTexture").exists()){
            getLogger().info("未检测到CheckTexture文件,已自动生成");
            saveResource("CheckTexture.yml", false);
        }
        if (!Util.getFile("HornTexture").exists()){
            getLogger().info("未检测到HornTexture文件,已自动生成");
            saveResource("HornTexture.yml", false);
        }
        Bukkit.getPluginCommand("CazellaHorn").setExecutor(new Commands());
        Bukkit.getPluginManager().registerEvents(new GermListener(), this);
        Bukkit.getServer().getMessenger().registerIncomingPluginChannel(this,"CazellaHorn", new Channel());
        Bukkit.getServer().getMessenger().registerOutgoingPluginChannel(this,"CazellaHorn");
    }

    @Override
    public void onDisable() {
        this.getServer().getMessenger().unregisterOutgoingPluginChannel(this);
        this.getServer().getMessenger().unregisterIncomingPluginChannel(this);
    }
}
