package models.strategies;

import models.Coord;
import models.Individual;

import java.util.Comparator;

import static java.lang.Integer.compare;
import static java.util.Objects.requireNonNullElseGet;

public final class DistanceBasedMatchmakingStrategy implements MatchmakingStrategy {

    private final Comparator<Integer> coordComparator;

    public DistanceBasedMatchmakingStrategy() {
        this(Integer::compareTo);
    }

    public DistanceBasedMatchmakingStrategy(Comparator<Integer> coordComparator) {
        this.coordComparator = requireNonNullElseGet(coordComparator, Comparator::naturalOrder);
    }

    @Override
    public int match(Individual matcher, Individual previousMatcher, Individual nextMatcher) {
        Coord matcherCoord = matcher.getCoord();
        int res = coordComparator.compare(
                matcherCoord.compareTo(previousMatcher.getCoord()),
                matcherCoord.compareTo(nextMatcher.getCoord()));
        return res != 0 ? res : compare(previousMatcher.getId(), nextMatcher.getId());
    }
}
