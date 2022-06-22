package hi.cr.inv;

import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

import javax.persistence.Entity;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPhysicsEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.intellectualcrafters.plot.api.PlotAPI;
import com.plotsquared.bukkit.events.PlotRateEvent;

import hi.cr.inv.Listeners.AntiRedstone;
import hi.cr.inv.Listeners.JoinPlotListener;
import hi.cr.inv.Listeners.PlotRate;
import hi.cr.inv.Listeners.ranks;
import hi.cr.inv.Listeners.scoreboard;
public class Main extends JavaPlugin implements Listener {
	
  public static Main main;
  private static Main instance;
  public static String Prefix;
  public static String PrefixError;
  public static String PrefixBigError;
  public static String PrefixSuccess;
  public static ArrayList<String> list;
  public static PlotAPI api;
  
  public void onEnable() {
    getConfig().addDefault("Cooldown", Integer.valueOf(2));
    getConfig().options().copyDefaults(true);
    saveConfig();
    
    main = this;
    for (Player all : Bukkit.getOnlinePlayers()) {
    	scoreboard.Board(all);
    }
    Bukkit.getServer().getPluginManager().registerEvents(new JoinPlotListener(), this);    
    Bukkit.getServer().getPluginManager().registerEvents(new AntiRedstone(), this);
    Bukkit.getServer().getPluginManager().registerEvents(new PlotRate(), this);
    Bukkit.getServer().getPluginManager().registerEvents(new ranks(), this);
    Bukkit.getServer().getPluginManager().registerEvents(this, this);
    getCommand("rate").setExecutor(new PlotRate());
    api = new PlotAPI();
    msq.connect();
  }
  
  @EventHandler
  public void onBlockChangePhisics(BlockPhysicsEvent e) {
	  e.setCancelled(true);
  }
  
  @EventHandler(priority = EventPriority.HIGHEST, ignoreCancelled = true)
  private void onSandFall(EntityChangeBlockEvent event){
      if(event.getEntityType()==EntityType.FALLING_BLOCK && event.getTo()==Material.AIR){
              event.setCancelled(true);
              //Update the block to fix a visual client bug, but don't apply physics
              event.getBlock().getState().update(false, false);
      }
  }
  
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		scoreboard.Board(e.getPlayer());
	}
	@EventHandler
	public void onRespawn(PlayerRespawnEvent e) {
		scoreboard.Board(e.getPlayer());
	}
	@EventHandler
	public void onRate(PlotRateEvent e) {
		for (Player all : Bukkit.getOnlinePlayers()) {
			scoreboard.Board(all);
		}
	}
	
	@EventHandler
	public void onRate(PlayerCommandPreprocessEvent e) {
		if (e.getMessage().startsWith("/lrates")) {
			int i = 1;
			for (UUID rate : api.getPlot(Bukkit.getPlayer("ahmed_YT")).getRatings().keySet()) {
				if (i >= 62) {
					api.getPlot(Bukkit.getPlayer("ahmed_YT")).getRatings().remove(rate);
				}
				i++;
			}
			e.getPlayer().sendMessage("" + api.getPlot(Bukkit.getPlayer("ahmed_YT")).getRatings().size());
		}
	}
  
  public static String getRank(Player p) {
		double nrates = Main.getInstance().getConfig().getDouble("[" + p.getUniqueId().toString() + "].count");
		double rates = Main.getInstance().getConfig().getDouble("[" + p.getUniqueId().toString() + "].rate")/nrates;
		
		String prank = "§7Unranked";
    	if (rates >= 9.5 && nrates >= 70) {
    		prank = "§6§lSr.Eng";
    	} else if (rates >= 9 && nrates >= 55) {
    		prank = "§6Engineer";
    	} else if (rates >= 8 && nrates >= 40) {
    		prank = "§aCreative §8II";	
    	} else if (rates >= 7.5 && nrates >= 35) {
    		prank = "§aCreative §8I";	
    	} else if (rates >= 7 && nrates >= 20) {
    		prank = "§bArtist §8III";	
    	} else if (rates >= 6.5 && nrates >= 15) {
    		prank = "§bArtist §8II";	
    	} else if (rates >= 6 && nrates >= 10) {
    		prank = "§bArtist §8I";	
    	}
		
	  return prank;
  }
  
  public static double getAvgRate(Player p) {
	  return Main.getInstance().getConfig().getDouble("[" + p.getUniqueId().toString() + "].rate")/Main.getInstance().getConfig().getDouble("[" + p.getUniqueId().toString() + "].count");
  }
  
  public Main() {
	    Prefix = ChatColor.translateAlternateColorCodes('&', "§8❘ §eHozexMC §8❘ §7");
	    PrefixError = ChatColor.translateAlternateColorCodes('&', "§8❘ §cHozexMC §8❘ §7");
	    PrefixSuccess = ChatColor.translateAlternateColorCodes('&', "§8❘ §aHozexMC §8❘ §7");
	    PrefixBigError = ChatColor.translateAlternateColorCodes('&', "§8❘ §4HozexMC §8❘ §c");
    getConfig().getList("Letters");
  }

  @EventHandler
  public void onTNT(EntityExplodeEvent e) {
	  e.setCancelled(true);
  }
  
  public boolean checkMineCart(Location loc) {
	  int locs = 0;
	  for (org.bukkit.entity.Entity en : loc.getWorld().getEntities()) {
		  if (en instanceof Minecart) {
			  if (en.getLocation().distance(loc) <= 10) {
				  locs++;
			  }
		  }
	  }
	  if (locs >= 5) {
		  return true;
	  }
	  return false;
  }
  
  @EventHandler
  public void disableMineCard(PlayerInteractEvent e) {
	  Player p = e.getPlayer();
	  Location loc = p.getLocation();
	  if (p.getItemInHand().getType() == Material.MINECART) {
		  int locs = 0;
		  for (org.bukkit.entity.Entity en : loc.getWorld().getEntities()) {
			  if (en instanceof Minecart) {
				  if (en.getLocation().distance(loc) <= 10) {
					  locs++;
				  }
			  }
		  }
		  if (locs >= 5) {
			  e.setCancelled(true);
			  p.sendMessage(Main.PrefixError + "You can't place more minecarts");
		  }
	  }
  }
  
  public static Main getInstance() {
	    return instance;
	  }
  
  public int getRandom(int lower, int upper) {
    Random random = new Random();
    return random.nextInt(upper - lower + 1) + lower;
  }
  
  public void onLoad() {
	  instance = this;
  }
  
}
