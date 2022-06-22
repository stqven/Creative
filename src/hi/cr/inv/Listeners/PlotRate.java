package hi.cr.inv.Listeners;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.intellectualcrafters.plot.object.Plot;
import com.intellectualcrafters.plot.object.Rating;
import com.plotsquared.bukkit.events.PlotRateEvent;

import hi.cr.inv.Main;

public class PlotRate implements CommandExecutor, Listener {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (cmd.getName().equals("rate")) {
			Player p = (Player) sender;
			openPlotRate(p, "Rate this plot");
		}
		return false;
	}
	public static void openPlotRate(Player p, String title) {
		Inventory inv = Bukkit.createInventory(null, 9, title);
		ItemStack trash = new ItemStack(289);
		ItemMeta mtrash = trash.getItemMeta();
		mtrash.setDisplayName("§4§lTRASH");
		trash.setItemMeta(mtrash);
		
		ItemStack bad = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 14);
		ItemMeta mbad = bad.getItemMeta();
		mbad.setDisplayName("§c§lJUST BAD");
		bad.setItemMeta(mbad);
		
		ItemStack good = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 5);
		ItemMeta mgood = good.getItemMeta();
		mgood.setDisplayName("§a§lGOOD");
		good.setItemMeta(mgood);
		
		ItemStack excellent = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 13);
		ItemMeta mexcellent = excellent.getItemMeta();
		mexcellent.setDisplayName("§2§lEXCELLENT");
		excellent.setItemMeta(mexcellent);
		
		ItemStack wonderful = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 3);
		ItemMeta mwonderful = wonderful.getItemMeta();
		mwonderful.setDisplayName("§b§lWONDERFUL");
		wonderful.setItemMeta(mwonderful);
		
		ItemStack stunning = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 4);
		ItemMeta mstunning = stunning.getItemMeta();
		mstunning.setDisplayName("§e§lSTUNNING");
		stunning.setItemMeta(mstunning);
		
		ItemStack fullmark = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 1);
		ItemMeta mfullmark = fullmark.getItemMeta();
		mfullmark.setDisplayName("§e✓ §6§lFULL MARK §e✓");
		fullmark.setItemMeta(mfullmark);
		
		inv.setItem(1, trash);
		inv.setItem(2, bad);
		inv.setItem(3, good);
		inv.setItem(4, excellent);
		inv.setItem(5, wonderful);
		inv.setItem(6, stunning);
		inv.setItem(7, fullmark);
		p.openInventory(inv);
	}
	
	public static double getAvgRatings(Player p) {
		if (Main.getInstance().getConfig().contains(p.getName() + ".rate")) {
			return ((Main.getInstance().getConfig().getDouble(p.getName() + ".rate"))/(Main.getInstance().getConfig().getDouble(p.getName() + ".count")));	
		} else {
			return 0;
		}
	}
	
	public static double getRate(Player p) {
		if (Main.getInstance().getConfig().contains(p.getName() + ".rate")) {
			double rate = Main.getInstance().getConfig().getDouble(p.getName() + ".rate");
			double count = Main.getInstance().getConfig().getDouble(p.getName() + ".count");
			if (count > 15) {
				return rate/count;
			} else {
				return 0;
			}
		} else {
			return 0;
		}
	}
	
	@EventHandler
	public void plotRateEvent(PlotRateEvent e) {
		Main.getInstance().getConfig().set(e.getPlot().getOwners() + ".rate", Double.valueOf(Main.getInstance().getConfig().getDouble(e.getPlot().getOwners() + ".rate")).doubleValue() + e.getRating().getAverageRating());
		Main.getInstance().getConfig().set(e.getPlot().getOwners() + ".count", Double.valueOf(Main.getInstance().getConfig().getDouble(e.getPlot().getOwners() + ".count")).doubleValue() + 1);
		Main.getInstance().saveConfig();
	}
	
	@EventHandler
	public void onPlotRate(InventoryClickEvent e) {
		Inventory inv = e.getClickedInventory();
		Player p = (Player) e.getWhoClicked();
		if (inv.getName().toLowerCase().contains("rate")) {
			e.setCancelled(true);
			if (Main.api.isInPlot(p)) {
						String name = e.getCurrentItem().getItemMeta().getDisplayName();
						if (name.contains("TRASH")) {
							Bukkit.getServer().dispatchCommand(p, "plot rate 1");
						}
						if (name.contains("JUST BAD")) {
							Bukkit.getServer().dispatchCommand(p, "plot rate 3");
						}
						if (name.contains("GOOD")) {
							Bukkit.getServer().dispatchCommand(p, "plot rate 4");
						}
						if (name.contains("EXCELLENT")) {
							Bukkit.getServer().dispatchCommand(p, "plot rate 7");
						}
						if (name.contains("WONDERFUL")) {
							Bukkit.getServer().dispatchCommand(p, "plot rate 8");
						}
						if (name.contains("TUNNING")) {
							Bukkit.getServer().dispatchCommand(p, "plot rate 9");
						}
						if (name.contains("FULL MARK")) {
							Bukkit.getServer().dispatchCommand(p, "plot rate 10");
						}
			} else {
				p.sendMessage(Main.PrefixError + "You are not in a plot");
			}
			p.closeInventory();
		}
	}

}
