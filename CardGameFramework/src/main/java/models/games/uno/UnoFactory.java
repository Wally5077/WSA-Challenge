package models.games.uno;

import models.common.CardGameFactory;
import models.common.cards.Deck;
import models.common.io.CommandLine;
import models.common.io.ScannerCommandLineAdapter;
import models.games.uno.cards.Color;
import models.games.uno.cards.Number;
import models.games.uno.cards.UnoCard;
import models.games.uno.players.UnoAIPlayer;
import models.games.uno.players.UnoHumanPlayer;
import models.games.uno.players.UnoPlayer;

import java.util.List;
import java.util.Scanner;

import static models.common.cards.Deck.emptyDeck;
import static models.games.uno.Uno.MAX_PLAYER;
import static utils.StreamUtil.generate;

public class UnoFactory implements CardGameFactory<Uno> {

    @Override
    public Uno createWithHumanPlayer(int numberOfHumanPlayers) {
        CommandLine commandLine = new ScannerCommandLineAdapter(new Scanner(System.in));
        List<UnoPlayer> players = generate(numberOfHumanPlayers, () -> new UnoHumanPlayer(commandLine));
        players.addAll(generate(MAX_PLAYER - numberOfHumanPlayers, UnoAIPlayer::new));
        var deck = Deck.create(Color.class, Number.class, UnoCard::new);
        Uno uno = new Uno(players, deck, emptyDeck());
        players.forEach(player -> player.setUnoEventListener(uno));
        return uno;
    }
}
