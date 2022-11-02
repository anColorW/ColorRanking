package me.color.utils;

import com.avaje.ebean.validation.NotNull;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.NBTTagList;
import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static String chat (String s) {
        return ChatColor.translateAlternateColorCodes('&', s);
    }

    public static ItemStack addSkull(Inventory inv, int invSlot, String displayName, String playername, boolean glow, String... loreString) {


        List<String> lore = new ArrayList<String>();


        for (String s : loreString) {
            lore.add(Utils.chat(s));
        }

        org.bukkit.inventory.ItemStack item = new ItemStack(397, 1, (short) 3);
        SkullMeta sm = (SkullMeta) item.getItemMeta();
        sm.setOwner(playername);
        sm.setLore(lore);
        sm.setDisplayName(Utils.chat(displayName));
        item.setItemMeta(sm);


        if(glow) {
            item = addGlow(item);
        }

        inv.setItem(invSlot - 1, item);

        return item;
    }

    public static ItemStack createItem(Inventory inv, int materialId, int amount, int invSlot, String displayName, boolean glow, String... loreString) {

        ItemStack item;
        List<String> lore = new ArrayList<String>();


        item = new ItemStack(materialId, amount);


        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Utils.chat(displayName));
        for (String s : loreString) {
            lore.add(Utils.chat(s));
        }
        meta.setLore(lore);
        item.setItemMeta(meta);


        if(glow) {
            item = addGlow(item);
        }


        inv.setItem(invSlot - 1, item);

        return item;
    }

    public static ItemStack addGlow(ItemStack item){
        net.minecraft.server.v1_8_R3.ItemStack nmsStack = CraftItemStack.asNMSCopy(item);
        NBTTagCompound tag = null;
        if (!nmsStack.hasTag()) {
            tag = new NBTTagCompound();
            nmsStack.setTag(tag);
        }
        if (tag == null) tag = nmsStack.getTag();
        NBTTagList ench = new NBTTagList();
        tag.set("ench", ench);
        nmsStack.setTag(tag);
        return CraftItemStack.asCraftMirror(nmsStack);
    }

    @NotNull
    public static ItemStack createItemByte(Inventory inv, int materialId, int byteId,  int amount, int invSlot, String displayName, String... loreString) {

        ItemStack item;
        List<String> lore = new ArrayList<String>();

        item = new ItemStack(materialId, amount, (short) byteId);

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(Utils.chat(displayName));
        for (String s : loreString) {
            lore.add(Utils.chat(s));
        }
        meta.setLore(lore);
        item.setItemMeta(meta);
        inv.setItem(invSlot - 1, item);

        return item;
    }

    public static int CountItemInInventory(Player p, ItemStack block){
        int amount = 0;
        for (int i2 = 0; i2 < 36; i2++) {
            ItemStack slot = p.getInventory().getItem(i2);
            if (slot == null || !slot.isSimilar(block))
                continue;
            amount += slot.getAmount();
        }
        return amount;
    }

}
