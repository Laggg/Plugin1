package me.lagg.plugin1;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChatTabCompleteEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_8_R1.BiomeBase;
import net.minecraft.server.v1_8_R1.BiomeMeta;
import net.minecraft.server.v1_8_R1.EntityEndermite;
import net.minecraft.server.v1_8_R1.EntityInsentient;
import net.minecraft.server.v1_8_R1.EntityTypes;

/**
 * @author Ddude88888
 *
 */
public class Plugin1 extends JavaPlugin implements Listener {

	public static Logger log;
	static Plugin1 instance;
	
	@Override
	public void onEnable() {
		log = getLogger();
		Bukkit.getPluginManager().registerEvents(this, this);
		this.getCommand("test").setExecutor(new TestCommand());
		instance = this;
		registerEntities();
	}
	
	@EventHandler
	public void onLeave(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		Game currentGame = Game.getGameByPlayer(p);
		if(currentGame!=null) {
			currentGame.removePlayer(p);
		}
	}
	
	@EventHandler
	public void onKick(PlayerKickEvent e) {
		Player p = e.getPlayer();
		Game currentGame = Game.getGameByPlayer(p);
		currentGame.removePlayer(p);
	}
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) {
		event.setFormat(ChatColor.GREEN + "" + ChatColor.BOLD + "[" + ChatColor.RESET + ChatColor.GOLD + event.getPlayer().getDisplayName() + ChatColor.RESET + ChatColor.GREEN + "" + ChatColor.BOLD + "]" + ChatColor.RESET + ChatColor.DARK_GRAY +" : " + ChatColor.LIGHT_PURPLE + event.getMessage());
		log.info(event.getFormat());
	}
	
	@EventHandler
	public void onPlayerChatTabComplete(PlayerChatTabCompleteEvent event) {
		log.info("working");
		Player p = event.getPlayer();
		Collection<String> c = event.getTabCompletions();
		c.add("poo");
		for(String s : c) {
			p.sendMessage(s);
		}
		if(c.size()==0) {
			p.sendMessage("empty");
		}
	}
	
	@Override
	public void onDisable() {
		
	}
	
	public void registerEntities() {
		for(CustomEntityType t : CustomEntityType.values()) {
			registerEntity(t.getName(),t.getID(),t.getNMSClass(),t.getCustomClass());
		}
	}
	
	public static void registerEntity(String name, int id, Class<? extends EntityInsentient> nmsClass, Class<? extends EntityInsentient> customClass){
        try {
     
            List<Map<?, ?>> dataMap = new ArrayList<Map<?, ?>>();
            for (Field f : EntityTypes.class.getDeclaredFields()){
                if (f.getType().getSimpleName().equals(Map.class.getSimpleName())){
                    f.setAccessible(true);
                    dataMap.add((Map<?, ?>) f.get(null));
                }
            }
     
            if (dataMap.get(2).containsKey(id)){
                dataMap.get(0).remove(name);
                dataMap.get(2).remove(id);
            }
     
            Method method = EntityTypes.class.getDeclaredMethod("a", Class.class, String.class, int.class);
            method.setAccessible(true);
            method.invoke(null, customClass, name, id);
     
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
	
}
