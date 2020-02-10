package framework.utilities;

// TO DO
//Default: Sort by ascending
//Sort strings by length
//Sort by letters
//Sort descending (display in reverse)

public abstract class Sort {
    public static String sort(String s, String s2){
        int length = Math.min(s.length(), s2.length());
        String sortedString = "";
        for(int i = 0; i <= (length-1); i++){
            if(s.getBytes()[i] <= s2.getBytes()[i]){
                sortedString = s;
            }
            else if(s.getBytes()[i] > s2.getBytes()[i]){
                sortedString = s2;
            }
        }
        return sortedString;
    }
}
