package models;

import java.util.Collections;
import java.util.Iterator;
import java.util.Stack;

import static java.util.Arrays.stream;
import static utils.StreamUtil.flatMapToStack;

public class Deck implements Iterable<Card> {

    private final Stack<Card> cards;

    public Deck() {
        this.cards = flatMapToStack(Suit.values(), suit -> stream(Rank.values()).map(rank -> new Card(suit, rank)));
    }

    public void shuffle() {
        System.out.println("[系統] 牌推開始洗牌");
        Collections.shuffle(cards);
    }

    public Card dealCard() {
        return cards.pop();
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    @Override
    public Iterator<Card> iterator() {
        return cards.iterator();
    }
}
