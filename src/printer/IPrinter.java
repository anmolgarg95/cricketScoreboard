package printer;

import service.Match;

public interface IPrinter {
    void setMatch(Match match);
    void printMatchResult();
    void printBattingScoreCard();
    void printBowlingScoreCard();
}
