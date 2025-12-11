import utility.AoCDownloader;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;

/**
 * This class solves Advent of Code
 *
 * @author Florin Buffet
 */
public class Main {

    private static final int YEAR = 2025;
    private static final int DAY = 11;

    /**
     * Private constructor to prevent instantiation.
     */
    private Main() {
    }

    /**
     * Main method to run the challenges.
     *
     * @param args command line arguments
     * @throws IOException        if the file is not found
     * @throws URISyntaxException if the URI is invalid
     */
    public static void main(String[] args) throws IOException, URISyntaxException {

        if (AoCDownloader.doesFileExistOrIsDownloaded(YEAR, DAY, true)) {
            System.out.println("Calculating the result for the challenge...");
            String filePath = "data/AoC_" + YEAR + "_" + String.format("%02d", DAY) + ".txt";
            String className = "AoC" + YEAR + String.format("%02d", DAY);

            try {
                Class<?> usedClass = Class.forName("year" + YEAR + "." + className);
                Method partOneMethod = usedClass.getMethod("partOne", String.class);
                System.out.println(YEAR + " Day " + DAY + ", Part 1: " + partOneMethod.invoke(null, filePath));

                Method partTwoMethod = usedClass.getMethod("partTwo", String.class);
                System.out.println(YEAR + " Day " + DAY + ", Part 2: " + partTwoMethod.invoke(null, filePath));
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("The software exits now.");
        }
    }
}