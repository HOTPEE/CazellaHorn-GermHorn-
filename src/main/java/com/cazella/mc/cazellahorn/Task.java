package com.cazella.mc.cazellahorn;

import com.germ.germplugin.api.GermHudAPI;
import com.germ.germplugin.api.dynamic.gui.GermGuiScreen;
import org.bukkit.scheduler.BukkitRunnable;

public class Task extends BukkitRunnable {

    private GermGuiScreen germGuiScreen;
    public Task(GermGuiScreen germGuiScreen){
        this.germGuiScreen = germGuiScreen;
    }
    @Override
    public void run() {
        GermHudAPI.removeHUD(germGuiScreen);
        Util.states = false;
        cancel();
    }

}
