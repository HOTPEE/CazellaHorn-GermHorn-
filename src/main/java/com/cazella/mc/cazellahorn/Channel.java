package com.cazella.mc.cazellahorn;

import com.google.common.io.ByteArrayDataInput;
import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;
import org.bukkit.entity.Player;
import org.bukkit.plugin.messaging.PluginMessageListener;

public class Channel implements PluginMessageListener {
    @Override
    public void onPluginMessageReceived(String channel, Player player, byte[] message) {
        if (!channel.equals("CazellaHorn")){
            return;
        }
        ByteArrayDataInput in = ByteStreams.newDataInput(message);
        String hornMessage = in.readUTF();
        String playerName = in.readUTF();
        Util.states = true;
        Util.sendHornTexture(playerName, hornMessage);
    }

    public static void sendPacket(Player player, String s){
        ByteArrayDataOutput out = ByteStreams.newDataOutput();
        out.writeUTF(s);
        out.writeUTF(player.getName());
        player.sendPluginMessage(CazellaHorn.getIns(), "CazellaHorn", out.toByteArray());
    }
}
