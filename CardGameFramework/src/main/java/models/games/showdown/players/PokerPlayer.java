package models.games.showdown.players;

import models.common.CardGamePlayer;
import models.games.showdown.cards.PokerCard;

public abstract class PokerPlayer extends CardGamePlayer<PokerCard> {

    private int points;

    public void gainPoints(int winPoints) {
        points += winPoints;
    }

    public int getPoints() {
        return points;
    }

    @Override
    public PokerCard show() {
        return removeHand(playCard(getHands()));
    }

}
