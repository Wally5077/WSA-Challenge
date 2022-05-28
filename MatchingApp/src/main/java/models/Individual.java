package models;

import models.strategies.MatchmakingStrategy;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.requireNonNull;
import static java.util.Objects.requireNonNullElseGet;
import static java.util.Optional.ofNullable;
import static utils.StreamUtil.mapToList;

public class Individual {

    private final List<Habit> habits = new ArrayList<>();
    private int id;
    private Gender gender;
    private int age;
    private String intro;
    private Coord coord;

    private MatchmakingStrategy matchmakingStrategy;

    public Individual(int id, Gender gender, int age, String habits, Coord coord, MatchmakingStrategy matchmakingStrategy) {
        this(id, gender, age, String.format("I'm %d", id), habits, coord, matchmakingStrategy);
    }

    public Individual(int id, Gender gender, int age, String intro, String habits, Coord coord, MatchmakingStrategy matchmakingStrategy) {
        setId(id);
        setGender(gender);
        setAge(age);
        setIntro(intro);
        addHabits(habits);
        setCoord(coord);
        this.matchmakingStrategy = matchmakingStrategy;
    }

    public Optional<Individual> match(List<Individual> otherMatchers) {
        return ofNullable(matchmakingStrategy.match(this, otherMatchers));
    }

    public void setMatchmakingStrategy(MatchmakingStrategy matchmakingStrategy) {
        this.matchmakingStrategy = matchmakingStrategy;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Individual's id must over than 0");
        }
        this.id = id;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = requireNonNull(gender, "Individual must have gender");
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if (age < 18) {
            throw new IllegalArgumentException("Individual's age must over than 18");
        }
        this.age = age;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = requireNonNullElseGet(intro, String::new);
        if (this.intro.isBlank() || this.intro.length() > 200) {
            throw new IllegalArgumentException("Individual's intro must be in the 0 character to 200 characters");
        }
    }

    public void addHabits(String habits) {
        addHabits(mapToList(requireNonNullElseGet(habits, String::new).split(","), Habit::new));
    }

    public void addHabits(List<Habit> habits) {
        this.habits.addAll(habits);
    }

    public List<Habit> getHabits() {
        return habits;
    }

    public void setCoord(int x, int y) {
        setCoord(new Coord(x, y));
    }

    public Coord getCoord() {
        return coord;
    }

    public void setCoord(Coord coord) {
        this.coord = requireNonNull(coord, "Individual must have coord");
    }

    @Override
    public String toString() {
        return "Individual{" +
                "intro='" + intro + '\'' +
                ", habits=" + habits +
                ", coord=" + coord +
                '}';
    }
}
