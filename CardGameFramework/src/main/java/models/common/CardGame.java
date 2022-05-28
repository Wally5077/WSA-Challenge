package models.common;

import models.common.cards.Deck;
import models.common.cards.ShowCard;

import java.util.*;

import static java.util.List.copyOf;
import static java.util.Objects.requireNonNullElseGet;
import static utils.StreamUtil.mapToList;

public abstract class CardGame<Player extends CardGamePlayer<Card>, Card extends Comparable<Card>> {

    protected final Deck<Card> deck;
    private final List<Player> players;
    private int round;

    protected CardGame(Collection<Player> players, Deck<Card> deck) {
        this.players = new ArrayList<>(requireNonNullElseGet(players, Collections::emptyList));
        this.deck = requireNonNullElseGet(deck, Deck::new);
    }

    public void start() {
        System.out.printf("[系統] %s 開始\n", getClass().getSimpleName());
        setupCardGame();
        drawCards();
        startNewRound();
        announceWinner();
    }

    private void setupCardGame() {
        players.forEach(player -> System.out.printf("[系統] 請玩家開始取名: %s\n", player.nameSelf()));
        System.out.println("[系統] 牌推開始洗牌");
        deck.shuffle();
    }

    protected void drawCards() {
        System.out.println("[系統] 玩家開始輪流抽牌");
        for (int drawCardTime = 0; drawCardTime < getNumberOfHandCards(); drawCardTime++) {
            players.forEach(player -> player.drawCard(deck.dealCard()));
        }
    }

    protected abstract int getNumberOfHandCards();

    private void startNewRound() {
        while (!isGameOver()) {
            System.out.printf("[系統] 第 %d 回合開始\n", ++round);
            System.out.println("[系統] 玩家開始輪流出牌");
            handleShowCards(mapToList(players, this::takeTurn));
        }
    }

    protected void handleShowCards(List<ShowCard<Player, Card>> cards) {
        // do something when overrode the method
    }

    protected ShowCard<Player, Card> takeTurn(Player player) {
        var card = player.show();
        System.out.printf("[系統] %s 出了 %s\n", player.getName(), card);
        return ShowCard.create(player, card);
    }

    protected abstract boolean isGameOver();

    private void announceWinner() {
        findWinner().ifPresent(winner -> System.out.printf("[系統] 遊戲結束，獲勝者 %s\n", winner.getName()));
    }

    protected abstract Optional<Player> findWinner();

    protected List<Player> getPlayers() {
        return copyOf(players);
    }

    protected int getRound() {
        return round;
    }
}
