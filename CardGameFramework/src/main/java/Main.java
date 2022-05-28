import models.common.CardGame;
import models.common.CardGameFactory;
import models.games.showdown.ShowdownFactory;
import models.games.uno.UnoFactory;

import static utils.StreamUtil.mapToList;

public class Main {
    public static void main(String[] args) {
        var gameFactories = new CardGameFactory[]{
                new ShowdownFactory(),
                new UnoFactory()
        };
        mapToList(gameFactories, CardGameFactory::create)
                .forEach(CardGame::start);
    }
}
