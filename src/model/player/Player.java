package model.player;

public class Player {
    private final String name;
    private final PlayerBattingStats playerBattingStats;
    private final PlayerBowlingStats playerBowlingStats;

    public Player(String name){
        this.name = name;
        this.playerBattingStats = new PlayerBattingStats();
        this.playerBowlingStats = new PlayerBowlingStats();
    }

    /**
     * Public getters
     **/
    public String getName() {
        return name;
    }

    public PlayerBattingStats getBattingStats() {
        return playerBattingStats;
    }

    public PlayerBowlingStats getPlayerBowlingStats() {
        return playerBowlingStats;
    }

}
