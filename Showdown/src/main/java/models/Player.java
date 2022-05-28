package models;

import java.util.ArrayList;
import java.util.List;

import static utils.StreamUtil.generate;

public abstract class Player {

    private static final int MAX_HAND_AMOUNT = 13;
    private final Showdown showdown;
    private final ExchangeHands exchangeHands;
    protected String name;
    protected List<Card> hands;
    private int points;

    public Player(Showdown showdown) {
        this.showdown = showdown;
        this.hands = new ArrayList<>(MAX_HAND_AMOUNT);
        this.exchangeHands = new ExchangeHands(this);
    }

    public abstract void nameSelf();

    public void drawCard(Card card) {
        hands.add(card);
    }

    public ShowCard show() {
        switch (exchangeHands.state) {
            case NOT_EXCHANGE_YET -> exchangeHandsIfPrivilegeEnabled();
            case EXCHANGING -> exchangeHandsBackIfTimeExpired();
        }

        String handOptions = generate(hands.size(), number -> String.format("(%d) %s", number, hands.get(number - 1)), " ");
        System.out.printf("[系統] %s 請選擇欲出手牌 : %s\n", name, handOptions);

        ShowCard showCard = showCard();
        System.out.printf("[系統] %s 出了 %s\n", name, showCard.card);
        return showCard;
    }

    private void exchangeHandsIfPrivilegeEnabled() {
        System.out.printf("[系統] %s 是否使用特權 (Y/N) : ", name);
        if (decideWhetherToExchangeHands()) {
            List<Player> exchangeHandsPlayers = showdown.findExchangeHandsPlayers(this);

            if (exchangeHandsPlayers.isEmpty()) {
                System.out.println("[系統] 暫無可交換手牌對象");
            } else {
                String exchangeHandsPlayerOptions = generate(exchangeHandsPlayers.size(), number -> String.format("(%d) %s", number, exchangeHandsPlayers.get(number - 1).name), " ");
                System.out.printf("[系統] %s 請選擇交換手牌對象 : %s\n", name, exchangeHandsPlayerOptions);

                Player exchangeHandsPlayer = pickExchangeHandsPlayer(exchangeHandsPlayers);
                exchangeHands.exchangeWith(exchangeHandsPlayer);
                exchangeHands.increaseExpiryTime();
                System.out.printf("[系統] %s 特權時間還有 %d 回合\n", name, ExchangeHands.MAX_EXPIRY_TIME - exchangeHands.expiryTime);
            }
        }
    }

    protected abstract boolean decideWhetherToExchangeHands();

    protected abstract Player pickExchangeHandsPlayer(List<Player> exchangeHandsPlayers);

    private void exchangeHandsBackIfTimeExpired() {
        if (exchangeHands.isTimeExpired()) {
            System.out.printf("[系統] %s 特權時間已結束\n", name);
            exchangeHands.exchangeBack();
        } else {
            exchangeHands.increaseExpiryTime();
            if (exchangeHands.isTimeExpired()) {
                System.out.printf("[系統] %s 特權時間最後回合\n", name);
            } else {
                System.out.printf("[系統] %s 特權時間還有 %d 回合\n", name, ExchangeHands.MAX_EXPIRY_TIME - exchangeHands.expiryTime);
            }
        }
    }

    protected abstract ShowCard showCard();

    public void gainPoints(int winPoints) {
        points += winPoints;
    }

    public String getName() {
        return name;
    }

    public int getPoints() {
        return points;
    }

    public record ShowCard(Player owner, Card card) {
    }

    public static class ExchangeHands {
        private static final int MAX_EXPIRY_TIME = 3;
        private final Player self;
        private Player exchangeHandsPlayer;
        private State state;
        private int expiryTime;

        public ExchangeHands(Player player) {
            this.self = player;
            this.state = State.NOT_EXCHANGE_YET;
        }

        public void exchangeWith(Player exchangeHandsPlayer) {
            if (state == State.NOT_EXCHANGE_YET) {
                this.exchangeHandsPlayer = exchangeHandsPlayer;
                List<Card> myHands = self.hands;
                self.hands = exchangeHandsPlayer.hands;
                exchangeHandsPlayer.hands = myHands;
                state = State.EXCHANGING;
                System.out.printf("[系統] %s 與 %s 交換手牌\n", self.name, exchangeHandsPlayer.name);
            }
        }

        public void exchangeBack() {
            if (state == State.EXCHANGING) {
                List<Card> exchangePlayerHands = self.hands;
                self.hands = exchangeHandsPlayer.hands;
                exchangeHandsPlayer.hands = exchangePlayerHands;
                state = State.EXCHANGED;
                System.out.printf("[系統] %s 與 %s 換回手牌\n", self.name, exchangeHandsPlayer.name);
            }
        }

        private void increaseExpiryTime() {
            expiryTime++;
        }

        private boolean isTimeExpired() {
            return expiryTime == MAX_EXPIRY_TIME;
        }

        public enum State {
            NOT_EXCHANGE_YET, EXCHANGING, EXCHANGED
        }

    }
}
