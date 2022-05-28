package models.common.cards;

import java.util.Objects;

public abstract class GameCard<Symbol extends Enum<Symbol>, Point extends Enum<Point>> {

    protected final Symbol symbol;
    protected final Point point;

    public GameCard(Symbol symbol, Point point) {
        this.symbol = symbol;
        this.point = point;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        var gameCard = (GameCard<?, ?>) o;
        if (!Objects.equals(symbol, gameCard.symbol)) {
            return false;
        }
        return Objects.equals(point, gameCard.point);
    }

    @Override
    public int hashCode() {
        int result = symbol != null ? symbol.hashCode() : 0;
        return 31 * result + (point != null ? point.hashCode() : 0);
    }

    @Override
    public String toString() {
        return String.format("[%s]%s", symbol, point);
    }
}
