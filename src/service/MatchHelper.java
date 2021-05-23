package service;

import constants.Constants;
import model.player.Player;
import model.team.Team;

public class MatchHelper {
    private final Match match;

    public MatchHelper(Match match) {
        this.match = match;
    }

    public void changeStrike() {
        Player temp = match.getOnStrikeBatsman();
        match.setOnStrikeBatsman(match.getOffStrikeBatsman());
        match.setOffStrikeBatsman(temp);
    }

    public void changeInnings() {
        match.setCurrentInning(match.getCurrentInning() + 1);
        Team temp = match.getBattingTeam();
        match.setBattingTeam(match.getBowlingTeam());
        match.setBowlingTeam(temp);
    }

    public boolean isInningEnd() {
        return (match.getBattingTeam().getTeamBattingStats().getWicketsFallen() >=
                match.getPlayersInEachTeam() - 1) ||
                getOversCompleted() == match.getMaxOversAllowed() ||
                hasSecondBattingTeamWon();
    }

    public int getOversCompleted() {
        return match.getBattingTeam().getTeamBattingStats().getBallsPlayed() /
                Constants.NUMBER_OF_BALLS_IN_OVER;
    }

    private boolean hasSecondBattingTeamWon() {
        if (match.getCurrentInning() < 2)
            return false;
        return match.getBattingTeam().getTeamBattingStats().getTotalRunsScored() >
                match.getBowlingTeam().getTeamBattingStats().getTotalRunsScored();
    }
}
