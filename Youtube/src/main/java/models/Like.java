package models;

/**
 * @author - wally55077@gmail.com
 */
public record Like(User user) {

    public static Like like(User user) {
        return new Like(user);
    }
}
