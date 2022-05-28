package models.common;

import models.common.cards.GameCard;

public interface CardGameFactory<Game extends CardGame<? extends CardGamePlayer<? extends GameCard<? extends Enum<?>, ? extends Enum<?>>>, ? extends GameCard<? extends Enum<?>, ? extends Enum<?>>>> {

    default Game create() {
        return createWithHumanPlayer(0);
    }

    Game createWithHumanPlayer(int numberOfHumanPlayers);
}
