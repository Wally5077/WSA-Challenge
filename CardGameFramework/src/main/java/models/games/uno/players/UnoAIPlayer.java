package models.games.uno.players;

import models.games.uno.cards.UnoCard;

import java.util.List;
import java.util.Random;

/**
 * @author - wally55077@gmail.com
 */
public class UnoAIPlayer extends UnoPlayer {

    private static final Random RANDOM = new Random();

    private final int id;

    public UnoAIPlayer(int id) {
        this.id = id;
    }

    @Override
    public String nameSelf() {
        return name = String.format("AI[%d]", id);
    }

    @Override
    protected UnoCard playCard(List<UnoCard> availableHands) {
        return availableHands.get(RANDOM.nextInt(availableHands.size()));
    }
}
