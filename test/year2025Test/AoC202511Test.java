package year2025Test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import utility.AoCDownloader;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.stream.Stream;

/**
 * This class contains unit tests for Advent of Code.
 * It tests both parts of the challenge using sample and personal inputs.
 *
 * @author Florin Buffet
 */
@SuppressWarnings("StringConcatenationMissingWhitespace")
class AoC202511Test {

    // set these constants to the day's input
    private static final int YEAR = 2025;
    private static final int DAY = 11;
    private static final int PERSONAL_RESULT_PART1 = 683;
    private static final long PERSONAL_RESULT_PART2 = 2347225200L;

    // Creates the path to the sample files
    private static final String PATH_SAMPLE = "test/year" + YEAR + "Test/samples/AoC_" + YEAR + "_" + String.format("%02d", DAY) + "_";
    private static final String PATH_PERSONAL = "data/AoC_" + YEAR + "_" + String.format("%02d", DAY) + ".txt";
    private static final String ENDING = ".txt";

    // Creates the error message
    private static final String ERROR_MESSAGE_PART1 = "AoC " + YEAR + ", Day " + DAY + ", Part 1, Sample ";
    private static final String ERROR_MESSAGE_PART2 = "AoC " + YEAR + ", Day " + DAY + ", Part 2, Sample ";
    private static final String ERROR_MESSAGE_ENDING = " failed";
    private static final String ERROR_MESSAGE_PART1_PERSONAL = "AoC " + YEAR + ", Day " + DAY + ", Part 1, Personal failed";
    private static final String ERROR_MESSAGE_PART2_PERSONAL = "AoC " + YEAR + ", Day " + DAY + ", Part 2, Personal failed";
    private static final String ERROR_MESSAGE_FILE_NOT_FOUND = "AoC " + YEAR + ", Day " + DAY + ", Personal file not found";
    private static final String ERROR_MESSAGE_PERSONAL_DISABLED = "Personal tests are disabled";

    // Creates the methods
    private static Method partOneMethod;
    private static Method partTwoMethod;
    private static Properties properties;

    /**
     * Sets up the test environment by initializing the methods for part one and part two.
     * This method is executed once before all tests.
     */
    @BeforeAll
    static void setUp() {
        try {
            Class<?> usedClass = Class.forName("year" + YEAR + ".AoC" + YEAR + String.format("%02d", DAY));
            partOneMethod = usedClass.getMethod("partOne", String.class);
            partTwoMethod = usedClass.getMethod("partTwo", String.class);
            properties = new Properties();
            InputStream stream = Thread.currentThread().getContextClassLoader().getResourceAsStream("AoC.properties");
            properties.load(stream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Tests part one of the solution with sample inputs.
     */
    @Test
    void partOneSampleA() throws Exception {
        Assertions.assertEquals(5, partOneMethod.invoke(null, PATH_SAMPLE + "a" + ENDING), ERROR_MESSAGE_PART1 + "a" + ERROR_MESSAGE_ENDING);
    }

    /**
     * Tests part one of the solution with the personal input.
     *
     * @throws Exception if an error occurs during method invocation
     */
    @Test
    void partOnePersonal() throws Exception {
        Assumptions.assumeTrue("true".equals(properties.getProperty("TEST_FOR_PERSONAL_INPUT")), ERROR_MESSAGE_PERSONAL_DISABLED);
        Assumptions.assumeTrue(AoCDownloader.doesFileExistOrIsDownloaded(YEAR, DAY, false), ERROR_MESSAGE_FILE_NOT_FOUND);
        Assertions.assertEquals(PERSONAL_RESULT_PART1, partOneMethod.invoke(null, PATH_PERSONAL), ERROR_MESSAGE_PART1_PERSONAL);
    }

    /**
     * Tests part two of the solution with sample inputs.
     */
    @Test
    void partTwoSampleB() throws Exception {
        Assertions.assertEquals(2, partTwoMethod.invoke(null, PATH_SAMPLE + "b" + ENDING), ERROR_MESSAGE_PART2 + "b" + ERROR_MESSAGE_ENDING);
    }

    /**
     * Tests part two of the solution with the personal input.
     *
     * @throws Exception if an error occurs during method invocation
     */
    @Test
    void partTwoPersonal() throws Exception {
        Assumptions.assumeTrue("true".equals(properties.getProperty("TEST_FOR_PERSONAL_INPUT")), ERROR_MESSAGE_PERSONAL_DISABLED);
        Assumptions.assumeTrue(AoCDownloader.doesFileExistOrIsDownloaded(YEAR, DAY, false), ERROR_MESSAGE_FILE_NOT_FOUND);
        Assertions.assertEquals(PERSONAL_RESULT_PART2, partTwoMethod.invoke(null, PATH_PERSONAL), ERROR_MESSAGE_PART2_PERSONAL);
    }
}