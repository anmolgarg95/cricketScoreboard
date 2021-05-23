package service;

import constants.Constants;
import model.bowl.Ball;
import model.bowl.BallFactory;
import model.player.Player;
import model.team.Team;
import printer.IPrinter;
import java.util.Scanner;

public class Match {
    private final BallFactory ballFactory = new BallFactory();
    private final Scanner scanner = new Scanner(System.in);
    private int maxOversAllowed;
    private int playersInEachTeam;
    private Team battingTeam;
    private Team bowlingTeam;
    private Player onStrikeBatsman;
    private Player offStrikeBatsman;
    private Player bowler;
    private int currentInning = 1;
    private final IPrinter scorePrinter;

    private final MatchHelper matchHelper;

    public Match(IPrinter printer) {
        this.scorePrinter = printer;
        this.scorePrinter.setMatch(this);
        matchHelper = new MatchHelper(this);
    }

    public void startMatch() {
        while (currentInning <= 2) {
            startInning();
            this.matchHelper.changeInnings();
        }
        scorePrinter.printMatchResult();
    }

    /**
     * Private helpers
     **/
    private void startInning() {
        System.out.println("Batting order for team" + battingTeam.getName());
        for (int i = 0; i < playersInEachTeam; ++i) {
            String playerName = scanner.nextLine();
            battingTeam.addBatsman(playerName);
        }
        onStrikeBatsman = battingTeam.getNextBatsman();
        offStrikeBatsman = battingTeam.getNextBatsman();
        while (!matchHelper.isInningEnd()) {
            startNewOver();
            if (!matchHelper.isInningEnd()) {
                this.matchHelper.changeStrike();
            }
            scorePrinter.printBattingScoreCard();
            scorePrinter.printBowlingScoreCard();
        }
    }

    private void startNewOver() {
        System.out.println("\nOver " + (matchHelper.getOversCompleted() + 1));
        System.out.println("Bowlers Name:");
        String bowlerName = scanner.nextLine();
        bowler = bowlingTeam.getBowler(bowlerName);
        System.out.println("Over Input");
        int prevBallsPlayed = battingTeam.getTeamBattingStats().getBallsPlayed();
        int newBallsPlayed = battingTeam.getTeamBattingStats().getBallsPlayed();
        while ((newBallsPlayed - prevBallsPlayed) < Constants.NUMBER_OF_BALLS_IN_OVER
                && !matchHelper.isInningEnd()) {
            Ball ball = getNewBall();
            ball.updateBattingScoreboard(battingTeam, onStrikeBatsman);
            ball.updateBowlingScoreboard(bowlingTeam, bowler);
            if (ball.isStrikeChangingBowl()) {
                this.matchHelper.changeStrike();
            }
            if (ball.isSendNextBatsman()) {
                onStrikeBatsman = battingTeam.getNextBatsman();
            }
            newBallsPlayed = battingTeam.getTeamBattingStats().getBallsPlayed();
        }
    }

    private Ball getNewBall() {
        Ball ball;
        while (true) {
            String bowlInput = scanner.nextLine();
            ball = ballFactory.createBall(bowlInput);
            if (ball == null) {
                System.out.println("Invalid input, Try again:");
            } else {
                return ball;
            }
        }
    }

    /**
     * Public setters
     **/
    public void setMaxOversAllowed(int maxOversAllowed) {
        this.maxOversAllowed = maxOversAllowed;
    }

    public void setPlayersInEachTeam(int playersInEachTeam) {
        this.playersInEachTeam = playersInEachTeam;
    }

    public void setBattingTeam(Team battingTeam) {
        this.battingTeam = battingTeam;
    }

    public void setBowlingTeam(Team bowlingTeam) {
        this.bowlingTeam = bowlingTeam;
    }

    public void setOnStrikeBatsman(Player onStrikeBatsman) {
        this.onStrikeBatsman = onStrikeBatsman;
    }

    public void setOffStrikeBatsman(Player offStrikeBatsman) {
        this.offStrikeBatsman = offStrikeBatsman;
    }

    public void setCurrentInning(int currentInning) {
        this.currentInning = currentInning;
    }

    /**
     * Public getters
     **/
    public int getMaxOversAllowed() {
        return this.maxOversAllowed;
    }

    public int getPlayersInEachTeam() {
        return playersInEachTeam;
    }

    public Team getBattingTeam() {
        return battingTeam;
    }

    public Team getBowlingTeam() {
        return bowlingTeam;
    }

    public Player getOnStrikeBatsman() {
        return onStrikeBatsman;
    }

    public Player getOffStrikeBatsman() {
        return offStrikeBatsman;
    }

    public int getCurrentInning() {
        return this.currentInning;
    }

    public MatchHelper getMatchHelper() {
        return matchHelper;
    }

}
