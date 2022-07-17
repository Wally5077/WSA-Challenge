package models;

import models.Player.ShowCard;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.util.Collections.max;
import static java.util.Comparator.comparing;
import static java.util.Comparator.comparingInt;
import static java.util.function.UnaryOperator.identity;
import static java.util.stream.Collectors.toList;
import static java.util.stream.IntStream.rangeClosed;
import static java.util.stream.Stream.iterate;
import static utils.StreamUtil.generate;

public class Showdown {

    private static final int MAX_PLAYER = 4;
    private static final int MAX_ROUND = 13;
    private static final int WIN_POINTS = 1;
    private final Scanner input;
    private final List<Player> players;
    private final Deck deck;

    public Showdown(InputStream in) {
        this.input = new Scanner(in);
        this.players = new ArrayList<>(MAX_PLAYER);
        this.deck = Deck.create(Card::new);
    }

    public void start() {
        prepare();
        drawCards();
        rangeClosed(1, MAX_ROUND).forEach(this::startNewRound);
        announceWinner();
    }

    private void prepare() {
        createPlayers();
        deck.shuffle();
    }

    private void createPlayers() {
        System.out.printf("[系統] 請輸入人類玩家數量 (最多 %d 人) : ", MAX_PLAYER);
        int humanPlayerAmount = input.nextInt(MAX_PLAYER);
        players.addAll(generate(humanPlayerAmount, () -> new HumanPlayer(this, input)));
        players.addAll(generate(MAX_PLAYER - humanPlayerAmount, () -> new AIPlayer(this)));
        players.forEach(Player::nameSelf);
    }

    private void drawCards() {
        System.out.println("[系統] 玩家開始輪流抽牌");
        iterate(deck, deck -> !deck.isEmpty(), identity())
                .forEach(deck -> players.forEach(player -> player.drawCard(deck.dealCard())));
    }

    private void startNewRound(int round) {
        System.out.printf("[系統] 第 %d 回合開始\n", round);
        System.out.println("[系統] 玩家開始輪流出牌");
        players.stream()
                .map(Player::show)
                .max(comparing(ShowCard::card))
                .ifPresent(this::announceWinningResult);
    }

    private void announceWinningResult(ShowCard showCard) {
        Player winner = showCard.owner();
        winner.gainPoints(WIN_POINTS);
        System.out.printf("[系統] 此回合出了最大卡牌 %s 的獲勝者 %s 獲得了 %d 分\n", showCard.card(), winner.getName(), WIN_POINTS);
    }

    private void announceWinner() {
        Player winner = max(players, comparingInt(Player::getPoints).thenComparing(Player::getName));
        System.out.printf("[系統] 遊戲結束，獲勝者 %s 總共獲得了 %d 分\n", winner.getName(), winner.getPoints());
    }

    public List<Player> findExchangeHandsPlayers(Player player) {
        return players.stream()
                .filter(exchangeHandsPlayer -> exchangeHandsPlayer != player)
                .filter(exchangeHandsPlayer -> exchangeHandsPlayer.hands.size() == player.hands.size())
                .collect(toList());
    }
}
