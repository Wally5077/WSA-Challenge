package models;

import java.util.List;
import java.util.Scanner;

public class HumanPlayer extends Player {

    private final Scanner input;

    public HumanPlayer(Showdown showdown, Scanner input) {
        super(showdown);
        this.input = input;
    }

    @Override
    public void nameSelf() {
        System.out.print("[系統] 請玩家開始取名: ");
        name = input.nextLine();
    }

    @Override
    protected boolean decideWhetherToExchangeHands() {
        String enableExchangeHands = input.next();
        return "Y".equalsIgnoreCase(enableExchangeHands);
    }

    @Override
    protected Player pickExchangeHandsPlayer(List<Player> exchangeHandsPlayers) {
        System.out.printf("[系統] %s 選擇 (", name);
        int option = input.nextInt(exchangeHandsPlayers.size()) - 1;
        Player exchangeHandsPlayer = exchangeHandsPlayers.get(option);
        System.out.printf(") %s\n", exchangeHandsPlayer.name);
        return exchangeHandsPlayer;
    }

    @Override
    public ShowCard showCard() {
        return new ShowCard(this, hands.remove(input.nextInt(hands.size()) - 1));
    }
}
