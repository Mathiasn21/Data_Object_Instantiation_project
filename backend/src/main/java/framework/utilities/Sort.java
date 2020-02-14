package framework.utilities;

// TO DO
//Default: Sort by ascending              |    DONE: YES
//Sort strings by length                  |    DONE: YES
//Sort by letters                         |    DONE: YES
//Sort descending (display in reverse)    |    DONE: YES

/**
 * Class for sorting the collected data
 */
public abstract class Sort {
    public static String sort(String s, String s2){
        int length = Math.min(s.length(), s2.length());

        String sortedString = "";

        byte[] sBytes = s.getBytes();
        byte[] s2Bytes = s2.getBytes();

        for(int i = 0; i <= (length-1); i++){
            if(sBytes[i] <= s2Bytes[i]){
                sortedString = s;
            }
            else if(sBytes[i] > s2Bytes[i]){
                sortedString = s2;
            }
        }
        return sortedString;
    }
    public static String sortDescending(String s, String s2){
        int length = Math.min(s.length(), s2.length());

        String sortedString = "";

        byte[] sBytes = s.getBytes();
        byte[] s2Bytes = s2.getBytes();

        for(int i = 0; i <= (length-1); i++){
            if(sBytes[i] <= s2Bytes[i]){
                sortedString = s2;
            }
            else if(sBytes[i] > s2Bytes[i]){
                sortedString = s;
            }
        }
        return sortedString;
    }
}
