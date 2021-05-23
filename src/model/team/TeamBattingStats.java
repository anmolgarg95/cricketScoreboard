package model.team;

public class TeamBattingStats {
    private int totalRunsScored = 0;
    private int wicketsFallen = 0;
    private int ballsPlayed = 0;
    private int extras = 0;

    /**
     * Public setters
     **/
    public void setTotalRunsScored(int totalRunsScored) {
        this.totalRunsScored = totalRunsScored;
    }

    public void setWicketsFallen(int wicketsFallen) {
        this.wicketsFallen = wicketsFallen;
    }

    public void setBallsPlayed(int ballsPlayed) {
        this.ballsPlayed = ballsPlayed;
    }

    public void setExtras(int extras) {
        this.extras = extras;
    }


    /**
     * Public getters
     **/
    public int getTotalRunsScored() {
        return totalRunsScored;
    }

    public int getWicketsFallen() {
        return wicketsFallen;
    }

    public int getBallsPlayed() {
        return ballsPlayed;
    }

    public int getExtras() {
        return extras;
    }
}
