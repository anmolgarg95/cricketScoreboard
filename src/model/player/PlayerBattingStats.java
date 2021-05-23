package model.player;

public class PlayerBattingStats {
    private int ballsPlayed = 0;
    private int runsScored = 0;
    private int fours = 0;
    private int sixes = 0;

    /**
     * Public setters
     **/
    public void increaseBallsPlayed() {
        this.ballsPlayed += 1;
    }

    public void increaseRunsScored(int runsScored) {
        this.runsScored += runsScored;
    }

    public void increaseFours() {
        this.fours += 1;
    }

    public void increaseSixes() {
        this.sixes += 1;
    }

    /**
     * Public getters
     **/
    public int getRunsScored() {
        return runsScored;
    }

    public int getBallsPlayed() {
        return ballsPlayed;
    }

    public int getFours() {
        return fours;
    }

    public int getSixes() {
        return sixes;
    }

    public int getStrikeRate(){
        if(ballsPlayed==0)
            return 0;
        return runsScored*100/ballsPlayed;
    }
}

