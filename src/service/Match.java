package service;

import helper.Constants;
import model.bowl.Ball;
import model.bowl.BallFactory;
import model.bowl.BallType;
import model.player.Player;
import model.team.Team;
import printer.IPrinter;
import printer.ScorePrinter;

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
    private int currentInning = 1;
    private final IPrinter scorePrinter;

    public Match() {
        this.scorePrinter = new ScorePrinter(this);
    }

    public void startMatch() {
        while (currentInning <= 2) {
            startInning();
            changeInnings();
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
            battingTeam.addPlayer(playerName);
        }
        onStrikeBatsman = battingTeam.getNextBatsman();
        offStrikeBatsman = battingTeam.getNextBatsman();
        while (!isInningEnd()) {
            startNewOver();
            if (!isInningEnd()) {
                changeStrike();
            }
            scorePrinter.printBattingScoreCard();
        }
    }

    private void startNewOver() {
        System.out.println("Over " + (getOversBowled() + 1));
        int prevBallsPlayed = battingTeam.getTeamBattingStats().getBallsPlayed();
        int newBallsPlayed = battingTeam.getTeamBattingStats().getBallsPlayed();
        while ((newBallsPlayed - prevBallsPlayed) < Constants.NUMBER_OF_BALLS_IN_OVER && !isInningEnd()) {
            Ball ball = getNewBall();
            battingTeam.getTeamBattingStats().update(onStrikeBatsman, ball);
            if (ball.isStrikeChangingBowl()) {
                changeStrike();
            }
            if (BallType.WICKET.equals(ball.getBallType())) {
                onStrikeBatsman = battingTeam.getNextBatsman();
            }
            newBallsPlayed = battingTeam.getTeamBattingStats().getBallsPlayed();
        }
    }

    private void changeStrike() {
        Player temp = onStrikeBatsman;
        onStrikeBatsman = offStrikeBatsman;
        offStrikeBatsman = temp;
    }

    private void changeInnings() {
        currentInning++;
        Team temp = battingTeam;
        battingTeam = bowlingTeam;
        bowlingTeam = temp;
    }

    private boolean hasSecondBattingTeamWon() {
        if (currentInning < 2)
            return false;
        return battingTeam.getTeamBattingStats().getTotalRunsScored() > bowlingTeam.getTeamBattingStats().getTotalRunsScored();
    }

    private int getOversBowled() {
        return battingTeam.getTeamBattingStats().getBallsPlayed() / Constants.NUMBER_OF_BALLS_IN_OVER;
    }

    private boolean isInningEnd() {
        return (battingTeam.getTeamBattingStats().getWicketsFallen() >= playersInEachTeam - 1) ||
                getOversBowled() == maxOversAllowed ||
                hasSecondBattingTeamWon();
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

    /**
     * Public getters
     **/
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
        return currentInning;
    }
}
