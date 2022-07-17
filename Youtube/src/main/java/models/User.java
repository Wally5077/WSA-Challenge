package models;

import static java.util.Arrays.stream;
import static java.util.Objects.requireNonNull;

/**
 * @author - wally55077@gmail.com
 */
public abstract class User implements Subscriber {

    private final String name;

    public User(String name) {
        this.name = requireNonNull(name);
    }

    public void subscribe(Channel... channels) {
        stream(channels).forEach(channel -> channel.subscribe(this));
    }

    @Override
    public String toString() {
        return name;
    }
}
