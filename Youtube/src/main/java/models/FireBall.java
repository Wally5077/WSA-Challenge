package models;

import static java.util.Objects.requireNonNull;

/**
 * @author - wally55077@gmail.com
 */
public class FireBall extends User {

    public FireBall(String name) {
        super(name);
    }

    @Override
    public void receive(Video video) {
        requireNonNull(video);
        if (video.toMinutes() <= 1) {
            video.getChannel().unsubscribe(this);
        }
    }
}
