package models;

import java.util.Collections;
import java.util.Iterator;
import java.util.Stack;
import java.util.function.BiFunction;

import static java.util.Arrays.stream;
import static utils.StreamUtil.flatMapToStack;

public class Deck implements Iterable<Card> {

    private final Stack<Card> cards;

    private Deck(Stack<Card> cards) {
        this.cards = cards;
    }

    public static Deck create(BiFunction<Suit, Rank, Card> mapper) {
        return new Deck(flatMapToStack(Suit.values(), suit -> stream(Rank.values()).map(rank -> mapper.apply(suit, rank))));
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
