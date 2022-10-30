package me.color.listeners;

import me.color.database.Database;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;

import java.sql.SQLException;

public class Listeners implements Listener {

    private Database db = new Database();

    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        try {
            if(!db.exist("SELECT * FROM stats WHERE PlayerName = '"+ e.getPlayer().getName() +"';")){
                db.insertQuery("INSERT INTO stats (PlayerName, Points) VALUES ('"+ e.getPlayer().getName() +"', 500);");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @EventHandler
    public void onKill(PlayerDeathEvent e) {
        String killed = e.getEntity().getName();
        String killer = e.getEntity().getKiller().getName();
        int pointsKilled = Integer.parseInt(db.printData("SELECT Points FROM stats WHERE PlayerName = '"+ killed +"'"));
        int pointsKiller = Integer.parseInt(db.printData("SELECT Points FROM stats WHERE PlayerName = '"+ killer +"'"));
        int difference;

        if(pointsKilled - pointsKiller < 0){
            difference = (pointsKilled - pointsKiller) * -1;
            int cas = (int) calculatePercentage( 2, difference);
        }


        if(pointsKilled > 600){

        }

    }

    public static float calculatePercentage(float percent, float fromNumber) {
        return (float) (percent / 100) * fromNumber;
    }



}
