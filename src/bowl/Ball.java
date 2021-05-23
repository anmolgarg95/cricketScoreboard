package bowl;

import bowl.scoreboardupdatebehaviour.IScoreboardUpdateBehaviour;
import model.player.Player;
import model.team.Team;

public class Ball {
    private final int runs;
    private final boolean isStrikeChangingBowl;
    private final boolean sendNextBatsman;
    private final IScoreboardUpdateBehaviour  scoreboardUpdateBehaviour;

    public Ball(int runs, boolean isStrikeChangingBowl, boolean sendNextBatsman,
                IScoreboardUpdateBehaviour scoreboardUpdateBehaviour){
        this.runs = runs;
        this.isStrikeChangingBowl = isStrikeChangingBowl;
        this.sendNextBatsman = sendNextBatsman;
        this.scoreboardUpdateBehaviour = scoreboardUpdateBehaviour;
    }

    /**
     * Public getters
     **/
    public int getRuns() {
        return runs;
    }

    public boolean isStrikeChangingBowl() {
        return isStrikeChangingBowl;
    }

    public boolean isSendNextBatsman() {
        return sendNextBatsman;
    }

    public void updateBattingScoreboard(Team battingTeam, Player batsman) {
        scoreboardUpdateBehaviour.updateBattingScoreboard(getRuns(), battingTeam, batsman);
    }

    public void updateBowlingScoreboard(Team bowlingTeam, Player bowler) {
        scoreboardUpdateBehaviour.updateBowlingScoreboard(getRuns(), bowlingTeam, bowler);
    }

}

