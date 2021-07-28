package com.cazella.mc.cazellahorn.germgui;

import com.cazella.mc.cazellahorn.CazellaHorn;
import com.cazella.mc.cazellahorn.Channel;
import com.cazella.mc.cazellahorn.HornItem;
import com.cazella.mc.cazellahorn.Util;
import com.germ.germplugin.api.dynamic.gui.GermGuiScreen;
import com.germ.germplugin.api.dynamic.gui.GuiManager;
import com.germ.germplugin.api.dynamic.gui.part.GermGuiButton;
import com.germ.germplugin.api.dynamic.gui.part.GermGuiInput;
import com.germ.germplugin.api.event.gui.GermGuiButtonEvent;
import com.germ.germplugin.api.event.gui.GermGuiClosedEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.List;
import java.util.Objects;

public class GermListener implements Listener {

    @EventHandler
    public void GuiClose(GermGuiClosedEvent e){
        if (!e.getGermGuiScreen().isOpened()){
            return;
        }
        if (e.getGermGuiScreen().getGuiName().equals("CheckTexture")){
            e.getGermGuiScreen().close();
        }
    }

    @EventHandler
    public void GermButton(GermGuiButtonEvent e){
        if (!e.getGermGuiButton().isOpened()){
            return;
        }
        if (e.getGermGuiScreen().getGuiName().equals("CheckTexture")){
            if (e.getEventType() == GermGuiButton.EventType.LEFT_CLICK){
                String button = e.getGermGuiButton().getIndexName();
                if (button.equals("ConfirmButton")){
                    if (Util.states){
                        Util.Message(e.getPlayer(), CazellaHorn.getIns().getConfig().getString("isHorn-Message"));
                        e.getGermGuiScreen().close();
                        return;
                    }
                    GermGuiScreen germ = GuiManager.getOpenedGui(e.getPlayer(),"CheckTexture");
                    GermGuiInput germGuiInput = (GermGuiInput) Objects.requireNonNull(germ).getGuiPart("Input");
                    for (String msg : CazellaHorn.getIns().getConfig().getStringList("BanString")) {
                        if (germGuiInput.getInput().contains(msg)) {
                            Util.Message(e.getPlayer(), CazellaHorn.getIns().getConfig().getString("BanString-Message"));
                            return;
                        }
                    }
                    HornItem.removeOne(e.getPlayer(), HornItem.getItem(e.getPlayer()));
                    if (CazellaHorn.getIns().getConfig().getBoolean("BungeeCord")){
                        Channel.sendPacket(e.getPlayer(), germGuiInput.getInput());
                    } else {
                        Util.sendHornTexture(e.getPlayer().getName(), germGuiInput.getInput());
                    }
                    Util.Message(e.getPlayer(), "&b发送成功，你已发送" + germGuiInput.getInput());
                    e.getGermGuiScreen().close();

                }
            }
        }
    }
}
