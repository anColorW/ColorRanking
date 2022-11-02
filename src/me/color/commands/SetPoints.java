package me.color.commands;

import me.color.Main;
import me.color.database.Database;
import me.color.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetPoints implements CommandExecutor {

    public SetPoints(Main pl) {
        pl.getCommand("setpoints").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {


        if(args[1] == null || args[0] == null)
            return true;


        Player targetplayer = Bukkit.getPlayer(args[0]);
        Database db = new Database();
        Player p = (Player) sender;


        if(targetplayer == null){
            try{
                int num = Integer.parseInt(args[0]);
                db.insertQuery("UPDATE stats SET Points = "+ num +" WHERE PlayerName = '"+ sender.getName() +"';");
                p.setDisplayName(ChatColor.YELLOW + p.getDisplayName());
            } catch (NumberFormatException e) {
                return true;
            }
            return true;
        }

        try{
            int num = Integer.parseInt(args[1]);
            db.insertQuery("UPDATE stats SET Points = "+ num +" WHERE PlayerName = '"+ targetplayer.getName() +"';");
        } catch (NumberFormatException e) {
           return true;
        }



        return false;
    }
}
