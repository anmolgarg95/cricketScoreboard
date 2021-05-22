package model.player;

import model.bowl.Ball;

public class PlayerBattingStats {
    private int ballsPlayed = 0;
    private int runsScored = 0;
    private int fours = 0;
    private int sixes = 0;

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

    public void update(Ball ball) {
        runsScored += ball.getRuns();
        ballsPlayed++;
        if (ball.getRuns() == 4)
            fours++;
        if (ball.getRuns() == 6)
            sixes++;
    }
}

