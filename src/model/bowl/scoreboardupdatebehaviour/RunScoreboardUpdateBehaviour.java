package model.bowl.scoreboardupdatebehaviour;

import model.player.Player;
import model.player.PlayerBattingStats;
import model.player.PlayerBowlingStats;
import model.team.Team;
import model.team.TeamBattingStats;

public class RunScoreboardUpdateBehaviour implements IScoreboardUpdateBehaviour {

    @Override
    public void updateBattingScoreboard(int runs, Team battingTeam, Player batsman) {
        updateTeamScoreboard(runs, battingTeam);
        updatePlayerScoreboard(runs, batsman);
    }

    @Override
    public void updateBowlingScoreboard(int runs, Team bowlingTeam, Player bowler){
        PlayerBowlingStats playerBowlingStats = bowler.getPlayerBowlingStats();
        playerBowlingStats.increaseRunsConceded(runs);
        playerBowlingStats.increaseBowlsBowled();
    }

    private void updateTeamScoreboard(int runs, Team battingTeam) {
        TeamBattingStats battingStats = battingTeam.getTeamBattingStats();
        battingStats.setTotalRunsScored(battingStats.getTotalRunsScored() + runs);
        battingStats.setBallsPlayed(battingStats.getBallsPlayed() + 1);
    }

    private void updatePlayerScoreboard(int runs, Player player) {
        PlayerBattingStats battingStats = player.getBattingStats();
        battingStats.increaseRunsScored(runs);
        battingStats.increaseBallsPlayed();
        if (runs == 4) {
            battingStats.increaseFours();
        }
        if (runs == 6) {
            battingStats.increaseSixes();
        }
    }
}
