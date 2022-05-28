package models.strategies;

import models.Habit;
import models.Individual;

import java.util.Comparator;
import java.util.List;

import static java.lang.Integer.compare;
import static utils.StreamUtil.intersectionCount;

public final class HabitBasedMatchmakingStrategy implements MatchmakingStrategy {

    private final Comparator<Long> habitComparator;

    public HabitBasedMatchmakingStrategy() {
        this(Long::compareTo);
    }

    public HabitBasedMatchmakingStrategy(Comparator<Long> habitComparator) {
        this.habitComparator = habitComparator;
    }

    @Override
    public int match(Individual matcher, Individual previousMatcher, Individual nextMatcher) {
        List<Habit> matcherHabits = matcher.getHabits();
        int res = habitComparator.compare(
                intersectionCount(matcherHabits, previousMatcher.getHabits()),
                intersectionCount(matcherHabits, nextMatcher.getHabits()));
        return res != 0 ? res : compare(previousMatcher.getId(), nextMatcher.getId());
    }
}
