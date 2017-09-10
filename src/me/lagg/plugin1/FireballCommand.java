package me.lagg.plugin1;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;

/**
 * @author Ddude88888
 *
 */
public class FireballCommand implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender arg0, Command arg1, String arg2, String[] arg3) {
			if(arg0 instanceof Player) {
				Player p = (Player)(arg0);
				p.getWorld().spawn(p.getEyeLocation().add(0, 1, 0), Fireball.class);
				return true;
			}
		return false;
	}

}
