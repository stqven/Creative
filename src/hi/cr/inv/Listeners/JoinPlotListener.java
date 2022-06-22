package hi.cr.inv.Listeners;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Set;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryInteractEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import com.intellectualcrafters.plot.api.PlotAPI;
import com.intellectualcrafters.plot.object.Plot;
import com.intellectualcrafters.plot.object.PlotArea;
import com.intellectualcrafters.plot.object.PlotId;
import com.intellectualcrafters.plot.object.PlotPlayer;

import hi.cr.inv.API;
import hi.cr.inv.Main;

public class JoinPlotListener implements Listener {
	
	@EventHandler
	public void onCommandMe(PlayerCommandPreprocessEvent e) {
		if (e.getMessage().startsWith("/tp ") || e.getMessage().startsWith("/teleport ") || e.getMessage().equalsIgnoreCase("/tp") || e.getMessage().equalsIgnoreCase("/teleport")) {
			e.setMessage("/wtf");
			e.setCancelled(true);
			e.setMessage("/wtf");
			openTotalPlayersMenu(e.getPlayer(), "Teleport menu");
		}
	}
	
	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		ItemStack compass = new ItemStack(Material.COMPASS);
		ItemMeta mcompass = compass.getItemMeta();
		mcompass.setDisplayName("§aCreative Menu");
		compass.setItemMeta(mcompass);
		e.getPlayer().getInventory().setItem(8, compass);
	}
	
	@EventHandler
	public void onInteract(PlayerInteractEvent e) {
			if (e.getPlayer().getInventory().getItemInHand().getType() == Material.COMPASS) {
				Player p = e.getPlayer();
				if (Main.api.getPlayerPlots(p).size() >= 1) {
					openCreativeMenu(p);
				} else {
					openNoPlot(p);
				}
			}
	}
	public static void openCreativeMenu(Player p) {
		Inventory inv = Bukkit.createInventory(null, 45, "Creative Menu");
		
		ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
		ItemMeta mglass = glass.getItemMeta();
		mglass.setDisplayName("§7CREATIVE");
		glass.setItemMeta(mglass);
		
		ItemStack wand = new ItemStack(Material.WOOD_AXE);
		ItemMeta mwand = wand.getItemMeta();
		mwand.setDisplayName("§8● §a§lWorldEdit §8●");
		ArrayList<String> lwand = new ArrayList<String>();
		lwand.add("§7Default: §b50k Blocks");
		lwand.add("§6Gold: §b100k Blocks");
		lwand.add("§aCreative: §b100k Blocks");
		lwand.add("§6Sr.Eng: §bUnlimited Blocks");
		lwand.add("§bDiamond: §bUnlimited Blocks");
		lwand.add("§aEmerald: §bUnlimited Blocks");
		lwand.add("§7");
		lwand.add("§eLeft-Click §7to get plugin items");
		lwand.add("§eRight-Click §7to get help about §aWorldEdit");
		mwand.setLore(lwand);
		wand.setItemMeta(mwand);
		
		ItemStack voxel = new ItemStack(Material.ARROW);
		ItemMeta mvoxel = voxel.getItemMeta();
		mvoxel.setDisplayName("§8● §7§lVoxelSniper §8●");
		ArrayList<String> lvoxel = new ArrayList<String>();
		lvoxel.add("§7Default: §b5 brush size");
		lvoxel.add("§6Engineer: §bUnlimited brush size");
		lvoxel.add("§6Sr.Eng: §bUnlimited brush size");
		lvoxel.add("§7");
		lvoxel.add("§eRight-Click §7to get help about §aVoxelSniper");
		mvoxel.setLore(lvoxel);
		voxel.setItemMeta(mvoxel);
		
		ItemStack ast = new ItemStack(Material.ARMOR_STAND);
		ItemMeta mast = ast.getItemMeta();
		mast.setDisplayName("§8● §6§lArmorStandTools §8●");
		ArrayList<String> last = new ArrayList<String>();
		last.add("§eClick §7to get plugin items");
		mast.setLore(last);
		ast.setItemMeta(mast);
		
		ItemStack gobp = new ItemStack(Material.DARK_OAK_FENCE);
		ItemMeta mgobp = gobp.getItemMeta();
		mgobp.setDisplayName("§8● §b§lGoBrush§3/§b§lGoPaint §8●");
		ArrayList<String> lgobp = new ArrayList<String>();
		lgobp.add("§eLeft-Click §7to get plugin items");
		lgobp.add("§eRight-Click §7to get help about §aGoBrush/Paint");
		mgobp.setLore(lgobp);
		gobp.setItemMeta(mgobp);
		
		ItemStack yourplot = new ItemStack(2);
		ItemMeta myourplot = yourplot.getItemMeta();
		myourplot.setDisplayName("§8※ §e§lGo to Your Plot §8※");
		if (Main.api.getPlayerPlots(p).size() > 1) {
			ArrayList<String> lyourplot = new ArrayList<String>();
			lyourplot.add("§eClick §7to view your plots");
		}
		yourplot.setItemMeta(myourplot);
		
		ItemStack someplot = new ItemStack(3, 1, (short) 2);
		ItemMeta msomeplot = someplot.getItemMeta();
		msomeplot.setDisplayName("§8※ §e§lGo to someone's Plot §8※");
		someplot.setItemMeta(msomeplot);
		
		ItemStack teleport = new ItemStack(280);
		ItemMeta mteleport = teleport.getItemMeta();
		mteleport.setDisplayName("§8※ §e§lTeleporter §8※");
		teleport.setItemMeta(mteleport);
		
		ItemStack plotsettings = new ItemStack(Material.ITEM_FRAME);
		ItemMeta mplotsettings = plotsettings.getItemMeta();
		mplotsettings.setDisplayName("§8※ §e§lPlot Settings §8(§c§lMaintenance§8) §8※");
		plotsettings.setItemMeta(mplotsettings);

		inv.setItem(0, glass);
		inv.setItem(1, glass);
		inv.setItem(2, glass);
		inv.setItem(3, glass);
		inv.setItem(4, glass);
		inv.setItem(5, glass);
		inv.setItem(6, glass);
		inv.setItem(7, glass);
		inv.setItem(8, glass);
		inv.setItem(9, glass);
		inv.setItem(17, glass);
		inv.setItem(18, glass);
		inv.setItem(26, glass);
		inv.setItem(27, glass);
		inv.setItem(35, glass);
		inv.setItem(36, glass);
		inv.setItem(37, glass);
		inv.setItem(38, glass);
		inv.setItem(39, glass);
		inv.setItem(40, glass);
		inv.setItem(41, glass);
		inv.setItem(42, glass);
		inv.setItem(43, glass);
		inv.setItem(44, glass);
		inv.setItem(11, wand);
		inv.setItem(12, voxel);
		inv.setItem(14, ast);
		inv.setItem(15, gobp);
		inv.setItem(21, yourplot);
		inv.setItem(22, teleport);
		inv.setItem(23, someplot);
		inv.setItem(31, plotsettings);
		p.openInventory(inv);
	}
	public static void openNoPlot(Player p) {
		Inventory inv = Bukkit.createInventory(null, 45, "Claim a plot");
		
		ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
		ItemMeta mglass = glass.getItemMeta();
		mglass.setDisplayName("§7CREATIVE");
		glass.setItemMeta(mglass);
		
		ItemStack createplot = new ItemStack(Material.WOOD_AXE);
		ItemMeta mcreateplot = createplot.getItemMeta();
		mcreateplot.setDisplayName("§b§lClick to claim your first plot");
		ArrayList<String> lcreateplot = new ArrayList<String>();
		lcreateplot.add("§7You don't have any plots");
		mcreateplot.setLore(lcreateplot);
		createplot.setItemMeta(mcreateplot);

		inv.setItem(0, glass);
		inv.setItem(1, glass);
		inv.setItem(2, glass);
		inv.setItem(3, glass);
		inv.setItem(4, glass);
		inv.setItem(5, glass);
		inv.setItem(6, glass);
		inv.setItem(7, glass);
		inv.setItem(8, glass);
		inv.setItem(9, glass);
		inv.setItem(17, glass);
		inv.setItem(18, glass);
		inv.setItem(26, glass);
		inv.setItem(27, glass);
		inv.setItem(35, glass);
		inv.setItem(36, glass);
		inv.setItem(37, glass);
		inv.setItem(38, glass);
		inv.setItem(39, glass);
		inv.setItem(40, glass);
		inv.setItem(41, glass);
		inv.setItem(42, glass);
		inv.setItem(43, glass);
		inv.setItem(44, glass);
		inv.setItem(22, createplot);
		p.openInventory(inv);
	}

	public static void openPlotsMenu(Player p, String title, Player pt) {
		Inventory inv = Bukkit.createInventory(null, 27, title);
		int plots = Main.api.getPlayerPlots(pt).size();
		
		ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
		ItemMeta mglass = glass.getItemMeta();
		mglass.setDisplayName("§7CREATIVE");
		glass.setItemMeta(mglass);

		inv.setItem(0, glass);
		inv.setItem(1, glass);
		inv.setItem(2, glass);
		inv.setItem(3, glass);
		inv.setItem(4, glass);
		inv.setItem(5, glass);
		inv.setItem(6, glass);
		inv.setItem(7, glass);
		inv.setItem(8, glass);
		inv.setItem(9, glass);
		inv.setItem(17, glass);
		inv.setItem(18, glass);
		inv.setItem(19, glass);
		inv.setItem(20, glass);
		inv.setItem(21, glass);
		inv.setItem(22, glass);
		inv.setItem(23, glass);
		inv.setItem(24, glass);
		inv.setItem(25, glass);
		inv.setItem(26, glass);
		ItemStack xplot = new ItemStack(7);
		ItemMeta mxplot = xplot.getItemMeta();
		mxplot.setDisplayName("§c#X §7Plot");
		xplot.setItemMeta(mxplot);
		for (int i = 10; i <= 16; i++) {
			if ((i - 10) < plots) {
				ItemStack tplot = new ItemStack(2);
				ItemMeta mtplot = tplot.getItemMeta();
				mtplot.setDisplayName("§e#" + (i - 9) + " §7Plot");
				tplot.setItemMeta(mtplot);
				inv.setItem(i, tplot);
			} else {
				inv.setItem(i, xplot);
			}
		}
		p.openInventory(inv);
	}
	public static void openTotalPlayersMenu(Player p, String title) {
		Inventory inv = Bukkit.createInventory(null, 54, title);
		int online = Bukkit.getServer().getOnlinePlayers().size();

		ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short) 15);
		ItemMeta mglass = glass.getItemMeta();
		mglass.setDisplayName("§7CREATIVE");
		glass.setItemMeta(mglass);

		ItemStack refresh = new ItemStack(Material.EMERALD);
		ItemMeta mrefresh = refresh.getItemMeta();
		mrefresh.setDisplayName("§aRefresh");
		refresh.setItemMeta(mrefresh);

		ItemStack block = new ItemStack(Material.REDSTONE_BLOCK);
		ItemMeta mblock = block.getItemMeta();
		mblock.setDisplayName("§4Block teleport");
		ArrayList<String> lblock = new ArrayList<String>();
		if (Main.getInstance().getConfig().contains(p.getName() + ".bt")) {
			if (Main.getInstance().getConfig().getBoolean(p.getName() + ".bt")) {
				lblock.add("§cNo one can teleport to you");
			} else {
				lblock.add("§aEveryone can teleport to you");
			}
		} else {
			lblock.add("§aEveryone can teleport to you");
		}
		mblock.setLore(lblock);
		block.setItemMeta(mblock);
		
		ItemStack next = new ItemStack(Material.SIGN);
		ItemMeta mnext = next.getItemMeta();
		mnext.setDisplayName("§cNext ->");
		next.setItemMeta(mnext);
		ItemStack back = new ItemStack(431);
		ItemMeta mback = back.getItemMeta();
		mback.setDisplayName("§cBack to creative menu");
		back.setItemMeta(mback);

		inv.setItem(0, glass);
		inv.setItem(1, glass);
		inv.setItem(2, glass);
		inv.setItem(3, glass);
		inv.setItem(4, glass);
		inv.setItem(5, glass);
		inv.setItem(6, glass);
		inv.setItem(7, glass);
		inv.setItem(8, glass);
		inv.setItem(9, glass);
		inv.setItem(17, glass);
		inv.setItem(18, glass);
		inv.setItem(26, glass);
		inv.setItem(27, glass);
		inv.setItem(35, glass);
		inv.setItem(36, glass);
		inv.setItem(44, glass);
		inv.setItem(45, glass);
		inv.setItem(46, glass);
		inv.setItem(47, glass);
		inv.setItem(51, glass);
		inv.setItem(52, glass);
		inv.setItem(53, back);
		if (inv.getName().contains("Teleport menu")) {
			inv.setItem(48, refresh);
			inv.setItem(49, glass);
			inv.setItem(50, block);
		} else {
			inv.setItem(48, glass);
			inv.setItem(49, refresh);
			inv.setItem(50, glass);
		}
		int count = 0;
		ArrayList<String> players = new ArrayList<String>(Arrays.asList(Bukkit.getServer().getOnlinePlayers().toString().replace("[", "").replace("]", "").replace("CraftPlayer{name=", "").replace("", "").replace("}", "").split(", ")));
		if (inv.getName().contains("Kick a player from your plot")) {
			if (Main.api.getPlot(p).getOwners().contains(p)) {
				if (Main.api.getPlot(p).getPlayersInPlot().size() >= 1) {
					players = new ArrayList<String>(Arrays.asList(Main.api.getPlot(p).getPlayersInPlot().toString().replace("[", "").replace("]", "").replace("CraftPlayer{name=", "").replace("", "").replace("}", "").split(", ")));	
				} else {
					players = new ArrayList<String>();
				}
			}
		}
		players.remove(p.getName());
		if (inv.getName().contains("Teleport menu")) {
			for (Player all : Bukkit.getOnlinePlayers()) {
				if (API.isVanished(p.getName())) continue;
				if (!all.getName().equals(p.getName())) {
					if (Main.getInstance().getConfig().contains(all.getName() + ".bt")) {
						if (Main.getInstance().getConfig().getBoolean(all.getName() + ".bt")) {
							players.remove(all.getName());
						}
					}
				}
			}
		}
		if (players.size() != 0) {
			for (int j = 0; j < (((int) players.size()/29) + 1); j++) {
				for (int i = 0; i < 28; i++) {
			        if (i == 27) {
			        	if (players.size() > 28) {
			        		inv.setItem(i, next);
			        	} else {
					        ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
					        SkullMeta mhead = (SkullMeta) head.getItemMeta();
					        mhead.setOwner(players.get(count));
					        mhead.setDisplayName("§a" + players.get(count));
					        head.setItemMeta(mhead);
					        inv.addItem(head);
					        count++;
			        	}
			        } else {
				        ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short) SkullType.PLAYER.ordinal());
				        SkullMeta mhead = (SkullMeta) head.getItemMeta();
				        mhead.setOwner(players.get(count));
				        mhead.setDisplayName("§a" + players.get(count));
				        head.setItemMeta(mhead);
				        inv.addItem(head);
				        count++;
			        }
			        if (i + 1 == players.size()) {
			        	break;
			        }
				}
			}
		} else {
			
		}
		p.openInventory(inv);
	}
	
	@EventHandler
	public void onCreativeMenu(InventoryClickEvent e) {
		Inventory inv = e.getInventory();
		Player p = (Player) e.getWhoClicked();
		if (inv.getName().contains("Creative Menu")) {
			e.setCancelled(true);
			ItemStack back = new ItemStack(431);
			ItemMeta mback = back.getItemMeta();
			mback.setDisplayName("§cBack to creative menu");
			back.setItemMeta(mback);
			if (e.getCurrentItem().getType() == Material.WOOD_AXE) {
				p.closeInventory();
				if (e.getClick() == ClickType.RIGHT) {
					Bukkit.getServer().dispatchCommand(p, "worldedit help");
					p.sendMessage("§ahttps://www.youtube.com/watch?v=1bY-pOYhzVU");
					p.sendMessage("§ahttps://www.youtube.com/watch?v=dmzAXKSv0qA");
					p.sendMessage("§ahttps://www.youtube.com/watch?v=SOOvommDpUA");
				} else {
					
					p.getInventory().setHeldItemSlot(0);
					Bukkit.getServer().dispatchCommand(p, "/wand");
				}
			}
			if (e.getCurrentItem().getType() == Material.ARROW) {
				p.closeInventory();
				if (e.getClick() == ClickType.RIGHT) {
					p.sendMessage("§ahttps://www.youtube.com/watch?v=WhqbDi6ICA8");
					p.sendMessage("§ahttps://www.youtube.com/watch?v=UM4SpgWK_eo");
					p.sendMessage("§ahttps://www.youtube.com/watch?v=HBsI8t3wBF4");
					p.sendMessage("§ahttps://www.youtube.com/watch?v=kcfcfFdrWVk");
				} else {
					p.getInventory().addItem(new ItemStack(Material.ARROW));
					p.getInventory().addItem(new ItemStack(289));
				}
			}
			if (e.getCurrentItem().getType().getId() == 191) {
				p.closeInventory();
				if (e.getClick() == ClickType.RIGHT) {
					p.sendMessage("§cGoBrush:");
					p.sendMessage("§ahttps://www.youtube.com/watch?v=RUp0mX50FIU");
					p.sendMessage("§cGoPaint:");
					p.sendMessage("§ahttps://www.youtube.com/watch?v=ns_E_Nly-VU");
				} else {
					p.getInventory().addItem(new ItemStack(Material.FEATHER));
					p.getInventory().addItem(new ItemStack(318));
				}
			}
			if (e.getCurrentItem().getType() == Material.ARMOR_STAND) {
				p.closeInventory();
				
				Bukkit.getServer().dispatchCommand(p, "ast");
			}
			if (e.getCurrentItem().getType() == Material.DARK_OAK_FENCE) {
				p.closeInventory();
				
				if (e.getClick() == ClickType.RIGHT) {
					
				} else {
					
				}
			}
			if (e.getCurrentItem().getTypeId() == 2) {
				if (Main.api.getPlayerPlots(p).size() > 1) {
					openPlotsMenu(p, "Click to teleport to your plots", p);//IMP
					p.getOpenInventory().setItem(26, back);
				} else {
					Bukkit.getServer().dispatchCommand(p, "plot h");
				}
				p.getOpenInventory().setItem(26, back);
			}
			if (e.getCurrentItem().getTypeId() == 3) {
				
				openTotalPlayersMenu(p, "Go to someone's plot");
				p.getOpenInventory().setItem(53, back);
			}
			if (e.getCurrentItem().getTypeId() == 389) {
				e.setCancelled(true);
			}
			if (e.getCurrentItem().getTypeId() == 280) {
				
				openTotalPlayersMenu(p, "Teleport menu");
				p.getOpenInventory().setItem(53, back);
			}
			if (e.getCurrentItem().getTypeId() == 389) {
				
				//Settings
			}
		} else if (inv.getName().contains("Click to teleport")) {
			e.setCancelled(true);
			if (inv.getName().contains("Click to teleport to your plots")) {
				if (!e.getCurrentItem().getItemMeta().getDisplayName().contains("#X")) {
					if (e.getCurrentItem().getItemMeta().getDisplayName().contains("#")) {
						String name = e.getCurrentItem().getItemMeta().getDisplayName().replace("§e#", "").replace(" §7Plot", "");
						Bukkit.getServer().dispatchCommand(p, "plot h " + name);
					}
				}
			} else {
				if (!e.getCurrentItem().getItemMeta().getDisplayName().contains("#X")) {
					if (e.getCurrentItem().getItemMeta().getDisplayName().contains("#")) {
						String name = inv.getName().replace("Click to teleport to ", "").replace("'s plots", "");
						Bukkit.getServer().dispatchCommand(p, "plot h " + name);
					}
				}
			}
		} else if (inv.getName().equals("Go to someone's plot")) {
			e.setCancelled(true);
			if (e.getCurrentItem().getType() != Material.STAINED_GLASS_PANE) {
				Player pt = Bukkit.getPlayer(e.getCurrentItem().getItemMeta().getDisplayName().replace("§a", ""));
				if (pt != null) {
					if (Main.api.getPlayerPlots(p).size() >= 1) {
						openPlotsMenu(p, "Click to teleport to " + pt.getName()  + "'s plots", pt);
						ItemStack back = new ItemStack(431);
						ItemMeta mback = back.getItemMeta();
						mback.setDisplayName("§cBack to creative menu");
						back.setItemMeta(mback);
						p.getOpenInventory().setItem(26, back);
					} else {
						Bukkit.getServer().dispatchCommand(p, "plot h " + pt.getName());
					}
				}
			}
		} else if (inv.getName().equals("Teleport menu")) {
			e.setCancelled(true);
			if (e.getCurrentItem().getType() != Material.STAINED_GLASS_PANE) {
				Player pt = Bukkit.getPlayer(e.getCurrentItem().getItemMeta().getDisplayName().replace("§a", ""));
				if (pt != null) {
					if (Main.getInstance().getConfig().contains(pt.getName() + ".bt")) {
						if (Main.getInstance().getConfig().getBoolean(pt.getName() + ".bt")) {
							p.sendMessage("§8┃ §cHozexMC §8┃ §7You can't teleport to that player");
						} else {
							p.teleport(pt);
						}
					} else {
						p.teleport(pt);
					}
				}
			}
		} else if (inv.getName().contains("Claim a plot")) {
			if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Click to claim")) {
				e.setCancelled(true);
				
				Bukkit.getServer().dispatchCommand(p, "plot auto");
			}
		}
	}
	
	
	public static void openPlotSettings(Player p) {
		Inventory inv = Bukkit.createInventory(null, 45, "Plot Settings");
		
		ItemStack glass = new ItemStack(Material.STAINED_GLASS_PANE, 1,(short) 3);
		ItemMeta mglass = glass.getItemMeta();
		mglass.setDisplayName("§7CREATIVE");
		glass.setItemMeta(mglass);
		
		ItemStack kick = new ItemStack(351, 1,(short) 8);
		ItemMeta mkick = kick.getItemMeta();
		ArrayList<String> lkick = new ArrayList<String>();
		lkick.add("§7Kick a player from your plot");
		lkick.add("§7Kicked players can join your plot again");
		mkick.setLore(lkick);
		mkick.setDisplayName("§8◆ §cKick a player §8◆");
		kick.setItemMeta(mkick);
		
		ItemStack add = new ItemStack(351, 1,(short) 1);
		ItemMeta madd = add.getItemMeta();
		ArrayList<String> ladd = new ArrayList<String>();
		ladd.add("§7Remove an added/trusted player");
		ladd.add("§7from your plot");
		madd.setLore(ladd);
		madd.setDisplayName("§8◆ §cRemove a player §8◆");
		add.setItemMeta(madd);
		
		ItemStack trust = new ItemStack(Material.DIAMOND_AXE);
		ItemMeta mtrust = trust.getItemMeta();
		ArrayList<String> ltrust = new ArrayList<String>();
		ltrust.add("§7Trusted players can build in your");
		ltrust.add("§7plot even you are §eonline §7or §eoffline");
		mtrust.setLore(ltrust);
		mtrust.setDisplayName("§8◆ §aTrust a player §8◆");
		trust.setItemMeta(mtrust);
		
		ItemStack teleport = new ItemStack(Material.EYE_OF_ENDER);
		ItemMeta mteleport = teleport.getItemMeta();
//		ArrayList<String> lteleport = new ArrayList<String>();
//		if (plots > 1) {
//			lteleport.add("§7You have §e#" + plots + " §7plots");
//			lteleport.add("§eRight-Click §7to teleport to last plot (§e#" + last + "§7)");
//			lteleport.add("§eLeft-Click §7to see all plots");
//		} else {
//			lteleport.add("§7You have only §e#1 §7plot");
//			lteleport.add("§eRight-Click §7to teleport in your plot");
//		}
//		mteleport.setLore(lteleport);
		mteleport.setDisplayName("§8◆ §eTeleport in your plot §8◆");
		teleport.setItemMeta(mteleport);
		
		inv.setItem(0, glass);
		inv.setItem(1, glass);
		inv.setItem(2, glass);
		inv.setItem(3, glass);
		inv.setItem(4, glass);
		inv.setItem(5, glass);
		inv.setItem(6, glass);
		inv.setItem(7, glass);
		inv.setItem(8, glass);
		inv.setItem(19, teleport);
		inv.setItem(21, kick);
		inv.setItem(23, add);
		inv.setItem(25, trust);
		inv.setItem(36, glass);
		inv.setItem(37, glass);
		inv.setItem(38, glass);
		inv.setItem(39, glass);
		inv.setItem(40, glass);
		inv.setItem(41, glass);
		inv.setItem(42, glass);
		inv.setItem(43, glass);
		inv.setItem(44, glass);
		
		p.openInventory(inv);
	}
	
	public static void openKickListMenu2(Player p) {
		Inventory inv = Bukkit.createInventory(null, 9, "Kick a player");
		Set<Plot> plots = Main.api.getPlotSquared().getPlots(p.getUniqueId());
		ArrayList<Player> playersinplot = new ArrayList<Player>();
			for (Plot allplots : plots) {
				playersinplot.add((Player) allplots.getPlayersInPlot());
			}
			p.openInventory(inv);
			
			for (Player all : playersinplot) {
				ItemStack playerhead = new ItemStack(Material.ANVIL);
				ItemMeta mplayerhead = playerhead.getItemMeta();
				mplayerhead.setDisplayName("" + all.getName());
				playerhead.setItemMeta(mplayerhead);
				inv.addItem(playerhead);
			}
	}
	
	public static void openKickListMenu(Player p) {
		Inventory inv = Bukkit.createInventory(null, 27, "Kick a player");
			Set<Plot> plots = Main.api.getPlotSquared().getPlots(p.getUniqueId());
				for (Plot allplots : plots) {
					for (PlotPlayer pp : allplots.getPlayersInPlot()) {
						if (pp.getName() != p.getName()) {
							
						    ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short)SkullType.PLAYER.ordinal());
						    SkullMeta mskull = (SkullMeta)skull.getItemMeta();
						    mskull.setOwner(pp.getName());
						    mskull.setDisplayName("§8➥ §e" + pp.getName());
						    ArrayList<String> lore = new ArrayList<String>();
							if (pp.hasPermission("H.Owner") || pp.hasPermission("H.HeadAdmin") || pp.hasPermission("H.Admin")) {
								lore.add("§7You can't kick that player");
								lore.add("§7from your plot");
							} else {
								lore.add("§7click to §cKICK §7this player");
								lore.add("§7from your plot");
							}
						    mskull.setLore(lore);
						    skull.setItemMeta(mskull);
		                    inv.addItem(skull);
						}
					}
				}
				p.openInventory(inv);
	}
	
	@EventHandler
	public void onKickListMenu(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		Inventory inv = e.getInventory();
		if (inv.getName().contains("Kick a player")) {
			e.setCancelled(true);
			if (!e.getCurrentItem().getItemMeta().getLore().contains("§7You can't kick that player")) {
				String pname = e.getCurrentItem().getItemMeta().getDisplayName().replace("§8➥ §e", "");
				Bukkit.getServer().dispatchCommand(p, "plot kick " + pname);
				openKickListMenu(p);
			}
		}
	}
	
	public static void openRemoveListMenu(Player p) {
		Inventory inv = Bukkit.createInventory(null, 27, "Remove a player");
			Set<Plot> plots = Main.api.getPlotSquared().getPlots(p.getUniqueId());
					for (Player all : Bukkit.getOnlinePlayers()) {
						if (!all.getName().equals(p.getName())) {
							boolean isPlayerAdded = false;
							for (Plot ap : Main.api.getPlotSquared().getPlots(p.getUniqueId())) {
								if (ap.getTrusted().contains(all.getUniqueId())) {
									isPlayerAdded = true;
								}
							}
							if (isPlayerAdded == true) {
							    ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short)SkullType.PLAYER.ordinal());
							    SkullMeta mskull = (SkullMeta)skull.getItemMeta();
							    mskull.setOwner(all.getName());
							    mskull.setDisplayName("§8➥ §e" + all.getName());
							    ArrayList<String> lore = new ArrayList<String>();
							    lore.add("§eLeft-Click §7click to §cRemove §7this player");
							    lore.add("§7in your plot");
							    lore.add("§eRight-Click §7to §7to teleport to that plot");
							    mskull.setLore(lore);
							    skull.setItemMeta(mskull);
			                    inv.addItem(skull);
							}
						}
					}
				p.openInventory(inv);
	}
	
	@EventHandler
	public void onRemoveListMenu(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		Inventory inv = e.getInventory();
		if (inv.getName().contains("Remove a player")) {
			e.setCancelled(true);
			String pname = e.getCurrentItem().getItemMeta().getDisplayName().replace("§8➥ §e", "");
			p.chat("dsfksdgiuretertjkRemove " + pname);
		}
	}
	
	public static void openPlotRemoveSelector(Player p, Player add) {
		Inventory inv = Bukkit.createInventory(null, 9, "Choose a plot");
		int num = 1;
		ItemStack allItem = new ItemStack(110);
		ItemMeta mallItem = allItem.getItemMeta();
		mallItem.setDisplayName("§a§lALL Plots");
		ArrayList<String> lallItem = new ArrayList<String>();
		lallItem.add("§7click to remove §a" + add.getName() + " §7from all plots");
		mallItem.setLore(lallItem);
		allItem.setItemMeta(mallItem);
		inv.addItem(allItem);
		for (Plot pPlots : Main.api.getPlotSquared().getPlots(p.getUniqueId())) {
			if (pPlots.getTrusted().contains(add.getUniqueId())) {
				ItemStack plotItem = new ItemStack(Material.GRASS);
				ItemMeta mplotItem = plotItem.getItemMeta();
				mplotItem.setDisplayName("§e#" + num + " §7Plot");
				ArrayList<String> lplotItem = new ArrayList<String>();
				lplotItem.add("§7click to remove §a" + add.getName() + " §7from that plot");
				lplotItem.add("§eID: §7" + pPlots.getId().toString());
				mplotItem.setLore(lplotItem);
				plotItem.setItemMeta(mplotItem);
				num = num + 1;
				inv.addItem(plotItem);
			}
		}
		p.openInventory(inv);
	}
	
	
	//------------------------------------------------------------------------------------------------------------------
	
	
	public static void openTrustListMenu(Player p) {
		Inventory inv = Bukkit.createInventory(null, 27, "Trust a player");
			Set<Plot> plots = Main.api.getPlotSquared().getPlots(p.getUniqueId());
					for (Player all : Bukkit.getOnlinePlayers()) {
						if (!all.getName().equals(p.getName())) {
							
						    ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1, (short)SkullType.PLAYER.ordinal());
						    SkullMeta mskull = (SkullMeta)skull.getItemMeta();
						    mskull.setOwner(all.getName());
						    mskull.setDisplayName("§8➥ §e" + all.getName());
						    ArrayList<String> lore = new ArrayList<String>();
						    lore.add("§eLeft-Click §7click to §aTRUST §7this player");
						    lore.add("§7in your plot");
						    lore.add("§eRight-Click §7to §7to teleport to that plot");
						    mskull.setLore(lore);
						    skull.setItemMeta(mskull);
		                    inv.addItem(skull);
						}
					}
				p.openInventory(inv);
	}
	
	@EventHandler
	public void onTrustListMenu(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		Inventory inv = e.getInventory();
		if (inv.getName().contains("Trust a player")) {
			e.setCancelled(true);
				String pname = e.getCurrentItem().getItemMeta().getDisplayName().replace("§8➥ §e", "");
				p.chat("dsfksdgiuretertjkTrust " + pname);
		}
	}
	
	public static void openPlotTrustSelector(Player p, Player add) {
		Inventory inv = Bukkit.createInventory(null, 9, "Choose a plot");
		int num = 1;
		ItemStack allItem = new ItemStack(110);
		ItemMeta mallItem = allItem.getItemMeta();
		mallItem.setDisplayName("§a§lALL Plots");
		ArrayList<String> lallItem = new ArrayList<String>();
		lallItem.add("§7click to trust §a" + add.getName() + " §7in all plots");
		mallItem.setLore(lallItem);
		allItem.setItemMeta(mallItem);
		inv.addItem(allItem);
		for (Plot pPlots : Main.api.getPlotSquared().getPlots(p.getUniqueId())) {
			ItemStack plotItem = new ItemStack(Material.GRASS);
			ItemMeta mplotItem = plotItem.getItemMeta();
			mplotItem.setDisplayName("§e#" + num + " §7Plot");
			ArrayList<String> lplotItem = new ArrayList<String>();
			lplotItem.add("§7click to trust §a" + add.getName() + " §7in that plot");
			lplotItem.add("§eID: §7" + pPlots.getId().toString());
			mplotItem.setLore(lplotItem);
			plotItem.setItemMeta(mplotItem);
			num = num + 1;
			inv.addItem(plotItem);
		}
		p.openInventory(inv);
	}
	
	@EventHandler
	public void onSelector(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		Inventory inv = e.getInventory();
		if (inv.getName().contains("Choose a plot")) {
			e.setCancelled(true);
			if (e.getClick() == ClickType.RIGHT) {
				if (e.getCurrentItem().getItemMeta().getDisplayName().contains("ALL Plots")) {
					
				} else {
					String pId1 = e.getCurrentItem().getItemMeta().getLore().get(1).replace("§eID: §7", "");
					Bukkit.getServer().dispatchCommand(p, "plot home " + pId1);
				}
			} else {
				if (e.getCurrentItem().getItemMeta().getLore().get(0).contains("trust")) {
					if (e.getCurrentItem().getItemMeta().getDisplayName().contains("ALL Plots")) {
						String PlayerName = e.getCurrentItem().getItemMeta().getLore().get(0).replace("§7click to trust §a", "").replace(" §7in all plots", "");
						Set<Plot> plotsq = Main.api.getPlotSquared().getPlots(p.getUniqueId());
						for (Plot allP : plotsq) {
							allP.addTrusted(Bukkit.getPlayer(PlayerName).getUniqueId());
						}
						p.sendMessage("§8┃ §eHozexMC §8┃ §7You have added §a" + PlayerName + " §7to trusted list");
					} else {
						String plotId = e.getCurrentItem().getItemMeta().getDisplayName().replace("§e#", "").replace(" §7Plot", "");
						String PlayerName = e.getCurrentItem().getItemMeta().getLore().get(0).replace("§7click to trust §a", "").replace(" §7in that plot", "");
						ArrayList<Plot> allplots = new ArrayList<Plot>();
						Set<Plot> plotsq = Main.api.getPlotSquared().getPlots(p.getUniqueId());
						for (Plot pp : plotsq) {
							allplots.add(pp);
						}
						if (allplots.get((Integer.valueOf(plotId)) - 1).getTrusted().contains(Bukkit.getPlayer(PlayerName).getUniqueId())) {
							p.sendMessage("§8┃ §cHozexMC §8┃ §e" + PlayerName + " §7is already in your trusted list");
						} else {
							allplots.get((Integer.valueOf(plotId)) - 1).addTrusted(Bukkit.getPlayer(PlayerName).getUniqueId());
							p.sendMessage("§8┃ §eHozexMC §8┃ §7You have added §a" + PlayerName + " §7to trusted list");
						}
					}
				} else if (e.getCurrentItem().getItemMeta().getLore().get(0).contains("remove")) {
					if (e.getCurrentItem().getItemMeta().getDisplayName().contains("ALL Plots")) {
						p.sendMessage("AAa");
					} else {
						String plotId = e.getCurrentItem().getItemMeta().getDisplayName().replace("§e#", "").replace(" §7Plot", "");
						String PlayerName = e.getCurrentItem().getItemMeta().getLore().get(0).replace("§7click to remove §a", "").replace(" §7from that plot", "");
						ArrayList<Plot> allplots = new ArrayList<Plot>();
						Set<Plot> plotsq = Main.api.getPlotSquared().getPlots(p.getUniqueId());
						for (Plot pp : plotsq) {
							allplots.add(pp);
						}
						if (allplots.get((Integer.valueOf(plotId)) - 1).getTrusted().contains(Bukkit.getPlayer(PlayerName).getUniqueId())) {
							allplots.get((Integer.valueOf(plotId)) - 1).removeTrusted(Bukkit.getPlayer(PlayerName).getUniqueId());
							p.sendMessage("§8┃ §eHozexMC §8┃ §7You have remove §a" + PlayerName + " §7from added/trusted list");
						} else {
							p.sendMessage("§8┃ §cHozexMC §8┃ §e" + PlayerName + " §7isn't in your added/trusted list");
						}
					}
				} else {
					
				}
			}
			
		}
	}
	
	
	
	@EventHandler
	public void onPlotSettings(InventoryClickEvent e) {
		Player p = (Player) e.getWhoClicked();
		Inventory inv = e.getInventory();
		if (inv.getName().contains("Plot Settings")) {
			e.setCancelled(true);
			if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Creative")) {
				e.setCancelled(true);
			}
			if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Teleport in your plot")) {
				e.setCancelled(true);
			}
			if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Kick a player")) {
				e.setCancelled(true);
				openTotalPlayersMenu(p, "Kick a player from your plot");
			}
			if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Remove a player")) {
				e.setCancelled(true);
				openTotalPlayersMenu(p, "Remove an added player from your plot");
			}
			if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Trust a player")) {
				e.setCancelled(true);
				openTotalPlayersMenu(p, "Trust a player to your plot");
			}
		} else {
			if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Back to creative menu")) {
				
				openCreativeMenu(p);
			}
			if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Refresh")) {
				openTotalPlayersMenu(p, p.getOpenInventory().getTitle());
			}
			if (e.getCurrentItem().getItemMeta().getDisplayName().contains("Block teleport")) {
				e.setCancelled(true);
				if (Main.getInstance().getConfig().contains(p.getName() + ".bt")) {
					if (Main.getInstance().getConfig().getBoolean(p.getName() + ".bt")) {
						Main.getInstance().getConfig().set(p.getName() + ".bt", false);
						Main.getInstance().saveConfig();
					} else {
						Main.getInstance().getConfig().set(p.getName() + ".bt", true);
						Main.getInstance().saveConfig();
						ItemStack ss = e.getCurrentItem();
						ItemMeta mss = ss.getItemMeta();
						ArrayList<String> lss = new ArrayList<String>();
						lss.add("§cNo one can teleport to yout");
						mss.setLore(lss);
						inv.setItem(50, ss);
					}
				} else {
					Main.getInstance().getConfig().set(p.getName() + ".bt", true);
					Main.getInstance().saveConfig();
					ItemStack ss = e.getCurrentItem();
					ItemMeta mss = ss.getItemMeta();
					ArrayList<String> lss = new ArrayList<String>();
					lss.add("§cNo one can teleport to yout");
					mss.setLore(lss);
					inv.setItem(50, ss);
				}
				ItemStack block = new ItemStack(Material.REDSTONE_BLOCK);
				ItemMeta mblock = block.getItemMeta();
				mblock.setDisplayName("§4Block teleport");
				ArrayList<String> lblock = new ArrayList<String>();
				if (Main.getInstance().getConfig().contains(p.getName() + ".bt")) {
					if (Main.getInstance().getConfig().getBoolean(p.getName() + ".bt")) {
						lblock.add("§cNo one can teleport to you");
					} else {
						lblock.add("§aEveryone can teleport to you");
					}
				} else {
					lblock.add("§aEveryone can teleport to you");
				}
				mblock.setLore(lblock);
				block.setItemMeta(mblock);
				inv.setItem(50, block);
			}
		}
	}

}
