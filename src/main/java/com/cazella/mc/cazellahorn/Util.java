package com.cazella.mc.cazellahorn;

import com.germ.germplugin.api.GermHudAPI;
import com.germ.germplugin.api.dynamic.gui.GermGuiScreen;
import com.germ.germplugin.api.dynamic.gui.part.GermGuiLabel;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;

public class Util {
    public static Boolean states;
    public static void Message(Player player, String string){
        player.sendMessage(CazellaHorn.getIns().getConfig().getString("Prefix").replaceAll("&","§")
                + string.replaceAll("&","§"));
    }

    public static void Help(Player player){
        for (String s : CazellaHorn.getIns().getConfig().getStringList("Commands-Message")) {
            player.sendMessage(s.replaceAll("&","§"));
        }
    }

    public static File getFile(String s){
        return new File(CazellaHorn.getIns().getDataFolder(), s + ".yml");
    }

    public static void sendHornTexture(String playerName, String s){
        GermGuiScreen hornTexture = GermGuiScreen.getGermGuiScreen("HornTexture", YamlConfiguration.loadConfiguration(Util.getFile("HornTexture")));
        GermGuiLabel germGuiLabel = (GermGuiLabel) hornTexture.getGuiPart("Text");
        germGuiLabel.setText(playerName + ": " + s);
        GermHudAPI.registerHUD(hornTexture);
        GermHudAPI.sendRegisteredHUDToAllPlayer();
        Util.states = true;
        Task task = new Task(hornTexture);
        task.runTaskLater(CazellaHorn.getIns(), CazellaHorn.getIns().getConfig().getInt("HornTask") * 20L);
    }
}
