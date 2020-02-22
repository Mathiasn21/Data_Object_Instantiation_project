package framework.utilities;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

/* TO DO:
    * Sort type:
        * Sort by alpabetically                   |    DONE: YES
        * Sort by length                          |    DONE: YES
        * Sort by weight                          |    DONE: YES
        * Sort randomly (Just for fun)            |    DONE: YES

    * Sort order:
        * Sort by ascending/lowest/shortest       |    DONE: YES
        * Sort descending/highest/longest         |    DONE: YES
*/

/** Class for sorting the collected data
 * @author Robert Alexander Dankertsen
 * @author Github: Yeti-Programing @ https://github.com/yeti-programing
 * @version 1.0.1
 * @since 1.0.0
 */
public class Sort implements ISort{
    /**
     * @param string The first string you want to compare.
     * @param string2 The second string that will be compared to the first string.
     * @param Ascending A boolean that describes the sorting order
     * @return Returns the ascended or descended string depending on what the user previously wanted.
     */
    public String sortAlphabetically(@NotNull String string, @NotNull String string2, boolean Ascending){
        int length = sortByLength(string, string2, true).length();

        String sortedString = "";

        byte[] stringBytes = string.getBytes();
        byte[] string2Bytes = string2.getBytes();

        for(int i = 0; i <= (length-1); i++){
            if(stringBytes[i] <= string2Bytes[i]){
                sortedString = Ascending ? string : string2;
            } else if(stringBytes[i] > string2Bytes[i]){
                sortedString = Ascending ? string2 : string;
            }
        }
        return sortedString;
    }

    /**
     * @param string The first string you want to compare.
     * @param string2 The second string that will be compared to the first string.
     * @param shortestToLongest A boolean that checks if you want to return the shortest or longest word (true = shortest, false = longest).
     * @return Returns the shortest or longest word, depending on what the user previously wanted.
     */
    public String sortByLength(@NotNull String string, @NotNull String string2, boolean shortestToLongest){
        String sortedString;

        if(string.length() <= string2.length()){
            sortedString = shortestToLongest ? string : string2;
        }
        else{
            sortedString = shortestToLongest ? string2 : string;
        }
        return sortedString;
    }

    /**
     * @param weight The first weight you want to compare.
     * @param weight2 The second weight that will be compared to the first string.
     * @param lowToHigh A boolean that checks if you want to return the lowest og highest weigth (true = lowest, false = highest).
     * @return Returns the lowest or highest weight, depending on what the user perviously wanted.
     */
    public double sortByWeight(double weight, double weight2, boolean lowToHigh){
        double sortedWeight = 0;

        if(weight <= weight2) {
            sortedWeight = lowToHigh ? weight : weight2;
        }
        else if(weight2 > weight){
            sortedWeight = lowToHigh ? weight2 : weight;
        }
        return sortedWeight;
    }

    @Override
    public void sort(int[] array) {

    }

    @Override
    public void sort(double[] array) {

    }

    @Override
    public void sort(float[] array) {

    }

    @Override
    public void sort(char[] array) {

    }

    @Override
    public void sort(long[] array) {

    }

    @Override
    public void orderBy(boolean ascending) {

    }
}
