package models.strategies;

import models.Individual;

import java.util.Comparator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

import static java.util.Comparator.naturalOrder;
import static java.util.Objects.requireNonNull;

public interface MatchmakingStrategy {

    static <T extends Comparable<? super T>> MatchmakingStrategy matching(
            Function<? super Individual, T> keyExtractor) {
        requireNonNull(keyExtractor);
        return matching(keyExtractor, naturalOrder());
    }

    static <T extends Comparable<? super T>> MatchmakingStrategy matching(
            Function<? super Individual, T> keyExtractor,
            Comparator<Integer> keyComparator) {
        requireNonNull(keyExtractor);
        requireNonNull(keyComparator);
        return (matcher, previousMatcher, nextMatcher) -> keyComparator.compare(
                keyExtractor.apply(matcher).compareTo(keyExtractor.apply(previousMatcher)),
                keyExtractor.apply(matcher).compareTo(keyExtractor.apply(nextMatcher)));
    }

    static <T, R extends Comparable<? super R>> MatchmakingStrategy matching(
            Function<? super Individual, T> keyExtractor,
            BiFunction<T, T, R> keyCalculator) {
        return matching(keyExtractor, keyCalculator, naturalOrder());
    }

    static <T, R extends Comparable<? super R>> MatchmakingStrategy matching(
            Function<? super Individual, T> keyExtractor,
            BiFunction<T, T, R> keyCalculator,
            Comparator<? super R> keyComparator) {
        requireNonNull(keyExtractor);
        requireNonNull(keyCalculator);
        requireNonNull(keyComparator);
        return (matcher, previousMatcher, nextMatcher) -> keyComparator.compare(
                keyCalculator.apply(keyExtractor.apply(matcher), keyExtractor.apply(previousMatcher)),
                keyCalculator.apply(keyExtractor.apply(matcher), keyExtractor.apply(nextMatcher)));
    }

    default Individual match(Individual matcher, List<Individual> otherMatchers) {
        otherMatchers.sort((m1, m2) -> match(matcher, m1, m2));
        return findBestMatch(otherMatchers);
    }

    int match(Individual matcher, Individual previousMatcher, Individual nextMatcher);

    default Individual findBestMatch(List<Individual> otherMatchers) {
        return otherMatchers.isEmpty() ? null : otherMatchers.get(0);
    }

    default <T extends Comparable<? super T>> MatchmakingStrategy thenMatching(Function<? super Individual, T> keyExtractor) {
        return thenMatching(keyExtractor, naturalOrder());
    }

    default <T extends Comparable<? super T>> MatchmakingStrategy thenMatching(
            Function<? super Individual, T> keyExtractor,
            Comparator<? super T> keyComparator) {
        return thenMatching((matcher, previousMatcher, nextMatcher) ->
                keyComparator.compare(keyExtractor.apply(previousMatcher), keyExtractor.apply(nextMatcher)));
    }

    default MatchmakingStrategy thenMatching(MatchmakingStrategy other) {
        requireNonNull(other);
        return (matcher, previousMatcher, nextMatcher) -> {
            int res = match(matcher, previousMatcher, nextMatcher);
            return (res != 0) ? res : other.match(matcher, previousMatcher, nextMatcher);
        };
    }
}
