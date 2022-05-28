package models.games.showdown.cards;

import models.common.cards.GameCard;

public class PokerCard extends GameCard<Suit, Rank> implements Comparable<PokerCard> {

    public PokerCard(Suit suit, Rank rank) {
        super(suit, rank);
    }

    @Override
    public int compareTo(PokerCard card) {
        return point == card.point ? symbol.compareTo(card.symbol) : point.compareTo(card.point);
    }
}
