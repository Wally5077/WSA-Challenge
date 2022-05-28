package models.common;

import java.util.ArrayList;
import java.util.List;

import static java.util.List.copyOf;

public abstract class CardGamePlayer<Card extends Comparable<Card>> {

    private final List<Card> hands = new ArrayList<>();
    protected String name;

    public abstract String nameSelf();

    public void drawCard(Card card) {
        hands.add(card);
    }

    public abstract Card show();

    protected abstract Card playCard(List<Card> availableHands);

    protected Card removeHand(Card card) {
        hands.removeIf(hand -> hand.equals(card));
        return card;
    }

    public String getName() {
        return name;
    }

    public List<Card> getHands() {
        return copyOf(hands);
    }

    @Override
    public String toString() {
        return name;
    }
}
