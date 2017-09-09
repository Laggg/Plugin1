package me.lagg.plugin1;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.connorlinfoot.titleapi.TitleAPI;

public class Command1 implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		if(arg0 instanceof Player) {
			Player p = (Player)(arg0);
			p.sendMessage("HELLO!");
			ItemStack diamond = new ItemStack(Material.DIAMOND,30);
			p.getInventory().addItem(diamond);
			TitleAPI.sendTitle((Player)(arg0), 10, 10, 10, "Hello! ^-^","Welcome to the server!");
			Quidditch q = new Quidditch(p.getLocation());
			q.startPregame();
			try {
				q.addPlayer(p);
			} catch (GameFullException e) {
				e.printStackTrace();
			}
			return true;
		}
		return false;
	}
	
}
