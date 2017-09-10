package me.lagg.plugin1;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.UUID;
import javax.swing.Timer;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scoreboard.Scoreboard;

import net.md_5.bungee.api.ChatColor;

/**
 * @author Ddude88888
 *
 */
public abstract class Game implements ActionListener, Listener {
	static ArrayList<Game> games = new ArrayList<Game>();
	UUID gameID = UUID.randomUUID();
	ArrayList<Player> players = new ArrayList<Player>();
	Location waitingRoom;
	EnumGamePhase phase = EnumGamePhase.PREGAME;
	long startTime;
	Scoreboard scoreboard;
	int[] gameStartWarningTimes = {1,2,3,4,5,10,15};
	int gameStartWarningTimeIterator = -1;
	int minimumPlayersForStart;
	int maxPlayers;
	Timer timer;
	String CHATPREFIX;
	
	public static Game getGameByPlayer(Player p) {
		for(Game g : games) {
			if(g.players.contains(p)) {
				return g;
			}
		}
		return null;
	}
	
	public static Game getGameFromUUID(UUID gameUUID) {
		for(Game g : games) {
			if(gameUUID.equals(g.gameID)) {
				return g;
			}
		}
		return null;
	}
	
	public synchronized void addPlayer(Player p) throws GameFullException {
		if(players.size()>=maxPlayers) {
			System.out.println(players.size() + ":" + maxPlayers);
			throw new GameFullException();
		} else {
			players.add(p);
			p.teleport(waitingRoom);
		}
	}
	
	public void removePlayer(Player p) {
		players.remove(p);
	}
	
	public void startPregame() {
		System.out.println("starting pregame");
		Bukkit.getPluginManager().registerEvents(this, Plugin1.instance);
		games.add(this);
		timer = new Timer(20,this);
		timer.start();
		System.out.println("pregame started");
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		if(phase == EnumGamePhase.PREGAME) {
			preGameTick();
			updateScoreboardPreGame();
		} else if (phase == EnumGamePhase.INGAME) {
			inGameTick();
		} else if(phase == EnumGamePhase.POSTGAME) {
			postGameTick();
			updateScoreboardPostGame();
		}
		
	}
	
	public void preGameTick() {
		if(players.size()<minimumPlayersForStart) {
			startTime = 0L;
			gameStartWarningTimeIterator = -1;
		} else if (gameStartWarningTimeIterator==-1){
			startTime = System.currentTimeMillis() + (long)(gameStartWarningTimes[gameStartWarningTimes.length-1]*1000);
			gameStartWarningTimeIterator = 0;
		}
		if(gameStartWarningTimeIterator>-1 && gameStartWarningTimeIterator<gameStartWarningTimes.length) {
			long nextWarning = startTime - (long)(gameStartWarningTimes[gameStartWarningTimes.length-1-gameStartWarningTimeIterator]*1000);
			if(System.currentTimeMillis()>=nextWarning) {
				for(Player p : players) {
					p.sendMessage(CHATPREFIX + ChatColor.RED + "Game starting in " + ChatColor.YELLOW + gameStartWarningTimes[gameStartWarningTimes.length-1-gameStartWarningTimeIterator] + ChatColor.RED + " seconds!");
				}
				gameStartWarningTimeIterator++;
			}
		}
		if(System.currentTimeMillis()>=startTime) {
			start();
		}
	}
	
	public abstract void updateScoreboardPreGame();
	
	public abstract void updateScoreboardPostGame();
	
	public abstract void inGameTick();
	
	public abstract void postGameTick();
	
	public abstract void start();
	
	public abstract void end();

}
