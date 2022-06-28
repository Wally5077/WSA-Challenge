package models.games.uno;

import models.common.CardGame;
import models.common.CardGamePlayer;
import models.common.cards.Deck;
import models.common.cards.ShowCard;
import models.games.uno.cards.UnoCard;
import models.games.uno.players.UnoPlayer;
import models.games.uno.players.UnoPlayer.UnoEventListener;

import java.util.Collection;
import java.util.Optional;

import static java.util.Optional.ofNullable;
import static models.common.cards.ShowCard.create;
import static utils.StreamUtil.isEmpty;

/**
 * @author - wally55077@gmail.com
 */
public class Uno extends CardGame<UnoPlayer, UnoCard> implements UnoEventListener {

    public static final int MAX_PLAYER = 4;
    private static final int MAX_HAND_AMOUNT = 5;
    private final Deck<UnoCard> discardDeck;
    private ShowCard<UnoPlayer, UnoCard> topPlay;

    public Uno(Collection<UnoPlayer> players, Deck<UnoCard> unoCards, Deck<UnoCard> discardDeck) {
        super(players, unoCards);
        this.discardDeck = discardDeck;
    }

    @Override
    protected void drawCards() {
        super.drawCards();
        notifyPlayerTopPlayChanged(create(getDeck()::dealCard));
    }

    @Override
    protected int getNumberOfHandCards() {
        return MAX_HAND_AMOUNT;
    }

    @Override
    protected boolean isGameOver() {
        return isEmpty(getPlayers(), CardGamePlayer::getHands);
    }

    @Override
    protected ShowCard<UnoPlayer, UnoCard> takeTurn(UnoPlayer player) {
        var topPlay = super.takeTurn(player);
        notifyPlayerTopPlayChanged(topPlay);
        return topPlay;
    }

    private void notifyPlayerTopPlayChanged(ShowCard<UnoPlayer, UnoCard> topPlay) {
        this.topPlay = topPlay;
        var card = topPlay.card();
        discardDeck.withdrawCard(card);
        getPlayers().forEach(p -> p.setTopPlay(card));
    }

    @Override
    protected Optional<UnoPlayer> findWinner() {
        return ofNullable(topPlay).map(ShowCard::owner);
    }

    @Override
    public void onUnavailableHands(CardGamePlayer<UnoCard> player) {
        var deck = getDeck();
        deck.withdrawIfEmpty(discardDeck);
        player.drawCard(deck.dealCard());
    }
}
