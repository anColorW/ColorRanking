package me.color.listeners;

import me.color.Gui.TopkaGui;
import me.color.Main;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ListenersGui implements Listener {

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        String title = e.getInventory().getTitle();
        if (title.equals(TopkaGui.inventory_name)) {
            e.setCancelled(true);
            if(e.getCurrentItem() == null) {
                return;
            }
        }
        if(title.equals(TopkaGui.inventory_name)){
            TopkaGui.clicked((Player) e.getWhoClicked(), e.getSlot(), e.getCurrentItem(), e.getInventory());
        }
    }
}


