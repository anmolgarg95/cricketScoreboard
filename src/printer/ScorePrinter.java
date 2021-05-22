package printer;

import model.player.Player;
import model.player.PlayerBattingStats;
import model.team.Team;
import model.team.TeamBattingStats;
import helper.Constants;
import service.Match;

import java.util.List;

public class ScorePrinter implements IPrinter{
    private final Match match;
    public ScorePrinter(Match match){
        this.match = match;
    }

    @Override
    public void printMatchResult(){
        Team firstBattingTeam = match.getBattingTeam();
        Team secondBattingTeam = match.getBowlingTeam();
        int batting1Score = firstBattingTeam.getTeamBattingStats().getTotalRunsScored();
        int batting2Score = secondBattingTeam.getTeamBattingStats().getTotalRunsScored();
        if(batting1Score>batting2Score){
            System.out.println("Team1 won the match by "+ (batting1Score-batting2Score) + " runs.");
        } else if(batting2Score>batting1Score){
            System.out.println("Team2 won the match by "+ (match.getPlayersInEachTeam() - secondBattingTeam.getTeamBattingStats().getWicketsFallen()-1) + " wickets.");
        }
        else {
            System.out.println("service.Match drawn.");
        }
    }

    @Override
    public void printBattingScoreCard() {
        Team battingTeam = match.getBattingTeam();
        Player onStrikeBatsman = match.getOnStrikeBatsman();
        Player offStrikeBatsman = match.getOffStrikeBatsman();
        List<Player> players = battingTeam.getPlayers();
        TeamBattingStats teamBattingStats = battingTeam.getTeamBattingStats();
        System.out.println("Scorecard for team"+match.getCurrentInning());
        System.out.printf("%-22s%-22s%-22s%-22s%-22s%-22s\n","PlayerName","Score","4s","6s","Balls", "Strike Rate");
        for (int i = 0; i < players.size(); ++i) {
            Player player = players.get(i);
            PlayerBattingStats playerBattingStats = player.getBattingStats();
            System.out.printf("%-22s%-22d%-22d%-22d%-22d%-22d\n",
                    getBatsmanName(player, onStrikeBatsman, offStrikeBatsman),
                    playerBattingStats.getRunsScored(),
                    playerBattingStats.getFours(),
                    playerBattingStats.getSixes(),
                    playerBattingStats.getBallsPlayed(),
                    playerBattingStats.getStrikeRate());
        }
        System.out.println("Total: " + teamBattingStats.getTotalRunsScored() + "/" + teamBattingStats.getWicketsFallen());
        int ballsPlayed = teamBattingStats.getBallsPlayed();
        System.out.println("Overs: " + ballsPlayed/ Constants.NUMBER_OF_BALLS_IN_OVER + "." + ballsPlayed% Constants.NUMBER_OF_BALLS_IN_OVER);
        System.out.println("Extras: " + teamBattingStats.getExtras());
    }

    private String getBatsmanName(Player currentBatsman, Player batsman1, Player batsman2) {
        String name = currentBatsman.getName();
        //TODO: Better comparision
        if (batsman1!=null && currentBatsman.getName().equalsIgnoreCase(batsman1.getName())){
            name = name + "**";
        } else if (batsman2!=null && currentBatsman.getName().equalsIgnoreCase(batsman2.getName())){
            name = name + "*";
        }
        return name;
    }

}
