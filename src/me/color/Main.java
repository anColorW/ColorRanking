package me.color;

import me.color.Gui.TopkaGui;
import me.color.commands.SetPoints;
import me.color.commands.Topka;
import me.color.database.Database;
import me.color.listeners.Listeners;
import me.color.listeners.ListenersGui;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scoreboard.Scoreboard;

import java.util.ArrayList;
import java.util.List;

public class Main extends JavaPlugin {

    static Main instance;
    public Scoreboard sb;

    @Override
    public void onEnable() {
        sb = Bukkit.getScoreboardManager().getMainScoreboard();
        instance = this;
        Database db = new Database();



        getServer().getPluginManager().registerEvents(new Listeners(), this);
        getServer().getPluginManager().registerEvents(new ListenersGui(), this);
        new SetPoints(this);
        new Topka(this);
        TopkaGui.initialize();
    }

    public static Main getInstance(){
        return instance;
    }


}
