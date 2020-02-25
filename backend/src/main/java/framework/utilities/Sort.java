package framework.utilities;
import org.jetbrains.annotations.NotNull;

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
     * @return Returns the ascended or descended string depending on what the user previously wanted.
     */
    public String sortAlphabetically(@NotNull String string, @NotNull String string2){
        int length = sortByLength(string, string2).length();

        String sortedString = "";

        byte[] stringBytes = string.getBytes();
        byte[] string2Bytes = string2.getBytes();

        for(int i = 0; i <= (length-1); i++){
            sortedString = stringBytes[i] <= string2Bytes[i] ? string : string2;
        }
        return sortedString;
    }

    /**
     * @param string The first string you want to compare.
     * @param string2 The second string that will be compared to the first string.
     * @return Returns the shortest or longest word, depending on what the user previously wanted.
     */
    public String sortByLength(@NotNull String string, @NotNull String string2){
        return string.length() <= string2.length() ? string : string2;
    }

    /**
     * @param weight The first weight you want to compare.
     * @param weight2 The second weight that will be compared to the first string.
     * @return String
     */
    public double sortByWeight(double weight, double weight2){
        return Math.min(weight, weight2);
    }

    @Override
    public int sort(int[] array) {

        return 0;
    }

    @Override
    public int sort(double[] array) {

        return 0;
    }

    @Override
    public int sort(float[] array) {

        return 0;
    }

    @Override
    public int sort(char[] array) {

        return 0;
    }

    @Override
    public int sort(long[] array) {

        return 0;
    }

    @Override
    public void reverse() {
        
    }
}
