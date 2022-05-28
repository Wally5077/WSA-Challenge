package models;

import java.util.Objects;

import static java.util.Objects.requireNonNull;

public class Habit {

    private String name;

    public Habit(String name) {
        setName(name);
    }

    private void setName(String name) {
        this.name = requireNonNull(name, "Habit must name");
        if (this.name.isBlank() || this.name.length() > 10) {
            throw new IllegalArgumentException("Individual's intro must be in the 0 character to 10 characters");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Habit habit = (Habit) o;
        return Objects.equals(name, habit.name);
    }

}
