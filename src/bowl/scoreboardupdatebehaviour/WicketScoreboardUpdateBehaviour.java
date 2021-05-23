package bowl.scoreboardupdatebehaviour;

import model.player.Player;
import model.player.PlayerBattingStats;
import model.player.PlayerBowlingStats;
import model.team.Team;
import model.team.TeamBattingStats;

public class WicketScoreboardUpdateBehaviour implements IScoreboardUpdateBehaviour{

    @Override
    public void updateBattingScoreboard(int runs, Team battingTeam, Player batsman) {
        updateTeamScoreboard(runs, battingTeam);
        updatePlayerScoreboard(runs, batsman);
    }

    @Override
    public void updateBowlingScoreboard(int runs, Team bowlingTeam, Player bowler){
        PlayerBowlingStats playerBowlingStats = bowler.getPlayerBowlingStats();
        playerBowlingStats.increaseBowlsBowled();
        playerBowlingStats.increaseWicketsTaken();
    }

    private void updateTeamScoreboard(int runs, Team battingTeam){
        TeamBattingStats battingStats = battingTeam.getTeamBattingStats();
        battingStats.setWicketsFallen(battingStats.getWicketsFallen()+1);
        battingStats.setBallsPlayed(battingStats.getBallsPlayed()+1);
    }

    private void updatePlayerScoreboard(int runs, Player player){
        PlayerBattingStats battingStats = player.getBattingStats();
        battingStats.increaseBallsPlayed();
    }
}
