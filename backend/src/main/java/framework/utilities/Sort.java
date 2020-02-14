package framework.utilities;

/* TO DO:
        * Default: Sort by ascending              |    DONE: YES
        * Sort strings by length                  |    DONE: NO
        * Sort by letters                         |    DONE: YES
        * Sort descending (display in reverse)    |    DONE: YES
        * Sort by weight                          |    DONE: YES
        * Sort by group?                          |    DONE: NO
*/

/**
 * Class for sorting the collected data
 */
public abstract class Sort {
    //Sorts by alphabetically (Ascending order = boolean true, Descending order = boolean false)
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

    //Sorts by length of word (Shortest to longest = boolean true, longest to shortest = boolean false)
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

    //Sorts by weight (lowest to highest = boolean true, highest to lowest = boolean false)
    public static double sortWeight(double weight, double weight2, boolean lowToHigh){
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
