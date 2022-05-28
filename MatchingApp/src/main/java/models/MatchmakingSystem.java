package models;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.requireNonNullElseGet;
import static utils.StreamUtil.filterToList;

public class MatchmakingSystem {

    private final List<Individual> individuals;
    private final List<Match> matches;

    public MatchmakingSystem(List<Individual> individuals) {
        this.individuals = requireNonNullElseGet(individuals, ArrayList::new);
        this.matches = new ArrayList<>();
    }

    public void launch() {
        individuals.forEach(this::match);
    }

    private void match(Individual matcher) {
        matcher.match(filterToList(individuals, otherMatcher -> otherMatcher != matcher))
                .map(otherMatcher -> new Match(matcher, otherMatcher))
                .ifPresent(matches::add);
    }

    public void showAllMatch() {
        matches.forEach(System.out::println);
    }
}
