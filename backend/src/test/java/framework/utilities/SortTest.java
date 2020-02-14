package framework.utilities;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;

/** Testing class for "Sort.java"
 * @author Robert Alexander Dankertsen
 * @author Github: Yeti-Programing @ https://github.com/yeti-programing
 * @version 1.0
 */
@DisplayName("Sorter tests: ")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SortTest {
    /** A stream of arguments that belongs to the parameterized test which sorts alpabetically in ascending order
     * @return Returns the expected outcome, the first variable and second variable
     */
    private static Stream<Arguments> CompareStrings() {
        return Stream.of(
                Arguments.of("Abc", "Abc", "Bcd"),
                Arguments.of("Aleksander", "Alexander", "Aleksander"),
                Arguments.of("Mathias", "Mathias", "Mattias"),
                Arguments.of("Maria", "Maria", "Marie")
        );
    }
    /** A stream of arguments that belongs to the parameterized test which sorts alpabetically in descending order
     * @return Returns the expected outcome, the first variable and second variable
     */
    static Stream<Arguments> CompareStringsDescending() {
        return Stream.of(
                Arguments.of("Bcd", "Abc", "Bcd"),
                Arguments.of("Alexander", "Alexander", "Aleksander"),
                Arguments.of("Mattias", "Mathias", "Mattias"),
                Arguments.of("Marie", "Maria", "Marie")
        );
    }
    /** A stream of arguments that belongs to the parameterized test which sorts by the sortest word first
     * @return Returns the expected outcome, the first variable and second variable
     */
    private static Stream<Arguments> CompareLength() {
        return Stream.of(
                Arguments.of("To", "To", "Tre"),
                Arguments.of("Tre", "Tre", "Fire"),
                Arguments.of("Hei", "Hei", "Hallo"),
                Arguments.of("Norge", "Norge", "Norway")
        );
    }
    /** A stream of arguments that belongs to the parameterized test which sorts by the longest word first
     * @return Returns the expected outcome, the first variable and second variable
     */
    private static Stream<Arguments> CompareLengthLongest() {
        return Stream.of(
                Arguments.of("Tre", "To", "Tre"),
                Arguments.of("Fire", "Tre", "Fire"),
                Arguments.of("Hallo", "Hei", "Hallo"),
                Arguments.of("Norway", "Norge", "Norway")
        );
    }
    /** A stream of arguments that belongs to the parameterized test which sorts weight by lowest first
     * @return Returns the expected outcome, the first variable and second variable
     */
    private static Stream<Arguments> CompareWeight() {
        return Stream.of(
                Arguments.of(2.55, 2.55, 3.8),
                Arguments.of(105, 105, 200),
                Arguments.of(225.55, 225.55, 555.5),
                Arguments.of(11.2, 11.2, 25.77)
        );
    }
    /** A stream of arguments that belongs to the parameterized test which sorts weight by highest first
     * @return Returns the expected outcome, the first variable and second variable
     */
    private static Stream<Arguments> CompareWeightHighest() {
        return Stream.of(
                Arguments.of(3.8, 2.55, 3.8),
                Arguments.of(200, 105, 200),
                Arguments.of(555.5, 225.55, 555.5),
                Arguments.of(25.77, 11.2, 25.77)
        );
    }

    @Test
    @Order(1)
    @Tag("Alphabetical")
    @Tag("Ascending")
    @DisplayName("Simple Test: Sorts ascending alphabetically")
    void sortAlpabeticallyTest(){
        assertEquals("Abc", Sort.sortAlphabetically("Abc","Bcd", true));
    }
    @ParameterizedTest
    @Order(2)
    @Tag("Alphabetical")
    @Tag("Ascending")
    @DisplayName("Parameterized Test: Sorts ascending alphabetically")
    @MethodSource("CompareStrings")
    void sortAlpabeticallyParameterizedTest(String expected, String input1, String input2){
        assertEquals(expected, Sort.sortAlphabetically(input1,input2,true));
        System.out.println(expected + " is the first of: " + input1 + " and " + input2);
    }

    @Test
    @Order(3)
    @Tag("Alphabetical")
    @Tag("Descending")
    @DisplayName("Simple Test: Sorts descending alphabetically")
    void sortAlpabeticallyDescendingTest(){
        assertEquals("Bcd", Sort.sortAlphabetically("Abc","Bcd",false));
    }
    @ParameterizedTest
    @Order(4)
    @Tag("Alphabetical")
    @Tag("Descending")
    @DisplayName("Parameterized Test: Sorts descending alphabetically")
    @MethodSource("CompareStringsDescending")
    void sortAlphabeticallyDescendingParameterizedTest(String expected, String input1, String input2) {
        assertEquals(expected, Sort.sortAlphabetically(input1,input2,false));
        System.out.println(expected + " is the last of: " + input1 + " and " + input2);
    }

    @Test
    @Order(5)
    @Tag("Length")
    @Tag("Ascending")
    @DisplayName("Simple Test: Sorts shortest to longest word")
    void sortLengthTest(){
        assertEquals("To", Sort.sortByLength("To","Tre",true));
    }
    @ParameterizedTest
    @Order(6)
    @Tag("Length")
    @Tag("Ascending")
    @DisplayName("Parameterized Test: Sorts shortest to longest word")
    @MethodSource("CompareLength")
    void sortLengthParameterizedTest(String expected, String input1, String input2){
        assertEquals(expected,Sort.sortByLength(input1,input2,true));
        System.out.println(expected + " is the shortest word of: " + input1 + " and " + input2);
    }

    @Test
    @Order(7)
    @Tag("Length")
    @Tag("Descending")
    @DisplayName("Simple Test: Sorts longest to shortest word")
    void sortLengthLongestTest(){
        assertEquals("Tre", Sort.sortByLength("To","Tre",false));
    }
    @ParameterizedTest
    @Order(8)
    @Tag("Length")
    @Tag("Descending")
    @DisplayName("Parameterized Test: Sorts longest to shortest word")
    @MethodSource("CompareLengthLongest")
    void sortLengthLongestParameterizedTest(String expected, String input1, String input2){
        assertEquals(expected,Sort.sortByLength(input1,input2,false));
        System.out.println(expected + " is the longest word of: " + input1 + " and " + input2);
    }

    @Test
    @Order(9)
    @Tag("Weight")
    @Tag("Ascending")
    @DisplayName("Simple Test: Sorts weight from lowest to highest")
    void sortWeightLowToHighTest(){
        assertEquals(2.55, Sort.sortByWeight(2.55,3.8, true));
    }
    @ParameterizedTest
    @Order(10)
    @Tag("Weight")
    @Tag("Ascending")
    @DisplayName("Parameterized Test: Sorts weight from lowest to highest")
    @MethodSource("CompareWeight")
    void sortWeightLowToHighParameterizedTest(double expected, double input1, double input2){
        assertEquals(expected, Sort.sortByWeight(input1,input2, true));
        System.out.println(expected + " is the lowest weight of " + input1 + " and " + input2);
    }

    @Test
    @Order(11)
    @Tag("Weight")
    @Tag("Descending")
    @DisplayName("Simple Test: Sorts weight from highest to lowest")
    void sortWeightLowToHighHighestTest(){
        assertEquals(3.8, Sort.sortByWeight(2.55,3.8, false));
    }
    @ParameterizedTest
    @Order(12)
    @Tag("Weight")
    @Tag("Descending")
    @DisplayName("Parameterized Test: Sorts weight from highest to lowest")
    @MethodSource("CompareWeightHighest")
    void sortWeightLowToHighHighestParameterizedTest(double expected, double input1, double input2){
        assertEquals(expected, Sort.sortByWeight(input1,input2, false));
        System.out.println(expected + " is the highest weight of " + input1 + " and " + input2);
    }


}