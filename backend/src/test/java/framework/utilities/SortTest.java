package framework.utilities;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Sorter tests:")
public class SortTest {

    private static Stream<Arguments> CompareStrings() {
        return Stream.of(
                Arguments.of("Abc", "Abc", "Bcd"),
                Arguments.of("Aleksander", "Alexander", "Aleksander"),
                Arguments.of("Mathias", "Mathias", "Mattias"),
                Arguments.of("Maria", "Maria", "Marie")
        );
    }
    private static Stream<Arguments> CompareStringsDescending() {
        return Stream.of(
                Arguments.of("Bcd", "Abc", "Bcd"),
                Arguments.of("Alexander", "Alexander", "Aleksander"),
                Arguments.of("Mattias", "Mathias", "Mattias"),
                Arguments.of("Marie", "Maria", "Marie")
        );
    }
    private static Stream<Arguments> CompareWeight() {
        return Stream.of(
                Arguments.of(2.55, 2.55, 3.8),
                Arguments.of(105, 105, 200),
                Arguments.of(225.55, 225.55, 555.5),
                Arguments.of(11.2, 11.2, 25.77)
        );
    }
    private static Stream<Arguments> CompareWeightDescending() {
        return Stream.of(
                Arguments.of(3.8, 2.55, 3.8),
                Arguments.of(200, 105, 200),
                Arguments.of(555.5, 225.55, 555.5),
                Arguments.of(25.77, 11.2, 25.77)
        );
    }

    @Test
    @DisplayName("Simple Test: Sorts ascending alphabetically and by length")
    void sortAsc(){
        assertEquals("Abc", Sort.sortByLengthAndAlphabetically("Abc","Bcd", true));
    }
    @ParameterizedTest
    @DisplayName("Parameterized Test: Sorts ascending alphabetically and by length")
    @MethodSource("CompareStrings")
    void sortParamAsc(String expected, String input1, String input2){
        assertEquals(expected, Sort.sortByLengthAndAlphabetically(input1,input2,true));
        System.out.println(expected + " is the first of: " + input1 + " and " + input2);
    }

    @Test
    @DisplayName("Simple Test: Sorts descending alphabetically and by length")
    void sortDesc(){
        assertEquals("Bcd", Sort.sortByLengthAndAlphabetically("Abc","Bcd",false));
    }
    @ParameterizedTest
    @DisplayName("Parameterized Test: Sorts descending alphabetically and by length")
    @MethodSource("CompareStringsDescending")
    void sortParamDesc(String expected, String input1, String input2) {
        assertEquals(expected, Sort.sortByLengthAndAlphabetically(input1,input2,false));
        System.out.println(expected + " is the last of: " + input1 + " and " + input2);
    }

    @Test
    @DisplayName("Simple Test: Sorts weight from lowest to highest")
    void sortWeightLowToHigh(){
        assertEquals(2.55, Sort.sortWeight(2.55,3.8, true));
    }
    @ParameterizedTest
    @DisplayName("Parameterized Test: Sorts weight from lowest to highest")
    @MethodSource("CompareWeight")
    void sortWeightLowToHigh(double expected, double input1, double input2){
        assertEquals(expected, Sort.sortWeight(input1,input2, true));
    }

    @Test
    @DisplayName("Simple Test: Sorts weight from highest to lowest")
    void sortWeightLowToHighDescending(){
        assertEquals(3.8, Sort.sortWeight(2.55,3.8, false));
    }
    @ParameterizedTest
    @DisplayName("Parameterized Test: Sorts weight from highest to lowest")
    @MethodSource("CompareWeightDescending")
    void sortWeightLowToHighDescending(double expected, double input1, double input2){
        assertEquals(expected, Sort.sortWeight(input1,input2, false));
    }

}

