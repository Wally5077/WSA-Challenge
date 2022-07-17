package models;

import java.util.Collection;
import java.util.LinkedList;

import static java.util.Objects.requireNonNull;
import static java.util.concurrent.TimeUnit.MINUTES;
import static java.util.concurrent.TimeUnit.SECONDS;

/**
 * @author - wally55077@gmail.com
 */
public class Video {

    private final String title;
    private final String description;
    private long length;
    private Channel channel;
    private final Collection<Like> likes;

    public Video(String title, String description) {
        this.title = title;
        this.description = description;
        this.likes = new LinkedList<>();
    }

    public void setChannel(Channel channel) {
        this.channel = requireNonNull(channel);
    }

    public void like(Like like) {
        // 水球 對影片 "C1M1S2" 按讚。
        System.out.printf("%s 對影片 \"%s\" 按讚。%n", like.user(), this);
        this.likes.add(requireNonNull(like));
    }

    public Video withMinutes(long minutes) {
        length = MINUTES.toSeconds(minutes);
        return this;
    }

    public Video witSeconds(long seconds) {
        length = SECONDS.toSeconds(seconds);
        return this;
    }

    public long toMinutes() {
        return SECONDS.toMinutes(getLength());
    }

    public long getLength() {
        return length;
    }

    public Channel getChannel() {
        return channel;
    }

    @Override
    public String toString() {
        return title;
    }
}
