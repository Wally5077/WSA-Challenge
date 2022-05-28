package models.games.uno.players;

import models.common.io.CommandLine;
import models.games.uno.cards.UnoCard;

import java.util.List;

/**
 * @author - wally55077@gmail.com
 */
public class UnoHumanPlayer extends UnoPlayer {
    private final CommandLine input;

    public UnoHumanPlayer(CommandLine input) {
        this.input = input;
    }

    @Override
    public String nameSelf() {
        return name = input.nextLine();
    }

    @Override
    protected UnoCard playCard(List<UnoCard> availableHands) {
        return availableHands.get(input.nextInt(availableHands.size()) - 1);
    }
}
