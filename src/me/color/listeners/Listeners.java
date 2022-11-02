package me.color.listeners;

import me.color.Main;
import me.color.database.Database;
import me.color.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;
import org.bukkit.scoreboard.Team;

import java.sql.SQLException;
import java.util.Random;

public class Listeners implements Listener {

    private Database db = new Database();

    @EventHandler
    public void join(PlayerJoinEvent e){
        Player p = e.getPlayer();
        int playerpoints = Integer.parseInt(db.printData("SELECT Points FROM stats WHERE PlayerName = '"+ e.getPlayer().getName() +"'", 1));
        if(Main.getInstance().sb.getTeam(e.getPlayer().getName()) != null){
            Main.getInstance().sb.getTeam(e.getPlayer().getName()).setPrefix(Utils.chat("&7["+ playerpoints +"] &f"));
            return;
        }

        Team t = Main.getInstance().sb.registerNewTeam(e.getPlayer().getName());
        t.setPrefix("["+ playerpoints +"] ");
        t.addPlayer(e.getPlayer()); //dziala
    }


    @EventHandler
    public void onJoin(PlayerJoinEvent e){
     if(!db.exist("SELECT * FROM stats WHERE PlayerName = '"+ e.getPlayer().getName() +"';")){
            db.insertQuery("INSERT INTO stats (PlayerName, Points) VALUES ('" + e.getPlayer().getName() + "', 500);");
        }
    }

    @EventHandler
    public void onKill(PlayerDeathEvent e) {
        String killed = e.getEntity().getName();
        String killer = e.getEntity().getKiller().getName();
        int pointsKilled = Integer.parseInt(db.printData("SELECT Points FROM stats WHERE PlayerName = '"+ killed +"'", 1));
        int pointsKiller = Integer.parseInt(db.printData("SELECT Points FROM stats WHERE PlayerName = '"+ killer +"'", 1));
        int difference = pointsKilled - pointsKiller;
        Random random = new Random();


        if(pointsKilled > 600){
            if(difference > 0){
                pointsKilled = (int) (pointsKilled - calculatePercentage(5, difference));
                pointsKiller = (int) (pointsKiller + calculatePercentage(5, difference));
            } else{
                pointsKilled = (int) (pointsKilled - calculatePercentage(2, difference));
                pointsKiller = (int) (pointsKiller + calculatePercentage(2, difference));
            }
        } else{
            if(pointsKilled > 500){
                if(difference > 0){
                    pointsKilled = pointsKilled - 10;
                    pointsKiller = pointsKiller + 10;
                } else{
                    pointsKilled = pointsKilled - 5;
                    pointsKiller = pointsKiller + 5;
                }
            } else{
                if(difference > 0){
                    int rand = random.nextInt(10 - 1) + 1;
                    pointsKilled = pointsKilled - rand;
                    pointsKiller = pointsKiller + rand;
                } else{
                    int rand = random.nextInt(5 - 1) + 1;
                    pointsKilled = pointsKilled - rand;
                    pointsKiller = pointsKiller + rand;
                }
            }
        }
//

        db.insertQuery("UPDATE stats SET Points = "+ pointsKilled +" WHERE PlayerName = '"+ killed +"';");
        db.insertQuery("UPDATE stats SET Points = "+ pointsKiller +" WHERE PlayerName = '"+ killer +"';");
        int p1 = Integer.parseInt(db.printData("SELECT Points FROM stats WHERE PlayerName = '"+ killed +"'", 1));
        int p2 = Integer.parseInt(db.printData("SELECT Points FROM stats WHERE PlayerName = '"+ killer +"'", 1));
        Main.getInstance().sb.getTeam(killed).setPrefix(Utils.chat("&7["+ p1 +"] &f"));
        Main.getInstance().sb.getTeam(killer).setPrefix(Utils.chat("&7["+ p2 +"] &f"));
        e.setDeathMessage(Utils.chat("&c" + killed + "["+ pointsKilled +"] &ckilled by &c" +"["+ pointsKiller +"]" + killer ));
    }

    public static float calculatePercentage(float percent, float fromNumber) {
        return (float) (percent / 100) * fromNumber;
    }



}
