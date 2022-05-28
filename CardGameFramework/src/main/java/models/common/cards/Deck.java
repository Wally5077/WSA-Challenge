package models.common.cards;

import java.util.*;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.stream.Stream;

import static java.util.Arrays.asList;
import static java.util.Collections.emptyList;
import static java.util.Objects.requireNonNullElseGet;
import static utils.StreamUtil.flatMapToList;

public record Deck<Card>(LinkedList<Card> cards) implements Iterable<Card> {

    public Deck() {
        this(emptyList());
    }

    public Deck(Collection<Card> deck) {
        this(new LinkedList<>(requireNonNullElseGet(deck, Collections::emptyList)));
    }

    public static <Symbol extends Enum<Symbol>, Point extends Enum<Point>, Card extends GameCard<Symbol, Point>> Deck<Card> create(Class<Symbol> symbolClass, Class<Point> pointClass, BiFunction<Symbol, Point, Card> mapper) {
        var symbols = symbolClass.getEnumConstants();
        var points = pointClass.getEnumConstants();
        return new Deck<>(flatMapToList(symbols, symbol -> Stream.of(points).map(point -> mapper.apply(symbol, point))));
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card dealCard() {
        return cards.removeLast();
    }

    public void withdrawIfEmpty(Deck<Card> withdrawDeck) {
        if (isEmpty()) {
            withdrawCard(withdrawDeck.cards);
            withdrawDeck.clear();
        }
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    @SafeVarargs
    public final void withdrawCard(Card... cards) {
        withdrawCard(asList(cards));
    }

    public void withdrawCard(List<Card> cards) {
        Collections.shuffle(cards);
        cards.forEach(this.cards::addFirst);
    }

    private void clear() {
        cards.clear();
    }

    @Override
    public String toString() {
        return cards.toString();
    }

    @Override
    public Iterator<Card> iterator() {
        return cards.iterator();
    }

    public Stream<Card> stream() {
        return cards.stream();
    }

    public void forEach(Consumer<? super Card> action) {
        cards.forEach(action);
    }
}
