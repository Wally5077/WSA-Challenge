package models.games.uno.players;

import models.common.CardGamePlayer;
import models.games.uno.cards.UnoCard;

import java.util.List;

import static utils.StreamUtil.filterToList;

public abstract class UnoPlayer extends CardGamePlayer<UnoCard> {

    protected UnoCard topPlay;
    private UnoEventListener unoEventListener;

    @Override
    public UnoCard show() {
        var availableHands = findAvailableHands();
        if (availableHands.isEmpty()) {
            unoEventListener.onUnavailableHands(this);
            return show();
        }
        return removeHand(playCard(availableHands));
    }

    private List<UnoCard> findAvailableHands() {
        return filterToList(getHands(), hand -> hand.isAvailableCard(topPlay));
    }

    public void setTopPlay(UnoCard topPlay) {
        this.topPlay = topPlay;
    }

    public void setUnoEventListener(UnoEventListener unoEventListener) {
        this.unoEventListener = unoEventListener;
    }

    public interface UnoEventListener {
        void onUnavailableHands(CardGamePlayer<UnoCard> player);
    }
}
