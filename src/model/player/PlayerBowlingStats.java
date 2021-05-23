package model.player;

public class PlayerBowlingStats {
    private int ballsBowled = 0;
    private int runsConceded = 0;
    private int wicketsTaken = 0;

    public int getRunsConceded() {
        return runsConceded;
    }

    public int getBallsBowled() {
        return ballsBowled;
    }

    public int getWicketsTaken() {
        return wicketsTaken;
    }

    public void increaseBowlsBowled(){
        ballsBowled+=1;
    }

    public void increaseRunsConceded(int runs){
        runsConceded+=runs;
    }

    public void increaseWicketsTaken(){
        wicketsTaken+=1;
    }

}

