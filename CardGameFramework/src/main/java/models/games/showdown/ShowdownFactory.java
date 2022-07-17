package models.games.showdown;

import models.common.CardGameFactory;
import models.common.cards.Deck;
import models.common.io.CommandLine;
import models.common.io.ScannerCommandLineAdapter;
import models.games.showdown.cards.PokerCard;
import models.games.showdown.cards.Rank;
import models.games.showdown.cards.Suit;
import models.games.showdown.players.PokerAIPlayer;
import models.games.showdown.players.PokerHumanPlayer;
import models.games.showdown.players.PokerPlayer;

import java.util.List;
import java.util.Scanner;

import static models.games.showdown.Showdown.MAX_PLAYER;
import static utils.StreamUtil.generate;

public class ShowdownFactory implements CardGameFactory<Showdown> {

    @Override
    public Showdown createWithHumanPlayer(int numberOfHumanPlayers) {
        CommandLine commandLine = new ScannerCommandLineAdapter(new Scanner(System.in));
        List<PokerPlayer> players = generate(numberOfHumanPlayers, () -> new PokerHumanPlayer(commandLine));
        players.addAll(generate(MAX_PLAYER - numberOfHumanPlayers, PokerAIPlayer::new));
        var deck = Deck.create(Suit.class, Rank.class, PokerCard::new);
        return new Showdown(players, deck);
    }
}
