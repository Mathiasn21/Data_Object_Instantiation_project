package framework.utilities;

/* TO DO:
        * Default: Sort by ascending              |    DONE: YES
        * Sort strings by length                  |    DONE: YES
        * Sort by letters                         |    DONE: YES
        * Sort descending (display in reverse)    |    DONE: YES
        * Sort by weight                          |    DONE: NO
        * Sort by group?                          |    DONE: NO
*/

/**
 * Class for sorting the collected data
 */
public abstract class Sort {
    //Sorts by length and alphabetically (Ascending order = boolean true, Descending order = boolean false)
    public static String sortByLengthAndAlphabetically(String string, String string2, boolean Ascending){
        int length = Math.min(string.length(), string2.length());

        String sortedString = "";

        byte[] stringBytes = string.getBytes();
        byte[] string2Bytes = string2.getBytes();

        for(int i = 0; i <= (length-1); i++){
            if(stringBytes[i] <= string2Bytes[i]){
                sortedString = Ascending == true ? string : string2;
            }
            else if(stringBytes[i] > string2Bytes[i]){
                sortedString = Ascending == true ? string2 : string;
            }
        }
        return sortedString;
    }

    //Sorts by weight (lowest to highest)
    public static double sortWeight(double weight, double weight2, boolean lowToHigh){
        double sortedWeight = 0;

        if(weight < weight2) {
            sortedWeight = lowToHigh == true ? weight : weight2;
        }
        else if(weight2 > weight){
            sortedWeight = lowToHigh == true ? weight2 : weight;
        }
        return sortedWeight;
    }
}
