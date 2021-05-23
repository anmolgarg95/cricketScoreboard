package printer;

import model.player.Player;
import model.player.PlayerBattingStats;
import model.player.PlayerBowlingStats;
import model.team.Team;
import model.team.TeamBattingStats;
import constants.Constants;
import service.Match;

import java.util.List;

public class ScorePrinter implements IPrinter {
    private Match match;

    @Override
    public void setMatch(Match match) {
        this.match = match;
    }

    @Override
    public void printMatchResult() {
        Team firstBattingTeam = match.getBattingTeam();
        Team secondBattingTeam = match.getBowlingTeam();
        int batting1Score = firstBattingTeam.getTeamBattingStats().getTotalRunsScored();
        int batting2Score = secondBattingTeam.getTeamBattingStats().getTotalRunsScored();
        if (batting1Score > batting2Score) {
            System.out.println("\nTeam1 won the match by " + (batting1Score - batting2Score) + " runs.");
        } else if (batting2Score > batting1Score) {
            System.out.println("\nTeam2 won the match by " + (match.getPlayersInEachTeam() - secondBattingTeam.getTeamBattingStats().getWicketsFallen() - 1) + " wickets.");
        } else {
            System.out.println("\nMatch drawn.");
        }
    }

    @Override
    public void printBowlingScoreCard() {
        Team bowlingTeam = match.getBowlingTeam();
        List<Player> bowlers = bowlingTeam.getBowlers();
        System.out.println("\nBowling Scorecard for team " + bowlingTeam.getName());
        System.out.printf("%-15s%-15s%-15s%-15s\n", "Bowler", "Runs Conceded", "Overs", "Wickets Taken");
        for (Player bowler : bowlers) {
            PlayerBowlingStats playerBowlingStats = bowler.getPlayerBowlingStats();
            System.out.printf("%-15s%-15d%-15s%-15d\n",
                    bowler.getName(),
                    playerBowlingStats.getRunsConceded(),
                    convertToOvers(playerBowlingStats.getBallsBowled()),
                    playerBowlingStats.getWicketsTaken());
        }
    }

    @Override
    public void printBattingScoreCard() {
        Team battingTeam = match.getBattingTeam();
        Player onStrikeBatsman = match.getOnStrikeBatsman();
        Player offStrikeBatsman = match.getOffStrikeBatsman();
        List<Player> players = battingTeam.getBattingOrder();
        TeamBattingStats teamBattingStats = battingTeam.getTeamBattingStats();
        System.out.println("Scorecard for team " + battingTeam.getName());
        System.out.printf("%-15s%-15s%-15s%-15s%-15s%-15s\n", "PlayerName", "Score", "4s", "6s", "Balls", "Strike Rate");
        for (int i = 0; i < players.size(); ++i) {
            Player player = players.get(i);
            PlayerBattingStats playerBattingStats = player.getBattingStats();
            System.out.printf("%-15s%-15d%-15d%-15d%-15d%-15d\n",
                    getBatsmanName(player, onStrikeBatsman, offStrikeBatsman),
                    playerBattingStats.getRunsScored(),
                    playerBattingStats.getFours(),
                    playerBattingStats.getSixes(),
                    playerBattingStats.getBallsPlayed(),
                    playerBattingStats.getStrikeRate());
        }
        System.out.println("Total: " + teamBattingStats.getTotalRunsScored() + "/" + teamBattingStats.getWicketsFallen());
        int ballsPlayed = teamBattingStats.getBallsPlayed();
        System.out.println("Overs: " + convertToOvers(ballsPlayed));
        System.out.println("Extras: " + teamBattingStats.getExtras());
    }

    private String getBatsmanName(Player currentBatsman, Player batsman1, Player batsman2) {
        String name = currentBatsman.getName();
        if (batsman1 == currentBatsman) {
            name = name + "**";
        } else if (batsman2 == currentBatsman) {
            name = name + "*";
        }
        return name;
    }

    private String convertToOvers(int balls) {
        return (balls / Constants.NUMBER_OF_BALLS_IN_OVER) + "." + (balls % Constants.NUMBER_OF_BALLS_IN_OVER);
    }

}
