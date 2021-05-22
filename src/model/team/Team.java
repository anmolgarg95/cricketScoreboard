package model.team;

import model.player.Player;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private final String name;
    private final List<Player> players = new ArrayList<Player>();
    private int playersSentToBat = 0;
    private final TeamBattingStats teamBattingStats = new TeamBattingStats();

    public Team(String name){
        this.name = name;
    }

    public void addPlayer(String name) {
        players.add(new Player(name));
    }

    public String getName(){
        return name;
    }

    public TeamBattingStats getTeamBattingStats() {
        return teamBattingStats;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Player getNextBatsman() {
        if (playersSentToBat >= players.size()) {
            return null;
        }
        playersSentToBat++;
        return players.get(playersSentToBat - 1);
    }
}
