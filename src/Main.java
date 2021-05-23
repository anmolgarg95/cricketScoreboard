import model.team.Team;
import printer.ScorePrinter;
import service.Match;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Number of players for each team");
        int noOfPlayers = scanner.nextInt();
        System.out.println("Number of overs");
        int noOfOver = scanner.nextInt();
        Team team1 = new Team("1");
        Team team2 = new Team("2");
        Match match = new Match(new ScorePrinter());

        match.setMaxOversAllowed(noOfOver);
        match.setPlayersInEachTeam(noOfPlayers);
        match.setBattingTeam(team1);
        match.setBowlingTeam(team2);
        match.startMatch();

        System.out.println("End");
    }
}
