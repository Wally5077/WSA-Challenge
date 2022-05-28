package models.games.showdown;

import models.common.CardGame;
import models.common.cards.Deck;
import models.common.cards.ShowCard;
import models.games.showdown.cards.PokerCard;
import models.games.showdown.players.PokerPlayer;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.max;
import static java.util.Comparator.comparing;
import static java.util.Comparator.comparingInt;
import static java.util.Optional.of;

public class Showdown extends CardGame<PokerPlayer, PokerCard> {

    public static final int MAX_PLAYER = 4;
    private static final int MAX_HAND_AMOUNT = 13;
    private static final int MAX_ROUND = 13;
    private static final int WIN_POINTS = 1;

    public Showdown(Collection<PokerPlayer> players, Deck<PokerCard> pokerCards) {
        super(players, pokerCards);
    }

    @Override
    protected int getNumberOfHandCards() {
        return MAX_HAND_AMOUNT;
    }

    @Override
    protected boolean isGameOver() {
        return getRound() >= MAX_ROUND;
    }

    @Override
    protected void handleShowCards(List<ShowCard<PokerPlayer, PokerCard>> showCards) {
        var playCard = max(showCards, comparing(ShowCard::card));
        var winner = playCard.owner();
        winner.gainPoints(WIN_POINTS);
        System.out.printf("[系統] 第 %d 回合最大卡牌 %s 的出牌者 %s 獲得了 %d 分，當前分數： %d 分\n", getRound(), playCard.card(), winner.getName(), WIN_POINTS, winner.getPoints());
    }

    @Override
    protected Optional<PokerPlayer> findWinner() {
        return of(max(getPlayers(), comparingInt(PokerPlayer::getPoints)
                .thenComparing(PokerPlayer::getName)));
    }
}
