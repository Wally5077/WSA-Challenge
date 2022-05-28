package models;

import java.util.List;
import java.util.Random;

public class AIPlayer extends Player {

    private static final Random RANDOM = new Random();
    private static int sequence = 1;

    public AIPlayer(Showdown showdown) {
        super(showdown);
    }

    @Override
    public void nameSelf() {
        System.out.printf("[系統] 請玩家開始取名: %s\n", name = String.format("AI[%d]", sequence++));
    }

    @Override
    protected boolean decideWhetherToExchangeHands() {
        boolean enableExchangeHands = RANDOM.nextBoolean();
        System.out.println(enableExchangeHands ? "Y" : "N");
        return enableExchangeHands;
    }

    @Override
    protected Player pickExchangeHandsPlayer(List<Player> exchangeHandsPlayers) {
        int option = RANDOM.nextInt(exchangeHandsPlayers.size());
        Player exchangeHandsPlayer = exchangeHandsPlayers.get(option);
        System.out.printf("[系統] %s 選擇 (%d) %s\n", name, option + 1, exchangeHandsPlayer.name);
        return exchangeHandsPlayer;
    }

    @Override
    public ShowCard showCard() {
        return new ShowCard(this, hands.remove(RANDOM.nextInt(hands.size())));
    }
}
