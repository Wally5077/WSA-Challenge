package models;

public record Match(Individual matcher, Individual otherMatcher) {
    @Override
    public String toString() {
        return "Match{" +
                "matcher=" + matcher +
                ", otherMatcher=" + otherMatcher +
                '}';
    }
}