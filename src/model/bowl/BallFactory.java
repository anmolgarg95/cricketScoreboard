package model.bowl;

import model.bowl.scoreboardupdatebehaviour.ExtrasScoreboardUpdateBehaviour;
import model.bowl.scoreboardupdatebehaviour.RunScoreboardUpdateBehaviour;
import model.bowl.scoreboardupdatebehaviour.WicketScoreboardUpdateBehaviour;

public class BallFactory {

    public Ball createBall(String input) {
        switch (input) {
            case "W":
                return new Ball(0, false, true, new WicketScoreboardUpdateBehaviour());
            case "Wd":
            case "Nb":
                return new Ball(1, false, false, new ExtrasScoreboardUpdateBehaviour());
            case "1":
            case "3":
            case "5":
                return new Ball(Integer.parseInt(input), true, false, new RunScoreboardUpdateBehaviour());
            case "0":
            case "2":
            case "4":
            case "6":
                return new Ball(Integer.parseInt(input), false, false, new RunScoreboardUpdateBehaviour());
        }
        return null;
    }

}
