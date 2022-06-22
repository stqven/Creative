package hi.cr.inv;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import net.md_5.bungee.api.ChatColor;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.IChatBaseComponent.ChatSerializer;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle.EnumTitleAction;

public class API implements Listener {
	
//	  private WorldGuardPlugin wgPlugin;
//	
//	  public API(API plugin, WorldGuardPlugin wgPlugin) {
//		    this.wgPlugin = wgPlugin;
//		  }
	
	  public static boolean isVanished(String Playername) {
		  int vanished = 0;
		      ResultSet Result = msq.getResult("SELECT * FROM premiumvanish_playerdata WHERE Name='" + Playername + "'");
		      try {
		        if (Result.next()) {
		          vanished = Result.getInt("Vanished");
		        }
		      }
		      catch (SQLException sQLException) {}
		      if (vanished == 0) {
		    	  return false;
		      } else {
		    	  return true;
		      }
		  }
}
