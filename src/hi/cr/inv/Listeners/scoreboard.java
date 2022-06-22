package hi.cr.inv.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import hi.cr.inv.Main;

public class scoreboard {
	
	  public static void Board(Player p) {
		    Bukkit.getServer().getScheduler().runTaskLater(Main.getInstance(), new Runnable() {
				
				@Override
				public void run() {
				    SBS.Board(p);
				    SBS.Board(p);
				}
			}, 1L);
}
}
