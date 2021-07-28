package com.cazella.mc.cazellahorn;

import com.germ.germplugin.api.dynamic.gui.GermGuiScreen;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;


public class Commands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof ConsoleCommandSender){

            sender.sendMessage("§c控制台严禁使用");
            return false;

        }

        Player player = (Player) sender;

        if (!player.hasPermission("CazellaHorn.horn")){

            Util.Message(player, "" + CazellaHorn.getIns().getConfig().getString("NoPermission-Message"));
            return false;

        }
        if (args.length < 1){

            Util.Help(player);
            return false;

        }
        if (args[0].equalsIgnoreCase("Reload")){
            if (!player.hasPermission("CazellaHorn.admin")){
                Util.Message(player, "" + CazellaHorn.getIns().getConfig().getString("NoPermission-Message"));
                return false;
            }
            Util.Message(player, "" + CazellaHorn.getIns().getConfig().getString("Reload-Message"));
            CazellaHorn.getIns().reloadConfig();
        }
        if (args[0].equalsIgnoreCase("Send")){
            if (!HornItem.isItem(HornItem.getItem(player))) {
                Util.Message(player, "" + CazellaHorn.getIns().getConfig().getString("NoItem-Message"));
                return false;
            }
            GermGuiScreen germGuiScreen = GermGuiScreen.getGermGuiScreen("CheckTexture", YamlConfiguration.loadConfiguration(Util.getFile("CheckTexture")));
            germGuiScreen.setGuiName("CheckTexture");
            germGuiScreen.openGui(player);
        }
        return false;
    }
}
