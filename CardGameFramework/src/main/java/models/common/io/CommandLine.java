package models.common.io;

/**
 * @author - wally55077@gmail.com
 */
public interface CommandLine {

    int DEFAULT_RANGE = 10;

    boolean nextBoolean();

    default int nextInt() {
        return nextInt(DEFAULT_RANGE);
    }

    int nextInt(int range);

    String nextLine();
}
