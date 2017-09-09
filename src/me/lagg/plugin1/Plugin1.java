package me.lagg.plugin1;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
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
import net.minecraft.server.v1_8_R1.EntityTypes;

public class Plugin1 extends JavaPlugin implements Listener {

	public static Logger log;
	static Plugin1 instance;
	
	@Override
	public void onEnable() {
		log = getLogger();
		Bukkit.getPluginManager().registerEvents(this, this);
		this.getCommand("hello").setExecutor(new Command1());
		this.getCommand("fireball").setExecutor(new FireballCommand());
		this.getCommand("feed").setExecutor(new Feed());
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
	
	public static void registerEntities(){
	        for (CustomEntityType entity : CustomEntityType.values()){
	            try{
	                Method a = EntityTypes.class.getDeclaredMethod("a", new Class<?>[]{Class.class, String.class, int.class});
	                a.setAccessible(true);
	                a.invoke(null, entity.getCustomClass(), entity.getName(), entity.getID());
	            }catch (Exception e){
	                e.printStackTrace();
	            }
	        }
	     
	        for (BiomeBase biomeBase : BiomeBase.getBiomes()){
	            if (biomeBase == null){
	                break;
	            }
	         
	            for (String field : new String[]{"K", "J", "L", "M"}){
	                try{
	                    Field list = BiomeBase.class.getDeclaredField(field);
	                    list.setAccessible(true);
	                    @SuppressWarnings("unchecked")
	                    List<BiomeMeta> mobList = (List<BiomeMeta>) list.get(biomeBase);
	                 
	                    for (BiomeMeta meta : mobList){
	                        for (CustomEntityType entity : CustomEntityType.values()){
	                            if (entity.getNMSClass().equals(meta.b)){
	                                meta.b = entity.getCustomClass();
	                            }
	                        }
	                    }
	                }catch (Exception e){
	                    e.printStackTrace();
	                }
	            }
	        }
	    }
	
}
