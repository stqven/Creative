package hi.cr.inv.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockRedstoneEvent;

public class AntiRedstone implements Listener {
	
//	@EventHandler
	public void onBlockRedstone(BlockRedstoneEvent e) {
		e.setNewCurrent(0);
	}

}
