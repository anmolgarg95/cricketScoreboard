package model.team;

import model.bowl.Ball;
import model.bowl.BallType;
import model.player.Player;

public class TeamBattingStats {
    private int totalRunsScored = 0;
    private int wicketsFallen = 0;
    private int ballsPlayed = 0;
    private int extras = 0;

    public void update(Player onStrikeBatsman, Ball ball){
        totalRunsScored += ball.getRuns();
        if(BallType.EXTRAS.equals(ball.getBallType())){
            extras+= ball.getRuns();
            return;
        }
        if(BallType.WICKET.equals(ball.getBallType())){
            wicketsFallen+=1;
        }
        ballsPlayed++;
        onStrikeBatsman.getBattingStats().update(ball);
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
