package me.color.commands;

import me.color.Gui.TopkaGui;
import me.color.Main;
import me.color.database.Database;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

public class Topka implements CommandExecutor {

    public Topka(Main pl) {
        pl.getCommand("topka").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        Player p = (Player) sender;
        p.openInventory(TopkaGui.TopkaG(p));

        org.bukkit.inventory.ItemStack item = new ItemStack(397, 1, (short) 3);
        SkullMeta sm = (SkullMeta) item.getItemMeta();
        sm.setOwner(sender.getName());
        item.setItemMeta(sm);

        p.getInventory().addItem(item);

        return false;
    }
}
