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

    @Test
    @DisplayName("Simple Test: Sorts alpabetically and by length")
    public void sortAlpabeticallyAndLength(){
        assertEquals("Abc", Sort.sort("Abc","Bcd"));
    }

    @ParameterizedTest
    @DisplayName("Parameterized Test: Sorts alpabetically and by length")
    @MethodSource("CompareStrings")
    public void sortParamAlpabeticallyAndLength(String expected, String input1, String input2){
        assertEquals(expected, Sort.sort(input1,input2));
        System.out.println(expected + " is the first of: " + input1 + " and " + input2);
    }

    private static Stream<Arguments> CompareStrings() {
        return Stream.of(
                Arguments.of("Abc", "Abc", "Bcd"),
                Arguments.of("Aleksander", "Alexander", "Aleksander"),
                Arguments.of("Mathias", "Mathias", "Mattias"),
                Arguments.of("Maria", "Maria", "Marie")
        );
    }

}

