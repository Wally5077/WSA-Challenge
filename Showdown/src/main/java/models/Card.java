package models;

public record Card(Suit suit, Rank rank) implements Comparable<Card> {

    @Override
    public int compareTo(Card card) {
        return rank == card.rank ? suit.compareTo(card.suit) : rank.compareTo(card.rank);
    }

    @Override
    public String toString() {
        return String.format("%s%s", suit, rank);
    }
}
