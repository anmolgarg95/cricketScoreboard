package bowl.scoreboardupdatebehaviour;

import model.player.Player;
import model.player.PlayerBowlingStats;
import model.team.Team;
import model.team.TeamBattingStats;

public class ExtrasScoreboardUpdateBehaviour implements IScoreboardUpdateBehaviour{
    @Override
    public void updateBattingScoreboard(int runs, Team battingTeam, Player batsman) {
        updateTeamScoreboard(runs, battingTeam);
    }

    @Override
    public void updateBowlingScoreboard(int runs, Team bowlingTeam, Player bowler){
        PlayerBowlingStats playerBowlingStats = bowler.getPlayerBowlingStats();
        playerBowlingStats.increaseRunsConceded(runs);
    }

    private void updateTeamScoreboard(int runs, Team battingTeam){
        TeamBattingStats battingStats = battingTeam.getTeamBattingStats();
        battingStats.setTotalRunsScored(battingStats.getTotalRunsScored()+runs);
        battingStats.setExtras(battingStats.getExtras()+runs);
    }

}
