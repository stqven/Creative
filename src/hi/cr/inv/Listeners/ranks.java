package hi.cr.inv.Listeners;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class ranks implements Listener {
	
	@EventHandler
	public void onCommandPreProccess(PlayerCommandPreprocessEvent e) {
		Player p = e.getPlayer();
		if (e.getMessage().startsWith("/ranks")) {
			openRanksMenu(p);
		}
	}
	
	public static void openRanksMenu(Player p) {
		Inventory inv = Bukkit.createInventory(null, 9, "Creative ranks");
		
		ItemStack artist1 = new ItemStack(Material.PAPER);
		ItemMeta martist1 = artist1.getItemMeta();
		martist1.setDisplayName("§7→ §bArtist §8I");
		ArrayList<String> lartist1 = new ArrayList<String>();
		lartist1.add("§9Requirements:");
		lartist1.add("§fAverate rates: §e6.0");
		lartist1.add("§fNumber of votes: §e10");
		martist1.setLore(lartist1);
		artist1.setItemMeta(martist1);
		
		ItemStack artist2 = new ItemStack(Material.PAPER);
		ItemMeta martist2 = artist2.getItemMeta();
		martist2.setDisplayName("§7→ §bArtist §8II");
		ArrayList<String> lartist2 = new ArrayList<String>();
		lartist2.add("§9Requirements:");
		lartist2.add("§fAverate rates: §e6.5");
		lartist2.add("§fNumber of votes: §e15");
		martist2.setLore(lartist2);
		artist2.setItemMeta(martist2);
		
		ItemStack artist3 = new ItemStack(Material.PAPER);
		ItemMeta martist3 = artist3.getItemMeta();
		martist3.setDisplayName("§7→ §bArtist §8III");
		ArrayList<String> lartist3 = new ArrayList<String>();
		lartist3.add("§9Requirements:");
		lartist3.add("§fAverate rates: §e7.0");
		lartist3.add("§fNumber of votes: §e20");
		martist3.setLore(lartist3);
		artist3.setItemMeta(martist3);
		
		ItemStack creative1 = new ItemStack(Material.PAPER);
		ItemMeta mcreative1 = creative1.getItemMeta();
		mcreative1.setDisplayName("§7→ §aCreative §8I");
		ArrayList<String> lcreative1 = new ArrayList<String>();
		lcreative1.add("§9Requirements:");
		lcreative1.add("§fAverate rates: §e7.5");
		lcreative1.add("§fNumber of votes: §e35");
		mcreative1.setLore(lcreative1);
		creative1.setItemMeta(mcreative1);
		
		ItemStack creative2 = new ItemStack(Material.PAPER);
		ItemMeta mcreative2 = creative2.getItemMeta();
		mcreative2.setDisplayName("§7→ §aCreative §8II");
		ArrayList<String> lcreative2 = new ArrayList<String>();
		lcreative2.add("§9Requirements:");
		lcreative2.add("§fAverate rates: §e8.0");
		lcreative2.add("§fNumber of votes: §e40");
		mcreative2.setLore(lcreative2);
		creative2.setItemMeta(mcreative2);
		
		ItemStack eng1 = new ItemStack(Material.PAPER);
		ItemMeta meng1 = eng1.getItemMeta();
		meng1.setDisplayName("§7→ §6Engineer §8I");
		ArrayList<String> leng1 = new ArrayList<String>();
		leng1.add("§9Requirements:");
		leng1.add("§fAverate rates: §e9.0");
		leng1.add("§fNumber of votes: §e55");
		meng1.setLore(leng1);
		eng1.setItemMeta(meng1);
		
		ItemStack eng2 = new ItemStack(Material.PAPER);
		ItemMeta meng2 = eng2.getItemMeta();
		meng2.setDisplayName("§7→ §6Senior Engineer");
		ArrayList<String> leng2 = new ArrayList<String>();
		leng2.add("§9Requirements:");
		leng2.add("§fAverate rates: §e9.5");
		leng2.add("§fNumber of votes: §e70");
		meng2.setLore(leng2);
		eng2.setItemMeta(meng2);

		inv.setItem(1, artist1);
		inv.setItem(2, artist2);
		inv.setItem(3, artist3);
		inv.setItem(4, creative1);
		inv.setItem(5, creative2);
		inv.setItem(6, eng1);
		inv.setItem(7, eng2);
		p.openInventory(inv);
	}
	
	@EventHandler
	public void onInvClick(InventoryClickEvent e) {
		if (e.getInventory() == null) return;
		if (e.getInventory().getName().contains("Creative ranks")) e.setCancelled(true);
	}

}
