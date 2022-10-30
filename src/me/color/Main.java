package me.color;

import me.color.commands.SetPoints;
import me.color.database.Database;
import me.color.listeners.Listeners;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    static Main instance;

    @Override
    public void onEnable() {
        instance = this;
        Database db = new Database();
        getServer().getPluginManager().registerEvents(new Listeners(), this);
        getCommand("setpoints").setExecutor(new SetPoints());
    }

    public static Main getInstance(){
        return instance;
    }


}
