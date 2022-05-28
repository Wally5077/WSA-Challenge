package models.common.io;

import java.util.Scanner;

/**
 * @author - wally55077@gmail.com
 */
public record ScannerCommandLineAdapter(Scanner input) implements CommandLine {

    @Override
    public boolean nextBoolean() {
        return input.nextBoolean();
    }

    @Override
    public int nextInt(int range) {
        return input.nextInt(range);
    }

    @Override
    public String nextLine() {
        return input.nextLine();
    }
}
