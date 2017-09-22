package me.lagg.plugin1;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Bat;
import org.bukkit.entity.Player;
import org.bukkit.entity.Skeleton;
import org.bukkit.entity.Zombie;

import net.minecraft.server.v1_8_R1.EntityZombie;

public class TestCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
		
		if(arg0 instanceof Player) {
			Player p = (Player)(arg0);
			Plugin1.registerEntity("Zombie", 54, EntityZombie.class, Broom.class);
			Zombie s = (Zombie) Broom.spawn(p.getLocation());
			s.setPassenger(p);
		}
		System.out.println("command finished");
		return false;
	}
	
}
