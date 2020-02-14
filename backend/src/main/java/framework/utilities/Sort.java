package framework.utilities;

/* TO DO:
        * Default: Sort by ascending              |    DONE: YES
        * Sort strings by length                  |    DONE: YES
        * Sort by letters                         |    DONE: YES
        * Sort descending (display in reverse)    |    DONE: YES
        * Sort by weight                          |    DONE: YES
        * Sort by group?                          |    DONE: NO
*/

/** Class for sorting the collected data
 * @author Robert Alexander Dankertsen
 * @author Github: Yeti-Programing @ https://github.com/yeti-programing
 * @version 1.0
 */
public abstract class Sort {
    /**
     * @param string The first string you want to compare.
     * @param string2 The second string that will be compared to the first string.
     * @param Ascending A boolean that checks if you want to return ascended or descended order (true = ascending, false = descending).
     * @return Returns the ascended or descended string depending on what the user previously wanted.
     */
    public static String sortAlphabetically(String string, String string2, boolean Ascending){
        int length = Math.min(string.length(), string2.length());

        String sortedString = "";

        byte[] stringBytes = string.getBytes();
        byte[] string2Bytes = string2.getBytes();

        for(int i = 0; i <= (length-1); i++){
            if(stringBytes[i] <= string2Bytes[i]){
                sortedString = Ascending ? string : string2;
            }
            else if(stringBytes[i] > string2Bytes[i]){
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
    public static String sortByLength(String string, String string2, boolean shortestToLongest){
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
    public static double sortByWeight(double weight, double weight2, boolean lowToHigh){
        double sortedWeight = 0;

        if(weight <= weight2) {
            sortedWeight = lowToHigh ? weight : weight2;
        }
        else if(weight2 > weight){
            sortedWeight = lowToHigh ? weight2 : weight;
        }
        return sortedWeight;
    }
}
