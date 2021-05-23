package model.team;

import model.player.Player;

import java.util.ArrayList;
import java.util.List;

public class Team {
    private final String name;
    private final List<Player> battingOrder = new ArrayList<Player>();
    private final List<Player> bowlers = new ArrayList<Player>();
    private int playersSentToBat = 0;
    private final TeamBattingStats teamBattingStats = new TeamBattingStats();

    public Player getBowler(String bowlerName){
        for(Player bowler : bowlers){
            if(bowler.getName().equals(bowlerName))
                return bowler;
        }
        Player newBowler = new Player(bowlerName);
        bowlers.add(newBowler);
        return newBowler;
    }

    public Team(String name){
        this.name = name;
    }

    public void addBatsman(String name) {
        battingOrder.add(new Player(name));
    }

    public String getName(){
        return name;
    }

    public TeamBattingStats getTeamBattingStats() {
        return teamBattingStats;
    }

    public List<Player> getBattingOrder() {
        return battingOrder;
    }

    public List<Player> getBowlers() {
        return bowlers;
    }

    public Player getNextBatsman() {
        if (playersSentToBat >= battingOrder.size()) {
            return null;
        }
        playersSentToBat++;
        return battingOrder.get(playersSentToBat - 1);
    }
}
