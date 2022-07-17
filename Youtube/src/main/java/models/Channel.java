package models;

import java.util.Collection;
import java.util.LinkedList;

import static java.util.Objects.requireNonNull;

/**
 * @author - wally55077@gmail.com
 */
public class Channel {

    private final String name;
    private final Collection<Subscriber> subscribers;
    private final Collection<Video> videos;

    public Channel(String name) {
        this.name = requireNonNull(name);
        this.subscribers = new LinkedList<>();
        this.videos = new LinkedList<>();
    }

    public void subscribe(Subscriber subscriber) {
        this.subscribers.add(subscriber);
        System.out.printf("%s 訂閱了 %s。%n", subscriber, this);
    }

    public void unsubscribe(Subscriber subscriber) {
        this.subscribers.remove(subscriber);
        System.out.printf("%s 解除訂閱了 %s。%n", subscriber, this);
    }

    public void upload(Video video) {
        requireNonNull(video);
        video.setChannel(this);
        this.videos.add(video);
        System.out.printf("頻道 %s 上架了一則新影片 \"%s\"。%n", this, video);
        subscribers.forEach(subscriber -> subscriber.receive(video));
    }

    @Override
    public String toString() {
        return name;
    }
}
