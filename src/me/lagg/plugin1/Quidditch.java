package me.lagg.plugin1;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;

import net.minecraft.server.v1_8_R1.EntityInsentient;
import net.minecraft.server.v1_8_R1.EntityTypes;

public class Quidditch extends Game {

	{
		minimumPlayersForStart = 1;
		maxPlayers = 12;
		CHATPREFIX = ChatColor.BLUE + "[" + ChatColor.GOLD + "QUIDDITCH" + ChatColor.BLUE + "] ";
	}
	
	public Quidditch(Location waitingRoom) {
		this.waitingRoom = waitingRoom;
	}
	
	public Quidditch(Location waitingRoom, ArrayList<Location> startingPositions) {
		
	}
	
	@Override
	public void inGameTick() {
		
	}

	@Override
	public void postGameTick() {
		
	}

	@Override
	public void start() {
		phase = EnumGamePhase.INGAME;
		if(players.size()==0) {
			return;
		}
		World w = players.get(0).getWorld();
		for(Player p : players) {
			p.sendMessage("Game started!");
			Bukkit.getScheduler().scheduleSyncDelayedTask(Plugin1.instance, new Runnable() {
				@Override
				public void run() {
					Entity e = w.spawnEntity(p.getLocation(), EntityType.ENDERMITE);
					e.setPassenger(p);
					p.sendMessage("Made horse");
					//Broom b = (Broom)e;
					//e = (Entity)b;
					//p.sendMessage("casted");
				}
			});
		}
	}
	
	@Override
	public void end() {
		phase = EnumGamePhase.POSTGAME;
		for(Player p : players) {
			p.sendMessage("Game over!");
		}
	}

	@Override
	public void updateScoreboardPreGame() {
		
	}

	@Override
	public void updateScoreboardPostGame() {
		
	}
		
}
