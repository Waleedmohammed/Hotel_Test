package hotel.qa.test.helper;

import org.apache.commons.lang3.RandomStringUtils;

import java.util.List;
import java.util.stream.Collectors;

public class TestHelpers {

    /**
     * Generate random numeric string with given length
     * Can be used for amount generation, so usage of '0' is restricted to avoid leading 0
     * <p/>
     *
     * @param length Length of generated string
     * @return Random string
     */
    public static String getRandomNumeric(int length) {
        return RandomStringUtils.random(length, "123456789");
    }



    /**
     * Filter Given List<String> with substring after given word
     * Can be used for returning back room number given its image alt
     * <p/>
     *
     * @param inputStrings List of string before substring
     * @param substringAfter the Word which substring is needed after
     * @return List<String> with filtered data
     */
    public static List<String> getFilteredList(List<String> inputStrings, String substringAfter) {

        return inputStrings.stream()
                .map(str -> {
                    int index = str.indexOf(substringAfter);
                    if (index != -1) {
                        return str.substring(index + substringAfter.length()).trim();
                    } else {
                        return null;
                    }
                })
                .filter(substring -> substring != null && !substring.isEmpty())
                .collect(Collectors.toList());
    }


}
