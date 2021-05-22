package model.bowl;

public class Ball {
    private final int runs;
    private final boolean isStrikeChangingBowl;
    private final BallType ballType;

    public Ball(int runs, boolean isStrikeChangingBowl, BallType ballType){
        this.runs = runs;
        this.isStrikeChangingBowl = isStrikeChangingBowl;
        this.ballType = ballType;
    }

    /**
     * Public getters
     **/
    public int getRuns() {
        return runs;
    }

    public boolean isStrikeChangingBowl() {
        return isStrikeChangingBowl;
    }

    public BallType getBallType() {
        return ballType;
    }

}

