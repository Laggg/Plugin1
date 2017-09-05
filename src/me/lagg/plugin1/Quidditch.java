package me.lagg.plugin1;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Location;

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
	}

	@Override
	public void end() {
		
	}

	@Override
	public void updateScoreboardPreGame() {
		
	}

	@Override
	public void updateScoreboardPostGame() {
		
	}

}
