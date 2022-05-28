package models.common.cards;

import models.common.CardGamePlayer;

import java.util.function.Supplier;

public record ShowCard<Player extends CardGamePlayer<Card>, Card extends Comparable<Card>>(Player owner, Card card) {

    public static <Player extends CardGamePlayer<Card>, Card extends Comparable<Card>> ShowCard<Player, Card> create(Supplier<Card> cardSupplier) {
        return create(null, cardSupplier.get());
    }

    public static <Player extends CardGamePlayer<Card>, Card extends Comparable<Card>> ShowCard<Player, Card> create(Player player, Card card) {
        return new ShowCard<>(player, card);
    }
}
