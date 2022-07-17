package models;

/**
 * @author - wally55077@gmail.com
 */
@FunctionalInterface
public interface Subscriber {
    void receive(Video video);
}
