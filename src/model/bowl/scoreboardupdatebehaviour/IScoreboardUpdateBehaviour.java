package model.bowl.scoreboardupdatebehaviour;

import model.player.Player;
import model.team.Team;

public interface IScoreboardUpdateBehaviour {
    void updateBattingScoreboard(int runs, Team battingTeam, Player batsman);
    void updateBowlingScoreboard(int runs, Team bowlingTeam, Player bowler);
}