package models.games.showdown.players;

import models.games.showdown.cards.PokerCard;

import java.util.List;
import java.util.Random;

public class PokerAIPlayer extends PokerPlayer {

    private static final Random RANDOM = new Random();

    private final int id;

    public PokerAIPlayer(int id) {
        this.id = id;
    }

    @Override
    public String nameSelf() {
        return name = String.format("AI[%d]", id);
    }

    @Override
    protected PokerCard playCard(List<PokerCard> availableHands) {
        return availableHands.get(RANDOM.nextInt(availableHands.size()));
    }

}
