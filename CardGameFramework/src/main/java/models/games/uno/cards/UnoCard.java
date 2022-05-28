package models.games.uno.cards;

import models.common.cards.GameCard;

import java.util.Objects;

/**
 * @author - wally55077@gmail.com
 */
public class UnoCard extends GameCard<Color, Number> implements Comparable<UnoCard> {

    public UnoCard(Color color, Number number) {
        super(color, number);
    }

    @Override
    public int compareTo(UnoCard card) {
        return 0;
    }

    public boolean isAvailableCard(UnoCard card) {
        return Objects.equals(symbol, card.symbol) || Objects.equals(point, card.point);
    }
}
