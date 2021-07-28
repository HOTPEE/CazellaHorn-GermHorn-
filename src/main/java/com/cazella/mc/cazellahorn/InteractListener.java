package com.cazella.mc.cazellahorn;

import com.germ.germplugin.api.dynamic.gui.GermGuiScreen;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

public class InteractListener implements Listener {

    @EventHandler
    public void interact(PlayerInteractEvent e){
        if (!e.getPlayer().hasPermission("CazellaHorn.horn")){
            return;
        }
        if (!e.getItem().getItemMeta().getLore().contains(CazellaHorn.getIns().getConfig().getString("MatchLore").replaceAll("&", "§"))) {
            return;
        }
        if (e.getAction() == Action.RIGHT_CLICK_AIR && e.getAction() == Action.RIGHT_CLICK_BLOCK){
            GermGuiScreen germGuiScreen = GermGuiScreen.getGermGuiScreen("CheckTexture", YamlConfiguration.loadConfiguration(Util.getFile("CheckTexture")));
            germGuiScreen.setGuiName("CheckTexture");
            germGuiScreen.openGui(e.getPlayer());
        }
    }
}
