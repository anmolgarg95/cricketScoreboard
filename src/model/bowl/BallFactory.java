package model.bowl;

public class BallFactory {

    public Ball createBall(String input){
        switch (input){
            case "W":
                return new Ball(0, false, BallType.WICKET);
            case "Wd":
            case "Nb":
                return new Ball(1, false, BallType.EXTRAS);
            case "0":
                return new Ball(0, false, BallType.RUN);
            case "1":
                return new Ball(1, true, BallType.RUN);
            case "2":
                return new Ball(2, false, BallType.RUN);
            case "3":
                return new Ball(3, true, BallType.RUN);
            case "4":
                return new Ball(4, false, BallType.RUN);
            case "5":
                return new Ball(5, true, BallType.RUN);
            case "6":
                return new Ball(6, false, BallType.RUN);
        }
        return null;
    }
}
