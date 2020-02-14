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

    @Test
    @DisplayName("Simple Test: Sorts ascending alphabetically and by length")
    void sortAsc(){
        assertEquals("Abc", Sort.sort("Abc","Bcd"));
    }

    @ParameterizedTest
    @DisplayName("Parameterized Test: Sorts ascending alphabetically and by length")
    @MethodSource("CompareStrings")
    void sortParamAsc(String expected, String input1, String input2){
        assertEquals(expected, Sort.sort(input1,input2));
        System.out.println(expected + " is the first of: " + input1 + " and " + input2);
    }

    @Test
    @DisplayName("Simple Test: Sorts descending alphabetically and by length")
    void sortDesc(){
        assertEquals("Bcd", Sort.sortDescending("Abc","Bcd"));
    }

    @ParameterizedTest
    @DisplayName("Parameterized Test: Sorts descending alphabetically and by length")
    @MethodSource("CompareStringsDescending")
    void sortParamDesc(String expected, String input1, String input2) {
        assertEquals(expected, Sort.sortDescending(input1,input2));
        System.out.println(expected + " is the first of: " + input1 + " and " + input2);
    }

}

