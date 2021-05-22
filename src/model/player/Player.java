package model.player;

public class Player {
    private String name;
    private PlayerBattingStats playerBattingStats;

    public Player(String name){
        this.name = name;
        this.playerBattingStats = new PlayerBattingStats();
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

}
