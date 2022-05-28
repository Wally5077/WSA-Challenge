package models.games.showdown.players;

import models.common.io.CommandLine;
import models.games.showdown.cards.PokerCard;

import java.util.List;

public class PokerHumanPlayer extends PokerPlayer {
    private final CommandLine input;

    public PokerHumanPlayer(CommandLine input) {
        this.input = input;
    }

    @Override
    public String nameSelf() {
        return name = input.nextLine();
    }

    @Override
    protected PokerCard playCard(List<PokerCard> availableHands) {
        return availableHands.get(input.nextInt(availableHands.size()) - 1);
    }
}
