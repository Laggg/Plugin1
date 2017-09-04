package me.lagg.plugin1;

import java.util.Collection;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChatTabCompleteEvent;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;

public class Plugin1 extends JavaPlugin implements Listener {

	Logger log = getLogger();
	
	@Override
	public void onEnable() {
		Bukkit.getPluginManager().registerEvents(this, this);
		this.getCommand("hello").setExecutor(new Command1());
		this.getCommand("fireball").setExecutor(new FireballCommand());
		this.getCommand("feed").setExecutor(new Feed());
		//this.getCommand("game1").setExecutor(new Game1());
	}
	
	@EventHandler
	public void onLeave(PlayerQuitEvent e) {
		Player p = e.getPlayer();
		Game currentGame = Game.getGameByPlayer(p);
		currentGame.removePlayer(p);
	}
	
	@EventHandler
	public void onKick(PlayerKickEvent e) {
		Player p = e.getPlayer();
		Game currentGame = Game.getGameByPlayer(p);
		currentGame.removePlayer(p);
	}
	
	@EventHandler
	public void onChat(AsyncPlayerChatEvent event) {
		//event.setFormat(ChatColor.GREEN.toString() + event.getFormat());
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
	
}