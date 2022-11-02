package me.color.Gui;

import me.color.Main;
import me.color.database.Database;
import me.color.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;

public class TopkaGui {
    public static Main plugin;

    public TopkaGui(Main pl) {
        plugin = pl;
    }

    public static Inventory inv;
    public static String inventory_name;
    public static int inv_rows = 3*9;

    public static void initialize() {
        inventory_name = Utils.chat("&8&lTopkaPVP...");

        inv = Bukkit.createInventory(null, inv_rows);
    }


    public static Inventory TopkaG(Player p) {
        Inventory toReturn = Bukkit.createInventory(null, inv_rows, inventory_name);


        for (int a = 1; a <= inv_rows; a++) {
            Utils.createItemByte(inv, 160, 15, 1, a, "&8&m--।--", "&8» &cPuste Pole!", "&8» &cWybierz inna pozycja!");
        }


        Database db = new Database();

        ArrayList<String> players = new ArrayList<String>();
        db.printDataArray("SELECT * FROM stats ORDER BY Points DESC", players);

        for(int i = 0; i < players.size(); i++){
            System.out.println(players.get(i));
        }

        Utils.createItem(inv, 276, 1, 11, "&cTopka PVP", false);
        int s = 12;
        for(int i = 0; i < 5; i++){
            s = s + 1;
            if(players.size() - 1 < i){
                Utils.addSkull(inv, s, "&c Top " + (i + 1), "No Data", false, "&8» &7Name: " + "No Data", "&8» &7Points: No Data");

                continue;
            }

            Utils.addSkull(inv, s, "&c Top " + (i + 1), players.get(i), false, "&8» &7Name: " + players.get(i), "&8» &7Points: " + db.printData("SELECT Points FROM stats WHERE PlayerName = '"+ players.get(i) + "';",1));

        }


        toReturn.setContents(inv.getContents());
        return toReturn;

    }

    public static void clicked(Player p, int slot, ItemStack clicked, Inventory inv) {


    }


}

