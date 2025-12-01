import utility.AoCDownloader;

import java.io.IOException;
import java.lang.reflect.Method;
import java.net.URISyntaxException;

/**
 * This class solves AdventofCode
 *
 * @author Florin Buffet
 * @version V1.3
 */
public class Main {
    private Main() {
    }

    /**
     * Main method to run the challenges.
     *
     * @param args command line arguments
     * @throws IOException if the file is not found
     * @throws URISyntaxException if the URI is invalid
     */
    @SuppressWarnings({"MagicNumber", "DuplicateStringLiteralInspection"})
    public static void main(String[] args) throws IOException, URISyntaxException {

        // Please change the year and day to the desired challenge
        int year = 2025;
        int day = 1;

        if (AoCDownloader.doesFileExistOrIsDownloaded(year, day, true)) {
            System.out.println("Calculating the result for the challenge...");
            String filePath = "data/AoC_" + year + "_" + String.format("%02d", day) + ".txt";
            //noinspection StringConcatenationMissingWhitespace
            String className = "AoC" + year + String.format("%02d", day);

            //noinspection OverlyBroadCatchBlock
            try {
                //noinspection StringConcatenationMissingWhitespace
                Class<?> usedClass = Class.forName("year" + year + "." + className);
                Method partOneMethod = usedClass.getMethod("partOne", String.class);
                System.out.println(year + " Day " + day + ", Part 1: " + partOneMethod.invoke(null, filePath));

                //noinspection ConstantValue
                if (day != 25) {
                    Method partTwoMethod = usedClass.getMethod("partTwo", String.class);
                    System.out.println(year + " Day " + day + ", Part 2: " + partTwoMethod.invoke(null, filePath));
                }
            } catch (Exception e) {
                //noinspection CallToPrintStackTrace
                e.printStackTrace();
            }
        } else {
            System.out.println("The software exits now.");
        }
    }
}