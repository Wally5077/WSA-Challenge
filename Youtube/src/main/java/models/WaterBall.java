package models;

import static java.util.Objects.requireNonNull;
import static models.Like.like;

/**
 * @author - wally55077@gmail.com
 */
public class WaterBall extends User {

    public WaterBall(String name) {
        super(name);
    }

    @Override
    public void receive(Video video) {
        requireNonNull(video);
        if (video.toMinutes() >= 3) {
            video.like(like(this));
        }
    }
}
