package hi.cr.inv.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;

import com.plotsquared.bukkit.events.PlotRateEvent;

import hi.cr.inv.Main;

public class SBS {
	
  public static void Board(Player p) {
    Scoreboard b = Bukkit.getScoreboardManager().getNewScoreboard();
    b.registerNewObjective("Drak", "dummy");
    b.getObjective("Drak").setDisplayName("§e§lCreative");
    b.getObjective("Drak").setDisplaySlot(DisplaySlot.SIDEBAR);
    Score s1 = b.getObjective("Drak").getScore("§0");
    s1.setScore(13);
    Score s2 = b.getObjective("Drak").getScore("§8» §aName");
    s2.setScore(12);
    Score s3 = b.getObjective("Drak").getScore("§f §f §f " + p.getName());
    s3.setScore(11);
    Score s4 = b.getObjective("Drak").getScore("§1");
    s4.setScore(10);
    Score s5 = b.getObjective("Drak").getScore("§8» §aRank");
    s5.setScore(9);
    Score s6 = b.getObjective("Drak").getScore("§f §f §f " + Main.getInstance().getRank(p));
    s6.setScore(8);
    Score s4a = b.getObjective("Drak").getScore("§a");
    s4a.setScore(7);
    Score s8 = b.getObjective("Drak").getScore("§8» §bAverage rates");
    s8.setScore(6);
    Score s9 = b.getObjective("Drak").getScore("§f §f §f " + String.format("%.2f", Main.getInstance().getAvgRate(p)));
    s9.setScore(5);
    Score s10 = b.getObjective("Drak").getScore("§2");
    s10.setScore(4);
    Score s11 = b.getObjective("Drak").getScore("§8» §bNumber of rates");
    s11.setScore(3);
	int nrates = Main.getInstance().getConfig().getInt("[" + p.getUniqueId().toString() + "].count");
    Score s12 = b.getObjective("Drak").getScore("§f §f §f " + nrates);
    s12.setScore(2);
    Score s13 = b.getObjective("Drak").getScore("§3");
    s13.setScore(1);
    Score s14 = b.getObjective("Drak").getScore("§eplay.hozexmc.net");
    s14.setScore(0);
    p.setScoreboard(b);
  }
}
