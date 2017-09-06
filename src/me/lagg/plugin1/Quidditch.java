package me.lagg.plugin1;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_8_R1.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

public class Quidditch extends Game {

	int minimumPlayersForStart = 4;
	int maxPlayers = 12;
	final String CHATPREFIX = ChatColor.BLUE + "[" + ChatColor.GOLD + "QUIDDITCH" + ChatColor.BLUE + "] ";
	
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
			Entity e = w.spawnEntity(p.getLocation(), EntityType.ARMOR_STAND);
			e.setPassenger(p);
			
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
