package me.lagg.plugin1;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Bat;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Zombie;

public class TestCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		
		if(arg0 instanceof Player) {
			Player p = (Player)(arg0);
			Zombie s = (Zombie) Broom.spawn(p.getLocation());
			s.setPassenger(p);
		}
		System.out.println("command finished");
		return false;
	}
	
}
