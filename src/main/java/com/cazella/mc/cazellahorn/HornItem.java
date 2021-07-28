package com.cazella.mc.cazellahorn;

import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;
import java.util.List;

public class HornItem {
    public static HashMap<Player, ItemStack> stack2 = new HashMap<>();
    public static boolean isItem(ItemStack item){
        if (item == null){
            return false;
        }
        ItemMeta meta = item.getItemMeta();
        if (meta == null){
            return false;
        }
        List<String> stringList = meta.getLore();
        if (stringList.contains(CazellaHorn.getIns().getConfig().getString("MatchLore").replaceAll("&", "§"))){
            return true;
        }
        return false;
    }

    public static ItemStack getItem(Player player){
        stack2.put(player, player.getInventory().getItemInMainHand());
        return stack2.get(player);
    }

    public static void removeOne(Player player, ItemStack stack){
        int amount = player.getInventory().getItemInMainHand().getAmount();
        int amount2 = amount - 1;
        if (amount <= 1){
            player.getInventory().getItemInMainHand().setAmount(0);
            return;
        }
        player.getInventory().getItemInMainHand().setAmount(amount2);
    }

    public static void addOne(Player player){
        int amount = player.getInventory().getItemInMainHand().getAmount();
        int amount2 = amount + 1;
        stack2.get(player).setAmount(amount2);
    }
}
